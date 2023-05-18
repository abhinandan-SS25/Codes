///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Garde Analyser
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Random;
import java.util.ArrayList;

/**
 * Contains methods for calculating grades.
 * @author Rui Huang
 * @author Rajesh Shashi Kumar
 * @author Bethany Schmeling
 */
public class GradeAnalyzer {

   /**
   * This method first generates a random number between 20 and 100 (both inclusive),
   * and then appends this generated number at the end of the ArrayList grades.
   * Use the method rand.nextInt and transform this value so that the number is within
   * the given bounds.
   *
   * @param grades the ArrayList of all students' grades
   * @param rand a random generator to generate grades
   */
	public static void addGrade(ArrayList<Integer> grades, Random rand) {
		if (grades == null) {

		}
		else {
			int randomGrade = rand.nextInt(100 -20 + 1) + 20;
			grades.add(randomGrade);
		}
	}
	
	/**
	 * This method searches for the lowest score in the ArrayList.
	 * Hint: Assume maximum score of 100 as initial minimum value
	 * 
	 * @param grades the ArrayList of scores to be searched from
	 * @return the minimum element in the ArrayList; -1 if it is null;
	 *         or 0 if the ArrayList is empty.
	 */
	public static int findMinScore(ArrayList<Integer> grades) {
		if (grades == null) {
			return -1;
		}
		else if (grades.size() == 0) {
			return 0;
		}
		else {
			int min = 100;

			for (int i=0; i<grades.size(); i++) {
				if (grades.get(i) < min) {
					min = grades.get(i);
				}
			}

			return min;
		}
	}
	
	/**
	 * This method calculates an average over all the scores in the ArrayList.
	 * 
	 * Note: The return value should include decimals.
	 * Make sure to use double division instead of integer division.
	 * 
	 * @param grades the ArrayList of all scores
	 * @return the average over all the elements in the ArrayList;
	 *         -1 if the ArrayList is null or empty;
	 */
	public static double calcAverageScore(ArrayList<Integer> grades) {
		if (grades == null || grades.size() == 0) {
			return -1;
		}

		double total = 0.0;
		for (int i=0; i<grades.size(); i++) {
			total = total + grades.get(i);
		}

		return (total / grades.size());
	}
	
   /**
     * This method calculates the percentage of students whose grades are greater than or equal to
     * a certain threshold. E.g.:
     *     if grades = [1, 2, 3, 4, 5]
     *     and threshold = 2
     *     the return value should be 80.0 (because 2, 3, 4, 5 are at or above threshold;
	 *     4 / 5 = 80%)
     *
     * Note: The return value should include decimals.
     * Make sure to use double division instead of integer division.
     *
     * @param grades the ArrayList of all students' grades
     * @param threshold  a specific number to compare with
     * @return a percentage in the range of [0.0, 100.0]; or -1 if the ArrayList is null or empty.
     */
    public static double calcPercentageAbove(ArrayList<Integer> grades, int threshold) {
		if (grades == null || grades.size() == 0) {
			return -1;
		}

		double num = 0;

		for (int i=0; i<grades.size(); i ++) {
			if (grades.get(i) >= threshold) {
				num = num + 1;
			}
		}

		return (num / grades.size()) * 100;
	}
	
	/**
	 * This method finds out all the students in the ArrayList with a certain grade,
	 * and stores their indices from the grades ArrayList into a new ArrayList, which is
	 * then returned.
	 * 
	 * @param grades the ArrayList of all students' grades
	 * @param gradeToFind a specific grade to be found in the ArrayList
	 * @return an ArrayList of found indices in ascending order; or null if grades is null
	 */
	public static ArrayList<Integer> findStudentsWithGrade(ArrayList<Integer> grades,
														   int gradeToFind) {
		if (grades == null) {
			return null;
		}

		ArrayList<Integer> gradeIndices = new ArrayList<Integer>();

		for (int i=0; i<grades.size(); i++) {
			if (grades.get(i) == gradeToFind) {
				gradeIndices.add(i);
			}
		}

		return gradeIndices;
	}
	
	/**
	 * Main method calls implemented methods to fil an ArrayList with random grades
	 * run statistical analysis on this list, and print the results.
	 * 
	 * @param args unused
	 */
    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<Integer>();
        Random rand = new Random(Config.SEED);
        for (int i = 0; i < Config.NUM_STUDENTS; i++)
            addGrade(grades, rand);

        int minScore = findMinScore(grades);
        System.out.println("The lowest score in this class is: " + minScore);

        double aveScore = calcAverageScore(grades);
        System.out.println("The average score in this class is: " + aveScore);

        double successRate = calcPercentageAbove(grades, 64);
        System.out.println("The percentage of students above 64 is: " + successRate + "%");

        ArrayList<Integer> studentIndices = findStudentsWithGrade(grades, 100);
        System.out.println("Here are the IDs of students who got full marks: " + studentIndices);

    }

}
