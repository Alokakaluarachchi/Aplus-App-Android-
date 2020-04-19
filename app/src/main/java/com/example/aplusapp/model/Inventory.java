package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventory")
public class Inventory {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    @NonNull
    private String InventoryName;
    @NonNull
    private int Quantity;
    @NonNull
    private String SalesPrice;
    @NonNull
    private String Category;

    private boolean IsActive;

    public Inventory(@NonNull int ID, @NonNull String InventoryName, @NonNull int Quantity, @NonNull String SalesPrice, @NonNull String Category, boolean IsActive) {
        this.ID = ID;
        this.InventoryName = InventoryName;
        this.Quantity=Quantity;
        this.SalesPrice=SalesPrice;
        this.Category=Category;
        this.IsActive = IsActive;
    }
    public int getID() {
        return ID;
    }

    @NonNull
    public String getInventoryName() {
        return InventoryName;
    }

    @NonNull
    public int getQuantity() {
        return Quantity;
    }

    @NonNull
    public String getSalesPrice() {
        return SalesPrice;
    }

    @NonNull
    public String getCategory() {
        return Category;
    }

    public boolean getIsActive() {
        return IsActive;
    }


}