package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import com.example.michael.cardgameproject.GameWinner;
import com.example.michael.cardgameproject.playingcards.Card;
import com.example.michael.cardgameproject.playingcards.CardSuit;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistConsole implements SimpleWhistUI {
    @Override
    public void showPlayerHand(List<Card> playerHand) {
        System.out.println("You have been dealt:");
        for (Card card : playerHand) {
            String message = String.format(
                    "%s OF %s", card.getRank().name(), card.getSuit().name()
            );
            System.out.println(message);
        }
        System.out.println();
    }

    @Override
    public void showTrumps(CardSuit trumps) {
        System.out.println( String.format(
                "Trumps is %s", trumps.name()
        ));
    }

    @Override
    public Card getPlayerCard(List<Card> playerCards) {
        System.out.println("Available cards:");
        int counter = 1;

        for (Card card : playerCards) {
            String message = String.format(
                    "(%d) %s OF %s", counter, card.getRank().name(), card.getSuit().name()
            );
            System.out.println(message);
            counter++;
        }

        Scanner in = new Scanner(System.in);
        int index = 0;

        while (index <= 0 || index > playerCards.size()) {
            try {
                index = in.nextInt();
                if (index <= 0 || index > playerCards.size()) {
                    System.out.println("Number out of range!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You didn't give me a number!");
            }
        }

        Card playerCard = playerCards.get(index - 1);
        System.out.println(String.format(
                "You play: %s OF %s.",
                playerCard.getRank().name(), playerCard.getSuit().name()
        ));
        return playerCard;
    }

    @Override
    public void showDealerCard(Card dealerCard) {
        System.out.println( String.format(
                "The dealer plays: %s OF %s.",
                dealerCard.getRank().name(), dealerCard.getSuit().name()
        ));
    }

    @Override
    public void showTrickWinner(GameWinner trickWinner) {
        switch (trickWinner) {
            case PLAYER:
                System.out.println("You win this trick."); break;
            default:
                System.out.println("The dealer wins this trick.");
        }
    }

    @Override
    public void showScores(int dealerTricks, int playerTricks) {
        System.out.println( String.format(
                "The dealer has %d tricks and you have %d tricks.",
                dealerTricks, playerTricks
        ));
    }

    @Override
    public void showGameWinner(GameWinner gameWinner) {
        switch (gameWinner) {
            case PLAYER:
                System.out.println("Congratulations, you are the winner!"); break;
            default:
                System.out.println("Better luck next time!");
        }
    }
}
