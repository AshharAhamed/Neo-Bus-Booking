package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.CardAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerAccountActivity extends AppCompatActivity {

    private TextView passengerAccountCreditBalance;
    private TextView passengerAccountLoanAmount;
    private TextView passengerAccountTravelCardNo;
    private ListView cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);
        this.initializeUIComponents();
        this.getPassengerAccountDetails();
    }

    //data binding
    private void initializeUIComponents() {
        passengerAccountCreditBalance = findViewById(R.id.PassengerAccountCreditBalance);
        passengerAccountLoanAmount = findViewById(R.id.PassengerAccountLoanAmount);
        passengerAccountTravelCardNo = findViewById(R.id.PassengerAccountTravelCardNo);
        this.cardList = (ListView) findViewById(R.id.CardList);
    }

    //render Passenger Account Details
    private void getPassengerAccountDetails() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<PassengerAccountResult> call = service.getPassengerAccount(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                passengerAccountCreditBalance.setText(String.format("Rs. %s", response.body().getCreditBalance()));
                passengerAccountLoanAmount.setText(String.format("Rs. %s", response.body().getLoanAmount()));
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
