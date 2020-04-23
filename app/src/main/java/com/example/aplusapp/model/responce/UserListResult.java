package com.example.aplusapp.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserListResult {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("userName")
    private String UserName;
    @Expose
    @SerializedName("email")
    private String Email;
    @Expose
    @SerializedName("roleId")
    private int RoleID;
    @Expose
    @SerializedName("roleName")
    private String RoleName;
    @Expose
    @SerializedName("modifyAllowed")
    private Boolean modifyAllowed;
    @Expose
    @SerializedName("locked")
    private String Locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public Boolean getModifyAllowed() {
        return modifyAllowed;
    }

    public void setModifyAllowed(Boolean modifyAllowed) {
        this.modifyAllowed = modifyAllowed;
    }

    public String getLocked() {
        return Locked;
    }

    public void setLocked(String locked) {
        Locked = locked;
    }
}
