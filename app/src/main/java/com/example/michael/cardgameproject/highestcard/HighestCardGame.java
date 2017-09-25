package com.example.michael.cardgameproject.highestcard;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.Deck;

import static com.example.michael.cardgameproject.GameWinner.DEALER;
import static com.example.michael.cardgameproject.GameWinner.PLAYER;

/**
 * Created by michael on 22/09/2017.
 */

public class HighestCardGame {
    private Deck deck;
    private Card dealerCard;
    private Card playerCard;

    public HighestCardGame(Deck deck) {
        this.deck = deck;
    }

    public GameWinner playGame() {
        dealCards();
        return determineWinner();
    }

    void dealCards() {
        dealerCard = deck.deal();
        playerCard = deck.deal();
    }

    Card getDealerCard() {
        return dealerCard;
    }

    Card getPlayerCard() {
        return playerCard;
    }

    int cardValue(Card card) {
        int cardRankValue = card.getRank().ordinal();
        // We decide that aces are high in this game
        if (cardRankValue == 0) cardRankValue = 13;
        int cardSuitValue = card.getSuit().ordinal();
        return (4 * cardRankValue) + cardSuitValue;
    }

    private GameWinner determineWinner() {
        if (cardValue(playerCard) > cardValue(dealerCard)) return PLAYER;
        return DEALER;
    }
}
