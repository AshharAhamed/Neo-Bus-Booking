package com.neo.ticketingapp.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RouteService {

    @Headers("Content-Type: application/json")
    @GET("route/endJourney/{logID}")
    Call<String> endJourney(@Path("logID") String logID);

    @Headers("Content-Type: application/json")
    @GET("route/getBusHalts/{routeID}")
    Call<List<String>> getBusHalts(@Path("routeID") String routeID);
}
