import java.util.Scanner;
import java.util.Arrays;

public class Tpotify1 {

    public static final char[] genreList = {'C', 'E', 'H', 'P', 'J'};

    public static char findMax(int[] genreOccurances) {
        int m = genreOccurances[0];
        char max = genreList[0];
        for (int k = 1; k < 5; k++) {
            if (m < genreOccurances[k]) {
                m = genreOccurances[k];
                max = genreList[k];
            }
            else {
                continue;
            }
        }

        return max;
    }
    public static int[] countOccurances(String genre) {
        int[] genreOccurances = new int[5];

        for (int j=0; j<genre.length(); j++) {
            if (genre.charAt(j)=='C') {
                genreOccurances[0]=genreOccurances[0]+1;
            }
            else if (genre.charAt(j)=='E') {
                genreOccurances[1]=genreOccurances[1]+1;
            }
            else if (genre.charAt(j)=='H') {
                genreOccurances[2]=genreOccurances[2]+1;
            }
            else if (genre.charAt(j)=='P') {
                genreOccurances[3]=genreOccurances[3]+1;
            }
            else if (genre.charAt(j)=='J') {
                genreOccurances[4]=genreOccurances[4]+1;
            }
        }

        return genreOccurances;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int user_no = input.nextInt();
        input.nextLine();

        String[] users = new String[user_no];
        String genres = "";

        for (int i=0; i<user_no; i++) {
            users[i] = input.nextLine();
            String genre = input.nextLine();

            int[] genreOccurances = countOccurances(genre);

            genres = genres + findMax(genreOccurances);
        }

        for (int y=0; y<user_no; y++) {
            System.out.println(users[y] + ": " + genres.charAt(y));
        }
        int[] overAll = countOccurances(genres);
        System.out.println("Summary: " + findMax(overAll));
    }
}
