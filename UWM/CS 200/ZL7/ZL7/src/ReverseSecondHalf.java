///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Second half list elements reversal
// Course:          CS 200, Fall, 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help received or given.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Scanner;

/**
 *
 */
public class ReverseSecondHalf {

    /**
     *
     * @param args unused
     */
    public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      
      int currNum;
      int i; 
      int tmp;
      int listLength;
      
      listLength = scnr.nextInt();
      
      int[] listNums = new int[listLength];
      
      for(i = 0; i < listLength; i++){
         currNum = scnr.nextInt();
         listNums[i] = currNum;
      }

      int c=1;
      for (i=listLength/2; i < (listLength - listLength/4); i++) {
          int temp = listNums[i];
          listNums[i] = listNums[listLength-c];
          listNums[listLength-c] = temp;

          c=c+1;
      }
      
      
      for (i = 0; i < listLength; ++i) {
         System.out.print(listNums[i] + " ");
      }
      System.out.println();
   }
}
