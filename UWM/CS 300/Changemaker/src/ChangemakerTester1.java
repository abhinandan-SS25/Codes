//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   (your name)
// Email:    (your @wisc.edu email address)
// Lecturer: (Mouna Kacem or Hobbes LeGault)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class ChangemakerTester1 {
  public static boolean testCountBase() {
    {
      // count() returns 0 when value is negative
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {3, 1, 0, 3};
      int value = -9;

      int expected = 0;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      // count() returns 0 when value is positive but there is no way to make change. You
      // can create such a scenario by choosing the sum total of all the coins in the
      // register to be smaller than value.
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {3, 1, 0, 3};
      int value = 9;

      int expected = 0;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      // count() returns 1 when value = 0
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {3, 1, 0, 3};
      int value = 0;

      int expected = 1;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }

  public static boolean testCountRecursive() {
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 1, 0, 1};
      int value = 31;

      int expected = 6;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 2, 2, 1};
      int value = 11;

      int expected = 5;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }

    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = 17;

      int expected = 5;
      int actual = Changemaker.count(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  public static boolean testMinCoinsBase() {
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = -12;

      int expected = -1;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {0, 1, 1, 1};
      int value = 2;

      int expected = -1;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = 17;

      int expected = 2;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }

  public static boolean testMinCoinsRecursive() {
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 1, 0, 1};
      int value = 31;

      int expected = 3;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 2, 2, 1};
      int value = 11;

      int expected = 2;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }

    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = 17;

      int expected = 2;
      int actual = Changemaker.minCoins(denominations, coinsRemaining, value);
      if (expected != actual) {
        return false;
      }
    }

    return true;
  }

  public static boolean testMakeChangeBase() {
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = -12;

      int[] expected = null;
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);

      if (!Arrays.equals(actual, expected)) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {0, 1, 1, 1};
      int value = 2;

      int[] expected = null;
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);
      if (!Arrays.equals(actual, expected)) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = 0;

      int[] expected = {0, 0, 0, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);

      if (!Arrays.equals(actual, expected)) {
        return false;
      }
    }
    return true;
  }

  public static boolean testMakeChangeRecursive() {
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 1, 0, 1};
      int value = 31;

      int[] expected = new int[] {1, 1, 0, 1};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);

      if (!Arrays.equals(expected, actual)) {
        return false;
      }
    }
    {
      int[] denominations = new int[] {1, 5, 10, 25};
      int[] coinsRemaining = new int[] {1, 2, 2, 1};
      int value = 11;

      int[] expected = new int[] {1, 0, 1, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);
      if (!Arrays.equals(expected, actual)) {
        return false;
      }
    }

    {
      int[] denominations = new int[] {1, 7, 10, 15};
      int[] coinsRemaining = new int[] {2, 1, 1, 1};
      int value = 17;

      int[] expected = new int[] {0, 1, 1, 0};
      int[] actual = Changemaker.makeChange(denominations, coinsRemaining, value);

      if (!Arrays.equals(expected, actual)) {
        return false;
      }
    }

    return true;

  }

  public static void main(String[] args) {
    System.out.println("testCountBase: " + testCountBase());
    System.out.println("testCountRecursive: " + testCountRecursive());
    System.out.println("testMinCoinsBase: " + testMinCoinsBase());
    System.out.println("testMinCoinsRecursive: " + testMinCoinsRecursive());
    System.out.println("testMakeChangeBase: " + testMakeChangeBase());
    System.out.println("testMakeChangeRecursive: " + testMakeChangeRecursive());


  }
}
