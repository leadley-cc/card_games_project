package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import android.app.Activity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.michael.cardgameproject.playingcards.Card;

import java.util.List;

import static android.os.SystemClock.sleep;

/**
 * Created by michael on 26/09/2017.
 */

public class SimpleWhistAndroidText extends SimpleWhistTextBasedUI {
    private ScrollView scrollBox;
    private TextView messageBox;
    private CardTextAdapter cardTextAdapter;
    private Activity activity;

    private Card chosenCard;

    public SimpleWhistAndroidText(
            ScrollView scrollBox, TextView messageBox,
            CardTextAdapter cardTextAdapter, Activity activity
    ) {
        this.scrollBox = scrollBox;
        this.messageBox = messageBox;
        this.cardTextAdapter = cardTextAdapter;
        this.activity = activity;
        this.chosenCard = null;
    }

    @Override
    public void log(final String message) {
        messageBox.post( new Runnable() {
            public void run() {
                messageBox.append("\n");
                messageBox.append(message);
            }
        });

        scrollBox.postDelayed( new Runnable() {
            public void run() {
                scrollBox.fullScroll(View.FOCUS_DOWN);
            }
        }, 100);
    }

    @Override
    public Card getPlayerCard(final List<Card> playerCards) {

        activity.runOnUiThread( new Runnable() {
            public void run() {
                cardTextAdapter.addAll(playerCards);
            }
        });

        this.chosenCard = null;

        while (chosenCard == null) {
            sleep(100);
        }

        activity.runOnUiThread( new Runnable() {
            public void run() {
                cardTextAdapter.clear();
            }
        });

        Card playerCard = chosenCard;
        log(String.format(
                "You play: %s OF %s.",
                playerCard.getRank().name(), playerCard.getSuit().name()
        ));
        return playerCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }
}
