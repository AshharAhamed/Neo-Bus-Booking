package com.neo.ticketingapp.ui.inspector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.neo.ticketingapp.R;

public class InspectorHome extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_home);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewAllJourneysBtn) {
            Intent intent = new Intent(InspectorHome.this, InspectorOnGoingJourney.class);
            startActivity(intent);
        }
    }
}
