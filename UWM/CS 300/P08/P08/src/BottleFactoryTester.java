//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tester for the Files and classes and methods
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// No Pair Programing
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help taken
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {
    // tests constructor
    {
      {
        // null as color
        try {
          Bottle bottle_e1 = new Bottle(null);
        } catch (IllegalArgumentException i) {

        } catch (Exception e) {
          return false;
        }
      }
      {
        // errenous color
        try {
          Bottle bottle_e2 = new Bottle("");
        } catch (IllegalArgumentException i) {

        } catch (Exception e) {
          return false;
        }
      }
      {
        // errenous color
        try {
          Bottle bottle_e3 = new Bottle(" ");
        } catch (IllegalArgumentException i) {

        } catch (Exception e) {
          return false;
        }
      }
    }

    // test equals method
    {
      Bottle.resetBottleCounter();
      Bottle bottle1 = new Bottle("Green");
      Bottle.resetBottleCounter();
      Bottle bottle2 = new Bottle("Green");
      Bottle bottle3 = new Bottle("Red");

      if (!bottle1.equals(bottle2)) {
        return false;
      }
      if (bottle1.equals(bottle3)) {
        return false;
      }
    }
    // test toString method
    {
      Bottle.resetBottleCounter();
      Bottle bottle1 = new Bottle("Green");

      String expected = "SN1Green:Empty:Open";
      String actual = bottle1.toString();

      if (!actual.equals(expected)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean linkedBottleQueueTester() {
    Bottle.resetBottleCounter();

    // test constructor - verify fields and exception behavior (when capacity is
    // invalid)

    {
        Bottle.resetBottleCounter();
        try {
          LinkedBottleQueue queue = new LinkedBottleQueue(0);
          return false;
        }
        catch (IllegalArgumentException i) {

        }
        catch (Exception e) {
          return false;
        }
        try {
          LinkedBottleQueue queue = new LinkedBottleQueue(-2);
          return false;
        }
        catch (IllegalArgumentException i) {

        }
        catch (Exception e) {
          return false;
        }
        LinkedBottleQueue queue = new LinkedBottleQueue(4);

        if (queue.size()!=0 && !queue.isEmpty()) {
          return  false;
        }
    }
    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all methods on an empty
     * queue
     */
    // enqueue on empty queue
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(6);

      Bottle bottle1 = new Bottle("Pink");
      queue.enqueue(bottle1);

      if (queue.size() != 1 && !queue.toString().equals(bottle1.toString())) {
        return false;
      }
    }
    // peek on empty queue
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(6);

      try {
        Bottle b = queue.peek();
        return false;
      } catch (NoSuchElementException e) {

      } catch (Exception e) {
        return false;
      }
    }
    // dequeue on empty queue
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(6);

      if (queue.size() == 0 && !queue.isEmpty() && queue.isFull()) {
        return false;
      }

      try {
        Bottle b = queue.dequeue();
        return false;
      } catch (NoSuchElementException e) {

      } catch (Exception e) {
        return false;
      }
    }
    /*
     * 2) all methods on a full queue
     */
    {
      Bottle.resetBottleCounter();
      // enqueue on full queue
      LinkedBottleQueue queue = new LinkedBottleQueue(3);

      Bottle bottle1 = new Bottle("Pink");
      Bottle bottle2 = new Bottle("Green");
      Bottle bottle3 = new Bottle("Red");
      queue.enqueue(bottle1);
      // null bottle passed
      try {
        queue.enqueue(null);
        return false;
      } catch (NullPointerException n) {

      } catch (Exception e) {
        return false;
      }

      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      if (queue.size() != 3
          && !queue.toString()
              .equals(bottle1.toString() + "\n" + bottle2.toString() + "\n" + bottle3.toString())
          && !queue.isFull() && queue.isEmpty()) {
        return false;
      }

      // enqueue on full queue
      Bottle bottle4 = new Bottle("T");

      try {
        queue.enqueue(bottle4);
      } catch (IllegalStateException i) {
      } catch (Exception e) {
        return false;
      }
    }
    {
      Bottle.resetBottleCounter();
      // Peek on full queue
      LinkedBottleQueue queue = new LinkedBottleQueue(3);

      Bottle bottle1 = new Bottle("Pink");
      Bottle bottle2 = new Bottle("Green");
      Bottle bottle3 = new Bottle("Red");
      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      try {
        Bottle b = queue.peek();
        if (!b.toString().equals(bottle1.toString()) && queue.size() != 3) {
            return false;
        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {
        return false;
      }
    }
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(3);

      Bottle bottle1 = new Bottle("Pink");
      Bottle bottle2 = new Bottle("Green");
      Bottle bottle3 = new Bottle("Red");
      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      if (!queue.dequeue().equals(bottle1)
          && !queue.toString().equals("" + bottle2.toString() + "\n" + bottle3.toString())
          && queue.size() != 2) {
        return false;
      }
    }
    /*
     * 3) all methods on a partially filled queue
     */
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(5);

      Bottle bottle1 = new Bottle("Pink");
      Bottle bottle2 = new Bottle("Green");
      Bottle bottle3 = new Bottle("Red");
      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      // enqueue
      Bottle bottle4 = new Bottle("bottle");
      queue.enqueue(bottle4);

      if (queue.size() != 4 && !queue.toString().equals("" + bottle1.toString() + "\n"
          + bottle2.toString() + "\n" + bottle3.toString() + "\n" + bottle4.toString())) {
        return false;
      }
      // peek
      if (!queue.peek().equals(bottle1) && queue.size() != 4) {
        return false;
      }
      // dequeue
      Bottle b = queue.dequeue();
      Bottle b1 = queue.dequeue();

      if (queue.size() != 2 && !b.equals(bottle1) && !b1.equals(bottle2)
          && queue.toString().equals("" + bottle3 + "\n" + bottle4)) {
        return false;
      }
    }
    /*
     * 4) Verify queue contents (using peek and size) after a sequence of enqueue-dequeue and
     * dequeue-enqueue
     */
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(5);
      Bottle bottle1 = new Bottle("Green");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Red");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      if (queue.size() != 3) {
        return false;
      }
      queue.dequeue();
      if (queue.size() != 2) {
        return false;
      }

      Bottle expectedPeek = bottle2;
      Bottle actualPeek = queue.peek();

      if (!expectedPeek.equals(actualPeek)) {
        return false;
      }
    }
    /*
     * 5) Enqueue until queue is full and dequeue until queue is empty
     */
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(4);

      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Green");
      Bottle b3 = new Bottle("Blue");
      Bottle b4 = new Bottle("Yellow");

      // enqueue till full
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);
      queue.enqueue(b4);

      if (!queue.isFull()
          && !queue.toString().equals("" + b1 + "\n" + b2 + "\n" + b3 + "\n" + b4)) {
        return false;
      }

      Bottle b5 = queue.dequeue();
      Bottle b6 = queue.dequeue();
      Bottle b7 = queue.dequeue();
      Bottle b8 = queue.dequeue();

      if (!queue.isEmpty() && !queue.toString().equals("") && !b5.equals(b1) && !b6.equals(b2)
          && !b7.equals(b3) && !b8.equals(b4)) {
        return false;
      }
    }
    // test copy method
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(6);
      Bottle bottle1 = new Bottle("Blue");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Green");
      Bottle bottle4 = new Bottle("Yellow");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.dequeue();
      queue.enqueue(bottle4);

      LinkedBottleQueue copyQueue = (LinkedBottleQueue) queue.copy();

      if (!copyQueue.toString().equals(queue.toString())) {
        return false;
      }

      queue.dequeue();
      if (copyQueue.toString().equals(queue.toString())) {
        return false;
      }
    }

    // test toString method
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(6);
      Bottle bottle1 = new Bottle("Blue");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Green");
      Bottle bottle4 = new Bottle("Yellow");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.dequeue();
      queue.enqueue(bottle4);

      String expected = bottle2.toString() + "\n" + bottle3.toString() + "\n" + bottle4.toString();
      String actual = queue.toString();

      if (!expected.equals(actual)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue clas
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {

    // test constructor - verify fields and exception behavior
    {
      CircularBottleQueue queue = new CircularBottleQueue(5);
      if (!queue.isEmpty() && queue.isFull()) {
        return false;
      }
      if (queue.size() != 0) {
        return false;
      }

      try {
        CircularBottleQueue queue1 = new CircularBottleQueue(0);
      } catch (IllegalArgumentException i) {
      } catch (Exception e) {
        return false;
      }
    }

    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all 3 methods on an empty
     * queue
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);

      Bottle b = new Bottle("Red");

      queue.enqueue(b);

      if (!queue.toString().equals("" + b) && queue.size() != 1) {
        return false;
      }

    }
    // check peek on empty queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);

      try {
        queue.peek();
        return false;
      } catch (NoSuchElementException n) {

      } catch (Exception e) {
        return false;
      }
      if (!queue.toString().equals("")) {
        return false;
      }
    }
    // check dequeue on empty queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);

      try {
        queue.dequeue();
        return false;
      } catch (NoSuchElementException n) {

      } catch (Exception e) {
        return false;
      }
    }
    /*
     * 2) all 3 methods on a full queue
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      Bottle b4 = new Bottle("Red");

      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);
      queue.enqueue(b4);

      if (!queue.toString().equals("" + b + "\n" + b1 + "\n" + b2 + "\n" + b3 + "\n" + b4)
          && !queue.isFull()) {
        return false;
      }

      try {
        queue.enqueue(new Bottle("l"));
        return false;
      } catch (IllegalStateException i) {

      } catch (Exception e) {
        return false;
      }
    }
    // peek on full queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(4);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      String initial = queue.toString();
      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);

      Bottle b4 = queue.peek();

      if (!b4.equals(b) && !queue.isFull() && queue.size() != 4
          && !queue.toString().equals(initial)) {
        return false;
      }
    }
    // dequeue on full queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(4);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);

      Bottle b4 = queue.dequeue();

      if (!b4.equals(b) && queue.isFull() && queue.size() != 3
          && !queue.toString().equals("" + b1 + "\n" + b2 + "\n" + "b3")) {
        return false;
      }
    }
    /*
     * 3) all 3 methods on a partially filled queue
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(8);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      Bottle b4 = new Bottle("Red");
      Bottle b5 = new Bottle("Red");

      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);
      queue.enqueue(b4);

      queue.enqueue(b5);

      if (!queue.toString()
          .equals("" + b + "\n" + b1 + "\n" + b2 + "\n" + b3 + "\n" + b4 + "\n" + b5)
          && queue.size() != 6) {
        return false;
      }
    }
    // peek on queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(6);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      String initial = queue.toString();
      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);

      Bottle b4 = queue.peek();

      if (!b4.equals(b) && queue.size() != 4 && !queue.toString().equals(initial)) {
        return false;
      }
    }
    // dequeue on full queue
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(6);

      Bottle b = new Bottle("Red");
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Red");
      queue.enqueue(b);
      queue.enqueue(b1);
      queue.enqueue(b2);
      queue.enqueue(b3);

      Bottle b4 = queue.dequeue();

      if (!b4.equals(b) && queue.isFull() && queue.size() != 3
          && !queue.toString().equals("" + b1 + "\n" + b2 + "\n" + "b3")) {
        return false;
      }
    }
    /*
     * 4) Verify queue contents and size after a sequence of enqueue-dequeue and dequeue-enqueue
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(6);
      Bottle bottle1 = new Bottle("Blue");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Green");
      Bottle bottle4 = new Bottle("Green");
      Bottle bottle5 = new Bottle("Green");
      Bottle bottle6 = new Bottle("Red");
      Bottle bottle7 = new Bottle("Yelow");
      Bottle bottle8 = new Bottle("Yelow");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.enqueue(bottle4);
      queue.dequeue();
      queue.enqueue(bottle5);
      queue.enqueue(bottle6);
      queue.enqueue(bottle7);
      queue.dequeue();
      queue.dequeue();
      queue.enqueue(bottle8);

      if (queue.size() != 5 && !queue.peek().equals(bottle4) && !queue.toString().equals(
          "" + bottle4 + "\n" + bottle5 + "\n" + bottle6 + "\n" + bottle7 + "\n" + bottle8)) {
        return false;
      }
    }
    /*
     * 5) Enqueue until queue is full and dequeue until queue is empty
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(3);

      try {
        Bottle b1 = new Bottle("Red");
        Bottle b2 = new Bottle("Red");
        Bottle b3 = new Bottle("Red");

        queue.enqueue(b1);
        queue.enqueue(b2);
        queue.enqueue(b3);

        if (!queue.isFull() && !queue.peek().equals(b1)) {
          return false;
        }

        Bottle b4 = queue.dequeue();
        Bottle b5 = queue.dequeue();
        Bottle b6 = queue.dequeue();

        if (!queue.isEmpty() && !b4.equals(b3) && !b5.equals(b2) && !b6.equals(b3)) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }
    // test copy method
    {
      CircularBottleQueue queue = new CircularBottleQueue(5);

      Bottle bottle1 = new Bottle("Green");
      Bottle bottle2 = new Bottle("Blue");
      Bottle bottle3 = new Bottle("Yellow");
      Bottle bottle4 = new Bottle("Yellow");
      Bottle bottle5 = new Bottle("Red");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.enqueue(bottle4);
      queue.dequeue();
      queue.enqueue(bottle5);

      String expected = bottle2.toString() + "\n" + bottle3.toString() + "\n" + bottle4.toString()
          + "\n" + bottle5.toString();
      CircularBottleQueue qCopy = (CircularBottleQueue) queue.copy();

      String actual = qCopy.toString();

      if (!expected.equals(actual)) {
        return false;
      }

      queue.dequeue();
      if (queue.toString().equals(qCopy.toString())) {
        return false;
      }
    }

    // test toString method
    {
      CircularBottleQueue queue = new CircularBottleQueue(5);

      Bottle bottle1 = new Bottle("Green");
      Bottle bottle2 = new Bottle("Blue");
      Bottle bottle3 = new Bottle("Yellow");
      Bottle bottle4 = new Bottle("Yellow");
      Bottle bottle5 = new Bottle("Red");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.enqueue(bottle4);
      queue.dequeue();
      queue.enqueue(bottle5);

      String expected = bottle2.toString() + "\n" + bottle3.toString() + "\n" + bottle4.toString()
          + "\n" + bottle5.toString();
      String actual = queue.toString();

      if (!expected.equals(actual)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {

    /*
     * test 01: Create a LinkedBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */
    {
      LinkedBottleQueue queue = new LinkedBottleQueue(6);
      Bottle bottle1 = new Bottle("Blue");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Green");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);

      Bottle[] expected = new Bottle[] {bottle1, bottle2, bottle3};
      int i = 0;
      for (Bottle b : queue) {
        if (!b.equals(expected[i])) {
          return false;
        }
        i++;
      }

      if (queue.toString().equals("")) {
        return false;
      }
    }

    /*
     * test 02: Create a CircularBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */
    {
      CircularBottleQueue queue = new CircularBottleQueue(6);
      Bottle bottle1 = new Bottle("Blue");
      Bottle bottle2 = new Bottle("Red");
      Bottle bottle3 = new Bottle("Green");
      Bottle bottle4 = new Bottle("Green");
      Bottle bottle5 = new Bottle("Green");
      Bottle bottle6 = new Bottle("Red");
      Bottle bottle7 = new Bottle("Yelow");
      Bottle bottle8 = new Bottle("Yelow");

      queue.enqueue(bottle1);
      queue.enqueue(bottle2);
      queue.enqueue(bottle3);
      queue.enqueue(bottle4);
      queue.dequeue();
      queue.enqueue(bottle5);
      queue.enqueue(bottle6);
      queue.enqueue(bottle7);
      queue.dequeue();
      queue.dequeue();
      queue.enqueue(bottle8);

      Bottle[] expected = new Bottle[] {bottle4, bottle5, bottle6, bottle7, bottle8};
      int i = 0;
      for (Bottle b : queue) {
        if (!b.equals(expected[i])) {
          return false;
        }
        i++;
      }

      if (queue.toString().equals("")) {
        return false;
      }
    }

    return true;
  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out
        .println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
