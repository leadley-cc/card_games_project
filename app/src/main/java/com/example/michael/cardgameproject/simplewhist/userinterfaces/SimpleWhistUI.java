package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.List;

/**
 * Created by michael on 25/09/2017.
 */

public interface SimpleWhistUI {
    void showPlayerHand(List<Card> playerHand);

    void showTrumps(CardSuit trumps);

    Card getPlayerCard(List<Card> playerHand);

    void showDealerCard(Card dealerCard);

    void showTrickWinner(GameWinner trickWinner);

    void showScores(int dealerTricks, int playerTricks);

    void showGameWinner(GameWinner gameWinner);
}
