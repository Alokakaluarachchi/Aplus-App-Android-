package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class RequestNewAccount {
    @SerializedName("Email")
    public String email;

    @SerializedName("Name")
    public String name;

    @SerializedName("RoleID")
    public int roleID;
}
