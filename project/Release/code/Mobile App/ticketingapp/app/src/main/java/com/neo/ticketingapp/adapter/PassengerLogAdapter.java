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
import com.neo.ticketingapp.response.model.Journey;
import com.neo.ticketingapp.response.model.PassengerLogResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        TextView TextViewAccount = view.findViewById(R.id.logIDTxt);
        TextViewAccount.setText(passengerLogResponses.get(position).getLogID());

        TextView textViewComplaintType = view.findViewById(R.id.logTicketPriceTxt);
        textViewComplaintType.setText(passengerLogResponses.get(position).getTicketPrice());

        TextView textViewComplaintType2 = view.findViewById(R.id.logStartStationTxt);
        textViewComplaintType2.setText(passengerLogResponses.get(position).getStartStation());

        TextView textViewComplaintType3 = view.findViewById(R.id.logEndStationTxt);
        textViewComplaintType3.setText(passengerLogResponses.get(position).getEndStation());


        TextView textViewComplaintType4 = view.findViewById(R.id.logStartTimeTxt);
        textViewComplaintType4.setText(passengerLogResponses.get(position).getStartTime());

        TextView textViewComplaintType5 = view.findViewById(R.id.logEndTimeTxt);
        textViewComplaintType5.setText(passengerLogResponses.get(position).getEndTime());
        return view;
    }
}
