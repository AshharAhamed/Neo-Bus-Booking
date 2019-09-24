package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.request.model.StartJourneyRequest;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.response.model.StartJourneyResult;
import com.neo.ticketingapp.service.JourneyService;
import com.neo.ticketingapp.service.RouteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PassengerJourneyActivity extends AppCompatActivity implements View.OnClickListener {

    private Journey journey;
    private TextView busNoTxt;
    private TextView nextStationTxt;
    private ImageView tapBtn;
    private ImageView confirmBtn;
    private Spinner startStationSpinner;
    private Spinner endStationSpinner;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_journey);
        Intent intent = getIntent();
        this.journey = (Journey) intent.getSerializableExtra("Journey");
        retrofit = GeneralUtil.getGeneralUtilInstance().getRetroFit();
        initializeUIObjects();
        getBusHalts();
    }

    //data binding
    private void initializeUIObjects() {
        this.busNoTxt = findViewById(R.id.busNoTxt);
        this.nextStationTxt = findViewById(R.id.NextStationTxt);
        this.tapBtn = findViewById(R.id.tapBtn);
        this.confirmBtn = findViewById(R.id.confirmBtn);
        this.startStationSpinner = findViewById(R.id.StartStationSpinner);
        this.endStationSpinner = findViewById(R.id.EndStationSpinner);

        tapBtn.setVisibility(View.GONE);
        busNoTxt.setText(journey.getBusNo());
        nextStationTxt.setText(journey.getNextStation());
    }

    //get bus halts on the spinner
    private void getBusHalts() {
        RouteService service = retrofit.create(RouteService.class);

        Call<List<String>> call = service.getBusHalts(journey.getRouteID());

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    List<String> busHaltList = response.body();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            getApplicationContext(),
                            android.R.layout.simple_spinner_item,
                            busHaltList
                    );
                    startStationSpinner.setAdapter(adapter);
                    endStationSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }

    private void startJourney(){
        JourneyService service = retrofit.create(JourneyService.class);

        Call<StartJourneyResult> call = service.startJourney(new StartJourneyRequest(GeneralUtil.getGeneralUtilInstance().getTravelCardID(), startStationSpinner.getSelectedItem().toString(), endStationSpinner.getSelectedItem().toString(), journey.getJourneyID()));

        call.enqueue(new Callback<StartJourneyResult>() {
            @Override
            public void onResponse(Call<StartJourneyResult> call, Response<StartJourneyResult> response) {
                if (response.body() != null) {
                    if (response.body().getError() != null) {
                        GeneralUtil.toastShort(response.body().getError(), getBaseContext()).show();
                    } else {
                        GeneralUtil.toastShort(response.body().getMessage(), getBaseContext()).show();
                        Intent intent = new Intent(PassengerJourneyActivity.this, PassengerTripDetail.class);
                        intent.putExtra("Journey", journey);
                        intent.putExtra("ticketPrice", response.body().getTicketPrice());
                        intent.putExtra("startStation", startStationSpinner.getSelectedItem().toString());
                        intent.putExtra("endStation", endStationSpinner.getSelectedItem().toString());
                        intent.putExtra("logID", response.body().getLogID());
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<StartJourneyResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }

    private void confirmJourney(){
        JourneyService service = retrofit.create(JourneyService.class);

        Call<StartJourneyResult> call = service.validateJourney(new StartJourneyRequest(GeneralUtil.getGeneralUtilInstance().getTravelCardID(), startStationSpinner.getSelectedItem().toString(), endStationSpinner.getSelectedItem().toString(), journey.getJourneyID()));

        call.enqueue(new Callback<StartJourneyResult>() {
            @Override
            public void onResponse(Call<StartJourneyResult> call, Response<StartJourneyResult> response) {
                if (response.body() != null) {
                    if (response.body().getError() != null) {
                        GeneralUtil.toastShort(response.body().getError(), getBaseContext()).show();
                    } else {
                        GeneralUtil.toastShort(response.body().getMessage(), getBaseContext()).show();
                        startStationSpinner.setEnabled(false);
                        endStationSpinner.setEnabled(false);
                        tapBtn.setVisibility(View.VISIBLE);
                        confirmBtn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<StartJourneyResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }

    //setting on click listener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tapBtn) {
            this.startJourney();
        } else if (v.getId() == R.id.confirmBtn) {
            this.confirmJourney();
        }
    }
}
