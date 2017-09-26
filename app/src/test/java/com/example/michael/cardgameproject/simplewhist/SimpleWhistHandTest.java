package com.example.michael.cardgameproject.simplewhist;

import com.example.michael.cardgameproject.playingcards.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michael on 26/09/2017.
 */
public class SimpleWhistHandTest {
    SimpleWhistHand hand;
    Card nineOfClubs;
    Card jackOfClubs;
    Card kingOfSpades;

    @Before
    public void before() {
        hand = new SimpleWhistHand();
        nineOfClubs = new Card(CardRank.NINE, CardSuit.CLUBS);
        jackOfClubs = new Card(CardRank.JACK, CardSuit.CLUBS);
        kingOfSpades = new Card(CardRank.KING, CardSuit.SPADES);
    }

    @Test
    public void canGetEmptyHand() {
        assertEquals(0, hand.getHand().size());
    }

    @Test
    public void canGetEmptySuit() {
        assertEquals(0, hand.getSuit(CardSuit.HEARTS).size());
    }

    @Test
    public void takeHighestReturnsNull() {
        assertNull(hand.takeHighest());
    }

    @Test
    public void takeLowestReturnsNull() {
        assertNull(hand.takeLowest());
    }

    @Test
    public void takeLowestDiamondReturnsNull() {
        assertNull(hand.takeLowestInSuit(CardSuit.DIAMONDS));
    }

    @Test
    public void canAddCardToHand() {
        hand.add(kingOfSpades);

        assertEquals(1, hand.getHand().size());
        assertEquals(1, hand.getSuit(CardSuit.SPADES).size());
    }

    @Test
    public void canAddManyCardsToHand() {
        hand.add(nineOfClubs);
        hand.add(jackOfClubs);
        hand.add(kingOfSpades);

        assertEquals(3, hand.getHand().size());
        assertEquals(2, hand.getSuit(CardSuit.CLUBS).size());
        assertEquals(1, hand.getSuit(CardSuit.SPADES).size());
    }

    @Test
    public void takeHighestReturnsKing() {
        hand.add(nineOfClubs);
        hand.add(jackOfClubs);
        hand.add(kingOfSpades);

        assertEquals(kingOfSpades, hand.takeHighest());
    }

    @Test
    public void takeLowestReturnsNine() {
        hand.add(nineOfClubs);
        hand.add(jackOfClubs);
        hand.add(kingOfSpades);

        assertEquals(nineOfClubs, hand.takeLowest());
    }

    @Test
    public void takeLowestFromSpadesReturnsKing() {
        hand.add(nineOfClubs);
        hand.add(jackOfClubs);
        hand.add(kingOfSpades);

        assertEquals(kingOfSpades, hand.takeLowestInSuit(CardSuit.SPADES));
    }

    @Test
    public void canRemoveByCard() {
        hand.add(nineOfClubs);
        hand.add(jackOfClubs);
        hand.add(kingOfSpades);

        hand.remove(nineOfClubs);

        assertEquals(2, hand.getHand().size());
        assertEquals(1, hand.getSuit(CardSuit.CLUBS).size());
        assertEquals(1, hand.getSuit(CardSuit.SPADES).size());
    }

    @Test
    public void canAddSevenCards() {
        hand.add( new Card(CardRank.TWO, CardSuit.DIAMONDS));
        hand.add( new Card(CardRank.FIVE, CardSuit.CLUBS));
        hand.add( new Card(CardRank.SEVEN, CardSuit.SPADES));
        hand.add( new Card(CardRank.NINE, CardSuit.CLUBS));
        hand.add( new Card(CardRank.JACK, CardSuit.CLUBS));
        hand.add( new Card(CardRank.KING, CardSuit.SPADES));
        hand.add( new Card(CardRank.ACE, CardSuit.DIAMONDS));

        assertEquals(7, hand.getHand().size());
        assertEquals(3, hand.getSuit(CardSuit.CLUBS).size());
        assertEquals(2, hand.getSuit(CardSuit.DIAMONDS).size());
        assertEquals(0, hand.getSuit(CardSuit.HEARTS).size());
        assertEquals(2, hand.getSuit(CardSuit.SPADES).size());
    }
}