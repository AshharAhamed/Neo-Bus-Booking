package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.SignInActivity;

public class PassengerHome extends AppCompatActivity implements View.OnClickListener  {

    private static final String WEB_URL = "https://neo-bus-frontend.herokuapp.com/";

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
        } else if (v.getId() == R.id.log_out_btn) {
            startActivity(new Intent(PassengerHome.this, SignInActivity.class));
        } else if (v.getId() == R.id.go_to_web_btn) {
            startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse(WEB_URL)));
        }
    }
}
