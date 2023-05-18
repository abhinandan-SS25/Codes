///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           IDE Vs ZyLabs
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
 * The behavior of this code is likely different in Eclipse or IntelliJ as
 * compared to zyBooks.
 * Refactor the code so that it works in both zyBooks and Eclipse or IntelliJ.
 *
 * @author mrenault, Yanan, mppowers
 */
public class IDEvsZyBooks {

    /**
     * This program reads in two numbers. If their difference is less than a threshold value
     * the number are considered equal otherwise they are not equal.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        final double SIGNIFICANT_THRESHOLD = 0.1;
        System.out.print("Enter 2 floating point numbers: ");
        Scanner input = new Scanner(System.in);
        double firstNum = readDouble(input);
        double secondNum = readDouble(input);
        System.out.println("The values you entered are: " + firstNum + " and " + secondNum);

        if (areEqual(firstNum, secondNum, SIGNIFICANT_THRESHOLD)) {
            System.out.println("The numbers are considered equal.");
        } else {
            System.out.println("The numbers are not considered equal.");
        }
    }

    /**
     * returns whether num1 and num2 are essentially equal. Equal means that the
     * absolute value of the difference between num1 and num2 is less than epsilon.
     *
     * @param num1    a number to compare
     * @param num2    another number to compare
     * @param epsilon a threshold value, such that differences less than this number
     *                are considered insignificant.
     * @return whether the numbers are considered equal.
     */
    public static boolean areEqual(double num1, double num2, double epsilon) {
        return Math.abs(num1 - num2) < epsilon;
    }

    /**
     * this method reads a double value from the scanner.
     *
     * @param input    Scanner variable to read in next double value
     *
     * @return a double value read from the scanner.
     */
    public static double readDouble(Scanner input) {
        return input.nextDouble();
    }
}