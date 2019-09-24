package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerProfileActivity extends AppCompatActivity {

    private TextView accountTxt;
    private TextView firstNameTxt;
    private TextView lastNameTxt;
    private TextView emailTxt;
    private TextView mobileTxt;
    private TextView typeTxt;
    private TextView nicTxt;
    private TextView dobTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);
        this.initializeUIObjects();
        this.getPassengerAccountDetails();
    }

    //data binding
    private void initializeUIObjects() {
        this.accountTxt = findViewById(R.id.AccountTxt);
        this.firstNameTxt = findViewById(R.id.FirstNameTxt);
        this.lastNameTxt = findViewById(R.id.LastNameTxt);
        this.emailTxt = findViewById(R.id.EmailTxt);
        this.mobileTxt = findViewById(R.id.MobileTxt);
        this.typeTxt = findViewById(R.id.TypeTxt);
        this.nicTxt = findViewById(R.id.NICTxt);
        this.dobTxt = findViewById(R.id.DoBTxt);
    }

    //render passenger account details
    private void getPassengerAccountDetails() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<PassengerAccountResult> call = service.getPassengerAccount(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                accountTxt.setText(response.body().getAccountId());
                firstNameTxt.setText(response.body().getFirstName());
                lastNameTxt.setText(response.body().getLastName());
                emailTxt.setText(response.body().getEmail());
                mobileTxt.setText(response.body().getContact());
                typeTxt.setText(response.body().getType());
                nicTxt.setText(response.body().getNic());
                dobTxt.setText(GeneralUtil.convertMongoDate(response.body().getDob()));
            }

            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
