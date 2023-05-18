///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Printing nested arrays.
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
//No help taken or given.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;

/**
 * A class that can be used to build String representations of 1D, 2D, and 3D String arrays to
 * display their contents.
 * 
 * @author T.J. Wilder
 * @author Anirudh Sundara Rajan
 * @author Abhinandan Saha
 */
public class NestedPrinting {

    /**
     * Takes a one-line String of input and makes a new version of it which ends with a suffix and
     * is of the given length. To make it the correct length, the original input should either be
     * truncated (losing some characters) or it should be padded at the end with spaces (' ') before
     * adding the suffix.
     * 
     * Example: endWith("xyzwad","!",4) must return "xyz!"
     *
     * @param input  A single line String to be fixed
     * @param suffix The String that the output should end with
     * @param length The total length of the output, including the suffix
     * @return A String of the given length which ends with the suffix
     **/
    public static String endWith(String input, String suffix, int length) {
        String output = "";
        for (int i = 0; i < length - suffix.length(); i++) {
            try {
                output = output + input.charAt(i);
            }
            catch (Exception e) {
                output = output + " ";
            }
        }
        output = output + suffix;
        return output;
    }

    /**
     * Returns a line of characters which consists of lineChar repeated length times.
     * 
     * Example: lineOf('_', 7) returns "_______"
     *
     * @param lineChar The character to create a line out of
     * @param length   The length of the returned String
     * @return A String of the given length consisting only of lineChar
     */
    public static String lineOf(char lineChar, int length) {
        String output = "";
        for (int i = 0; i < length; i++) {
            output = output + lineChar;
        }
        return output;
    }

