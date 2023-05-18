///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Waffle Game Implementation
// Course:          CS 200, Fall 2022
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

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;

/**
 * A data type for keeping track of the different states of a letter in the waffle. CORRECT means
 * the letter in the position in the puzzle is the same letter in the position in the solution.
 * WRONG_POSITION means the letter in the position in the puzzle is in the solution but in a
 * different position. NOT_IN_WORD means the letter in the position in the puzzle is not in the word
 * in the solution. BLANK means a space character in the puzzle and solution which are at 4
 * positions in the waffle grid.
 */
enum Hint {CORRECT, WRONG_POSITION, NOT_IN_WORD, BLANK};

/**
 *  This class contains specific methods for playing and creating the text interface of the game
 *  Waffle.
 *
 * @author Jim Williams
 * @author Abhinandan Saha
 */
public class WaffleGame {

    /**
     * This method takes in the filename containing puzzle, the puzzle number to load and two 5 x 5
     * character arrays and loads the requested puzzle from file into the two character arrays. The
     * first array contains the puzzle characters. The second array contains the solution characters
     * to the puzzle.
     * Method goes through all the lines in file till it finds the requested puzzle number and
     * tries to read the next 5 lines. The lines are stored in the puzzle character array. It
     * then tries to read the next 5 lines and stores it in the solution character array. Method
     * then checks if both the character arrays have 5 lines and 5 characters each in each line.
     *
     * @param fileName              String: the name of file containing puzzles
     * @param puzzleNum             int: puzzle number to load from file
     * @param puzzleCharacters      char[][]: 5 x 5 array containing each character of requested
     *                              puzzle
     * @param solutionCharacters    char[][]: 5 x 5 array containing each character of solution to
     *                              requested puzzle
     * @return  if any parameter is null or puzzleNum <= 0: "Invalid parameter in loadPuzzle"
     *          if unable to open file: "Unable to open file: (fileName)"
     *          if puzzle corresponding puzzleNum doesn't exist:  "Puzzle not found: (puzzleNum)"
     *          when puzzle found but nextLine is integer or doesn't exist:
     *              "Invalid puzzle: (puzzleNum)"
     *          when puzzle found but a line doesn't have exactly 5 characters:
     *              "Invalid puzzle: (puzzleNum)"
     *          if puzzle and solution arrays don't have exactly 5 lines with 5 characters each:
     *              "Invalid puzzle: (puzzleNum)"
     *          if puzzle and solution read successfully: returns null
     *
     */
    public static String loadPuzzle(String fileName, int puzzleNum, char[][] puzzleCharacters,
                                    char[][] solutionCharacters) {
        try {
            if (fileName == null || puzzleNum <= 0
                    || puzzleCharacters == null || solutionCharacters == null) {
                throw new NullPointerException();
            }
            File outputStream = new File(fileName);
            Scanner scnr = new Scanner(outputStream);

            while (scnr.hasNextLine()) {

                if (scnr.hasNextInt()) {
                    if (scnr.nextInt() == puzzleNum) {
                        scnr.nextLine();
                    int line = 0;
                    while (scnr.hasNextLine() && line < 5) {
                        if (scnr.hasNextInt()) {
                            return "Invalid puzzle: " + puzzleNum;
                        }
                        if (scnr.hasNextLine()) {
                            //scnr.nextLine();
                            String lineCharacters = scnr.nextLine();
                            if (lineCharacters.length() != 5) {
                                return "Invalid puzzle: " + puzzleNum;
                            }
                            for (int c = 0; c < 5 ; c++) {
                                char character = lineCharacters.charAt(c);
                                puzzleCharacters[line][c] = character;
                            }
                        }
                        line++;
                    }
                    line = 0;
                    while (scnr.hasNextLine() && line < 5) {
                        if (scnr.hasNextInt()) {
                            return "Invalid puzzle: " + puzzleNum;
                        }
                        if (scnr.hasNextLine()) {
                            //scnr.nextLine();
                            String lineCharacters = scnr.nextLine();
                            if (lineCharacters.length() != 5) {
                                return "Invalid puzzle: " + puzzleNum;
                            }
                            for (int c = 0; c < 5 ; c++) {
                                char character = lineCharacters.charAt(c);
                                solutionCharacters[line][c] = character;
                            }
                        }
                        line++;
                    }
                    if (puzzleCharacters.length == 5 && solutionCharacters.length == 5) {
                        for (int i = 0; i < 5; i++) {
                            if (puzzleCharacters[i].length != 5
                                    || solutionCharacters[i].length != 5) {
                                return "Invalid puzzle: " + puzzleNum;
                            }
                            else {
                                continue;
                            }
                        }
                    }
                    else {
                        return "Invalid puzzle: " + puzzleNum;
                    }
                    return null;
                    }
                }
                else {
                    scnr.nextLine();
                }
            }

            return "Puzzle not found: " + puzzleNum;

        }
        catch (NullPointerException n) {
            return "Invalid parameter in loadPuzzle";
        }
        catch (FileNotFoundException e) {
            return "Unable to open file: " + fileName;
        }
    }

