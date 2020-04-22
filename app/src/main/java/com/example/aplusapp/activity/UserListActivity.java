package com.example.aplusapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.example.aplusapp.adepter.UserAdapter;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.User;
import com.example.aplusapp.model.Users;
import com.example.aplusapp.model.responce.AuthData;
import com.example.aplusapp.model.responce.UserListResult;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.service.CryptoHelper;
import com.example.aplusapp.utils.SharedConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserListActivity extends Fragment {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private TextView btnReqAccount;

    private Retrofit retrofit;
    private UserApiService apiService;

    private SharedPreferences pref;

    private CircularProgressBarDialog circularProgressBarDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_user_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        btnReqAccount = rootView.findViewById(R.id.btnRequestAccount);

        circularProgressBarDialog = new CircularProgressBarDialog();

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        pref = getActivity().getApplicationContext().getSharedPreferences(SharedConst.APPLICATION_SHARED_PREF, 0); // 0 - for private mode

        String token = pref.getString(SharedConst.SETTINGS_JWT, null);
        if(token == null){
            Toasty.warning(getActivity(), "Session Expired ! Please log-in again", Toast.LENGTH_SHORT, true).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }

        mAdapter = new UserAdapter(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        try {
            token = CryptoHelper.decrypt(token);
            LoadUserList(token);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        btnReqAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getActivity(), ReqAccountActivity.class));
            }
        });

        return rootView;

    }

    public class LoadUserList extends AsyncTask<Void,Void,Void> {

        String jwtToken;

        public LoadUserList(String token){
            jwtToken = token;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;

        }

    }

    private void LoadUserList(String jwtToken){

        try{
            JSONObject jObj = new JSONObject();
            try {
                jObj.put("roleId",1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //setup the api call
            Call<List<UserListResult>> call = apiService.getAllUsers("Bearer "+jwtToken, jObj);

            //show progress bar
            circularProgressBarDialog.show(getChildFragmentManager() , null);

            call.enqueue(new Callback<List<UserListResult>>() {
                @Override
                public void onResponse(Call<List<UserListResult>> call, Response<List<UserListResult>> response) {

                    if(response.code() == 401){
                        Toasty.warning(getActivity(), "Session Expired !", Toast.LENGTH_SHORT, true).show();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }

                    userList.clear();
                    User user;
                    for (UserListResult result: response.body()
                    ) {
                        user = new User(result.getUserName(), result.getRoleName());
                        userList.add(user);
                    }

                    mAdapter.notifyDataSetChanged();
                    circularProgressBarDialog.dismiss();

                    //save to database
                    new DbProcess(response.body()).execute();

                }

                @Override
                public void onFailure(Call<List<UserListResult>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                    circularProgressBarDialog.dismiss();
                }
            });


        }catch (Exception ex){
            Toast.makeText(getActivity(), ex.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
            circularProgressBarDialog.dismiss();
        }

    }

    public class DbProcess extends AsyncTask<Void,Void,Void>{

        private List<UserListResult> _data;

        public DbProcess(List<UserListResult> data){
            super();
            _data = data;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("dbAccess", "hit the async task");
            UserRepository repo = new UserRepository(getActivity().getApplication());

            repo.removeUser();



            return null;
        }

    }
}
