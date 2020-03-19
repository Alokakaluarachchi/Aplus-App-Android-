package com.example.aplusapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplusapp.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLogin;
    private Button btnForgotPassword, btnReqAcount;
    private Dialog popupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.add_new_customer);

        this.setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnReqAcount = findViewById(R.id.btnReqAccount);

        //popupDialog.setContentView(R.layout.forget_pw_popup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
