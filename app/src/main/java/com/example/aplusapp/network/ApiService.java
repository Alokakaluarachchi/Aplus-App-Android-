package com.example.aplusapp.network;

import com.example.aplusapp.model.RequestBody.AuthBody;
import com.example.aplusapp.model.responce.AuthData;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/authenticate")
    Single<AuthData> doLogin(@Body AuthBody authBody);
}
