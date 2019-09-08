package com.neo.ticketingapp.service;

import com.neo.ticketingapp.responseModels.PassengerAccountResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PassengerAccountService {

    @Headers("Content-Type: application/json")
    @GET("passenger/getPassengerAccount/{travelCardID}")
    Call<PassengerAccountResult> getPassengerAccount(@Path("travelCardID") String travelCardID);
}
