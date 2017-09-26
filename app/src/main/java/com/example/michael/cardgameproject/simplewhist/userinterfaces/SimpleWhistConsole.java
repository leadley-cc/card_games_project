package com.example.michael.cardgameproject.simplewhist.userinterfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by michael on 25/09/2017.
 */

public class SimpleWhistConsole extends SimpleWhistTextBasedUI {
    @Override
    public void log(String message) {
        System.out.println(message);
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
}
