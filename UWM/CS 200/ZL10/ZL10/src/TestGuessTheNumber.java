///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Tester methods for checking output of Guess the Number Game
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

import java.util.Scanner;

/**
 * This contains testing methods for the InputMethods class.
 *
 * @author Jim Williams
 * @author Abhinandan Saha
 */
public class TestGuessTheNumber {

    /**
     * This main method runs the selected tests.  Comment out a test if you don't
     * want it to run.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testcheckNumValidity();
    }

    /**
     * Tests that the checkNumValidity method handles all the cases described in its
     * method header
     * comment.
     */
    private static void testcheckNumValidity() {
        boolean error = false;

        //test 1, a happy path case (valid input) "25\n" with min 2 and max 50 should result in
        // 25 being returned.
        {

            //simulates a user typing 25 then Enter. Do Not use System.in within test cases.
            Scanner input = new Scanner("25\n");

            //the value expected to be returned by the method for this test case.
            int expected = 25;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 2 and 50:",
                    2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }
        }

        {  //test 2, -1 is returned when the scanner is null irrespective of min and max.
            //simulates a null Scanner object.
            Scanner input = null;

            //the value expected to be returned by the method for this test case.
            int expected = -1;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 2 and 50:",
                    2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        { //test 3, -2 is returned when the min is less than 0, irrespective of other values.
            //simulates a user typing 2 then Enter.
            Scanner input = new Scanner("2\n");

            //the value expected to be returned by the method for this test case.
            int expected = -2;

            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between -2 and 50:",
                    -2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        { //test 4, -3 is returned when min is greater than the max irrespective of the input.
            //simulates a user typing 25 then Enter.
            Scanner input = new Scanner("25\n");

            //the value expected to be returned by the method for this test case.
            int expected = -3;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 60 and 50:",
                    60, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        { //test 5, -4 is returned with an input of ("70\n") with 2 and 50 as min and max.
            //simulates a user typing 70 then Enter.
            Scanner input = new Scanner("70\n");

            //the value expected to be returned by the method for this test case.
            int expected = -4;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 2 and 50:",
                    2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        { //test 6, -5 is returned with an input of ("0\n") with 2 and 50 as min and max.
            //simulates a user typing 0 then Enter.
            Scanner input = new Scanner("0\n");

            //the value expected to be returned by the method for this test case.
            int expected = -5;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 2 and 50:",
                    2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        { //test 7, -6 is returned with a String input of ("No Input\n") with 2 and 50 as min
            // and max.
            //simulates a user typing No Input and then Enter.
            Scanner input = new Scanner("No Input\n");

            //the value expected to be returned by the method for this test case.
            int expected = -6;
            int actual = GuessTheNumber.checkNumValidity(input,
                    "Enter a number between 2 and 50:",
                    2, 50);

            //if the actual value returned doesn't equal the expected then this is an error.
            if (actual != expected) {
                error = true;
                System.out.println("testcheckNumValidity 1) Expected " + expected +
                        ", a valid value to " +
                        "be returned instead of " + actual);
            }

        }

        if (error) {
            System.out.println("\ntestcheckNumValidity failed");
        } else {
            System.out.println(
                    "\ntestcheckNumValidity passed; it is expected that the prompt is output"
            );
        }
    }
}
