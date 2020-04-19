package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cashier")
public class Cashier {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @NonNull
    private String Items;

    @NonNull
    private int Qty;

    @NonNull
    private String Price;

    private boolean IsActive;

    public Cashier(@NonNull int ID, @NonNull String Items, @NonNull int Qty, @NonNull String Price, boolean IsActive) {

        this.ID = ID;
        this.Items = Items;
        this.Qty = Qty;
        this.Price = Price;
        this.IsActive = IsActive;

    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getItems() {
        return Items;
    }

    @NonNull
    public int getQty() {
        return Qty;
    }

    @NonNull
    public String getPrice() {
        return Price;
    }

    public boolean getIsActive() {
        return IsActive;
    }
}