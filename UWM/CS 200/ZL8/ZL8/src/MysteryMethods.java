///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Understanding and Refactoring different Methods and their
//                  working
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

/**
 * This class contains some working mystery methods and a testing method demonstrating how to use
 * them.
 * The objective is to style (format, name and comment) these methods according to the assignment
 * and style guide.
 *
 * @author Jim Williams
 * @author Abhinandan Saha
 */
public class MysteryMethods {

    /**
     * This main method simply calls the testing method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testMysteryMethods();
    }

    /**
     * Method generates an integer array of desired length populated with random numbers between a
     * specified upper and lower bound.
     *
     * @param randGen    (Random) The random object passed for random number generation
     * @param listLength (int) Specifies the length of the integer array to be generated
     * @param lowerBound (int) Specifies the lower bound for generated random numbers
     * @param upperBound (int) Specifies the upper bound of the generated random numbers
     * @return The newly created integer array is returned
     */
    public static int[] generateRandomArray(Random randGen, int listLength, int lowerBound,
                                            int upperBound) {
        int[] randomList = new int[listLength];
        for (int i = 0; i < listLength; i++) {
            randomList[i] = randGen.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }
        return randomList;
    }

    /**
     * Method is used to take in user input and returns the input. It ensures
     * a string is returned by discarding inputs of other types and prompting
     * user for correct input type till one is provided.
     *
     * @param input (Scanner) The Scanner object passed for taking user Input
     * @return The input is returned as string
     */
    public static String takeInput(Scanner input) {
        String comparisonStr = null;
        if (input != null) {
            while (input.hasNextDouble()) {  //also true for ints
                input.nextDouble();
            }
            if (input.hasNext()) {
                comparisonStr = input.next();
            }
        }
        return comparisonStr;
    }

    /**
     * Method takes a string whose letters are to be counted and counts
     * the number of occurrences of each letter of English in the string as an
     * integer in an integer array. It stores the number of occurrences of each
     * letter according to their positin in the English alphabet.
     *
     * @param str (String) The string whose letters are to be counted
     * @return The integer array containing the count of each letter in
     * provided string
     */
    public static int[] countLetters(String str) {
        final int NUM_LETTERS = 26;
        int[] letterCount = new int[NUM_LETTERS];
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            int position = str.charAt(i) - 'a';
            letterCount[position] += 1;
        }
        return letterCount;
    }

    /**
     * Method takes a string and checks if it is a palindrome by reversing it
     * and equating with original word. It returns tru if word is a palindrome,
     * else false.
     *
     * @param originalString (String) The string which is to be checked
     * @return true or false if word passed is a palindrome
     * provided string
     */
    public static boolean checkPalindrome(String originalString) {
        String reverseString = "";
        for (int i = originalString.length() - 1; i >= 0; i--) {
            char letter = originalString.charAt(i);
            reverseString = reverseString + letter;
        }
        return reverseString.equals(originalString);
    }

    /**
     * This contains a collection of testing methods for the methods in this class.
     */
    public static void testMysteryMethods() {
        boolean error = false;

        {
            final int SEED = 12463;
            Random rand = new Random(SEED);

            int[] expected = {10, 12, 11, 14};
            int[] actual = generateRandomArray(rand, 4, 10, 15);
            if (!Arrays.equals(expected, actual)) {
                error = true;
                System.out.println("1) expected: " + Arrays.toString(expected) + ", actual: "
                        + Arrays.toString(actual));
            }
        }

        {
            Scanner scnr = new Scanner(" 19.26 acorn 29 found 42");
            String expected = "acorn";
            String actual = takeInput(scnr);
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("2a) expected: " + expected + ", actual: " + actual);
            }

            expected = "found";
            actual = takeInput(scnr);
            if (!actual.equals(expected)) {
                error = true;
                System.out.println("2b) expected: " + expected + ", actual: " + actual);
            }

            expected = null;
            actual = takeInput(scnr);
            if (actual != expected) {
                error = true;
                System.out.println("2c) expected: " + expected + ", actual: " + actual);
            }
        }

        {
            int[] expected = {4, 3, 2, 1, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 1};
            int[] actual = countLetters("aaaabbbccdeeeeeggz");
            if (!Arrays.equals(actual, expected)) {
                error = true;
                System.out.println("3a) expected: " + Arrays.toString(expected) + ", actual: "
                        + Arrays.toString(actual));
            }

            expected = new int[]{3, 0, 3, 1, 2, 1, 1, 0, 7, 0, 0, 3, 0, 0, 2, 2, 0, 2,
                    3, 1, 2, 0, 0, 1, 0, 0};
            actual = countLetters("supercalifragilisticexpialidocious");
            if (!Arrays.equals(actual, expected)) {
                error = true;
                System.out.println("3b) expected: " + Arrays.toString(expected) + ", actual: "
                        + Arrays.toString(actual));
            }
        }

        {
            boolean expected = true;
            boolean actual = checkPalindrome("radar");
            if (actual != expected) {
                error = true;
                System.out.println("3a) expected: " + expected + ", actual: " + actual);
            }

            expected = false;
            actual = checkPalindrome("story");
            if (actual != expected) {
                error = true;
                System.out.println("3b) expected: " + expected + ", actual: " + actual);
            }
        }

        if (error) {
            System.out.println("testMysteryMethods failed");
        } else {
            System.out.println("testMysteryMethods passed");
        }
    }
}
