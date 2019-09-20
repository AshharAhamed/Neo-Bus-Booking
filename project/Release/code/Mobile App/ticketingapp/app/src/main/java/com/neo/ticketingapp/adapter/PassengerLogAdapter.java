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
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.PassengerLogResponse;

import java.util.List;

public class PassengerLogAdapter extends ArrayAdapter<PassengerLogResponse> {
    private Context context;
    private List<PassengerLogResponse> passengerLogResponses;

    public PassengerLogAdapter(Context context, List<PassengerLogResponse> passengerLogResponses) {
        super(context, R.layout.log_detail, passengerLogResponses);
        this.context = context;
        this.passengerLogResponses = passengerLogResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.log_detail,parent, false);

        TextView logIDTxt = view.findViewById(R.id.logIDTxt);
        logIDTxt.setText(passengerLogResponses.get(position).getLogID());

        TextView logTicketPriceTxt = view.findViewById(R.id.logTicketPriceTxt);
        logTicketPriceTxt.setText(passengerLogResponses.get(position).getTicketPrice());

        TextView logStartStationTxt = view.findViewById(R.id.logStartStationTxt);
        logStartStationTxt.setText(passengerLogResponses.get(position).getStartStation());

        TextView logEndStationTxt = view.findViewById(R.id.logEndStationTxt);
        logEndStationTxt.setText(passengerLogResponses.get(position).getEndStation());

        TextView logStartTimeTxt = view.findViewById(R.id.logStartTimeTxt);
        logStartTimeTxt.setText(GeneralUtil.convertMongoDateTime(passengerLogResponses.get(position).getStartTime()));

        TextView logEndTimeTxt = view.findViewById(R.id.logEndTimeTxt);
        logEndTimeTxt.setText(GeneralUtil.convertMongoDateTime(passengerLogResponses.get(position).getEndTime()));
        return view;
    }
}
