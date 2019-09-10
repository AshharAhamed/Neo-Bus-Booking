package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neo.ticketingapp.R;

public class PassengerHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.MyAccountBtn) {
            Intent intent = new Intent(PassengerHome.this, PassengerAccountActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.MyProfileBtn) {
            Intent intent = new Intent(PassengerHome.this, PassengerProfileActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.OnGoingJourneyBtn) {
            Intent intent = new Intent(PassengerHome.this, PassengerOnGoingJourneyActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.pastLogBtn) {
            Intent intent = new Intent(PassengerHome.this, PassengerLog.class);
            startActivity(intent);
        }
    }
}
