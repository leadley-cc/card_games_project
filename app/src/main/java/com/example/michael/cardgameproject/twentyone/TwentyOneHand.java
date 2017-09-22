package com.example.michael.cardgameproject.twentyone;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardRank;

import java.util.*;

/**
 * Created by michael on 22/09/2017.
 */

class TwentyOneHand {
    private List<Card> cards;

    public TwentyOneHand() {
        this.cards = new ArrayList<>();
    }

    public TwentyOneHand(Card card1, Card card2) {
        this.cards = new ArrayList<>( Arrays.asList(card1, card2) );
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCurrentValue() {
        int totalValue = 0;
        int aces = 0;
        for (Card card : cards) {
            totalValue += cardValue(card);
            if (card.getRank() == CardRank.ACE) aces++;
        }
        while (totalValue > 21 && aces != 0) {
            totalValue -= 10;
            aces--;
        }
        return totalValue;
    }

    public int cardValue(Card card) {
        switch (card.getRank()) {
            case ACE:
                return 11;
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return card.getRank().ordinal() + 1;
        }
    }
}
