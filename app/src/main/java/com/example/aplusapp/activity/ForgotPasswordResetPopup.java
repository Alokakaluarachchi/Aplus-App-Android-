package com.example.aplusapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aplusapp.R;

public class ForgotPasswordResetPopup extends DialogFragment {

    Button btnReset;
    EditText txtToken, txtPassword, txtPasswordConfirm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReset = view.findViewById(R.id.btnReset);
        txtToken = view.findViewById(R.id.txtResetCode);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtPasswordConfirm = view.findViewById(R.id.txtResetPassword);
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
