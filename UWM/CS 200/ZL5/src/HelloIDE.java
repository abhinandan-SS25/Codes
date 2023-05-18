///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           First IDE Program
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
 * This program prints a greeting for a name entered or a default name
 * specified in config.java file
 *
 * @author Abhinandan Saha
 */
public class HelloIDE {

    /**
     * This program prompts for the name of user. It prints out
     * "Hello, (name entered)!" if valid name provided or
     * "Hello, (default name in config.java)!".
     * A string longer than 0 character and non-empty is considered valid name.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("What is your name?");


        if (scnr.hasNextLine()) {
            String name = scnr.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Hello, " + Config.DEFAULT_NAME + "!");
            } else {
                System.out.println("Hello, " + name + "!");
            }
        } else {
            System.out.println("Hello, " + Config.DEFAULT_NAME + "!");
        }
    }
}