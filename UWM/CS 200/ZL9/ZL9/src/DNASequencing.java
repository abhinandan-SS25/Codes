///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           descriptive title of the program making use of this file
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help recieved or given.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Methods to sequence DNA strings.
 * @author Rami Dahman
 * @author Elliott Janssen Saldivar
 * @author Mick Ohtake
 */
public class DNASequencing {

    /**
     * Creates a String representing the given DNA sequence.
     * Return null if the aDNASequence parameter is null.
     *
     * @param aDNASequence A DNA sequence.
     * @return the String representation of the DNA sequence
     */
    public static String getSequence(ArrayList<Character> aDNASequence) {

        String seqDNA = "";
        if (aDNASequence != null) {
            for (char protein : aDNASequence) {
                seqDNA = seqDNA + protein;
            }
            return seqDNA;
        }
        else {
            return null;
        }
    }

    /**
     * Swaps the first half of the DNA strand with the second half of the DNA strand.
     * Return if aDNASequence is null.
     *
     * @param aDNASequence A DNA sequence.
     */
    public static void swapHalves(ArrayList<Character> aDNASequence) {

        if (aDNASequence == null) {
            return;
        }

        if(aDNASequence.size() % 2 != 0) {
            int listHalf = ((aDNASequence.size()) / 2);
            for (int i = 0; i < listHalf; i++) {
                Character value1 = aDNASequence.get(i + listHalf + 1);
                Character value2 = aDNASequence.get(i);
                aDNASequence.add(i, value1);
                aDNASequence.remove(i + 1);
                aDNASequence.add(i + listHalf + 1, value2);
                aDNASequence.remove(i + listHalf + 2);
            }
        }
        else {
            int listHalf = ((aDNASequence.size()) / 2);
            for (int i = 0; i < listHalf; i++) {
                Character value1 = aDNASequence.get(i + listHalf);
                Character value2 = aDNASequence.get(i);
                aDNASequence.add(i, value1);
                aDNASequence.remove(i + 1);
                aDNASequence.add(i + listHalf, value2);
                aDNASequence.remove(i + listHalf + 1);
            }
        }

    }

    /**
     * Checks for a specific String sequence within the given DNA sequence.
     * Return false if aDNASequence or sequence are null.
     *
     * @param aDNASequence A DNA sequence.
     * @param sequence A sequence to look for.
     * @return true if the sequence is found, else false
     */
    public static boolean containsSequence(ArrayList<Character> aDNASequence, String sequence) {
        if (aDNASequence == null || sequence == null ) {
            return false;
        }
        else if(getSequence(aDNASequence).contains(sequence)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method interacts with the user and manipulates DNA sequences.
     * @param args unused
     */
    public static void main(String[] args) {

        ArrayList<Character> dnaSeq = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        System.out.println("Enter number of nucleotides: Enter each nucleotide on a new line");

        if ( n >= 0) {
            final char[] ACCEPTABLE_TYPES = {'A', 'C', 'G', 'T'};
            int counter = 0;
            while (counter < n) {
                Character newNucleotide = input.next().toUpperCase().charAt(0);
                for (char acceptable : ACCEPTABLE_TYPES) {
                    if (newNucleotide.equals(acceptable)) {
                        counter++;
                        dnaSeq.add(newNucleotide);
                        break;
                    }
                }
            }
        }

            System.out.println(getSequence(dnaSeq));
            swapHalves(dnaSeq);
            System.out.println(getSequence(dnaSeq));
            System.out.println(containsSequence(dnaSeq, "TACG"));
            System.out.println(containsSequence(dnaSeq, "GCATTA"));
            System.out.println(containsSequence(dnaSeq, "CGCG"));
        }

    }
