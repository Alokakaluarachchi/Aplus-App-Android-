package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.RequestBody.AuthBody;
import com.example.aplusapp.model.Users;
import com.example.aplusapp.model.responce.AuthData;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLogin;
    private Button btnForgotPassword, btnReqAcount;
    private Dialog popupDialog;

    private EditText txtUsername, txtPassword;

    private Retrofit retrofit;
    private UserApiService apiService;
    private AuthData authenticateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.add_new_customer);

        this.setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnReqAcount = findViewById(R.id.btnReqAccount);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthBody body = new AuthBody(txtUsername.getText().toString(), txtPassword.getText().toString());

                //setup the api call
                Call<AuthData> call = apiService.doLogin(body);

                //executing
                call.enqueue(new Callback<AuthData>() {
                    @Override
                    public void onResponse(Call<AuthData> call, Response<AuthData> response) {

                        if(!response.body().getAuthenticated() || response.body().getUserID() == null){
                            Toast.makeText(getApplication(), "Username or Password is incorrect",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        //save to database
                        SaveToDatabase(response.body());

                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }

                    @Override
                    public void onFailure(Call<AuthData> call, Throwable t) {

                    }
                });

            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ForgotPasswordPopup forgotPasswordPopup = new ForgotPasswordPopup();
                forgotPasswordPopup.show(getSupportFragmentManager(),null);

            }
        });

        btnReqAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReqAccountActivity.class));
            }
        });

    }

    public void SaveToDatabase(AuthData data){
        UserRepository repo = new UserRepository(getApplication());

        repo.removeUser(data.getUserID());

        Users users = new Users(data.getUserID(), data.getUserName(), data.getRoleID(), data.getEmail(), txtPassword.getText().toString(), data.getOrganizationID(), true );
        repo.insertUser(users);
    }

}
