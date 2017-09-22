package com.example.michael.cardgameproject.playingcards;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by michael on 22/09/2017.
 */
public class DeckTest {
    Deck deck;

    @Before
    public void before() {
        deck = new Deck();
    }

    @Test
    public void canGetCards() {
        assertNotNull(deck.getCards());
    }

    @Test
    public void deckStartsFull() {
        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void canShuffle() {
        List<Card> shallowCopy = new ArrayList<>(deck.getCards());
        deck.shuffle();
        assertNotEquals(shallowCopy, deck.getCards());
    }

    @Test
    public void canDeal() {
        assertNotNull(deck.deal());
    }

}