//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods for updating and maintaining patients in Urgent Care
// Admissions
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// No Pair Programming
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: NOne
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A collection of static utility methods for managing the admissions in an urgent care facility.
 * 
 * A patient's record in the patientsList consists of their five-digit case ID number, an integer
 * representing the order in which they arrived, and a constant value corresponding to the urgency
 * of their issue: patientRecord: [ caseID, arrivalOrder, triage ] You may assume that the integer
 * representing the admission order will be strictly increasing over the life of the program, so the
 * next patient to be admitted will have the LARGEST admission number in the array.
 * 
 * Patient records should be maintained in the patientsList such that a RED triage level will always
 * be before a YELLOW, which will always be before a GREEN. Within the same triage level, patients
 * should be in the order in which they were admitted.
 */
public class UrgentCareAdmissions {

  public static int RED = 0;
  public static int YELLOW = 1;
  public static int GREEN = 2;

  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the
   * rest of the list to maintain the oversize array in its current ordering.
   *
   * @param patientsList the current, active list of patient records
   * @param size         the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running
   */
  public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {

    if (patientsList.length ==0) {
      return -1;
    }
    else if (size == 0) {
      return 0;
    } else if (patientsList.length == size) {
      return -1;
    } else {
      for (int i = 0; i < size; i++) {
        int curr_triage = patientsList[i][2];
        if (triage > curr_triage) {
          continue;
        } else if (curr_triage == triage) {
          continue;
        } else {
          return i;
        }
      }
    }
    return size;
  }

  /**
   * Adds the patient record, a three-element perfect size array of ints, to the patients list in
   * the given position. This method must maintain the ordering of the rest of the array, so any
   * patients in higher index positions must be shifted out of the way.
   *
   * If there is no room to add a new patient to the array or the index provided is not a valid
   * index into the oversize array (for adding, valid indexes are 0 through size), the method should
   * not modify the patientsList array in any way.
   *
   * @param patientRecord
   * @param index
   * @param patientsList
   * @param size
   * @return
   */
  public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
    if (index < 0 || index > size) {
      return size;
    }
    if (size == patientsList.length) {
      return size;
    }
    int[][] copy = new int[patientsList.length][patientsList[0].length];
    for(int i = 0; i < patientsList.length; i++) {
      if (patientsList[i] == null) {
        copy[i] = null;
      }
      else {
        for(int j = 0; j < patientsList[i].length; j++) {
          copy[i][j] = patientsList[i][j];
        }
      }
    }
    for (int i = index + 1; i < size + 1; i++) {
      patientsList[i] = copy[i - 1];
    }
    patientsList[index] = patientRecord;

    return size + 1;
  }

  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the
   * rest of the list to maintain the oversize array in its current ordering.
   *
   * @param patientsList
   * @param size
   * @return
   */
  public static int removeNextPatient(int[][] patientsList, int size) {

    if (size == 0) {
      return 0;
    }
    int[][] copy = new int[patientsList.length][patientsList[0].length];
    for(int i = 0; i < patientsList.length; i++) {
      if (patientsList[i] == null) {
        copy[i] = null;
      }
      else {
        for(int j = 0; j < patientsList[i].length; j++) {
          copy[i][j] = patientsList[i][j];
        }
      }
    }

    for (int i = 1; i < size; i++) {
      patientsList[i - 1] = copy[i];
    }
    patientsList[size - 1] = null;

    return size - 1;
  }

  /**
   * Finds the index of a patient given their caseID number. This method must not modify
   * patientsList in any way.
   *
   * @param caseID
   * @param patientsList
   * @param size
   * @return
   */
  public static int getPatientIndex(int caseID, int[][] patientsList, int size) {

    if (size == 0) {
      return -1;
    }

    for (int i = 0; i < size; i++) {
      if (patientsList[i][0] == caseID) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Finds the patient who arrived earliest still currently present in the patientsList, and returns
   * the index of their patient record within the patientsList. The arrival value is strictly
   * increasing for each new patient, so you will not need to handle the case where two values are
   * equal.
   *
   * That is, for all patient records [ caseID, arrivalOrder, triage ], this method should find and
   * return the one with the minimum arrivalOrder value.
   *
   * This method must not modify patientsList in any way.
   *
   * @param patientsList
   * @param size
   * @return
   */
  public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
    if (size == 0) {
      return -1;
    }

    int min = 99999;
    int min_index = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i][1] < min) {
        min = patientsList[i][1];
        min_index = i;
      }
    }
    return min_index;
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList array, as follows:
   *
   * Total number of patients: 5 RED: 1 YELLOW: 3 GREEN: 1
   *
   * The first line displays the current size of the array. The next three lines display counts of
   * patients at each of the three triage levels currently in the patientsList. Any or all of these
   * numbers may be 0.
   *
   * This method must not modify the patientsList array in any way.
   * 
   * @param patientsList
   * @param size
   * @return
   */
  public static String getSummary(int[][] patientsList, int size) {

    if (size == 0) {
      return "Total number of patients: " + size + "\nRED: " + 0 + "\nYELLOW: " + 0 + "\nGREEN: "
          + 0;
    }

    int red = 0;
    int green = 0;
    int yellow = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i][2] == 0) {
        red += 1;
      }
      if (patientsList[i][2] == 1) {
        yellow += 1;
      }
      if (patientsList[i][2] == 2) {
        green += 1;
      }
    }
    return "Total number of patients: " + size + "\nRED: " + red + "\nYELLOW: " + yellow
        + "\nGREEN: " + green;
  }
}
