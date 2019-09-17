package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerProfileActivity extends AppCompatActivity {

    private TextView accountTxt;
    private TextView firstNameTxt;
    private TextView lastNameTxt;
    private TextView emailTxt;
    private TextView mobileTxt;
    private TextView typeTxt;
    private TextView nicTxt;
    private TextView dobtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);
        this.accountTxt = findViewById(R.id.AccountTxt);
        this.firstNameTxt = findViewById(R.id.FirstNameTxt);
        this.lastNameTxt = findViewById(R.id.LastNameTxt);
        this.emailTxt = findViewById(R.id.EmailTxt);
        this.mobileTxt = findViewById(R.id.MobileTxt);
        this.typeTxt = findViewById(R.id.TypeTxt);
        this.nicTxt = findViewById(R.id.NICTxt);
        this.dobtxt = findViewById(R.id.DoBTxt);
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
                accountTxt.setText(response.body().getAccountId());
                firstNameTxt.setText(response.body().getFirstName());
                lastNameTxt.setText(response.body().getLastName());
                emailTxt.setText(response.body().getEmail());
                mobileTxt.setText(response.body().getContact());
                typeTxt.setText(response.body().getType());
                nicTxt.setText(response.body().getNic());
                dobtxt.setText(response.body().getDob());
            }
            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
