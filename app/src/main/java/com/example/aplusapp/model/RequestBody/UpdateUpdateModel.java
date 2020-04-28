package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class UpdateUpdateModel {
    @SerializedName("Email")
    public String email;

    @SerializedName("Password")
    public String password;

    @SerializedName("RoleID")
    public String roleID;

    @SerializedName("PhoneNumber")
    public String phoneNumber;

    public UpdateUpdateModel(String email, String password, String roleID, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.phoneNumber = phoneNumber;
    }
}
