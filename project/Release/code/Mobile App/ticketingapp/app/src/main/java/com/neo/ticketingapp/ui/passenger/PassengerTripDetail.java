package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerTripDetail extends AppCompatActivity implements View.OnClickListener {

    private Journey journey;
    private TextView currBusNoTxt;
    private TextView ticketPriceTxt;
    private TextView getInTxt;
    private TextView getOutTxt;
    private TextView busRouteNameTxt;
    private ImageView tapOutBtn;
    private String ticketPrice;
    private String startStation;
    private String endStation;
    private String logID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_trip_detail);
        Intent intent = getIntent();
        this.journey = (Journey) intent.getSerializableExtra("Journey");
        this.ticketPrice = (String) intent.getSerializableExtra("ticketPrice");
        this.startStation = (String) intent.getSerializableExtra("startStation");
        this.endStation = (String) intent.getSerializableExtra("endStation");
        this.logID = (String) intent.getSerializableExtra("logID");
        this.initializeUIComponents();
    }

    private void initializeUIComponents() {
        this.currBusNoTxt = findViewById(R.id.currBusNoTxt);
        this.ticketPriceTxt = findViewById(R.id.ticketPriceTxt);
        this.getInTxt = findViewById(R.id.getInTxt);
        this.getOutTxt = findViewById(R.id.getOutTxt);
        this.tapOutBtn = findViewById(R.id.tapOutBtn);
        this.busRouteNameTxt = findViewById(R.id.busRouteNameTxt);

        this.currBusNoTxt.setText(journey.getBusNo());
        this.ticketPriceTxt.setText(this.ticketPrice);
        this.getInTxt.setText(this.startStation);
        this.getOutTxt.setText(this.endStation);
        this.busRouteNameTxt.setText(journey.getRouteName());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tapOutBtn) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server.SERVER_BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JourneyService service = retrofit.create(JourneyService.class);

            Call<String> call = service.endJourney(this.logID);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.body().equals("SUCCESS")) {
                        GeneralUtil.toastShort("Trip Ended Successfully", getBaseContext()).show();
                        Intent intent = new Intent(PassengerTripDetail.this, PassengerHome.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    //To get network errors
                    GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
                }
            });
        }
    }
}
