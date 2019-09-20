package com.neo.ticketingapp.ui.inspector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.JourneyInfoAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.response.model.InspectorPassengerResponse;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InspectorJourney extends AppCompatActivity {

    Journey journey;
    private ListView passengerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_journey);
        Intent intent = getIntent();
        this.journey = (Journey) intent.getSerializableExtra("Journey");
        this.passengerList = (ListView) findViewById(R.id.passengerList);
        this.getOnGoingPassengers();
    }

    private void getOnGoingPassengers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.SERVER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JourneyService service = retrofit.create(JourneyService.class);
        Call<List<InspectorPassengerResponse>> call = service.getOnGoingPassengers(journey.getJourneyID());

        call.enqueue(new Callback<List<InspectorPassengerResponse>>() {
            @Override
            public void onResponse(Call<List<InspectorPassengerResponse>> call, Response<List<InspectorPassengerResponse>> response) {
                if(response.body() != null)
                    passengerList.setAdapter(new JourneyInfoAdapter(getBaseContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<InspectorPassengerResponse>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
