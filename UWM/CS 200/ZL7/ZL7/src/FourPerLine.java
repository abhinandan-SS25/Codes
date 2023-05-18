///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Printing four elements per line
// Course:          CS 200, Fall, 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help received or given.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 * This program takes 12 values and prints out 4 of them on each line
 *
 * @author (Abhinandan Saha)
 */
public class FourPerLine {
    /**
     * Program prompts user for 12 integers and using for loop prints out 4
     * values in each line
     * @param args (Unused)
     * @return none
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        final int NUM_VALUES = 12;
        int[] yearlyValues = new int[NUM_VALUES];
        int i;
        int userNum;

        // Get array values
        for (i = 0; i < yearlyValues.length; ++i) {
            yearlyValues[i] = scnr.nextInt();
        }

        for (i = 0; i < yearlyValues.length; i++) {
            System.out.print("" + yearlyValues[i]);

            if ((i + 1) % 4 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
}
