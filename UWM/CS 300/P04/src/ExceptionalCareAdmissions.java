//////////////// FILE HEADER //////////////////////////
//
// Title: Methods for updating and maintaining patients in Exceptional Care
// Admissions Class
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
import java.io.PrintWriter;

/**
 * An object-oriented iteration of our UrgentCareAdmissions program, which performs its error
 * checking using exceptions instead of error codes. Patient records in the patientsList are now
 * single PatientRecord objects, containing the same data as before but with a few additional
 * components, namely: age, gender, and a boolean value indicating whether they have been seen by a
 * doctor. PatientRecords should still be maintained as an oversize array, such that a RED triage
 * level will always be before a YELLOW, which will always be before a GREEN. Within the same triage
 * level, patients appear in the order in which they were added to the list.
 */
public class ExceptionalCareAdmissions {
  private PatientRecord[] patientsList; // An oversize array containing the PatientRecords currently
                                        // active in this object
  private int size = 0; // The number of values in the oversize array

  /**
   * Creates a new, empty ExceptionalCareAdmissions object with the given capacity
   *
   * @param capacity - the maximum number of patient records this ExceptionalCareAdmissions object
   *                 can hold
   * @throws IllegalArgumentException - if the provided capacity is less than or equal to zero
   */
  public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Provided capacity less than or equal to 0");
    } else {
      this.patientsList = new PatientRecord[capacity];
    }
  }

  /**
   * This helper method will find the correct index to insert the new patient record into
   * patientsList, maintaining triage priority order. This should be the index AFTER the last index
   * currently occupied by a patient at that level. It uses objects and exceptions rather than
   * primitive values. For example, if the list currently contains: [RED, RED, GREEN, GREEN, GREEN,
   * GREEN, null, null, null, null] with a size of 6, a RED patient would be inserted at index 2, a
   * YELLOW patient would also be inserted at index 2, and a GREEN patient would be inserted at
   * index 6.
   *
   * @param rec - the PatientRecord to be added to the list
   * @return - the correct index of patientsList at which rec should be added
   * @throws IllegalStateException - if the patientsList is full: - with the message
   *                               "cleanPatientsList()" if the patientsList contains any patients
   *                               who have been seen, or - with the message "Cannot admit new
   *                               patients" if there are NO seen patients in the patientsList
   */
  public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {
    if (this.size == 0) {
      return 0; // First element is added to the beginning of the list
    } else if (this.isFull()) {
      // Throwing appropriate exception
      if (this.getNumberSeenPatients() > 0) {
        throw new IllegalStateException("cleanPatientsList()");
      } else if (this.getNumberSeenPatients() == 0) {
        throw new IllegalStateException("Cannot admit new patients");
      }
    } else {
      int triage = rec.getTriage();
      for (int i = 0; i < this.size; i++) {
        int curr_triage = this.patientsList[i].getTriage();
        if (triage < curr_triage) {
          return i;
        }
      }
    }
    return this.size;
  }

  /**
   * Adds the provided PatientRecord at the provided position in the oversize patientsList array.
   * This method must maintain the ordering of the patientsList as before, and rather than returning
   * the new size, maintains the size field in this ExceptionalCareAdmissions object appropriately.
   *
   * @param rec   - the PatientRecord to be added
   * @param index - the index at which the PatientRecord should be added to patientsList, which you
   *              may assume is the same as the output of getAdmissionIndex()
   * @throws IllegalStateException-   if the patientsList is full: - with the message
   *                                  "cleanPatientsList()" if the patientsList contains any
   *                                  patients who have been seen, - with the message "Cannot admit
   *                                  new patients" if there are NO seen patients in the
   *                                  patientsList
   * @throws IllegalArgumentException - with a descriptive error message if the patientsList is NOT
   *                                  full and the index is not a valid index into the oversize
   *                                  array
   */
  public void addPatient(PatientRecord rec, int index)
      throws IllegalStateException, IllegalArgumentException {
    // Throwing appropriate exception
    if (this.isFull()) {
      if (this.getNumberSeenPatients() > 0) {
        throw new IllegalStateException("cleanPatientsList()");
      } else if (this.getNumberSeenPatients() == 0) {
        throw new IllegalStateException("Cannot admit new patients");
      }
    } else {
      if (index < 0 || index > this.size) {
        throw new IllegalArgumentException("Provided index: " + index
            + ", cannot be less than 0 or greater than the capacity of patients");
      }
    }
    // Shifting elements in higher index than that provided to make room for provided element
    for (int i = this.size; i > index; i--) {
      this.patientsList[i] = this.patientsList[i - 1];
    }
    // Adding provided element
    this.patientsList[index] = rec;
    this.size++;
  }

  /**
   * Marks the patient with the given caseID as having been seen. This method requires thato the
   * patient with the given caseID within the patientsList is found before their status is modified.
   * This method may only modify one PatientRecord, and may not modify the patientsList array or its
   * size.
   *
   * @param caseID - the CASE_NUMBER of the PatientRecord to be marked as having been seen
   * @throws IllegalStateException    - if the patientsList is currently empty
   * @throws IllegalArgumentException - if no PatientRecord with the given caseID is found
   */
  public void seePatient(int caseID) throws IllegalStateException, IllegalArgumentException {
    // Throwing appropriate error
    if (this.size == 0) {
      throw new IllegalStateException("Cannot mark seen: There are currently no patients");
    } else {
      // Marks patient as seen if found by caseID
      for (int i = 0; i < this.size; i++) {
        if (this.patientsList[i] != null) {
          if (this.patientsList[i].CASE_NUMBER == caseID) {
            this.patientsList[i].seePatient();
            return;
          }
        }
      }
      // Throws error if patient of caseID not found
      throw new IllegalArgumentException("Provided case ID: " + caseID + ": does not exist");
    }
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList, incorporating an
   * additional component from the PatientRecord class. See below for details. Total number of
   * patients: 5 Total number seen: 3 RED: 1 YELLOW: 3 GREEN: 1 The first line displays the current
   * size of the array. The next displays the current number of patients who have been seen already,
   * followed by the number of patients at each triage level. Any of these numbers may be 0. This
   * method does not modify the patientsList array or its size in any way.
   *
   * @returna - String summarizing the patientsList as shown in this comment
   */
  public String getSummary() {
    int red = 0;
    int yellow = 0;
    int green = 0;
    for (int i = 0; i < this.size; i++) {
      // Counts number of each triage present in List
      switch (patientsList[i].getTriage()) {
        case 0:
          red++;
          break;
        case 1:
          yellow++;
          break;
        case 2:
          green++;
          break;
      }
    }
    return "Total number of patients: " + this.size + "\nTotal number seen: "
        + this.getNumberSeenPatients() + "\nRED: " + red + "\nYELLOW: " + yellow + "\nGREEN: "
        + green;
  }

  /**
   * This method runs occasionally to record the current state of the patientsList and save any
   * records for seen patients to a file, while removing them from the patientsList to make more
   * room. Every output file begins with the current summary of the patientsList, followed by the
   * string representation of each SEEN patient on a single line (PatientRecord's toString() method
   * will be helpful here). The patient records do not need to be in any particular order. If there
   * are NO seen patients when this method is called, the file will contain only the patientsList
   * summary (with "Total number seen: 0" as a part of it). If the provided file cannot be written
   * to, do not modify the patientsList in any way and just return from the method. By the end of
   * this method, all SEEN patients should be recorded in the file and removed from the patientsList
   * array, and the array's size should be updated accordingly.
   *
   * @param file - the file object to use for recording the data
   */
  public void cleanPatientsList(File file) {
    try {
      // Creates writer to write to files
      PrintWriter writer = new PrintWriter(file);
      writer.println(this.getSummary());
      if (this.patientsList != null) {
        int i = 0;
        while (i < this.size) {
          if (this.patientsList[i] != null) {
            if (this.patientsList[i].hasBeenSeen()) {
              writer.println(this.patientsList[i].toString());
              // Shifts all elements after the seen patient forward so that the patient is removed
              // and
              // list is accordingly updated
              for (int j = i + 1; j < size; j++) {
                this.patientsList[j - 1] = this.patientsList[j];
              }
              this.patientsList[size - 1] = null;
              size--;
            } else {
              i++;
            }
          }

          else {
            i++;
          }
        }
      }
      writer.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }
  }

  /**
   * An accessor method to determine if the patientsList is currently full
   *
   * @return - true if the patientsList is full, false otherwise
   */
  public boolean isFull() {
    if (this.patientsList.length == this.size) {
      return true;
    }
    return false;
  }

  /**
   * Accesses the current number of patient records in the patientsList
   *
   * @return - Accesses the current number of patient records in the patientsList
   */
  public int size() {
    return this.size;
  }

  /**
   * Accesses the current number of patient records in this patientsList representing patients who
   * have already been seen (and could be removed from the list)
   *
   * @return - the current count of patientRecords for which the hasBeenSeen() method returns true
   */
  public int getNumberSeenPatients() {
    int count = 0;
    if (this.patientsList != null) {
      for (int i = 0; i < this.size; i++) {
        PatientRecord record = this.patientsList[i];
        if (record != null) {
          if (record.hasBeenSeen()) {
            count++;
          }
        }
      }

    }
    return count;
  }

  /**
   * For testing purposes: this method creates and returns a string representation of the
   * patientsList, as the in-order string representation of each patient in the list on a separate
   * line. If patientsList is empty, returns an empty string.
   *
   * @return a string representation of the contents of patientsList
   */
  @Override
  public String toString() {
    String returnValue = "";
    for (PatientRecord r : patientsList) {
      returnValue += (r != null) ? r.toString() + "\n" : "";
    }
    return returnValue.trim();
  }
}
