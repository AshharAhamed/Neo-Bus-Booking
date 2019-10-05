package com.neo.ticketingapp.service;

import com.neo.ticketingapp.response.model.Card;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.response.model.PassengerLogResponse;
import com.neo.ticketingapp.response.model.StringResponse;
import com.neo.ticketingapp.ui.passenger.PassengerAddCardActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    @Headers("Content-Type: application/json")
    @POST("passenger/addCard/{cardNo}")
    Call<Card> addCardDetails(@Body Card card, @Path("cardNo") String code);

    @Headers("Content-Type: application/json")
    @DELETE("passenger/deleteCard/{code}/{cardNo}")
    Call<StringResponse> deleteCardDetails(@Path("code") String code, @Path("cardNo") String cardNo);

    @Headers("Content-Type: application/json")
    @GET("passenger/recoverPwd/{code}")
    Call<StringResponse> requestRecoverPwd(@Path("code") String code);
}
