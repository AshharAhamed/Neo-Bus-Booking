package com.neo.ticketingapp.ui.inspector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neo.ticketingapp.R;

public class InspectorHome extends AppCompatActivity implements View.OnClickListener {

    Button buttonReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_home);

        buttonReport = findViewById(R.id.buttonReport);

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InspectorHome.this, ReportRoguePassenger.class));
            }
        });
    }

    //On Click Listener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.viewAllJourneysBtn) {
            startActivity(new Intent(InspectorHome.this, InspectorOnGoingJourney.class));
        }/*else if(v.getId() == R.id.buttonReport){
            startActivity(new Intent(InspectorHome.this, ReportRoguePassenger.class));
        }*/
    }
}
