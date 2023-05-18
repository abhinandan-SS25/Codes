///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Guessing Game
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help received or taken
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 * This program implements logic for a game where the user will try to guess a
 * random number. They will have two tries to guess, and the program will output
 * feedback based on the number entered.
 *
 * @author Abhinandan Saha
 */
public class GuessingGame {
    /**
     * This program preselects a random number between 1 and 5 and
     * prompts for a guess between 1 and 5. In two guesses, if the user succeeds
     * in guessing, the user wins, otherwise loses. Depending on if the real number
     * is higher or lower than the guess, a hint is printed out to aid the user.
     *
     * @param args unused
     */

    public static void main(String[] args) {
        int randNum = 3;
        Scanner input = new Scanner(System.in);

        System.out.println("Guess a number between 1 and 5");

        int guess1 = input.nextInt();

        if (guess1 == randNum) {
            System.out.println("You win!");
        } else {
            if (guess1 < randNum) {
                System.out.println("That's too low\n" + "Guess again!");
            } else if (guess1 > randNum) {
                System.out.println("That's too high\n" + "Guess again!");
            }

            int guess2 = input.nextInt();

            if (guess2 == randNum) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose! It was 3");
            }
        }

    }
}