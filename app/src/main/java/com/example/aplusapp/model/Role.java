package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "role", indices = {@Index("Role")})
public class Role {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    @NonNull
    private String Role;
    @NonNull
    private String DisplayName;
    @NonNull
    private Boolean Editable;

    public Role(@NonNull int ID,@NonNull String Role, @NonNull String DisplayName, @NonNull Boolean Editable) {
        this.ID = ID;
        this.Role = Role;
        this.DisplayName = DisplayName;
        this.Editable = Editable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getRole() {
        return Role;
    }

    public void setRole(@NonNull String role) {
        Role = role;
    }

    @NonNull
    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(@NonNull String displayName) {
        DisplayName = displayName;
    }

    @NonNull
    public Boolean getEditable() {
        return Editable;
    }

    public void setEditable(@NonNull Boolean editable) {
        Editable = editable;
    }
}
