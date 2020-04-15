package com.example.aplusapp.network;

import com.example.aplusapp.model.RequestBody.AuthBody;
import com.example.aplusapp.model.RequestBody.ForgotPasswordReq;
import com.example.aplusapp.model.responce.AuthData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApiService {

    @POST("users/authenticate")
    Call<AuthData> doLogin(@Body AuthBody authBody);

    @POST("users/resetPasswordMobile")
    Call<Boolean> resetPasswordRequest(@Body ForgotPasswordReq data);

    @GET("users/forTest")
    Call<Boolean> ApiTest();
}
