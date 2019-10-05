package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.CardAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.Card;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView passengerAccountCreditBalance;
    private TextView passengerAccountLoanAmount;
    private TextView passengerAccountTravelCardNo;
    private ListView cardList;
    private ImageView addCardBtn;
    private List<Card> activeCard;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);
        this.initializeUIComponents();
        this.setOnListItemClickListner();
        this.getPassengerAccountDetails();
    }

    //data binding
    private void initializeUIComponents() {
        passengerAccountCreditBalance = findViewById(R.id.PassengerAccountCreditBalance);
        passengerAccountLoanAmount = findViewById(R.id.PassengerAccountLoanAmount);
        passengerAccountTravelCardNo = findViewById(R.id.PassengerAccountTravelCardNo);
        this.cardList = (ListView) findViewById(R.id.CardList);
        this.addCardBtn = findViewById(R.id.AddCardBtn);
    }

    //render Passenger Account Details
    private void getPassengerAccountDetails() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<PassengerAccountResult> call = service.getPassengerAccount(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                activeCard = response.body().getCardList();
                code = response.body().getCardNo();
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

    private void setOnListItemClickListner() {
        this.cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Card card = activeCard.get(i);
                Intent intent = new Intent(getApplicationContext(), PassengerDeleteCardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("card", card);
                intent.putExtras(bundle);
                intent.putExtra("code", code);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddCardBtn) {
            Intent intent = new Intent(getApplicationContext(), PassengerAddCardActivity.class);
            intent.putExtra("code", code);
            startActivity(intent);
        }
    }
}
