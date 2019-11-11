package com.neo.ticketingapp.ui.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.neo.ticketingapp.R;
import com.neo.ticketingapp.SignInActivity;
import com.neo.ticketingapp.common.GeneralUtil;

public class PassengerHome extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String WEB_URL = "https://neo-bus-frontend.herokuapp.com/";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);
        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setNavigationViewListener() {
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (toggle.onOptionsItemSelected(menuItem))
            return true;

        return super.onOptionsItemSelected(menuItem);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.itemOne: {
                startActivity(new Intent(PassengerHome.this, PassengerAccountActivity.class));
                break;
            }
            case R.id.itemTwo: {
                startActivity(new Intent(PassengerHome.this, PassengerLog.class));
                break;
            }
            case R.id.itemThree: {
                startActivity(new Intent(PassengerHome.this, PassengerProfileActivity.class));
                break;
            }
            case R.id.itemFour: {
                startActivity(new Intent(PassengerHome.this, PassengerOnGoingJourneyActivity.class));
                break;
            }
            case R.id.itemFive: {
                break;
            }
            case R.id.itemSix: {
                break;
            }
            case R.id.itemSeven: {
                startActivity(new Intent(PassengerHome.this, SignInActivity.class));
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
