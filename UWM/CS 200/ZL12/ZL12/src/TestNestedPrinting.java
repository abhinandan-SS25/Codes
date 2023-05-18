import java.util.Arrays;

public class TestNestedPrinting {
    public static boolean testEndWith() {
        System.out.println("Running testEndWith");
        // Test 1 -- Length 0 line
        if(!NestedPrinting.endWith("", "!", 2).equals(" !")) {
            System.out.println("\tFailed Test 1 (padding)");
            return false;
        }

        // Test 2 -- Length 4
        if(!NestedPrinting.endWith("XY", "Z", 2).equals("XZ")) {
            System.out.println("\tFailed Test 2 (truncating)");
            return false;
        }

        System.out.println("Done testEndWith");
        return true;
    }

    public static boolean testLineOf() {
        System.out.println("Running testLineOf");

        // Test 1 -- Length 0 line
        if(!NestedPrinting.lineOf('X', 0).equals("")) {
            System.out.println("\tFailed Test 1 (length 0)");
            return false;
        }

        // Test 2 -- Length 4
        if(!NestedPrinting.lineOf('-', 4).equals("----")) {
            System.out.println("\tFailed Test 2 (length 4)");
            return false;
        }

        System.out.println("Done testLineOf");
        return true;
    }
    public static boolean testArrToSplitString1D() {
        System.out.println("Running testArrToSplitString1D");

        // Test 1
        String[] arr1 = new String[] { "___hello_there", "what_time_is_it" };
        String exp = "{hello, there, what, time, is, it}";
        String actual = NestedPrinting.arrToString(arr1,'_');

        if (!actual.equals(exp)) {
            System.out.println("\tFailed Test 1");
            return false;
        }

        String[] arr2 = new String[] { "*****", "*P*r*o**g*r*am**" };
        String exp2 = "{P, r, o, g, r, am}";
        String actual2 = NestedPrinting.arrToString(arr2,'*');
        if (!actual2.equals(exp2)) {
            System.out.println("\tFailed Test 2");
            return false;
        }

        String[] arr3 = new String[] { "#it", "#P#r#o##g#r#am##" };
        String exp3 = "{it, P, r, o, g, r, am}";
        String actual3 = NestedPrinting.arrToString(arr3,'#');
        if (!actual3.equals(exp3)) {
            System.out.println("\tFailed Test 3");
            return false;
        }

        System.out.println("Done testArrToString1D");
        return true;
    }


    public static boolean testArrToString2D() {
        System.out.println("Running testArrToString2D");

        // Test 1
        String[][] arr1 = new String[][] {
                { "Hi_Guy" },
                { "2D" }
        };
        String exp =
                          "START{           END\n"
                        + "START  {Hi, Guy},END\n"
                        + "START  {2D}      END\n"
                        + "START}           END\n";
        String actual = NestedPrinting.arrToString(arr1, "START", "END", 20,'_');
        System.out.println(actual);
        if (!actual.equals(exp)) {
            System.out.println("\t Failed Test 1");
            return false;
        }

        String[][] arr2 = new String[][] {
                { "Hello_Guys" },
                { "What's_up" }
        };
        String exp2 =
                          "START{           END\n"
                        + "START  {Hello, GuEND\n"
                        + "START  {What's, uEND\n"
                        + "START}           END\n";
        String actual2 = NestedPrinting.arrToString(arr2, "START", "END", 20,'_');
        if (!actual2.equals(exp2)) {
            System.out.println("\t Failed Test 2");
            return false;
        }

        String[][] arr3 = new String[][] {
                {}
        };
        /*String exp3 =
                          "START{           END\n"
                        + "START  {Hello, GuEND\n"
                        + "START  {What's, uEND\n"
                        + "START}           END\n";
        */String actual3 = NestedPrinting.arrToString(arr3, "_", "!", 1,'_');
        System.out.println(actual3);
        /*if (!actual3.equals(exp3)) {
            System.out.println("\t Failed Test 3");
            return false;
        }*/
        /*
        _{!
        _  !
        _  !
        _}!
        */
        /*
          _{!
          _ !
          _}!
        */

        // arrToString()

        System.out.println("Done testArrToString2D");
        return true;
    }

    public static boolean testArrToString3D() {
        System.out.println("Running testArrToString3D");

        // Test 1
        String[][][] arr1 = new String[][][] {
                {
                        { "Hi++Guy" },
                        { "3D" }
                },
                {
                        { "Nope" }
                }
        };
        String exp =
                          "_{           !?\n"
                        + "_  {         !?\n"
                        + "_    {Hi, Guy!?\n"
                        + "_    {3D}    !?\n"
                        + "_  }         !?\n"
                        + "_------------!?\n"
                        + "_  {         !?\n"
                        + "_    {Nope}  !?\n"
                        + "_  }         !?\n"
                        + "_}           !?\n";
        String actual = NestedPrinting.arrToString(arr1, "_", "!?", '-', 15,'+');
        if (!actual.equals(exp)) {
            System.out.println("\t Failed Test 1");
            return false;
        }

        System.out.println("Done testArrToString3D");
        return true;
    }

    public static boolean testArrInBox() {
        System.out.println("Running testArrInBox");

        // Test 1
        String[][][] arr1 = new String[][][] {
                {
                        { "Hi", "Guy" },
                        { "&3&D&" }
                },
                {
                        { "Nope" }
                }
        };
        String exp =
                "---------------\n"
                        + "_{           !?\n"
                        + "_  {         !?\n"
                        + "_    {Hi, Guy!?\n"
                        + "_    {3, D}  !?\n"
                        + "_  }         !?\n"
                        + "_------------!?\n"
                        + "_  {         !?\n"
                        + "_    {Nope}  !?\n"
                        + "_  }         !?\n"
                        + "_}           !?\n"
                        + "---------------\n";
        String actual = NestedPrinting.arrInBox(arr1, "_", "!?", '-', 15,'&');
        if (!actual.equals(exp)) {
            System.out.println("\t Failed Test 1");
            return false;
        }

        System.out.println("Done testArrInBox");
        return true;
    }

    public static void main(String[] args) {
        testEndWith();
        testLineOf();
        testArrToSplitString1D();
        testArrToString2D();
        testArrToString3D();
        testArrInBox();
    }
}