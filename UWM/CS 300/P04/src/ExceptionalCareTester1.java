import java.io.File;
///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Tester for ExceptionalCareAdmissions
// Course:          CS300, Spring 2023
//
// Author:          Arnav Mohakud
// Email:           mohakud@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////////////// CITATIONS ////////////////////////////////////
// None
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

// File header goes here

// Javadoc style class header goes here
public class ExceptionalCareTester1 {

  /**
   * This test method is provided for you in its entirety, to give you a model for testing
   * an instantiable class. This method verifies the correctness of your PatientRecord
   * class.
   * 
   * In this test, we create two PatientRecords with different information and use the
   * accessor methods to verify that both contain the correct information and have the
   * correct String representation.
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

    // (4) finally, verify that the constructor throws an exception for an invalid triage
    // value
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

    // if we've gotten this far, we haven't failed either of the scenarios, so our test
    // passes!
    return true;
  }

  public static boolean testAdmissionsConstructorValid() {
    // (1) verify that a normal, valid-input constructor call does NOT throw an exception
    ExceptionalCareAdmissions test = null;
    try {
      test = new ExceptionalCareAdmissions(5);
    } catch (Exception e) {
      return false;
    }
    // (2) verify that a just-created object has size 0, is not full, has no seen
    // patients, and
    // its toString() is an empty string

    // verifying size
    {
      int expected = 0;
      int actual = test.size();
      if (expected != actual) {

        return false;
      }
    }
    // verifying not full
    {
      boolean expected = false;
      boolean actual = test.isFull();
      if (expected != actual) {
        return false;
      }
    }
    // verifying no seen patients
    {
      int expected = 0;
      int actual = test.getNumberSeenPatients();
      if (expected != actual) {
        return false;
      }
    }
    // verifying toString() is an empty string
    {
      String expected = "";
      String actual = test.toString();

      if (!(expected.equals(actual.trim()))) {
        return false;
      }
    }
    return true;
  }

  public static boolean testAdmissionsConstructorError() {

    // (1) verify that calling the constructor with capacity <= 0 causes an
    // IllegalArgumentException
    try {
      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(-1);
    } catch (IllegalArgumentException e) {
      // since this is the correct exception type, we'll ignore it
    } catch (Exception e) {
      // since this is the incorrect exception type, we'll throw an exception
      return false;
    }
    return true;
  }

  public static boolean testAddPatientValid() {
    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals()
    // here
    // anymore, verify the contents of the patientsList using
    // ExceptionalCareAdmissions.toString()
    {
      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.RED), 0);


      String expected = "21201: 12M (RED)";
      String actual = test.toString();
      if (!(actual.trim().equals(expected))) {
        return false;
      }
    }
    // (2) add a new patient to the end of the list
    {
      // Reset the patient counter so this tester method can be run independently
      PatientRecord.resetCounter();

      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(3);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.RED), 0);
      test.addPatient(new PatientRecord('F', 23, PatientRecord.RED), 1);
      test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);



      String expected = "21201: 12M (RED)\n12302: 23F (RED)\n40103: 1Q (GREEN)";
      String actual = test.toString();
      if (!(actual.trim().equals(expected))) {
        return false;
      }
    }
    // (3) add a new patient to the beginning of the list
    {
      // Reset the patient counter so this tester method can be run independently
      PatientRecord.resetCounter();

      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(3);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
      test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
      test.addPatient(new PatientRecord('Q', 01, PatientRecord.RED), 0);



      String expected = "40103: 1Q (RED)\n21201: 12M (YELLOW)\n12302: 23F (YELLOW)";
      String actual = test.toString();
      if (!(actual.trim().equals(expected))) {
        return false;
      }
    }
    return true;
  }

  /**
   * This test method is provided for you in its entirety, to give you a model for
   * verifying a method which throws exceptions. This method tests addPatient() with two
   * different, full patientsList arrays; one contains seen patients and one does not.
   * 
   * We assume for the purposes of this method that the ExceptionalCareAdmissions
   * constructor and PatientRecord constructor are working properly.
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

    // if we've gotten this far, we haven't failed either of the scenarios, so our test
    // passes!
    return true;
  }

  public static boolean testGetIndexValid() {
    // create an Admissions object and add a few Records to it, leaving some space

    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
    test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);


    // (1) get the index of a PatientRecord that should go at the END
    {
      PatientRecord testRecord = new PatientRecord('X', 24, PatientRecord.GREEN);
      int expected = 4;
      int actual = test.getAdmissionIndex(testRecord);
      if (expected != actual) {
        return false;
      }
    }
    // (2) get the index of a PatientRecord that should go at the BEGINNING
    {
      PatientRecord testRecord = new PatientRecord('X', 24, PatientRecord.RED);
      int expected = 0;
      int actual = test.getAdmissionIndex(testRecord);
      if (expected != actual) {
        return false;
      }
    }

    // (3) get the index of a PatientRecord that should go in the MIDDLE
    {
      PatientRecord testRecord = new PatientRecord('X', 24, PatientRecord.YELLOW);
      int expected = 2;
      int actual = test.getAdmissionIndex(testRecord);
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }

  public static boolean testGetIndexError() {
    // create an Admissions object and add Records to it until it is full, as in
    // testAddPatientError

    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(4);
    test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

    // (1) verify the exception when there are no patients who have been seen in the list
    {
      try {
        PatientRecord testRecord = new PatientRecord('X', 24, PatientRecord.YELLOW);

        int index = test.getAdmissionIndex(testRecord);

      } catch (IllegalStateException e) {
        String actual = e.getMessage();
        String expected = "Cannot admit new patients";
        if (!(actual.trim().equals(expected))) {
          return false;
        }

      }
    }
    // (2) verify the exception when there is at least one patient who has been seen
    {
      try {
        test.seePatient(12302);
        PatientRecord testRecord = new PatientRecord('X', 24, PatientRecord.YELLOW);

        int index = test.getAdmissionIndex(testRecord);

      } catch (IllegalStateException e) {
        String actual = e.getMessage();
        String expected = "cleanPatientsList()";
        if (!(actual.trim().equals(expected))) {
          return false;
        }

      }
    }


    return true;
  }

  public static boolean testAdmissionsBasicAccessors() {

    // (1) verify isFull() on a non-full and a full Admissions object
    {
      // Reset the patient counter so this tester method can be run independently

      PatientRecord.resetCounter();

      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
      test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
      test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
      test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

      boolean expected = false;
      boolean actual = test.isFull();
      if (expected != actual) {
        return false;
      }

    }
    {
      // Reset the patient counter so this tester method can be run independently

      PatientRecord.resetCounter();

      ExceptionalCareAdmissions testFull = new ExceptionalCareAdmissions(4);
      testFull.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
      testFull.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
      testFull.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
      testFull.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

      boolean expected = true;
      boolean actual = testFull.isFull();
      if (expected != actual) {
        return false;
      }

    }
    // (2) verify size() before and after adding a PatientRecord
    {
      // Reset the patient counter so this tester method can be run independently

      PatientRecord.resetCounter();

      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
      test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
      test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
      test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

      boolean expected = false;
      boolean actual = test.isFull();
      if (expected != actual) {
        return false;
      }

    }
    // (3) verify getNumberSeenPatients() before and after seeing a patient
    // (see testAddPatientError for a model of how to do this while bypassing
    // seePatient())

    {
      PatientRecord.resetCounter();

      ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);
      test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
      test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
      test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
      test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

      boolean expected = false;
      boolean actual = test.isFull();
      if (expected != actual) {
        return false;
      }

    }
    return true;
  }

  public static boolean testSeePatientValid() {
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references

    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);

    PatientRecord copyRecord = new PatientRecord('M', 12, PatientRecord.YELLOW);

    test.addPatient(copyRecord, 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    {
      test.seePatient(21201);

      boolean expected = true;
      boolean actual = copyRecord.hasBeenSeen();
      if (expected != actual) {
        return false;
      }
    }

    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    {
      test.seePatient(40103);

      int expected = 2;
      int actual = test.getNumberSeenPatients();
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }

  public static boolean testSeePatientError() {

    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);

    test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

    // (1) verify that seeing a caseID for a patient not in the list causes an
    // IllegalArgumentException

    {
      try {
        test.seePatient(00000);
      } catch (IllegalArgumentException e) {
      } catch (Exception e) {
        return false;
      }

    }
    return true;
  }

  public static boolean testGetSummary() {
    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);

    test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

    // (1) choose one getSummary() test from P01; this method has not changed much

    {
      String expected =
          "Total number of patients: 4\nTotal number seen: 0\nRED: 0\nYELLOW: 2\nGREEN: 2";
      String actual = test.getSummary();
      if ((!actual.trim().equals(expected))) {
        return false;
      }

    }
    return true;
  }

  public static boolean testCleanList() {
    // create an Admissions object and add a few Records to it

    // Reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(5);

    test.addPatient(new PatientRecord('M', 12, PatientRecord.YELLOW), 0);
    test.addPatient(new PatientRecord('F', 23, PatientRecord.YELLOW), 1);
    test.addPatient(new PatientRecord('Q', 01, PatientRecord.GREEN), 2);
    test.addPatient(new PatientRecord('M', 19, PatientRecord.GREEN), 3);

    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO
    // seen
    // patients does not change after calling cleanPatientsList()
    {
      try {
        File file = new File("./output.txt");
        test.cleanPatientsList(file);

        String expected =
            "21201: 12M (YELLOW)\n12302: 23F (YELLOW)\n40103: 1Q (GREEN)\n21904: 19M (GREEN)";
        String actual = test.toString();
        if (!(actual.trim().equals(expected))) {

          return false;
        }
      } catch (Exception e) {

        return false;
      }

    }

    // (2) call seePatient() for at least two of the records in your patientsList, and use
    // toString()
    // to verify that they have been removed after calling cleanPatientsList()
    {
      try {
        test.seePatient(21201);
        test.seePatient(12302);

        File file = new File("./output.txt");
        test.cleanPatientsList(file);

        String expected = "40103: 1Q (GREEN)\n21904: 19M (GREEN)";
        String actual = test.toString();

        if (!(actual.trim().equals(expected))) {

          return false;
        }
      } catch (Exception e) {
        return false;
      }

    }
    // NOTE: you do NOT need to verify file contents in this test method; please do so
    // manually
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result. Methods with two testers
   * have their output grouped for convenience; a failed test is displayed as "X" and a
   * passed test is displayed as "pass"
   * 
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out.println(
        "Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X")
            + ", " + (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", "
        + (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X")
        + ", " + (testGetIndexError() ? "pass" : "X"));
    System.out
        .println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", "
        + (testSeePatientError() ? "pass" : "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }

}
