package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.neo.ticketingapp.adapter.CardAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.responseModels.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerProfileActivity extends AppCompatActivity {

    private TextView AccountTxt;
    private TextView FirstNameTxt;
    private TextView LastNameTxt;
    private TextView EmailTxt;
    private TextView MobileTxt;
    private TextView TypeTxt;
    private TextView NICTxt;
    private TextView DoBTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);
        this.AccountTxt = findViewById(R.id.AccountTxt);
        this.FirstNameTxt = findViewById(R.id.FirstNameTxt);
        this.LastNameTxt = findViewById(R.id.LastNameTxt);
        this.EmailTxt = findViewById(R.id.EmailTxt);
        this.MobileTxt = findViewById(R.id.MobileTxt);
        this.TypeTxt = findViewById(R.id.TypeTxt);
        this.NICTxt = findViewById(R.id.NICTxt);
        this.DoBTxt = findViewById(R.id.DoBTxt);
        this.getPassengerAccountDetails();
    }

    private void getPassengerAccountDetails(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.SERVER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PassengerAccountService service = retrofit.create(PassengerAccountService.class);
        Call<PassengerAccountResult> call = service.getPassengerAccount(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                AccountTxt.setText(response.body().getAccountId());
                FirstNameTxt.setText(response.body().getFirstName());
                LastNameTxt.setText(response.body().getLastName());
                EmailTxt.setText(response.body().getEmail());
                MobileTxt.setText(response.body().getContact());
                TypeTxt.setText(response.body().getType());
                NICTxt.setText(response.body().getNic());
                DoBTxt.setText(response.body().getDob());
            }
            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
