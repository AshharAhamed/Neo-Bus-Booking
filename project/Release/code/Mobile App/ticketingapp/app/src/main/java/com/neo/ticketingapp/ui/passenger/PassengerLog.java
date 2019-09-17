package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.PassengerLogAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.response.model.PassengerLogResponse;
import com.neo.ticketingapp.service.PassengerAccountService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PassengerLog extends AppCompatActivity {

    private ListView passengerLogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_log);
        this.passengerLogList = (ListView) findViewById(R.id.passengerLogList);
        this.getLog();
    }

    private void getLog(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Server.SERVER_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PassengerAccountService service = retrofit.create(PassengerAccountService.class);
        Call<List<PassengerLogResponse>> call = service.getPassengerLog(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<List<PassengerLogResponse>>() {
            @Override
            public void onResponse(Call<List<PassengerLogResponse>> call, Response<List<PassengerLogResponse>> response) {
                passengerLogList.setAdapter(new PassengerLogAdapter(getBaseContext(), response.body()));
            }
            @Override
            public void onFailure(Call<List<PassengerLogResponse>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }
}
