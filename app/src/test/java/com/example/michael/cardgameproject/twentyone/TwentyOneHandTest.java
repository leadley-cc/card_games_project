package com.example.michael.cardgameproject.twentyone;

import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardRank;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michael on 22/09/2017.
 */
public class TwentyOneHandTest {
    Card card1;
    Card card2;
    Card card3;
    TwentyOneHand hand;

    @Before
    public void before() {
        card1 = new Card(CardRank.ACE, CardSuit.HEARTS);
        card2 = new Card(CardRank.NINE, CardSuit.CLUBS);
        card3 = new Card(CardRank.KING, CardSuit.DIAMONDS);
        hand = new TwentyOneHand();
    }

    @Test
    public void getCards() {
        assertNotNull(hand.getCards());
    }

    @Test
    public void addCards() {
        hand = new TwentyOneHand(card1, card2);
        assertEquals(2, hand.getCards().size());
    }

    @Test
    public void currentValueStartsAtZero() {
        assertEquals(0, hand.getCurrentValue());
    }

    @Test
    public void aceNineValueIs20() {
        hand = new TwentyOneHand(card1, card2);
        assertEquals(20, hand.getCurrentValue());
    }

    @Test
    public void aceNineAceValueIs21() {
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card1);
        assertEquals(21, hand.getCurrentValue());
    }

    @Test
    public void kingAceValueIs21() {
        hand = new TwentyOneHand(card3, card1);
        assertEquals(21, hand.getCurrentValue());
    }

    @Test
    public void nineAndManyAcesMakesAllAcesHard() {
        hand.addCard(card1);
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card1);
        hand.addCard(card1);
        hand.addCard(card1);
        assertEquals(14, hand.getCurrentValue());
    }

    @Test
    public void cardValue() {
        assertEquals(11, hand.cardValue(card1));
        assertEquals(9, hand.cardValue(card2));
    }

}