///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           WordSearch Part 2
// Course:          Cs 200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help received or given
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class defines two methods. First method reads in a list of words from file
 * and stores them in ArrayList. Second method reads in a list of words and
 * stores them in a 2-D character array. It defines two tester methods to check
 * success of methods.
 *
 * @author (Abhinandan Saha)
 */
public class WordSearch1 {

    /**
     * Runs test methods.
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException {
        testSearchHorizontal();
        testSearchVertical();
        testSearchDiagonal();
    }

    /**
     * Opens the given file, reads in a list of words and returns the list.
     * Example:
     * dog
     * cat
     * turtle
     * elephant
     * <p>
     * If there is an error reading the file, such as the file cannot be found,
     * then the following message is shown:
     * Error: Unable to read file <dictionaryFilename>
     * with <dictionaryFilename> replaced with the parameter value.
     *
     * @param dictionaryFilename The dictionary file to read.
     * @return An ArrayList of words.
     */
    public static ArrayList<String> readDictionary(String dictionaryFilename) {
        try {
            FileInputStream inputStream = new FileInputStream(dictionaryFilename);
            Scanner scnr = new Scanner(inputStream);

            ArrayList<String> words = new ArrayList<>();
            String stringToAdd;

            while (scnr.hasNext()) {
                stringToAdd = scnr.nextLine();
                words.add(stringToAdd);
            }

            return words;
        } catch (Exception excpt) {
            System.out.println("Error: Unable to read file " + dictionaryFilename);
            return null;
        }
    }

    /**
     * Opens and reads a wordSearchFileName file returning a block of characters.
     * Example:
     * jwufyhsinf
     * agzucneqpo
     * majeurnfyt
     * <p>
     * If there is an error reading the file, such as the file cannot be found,
     * then the following message is shown:
     * Error: Unable to read file <wordSearchFileName>
     * with <wordSearchFileName> replaced with the parameter value.
     *
     * @param wordSearchFileName The dictionary file to read.
     * @return A 2d-array of characters representing the block of letters.
     */
    public static char[][] readWordSearch(String wordSearchFileName) {
        try {
            FileInputStream inputStream = new FileInputStream(wordSearchFileName);
            Scanner scnr = new Scanner(inputStream);

            int rows = 0;
            int cols = 0;
            String word;
            while (scnr.hasNext()) {
                cols = 0;
                word = scnr.next();
                for (int j = 0; j < word.length(); j++) {
                    cols++;
                }
                rows++;
            }

            FileInputStream inputStream2 = new FileInputStream(wordSearchFileName);
            Scanner scnrN = new Scanner(inputStream2);

            String inWord;
            char[][] outChars = new char[rows][cols];

            int i = 0;
            while (scnrN.hasNext()) {
                inWord = scnrN.next();
                for (int j = 0; j < inWord.length(); j++) {
                    outChars[i][j] = inWord.charAt(j);
                }
                i++;
            }

            return outChars;
        } catch (Exception excpt) {
            System.out.println("Error: Unable to read file " + wordSearchFileName);
            return null;
        }
    }

    /**
     * Looks horizontally for the word in the word search, starting at
     * the given position. If the given position matches the first
     * character of word, look for the rest of the word characters
     * in the indexes to the right and left
     *
     * @param word       The word to look for.
     * @param wordSearch The grid of characters to search through
     * @param i          The row to start searching at
     * @param j          The column to start searching at
     * @return true if the word was found, false if not.
     */
    public static boolean searchHorizontal(String word, char[][] wordSearch, int i, int j) {
        if (wordSearch[i][j] == word.charAt(0)) {

            boolean returnRight = false;
            if (j + word.length() > wordSearch[i].length) {
                returnRight = false;
            }
            else {
                for (int index = j; index < word.length() + j; index++) {
                    if (wordSearch[i][index] != word.charAt(index - j)) {
                        returnRight = false;
                        break;
                    }
                    else {
                        returnRight = true;
                    }
                }
            }

            if (returnRight) {
                return true;
            } else if (word.length() - 1 > j) {
                return false;
            }
            boolean returnLeft = false;
            int counter = 0;
            for (int index = j; index > j - word.length() + 1; index--) {
                if (wordSearch[i][index] != word.charAt(counter)) {
                    returnLeft = false;
                    break;
                }
                else {
                    returnLeft = true;
                    counter++;
                }
            }

            return returnLeft || returnRight;
        }
        return false;
    }

    /**
     * Looks vertically for the word in the word search, starting at
     * the given position. If the given position matches the first character of
     * word, look for the rest of the word characters in the indexes above and below
     *
     * @param word       The word to look for.
     * @param wordSearch The grid of characters to search through
     * @param i          The row to start searching at
     * @param j          The column to start searching at
     * @return true if the word was found, false if not.
     */
    public static boolean searchVertical(String word, char[][] wordSearch, int i, int j) {
        if (wordSearch[i][j] == word.charAt(0)) {
            boolean returnDown = false;
            if (i + word.length() > wordSearch.length) {
                returnDown = false;
            }
            else {
                for (int index = i; index < word.length() + i; index++) {
                    if (wordSearch[index][j] != word.charAt(index - i)) {
                        returnDown = false;
                        break;
                    }
                    else {
                        returnDown = true;
                    }
                }
            }

            if (returnDown) {
                return true;
            } else if (word.length() - 1 > i) {
                return false;
            }
            boolean returnUp = false;
            int counter = 0;
            for (int index = i; index > i - word.length() + 1; index--) {
                if (wordSearch[index][j] != word.charAt(counter)) {
                    returnUp = false;
                    break;
                }
                else {
                    returnUp = true;
                    counter++;
                }
            }

            return returnUp || returnDown;
        }
        return false;
    }

