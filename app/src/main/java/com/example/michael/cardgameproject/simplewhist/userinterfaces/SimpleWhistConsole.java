package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import com.example.michael.cardgameproject.playingcards.Card;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistConsole extends SimpleWhistTextBasedUI {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public Card getPlayerCard(List<Card> playerCards) {
        log("Available cards:");
        int counter = 1;

        for (Card card : playerCards) {
            String message = String.format(
                    "(%d) %s OF %s", counter, card.getRank().name(), card.getSuit().name()
            );
            log(message);
            counter++;
        }

        int index = getIntegerInput(0, playerCards.size());

        Card playerCard = playerCards.get(index - 1);
        log(String.format(
                "You play: %s OF %s.",
                playerCard.getRank().name(), playerCard.getSuit().name()
        ));
        return playerCard;
    }

    public int getIntegerInput(int lowerBound, int upperBound) {
        Scanner in = new Scanner(System.in);
        int input = lowerBound;

        while (input <= lowerBound || input > upperBound) {
            try {
                input = in.nextInt();
                if (input <= lowerBound || input > upperBound) {
                    log("Number out of range!");
                }
            } catch (InputMismatchException e) {
                log("You didn't give me a number!");
            }
        }

        return input;
    }

    @Override
    public void clearText() {
        log("\n");
    }

    @Override
    public void prepareForNextTurn() {

    }
}
