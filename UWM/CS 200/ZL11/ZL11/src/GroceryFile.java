///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Using PrintWriter to create Grocery File
// Course:          CS200, Fall 2022
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


import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class GroceryFile {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> amounts = new ArrayList<>(
                Arrays.asList(2,4,8,30,6,10,3,2,1));
        ArrayList<String> items = new ArrayList<>(
                Arrays.asList("onions","diet coke","green peppers", "eggs",
                        "garlic", "yogurt","lemons","milk", "cheese"));
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        String fileName = scan.nextLine();

        try {
            makeGroceryFile(fileName, amounts, items);
        } catch (Exception excpt) {
            System.out.print("Caught FileNotFoundException: " + excpt.getMessage());
        }
    }

    /**
     * Opens a file and writes the grocery list inside using a PrintWriter
     * @param fileName the name of the file to be created
     * @param amounts the amount of each item in the grocery list
     * @param items the name of the products in the grocery list
     * @throws FileNotFoundException if unable to open fileName to write
     */
    public static void makeGroceryFile(String fileName,
                                       ArrayList<Integer> amounts, ArrayList<String> items) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(fileName);

        for (int i = 0; i < amounts.size(); i++) {
            int number = amounts.get(i);
            String element = items.get(i);

            writer.println("" + number + " " + element);
        }

        writer.close();
    }
}
