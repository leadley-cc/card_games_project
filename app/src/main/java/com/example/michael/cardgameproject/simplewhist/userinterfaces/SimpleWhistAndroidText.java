package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.michael.cardgameproject.playingcards.Card;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static android.os.SystemClock.sleep;

/**
 * Created by michael on 26/09/2017.
 */

public class SimpleWhistAndroidText extends SimpleWhistTextBasedUI {
    private ScrollView scrollBox;
    private TextView messageBox;
    private CardTextAdapter cardTextAdapter;
    private Activity activity;
    private Button nextTurnButton;

    private Card chosenCard;

    private Lock lock;
    private Condition cardChosen;
    private Condition nextTurn;

    public SimpleWhistAndroidText(
            ScrollView scrollBox, TextView messageBox,
            CardTextAdapter cardTextAdapter, Button nextTurnButton, Activity activity,
            Lock lock, Condition cardChosen, Condition nextTurn
    ) {
        this.scrollBox = scrollBox;
        this.messageBox = messageBox;
        this.cardTextAdapter = cardTextAdapter;
        this.nextTurnButton = nextTurnButton;
        this.activity = activity;

        this.chosenCard = null;

        this.lock = lock;
        this.cardChosen = cardChosen;
        this.nextTurn = nextTurn;
    }

    @Override
    public void log(final String message) {
        messageBox.post( new Runnable() {
            public void run() {
                messageBox.append(message);
                messageBox.append("\n");
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

        lock.lock();
        try {
            while (chosenCard == null) {
                cardChosen.awaitUninterruptibly();
            }
        } finally {
            lock.unlock();
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

    @Override
    public void clearText() {
        messageBox.post( new Runnable() {
            public void run() {
                messageBox.setText("");
            }
        });
    }

    @Override
    public void prepareForNextTurn() {
        nextTurnButton.post(new Runnable() {
            public void run() {
                nextTurnButton.setVisibility(View.VISIBLE);
            }
        });

        lock.lock();
        try {
            nextTurn.awaitUninterruptibly();
        } finally {
            lock.unlock();
        }

        clearText();

        toggleNextTurn();
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public void toggleNextTurn() {}
}
