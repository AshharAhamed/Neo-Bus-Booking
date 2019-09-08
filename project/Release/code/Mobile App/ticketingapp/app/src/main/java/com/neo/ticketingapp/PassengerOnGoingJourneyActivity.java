package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.neo.ticketingapp.adapter.JourneyAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.responseModels.Journey;
import com.neo.ticketingapp.service.JourneyService;
import com.neo.ticketingapp.service.PassengerAccountService;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerOnGoingJourneyActivity extends AppCompatActivity {

    private ListView journeyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_on_going_journey);
        this.journeyList = (ListView) findViewById(R.id.OnGoingJourneyList);
        this.getOnGoingJourneys();
    }

    private void getOnGoingJourneys(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.SERVER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JourneyService service = retrofit.create(JourneyService.class);
        Call<List<Journey>> call = service.getAllActiveJourneys();

        call.enqueue(new Callback<List<Journey>>() {
            @Override
            public void onResponse(Call<List<Journey>> call, Response<List<Journey>> response) {
                journeyList.setAdapter(new JourneyAdapter(getBaseContext(), response.body()));
            }
            @Override
            public void onFailure(Call<List<Journey>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
