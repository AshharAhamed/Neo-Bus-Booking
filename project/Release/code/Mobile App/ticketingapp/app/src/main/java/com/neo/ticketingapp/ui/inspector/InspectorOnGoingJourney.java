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
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InspectorOnGoingJourney extends AppCompatActivity {

    private ListView journeyList;
    private List<Journey> activeJourneyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_on_going_journey);
        this.initializeUIComponents();
        activeJourneyList = new ArrayList<>();
        this.getOnGoingJourneys();
        this.setListener();
    }

    //data binding
    private void initializeUIComponents() {
        this.journeyList = (ListView) findViewById(R.id.OnGoingJourneyList);
    }

    //renders all on going journeys
    private void getOnGoingJourneys() {
        JourneyService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(JourneyService.class);
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

    //listener for the list
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
