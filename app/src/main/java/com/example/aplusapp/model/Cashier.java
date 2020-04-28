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

    private boolean IsActive;

    public Cashier(@NonNull int ID,@NonNull String Items,@NonNull int Qty, boolean IsActive) {

        this.ID = ID;
        this.Items = Items;
        this.Qty = Qty;
        this.IsActive = IsActive;

    }

    @NonNull
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

    public boolean getIsActive() {
        return IsActive;
    }
}