package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

/**
 * Created by michael on 25/09/2017.
 */

class SimpleWhistAI {
    private SimpleWhistHand hand;

    SimpleWhistAI(SimpleWhistHand hand) {
        this.hand = hand;
    }

    Card chooseCardFirst() {
        // Pretty lazy, but for now just play our highest card
        return hand.takeHighest();
    }

    Card chooseCardSecond(Card playerCard, CardSuit trumps) {
        // If we only have one card, play it
        if (hand.size() == 1) return hand.takeHighest();

        CardSuit currentSuit = playerCard.getSuit();

        // If we don't have any cards in the same suit we want to trump
        if (hand.suitSize(currentSuit) == 0) {

            // If we don't have any trumps either, throw away our lowest card
            if (hand.suitSize(trumps) == 0) return hand.takeLowest();

            // Otherwise, return our lowest trump to win with
            return hand.takeLowestInSuit(trumps);
        }

        // Try to find the lowest card we have that will win
        Card lowestCardToWin = hand.takeLowestCardToBeat(playerCard);

        // If we didn't find any winner, play the lowest card we have in the suit
        if (lowestCardToWin == null) return hand.takeLowestInSuit(currentSuit);

        // Otherwise return our winner
        return lowestCardToWin;
    }
}
