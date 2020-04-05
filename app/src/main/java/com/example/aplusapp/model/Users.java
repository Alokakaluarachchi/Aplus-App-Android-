package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index("UserName"), @Index("Email")})
public class Users {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    @NonNull
    private String UserName;
    @NonNull
    private int RoleID;
    @NonNull
    private String Email;
    @NonNull
    private String Password;
    @NonNull
    private int OrgID;
    @NonNull
    private boolean IsActive;

    public Users(int ID, @NonNull String UserName, int RoleID, @NonNull String Email, @NonNull String Password, int OrgID, boolean IsActive) {
        this.ID = ID;
        this.UserName = UserName;
        this.RoleID = RoleID;
        this.Email = Email;
        this.Password = Password;
        this.OrgID = OrgID;
        this.IsActive = IsActive;
    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public int getRoleID() {
        return RoleID;
    }

    @NonNull
    public String getEmail() {
        return Email;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public int getOrgID() {
        return OrgID;
    }

    public boolean getIsActive() {
        return IsActive;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }
}
