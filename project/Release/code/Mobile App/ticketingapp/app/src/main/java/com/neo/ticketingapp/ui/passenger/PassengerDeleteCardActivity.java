package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.Card;
import com.neo.ticketingapp.response.model.StringResponse;
import com.neo.ticketingapp.service.PassengerAccountService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerDeleteCardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView codeTxt;
    private TextView cardNumber;
    private Card card;
    private String code;
    private ImageView deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_delete_card);
        Intent intent = getIntent();
        card = (Card) intent.getSerializableExtra("card");
        code = intent.getStringExtra("code");
        initialize();


    }

    private void initialize() {
        codeTxt = findViewById(R.id.DeleteCardCodeTextView);
        cardNumber = findViewById(R.id.DeleteCardCardNumberTextView);

        codeTxt.setText(code);
        cardNumber.setText(card.getCardNo());
        deleteBtn = findViewById(R.id.DeleteCardDeleteBtn);
    }

    private void DeleteCard() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Card card = new Card();
        card.setCardNo(cardNumber.getText().toString());
        GeneralUtil.toastShort(card.getCardNo() + codeTxt.getText().toString(), getApplicationContext()).show();
        Call<StringResponse> call = service.deleteCardDetails(codeTxt.getText().toString(), card.getCardNo());
        call.enqueue(new Callback<StringResponse>() {
            @Override
            public void onResponse(Call<StringResponse> call, Response<StringResponse> response) {
                GeneralUtil.toastShort("Card Details Updated Successfully !", getApplicationContext()).show();
            }

            @Override
            public void onFailure(Call<StringResponse> call, Throwable t) {
                //GeneralUtil.toastShort(t.getMessage(), getApplicationContext()).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.DeleteCardDeleteBtn) {
            DeleteCard();
            GeneralUtil.toastShort("Card Details Deleted Successfully !", getApplicationContext()).show();
            Intent intent = new Intent(getApplicationContext(), PassengerAccountActivity.class);
            startActivity(intent);
        }
    }
}
