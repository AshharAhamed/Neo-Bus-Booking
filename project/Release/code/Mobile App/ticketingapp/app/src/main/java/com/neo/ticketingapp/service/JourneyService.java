package com.neo.ticketingapp.service;

import com.neo.ticketingapp.request.model.StartJourneyRequest;
import com.neo.ticketingapp.response.model.InspectorPassengerResponse;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.response.model.StartJourneyResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JourneyService {

    @Headers("Content-Type: application/json")
    @GET("journey/getAllActiveJourneys")
    Call<List<Journey>> getAllActiveJourneys();

    @Headers("Content-Type: application/json")
    @POST("passenger/validateJourney")
    Call<StartJourneyResult> validateJourney(@Body StartJourneyRequest body);

    @Headers("Content-Type: application/json")
    @POST("passenger/startJourney")
    Call<StartJourneyResult> startJourney(@Body StartJourneyRequest body);

    @Headers("Content-Type: application/json")
    @PATCH("passenger/endJourney/{logID}")
    Call<String> endJourney(@Path("logID") String logID);

    @Headers("Content-Type: application/json")
    @GET("journeyPassenger/getAllPassengers/{journeyID}")
    Call<List<InspectorPassengerResponse>> getOnGoingPassengers(@Path("journeyID") String journeyID);
}
