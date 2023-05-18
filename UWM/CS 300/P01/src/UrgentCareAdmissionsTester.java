//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tester methods for Urgent Care Admissions
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

import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

/**
 * This class contains the tester methods to check the working and validity of the methods declared
 * in UrgentCareAdmissions
 */
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   * 1. Adding a patient with a HIGHER triage priority than any currently present in the array. To
   * do this, we create an array with no RED priority patients and get the index to add a RED
   * priority patient. We expect this to be 0, so if we get any other value, the test fails.
   * 
   * 2. Adding a patient with a LOWER triage priority than any currently present in the array. To do
   * this, we create an array with no GREEN priority patients and get the index to add a GREEN
   * priority patient. We expect this to be the current size of the oversize array, so if we get any
   * other value, the test fails.
   * 
   * 3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   * same priority should be added AFTER any existing patients. We test this for all three triage
   * levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values.
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
        null, null, null, null};
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual)
        return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;

      int[][] onlyYellowCopy = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
          null, null, null, null};
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy))
        return false;
    }

    return true;
  }

  /**
   * Tests the behavior of the addPatient method using a non-empty, non-full array. Each test should
   * verify that the returned size is as expected and that the array has been updated (or not)
   * appropriately
   */
  public static boolean testAddPatient() {

    int allLevelsSize = 5;
    int[] new_entry = {32710, 3, UrgentCareAdmissions.RED};

    // (1) add a patient to the END of the patientsList
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] newPatientsList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN},
          {32710, 3, UrgentCareAdmissions.RED}, null, null};
      int expected = 6;
      int actual = UrgentCareAdmissions.addPatient(new_entry, allLevelsSize, patientsListAllLevels,
          allLevelsSize);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, newPatientsList))
        return false;
    }

    // (2) add a patient to the MIDDLE of the patientsList
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] newPatientsList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {32710, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
          {31501, 1, UrgentCareAdmissions.GREEN}, null, null};
      int expected = 6;
      int actual =
          UrgentCareAdmissions.addPatient(new_entry, 1, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, newPatientsList))
        return false;
    }

    // (3) add a patient using an invalid (out-of-bounds) index
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int expected = 5;
      int actual =
          UrgentCareAdmissions.addPatient(new_entry, 20, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (4) add a patient to the START of the patientList
    {
      int[][] onlyYellowCopy = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
          null, null, null};
      int[][] newPatientList = new int[][] {{32710, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, null, null, null};
      int expected = 4;
      int actual = UrgentCareAdmissions.addPatient(new_entry, 0, onlyYellowCopy, 3);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(newPatientList, onlyYellowCopy))
        return false;
    }

    return true;
  }

  /**
   * Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each test
   * should verify that the returned size is as expected and that the array has been updated (or
   * not) appropriately
   * 
   */
  public static boolean testRemovePatient() {
    // (1) remove a patient from a patientsList containing more than one record
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] newPatientsList = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
          {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null, null};
      int expected = 4;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 5);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, newPatientsList))
        return false;
    }

    // (2) remove a patient from a patientsList containing only one record
    {
      int[][] patientsListAllLevels =
          new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null, null, null};
      int[][] newPatientsList = new int[][] {null, null, null, null, null, null};
      int expected = 0;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 1);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, newPatientsList))
        return false;
    }

    return true;
  }

  // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
  public static boolean testGetPatientIndex() {
    // (1) look for a patient at the end of the list
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int expected = 4;
      int actual = UrgentCareAdmissions.getPatientIndex(31501, patientsListAllLevels, 5);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (2) look for a patient in the middle of the list
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int expected = 2;
      int actual = UrgentCareAdmissions.getPatientIndex(22002, patientsListAllLevels, 5);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (3) look for a patient not present in the list
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int expected = -1;
      int actual = UrgentCareAdmissions.getPatientIndex(40000, patientsListAllLevels, 5);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    return true;
  }

  /**
   * Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When //
   * designing these tests, recall that arrivalOrder values will all be unique!
   */
  public static boolean testLongestWaitingPatient() {
    // (1) call the method on a patientsList with only one patient
    {
      int[][] patientsListAllLevels =
          new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null};
      int expected = 0;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, 1);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (2) call the method on a patientsList with at least three patients
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      int expected = 4;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, 5);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    return true;
  }

  /**
   * Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
   */
  public static boolean testEmptyAndFullArrays() {
    // (1) test getAdmissionIndex using an empty patientsList array and any triage level

    {
      int[] new_entry = {32710, 3, UrgentCareAdmissions.YELLOW};
      int[][] patientsListAllLevels = new int[][] {null, null, null, null, null};
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(new_entry[2], patientsListAllLevels, 0);
      if (expected != actual)
        return false;
    }

    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    {
      int allLevelsSize = 5;
      int[] new_entry = {32710, 3, UrgentCareAdmissions.RED};
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}};
      int expected = -1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(new_entry[2], patientsListAllLevels,
          allLevelsSize);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (3) test addPatient using a full patientsList array
    {
      int allLevelsSize = 5;
      int[] new_entry = {32710, 3, UrgentCareAdmissions.RED};
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}};
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}};
      int expected = 5;
      int actual =
          UrgentCareAdmissions.addPatient(new_entry, 2, patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (4) test removeNextPatient using an empty patientsList array
    {
      int[][] patientsListAllLevels = new int[][] {null, null, null, null, null};
      int expected = 0;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, 0);
      if (expected != actual)
        return false;
    }

    // (5) test getPatientIndex using an empty patientsList array
    {
      int[][] patientsListAllLevels = new int[][] {null, null, null};
      int[][] allLevelsCopy = new int[][] {null, null, null};
      int expected = -1;
      int actual = UrgentCareAdmissions.getPatientIndex(40000, patientsListAllLevels, 0);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    {
      int[][] patientsListAllLevels = new int[][] {null, null, null};
      int[][] allLevelsCopy = new int[][] {null, null, null};
      int expected = -1;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, 0);
      if (expected != actual)
        return false;
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;
    }

    return true;
  }

  /**
   * Tests the getSummary method using arrays in various states
   */
  public static boolean testGetSummary() {
    // (1) test getSummary using an empty patientsList
    {
      int[][] patientsListAllLevels = new int[][] {null, null, null, null, null};
      String expected =
          "Total number of patients: " + 0 + "\nRED: " + 0 + "\nYELLOW: " + 0 + "\nGREEN: " + 0;
      String actual = UrgentCareAdmissions.getSummary(patientsListAllLevels, 0);
      if (!expected.equals(actual))
        return false;
    }

    // (2) test getSummary using a patientsList with multiple patients at a single triage level
    {
      int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
          null, null, null, null};
      String expected =
          "Total number of patients: " + 3 + "\nRED: " + 0 + "\nYELLOW: " + 3 + "\nGREEN: " + 0;
      String actual = UrgentCareAdmissions.getSummary(patientsListOnlyYellow, 3);
      if (!expected.equals(actual))
        return false;
    }

    // (3) test getSummary using a patientsList with patients at all triage levels
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      String expected =
          "Total number of patients: " + 5 + "\nRED: " + 1 + "\nYELLOW: " + 3 + "\nGREEN: " + 1;
      String actual = UrgentCareAdmissions.getSummary(patientsListAllLevels, 5);
      if (!expected.equals(actual))
        return false;
    }
    {
      int[][] patientsListAllLevels = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.RED}, {11901, 5, UrgentCareAdmissions.GREEN},
          {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null, null, null};
      String expected =
          "Total number of patients: " + 4 + "\nRED: " + 2 + "\nYELLOW: " + 0 + "\nGREEN: " + 2;
      String actual = UrgentCareAdmissions.getSummary(patientsListAllLevels, 4);
      if (!expected.equals(actual))
        return false;
    }

    return true;
  }

  /**
   * Runs each of the tester methods and displays their result
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: " + testGetIndex());
    System.out.println("add patient: " + testAddPatient());
    System.out.println("remove patient: " + testRemovePatient());
    System.out.println("get patient: " + testGetPatientIndex());
    System.out.println("longest wait: " + testLongestWaitingPatient());
    System.out.println("empty/full: " + testEmptyAndFullArrays());
    System.out.println("get summary: " + testGetSummary());
  }

}
