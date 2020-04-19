package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordReq {
    @SerializedName("Email")
    public String email;

    public ForgotPasswordReq(String email) {
        this.email = email;
    }
}