package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;
public class AuthBody {
    @SerializedName("Email")
    public String email;

    @SerializedName("Password")
    public String password;

    public AuthBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}