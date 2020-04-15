package com.example.aplusapp.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aplusapp.R;
import com.example.aplusapp.model.RequestBody.ForgotPasswordReq;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.NetworkAccess;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.service.CommonServices;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPasswordPopup extends DialogFragment {

    private TextView btnClose;
    private EditText txtEmail;
    private Button btnReset;

    private Retrofit retrofit;
    private UserApiService apiService;

    private CircularProgressBarDialog circularProgressBarDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //code
        //dismiss();
        btnClose = (TextView) view.findViewById(R.id.btnClose);
        btnReset = view.findViewById(R.id.btnReset);
        txtEmail = view.findViewById(R.id.txtEmail);

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        circularProgressBarDialog = new CircularProgressBarDialog();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!NetworkAccess.isInternetAvailable(getActivity())){
                    Toast.makeText(getActivity(), "No internet connection !",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(txtEmail.getText().length() == 0 || !(CommonServices.isValidEmail(txtEmail.getText().toString()))){
                    Toast.makeText(getActivity(), "Please Enter valid email address",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                ForgotPasswordReq forgotPasswordReq = new ForgotPasswordReq(txtEmail.getText().toString());

                Call<Boolean> call = apiService.resetPasswordRequest(forgotPasswordReq);

                //show progress bar
                circularProgressBarDialog.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), null);

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()){
                            ForgotPasswordResetPopup forgotPasswordPopup = new ForgotPasswordResetPopup();
                            forgotPasswordPopup.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),null);

                            dismiss();
                        }else{
                            Toast.makeText(getActivity(), "The email address you entered is not linked to a A-Plus account",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();

                        //hide progress bar
                        circularProgressBarDialog.dismiss();
                    }
                });

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forget_pw_popup, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.forget_pw_popup, null);

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
}
