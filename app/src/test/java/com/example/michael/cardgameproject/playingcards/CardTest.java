package com.example.michael.cardgameproject.playingcards;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michael on 22/09/2017.
 */
public class CardTest {
    Card card;

    @Before
    public void before() {
        card = new Card(CardRank.ACE, CardSuit.SPADES);
    }

    @Test
    public void getRank() {
        assertEquals(CardRank.ACE, card.getRank());
    }

    @Test
    public void getSuit() {
        assertEquals(CardSuit.SPADES, card.getSuit());
    }
}