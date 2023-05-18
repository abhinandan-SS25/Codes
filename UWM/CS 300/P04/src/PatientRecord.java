//////////////// FILE HEADER //////////////////////////
//
// Title: Methods for a PatientRecord object for holding patient details
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

/**
 * An instantiable class which models a patient's record for the admissions program. This class
 * automatically generates a caseID number from provided patient data, which is not exactly
 * HIPAA-compliant.
 */
public class PatientRecord {
  public static final int RED = 0; // One of the triage levels(highest).
  public static final int YELLOW = 1; // One of the triage levels(middle).
  public static final int GREEN = 2; // One of the triage levels(last).
  private static int patientCounter = 1; // Counts the number of patients created.
  public final int CASE_NUMBER; // The generated case number associated with this patient record.
  private int triage; // This patient's triage level, one of [RED, YELLOW, GREEN].
  private char gender; // This patient's single-character gender marker.
  private int age; // This patient's age.
  private int orderOfArrival; // The order in which this patient arrived; taken from the value of
                              // patientCounter when this record was created.
  private boolean hasBeenSeen; // Whether this patient has been marked as "seen".

  /**
   * Creates a new patient record and assigns it a CASE_NUMBER using the provided information. Be
   * careful to record the orderOfArrival before creating a CASE_NUMBER, as the counter will advance
   * when the static helper method generateCaseNumber() is called.
   *
   * @param gender - a single-character representation of this patient's reported gender
   * @param age    - a single-character representation of this patient's reported gender
   * @param triage - a single-character representation of this patient's reported gender
   * @throws IllegalArgumentException - with a descriptive error message if the provided triage
   *                                  value is not one of the class constants
   */
  public PatientRecord(char gender, int age, int triage) throws IllegalArgumentException {
    // Initiating fields
    this.gender = gender;
    this.age = age;
    this.orderOfArrival = patientCounter;
    this.CASE_NUMBER = generateCaseNumber(gender, age);
    if (triage != 0 && triage != 1 && triage != 2) {
      throw new IllegalArgumentException("Triage provided: " + triage
          + " is not a valid triage. Triage must be either, 0, 1 or 2.");
    } else {
      switch (triage) {
        case 0:
          this.triage = RED;
          break;
        case 1:
          this.triage = YELLOW;
          break;
        case 2:
          this.triage = GREEN;
          break;
      }
    }
    this.hasBeenSeen = false;
  }

  /**
   * Generates a five-digit case number for this patient using their reported gender and age. The
   * first digit of the case number is based on gender marker: F=1, M=2, X=3. Any other gender
   * marker should be assigned the first digit of 4. The next two digits are the last two digits of
   * the patient's age: 03 could mean a three-year-old or a 103-year-old. The last two digits
   * increment according to the number of patients admitted during this run of ExceptionalCare; the
   * first patient should be 01, counting up to 99, and then wrapping around to 00. Therefore, a
   * 27-year-old nonbinary person who is the 20th patient of the day would be 32720.
   *
   * @param gender - a single-character representation of this patient's reported gender
   * @param age    - the age of this patient in years
   * @return - a five-digit case number for the patient
   */
  public static int generateCaseNumber(char gender, int age) {
    // Creating a custom string and later converting to string
    String caseIdString = "";
    switch (gender) {
      case 'F':
        caseIdString = caseIdString + "1";
        break;
      case 'M':
        caseIdString = caseIdString + "2";
        break;
      case 'X':
        caseIdString = caseIdString + "3";
        break;
      default:
        caseIdString = caseIdString + "4";
    }

    // formatting the strings as per the requirements and concatenating
    caseIdString = caseIdString + String.format("%02d", age%100);
    caseIdString = caseIdString + String.format("%02d", patientCounter%100);

    patientCounter++;

    // Converting to integer
    return Integer.parseInt(caseIdString);
  }

  /**
   * For tester class purposes only: resents PatientRecord.patientCounter to 1. This method should
   * be called at the beginning of EACH tester method to ensure that the methods are not dependent
   * on being called in a particular order.
   */
  public static void resetCounter() {
    patientCounter = 1;
  }

  /**
   * Accessor method for triage
   *
   * @return - this PatientRecord's triage value
   */
  public int getTriage() {
    return this.triage;
  }

  /**
   * Accessor method for gender
   *
   * @return - this PatientRecord's gender marker
   */
  public char getGender() {
    return this.gender;
  }

  /**
   * this PatientRecord's gender marker
   *
   * @return - this PatientRecord's age value
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Accessor method for order of arrival
   *
   * @return - Accessor method for order of arrival
   */
  public int getArrivalOrder() {

    return this.orderOfArrival;
  }

  /**
   * Accessor method for hasBeenSeen
   *
   * @return - true if this patient has been seen, false otherwise
   */
  public boolean hasBeenSeen() {

    return this.hasBeenSeen;
  }

  /**
   * Marks this patient as having been seen. There is no way to undo this action.
   */
  public void seePatient() {

    this.hasBeenSeen = true;
  }

  /**
   * Creates and returns a String representation of this PatientRecord using its data field values:
   * [CASE_NUMBER]: [AGE][GENDER] ([TRIAGE]) Note that the [] are not actually included in the
   * result. For example, a 17-year-old male who was the first person to arrive and has triage level
   * YELLOW would have the toString() 21701: 17M (YELLOW)
   *
   * @return - a String representation of this PatientRecord
   */
  public String toString() {
    int patientTriage = getTriage();
    String triage = "";
    switch (patientTriage) {
      case 0:
        triage = "RED";
        break;
      case 1:
        triage = "YELLOW";
        break;
      case 2:
        triage = "GREEN";
        break;
    }
    // Creating the string representation of Patient record
    return "" + this.CASE_NUMBER + ": " + getAge() + getGender() + " (" + triage + ")";
  }

}
