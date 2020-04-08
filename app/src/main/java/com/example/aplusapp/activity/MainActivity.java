package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplusapp.R;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.RequestBody.AuthBody;
import com.example.aplusapp.model.Users;
import com.example.aplusapp.model.responce.AuthData;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLogin;
    private Button btnForgotPassword, btnReqAcount;
    private Dialog popupDialog;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.add_new_customer);

        this.setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnReqAcount = findViewById(R.id.btnReqAccount);

        retrofit = APIClient.getClient();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiService apiService = retrofit.create(ApiService.class);

                AuthBody body = new AuthBody("shalithax@gmail.com", "Shalitha@2018");

                JSONObject paramObject = new JSONObject();
                try {
                    paramObject.put("email", "sample@gmail.com");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Call<Boolean> call = apiService.ApiTest();

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.code() == 400){
                            try {
                                Log.i("TAG", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("TAG", "ok");
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.i("TAG", t.getLocalizedMessage());
                    }
                });

                UserRepository repo = new UserRepository(getApplication());

                Users users = new Users(1, "shalitha", 1, "shalitha", "asasas", 1, true);
                repo.insertUser(users);

                startActivity(new Intent(MainActivity.this, HomeActivity.class));



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

}
