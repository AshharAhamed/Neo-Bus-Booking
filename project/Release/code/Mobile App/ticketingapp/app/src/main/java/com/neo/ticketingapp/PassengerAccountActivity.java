package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
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

public class PassengerAccountActivity extends AppCompatActivity {


    private TextView passengerAccountCreditBalance;
    private TextView passengerAccountLoanAmount;
    private TextView passengerAccountTravelCardNo;
    private ListView cardList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);
        passengerAccountCreditBalance = findViewById(R.id.PassengerAccountCreditBalance);
        passengerAccountLoanAmount = findViewById(R.id.PassengerAccountLoanAmount);
        passengerAccountTravelCardNo = findViewById(R.id.PassengerAccountTravelCardNo);
        this.cardList = (ListView) findViewById(R.id.CardList);
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
                passengerAccountCreditBalance.setText(response.body().getCreditBalance());
                passengerAccountLoanAmount.setText(response.body().getLoanAmount());
                passengerAccountTravelCardNo.setText(response.body().getCardNo());
                cardList.setAdapter(new CardAdapter(getBaseContext(), response.body().getCardList()));

            }
            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
