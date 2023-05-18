// File header goes here


import java.io.File;

// Javadoc style class header goes here
public class ExceptionalCareTester2 {

  /**
   * This test method is provided for you in its entirety, to give you a model for testing
   * an instantiable class. This method verifies the correctness of your PatientRecord class.
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
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2) return false;
    }
    {
      // triage
      int expected1 = PatientRecord.YELLOW;
      int expected2 = PatientRecord.GREEN;
      if (test1.getTriage() != expected1 || test2.getTriage() != expected2) return false;
    }
    {
      // gender
      char expected1 = 'M';
      char expected2 = 'X';
      if (test1.getGender() != expected1 || test2.getGender() != expected2) return false;
    }
    {
      // age
      int expected1 = 17;
      int expected2 = 21;
      if (test1.getAge() != expected1 || test2.getAge() != expected2) return false;
    }
    {
      // orderOfArrival
      int expected1 = 1;
      int expected2 = 2;
      if (test1.getArrivalOrder() != expected1 ||
          test2.getArrivalOrder() != expected2) return false;
    }
    {
      // hasBeenSeen - try the mutator too
      if (test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
      test1.seePatient();
      if (!test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
    }

    // (3) verify their string representations
    {
      String expected1 = "21701: 17M (YELLOW)";
      String expected2 = "32102: 21X (GREEN)";
      if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2)) return false;
    }

    // (4) finally, verify that the constructor throws an exception for an invalid triage value
    try {
      new PatientRecord('F', 4, -17);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank()) return false;
    } catch (Exception e) {
      // incorrect exception type
      return false;
    }
    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }



  public static boolean testAdmissionsConstructorValid() {
    PatientRecord.resetCounter();

    // (1) verify that a normal, valid-input constructor call does NOT throw an exception
    try {
      ExceptionalCareAdmissions constructorTest1 = new ExceptionalCareAdmissions(5);
    }
    catch (Exception e) {
      return false;
    }
    // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
    // its toString() is an empty string
    PatientRecord.resetCounter();

    ExceptionalCareAdmissions constructorTest2 = new ExceptionalCareAdmissions(10);
    if (constructorTest2.size() != 0) {
      return false;
    } else if (constructorTest2.isFull()) {
      return false;
    } else if (constructorTest2.getNumberSeenPatients() != 0) {
      return false;
    } else if (!constructorTest2.toString().equals("")) {
      return false;
    }
    return true;
  }

  public static boolean testAdmissionsConstructorError() {
    PatientRecord.resetCounter();
    // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
    try {
      ExceptionalCareAdmissions constructorTest3 = new ExceptionalCareAdmissions(-5);
    }
    catch (IllegalArgumentException e) {
      if ((e.getMessage() == null) || (e.getMessage().isBlank())) {
        return false;
      }
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean testAddPatientValid() {
    PatientRecord.resetCounter();
    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
    // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
    ExceptionalCareAdmissions addTest1 = new ExceptionalCareAdmissions(10);
    PatientRecord patient1 = new PatientRecord('F', 21, PatientRecord.GREEN);
    addTest1.addPatient(patient1, 0);
    String checkAdd1 = "12101: 21F (GREEN)";
    if (!checkAdd1.equals(addTest1.toString())) {
      return false;
    }

    // (2) add a new patient to the end of the list
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions addTest2 = new ExceptionalCareAdmissions(4);
    PatientRecord add1 = new PatientRecord('M',30, PatientRecord.RED);
    PatientRecord add2 = new PatientRecord('F', 58, PatientRecord.YELLOW);
    PatientRecord add3 = new PatientRecord('X', 16, PatientRecord.GREEN);
    addTest2.addPatient(add1, 0);
    addTest2.addPatient(add2, 1);
    addTest2.addPatient(add3, 2);
    PatientRecord patient2 = new PatientRecord('F', 21, PatientRecord.GREEN);
    addTest2.addPatient(patient2, 3);
    String checkAdd2 = "23001: 30M (RED)\n15802: 58F (YELLOW)\n31603: 16X (GREEN)\n12104: 21F (GREEN)";
    if (!checkAdd2.equals(addTest2.toString())) {
      return false;
    }

    // (3) add a new patient to the beginning of the list
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions addTest3 = new ExceptionalCareAdmissions(4);
    PatientRecord add4 = new PatientRecord('M',30, PatientRecord.YELLOW);
    PatientRecord add5 = new PatientRecord('F', 58, PatientRecord.YELLOW);
    PatientRecord add6 = new PatientRecord('X', 16, PatientRecord.GREEN);
    addTest3.addPatient(add4, 0);
    addTest3.addPatient(add5, 1);
    addTest3.addPatient(add6, 2);
    PatientRecord patient3 = new PatientRecord('F', 21, PatientRecord.RED);
    int indexAdd = addTest3.getAdmissionIndex(patient3);
    addTest3.addPatient(patient3, indexAdd);
    String checkAdd3= "12104: 21F (RED)\n23001: 30M (YELLOW)\n15802: 58F (YELLOW)\n31603: 16X (GREEN)";
    if (!checkAdd3.equals(addTest3.toString())) {
      return false;
    }

    return true;
  }

  /*
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   *
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor
   * and PatientRecord constructor are working properly.
   *
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   *
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) a full Admissions object that contains no seen patients

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
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }

    // (2) a full Admissions object that contains at least one seen patient
    PatientRecord.resetCounter();

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
      if (!message.equals(expected)) return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  public static boolean testGetIndexValid() {
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it, leaving some space
    ExceptionalCareAdmissions indexCheck = new ExceptionalCareAdmissions(10);
    indexCheck.addPatient(new PatientRecord('M', 36, PatientRecord.YELLOW), 0);
    indexCheck.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    indexCheck.addPatient(new PatientRecord('M', 73, PatientRecord.GREEN), 2);
    indexCheck.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);

    // (1) get the index of a PatientRecord that should go at the END
    PatientRecord endPatient = new PatientRecord('M', 68, PatientRecord.GREEN);
    int indexEnd = indexCheck.getAdmissionIndex(endPatient);
    if (indexEnd != 4) {
      return false;
    }

    // (2) get the index of a PatientRecord that should go at the BEGINNING
    PatientRecord beginPatient = new PatientRecord('F', 66, PatientRecord.RED);
    int indexBegin = indexCheck.getAdmissionIndex(beginPatient);
    if (indexBegin != 0) {
      return false;
    }

    // (3) get the index of a PatientRecord that should go in the MIDDLE
    PatientRecord middlePatient = new PatientRecord('X', 43, PatientRecord.YELLOW);
    int indexMiddle = indexCheck.getAdmissionIndex(middlePatient);
    if (indexMiddle != 2) {
      return false;
    }

    return true;
  }

  public static boolean testGetIndexError() {
    PatientRecord.resetCounter();
    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    ExceptionalCareAdmissions indexError = new ExceptionalCareAdmissions(5);
    indexError.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    indexError.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    indexError.addPatient(new PatientRecord('M', 73, PatientRecord.YELLOW), 2);
    indexError.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);
    PatientRecord seenPatient = new PatientRecord('N', 45, PatientRecord.GREEN);
    indexError.addPatient(seenPatient, 4);

    // (1) verify the exception when there are no patients who have been seen in the list
    PatientRecord patientUnseen = new PatientRecord('F', 21, PatientRecord.RED);
    try {
      indexError.getAdmissionIndex(patientUnseen);
      return false;
    } catch (IllegalStateException e) {
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      return false;
    }

    // (2) verify the exception when there is at least one patient who has been seen

    seenPatient.seePatient();
    try {
      indexError.getAdmissionIndex(patientUnseen);
      return false;
    } catch (IllegalStateException e) {
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  public static boolean testAdmissionsBasicAccessors() {
    PatientRecord.resetCounter();
    // (1) verify isFull() on a non-full and a full Admissions object
    ExceptionalCareAdmissions indexFinal = new ExceptionalCareAdmissions(5);
    indexFinal.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    indexFinal.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    indexFinal.addPatient(new PatientRecord('M', 73, PatientRecord.YELLOW), 2);
    indexFinal.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);
    if (indexFinal.isFull()) {
      return false;
    }
    if (indexFinal.size() != 4) {
      return false;
    }
    PatientRecord seenPatient = new PatientRecord('F', 21, PatientRecord.GREEN);
    indexFinal.addPatient( seenPatient, 4);
    if (!indexFinal.isFull()) {
      return false;
    }
    if (indexFinal.size() != 5) {
      return false;
    }

    // (2) verify size() before and after adding a PatientRecord

    // (3) verify getNumberSeenPatients() before and after seeing a patient
    // (see testAddPatientError for a model of how to do this while bypassing seePatient())
    if (indexFinal.getNumberSeenPatients() != 0) {
      return false;
    }
    seenPatient.seePatient();
    if (indexFinal.getNumberSeenPatients() != 1) {
      return false;
    }

    return true;
  }

  public static boolean testSeePatientValid() {
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references
    ExceptionalCareAdmissions indexSee = new ExceptionalCareAdmissions(10);
    indexSee.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    indexSee.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    indexSee.addPatient(new PatientRecord('M', 73, PatientRecord.YELLOW), 2);
    indexSee.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);
    PatientRecord seenPatient = new PatientRecord('F', 21, PatientRecord.GREEN);
    indexSee.addPatient(seenPatient, 4);

    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    if (seenPatient.hasBeenSeen()) {
      return false;
    }
    indexSee.seePatient(12105);
    if (!seenPatient.hasBeenSeen()) {
      return false;
    }

    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    PatientRecord seenPatient2 = new PatientRecord('M', 102, PatientRecord.GREEN);
    indexSee.addPatient(seenPatient2, 5);
    if (indexSee.getNumberSeenPatients() != 1) {
      return false;
    }
    indexSee.seePatient(20206);
    if (indexSee.getNumberSeenPatients() != 2) {
      return false;
    }

    return true;
  }

  public static boolean testSeePatientError() {
    PatientRecord.resetCounter();
    // (1) verify that seeing a caseID for a patient not in the list causes an IllegalArgumentException
    ExceptionalCareAdmissions indexSeeError = new ExceptionalCareAdmissions(10);
    indexSeeError.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    indexSeeError.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    indexSeeError.addPatient(new PatientRecord('M', 73, PatientRecord.YELLOW), 2);
    indexSeeError.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);
    PatientRecord seenPatient = new PatientRecord('F', 21, PatientRecord.GREEN);
    try {
      indexSeeError.seePatient(12105);
      return false;
    }
    catch (IllegalArgumentException e) {
      String message = e.getMessage();
      String expected = "Error: caseID not found";
      if (!message.equals(expected))
        return false;
    }
    catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean testGetSummary() {
    PatientRecord.resetCounter();
    // (1) choose one getSummary() test from P01; this method has not changed much
    ExceptionalCareAdmissions summaryTest = new ExceptionalCareAdmissions(10);
    summaryTest.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    summaryTest.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    summaryTest.addPatient(new PatientRecord('M', 73, PatientRecord.YELLOW), 2);
    summaryTest.addPatient(new PatientRecord('X', 22, PatientRecord.GREEN), 3);
    String expectedSummary = "Total number of patients: 4\nTotal number seen: 0\nRED: 1\nYELLOW: 2\nGREEN: 1";
    String actualSummary = summaryTest.getSummary();
    if (expectedSummary.compareTo(actualSummary) != 0) {
      return false;
    }
    return true;
  }

  public static boolean testCleanList() {
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it
    ExceptionalCareAdmissions cleanTest = new ExceptionalCareAdmissions(10);
    cleanTest.addPatient(new PatientRecord('M', 36, PatientRecord.RED), 0);
    cleanTest.addPatient(new PatientRecord('F', 29, PatientRecord.YELLOW), 1);
    String List1 = cleanTest.toString();
    PatientRecord seenPatient1 = new PatientRecord('M', 73, PatientRecord.YELLOW);
    PatientRecord seenPatient2 = new PatientRecord('X', 22, PatientRecord.GREEN);
    cleanTest.addPatient(seenPatient1, 2);
    cleanTest.addPatient(seenPatient2, 3);

    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
    // patients does not change after calling cleanPatientsList()
    String List2 = cleanTest.toString();
    cleanTest.cleanPatientsList(new File("CleanList.txt"));
    String List3 = cleanTest.toString();
    if (!List2.equals(List3)) {
      return false;
    }

    // (2) call seePatient() for at least two of the records in your patientsList, and use toString()
    // to verify that they have been removed after calling cleanPatientsList()
    seenPatient1.seePatient();
    seenPatient2.seePatient();
    cleanTest.cleanPatientsList(new File("CleanList.txt"));
    String List4 = cleanTest.toString();
    if (!List4.equals(List1)) {
      return false;
    }

    // NOTE: you do NOT need to verify file contents in this test method; please do so manually
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have
   * their output grouped for convenience; a failed test is displayed as "X" and a passed test
   * is displayed as "pass"
   *
   * @param args unused
   * @author hobbes
   */

  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out.println("Admissions Constructor: " +
        (testAdmissionsConstructorValid() ? "pass" : "X") + ", " +
        (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", " +
        (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", " +
        (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", " +
        (testSeePatientError() ? "pass" : "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }

}

