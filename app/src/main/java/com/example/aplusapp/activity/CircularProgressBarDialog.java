package com.example.aplusapp.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aplusapp.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class CircularProgressBarDialog extends DialogFragment {


    CircularProgressBar circularProgressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        circularProgressBar = view.findViewById(R.id.circularProgressBar);

        // with animation
        circularProgressBar.setProgressWithAnimation(65f); // =1s
        // Set Progress Max
        circularProgressBar.setProgressMax(200f);

        // Set ProgressBar Color
        //circularProgressBar.setProgressBarColorStart(Color.GRAY);
        //circularProgressBar.setProgressBarColorEnd(Color.RED);
        //circularProgressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set background ProgressBar Color
        //circularProgressBar.setBackgroundProgressBarColorStart(Color.WHITE);
        //circularProgressBar.setBackgroundProgressBarColorEnd(Color.RED);
        //circularProgressBar.setBackgroundProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);

        // Set Width
        circularProgressBar.setProgressBarWidth(7f); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(3f); // in DP

        // Other
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_RIGHT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.circular_loader_popup, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.circular_loader_popup, null);

        dialog.getWindow().setContentView(view);


        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER_VERTICAL;

        dialog.setCanceledOnTouchOutside(true);

        return dialog;

    }
}
