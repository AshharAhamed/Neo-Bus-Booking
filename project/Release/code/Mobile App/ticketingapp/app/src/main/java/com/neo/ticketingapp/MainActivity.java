package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.common.constants.Server;
import com.neo.ticketingapp.ui.inspector.InspectorHome;
import com.neo.ticketingapp.ui.passenger.PassengerHome;
import com.neo.ticketingapp.request.model.LoginRequest;
import com.neo.ticketingapp.response.model.LoginResult;
import com.neo.ticketingapp.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView loginBtn = findViewById(R.id.signInBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

}
