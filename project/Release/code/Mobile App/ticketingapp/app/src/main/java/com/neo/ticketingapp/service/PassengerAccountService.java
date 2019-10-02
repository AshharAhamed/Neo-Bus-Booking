package com.neo.ticketingapp.service;

import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.response.model.PassengerLogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PassengerAccountService {

    @Headers("Content-Type: application/json")
    @GET("passenger/getPassengerAccount/{travelCardID}")
    Call<PassengerAccountResult> getPassengerAccount(@Path("travelCardID") String travelCardID);

    @Headers("Content-Type: application/json")
    @PUT("passenger/profile/update")
    Call<PassengerAccountResult> updatePassengerAccount(@Body PassengerAccountResult accountResult);

    @Headers("Content-Type: application/json")
    @GET("log/get/{travelCardID}")
    Call<List<PassengerLogResponse>> getPassengerLog(@Path("travelCardID") String travelCardID);
}
