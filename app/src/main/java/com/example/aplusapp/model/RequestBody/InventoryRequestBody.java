package com.example.aplusapp.model.RequestBody;

import com.google.gson.annotations.SerializedName;

public class InventoryRequestBody {

    @SerializedName("ProductName")
    public String pName;

    @SerializedName("ProductCode")
    public String pCode;
    @SerializedName("Qty")
    public String qty;
    @SerializedName("SupplireName")
    public String psName;
    @SerializedName("SupplireEmail")
    public String psEmail;
    @SerializedName("UnitPrice")
    public String pUnitPrice;

    public InventoryRequestBody(String pName, String pCode, String qty, String psName, String psEmail, String pUnitPrice) {
        this.pName = pName;
        this.pCode = pCode;
        this.qty = qty;
        this.psName = psName;
        this.psEmail = psEmail;
        this.pUnitPrice = pUnitPrice;
    }
}
