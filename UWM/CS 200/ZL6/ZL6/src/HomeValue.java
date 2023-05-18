///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Calculates the years taken to double house value
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

import java.util.Scanner;

/**
 * This program prompts user to enter current value of a house and
 * calculates the no. of years required to double house value.
 *
 * @author (Abhinandan Saha)
 */
public class HomeValue {

    /**
     * This method prompts user to enter the current home value and appreciation
     * value. It prints out error message if either values are not of type double.
     * It calls yearsToDouble function and prints out time required to double the
     * current house value.
     *
     * @param args (Unused)
     * @return (None)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter home value:");

        if (input.hasNextDouble()) {
            double housePrice = input.nextDouble();
            System.out.print("Enter appreciation rate (3.9% enter as 0.039):");

            if (input.hasNextDouble()) {
                double appreciationRate = input.nextDouble();

                System.out.print("It will take about "
                        + yearsToDouble(housePrice, appreciationRate)
                        + " years to double in value.");
            } else {
                System.out.print("Unexpected value:unknown");
            }
        } else {
            System.out.print("Unexpected value:unknown");
        }

    }

    /**
     * This method takes in the current house value and appreciation
     * rate and calculates the no. of years required for house value
     * to double
     *
     * @param currentValue (double storing the current house value)
     * @param appRate      (double storing the appreciation rate)
     * @return (int years containing the years required to double the
     *current value)
     */
    public static int yearsToDouble(double currentValue, double appRate) {
        int years = 0;
        double targetValue = currentValue * 2;
        do {
            currentValue = currentValue + (currentValue * appRate);
            years = years + 1;
        } while (currentValue <= targetValue);

        return years;
    }

}