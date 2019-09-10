package com.neo.ticketingapp.ui.inspector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.JourneyAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InspectorOnGoingJourney extends AppCompatActivity {

    private ListView journeyList;
    private List<Journey> activeJourneyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_on_going_journey);
        this.journeyList = (ListView) findViewById(R.id.OnGoingJourneyList);
        activeJourneyList = new ArrayList<>();
        this.getOnGoingJourneys();
        this.setListener();
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
                activeJourneyList = response.body();
                journeyList.setAdapter(new JourneyAdapter(getBaseContext(), response.body()));
            }
            @Override
            public void onFailure(Call<List<Journey>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }

    public void setListener() {
        this.journeyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Journey journey = activeJourneyList.get(i);
                Intent intent = new Intent(InspectorOnGoingJourney.this, InspectorJourney.class);
                intent.putExtra("Journey", journey);
                startActivity(intent);
            }
        });
    }
}
