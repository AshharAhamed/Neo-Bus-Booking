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

    //Set On Click Listener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.MyAccountBtn) {
            startActivity(new Intent(PassengerHome.this, PassengerAccountActivity.class));
        } else if (v.getId() == R.id.MyProfileBtn) {
            startActivity(new Intent(PassengerHome.this, PassengerProfileActivity.class));
        } else if (v.getId() == R.id.OnGoingJourneyBtn) {
            startActivity(new Intent(PassengerHome.this, PassengerOnGoingJourneyActivity.class));
        } else if (v.getId() == R.id.pastLogBtn) {
            startActivity(new Intent(PassengerHome.this, PassengerLog.class));
        }
    }
}
