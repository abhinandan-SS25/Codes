//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Tester for Changemaker methods
// Course:   CS 300 Spring 2023
//
// Author:   Abhinandan Saha
// Email:    asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// No pair programming
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help taken or recieved
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class contains tester methods to test the validity of outputs from methods in ChangeMaker
 *
 */
public class ChangemakerTester {

  /**
   * tester methods to check validity and return for base cases of count method
   * 
   * @return true if all tests pass
   *         false if test conditions are not met
   */
    public static boolean testCountBase() {
        //test case 1: (Value is negetive) returns 0
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{3,0,0,3};
            int value = -10;
            int expected = 0;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 2: (No way to make change) returns 0
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 4;
            int expected = 0;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 3: (No way to make change) returns 1
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 0;
            int expected = 1;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * tester methods to check validity and return for recursive cases of count method
     * 
     * @return true if all tests pass
     *         false if test conditions are not met
     */
    public static boolean testCountRecursive() {
        //test case 1: (Count can be made with 3 different coins)
        {
            int[] denominations = new int[]{1,5,10,25};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 36;
            int expected = 6;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 2: (Atleast two different optimal ways to make change)
        {
            int[] denominations = new int[]{7,10,2,5};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 12;
            int expected = 4;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 3: (greedily choosing the largest
        //coin first does not produce a result with the minimum number of coins)
        {
            int[] denominations = new int[]{1,5,6,9};
            int[] coinsRemaining = new int[]{2,1,1,1};
            int value = 11;
            int expected = 5;
            int result = Changemaker.count(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * tester methods to check validity and return for base cases of minCount method
     * 
     * @return true if all tests pass
     *         false if test conditions are not met
     */
    public static boolean testMinCoinsBase() {
        //test case 1: (Value is negetive) returns -1
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{3,0,0,3};
            int value = -10;
            int expected = -1;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 2: (No way to make change) returns -1
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 4;
            int expected = -1;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 3: (Value is 0) returns 0
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 0;
            int expected = 0;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * tester methods to check validity and return for recursive cases of minCount method
     * 
     * @return true if all tests pass
     *         false if test conditions are not met
     */
    public static boolean testMinCoinsRecursive() {
        //test case 1: (At least 3 different coins used)
        {
            int[] denominations = new int[]{1,5,10,25};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 16;
            int expected = 3;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 2: (Atleast two different optimal ways to make change)
        {
            int[] denominations = new int[]{7,10,2,5};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 7;
            int expected = 1;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 3: (greedily choosing the largest
        //coin first does not produce a result with the minimum number of coins)
        {
            int[] denominations = new int[]{1,5,6,9};
            int[] coinsRemaining = new int[]{2,1,1,1};
            int value = 6;
            int expected = 1;
            int result = Changemaker.minCoins(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * tester methods to check validity and return for base cases of makeChange method
     * 
     * @return true if all tests pass
     *         false if test conditions are not met
     */
    public static boolean testMakeChangeBase() {
        //test case 1: (Value is negetive) returns null
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{3,0,0,3};
            int value = -10;
            int[] expected = null;
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 2: (No way to make change) returns null
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 4;
            int[] expected = null;
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (expected != result) {
                return false;
            }
        }
        //test case 3: (Value is 0) returns 0
        {
            int[] denominations = new int[]{25,5,10,1};
            int[] coinsRemaining = new int[]{0,0,0,3};
            int value = 0;
            int[] expected = new int[coinsRemaining.length];
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (!Arrays.equals(expected, result)) {
                return false;
            }
        }
        return true;
    }

    /**
     * tester methods to check validity and return for recursive cases of count method
     * 
     * @return true if all tests pass
     *         false if test conditions are not met
     */
    public static boolean testMakeChangeRecursive() {
        //test case 1: (At least 3 different coins used)
        {
            int[] denominations = new int[]{1,5,10,25};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 16;
            int[] expected = {1,1,1,0};
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (!Arrays.equals(expected, result)) {
                return false;
            }
        }
        //test case 2: (Atleast two different optimal ways to make change)
        {
            int[] denominations = new int[]{7,10,2,5};
            int[] coinsRemaining = new int[]{1,1,1,1};
            int value = 7;
            int[] expected = {1,0,0,0};
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (!Arrays.equals(expected, result)) {
                return false;
            }
        }
        //test case 3: (greedily choosing the largest
        //coin first does not produce a result with the minimum number of coins)
        {
            int[] denominations = new int[]{1,5,6,9};
            int[] coinsRemaining = new int[]{2,1,1,1};
            int value = 11;
            int[] expected = {0,1,1,0};
            int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
            if (!Arrays.equals(expected, result)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Run the tester methods to check required validity of ChangeMaker methods
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        //testCountBase()
        System.out.println("Testing base count for count()");
        if (testCountBase()) {
            System.out.println("testCountBase() passed");
        }
        else {
            System.out.println("testCountBase() failed");
        }
        //testCountRecursive()
        System.out.println("Testing recursive count for count()");
        if (testCountBase()) {
            System.out.println("testCountRecursive() passed");
        }
        else {
            System.out.println("testCountRecursive() failed");
        }

        //testMinCountBase()
        System.out.println("Testing base count for minCoins()");
        if (testMinCoinsBase()) {
            System.out.println("testMinCoinsBase() passed");
        }
        else {
            System.out.println("testMinCoinsRecursive() failed");
        }
        //testMinCountRecursive()
        System.out.println("Testing recursion count for minCoins()");
        if (testMinCoinsRecursive()) {
            System.out.println("testMinCoinsRecursive() passed");
        }
        else {
            System.out.println("testMinCoinsRecursive() failed");
        }

        //testMakeChangeBase()
        System.out.println("Testing base count for makeChange()");
        if (testMakeChangeBase()) {
            System.out.println("testMakeChangeBase() passed");
        }
        else {
            System.out.println("testMakeChangeBase() failed");
        }
        //testMinCountRecursive()
        System.out.println("Testing recursion count for minCoins()");
        if (testMakeChangeRecursive()) {
            System.out.println("testMakeChangeRecursive() passed");
        }
        else {
            System.out.println("testMakeChangeRecursive() failed");
        }
    }
}
