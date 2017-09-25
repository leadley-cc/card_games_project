package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 25/09/2017.
 */

class SimpleWhistAI {
    private List<Card> hand;

    SimpleWhistAI(List<Card> hand) {
        this.hand = hand;
    }

    Card chooseCardFirst() {
        // Pretty lazy, but for now just play our highest card
        return highestCard(hand);
    }

    Card chooseCardSecond(Card playerCard, CardSuit trumps) {
        // If we only have one card, play it
        if (hand.size() == 1) return hand.get(0);

        // Find all the cards we have in the same suit
        CardSuit currentSuit = playerCard.getSuit();
        List<Card> cardsInSuit = new ArrayList<>();
        for (Card card : hand) {
            if (card.getSuit() == currentSuit) cardsInSuit.add(card);
        }

        // If we don't have any cards in the same suit we want to trump
        if (cardsInSuit.size() == 0) {
            List<Card> cardsInTrumps = new ArrayList<>();
            for (Card card : hand) {
                if (card.getSuit() == trumps) cardsInTrumps.add(card);
            }
            Card lowestTrump = lowestCard(cardsInTrumps);

            // If we don't have any trumps either, throw away our lowest card
            if (lowestTrump == null) return lowestCard(hand);

            // Otherwise, return our lowest trump to win with
            return lowestTrump;
        }

        // Try to find the lowest card we have that will win
        Card lowestCardToWin = null;
        int lowestCardToWinValue = Integer.MAX_VALUE;
        for (Card card : cardsInSuit) {
            if ( cardValue(card) > cardValue(playerCard)
                    && cardValue(card) < lowestCardToWinValue ) {
                lowestCardToWin = card;
                lowestCardToWinValue = cardValue(card);
            }
        }

        // If we didn't find any winner, play the lowest card we have in the suit
        if (lowestCardToWin == null) return lowestCard(cardsInSuit);

        // Otherwise return our winner
        return lowestCardToWin;
    }

    private Card highestCard(List<Card> cards) {
        Card highestCard = null;
        int highestCardValue = Integer.MIN_VALUE;
        for (Card card : cards) {
            if (cardValue(card) > highestCardValue) {
                highestCard = card;
                highestCardValue = cardValue(card);
            }
        }
        return highestCard;
    }

    private Card lowestCard(List<Card> cards) {
        Card lowestCard = null;
        int lowestCardValue = Integer.MAX_VALUE;
        for (Card card : cards) {
            if (cardValue(card) < lowestCardValue) {
                lowestCard = card;
                lowestCardValue = cardValue(card);
            }
        }
        return lowestCard;
    }

    private int cardValue(Card card) {
        switch (card.getRank()) {
            case ACE:
                return 13;
            default:
                return card.getRank().ordinal();
        }
    }
}
