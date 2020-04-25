package com.example.aplusapp.activity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.RoleRepository;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.Role;
import com.example.aplusapp.model.Users;
import com.example.aplusapp.model.responce.RoleReponce;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.utils.SharedConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserEditPopupActivity extends DialogFragment {

    private ConstraintLayout backgroundLayout;
    private CircularProgressBarDialog circularProgressBarDialog;

    private EditText txtUserName, txtEmail, txtPhone, txtPassword, txtConfirmPassword;
    private TextView btnClose;
    MaterialSpinner spinner;

    private List<String> ITEMS = new ArrayList<>();

    private Retrofit retrofit;
    private UserApiService apiService;
    ArrayAdapter<String> adapter;

    Bundle mArgs;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backgroundLayout = view.findViewById(R.id.backGround_ediUser);

        txtUserName = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtConfirmPassword = view.findViewById(R.id.txtConfirmPassword);
        spinner = (MaterialSpinner) view.findViewById(R.id.spinnerRole);

        btnClose = view.findViewById(R.id.btnClose);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        circularProgressBarDialog = new CircularProgressBarDialog();

        mArgs = getArguments();
        assert mArgs != null;
        if(Objects.requireNonNull(mArgs.getString(SharedConst.SETTINGS_ID)).length() == 0){
            dismiss();
        }

        new dbProcess(this).execute();

        backgroundLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_user, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.edit_user, null);

        //final Drawable d = new ColorDrawable(Color.BLACK);
        //d.setAlpha(130);

        //dialog.getWindow().setBackgroundDrawable(d);
        dialog.getWindow().setContentView(view);

        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER_VERTICAL;

        dialog.setCanceledOnTouchOutside(true);


        return dialog;
    }


    private class dbProcess extends AsyncTask<Void, Void, Void> {

        LifecycleOwner context1;

        public dbProcess(LifecycleOwner context) {
            context1 = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //show progress bar
            circularProgressBarDialog.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), null);

            Call<List<RoleReponce>> call = apiService.getRoleList();

            call.enqueue(new Callback<List<RoleReponce>>() {
                @Override
                public void onResponse(Call<List<RoleReponce>> call, Response<List<RoleReponce>> response) {
                    Log.i("dbAccess", "hit the async task");
                    RoleRepository roleRepository = new RoleRepository(getActivity().getApplication());

                    List<Role> roles = new ArrayList<>();
                    Role roleNew;
                    for (RoleReponce roleR : response.body()) {
                        roleNew = new Role(roleR.getId(), roleR.getRoleName(), roleR.getRoleDisplayName(), roleR.getEditable() == null ? false : roleR.getEditable());
                        roles.add(roleNew);
                    }

                    roleRepository.insertRole(roles);

                    roleRepository.fetchAllRoles().observe(context1, new Observer<List<Role>>() {
                        @Override
                        public void onChanged(List<Role> roles) {

                            try {
                                for (Role role : roles
                                ) {
                                    ITEMS.add(role.getRole());
                                }

                                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                spinner.setAdapter(adapter);
                                new setData().execute();
                                circularProgressBarDialog.dismiss();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                circularProgressBarDialog.dismiss();
                            }
                        }
                    });

                    return;
                }

                @Override
                public void onFailure(Call<List<RoleReponce>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                    circularProgressBarDialog.dismiss();
                }
            });
            return null;
        }
    }

    private class setData extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            UserRepository repo = new UserRepository(getActivity().getApplication());
            int userID = Integer.parseInt(mArgs.getString(SharedConst.SETTINGS_ID));
            Users user = repo.findByID(userID);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try{

                        txtUserName.setText(user.getUserName());
                        txtEmail.setText(user.getEmail());
                        txtPhone.setText(user.getPhone());
                        spinner.setSelection(adapter.getPosition(user.getRoleName()));
                        circularProgressBarDialog.dismiss();
                    }catch (Exception ex){
                        ex.printStackTrace();
                        circularProgressBarDialog.dismiss();
                    }

                }
            });

            return null;
        }
    }

}
