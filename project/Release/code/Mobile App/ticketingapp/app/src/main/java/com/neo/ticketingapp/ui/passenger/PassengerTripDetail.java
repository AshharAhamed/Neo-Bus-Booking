package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.service.JourneyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerTripDetail extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Journey journey;
    private TextView currBusNoTxt;
    private TextView ticketPriceTxt;
    private TextView getInTxt;
    private TextView getOutTxt;
    private TextView busRouteNameTxt;
    private String ticketPrice;
    private String startStation;
    private String endStation;
    private String logID;
    private ImageView tapOutBtn;

    private int tapOutBtnClickCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_trip_detail);
        this.intent = getIntent();
        this.extractSerializableData();
        this.initializeUIComponents();
    }

    //extract data from the serializable Journey object
    private void extractSerializableData() {
        this.journey = (Journey) intent.getSerializableExtra("Journey");
        this.ticketPrice = (String) intent.getSerializableExtra("ticketPrice");
        this.startStation = (String) intent.getSerializableExtra("startStation");
        this.endStation = (String) intent.getSerializableExtra("endStation");
        this.logID = (String) intent.getSerializableExtra("logID");
    }

    //data binding
    private void initializeUIComponents() {
        this.currBusNoTxt = findViewById(R.id.currBusNoTxt);
        this.ticketPriceTxt = findViewById(R.id.ticketPriceTxt);
        this.getInTxt = findViewById(R.id.getInTxt);
        this.getOutTxt = findViewById(R.id.getOutTxt);
        this.busRouteNameTxt = findViewById(R.id.busRouteNameTxt);
        this.tapOutBtn = findViewById(R.id.tapOutBtn);
        this.currBusNoTxt.setText(journey.getBusNo());
        this.ticketPriceTxt.setText(this.ticketPrice);
        this.getInTxt.setText(this.startStation);
        this.getOutTxt.setText(this.endStation);
        this.busRouteNameTxt.setText(journey.getRouteName());

        this.tapOutBtnClickCount = 0;
    }

    private void handleTapOutBtnClick() {
        if (this.tapOutBtnClickCount == 1) {
            tapOutBtn.setEnabled(false);
        }
    }

    //setting Click Listener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tapOutBtn) {
            ++tapOutBtnClickCount;
            handleTapOutBtnClick();
            JourneyService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(JourneyService.class);
            Call<String> call = service.endJourney(this.logID);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.body().equals("SUCCESS")) {
                        GeneralUtil.toastShort("Trip Ended Successfully", getBaseContext()).show();
                        startActivity(new Intent(PassengerTripDetail.this, PassengerHome.class));
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
