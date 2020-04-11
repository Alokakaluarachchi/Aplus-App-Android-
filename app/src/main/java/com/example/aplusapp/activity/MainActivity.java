package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.example.aplusapp.network.NetworkAccess;
import com.example.aplusapp.network.UserApiService;
import com.rerlanggas.lib.ExceptionHandler;

import java.util.List;

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

    public CircularProgressBarDialog circularProgressBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //centralize error handler
        ExceptionHandler.init(this, ErrorActivity.class);

        this.setContentView(R.layout.add_new_customer);

        this.setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnReqAcount = findViewById(R.id.btnReqAccount);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        circularProgressBarDialog = new CircularProgressBarDialog();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!NetworkAccess.isInternetAvailable(getApplicationContext())){
                    Toast.makeText(getApplication(), "No internet connection !",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                AuthBody body = new AuthBody(txtUsername.getText().toString(), txtPassword.getText().toString());

                //setup the api call
                Call<AuthData> call = apiService.doLogin(body);

                //show progress bar
                circularProgressBarDialog.show(getSupportFragmentManager(), null);

                //executing
                call.enqueue(new Callback<AuthData>() {
                    @Override
                    public void onResponse(Call<AuthData> call, Response<AuthData> response) {

                        if(!response.body().getAuthenticated() || response.body().getUserID() == null){
                            Toast.makeText(getApplication(), "Username or Password is incorrect",
                                    Toast.LENGTH_LONG).show();

                            //hide progress bar
                            circularProgressBarDialog.dismiss();

                            return;
                        }
                        //saving data to the database. separate from main thread !.
                        new DbProcess(response.body()).execute();

                    }

                    @Override
                    public void onFailure(Call<AuthData> call, Throwable t) {
                        Toast.makeText(getApplication(), t.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();
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

    public class DbProcess extends AsyncTask<Void,Void,Void>{

        private AuthData _data;

        public DbProcess(AuthData data){
            super();
            _data = data;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("dbAccess", "hit the async task");
            UserRepository repo = new UserRepository(getApplication());

            Users user = repo.findByID(_data.getUserID());

            Users authUser = new Users(_data.getUserID(), _data.getUserName(), _data.getRoleID(), _data.getEmail(), txtPassword.getText().toString(), _data.getOrganizationID(), true );

            if(user == null){
                Log.i("dbAccess", "hit as  the new user");
                repo.insertUser(authUser);
            }else{
                Log.i("dbAccess", "hit as  the update user");
                repo.updateUser(authUser);
            }

            startActivity(new Intent(MainActivity.this, HomeActivity.class));

            //hide progress bar
            circularProgressBarDialog.dismiss();

            return null;
        }

    }

}
