package com.example.aplusapp.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryListResult {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("inventoryName")
    private String InventoryName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInventoryName() {
        return InventoryName;
    }

    public void setInventoryName(String inventoryName) {
        InventoryName = inventoryName;
    }
}
