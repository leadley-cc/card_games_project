package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.michael.cardgameproject.R;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.List;

/**
 * Created by michael on 26/09/2017.
 */

public class CardTextAdapter extends ArrayAdapter<Card> {
    public CardTextAdapter(Context context, List<Card> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.card_picker_item, parent, false);
        }

        Card currentCard = getItem(position);

        TextView cardText = listItemView.findViewById(R.id.card_item_text);

        if (currentCard != null) {
            cardText.setText(String.format(
                    "%s %s of %s",
                    getSuitEmoji(currentCard.getSuit()),
                    currentCard.getRank().name(),
                    currentCard.getSuit().name()
            ));

            listItemView.setTag(currentCard);
        }

        return listItemView;
    }

    private String getSuitEmoji(CardSuit suit) {
        switch (suit) {
            case CLUBS:
                return "♣️";
            case DIAMONDS:
                return "♦️";
            case HEARTS:
                return "♥️";
            case SPADES:
                return "♠️";
            default:
                return "";
        }
    }
}
