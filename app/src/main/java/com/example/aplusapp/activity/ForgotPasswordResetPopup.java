package com.example.aplusapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.aplusapp.model.responce.StatusResponce;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.service.CommonServices;
import com.example.aplusapp.utils.SharedConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPasswordResetPopup extends DialogFragment {

    Button btnReset;
    EditText txtToken, txtPassword, txtPasswordConfirm;
    TextView txtEmail;

    private Retrofit retrofit;
    private UserApiService apiService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReset = view.findViewById(R.id.btnReset);
        txtToken = view.findViewById(R.id.txtResetCode);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtPasswordConfirm = view.findViewById(R.id.txtResetPassword);
        txtEmail = view.findViewById(R.id.txtEmail);

        Bundle mArgs = getArguments();
        txtEmail.setText(mArgs.getString(SharedConst.SETTINGS_EMAIL));

        retrofit = APIClient.getClient(); //initialize Retrofit Client
        apiService = retrofit.create(UserApiService.class); //Register the Api Service

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtToken.getText().toString())){
                    Toasty.warning(getActivity(), "Please enter Reset Code !", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if(TextUtils.isEmpty(txtPassword.getText().toString())){
                    Toasty.warning(getActivity(), "Please enter Password !", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if(TextUtils.isEmpty(txtPasswordConfirm.getText().toString())){
                    Toasty.warning(getActivity(), "Please enter Confirm Password !", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                float rate = CommonServices.getPasswordRating(txtPassword.getText().toString());
                if(rate < 4){
                    Toasty.warning(getActivity(), "More complex password is the more secure it will be", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if(!txtPassword.getText().toString().equals(txtPasswordConfirm.getText().toString())){
                    Toasty.error(getActivity(), "Password is not matched", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                JSONObject jObj = new JSONObject();
                try {
                    jObj.put("token", txtToken.getText().toString());
                    jObj.put("password", txtPassword.getText().toString());

                    Call<StatusResponce> call = apiService.doPasswordReset(jObj);

                    call.enqueue(new Callback<StatusResponce>() {
                        @Override
                        public void onResponse(Call<StatusResponce> call, Response<StatusResponce> response) {
                            if(response.body().getStatus() == 1){
                                Toasty.success(getActivity(), "Your password has been reset successfully", Toast.LENGTH_SHORT, true).show();
                                dismiss();
                            }else if(response.body().getStatus() == 2){
                                Toasty.error(getActivity(), "Your Reset password token is expired !", Toast.LENGTH_LONG, true).show();

                                ForgotPasswordPopup forgotPasswordPopup = new ForgotPasswordPopup();
                                forgotPasswordPopup.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),null);

                                dismiss();
                            }else{
                                Toasty.error(getActivity(), "Password reset code is invalid !", Toast.LENGTH_LONG, true).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<StatusResponce> call, Throwable t) {
                            Toasty.error(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT, true).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toasty.error(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT, true).show();
                }


            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_passowrd_popup, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.new_passowrd_popup, null);

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
