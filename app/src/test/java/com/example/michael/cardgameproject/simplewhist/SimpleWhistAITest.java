package com.example.michael.cardgameproject.simplewhist;

import org.junit.Before;
import org.junit.Test;

import com.example.michael.cardgameproject.playingcards.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistAITest {
    private Card twoOfDiamonds;
    private Card fiveOfClubs;
    private Card sevenOfSpades;
    private Card nineOfClubs;
    private Card jackOfClubs;
    private Card kingOfSpades;
    private Card aceOfDiamonds;
    private SimpleWhistHand aiHand;
    private SimpleWhistAI testAI;
    private CardSuit trumps;

    @Before
    public void before() {
        twoOfDiamonds = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        fiveOfClubs = new Card(CardRank.FIVE, CardSuit.CLUBS);
        sevenOfSpades = new Card(CardRank.SEVEN, CardSuit.SPADES);
        nineOfClubs = new Card(CardRank.NINE, CardSuit.CLUBS);
        jackOfClubs = new Card(CardRank.JACK, CardSuit.CLUBS);
        kingOfSpades = new Card(CardRank.KING, CardSuit.SPADES);
        aceOfDiamonds = new Card(CardRank.ACE, CardSuit.DIAMONDS);
        List<Card> aiCards = Arrays.asList(
                twoOfDiamonds, fiveOfClubs, sevenOfSpades,
                nineOfClubs, jackOfClubs, kingOfSpades, aceOfDiamonds
        );
        aiHand = new SimpleWhistHand(aiCards);
        testAI = new SimpleWhistAI(aiHand);
        trumps = CardSuit.SPADES;
    }

    @Test
    public void chooseCardFirst() {
        assertEquals(aceOfDiamonds, testAI.chooseCardFirst());
    }

    @Test
    public void chooseFiveAgainstQueenOfClubs() {
        Card expected = fiveOfClubs;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.QUEEN, CardSuit.CLUBS), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseSevenOfSpadesAgainstTenOfHearts() {
        Card expected = sevenOfSpades;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.TEN, CardSuit.HEARTS), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseNineAgainstSevenOfClubs() {
        Card expected = nineOfClubs;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.SEVEN, CardSuit.CLUBS), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseJackAgainstTenOfClubs() {
        Card expected = jackOfClubs;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.TEN, CardSuit.CLUBS), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseKingAgainstQueenOfSpades() {
        Card expected = kingOfSpades;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.QUEEN, CardSuit.SPADES), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseAceToBeatFiveOfDiamonds() {
        Card expected = aceOfDiamonds;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.FIVE, CardSuit.DIAMONDS), trumps
        );
        assertEquals(expected, result);
    }

    @Test
    public void chooseTwoOfDiamondsAgainstSixOfSpadesWhenNoTrumps() {
        List<Card> aiCards = Arrays.asList( twoOfDiamonds, fiveOfClubs );
        aiHand = new SimpleWhistHand(aiCards);
        testAI = new SimpleWhistAI(aiHand);

        Card expected = twoOfDiamonds;
        Card result = testAI.chooseCardSecond(
                new Card(CardRank.SIX, CardSuit.SPADES), trumps
        );
        assertEquals(expected, result);
    }

}