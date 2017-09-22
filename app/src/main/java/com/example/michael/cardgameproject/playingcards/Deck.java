package com.example.michael.cardgameproject.playingcards;

import java.util.*;

/**
 * Created by michael on 22/09/2017.
 */

public class Deck {
    private LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        generateDeck();
        shuffle();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void generateDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.poll();
    }
}
