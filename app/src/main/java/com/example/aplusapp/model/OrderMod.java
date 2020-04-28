package com.example.aplusapp.model;

public class OrderMod {

    private int oId;
    private String oItem;
    private int oQty;


    public OrderMod(int oId, String oItem, int oQty){

        this.oId = oId;
        this.oItem = oItem;
        this.oQty = oQty;

    }


    public int getOid() {
        return oId;
    }

    public void setOid(int oId){ this.oId = oId;}

    public String getOitem() {
        return oItem;
    }

    public void setOitem(String oItem){this.oItem = oItem;}

    public int getOqty() {
        return oQty;
    }

    public void setOqty(int oQty){this.oQty = oQty;}
}
