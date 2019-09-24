package com.neo.ticketingapp.ui.inspector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.JourneyInfoAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.InspectorPassengerResponse;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectorJourney extends AppCompatActivity {

    Journey journey;
    private ListView passengerList;
    private Intent intent;
    private SwipeRefreshLayout pullToRefreshPassengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_journey);
        intent = getIntent();
        this.initializeUIComponents();
        this.getOnGoingPassengers();
        this.setListener();
    }

    //data binding
    private void initializeUIComponents() {
        this.journey = (Journey) this.intent.getSerializableExtra("Journey");
        this.passengerList = (ListView) findViewById(R.id.passengerList);
        this.pullToRefreshPassengers = findViewById(R.id.PullToRefreshPassengers);
    }

    //render all Passengers in a Journey
    private void getOnGoingPassengers() {
        JourneyService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(JourneyService.class);
        Call<List<InspectorPassengerResponse>> call = service.getOnGoingPassengers(journey.getJourneyID());

        call.enqueue(new Callback<List<InspectorPassengerResponse>>() {
            @Override
            public void onResponse(Call<List<InspectorPassengerResponse>> call, Response<List<InspectorPassengerResponse>> response) {
                if (response.body() != null)
                    passengerList.setAdapter(new JourneyInfoAdapter(getBaseContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<InspectorPassengerResponse>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }

    //listener for the list
    public void setListener() {
        this.pullToRefreshPassengers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOnGoingPassengers();
                pullToRefreshPassengers.setRefreshing(false);
            }
        });
    }
}
