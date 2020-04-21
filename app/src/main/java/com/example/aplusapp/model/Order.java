package com.example.aplusapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;

    @NonNull
    private String Items;

    @NonNull
    private int Qty;

    private boolean IsActive;

    public Order(@NonNull int ID, @NonNull String Items,@NonNull int Qty, boolean IsActive){

        this.ID= ID;
        this.Items= Items;
        this.Qty= Qty;
        this.IsActive=IsActive;

    }

    public int getID() {
        return ID;
    }

    @NonNull
    public String getItems(){return Items;}

    @NonNull
    public int getQty(){return Qty;}

    public boolean getIsActive(){return IsActive;}
}
