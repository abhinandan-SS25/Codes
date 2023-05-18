///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           User Age Calculation
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
 * This program  given a person's birthdate and current date, returns the
 * person's age in years.
 *
 * @author Abhinandan Saha
 */
public class UserAge {

    /**
     * This program prompts for the birth month, birthday, birth year, current
     * month, current day and current year, calculates the difference between
     * the two dates and returns it as the person's age. The age is rounded down
     * as required.
     *
     * @param args unused
     */
    public static void main (String[] args) {
        int birthMonth;
        int birthDay;
        int birthYear;
        int currentMonth;
        int currentDay;
        int currentYear;

        int currYear;

        Scanner scnr = new Scanner(System.in);

        birthMonth = scnr.nextInt();
        birthDay = scnr.nextInt();
        birthYear = scnr.nextInt();

        currentMonth = scnr.nextInt();
        currentDay = scnr.nextInt();
        currentYear = scnr.nextInt();

        currYear = currentYear - birthYear;

        if (birthMonth > currentMonth) {
            currYear -= 1;
        }
        if (birthDay > currentDay) {
            currYear -= 1;
        }
        if (currYear<0) {
            currYear = 0;
        }

        System.out.println(currYear);

    }
}
