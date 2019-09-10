package com.neo.ticketingapp.service;

import com.neo.ticketingapp.requestModels.StartJourneyRequest;
import com.neo.ticketingapp.responseModels.Journey;
import com.neo.ticketingapp.responseModels.StartJourneyResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JourneyService {

    @Headers("Content-Type: application/json")
    @GET("journey/getAllActiveJourneys")
    Call<List<Journey>> getAllActiveJourneys();


    @Headers("Content-Type: application/json")
    @POST("passenger/startJourney")
    Call<StartJourneyResult> startJourney(@Body StartJourneyRequest body);
}
