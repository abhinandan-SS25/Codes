///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Guess the Number Game
// Course:          CS 200 Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Random;
import java.util.Scanner;

/**
 * This program prompts user to enter an integer and try to guess a random
 * integer randomly selected at the start of the program, till user correctly
 * guesses the number. The program prints out hints depending on the input.
 *
 * @author (Abhinandan Saha)
 */
public class GuessTheNumber {

    /**
     * This is a guess the number game that uses the method above for user input.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        final int LOW_BOUND = 1;
        final int HIGH_BOUND = 100;
        int hiddenNum = rand.nextInt(HIGH_BOUND - LOW_BOUND + 1) + LOW_BOUND;
        int guessCount = 0;
        int guessedNum;

        do {
            guessedNum = checkNumValidity(input,
                    "Enter a number between " + LOW_BOUND + " and " +
                            HIGH_BOUND + ": ",
                    LOW_BOUND, HIGH_BOUND);
            guessCount++;
            if (guessedNum >= LOW_BOUND && guessedNum <= HIGH_BOUND) {
                if (guessedNum < hiddenNum) {
                    System.out.println("This guess is too low.");
                } else if (guessedNum > hiddenNum) {
                    System.out.println("This guess is too high.");
                } else {
                    System.out.println("This guess is correct.");
                }
            } else {
                System.out.println("This guess is invalid.");
            }
        } while (guessedNum != hiddenNum);

        System.out.print("You won in " + guessCount + " attempts!");
        input.close();
    }

    /**
     * This method takes in the min, max, message for prompt and Scanner object to take
     * in the user input. It checks if the passed input is in range of possible values.
     *
     * @param input        (scanner the Scanner object to take in user input)
     * @param entryMessage (String the message that prompts the user to input
     *                     their guess)
     * @param min          (int the lower bound between which the random number is)
     * @param max          (int the upper bound between which the random number is)
     * @return (int         - 1 if the Scanner object is null
     *                      - 2 if the lower bound provided less than 0
     *                      - 3 if the lower bound provided greater than upper bound
     *                      - 4 if the guess is greater than upper bound
     *                      - 5 if the guess is lower than the lower bound
     *                      - 6 if the guess is not an int
     *the guess entered if the input passes all checks
     *current value)
     */
    public static int checkNumValidity(Scanner input, String entryMessage, int min, int max) {
        if (input == null) {
            return -1;
        } else if (min < 0) {
            return -2;
        } else if (min > max) {
            return -3;
        }
        int numToCheck = -6;
        System.out.println(entryMessage);
        if (input.hasNextInt()) {
            numToCheck = input.nextInt();
            input.nextLine(); //read and ignore the rest of the line
            if (numToCheck > max) {
                return -4;
            } else if (numToCheck < min) {
                return -5;
            }
        } else {
            input.nextLine(); //read and ignore the token that isn't an integer
        }
        return numToCheck;
    }
}