    /**
     * Looks diagonally (up-left, up-right, down-left and down-right) for the word in the
     * word search, starting at the given position. If the given position matches
     * the first character of the word, look for the rest of the word
     * characters in the four diagonal directions.
     *
     * @param word       The word to look for.
     * @param wordSearch The grid of characters to search through
     * @param i          The row to start searching at
     * @param j          The column to start searching at
     * @return true if the word was found, false if not.
     */
    public static boolean searchDiagonal(String word, char[][] wordSearch, int i, int j) {
        if (wordSearch[i][j] == word.charAt(0)) {
            if ((i + word.length() > wordSearch.length) && (word.length() > i)
                    && (j + word.length() > wordSearch[i].length) && (word.length() > j)) {
                return false;
            }

            boolean returnLeftDown;
            boolean returnRightDown;
            boolean returnLeftUp;
            boolean returnRightUp;

            int countColUp = j;
            int countColDown = j;
            int count = 0;
            int indexDown = i;
            int indexUp = i;

            if (countColUp < word.length() && indexUp < word.length()) {
                while (count < word.length()) {
                    if (wordSearch[countColDown][indexDown] == word.charAt(indexDown- i)) {
                        countColDown++;
                        indexDown++;
                        count++;
                    }
                    else {
                        return false;
                    }
                }
            }
            if (countColDown < word.length() && indexDown < word.length()) {
                while (count < word.length()) {
                    if (wordSearch[countColUp][indexUp] == word.charAt(indexDown- i)) {
                        countColUp--;
                        indexDown--;
                        count++;
                    }
                    else {
                        return false;
                    }
                }
            }

            while (count < word.length()) {
                if ((wordSearch[indexDown][countColUp] == word.charAt(indexDown - i))
                        || (wordSearch[indexDown][countColDown] == word.charAt(indexDown - i))
                        || (wordSearch[indexUp][countColUp] == word.charAt(indexDown - i))
                        || (wordSearch[indexUp][countColDown] == word.charAt(indexDown - i))) {
                    if (countColUp != 0) {countColUp--;}
                    if (countColDown != wordSearch[i].length-1) {countColDown++;}
                    if (indexDown != wordSearch.length - 1) {indexDown++;}
                    if (indexUp != 0) {indexUp--;}
                    count++;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Contains tester methods for searchHorizontal method
     */
    public static void testSearchHorizontal() {
        System.out.println("Testing searchHorizontal");
        boolean expected;
        boolean returned;

        char[][] wordsS = readWordSearch("WordSearch.txt");
        boolean error = false;

        //test1:negative case
        expected = false;
        returned = searchHorizontal("ragond", wordsS, 5, 15);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 1 failed");
        } else {
            System.out.println("Test 1 passed");
        }

        error = false;
        //test2:happy case
        expected = true;
        returned = searchHorizontal("hsuw", wordsS, 0, 0);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 2 failed");
        } else {
            System.out.println("Test 2 passed");
        }

    }

    /**
     * Contains tester methods for searchVertical method
     */
    public static void testSearchVertical() {
        System.out.println("Testing searchVertical");
        boolean expected;
        boolean returned;

        char[][] wordsS = readWordSearch("WordSearch.txt");
        boolean error = false;

        //test1:negative case
        expected = false;
        returned = searchVertical("rxs", wordsS, 11, 10);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 1 failed");
        } else {
            System.out.println("Test 1 passed");
        }

        error = false;
        //test2:happy case
        expected = true;
        returned = searchVertical("rrs", wordsS, 11, 10);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 2 failed");
        } else {
            System.out.println("Test 2 passed");
        }
    }

    /**
     * Contains tester methods for searchDiagonal method
     */
    public static void testSearchDiagonal() {
        System.out.println("Testing searchDiagonal");
        boolean expected;
        boolean returned;

        char[][] wordsS = readWordSearch("SampleWordSearch.txt");
        boolean error = false;

        //test1:negative case
        expected = false;
        returned = searchDiagonal("xfdg", wordsS, 5, 15);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 1 failed");
        } else {
            System.out.println("Test 1 passed");
        }

        error = false;
        //test2:happy case
        expected = false;
        returned = searchDiagonal("cac", new char[][] {{'a' , 'b' , 'c'}, {'a' , 'b' , 'c'}, {'c' , 'b' , 'a'}}, 2, 0);
        if (expected == returned) {
            error = false;
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Test 2 failed");
        } else {
            System.out.println("Test 2 passed");
        }
    }
}
