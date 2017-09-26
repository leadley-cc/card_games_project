package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.Deck;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistConsole;
import com.example.michael.cardgameproject.simplewhist.userinterfaces.SimpleWhistUI;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michael on 25/09/2017.
 */
public class SimpleWhistGameTest {
    SimpleWhistGame game;

    @Test
    public void prepareGivesSevenCardsToEachHand() {
        Deck deck = new Deck();
        SimpleWhistUI ui = new SimpleWhistConsole();
        game = new SimpleWhistGame(deck, ui);

        game.prepare();

        assertEquals(7, game.playerHand.size());
        assertEquals(7, game.dealerHand.size());
    }
}