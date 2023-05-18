import java.util.Scanner;

public class GPS {
    public static void main(String[] args) {

        //Problem 1: uncomment the next line to always have the same input to the program.
        //Scanner scnr = new Scanner("456\nMain St.\n");
        Scanner scnr = new Scanner(System.in);


        int streetNumber = 0;
        String streetName = "";
        boolean haveNumber = false;
        do {
            System.out.print("Enter street number:");
            if (scnr.hasNextInt()) {
                streetNumber = scnr.nextInt();
                haveNumber = true;
                System.out.print("Enter street name:");
                scnr.nextLine();
                streetName = scnr.nextLine();
            } else {
                System.out.println("Error, not a number");
                haveNumber = false;
            }
        } while (haveNumber);
        System.out.println("Address: " + streetNumber + " " + streetName);
    }
}