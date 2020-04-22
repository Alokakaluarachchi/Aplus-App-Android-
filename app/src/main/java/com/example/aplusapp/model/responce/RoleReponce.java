package com.example.aplusapp.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleReponce {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("roleName")
    private String roleName;

    @Expose
    @SerializedName("roleDisplayName")
    private String roleDisplayName;

    @Expose
    @SerializedName("Editable")
    private Boolean Editable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDisplayName() {
        return roleDisplayName;
    }

    public void setRoleDisplayName(String roleDisplayName) {
        this.roleDisplayName = roleDisplayName;
    }

    public Boolean getEditable() {
        return Editable;
    }

    public void setEditable(Boolean editable) {
        Editable = editable;
    }
}
