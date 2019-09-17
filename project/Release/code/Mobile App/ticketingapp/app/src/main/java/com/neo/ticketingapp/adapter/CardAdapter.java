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
import com.neo.ticketingapp.response.model.Card;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {
    private Context context;
    private List<Card> cards;

    public CardAdapter(Context context, List<Card> cards) {
        super(context, R.layout.payment_card_detail, cards);
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.payment_card_detail, parent, false);

        TextView cardNoTxt = view.findViewById(R.id.cardNoTxt);
        cardNoTxt.setText(cards.get(position).getCardNo());

        TextView expDateTxt = view.findViewById(R.id.expDateTxt);
        expDateTxt.setText(cards.get(position).getExpiryDate());

        return view;
    }
}
