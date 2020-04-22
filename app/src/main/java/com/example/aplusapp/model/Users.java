package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private String RoleName;
    @NonNull
    private String Email;
    @Nullable
    private String Password;
    @NonNull
    private int OrgID;
    @NonNull
    private boolean IsActive;
    @NonNull
    private boolean ModifiedAllowed;

    public Users(int ID, @NonNull String UserName, int RoleID, @NonNull String RoleName, @NonNull String Email, @Nullable String Password, int OrgID, boolean IsActive, boolean ModifiedAllowed) {
        this.ID = ID;
        this.UserName = UserName;
        this.RoleID = RoleID;
        this.RoleName = RoleName;
        this.Email = Email;
        this.Password = Password;
        this.OrgID = OrgID;
        this.IsActive = IsActive;
        this.ModifiedAllowed = ModifiedAllowed;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public void setUserName(@NonNull String userName) {
        UserName = userName;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    @NonNull
    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(@NonNull String roleName) {
        RoleName = roleName;
    }

    @NonNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NonNull String email) {
        Email = email;
    }

    @Nullable
    public String getPassword() {
        return Password;
    }

    public void setPassword(@Nullable String password) {
        Password = password;
    }

    public int getOrgID() {
        return OrgID;
    }

    public void setOrgID(int orgID) {
        OrgID = orgID;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public boolean isModifiedAllowed() {
        return ModifiedAllowed;
    }

    public void setModifiedAllowed(boolean modifiedAllowed) {
        ModifiedAllowed = modifiedAllowed;
    }
}
