package com.neo.ticketingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.response.model.InspectorPassengerResponse;

import java.util.List;

public class JourneyInfoAdapter extends ArrayAdapter<InspectorPassengerResponse> {
    private Context context;
    private List<InspectorPassengerResponse> inspectorPassengerResponses;

    public JourneyInfoAdapter(Context context, List<InspectorPassengerResponse> inspectorPassengerResponses) {
        super(context, R.layout.passenger_detail, inspectorPassengerResponses);
        this.context = context;
        this.inspectorPassengerResponses = inspectorPassengerResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.passenger_detail, parent, false);

        TextView cardIDTxt = view.findViewById(R.id.cardIDTxt);
        cardIDTxt.setText(inspectorPassengerResponses.get(position).getTravelCardID());

        TextView passengerNameTxt = view.findViewById(R.id.passengerNameTxt);
        passengerNameTxt.setText(inspectorPassengerResponses.get(position).getName());

        TextView nicTxt = view.findViewById(R.id.nicTxt);
        nicTxt.setText(inspectorPassengerResponses.get(position).getNic());

        TextView typeTxt = view.findViewById(R.id.typeTxt);
        typeTxt.setText(inspectorPassengerResponses.get(position).getType());

        return view;
    }
}
