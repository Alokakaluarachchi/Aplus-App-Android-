package com.example.aplusapp.network;

import com.example.aplusapp.model.responce.InventoryListResult;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InventoryApiService {

    @POST("inventory/removeInventory")
    Call<JSONObject> removeInventory(@Header("Authorization") String token, @Body JSONObject jObj);

    @POST("inventory/getAllUsers")
    Call<List<InventoryListResult>> getInventoryList(@Header("Authorization") String token, @Body JSONObject role);

    @POST("inventory/addInventory")
    Call<Void> addInventory(@Header("Authorization") String token,@Body JSONObject jObj );




}
