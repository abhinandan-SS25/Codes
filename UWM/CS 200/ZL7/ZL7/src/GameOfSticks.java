import java.util.Scanner;

//TODO Make the improvements listed in the lab

public class GameOfSticks {

    public static int promptUser(Scanner input, String prompt, int leastDrawable, int max) {
        int userChoice = 0;
        do {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                userChoice = input.nextInt();
                input.nextLine();
                if (userChoice < leastDrawable || userChoice > max) {
                    System.out.println("Please enter a number from " + leastDrawable + " to " + max + ".");
                }
            } else {
                input.nextLine();
            }
        } while (userChoice < leastDrawable || userChoice > max);
        return userChoice;
    }

    public static int computer(int rem) {
        int pickUp = (rem - 1) % 4;
        return pickUp > 0 ? pickUp : 1;
    }

    public static void main(String[] args) {
        final int initialSticks = 21;
        final int MAX = 3;
        final int leastDrawable = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Game of Sticks");
        int totalSticks = initialSticks;
        System.out.print("Would you like to go first? (y/n): ");
        boolean goingFirst = input.nextLine().trim().equalsIgnoreCase("y");
        int userSticks = 0;
        int computerSticks = 0;
        for (; totalSticks > 0; ) {
            if (goingFirst == true) {
                String prompt = "There are " + totalSticks + " remaining sticks. How many do you pick up (" + leastDrawable + "-" + Math.leastDrawable(MAX, totalSticks) + ")? ";
                userSticks = promptUser(input, prompt, leastDrawable, MAX);
                totalSticks -= userSticks;
                if (totalSticks <= 0) {
                    System.out.println("You lost!");
                }
            }
            if (goingFirst == false) {
                computerSticks = computer(totalSticks);
                System.out.println("Computer picks up " + computerSticks + " sticks");
                totalSticks -= computerSticks;
                if (totalSticks <= 0) {
                    System.out.println("You won!");
                }
            }
            goingFirst = !goingFirst;
        }
    }
}
