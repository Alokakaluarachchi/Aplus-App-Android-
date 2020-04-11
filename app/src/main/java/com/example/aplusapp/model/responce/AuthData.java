package com.example.aplusapp.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthData {
    @Expose
    @SerializedName("userID")
    private Integer userID;

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("roleID")
    private Integer roleID;

    @Expose
    @SerializedName("role")
    private String roleName;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("orgID")
    private Integer organizationID;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("token")
    private String token;

    @Expose
    @SerializedName("authenticated")
    private Boolean authenticated;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Integer organizationID) {
        this.organizationID = organizationID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }
}
