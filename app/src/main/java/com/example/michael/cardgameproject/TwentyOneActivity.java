package com.example.michael.cardgameproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.michael.cardgameproject.playingcards.Deck;
import com.example.michael.cardgameproject.twentyone.TwentyOneGame;
import com.example.michael.cardgameproject.twentyone.userinterfaces.TwentyOneAndroidText;

public class TwentyOneActivity extends AppCompatActivity {

    Deck deck;
    TwentyOneAndroidText gameUI;
    TwentyOneGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_one);

        deck = new Deck();
        gameUI = new TwentyOneAndroidText(

        );
        game = new TwentyOneGame(deck, gameUI);

        new Thread( new Runnable() {
            public void run() {
                game.playGame();
            }
        }).start();
    }
}
