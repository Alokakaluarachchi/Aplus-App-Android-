package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.RoleRepository;
import com.example.aplusapp.model.RequestBody.RequestNewAccount;
import com.example.aplusapp.model.Role;
import com.example.aplusapp.model.responce.RoleReponce;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.NetworkAccess;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.service.CommonServices;
import com.google.common.collect.FluentIterable;
import com.google.gson.internal.bind.JsonTreeReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.dmoral.toasty.Toasty;
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

    private EditText txtEmail, txtUserName;
    private Button btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_account);

        txtEmail = findViewById(R.id.txtEmail);
        txtUserName = findViewById(R.id.txtUsername);

        btnRequest = findViewById(R.id.btnRequest);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        circularProgressBarDialog = new CircularProgressBarDialog();

        if(!NetworkAccess.isInternetAvailable(getApplicationContext())){
            startActivity(new Intent(ReqAccountActivity.this, NoInternetActivity.class));
            return;
        }

        //saving data to the database. separate from main thread !.
        new dbProcess(this).execute();

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtUserName.getText().toString())){
                    Toasty.warning(getApplicationContext(),
                            "Username is required !", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if(TextUtils.isEmpty(txtEmail.getText().toString())){
                    Toasty.warning(getApplicationContext(),
                            "Email is required !", Toast.LENGTH_SHORT, true).show();
                    return;
                }


                if(!CommonServices.isValidEmail(txtEmail.getText())){
                    Toasty.warning(getApplicationContext(),
                            "Please enter correct Email address !", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if(spinner.getSelectedItem() == null){
                    Toasty.warning(getApplicationContext(),
                            "Please select role !", Toast.LENGTH_SHORT, true).show();
                    return;
                }


                String selectedText = spinner.getSelectedItem().toString();

                new putAccountRequest(txtUserName.getText().toString(), txtEmail.getText().toString(), selectedText).execute();

            }
        });

    }


    private class dbProcess extends AsyncTask<Void, Void, Void> {

        LifecycleOwner context1;

        public dbProcess(LifecycleOwner context) {
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

                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ITEMS);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinner = (MaterialSpinner) findViewById(R.id.spinnerRole);
                                spinner.setAdapter(adapter);

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
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                    circularProgressBarDialog.dismiss();
                }
            });
            return null;
        }
    }

        private class putAccountRequest extends AsyncTask<Void, Void, Void> {

            String userName;
            String email;
            String selectedRoleName;

            public putAccountRequest(String username, String email, String selectedRoleName){
                this.userName = username;
                this.email = email;
                this.selectedRoleName = selectedRoleName;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                //show progress bar
                circularProgressBarDialog.show(getSupportFragmentManager(), null);

                RoleRepository roleRepository = new RoleRepository(getApplication());

                Role role = roleRepository.findByName(selectedRoleName);

                String email = txtEmail.getText().toString();
                String username = txtUserName.getText().toString();

                RequestNewAccount requestNewAccount = new RequestNewAccount(email, username, role.getID());

                Call<Boolean> call = apiService.requestNewAccount(requestNewAccount);

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        if(response.body()){
                            Toasty.success(getApplicationContext(), "Request sent successfully !", Toast.LENGTH_SHORT, true).show();
                            startActivity(new Intent(ReqAccountActivity.this, MainActivity.class));
                        }else{
                            Toasty.warning(getApplicationContext(), "Try again !", Toast.LENGTH_SHORT, true).show();
                        }
                        circularProgressBarDialog.dismiss();
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                        circularProgressBarDialog.dismiss();
                    }
                });
                return null;
            }
        }
    }
