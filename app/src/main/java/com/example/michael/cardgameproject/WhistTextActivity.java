package com.example.michael.cardgameproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.Deck;
import com.example.michael.cardgameproject.simplewhist.SimpleWhistGame;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.CardTextAdapter;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistAndroidText;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WhistTextActivity extends AppCompatActivity {

    ScrollView scrollBox;
    TextView messageBox;
    ListView cardPicker;

    CardTextAdapter cardTextAdapter;

    Deck deck;
    SimpleWhistAndroidText gameUI;
    SimpleWhistGame game;

    Card chosenCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whist_text);

        scrollBox = (ScrollView) findViewById(R.id.scroll_box);
        messageBox = (TextView) findViewById(R.id.message_box);
        cardPicker = (ListView) findViewById(R.id.card_picker);

        cardTextAdapter = new CardTextAdapter(this, new ArrayList<Card>());
        cardPicker.setAdapter(cardTextAdapter);

        deck = new Deck();
        gameUI = new SimpleWhistAndroidText(
                scrollBox, messageBox, cardTextAdapter, this
        );
        game = new SimpleWhistGame(deck, gameUI);

        messageBox.append("Welcome to Whist!");

        new Thread( new Runnable() {
            public void run() {
                game.playGame();
            }
        }).start();
    }

    public void getCard(View listItem) {
        chosenCard = (Card) listItem.getTag();

        Log.d("getCard called", chosenCard.toString());

        gameUI.setChosenCard(chosenCard);
    }
}
