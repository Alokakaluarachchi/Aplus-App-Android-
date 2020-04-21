package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class RequestNewAccount {
    @SerializedName("Email")
    public String email;

    @SerializedName("Name")
    public String name;

    @SerializedName("RoleID")
    public int roleID;

    public RequestNewAccount(String email, String name, int roleID) {
        this.email = email;
        this.name = name;
        this.roleID = roleID;
    }
}
