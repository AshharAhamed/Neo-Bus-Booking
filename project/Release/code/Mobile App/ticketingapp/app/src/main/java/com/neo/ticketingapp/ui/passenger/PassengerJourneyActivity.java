package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.request.model.StartJourneyRequest;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.response.model.StartJourneyResult;
import com.neo.ticketingapp.service.JourneyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerJourneyActivity extends AppCompatActivity implements View.OnClickListener {

    private Journey journey;
    private TextView busNoTxt;
    private TextView nextStationTxt;
    private EditText startStation;
    private EditText endStation;
    private Button tapBtn;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_journey);
        Intent intent = getIntent();
        this.journey = (Journey) intent.getSerializableExtra("Journey");
        initializeUIObjects();
    }

    private void initializeUIObjects() {
        this.busNoTxt = findViewById(R.id.busNoTxt);
        this.nextStationTxt = findViewById(R.id.NextStationTxt);
        this.startStation = findViewById(R.id.startStation);
        this.endStation = findViewById(R.id.endStation);
        this.tapBtn = findViewById(R.id.tapBtn);
        this.confirmBtn = findViewById(R.id.confirmBtn);

        tapBtn.setVisibility(View.GONE);
        busNoTxt.setText(journey.getBusNo());
        nextStationTxt.setText(journey.getNextStation());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tapBtn) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server.SERVER_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JourneyService service = retrofit.create(JourneyService.class);

            Call<StartJourneyResult> call = service.startJourney(new StartJourneyRequest(GeneralUtil.getGeneralUtilInstance().getTravelCardID(), startStation.getText().toString(), endStation.getText().toString(), journey.getJourneyID()));

            call.enqueue(new Callback<StartJourneyResult>() {
                @Override
                public void onResponse(Call<StartJourneyResult> call, Response<StartJourneyResult> response) {
                    if (response.body().getError() != null) {
                        GeneralUtil.toastShort(response.body().getError(), getBaseContext()).show();
                    } else {
                        GeneralUtil.toastShort(response.body().getMessage(), getBaseContext()).show();
                        Intent intent = new Intent(PassengerJourneyActivity.this, PassengerTripDetail.class);
                        intent.putExtra("Journey", journey);
                        intent.putExtra("ticketPrice", response.body().getTicketPrice());
                        intent.putExtra("startStation",  startStation.getText().toString());
                        intent.putExtra("endStation",  endStation.getText().toString());
                        intent.putExtra("logID", response.body().getLogID());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<StartJourneyResult> call, Throwable t) {
                    //To get network errors
                    GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
                }
            });
        }else if (v.getId() == R.id.confirmBtn) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server.SERVER_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JourneyService service = retrofit.create(JourneyService.class);

            Call<StartJourneyResult> call = service.validateJourney(new StartJourneyRequest(GeneralUtil.getGeneralUtilInstance().getTravelCardID(), startStation.getText().toString(), endStation.getText().toString(), journey.getJourneyID()));

            call.enqueue(new Callback<StartJourneyResult>() {
                @Override
                public void onResponse(Call<StartJourneyResult> call, Response<StartJourneyResult> response) {
                    if (response.body().getError() != null) {
                        GeneralUtil.toastShort(response.body().getError(), getBaseContext()).show();
                    } else {
                        GeneralUtil.toastShort(response.body().getMessage(), getBaseContext()).show();
                        tapBtn.setVisibility(View.VISIBLE);
                        confirmBtn.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<StartJourneyResult> call, Throwable t) {
                    //To get network errors
                    GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
                }
            });
        }
    }
}
