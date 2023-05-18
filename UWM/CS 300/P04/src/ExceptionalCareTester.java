//////////////// FILE HEADER //////////////////////////
//
// Title: Testing out the methods defined for handling and maintaining PatientRecord and
//////////////// ExceptionalCareAdmission classes
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
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * Tester methods for testing and checking the validity and expected behaviour of
 * ExceptionalCareAdmissions and PatientRecord classes and its instantiated objects.
 */
public class ExceptionalCareTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for testing an
   * instantiable class. This method verifies the correctness of your PatientRecord class.
   * 
   * In this test, we create two PatientRecords with different information and use the accessor
   * methods to verify that both contain the correct information and have the correct String
   * representation.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testPatientRecord() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) create two PatientRecords with different, valid input
    // no exceptions should be thrown, so let's be safe:
    PatientRecord test1 = null, test2 = null;
    try {
      test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
      test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
    } catch (Exception e) {
      return false;
    }

    // (2) verify their data fields:
    {
      // CASE_NUMBER
      int expected1 = 21701;
      int expected2 = 32102;
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2)
        return false;
    }
    {
      // triage
      int expected1 = PatientRecord.YELLOW;
      int expected2 = PatientRecord.GREEN;
      if (test1.getTriage() != expected1 || test2.getTriage() != expected2)
        return false;
    }
    {
      // gender
      char expected1 = 'M';
      char expected2 = 'X';
      if (test1.getGender() != expected1 || test2.getGender() != expected2)
        return false;
    }
    {
      // age
      int expected1 = 17;
      int expected2 = 21;
      if (test1.getAge() != expected1 || test2.getAge() != expected2)
        return false;
    }
    {
      // orderOfArrival
      int expected1 = 1;
      int expected2 = 2;
      if (test1.getArrivalOrder() != expected1 || test2.getArrivalOrder() != expected2)
        return false;
    }
    {
      // hasBeenSeen - try the mutator too
      if (test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
      test1.seePatient();
      if (!test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
    }

    // (3) verify their string representations
    {
      String expected1 = "21701: 17M (YELLOW)";
      String expected2 = "32102: 21X (GREEN)";
      if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2))
        return false;
    }

    // (4) finally, verify that the constructor throws an exception for an invalid triage value
    try {
      new PatientRecord('F', 4, -17);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      // incorrect exception type
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * Tester to verify the validity of the ExceptionalCareAdmission class constructor for valid
   * constructors and checking the correctness of the initiated fields
   *
   * @return true if constructor is valid, otherwise false
   */
  public static boolean testAdmissionsConstructorValid() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    // (1) verify that a normal, valid-input constructor call does NOT throw an exception
    try {
      ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
      // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
      // its toString() is an empty string
      if (admissions.size() == 0 && !admissions.isFull() && admissions.getNumberSeenPatients() == 0
          && admissions.toString().equals("")) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tester for the constructor of ExceptionalCareAdmission class for invalid constructor calls and
   * the corresponding exceptions thrown
   *
   * @return true if constructor has valid behavior for invalid constructor calls, otherwise false
   */
  public static boolean testAdmissionsConstructorError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
    try {
      ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(0);
    } catch (IllegalArgumentException excpt) {
      try {
        ExceptionalCareAdmissions admissions2 = new ExceptionalCareAdmissions(-2);
      } catch (IllegalArgumentException e) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tester for checking the validity, behavior and result for addPatient method in
   * ExceptionalCareAdmission
   *
   * @return true if the expected behavior is achieved, otherwise false
   */
  public static boolean testAddPatientValid() {
    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
    // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
    PatientRecord test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
    PatientRecord test2 = new PatientRecord('X', 71, PatientRecord.RED);
    PatientRecord test3 = new PatientRecord('F', 25, PatientRecord.GREEN);

    String expected1 = "" + test1.CASE_NUMBER + ": 17M (YELLOW)";
    try {
      admissions.addPatient(test1, 0);
      if (!admissions.toString().equals(expected1)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    // (2) add a new patient to the end of the list
    String expected2 =
        "" + test1.CASE_NUMBER + ": 17M (YELLOW)\n" + test3.CASE_NUMBER + ": 25F (GREEN)";
    try {
      admissions.addPatient(test3, admissions.size());
      if (!admissions.toString().equals(expected2)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    String expected3 = "" + test2.CASE_NUMBER + ": 71X (RED)\n" + test1.CASE_NUMBER
        + ": 17M (YELLOW)\n" + test3.CASE_NUMBER + ": 25F (GREEN)";
    // (3) add a new patient to the beginning of the list
    try {
      admissions.addPatient(test2, 0);
      if (!admissions.toString().equals(expected3)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   * 
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor and
   * PatientRecord constructor are working properly.
   * 
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ////// (1) a full Admissions object that contains no seen patients

    // create a small admissions object and fill it with patients. i'm filling the list
    // in decreasing order of triage, so the addPatient() method has to do the least
    // amount of work.
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

    // saving one patient in a local variable so we can mark them "seen" later
    PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
    full.addPatient(seenPatient, 2);

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception, but for this method we expect a specific
      // error message so we have one more step to verify:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }

    ////// (2) a full Admissions object that contains at least one seen patient

    // since we have a reference to the patient at index 2, we'll just mark them seen here
    seenPatient.seePatient();

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * Tester method for checking validity of getIndex method in ExceptionalCareAdmission and expected
   * behavior
   *
   * @return true if the expected behavior is achieved for valid arguments
   */
  public static boolean testGetIndexValid() {
    // create an Admissions object and add a few Records to it, leaving some space
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest = new ExceptionalCareAdmissions(7);
    admissionTest.addPatient(new PatientRecord('M', 18, PatientRecord.YELLOW), 0);
    admissionTest.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
    admissionTest.addPatient(new PatientRecord('F', 15, PatientRecord.YELLOW), 1);

    // (1) get the index of a PatientRecord that should go at the END
    PatientRecord test1 = new PatientRecord('X', 25, PatientRecord.GREEN);
    int expected1 = 3;
    int actual1 = admissionTest.getAdmissionIndex(test1);

    if (expected1 != actual1) {
      return false;
    }
    // (2) get the index of a PatientRecord that should go at the BEGINNING
    PatientRecord test2 = new PatientRecord('F', 36, PatientRecord.YELLOW);
    int expected2 = 3;
    int actual2 = admissionTest.getAdmissionIndex(test2);

    if (expected2 != actual2) {
      return false;
    }
    // (3) get the index of a PatientRecord that should go in the MIDDLE
    PatientRecord test3 = new PatientRecord('M', 18, PatientRecord.RED);
    int expected3 = 0;
    int actual3 = admissionTest.getAdmissionIndex(test3);

    if (expected3 != actual3) {
      return false;
    }
    return true;
  }

  /**
   * Tester method for checking the expected behavior of getIndex of ExceptionalCareAdmission and
   * the exceptions thrown for invalid arguments passed to methhod
   *
   * @return true if the expected behavior is achieved else false
   */
  public static boolean testGetIndexError() {
    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    // create an Admissions object and add a few Records to it, leaving some space
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest = new ExceptionalCareAdmissions(3);
    PatientRecord test = new PatientRecord('M', 18, PatientRecord.YELLOW);
    admissionTest.addPatient(test, 0);
    admissionTest.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
    admissionTest.addPatient(new PatientRecord('F', 15, PatientRecord.YELLOW), 2);

    // (1) verify the exception when there are no patients who have been seen in the list
    try {
      admissionTest.getAdmissionIndex(new PatientRecord('M', 35, PatientRecord.RED));
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Cannot admit new patients")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    try {
      // (2) verify the exception when there is at least one patient who has been seen
      test.seePatient();
      admissionTest.getAdmissionIndex(new PatientRecord('M', 36, PatientRecord.GREEN));
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("cleanPatientsList()")) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tester for checking the expected behavior and field values after calling
   * ExceptionalAdmissionCare accessor methods
   *
   * @return true if the expected behavior is achieved else false
   */
  public static boolean testAdmissionsBasicAccessors() {
    // (1) verify isFull() on a non-full and a full Admissions object
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest = new ExceptionalCareAdmissions(3);
    admissionTest.addPatient(new PatientRecord('M', 18, PatientRecord.YELLOW), 0);
    admissionTest.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
    admissionTest.addPatient(new PatientRecord('F', 15, PatientRecord.YELLOW), 2);
    if (!admissionTest.isFull()) {
      return false;
    }

    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest1 = new ExceptionalCareAdmissions(3);
    PatientRecord test1 = new PatientRecord('X', 18, PatientRecord.YELLOW);
    PatientRecord test2 = new PatientRecord('M', 15, PatientRecord.GREEN);
    PatientRecord test3 = new PatientRecord('F', 15, PatientRecord.RED);
    admissionTest1.addPatient(test1, 0);
    admissionTest1.addPatient(test2, 1);
    if (admissionTest1.isFull()) {
      return false;
    }
    // (2) verify size() before and after adding a PatientRecord
    if (admissionTest1.size() != 2) {
      return false;
    }
    int index = admissionTest1.getAdmissionIndex(test3);
    admissionTest1.addPatient(test3, index);
    if (admissionTest1.size() != 3) {
      return false;
    }

    // (3) verify getNumberSeenPatients() before and after seeing a patient
    if (admissionTest1.getNumberSeenPatients() != 0) {
      return false;
    }
    test2.seePatient();
    if (admissionTest1.getNumberSeenPatients() != 1) {
      return false;
    }
    return true;
  }

  /**
   * Tester method for the valid behavior of seePatient method ExceptionalAdmissionCare class for
   * valid arguments passed
   *
   * @return true if expected behavior is achieved else false
   */
  public static boolean testSeePatientValid() {
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest1 = new ExceptionalCareAdmissions(3);
    PatientRecord test1 = new PatientRecord('X', 18, PatientRecord.YELLOW);
    PatientRecord test2 = new PatientRecord('M', 15, PatientRecord.GREEN);
    PatientRecord seen = test2;
    admissionTest1.addPatient(test1, 0);
    admissionTest1.addPatient(test2, 1);

    if (admissionTest1.getNumberSeenPatients() != 0) {
      return false;
    }
    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    admissionTest1.seePatient(seen.CASE_NUMBER);
    if (!seen.hasBeenSeen()) {
      return false;
    }
    if (admissionTest1.getNumberSeenPatients() != 1) {
      return false;
    }
    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    return true;
  }

  /**
   * Tester for checking the correct exceptions thrown for invalid arguments in seePatient method of
   * ExceptionalCareAdmission
   *
   * @return true if expected behavior is achieved else false
   */
  public static boolean testSeePatientError() {
    // (1) verify that seeing a caseID for a patient not in the list causes an
    // IllegalArgumentException
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest1 = new ExceptionalCareAdmissions(3);
    PatientRecord test1 = new PatientRecord('X', 18, PatientRecord.YELLOW);
    PatientRecord test2 = new PatientRecord('M', 15, PatientRecord.GREEN);
    admissionTest1.addPatient(test1, 0);
    admissionTest1.addPatient(test2, 1);
    try {
      admissionTest1.seePatient(111111);
    } catch (IllegalArgumentException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tester methods checking the validity of getSummary method of ExceptionalCareAdmission class and
   * the string representation of the class generated by the method.
   *
   * @return true if the generated methods are valid for different configurations of the class else
   *         false
   */
  public static boolean testGetSummary() {
    // (1) choose one getSummary() test from P01; this method has not changed much
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest = new ExceptionalCareAdmissions(7);
    PatientRecord test = new PatientRecord('M', 85, PatientRecord.RED);
    admissionTest.addPatient(new PatientRecord('M', 18, PatientRecord.YELLOW), 0);
    admissionTest.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
    admissionTest.addPatient(new PatientRecord('F', 15, PatientRecord.YELLOW), 2);
    admissionTest.addPatient(test, admissionTest.getAdmissionIndex(test));
    admissionTest.seePatient(test.CASE_NUMBER);
    String expected = "Total number of patients: " + 4 + "\nTotal number seen: " + 1 + "\nRED: " + 1
        + "\nYELLOW: " + 3 + "\nGREEN: " + 0;
    String actual = admissionTest.getSummary();
    if (expected.equals(actual)) {
      return true;
    }
    return false;
  }

  /**
   * tester methods for checking the validity of cleanList of ExceptionalCareAdmission and the
   * expected behavior of the method
   *
   * @return true if the expected behavior is achieved else false
   */
  public static boolean testCleanList() {
    // create an Admissions object and add a few Records to it
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions admissionTest = new ExceptionalCareAdmissions(7);
    PatientRecord test = new PatientRecord('M', 85, PatientRecord.RED);
    PatientRecord test2 = new PatientRecord('X', 55, PatientRecord.GREEN);
    PatientRecord add_test1 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    PatientRecord add_test2 = new PatientRecord('F', 15, PatientRecord.YELLOW);
    admissionTest.addPatient(add_test1, admissionTest.getAdmissionIndex(add_test1));
    admissionTest.addPatient(add_test2, admissionTest.getAdmissionIndex(add_test2));
    admissionTest.addPatient(test, admissionTest.getAdmissionIndex(test));
    admissionTest.addPatient(test2, admissionTest.getAdmissionIndex(test2));

    String initial1 =
        "" + test.CASE_NUMBER + ": 85M (RED)\n" + add_test1.CASE_NUMBER + ": 5M (YELLOW)\n"
            + add_test2.CASE_NUMBER + ": 15F (YELLOW)\n" + test2.CASE_NUMBER + ": 55X (GREEN)";
    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
    // patients does not change after calling cleanPatientsList()
    admissionTest.cleanPatientsList(new File("./output1.txt"));
    if (!admissionTest.toString().equals(initial1)) {
      return false;
    }

    // (2) call seePatient() for at least two of the records in your patientsList, and use
    // toString()
    // to verify that they have been removed after calling cleanPatientsList()
    admissionTest.seePatient(test.CASE_NUMBER);
    admissionTest.seePatient(test2.CASE_NUMBER);
    admissionTest.cleanPatientsList(new File("./output.txt"));

    String expected =
        "" + add_test1.CASE_NUMBER + ": 5M (YELLOW)\n" + add_test2.CASE_NUMBER + ": 15F (YELLOW)";

    if (!admissionTest.toString().equals(expected)) {
      return false;
    }

    admissionTest.seePatient(add_test1.CASE_NUMBER);
    admissionTest.seePatient(add_test2.CASE_NUMBER);
    admissionTest.cleanPatientsList(new File("./output2.txt"));

    String expected2 = "";

    if (!admissionTest.toString().equals(expected2)) {
      return false;
    }

    // NOTE: you do NOT need to verify file contents in this test method; please do so manually
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have their
   * output grouped for convenience; a failed test is displayed as "X" and a passed test is
   * displayed as "pass"
   * 
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out
        .println("Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X")
            + ", " + (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", "
        + (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", "
        + (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", "
        + (testSeePatientError() ? "pass" : "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }

}
