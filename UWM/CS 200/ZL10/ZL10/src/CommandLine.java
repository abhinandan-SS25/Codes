///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           The World's Worst Calculator
// Course:          CS 200 Spring 2022
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

/**
 * A class that acts as a simple calculator using command line arguments
 *
 * @author (Abhinanadan Saha)
 */
public class CommandLine{
   
   /**
    * Performs calculation based on command line arguments and prints result to stdout
    * Checks for invalid inputs such as dividing by zero or invalid operators
    * 
    * @param args command line arguments consisting of the two operands and the operator
    */ 
    public static void main(String[] args){
        
        if (args.length != 3) {
            System.out.print("Incorrect number of arguments passed");
        }
        else if (!("+-*/".contains(args[1]))) {
            System.out.print("Operator is invalid");
        }
        else if(args[1].equals("/") && args[2].equals("0")) {
            System.out.print("Cannot divide by zero");
        }
        else {
            int result = 0;
            if (args[1].equals("+")) {
                result = Integer.parseInt(args[0]) + Integer.parseInt(args[2]);
            }
            if (args[1].equals("-")) {
                result = Integer.parseInt(args[0]) - Integer.parseInt(args[2]);
            }
            if (args[1].equals("*")) {
                result = Integer.parseInt(args[0]) * Integer.parseInt(args[2]);
            }
            if (args[1].equals("/")) {
                result = Integer.parseInt(args[0]) / Integer.parseInt(args[2]);
            }
            System.out.print("" + args[0] + " " + args[1] + " " + args[2] + " = " + result);
        }
    }
}