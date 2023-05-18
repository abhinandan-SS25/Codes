///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Prints out an inverted half pine tree
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
 * This program prompts user to enter symbol to make up tree with, trunk width and
 * branch width and prints out an inverted half pine tree.
 *
 * @author (Abhinandan Saha)
 */
public class DrawInvHalfPine {

    /**
     * This method prompts user to enter symbol to make up tree with, trunk width and
     * branch width. Branch width is re-prompted if smaller than trunk width. Tree
     * has three parts - trunk (rectangle), middle (trapezoid) and top(cone), printed
     * using nested loopd.
     *
     * @param args (Unused)
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int pineTrunkWidth = 0;
        int pineBranchWidth = 0;
        String symbol;

        System.out.print("Enter the symbol: ");
        symbol = scnr.next();

        System.out.print("Enter the pine trunk width: ");
        pineTrunkWidth = scnr.nextInt();

        System.out.print("Enter the pine branch width: ");
        pineBranchWidth = scnr.nextInt();
        while (pineBranchWidth <= pineTrunkWidth) {
            System.out.print("Enter the pine branch width: ");
            pineBranchWidth = scnr.nextInt();
        }

        int height = (pineBranchWidth - pineTrunkWidth + 1);

        System.out.println();

        // draw trunk
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < pineTrunkWidth; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }

        // draw middle layer
        for (int i = pineBranchWidth; i >= pineTrunkWidth; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }

        // draw top layer
        for (int i = height; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }

    }
}
