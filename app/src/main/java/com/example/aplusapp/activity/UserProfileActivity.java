package com.example.aplusapp.activity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.RoleRepository;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.Role;
import com.example.aplusapp.model.User;
import com.example.aplusapp.model.Users;
import com.example.aplusapp.model.responce.UserListResult;
import com.example.aplusapp.utils.SharedConst;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class UserProfileActivity extends Fragment {

    private TextView txtFullName, txtMainRole, txtEmail, txtBranch, txtRole, txtContact, btnBack;

    private SharedPreferences pref;

    private CircularProgressBarDialog circularProgressBarDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_user_profile, container, false);

        txtFullName = rootView.findViewById(R.id.txtFullName);
        txtMainRole = rootView.findViewById(R.id.txtMainRole);
        txtEmail = rootView.findViewById(R.id.txtEmail);
        txtBranch = rootView.findViewById(R.id.txtBranch);
        txtRole = rootView.findViewById(R.id.txtRole);
        txtContact = rootView.findViewById(R.id.txtContact);
        btnBack = rootView.findViewById(R.id.btnBack);
        circularProgressBarDialog = new CircularProgressBarDialog();

        pref = getActivity().getApplicationContext().getSharedPreferences(SharedConst.APPLICATION_SHARED_PREF, 0); // 0 - for private mode

        new DbProcess().execute();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        return rootView;
    }

    public class DbProcess extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //show progress bar
            circularProgressBarDialog.show(getChildFragmentManager() , null);

            int currentUserID =pref.getInt(SharedConst.APP_USERID, 0);

            if(currentUserID == 0){
                Toasty.warning(getActivity(), "Something went wrong ! Please login again", Toast.LENGTH_SHORT, true).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }

            Log.i("dbAccess", "hit the async task");
            UserRepository repo = new UserRepository(getActivity().getApplication());

            Users user = repo.findByID(currentUserID);

            if(user == null){
                Toasty.warning(getActivity(), "Something went wrong ! Please login again", Toast.LENGTH_SHORT, true).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtFullName.setText(user.getUserName());
                    txtMainRole.setText(user.getRoleName());
                    txtRole.setText(user.getRoleName());
                    txtEmail.setText(user.getEmail());
                    txtContact.setText(user.getPhone());
                }
            });

            circularProgressBarDialog.dismiss();
            return null;
        }

    }
}
