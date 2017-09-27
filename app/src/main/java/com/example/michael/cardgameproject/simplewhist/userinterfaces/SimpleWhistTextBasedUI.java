package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.List;

/**
 * Created by michael on 26/09/2017.
 */

abstract class SimpleWhistTextBasedUI implements SimpleWhistUI {
    public abstract void log(String message);

    public abstract Card getPlayerCard(List<Card> playerCards);

    public abstract void clearText();

    @Override
    public void showPlayerHand(List<Card> playerHand) {
        log("You have been dealt:");
        for (Card card : playerHand) {
            String message = String.format(
                    "%s OF %s", card.getRank().name(), card.getSuit().name()
            );
            log(message);
        }
        log("");
    }

    @Override
    public void showTrumps(CardSuit trumps) {
        log( String.format(
                "Trumps is %s", trumps.name()
        ));
    }

    @Override
    public void showDealerCard(Card dealerCard) {
        log( String.format(
                "The dealer plays: %s OF %s.",
                dealerCard.getRank().name(), dealerCard.getSuit().name()
        ));
    }

    @Override
    public void showTrickWinner(GameWinner trickWinner) {
        switch (trickWinner) {
            case PLAYER:
                log("You win this trick."); break;
            default:
                log("The dealer wins this trick.");
        }
    }

    @Override
    public void showScores(int dealerTricks, int playerTricks) {
        log( String.format(
                "The dealer has %d tricks and you have %d tricks.",
                dealerTricks, playerTricks
        ));
    }

    @Override
    public void showGameWinner(GameWinner gameWinner) {
        switch (gameWinner) {
            case PLAYER:
                log("Congratulations, you are the winner!"); break;
            default:
                log("Better luck next time!");
        }
    }

    @Override
    public void prepareForNextTurn() {
        clearText();
    }
}
