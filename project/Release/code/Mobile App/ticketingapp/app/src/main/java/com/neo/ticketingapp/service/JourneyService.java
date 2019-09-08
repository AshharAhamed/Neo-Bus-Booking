package com.neo.ticketingapp.service;

import com.neo.ticketingapp.responseModels.Journey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JourneyService {

    @Headers("Content-Type: application/json")
    @GET("journey/getAllActiveJourneys")
    Call<List<Journey>> getAllActiveJourneys();
}
