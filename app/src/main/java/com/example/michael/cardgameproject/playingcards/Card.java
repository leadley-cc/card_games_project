package com.example.michael.cardgameproject.playingcards;

/**
 * Created by michael on 22/09/2017.
 */

public class Card {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
