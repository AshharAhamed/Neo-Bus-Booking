package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.Card;
import com.neo.ticketingapp.service.PassengerAccountService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerAddCardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout cardNumberInputLayout;
    private TextInputLayout cvcInputLayout;
    private EditText cardNumbersEditText;
    private Spinner monthSpinner;
    private Spinner yearSpinner;
    private EditText cvcNumberEditText;
    private ImageView addCardBtn;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_add_card);
        initializeElements();
    }

    private void initializeElements() {
        this.cardNumberInputLayout = findViewById(R.id.AddCardCardNumInputLayout);
        this.cvcInputLayout = findViewById(R.id.AddCardCVCInputLayout);

        this.cardNumbersEditText = findViewById(R.id.AddCardCardNumberEditText);
        this.monthSpinner = findViewById(R.id.AddCardMonthSpinner);
        this.yearSpinner = findViewById(R.id.AddCardYearSpinner);
        this.cvcNumberEditText = findViewById(R.id.AddCardCVCEditText);

        this.addCardBtn = findViewById(R.id.AddCardAddCardBtn);
        Intent intent = getIntent();
        code = intent.getStringExtra("code");
    }

    private String getExpiryDate() {
        String month = monthSpinner.getSelectedItem().toString();
        String year = yearSpinner.getSelectedItem().toString();
        String expiryDate = year + "-" + month + "-" + "01";
        return expiryDate;
    }

    private void AddCard() {
        String cardNumber = cardNumbersEditText.getText().toString();
        String cvcNumber = cvcNumberEditText.getText().toString();
        Card card = new Card(cardNumber, this.getExpiryDate(), cvcNumber);
        GeneralUtil.toastShort(getExpiryDate(), getApplicationContext()).show();
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<Card> call = service.addCardDetails(card, code);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                GeneralUtil.toastShort("Card Details Added Successfully !", getApplicationContext()).show();
                Intent intent = new Intent(getApplicationContext(), PassengerAccountActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddCardAddCardBtn) {
            this.AddCard();
        }
    }
}
