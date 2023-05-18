//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods to recursively make change given a particular value and collection of
//////////////// denominations
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
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
 * This class contains three to make change given a value and a collection of denominations in
 * different ways.
 */
public class Changemaker {

  /**
   * This method, determine the number of possible ways to make change for a given value with a
   * limited number of coins of varying denominations as described by the parameters.
   *
   * @param denominations  an array of various denominations or values of coins.
   * @param coinsRemaining an representing how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register.
   * @param value          the total value that we wish to make change for.
   * @return an integer "n" if there is n way to make change for given value 0 if there is no way to
   *         make the change given the number and type of denomination for value 1 if the given
   *         value is 0
   */
  public static int count(int[] denominations, int[] coinsRemaining, int value) {
    int cnt = 0;
    // base cases
    if (value == 0) {
      return 1;
    }
    if (value < 0) {
      return 0;
    }
    if (checkEmpty(coinsRemaining)) {
      return 0;
    }
    // recursive case
    for (int i = 0; i < coinsRemaining.length; i++) {
      // If there are denominations of the corresponding type available, use them to
      // try and satisfy value
      if (coinsRemaining[i] != 0) {
        // passed copy of coinsRemaining array with the corresponding denomination count
        // reduced by one and running the function recursively with value reduced by the
        // corresponding denomination
        int[] cRCopy = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        cRCopy[i]--;
        // Add corresponding count
        cnt = cnt + count(denominations, cRCopy, value - denominations[i]);
      }
    }
    return cnt;
  }

  /**
   * This method determines the optimal way to make the change with given combination of coins
   * remaining in register and denominations available It returns the minimum number of coins
   * required to make the change.
   *
   * @param denominations  an array of various denominations or values of coins.
   * @param coinsRemaining an representing how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register.
   * @param value          the total value that we wish to make change for.
   * @return 0 if the value is 0 (make change using no coins) -1 if it is not possible to make the
   *         change using current combination of coinsRemaining and denominations for given value
   *         "n" if n is the minimum number of coins required to be used for making change of the
   *         given value (optimal way to make change)
   */
  public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
    // base cases
    if (value == 0) {
      return 0;
    }
    if (value < 0) {
      return -1;
    }
    if (checkEmpty(coinsRemaining)) {
      return -1;
    }
    // recursive cases
    int min = 0;
    // cnt is the number of branches that may arise from the current value when
    // using a denomination
    int cnt = 0;
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] != 0) {
        cnt++;
      }
    }
    // array to hold the minimum coins returned by each recursive step below it and
    // running the algorithm again on correspondingly modified values
    int[] mins = new int[cnt];
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] != 0) {
        int[] cRCopy = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        cRCopy[i]--;
        int curr = minCoins(denominations, cRCopy, value - denominations[i]);
        for (int x = 0; x < mins.length; x++) {
          if (mins[x] == 0) {
            mins[x] = curr;
            break;
          }
        }
      }
    }
    // sets the first element of mins as the minimum.
    for (int j = 0; j < mins.length; j++) {
      if (mins[j] >= 0) {
        min = mins[j];
        break;
      }
    }
    // Checks if all the returned elements are -1 indicating no possible way to make
    // change
    int checkC = 0;
    for (int r = 0; r < mins.length; r++) {
      if (mins[r] == -1) {
        checkC++;
      }
    }
    if (checkC == mins.length) {
      return -1;
    }
    // find smallest non-negetive
    for (int k = 0; k < mins.length; k++) {
      if (mins[k] < min && mins[k] >= 0) {
        min = mins[k];
      }
    }
    // increases min by one corresponding for each level of return
    return 1 + min;
  }

  /**
   * This method computes an array exactly representing the exact number of each type of coin needed
   * to make the given value
   * 
   * @param denominations  an array of various denominations or values of coins.
   * @param coinsRemaining an representing how many coins of each corresponding denomination in the
   *                       denominations array are remaining in our cash register.
   * @param value          the total value that we wish to make change for.
   * @return null if there is no way to make change for the given value with the corresponding
   *         combination of coinsRemaining and denominations an array containing only 0s of length
   *         denominations array if value = 0 (change made using no coins) an array containing the
   *         number of corresponding denominations required for making change
   */
  public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
    int[] required = new int[denominations.length];
    int min = 0;
    // base cases
    if (value == 0) {
      return required;
    }
    if (value < 0) {
      return null;
    }
    if (checkEmpty(coinsRemaining)) {
      return null;
    }
    // recursive case
    // cnt is the number of branches that may arise from the current value when
    // using a denomination
    int cnt = 0;
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] != 0) {
        cnt++;
      }
    }
    // options contains the array returned by a nested recursive call
    int[][] options = new int[cnt][];
    // the number of coins used by corresponding nested recursive call
    int[] mins = new int[cnt];
    // nested recursive calls
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] != 0) {
        int[] cRCopy = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        cRCopy[i]--;
        int[] curr = makeChange(denominations, cRCopy, value - denominations[i]);
        if (curr != null) {
          curr[i]++;
        }
        for (int x = 0; x < mins.length; x++) {
          if (mins[x] == 0) {
            mins[x] = sumCoins(curr);
            options[x] = curr;
            break;
          }
        }
      }
    }
    // sets first element as min
    for (int j = 0; j < mins.length; j++) {
      if (mins[j] >= 0 && options[j] != null) {
        required = options[j];
        min = mins[j];
        break;
      }
    }
    // checks if all nested recursive steps returned null indicating making change
    // not possible
    int checkC = 0;
    for (int r = 0; r < mins.length; r++) {
      if (options[r] == null) {
        checkC++;
      }
    }
    if (checkC == mins.length) {
      return null;
    }
    // finds required array with minimum counts for step
    for (int k = 0; k < mins.length; k++) {
      if (mins[k] < min && mins[k] >= 0) {
        min = mins[k];
        required = options[k];
      }
    }
    return required;
  }

  /**
   * This method takes and array and returns the sum of the elements in it
   * 
   * @param arr array to be summed
   * @return the sum of elements in array
   */
  public static int sumCoins(int[] arr) {
    if (arr == null) {
      return -1;
    }
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum = sum + arr[i];
    }
    return sum;
  }

  /**
   * This method takes in an array and checks if it is empty, i.e. all elements are 0
   * 
   * @param coinsRemaining
   * @return true if array is empty false if not
   */
  public static boolean checkEmpty(int[] coinsRemaining) {
    for (int i = 0; i < coinsRemaining.length; i++) {
      if (coinsRemaining[i] != 0) {
        return false;
      }
    }
    return true;
  }
}
