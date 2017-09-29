package com.example.michael.cardgameproject.twentyone;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Deck;
import com.example.michael.cardgameproject.twentyone.userinterfaces.TwentyOneUI;

import java.util.*;

/**
 * Created by michael on 22/09/2017.
 */

public class TwentyOneGame {

    private static final int PLAYER_TURN = 0;
    private static final int DEALER_TURN = 1;

    private Deck deck;
    private TwentyOneHand dealerHand;
    private TwentyOneHand playerHand;
    private TwentyOneUI gameUI;
    private int currentTurn;

    public TwentyOneGame(Deck deck, TwentyOneUI gameUI) {
        this.deck = deck;
        this.gameUI = gameUI;
        this.currentTurn = PLAYER_TURN;
        dealCards();
    }

    private void dealCards() {
        dealerHand = new TwentyOneHand(deck.deal(), deck.deal());
        playerHand = new TwentyOneHand(deck.deal(), deck.deal());
    }

    public void playGame() {
        gameUI.prepareUI();
        takePlayerTurn();
        takeDealerTurn();
        gameUI.showWinner(getWinner());
    }

    private void takePlayerTurn() {

    }

    private void takeDealerTurn() {
        while (dealerHand.getCurrentValue() < 17) {
            dealerHand.addCard(deck.deal());
        }
        currentTurn++;
    }

    private GameWinner getWinner() {
        // TODO: Game winning logic
        return null;
    }
}
