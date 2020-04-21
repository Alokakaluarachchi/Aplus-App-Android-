package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.RoleRepository;
import com.example.aplusapp.model.Role;
import com.example.aplusapp.model.responce.RoleReponce;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.NetworkAccess;
import com.example.aplusapp.network.UserApiService;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReqAccountActivity extends AppCompatActivity {

    MaterialSpinner spinner;
    private List<String> ITEMS = new ArrayList<>();

    private Retrofit retrofit;
    private UserApiService apiService;

    private CircularProgressBarDialog circularProgressBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_account);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        circularProgressBarDialog = new CircularProgressBarDialog();

        if(!NetworkAccess.isInternetAvailable(getApplicationContext())){
            startActivity(new Intent(ReqAccountActivity.this, NoInternetActivity.class));

            return;
        }

        //saving data to the database. separate from main thread !.
        new dbProcess(this).execute();

    }

    private class dbProcess extends AsyncTask<Void, Void, Void>{

        LifecycleOwner context1;

        public dbProcess(LifecycleOwner context){
            context1 = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //show progress bar
            circularProgressBarDialog.show(getSupportFragmentManager(), null);

            Call<List<RoleReponce>> call = apiService.getRoleList();

            call.enqueue(new Callback<List<RoleReponce>>() {
                @Override
                public void onResponse(Call<List<RoleReponce>> call, Response<List<RoleReponce>> response) {
                    Log.i("dbAccess", "hit the async task");
                    RoleRepository roleRepository = new RoleRepository(getApplication());

                    List<Role> roles = new ArrayList<>();
                    Role roleNew;
                    for (RoleReponce roleR: response.body()) {
                        roleNew = new Role(roleR.getId(), roleR.getRoleName(), roleR.getRoleDisplayName(), roleR.getEditable() == null ? false : roleR.getEditable());
                        roles.add(roleNew);
                    }


                    roleRepository.insertRole(roles);

                    roleRepository.fetchAllRoles().observe( context1, new Observer<List<Role>>() {
                        @Override
                        public void onChanged(List<Role> roles) {

                            try{
                                for (Role role: roles
                                ) {
                                    ITEMS.add(role.getRole());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ITEMS);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinner = (MaterialSpinner) findViewById(R.id.spinnerRole);
                                spinner.setAdapter(adapter);

                                circularProgressBarDialog.dismiss();
                            }catch (Exception ex){
                                ex.printStackTrace();
                                circularProgressBarDialog.dismiss();
                            }
                        }
                    });

                    return ;
                }

                @Override
                public void onFailure(Call<List<RoleReponce>> call, Throwable t) {
                    circularProgressBarDialog.dismiss();
                }
            });
            return null;
        }
    }


}
