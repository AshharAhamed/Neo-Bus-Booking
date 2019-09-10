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
import com.neo.ticketingapp.response.model.Journey;

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
        View view = layoutInflater.inflate(R.layout.passenger_detail,parent, false);

        TextView TextViewAccount = view.findViewById(R.id.cardIDTxt);
        TextViewAccount.setText(inspectorPassengerResponses.get(position).getTravelCardID());

        TextView textViewComplaintType = view.findViewById(R.id.passengerNameTxt);
        textViewComplaintType.setText(inspectorPassengerResponses.get(position).getName());

        TextView textViewComplaintType2 = view.findViewById(R.id.nicTxt);
        textViewComplaintType2.setText(inspectorPassengerResponses.get(position).getNic());

        TextView textViewComplaintType3 = view.findViewById(R.id.typeTxt);
        textViewComplaintType3.setText(inspectorPassengerResponses.get(position).getType());

        return view;
    }
}
