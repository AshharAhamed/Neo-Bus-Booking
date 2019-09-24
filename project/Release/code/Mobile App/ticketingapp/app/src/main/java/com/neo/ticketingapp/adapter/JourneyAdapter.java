package com.neo.ticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.response.model.Journey;

import java.util.List;

public class JourneyAdapter extends ArrayAdapter<Journey> {
    private Context context;
    private List<Journey> journeys;

    public JourneyAdapter(Context context, List<Journey> journeys) {
        super(context, R.layout.journey_detail, journeys);
        this.context = context;
        this.journeys = journeys;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.journey_detail, parent, false);

        TextView routeNameTxt = view.findViewById(R.id.routeNameTxt);
        routeNameTxt.setText(journeys.get(position).getRouteName());

        TextView busNoTxt = view.findViewById(R.id.busNoTxt);
        busNoTxt.setText(journeys.get(position).getBusNo());

        TextView routeNumber = view.findViewById(R.id.routeNumber);
        routeNumber.setText(journeys.get(position).getRouteNo());

        TextView nextStationTxt = view.findViewById(R.id.nextStationTxt);
        nextStationTxt.setText(journeys.get(position).getNextStation());

        return view;
    }
}
