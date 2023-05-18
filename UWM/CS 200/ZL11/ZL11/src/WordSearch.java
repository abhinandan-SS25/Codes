///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           descriptive title of the program making use of this file
// Course:          course number, term, and year
//
// Author:          your name
// Email:           your @wisc.edu email address
// Lecturer's Name: name of your lecturer
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://cs200-www.cs.wisc.edu/wp/syllabus/#academicintegrity
// Source or Recipient; Description
// Examples:
// Jane Doe; helped me with for loop in reverse method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class WordSearch {

    public static void main(String[] args) throws FileNotFoundException {
    	Scanner userInput = new Scanner(System.in);
    	System.out.println("Enter the wordSearch file name");
    	String wordSearchFile = userInput.next();
    	ArrayList<String> foundWords = searchForWords("dictionary.txt",wordSearchFile);
      printWordsFound(foundWords);

      testSearchForWords("SampleWordSearch.txt", "SampleAnswers.txt");
      testPrintWordsFound("wordsfound.txt");

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
                stringToAdd = scnr.nextLine().toLowerCase();
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

            boolean searchLeft = true;
            boolean searchRight = true;
            boolean searchUp = true;
            boolean searchDown = true;

            int countColUp = j;
            int countColDown = j;
            int count = 0;
            int indexDown = i;
            int indexUp = i;
            while (count < word.length()) {
                if ((searchRight && searchDown && wordSearch[indexDown][countColUp] == word.charAt(indexDown - i))
                        || (searchDown && searchLeft && wordSearch[indexDown][countColDown] == word.charAt(indexDown - i))
                        || (searchUp && searchRight && wordSearch[indexUp][countColUp] == word.charAt(indexDown - i))
                        || (searchUp && searchLeft && wordSearch[indexUp][countColDown] == word.charAt(indexDown - i))) {
                    if (countColUp != 0) {countColUp--;} else {searchRight = false;}
                    if (countColDown != wordSearch[i].length-1) {countColDown++;} else {searchLeft = false;}
                    if (indexDown != wordSearch.length - 1) {indexDown++;} else {searchDown = false;}
                    if (indexUp != 0) {indexUp--;} else {searchUp = false;}
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
     * Given the name of a dictionary file, and the name of the word search file, this
     * will solve the word search by finding all of the words listed in the dictionary file.
     * 
     * Duplicates should not be returned.
     * 
     * @param dictionaryFileName The dictionary file to read.
     * @param wordSearchFileName The file containing the word search.
     * @return  An ArrayList of words found.
     */
    public static ArrayList<String> searchForWords(String dictionaryFileName, String wordSearchFileName){
    	ArrayList<String> returnList = new ArrayList<>();

        ArrayList<String> wordDict = readDictionary(dictionaryFileName);
        char[][] wordSearch = readWordSearch(wordSearchFileName);

        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);

            returnList.add(word);
            for (int j = 0; j < wordSearch.length; j++) {
                for (int k = 0; k < wordSearch[j].length; k++) {
                    if (searchDiagonal(word, wordSearch, j, k) || searchVertical(word, wordSearch, j, k) || searchHorizontal(word, wordSearch, j, k)) {
                        if (returnList.get(returnList.size()-1) != word) {
                            returnList.add(word);
                        }
                    }
                }
            }
            returnList.remove(0);
        }

        return returnList;
    }

    /** Given the name of a list, print the words in the list onto an output file
     *  named "wordsfound.txt"
     * 
     * @param foundWords an ArrayList of words found
     */
    public static void printWordsFound(ArrayList<String> foundWords) throws FileNotFoundException {
        File file = new File("wordsfound.txt");
        PrintStream stream = new PrintStream(file);
        System.setOut(stream);

        for (int i = 0; i < foundWords.size(); i++) {
            String word = foundWords.get(i);
            System.out.println(word);
        }
    }
	
    public static void testSearchForWords(String wordSearchFileName, String answersFileName){
    	
	//IMPLEMENT

    }

    public static void testPrintWordsFound(String wordsFoundFileName){
    	
	//IMPLEMENT

    }
}
