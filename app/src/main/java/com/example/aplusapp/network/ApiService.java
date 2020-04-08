package com.example.aplusapp.network;

import com.example.aplusapp.model.RequestBody.AuthBody;
import com.example.aplusapp.model.responce.AuthData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("users/authenticate")
    Call<List<AuthData>> doLogin(@Body AuthBody authBody);

    @GET("users/forTest")
    Call<Boolean> ApiTest();
}
