///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Calculation of min, max and average
// Course:          CS 200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or recieved
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 * 
 */
public class MinMaxAvg {

    /**
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        final int NUM_VALUES = 10;
        int[] userValues = new int[NUM_VALUES];
        int i;


        for (i = 0; i < userValues.length; ++i) {
            userValues[i] = scnr.nextInt();
        }

        int max = userValues[0];
        int min = userValues[0];
        for (int j=1; j< userValues.length; j++) {
            if (max < userValues[j]) {
                max = userValues[j];
            }
            if (min > userValues[j]) {
                min = userValues[j];
            }
        }

        double avg = 0.0;
        for (int x = 0; x< userValues.length; x++) {
            if ((userValues[x] == min) || (userValues[x] == max)) {
                continue;
            }
            else {
                avg = avg + userValues[x];
            }
        }

        System.out.println("" + min + " " + max + " " + avg/ (userValues.length-2));
      
   }
}