    /**
     * This method takes in a 5 x 5 character arrays and a 5 x 5 hint array that contains hints for
     * each corresponding puzzle array character. It prints out in horizontal and vertical
     * direction the row and column numbers and the corresponding character in the corresponding
     * row and column.
     * If position of a character is correct, corresponding uppercase character is printed out in
     * position.
     * If position of a character is incorrect, but the character exists in word corresponding
     * lowercase character in parentheses is printed out in position.
     * if character doesn't exist in word, corresponding lowercase character is printed out in
     * position.
     * if character is blank, a space is printed out.
     * Characters and if they are in correct position or not are determined by the corresponding
     * hint in same position as the character in the hint array.
     *
     * @param puzzleCharacters  char[][]: 5 x 5 array containing characters of puzzle
     * @param hints             char[][]: 5 x 5 hint object containing hints for each corresponding
     *                          puzzle character
     * @return  void
     */
    public static void printPuzzle(char[][] puzzleCharacters, Hint[][] hints) {
        System.out.println("    0  1  2  3  4 ");
        for (int row = 0; row < 5; row++) {
            System.out.print(" " + row + " ");
            for (int col = 0; col < 5; col++) {
                if (hints == null) {
                    System.out.print(" " + puzzleCharacters[row][col] + " ");
                }
                else {
                    switch (hints[row][col]) {
                        case CORRECT:
                            System.out.print(" " + puzzleCharacters[row][col] + " ");
                            break;
                        case NOT_IN_WORD:
                            System.out.print(" " + toLowerCase(puzzleCharacters[row][col]) + " ");
                            break;
                        case WRONG_POSITION:
                            System.out.print("(" + toLowerCase(puzzleCharacters[row][col]) + ")");
                            break;
                        case BLANK:
                            System.out.print("   ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * This method takes in two 5 x 5 character arrays and iterates through all characters. If all
     * puzzle characters match with corresponding solution characters the game is considered
     * complete.
     *
     * @param puzzle        char[][]: 5 x 5 array containing characters of puzzle
     * @param solution      char[][]: 5 x 5 array containing characters of solution
     * @return  if all corresponding characters of solution and puzzle are equal: true
     *          else: false
     */
    public static boolean waffleCompleted(char[][] puzzle, char[][] solution) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (puzzle[i][j] == solution[i][j]) {
                    continue;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }    
    
    /**
     * This looks through the puzzle and solution and initializes the hints. The hints indicate
     * whether each letter is in the correct location, wrong position in word or not in the word.
     * The blanks are also identified in hints.
     *
     * @param puzzle   The current state of the puzzle.
     * @param solution The solution to the puzzle.
     * @param hints    The hints are set based on the current state of the puzzle.
     */
    public static void identifyHints(char[][] puzzle, char[][] solution, Hint[][] hints) {

        //initialize hints from solution for blanks, and letters in the correct location. Also,
        //assume every other letter is not in the word, which will be modified for those
        //letters not in position below.
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[row].length; col++) {
                if (solution[row][col] == ' ') {
                    hints[row][col] = Hint.BLANK;
                } else if (puzzle[row][col] == solution[row][col]) {
                    hints[row][col] = Hint.CORRECT;
                } else {
                    hints[row][col] = Hint.NOT_IN_WORD;
                }
            }
        }

        //for horizontal words that don't have correct letters, see if the letters should be
        // marked as wrong position rather than not in word.
        for (int row = 0; row < puzzle.length; row += 2) {

            ArrayList<Character> unmatchedLettersInSolution = new ArrayList<>();
            for (int col = 0; col < solution[row].length; col++) {
                if (hints[row][col] != Hint.CORRECT) {
                    unmatchedLettersInSolution.add(solution[row][col]);
                }
            }
            for (int col = 0; col < puzzle[row].length; col++) {
                if (hints[row][col] != Hint.CORRECT) {
                    char unmatchedLetter = puzzle[row][col];
                    int indexOfLetter = unmatchedLettersInSolution.indexOf(unmatchedLetter);
                    if (indexOfLetter >= 0) {
                        hints[row][col] = Hint.WRONG_POSITION;
                        unmatchedLettersInSolution.remove(indexOfLetter);
                    }
                }
            }
        }

        //for vertical words that don't have correct letters, see if the letters should be
        // marked as wrong position rather than not in word.
        for (int col = 0; col < puzzle[0].length; col += 2) {

            ArrayList<Character> unmatchedLettersInSolution = new ArrayList<>();
            for (int row = 0; row < solution.length; row++) {
                if (hints[row][col] != Hint.CORRECT) {
                    unmatchedLettersInSolution.add(solution[row][col]);
                }
            }
            for (int row = 0; row < puzzle.length; row++) {
                if (hints[row][col] != Hint.CORRECT) {
                    char unmatchedLetter = puzzle[row][col];
                    int indexOfLetter = unmatchedLettersInSolution.indexOf(unmatchedLetter);
                    if (indexOfLetter >= 0) {
                        hints[row][col] = Hint.WRONG_POSITION;
                        unmatchedLettersInSolution.remove(indexOfLetter);
                    }
                }
            }
        }
    }

    /**
     * Swaps the letters in the specified positions in the puzzle.
     *
     * @param puzzle The current state of the puzzle that will be updated with the swap.
     * @param row1   The row of the first letter to be swapped, starts with index 0.
     * @param col1   The column of the first letter to be swapped.
     * @param row2   The row of the second letter to be swapped.
     * @param col2   The column of the second letter to be swapped.
     * @return true when the swap is successful, false otherwise.
     */
    public static boolean swap(char[][] puzzle, int row1, int col1, int row2, int col2) {
        try {
            //swap the letters
            char temp = puzzle[row1][col1];
            puzzle[row1][col1] = puzzle[row2][col2];
            puzzle[row2][col2] = temp;
            return true;
        } catch (Exception e) {  //catches all the various exceptions
            return false;
        }
    }

    /**
     * This method contains the text interface for the waffle game.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        String puzzleFilename = "waffles.txt";
        if (args.length > 0) {
            puzzleFilename = args[0];
        }

        Scanner input = new Scanner(System.in);
        final int WAFFLE_SIZE = 5;
        char[][] solution = new char[WAFFLE_SIZE][WAFFLE_SIZE];
        char[][] puzzle = new char[WAFFLE_SIZE][WAFFLE_SIZE];
        Hint[][] hints = new Hint[WAFFLE_SIZE][WAFFLE_SIZE];
        int puzzleNumber = 0;

        System.out.println("Welcome to Waffle!");
        System.out.print("Pick a puzzle number from 1 to 10:");
        if (input.hasNextInt()) {
            puzzleNumber = input.nextInt();
        } else {
            System.out.println("Invalid puzzle number: " + input.nextLine());
            return;
        }
        String loadResult = loadPuzzle(puzzleFilename, puzzleNumber, puzzle, solution);
        if (loadResult != null) {
            System.out.println(loadResult);
            return;
        }
        identifyHints(puzzle, solution, hints);

        int swapsRemaining = 15;
        boolean waffleCompleted = false;
        do {
            printPuzzle(puzzle, hints);
            System.out.println(swapsRemaining + " swaps remaining");
            System.out.print("Enter the row and column for each letter to swap.\n" +
                    "1 2 2 3 means swap row 1 column 2 with row 2 column 3:");
            boolean haveGoodInput = false;
            try {
                int row1 = input.nextInt();
                int col1 = input.nextInt();
                int row2 = input.nextInt();
                int col2 = input.nextInt();
                if (swap(puzzle, row1, col1, row2, col2)) {
                    haveGoodInput = true;
                    identifyHints(puzzle, solution, hints);
                    swapsRemaining--;
                    waffleCompleted = waffleCompleted(puzzle, solution);
                } else {
                    input.nextLine(); //clear the rest of the line.
                    System.out.println("Invalid input, please try again.");
                }
            } catch (InputMismatchException e) {
                input.nextLine(); //clear the rest of the line.
                System.out.println("Invalid input, please try again.");
            } catch (NoSuchElementException e) {
                input.nextLine(); //clear the rest of the line.
                System.out.println("Invalid input, please try again.");
            }
        } while (swapsRemaining > 0 && !waffleCompleted);

        if (waffleCompleted) {
            System.out.println("Congratulations! You solved the waffle with " + swapsRemaining
                    + " swaps remaining.");
            printPuzzle(solution, null);
        } else {
            System.out.println("Solution:");
            printPuzzle(solution, null);
        }
    }
}
