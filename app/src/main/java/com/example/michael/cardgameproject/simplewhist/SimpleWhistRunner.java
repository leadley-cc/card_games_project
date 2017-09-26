package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.Deck;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistConsole;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistUI;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistRunner {
    private static Deck deck;
    private static SimpleWhistUI gameUI;
    private static SimpleWhistGame game;

    public static void main(String[] args) {
        deck = new Deck();
        gameUI = new SimpleWhistConsole();
        game = new SimpleWhistGame(deck, gameUI);

        game.playGame();
    }
}
