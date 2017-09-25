package com.example.michael.cardgameproject.highestcard;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardRank;
import com.example.michael.cardgameproject.playingcards.CardSuit;
import com.example.michael.cardgameproject.playingcards.Deck;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by michael on 25/09/2017.
 */
public class HighestCardGameTest {
    private Deck spyDeck;
    private HighestCardGame game;

    private Card card1;
    private Card card2;

    @Before
    public void before() {
        spyDeck = Mockito.spy(new Deck());
        game = new HighestCardGame(spyDeck);

        card1 = new Card(CardRank.JACK, CardSuit.HEARTS);
        card2 = new Card(CardRank.ACE,  CardSuit.SPADES);

        Mockito.when( spyDeck.deal() ).thenReturn( card1 ).thenReturn( card2 );
    }

    @Test
    public void dealerCardStartsNull() {
        assertNull(game.getDealerCard());
    }

    @Test
    public void playerCardStartsNull() {
        assertNull(game.getPlayerCard());
    }

    @Test
    public void dealCardsSetsDealerCard() {
        game.dealCards();
        assertNotNull(game.getDealerCard());
        assertEquals(card1, game.getDealerCard());
    }

    @Test
    public void dealCardsSetsPlayerCard() {
        game.dealCards();
        assertNotNull(game.getPlayerCard());
        assertEquals(card2, game.getPlayerCard());
    }

    @Test
    public void card1ValueIsCorrect() {
        assertEquals(42, game.cardValue(card1));
    }

    @Test
    public void card2ValueIsCorrect() {
        assertEquals(55, game.cardValue(card2));
    }

    @Test
    public void playerIsDeterminedWinner() {
        assertEquals(GameWinner.PLAYER, game.playGame());
    }

}