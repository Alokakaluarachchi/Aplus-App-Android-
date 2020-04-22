package com.example.aplusapp.model;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "customer" ,indices = {@Index("Fristname"), @Index("NIC")})
public class Customers {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int ID;
    @NotNull
    private String Fristname;
    @NotNull
    private String Lastname;
    @NotNull
    private String Email;
    @NotNull
    private String NIC;
    @NotNull
    private int Phone;

    public Customers(int ID, @NotNull String Fristname, @NotNull String Lastname, @NotNull String Email, @NotNull String NIC, @NotNull int Phone) {
        this.ID = ID;
        this.Fristname = Fristname;
        this.Lastname = Lastname;
        this.Email = Email;
        this.NIC = NIC;
        this.Phone = Phone;
    }
    public int getID() { return ID; }

    @NotNull
    public String getFristname(){ return Fristname; }

    @NotNull
    public String getLastname(){ return Lastname; }

    @NotNull
    public String getEmail(){ return Email;}

    @NotNull
    public  String getNIC(){ return NIC; }

    @NotNull
    public int getPhone(){ return Phone; }

}