    /**
     * Returns a single String as a representation of an array of Strings. Each string in the array
     * must be split based on a delimeter.
     * The return should start with '{' and end with '}'.
     * The elements of the array should be included without quotes and
     * with commas after all but the last element of the array. There should be no newline at the
     * end of the output. The delimeter must not be printed, however the string should be split 
     * at the point of the delimeter
     * <p>
     * Example output: arrToString(new String[] { "hello_Rob", "there_is", "everyone" },'_') 
     * returns: "{hello, Rob, there, is, everyone}"
     *
     *Example output: arrToString(new String[] { "he_llo_Rob", "___is", "everyone" },'_') 
     * returns: "{he, llo, Rob, is, everyone}"
     *
     * @param arr An array of Strings to be printed
     * @param delimeter The character at which the string is split
     * @return A String which has the array on a single line
     */
    public static String arrToString(String[] arr, char delimeter) {
        ArrayList<String> arr_filtered = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            String[] words = arr[i].split("\\Q" + delimeter + "\\E");
            for (int j = 0; j < words.length; j++) {
                if (words[j].length() == 0) {
                    continue;
                }
                else {
                    arr_filtered.add(words[j]);
                }
            }
        }
        String output = "{";
        for (int i = 0; i < arr_filtered.size() - 1; i++) {
            output = output + arr_filtered.get(i) + ", ";
        }
        try {
            output = output + arr_filtered.get(arr_filtered.size() - 1);
        }
        catch (Exception e){

        }
        output = output + "}";
        return output;
    }
    

    /**
     * Returns a single String as a representation of an 2D array of Strings. There will be
     * arr.length+2 lines in the returned String and it should end with a newline. Every line should
     * start with the given prefix and end with the given suffix, but each line will have different
     * contents. If adding the prefix + suffix would make the line longer than length, then the
     * contents of the line should be truncated to allow for them to be added. You can assume that
     * the length of prefix + suffix is always less than or equal to the length.
     * <p>
     * The first line's contents is just "{". The final line's contents is just "}". The inner
     * line's contents are two spaces ("  ") plus our String representation of a 1D array plus a
     * comma after all but the last element of the 2D array.
     * <p>
     * Example Output (prefix is "_", suffix is "!", length is 15): _{            ! _  {Hi, Guy}, !
     * _  {2D}       ! _}            !
     *
     * @param arr    The 2D array we're trying to represent as a String
     * @param prefix A String which should be at the start of every line of the output
     * @param suffix A String which should be at the end of every line of the output
     * @param length The length of every line of the output
     * @param delimeter The character at which the string is split
     * @return A String representing the 2D array
     */   
    public static String arrToString(String[][] arr, String prefix, String suffix, int length, 
            char delimeter) {
        String output = "";
        output = output + prefix + "{" + endWith(lineOf(' ', length - 1), suffix, length  - prefix.length() - 1) + "\n";
        for (int i = 0; i < arr.length; i ++) {
            if (i == arr.length - 1) {
                output = output + prefix + endWith("  " + arrToString(arr[i], delimeter), suffix, length - prefix.length()) + "\n";
            }
            else {
                output = output + prefix + endWith("  " + arrToString(arr[i], delimeter) + ", ", suffix, length - prefix.length()) + "\n";
            }
        }
        output = output + prefix + "}" + endWith(lineOf(' ', length - 1), suffix, length  - prefix.length() - 1) + "\n";
        return output;
    }

    /**
     * Returns a single String as a representation of an 3D array of Strings. EVERY LINE should
     * start with the given prefix and end with the given suffix, but each line will have different
     * contents.
     * <p>
     * The first line's contents is just "{". The final line's contents is just "}". Between them
     * will be a blocks of lines for each 2D array which should be created using arrToString. Every
     * line in each block should have two additional spaces ("  ") after the prefix. Between every
     * block, there should be a divider which is a single line which is just the divider character
     * repeated.
     * <p>
     * Example Output (prefix is "_", suffix is "!?", divider is '-", length is 15): _{           !?
     * _  {         !? _    {Hi, Guy!? _    {3D}    !? _  }         !? _------------!? _  {
     * !? _    {Nope}  !? _  }         !? _}           !?
     *
     * @param arr     The 3D array we're trying to represent as a String
     * @param prefix  A String which should be at the start of every line of the output
     * @param suffix  A String which should be at the end of every line of the output
     * @param divider A character to be repeated to divide blocks of 2D arrays
     * @param length  The length of every line of the output
     * @param delimeter The character at which the string is split
     * @return A String representing the 3D array
     * 
     */
    public static String arrToString(String[][][] arr, String prefix, String suffix, char divider
            , int length,char delimeter) {
        String output = "";
        output = output + prefix + "{" + endWith(lineOf(' ', length), suffix, length  - prefix.length() - 1) + "\n";
        for (int i = 0; i < arr.length; i++) {
            output = output + prefix + "  {" + endWith(lineOf(' ', length - 2), suffix, length  - prefix.length() - 3) + "\n";
            for (int j = 0; j < arr[i].length; j++){
                if (j == arr[i].length - 1) {
                    output = output + prefix + "    " + endWith(arrToString(arr[i][j], delimeter), suffix, length - prefix.length() - 4) + "\n";
                }
                else {
                    output = output + prefix + "    " + endWith(arrToString(arr[i][j], delimeter) + ", ", suffix, length - prefix.length() - 4) + "\n";
                }
            }
            output = output + prefix + "  }" + endWith(lineOf(' ', length - 2), suffix, length  - prefix.length() - 3) + "\n";
            if (i == arr.length - 1){
            }
            else {
                output = output + prefix + endWith(lineOf(divider, length - prefix.length()), suffix, length - prefix.length()) + "\n";
            }
        }
        output = output + prefix + "}" + endWith(lineOf(' ', length), suffix, length  - prefix.length() - 1) + "\n";
        return output;
    }

    /**
     * Represents 3D array inside a box. The left and right variables represent the sides of the box
     * and the divider is both the top and bottom (including corners).
     * <p>
     * Example Output (left is "_", right is "!?", divider is '-", length is 15): --------------- _{
     *           !? _  {         !? _    {Hi, Guy!? _    {3D}    !? _  }         !? _------------!?
     * _  {         !? _    {Nope}  !? _  }         !? _}           !? ---------------
     *
     * @param arr     The 3D array we're trying to represent as a String
     * @param left    A String which should be at the start of every line of the 3D array
     *                representation
     * @param right   A String which should be at the end of every line of the 3D array
     *                representation
     * @param divider A character to be repeated to divide blocks of 2D arrays and fill the top and
     *                bottom of the box
     * @param length  The length of every line of the output
     * @param delimeter The character at which the string is split
     * @return A String representing the boxed 3D array
     */
    public static String arrInBox(String[][][] arr, String left, String right, char divider,
                                  int length, char delimeter) {
        String output = "";
        output = output + lineOf(divider, length) + "\n";
        output = output + arrToString(arr, left, right, divider, length, delimeter);
        output = output + lineOf(divider, length) + "\n";
        return output;

   }
}