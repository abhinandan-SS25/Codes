import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the
 * implementation of classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester1 {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one
   *         bug is detected
   */
  public static boolean bottleTester() {

    // test equals method
    {
      Bottle.resetBottleCounter();
      Bottle b1 = new Bottle("RED");
      Bottle.resetBottleCounter();
      Bottle b2 = new Bottle("RED");

      boolean expected = true;
      boolean actual = b1.equals(b2);
      if (expected != actual) {
        return false;
      }
    }
    {
      Bottle.resetBottleCounter();
      Bottle b1 = new Bottle("RED");
      Bottle b2 = new Bottle("RED");

      boolean expected = false;
      boolean actual = b1.equals(b2);
      if (expected != actual) {
        return false;
      }
    }
    // test toString method
    {
      Bottle.resetBottleCounter();
      Bottle b1 = new Bottle("RED");
      {
        String expected = "SN1RED:Empty:Open";
        String actual = b1.toString();
        if (!expected.equals(actual)) {

          return false;
        }
      }
      b1.fillBottle();
      b1.sealBottle();
      {
        String expected = "SN1RED:Filled:Capped";
        String actual = b1.toString();
        if (!expected.equals(actual)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the
   * LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one
   *         bug is detected
   */
  public static boolean linkedBottleQueueTester() {

    // test constructor - verify fields and exception behavior (when capacity is invalid)
    {
      {
        // testing exceptions
        {
          // when capacity is 0
          try {
            LinkedBottleQueue errorBottleQueue = new LinkedBottleQueue(0);
            return false;
          } catch (IllegalArgumentException e) {

          } catch (Exception e) {
            return false;
          }
        }
        {
          // when capacity is negative
          try {
            LinkedBottleQueue errorBottleQueue = new LinkedBottleQueue(-5);
            return false;
          } catch (IllegalArgumentException e) {

          } catch (Exception e) {
            return false;
          }
        }
      }
      {
        LinkedBottleQueue queue = new LinkedBottleQueue(5);

        {
          // testing isEmpty
          boolean expected = true;
          boolean actual = queue.isEmpty();
          if (expected != actual) {
            return false;
          }
        }
        {
          // testing size
          int expected = 0;
          int actual = queue.size();
          if (expected != actual) {
            return false;
          }
        }
      }
    }
    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all methods on
     * an empty queue 2) all methods on a full queue 3) all methods on a partially filled
     * queue 4) Verify queue contents (using peek and size) after a sequence of
     * enqueue-dequeue and dequeue-enqueue 5) Enqueue until queue is full and dequeue
     * until queue is empty
     */
    {
      {
        // testing with empty queue
        Bottle.resetBottleCounter();
        LinkedBottleQueue emptyQueue = new LinkedBottleQueue(5);
        {
          // testing exceptions
          {
            // exception using dequeue on empty queue
            try {
              emptyQueue.dequeue();
              return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
              return false;
            }
          }
          {
            // exception using enqueue
            try {
              emptyQueue.enqueue(null);
              return false;
            } catch (NullPointerException e) {

            } catch (Exception e) {
              return false;
            }
          }
          {
            // exception using peek
            try {
              emptyQueue.peek();
              return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
              return false;
            }
          }
        }
        {
          // using enqueue
          emptyQueue.enqueue(new Bottle("RED"));
          {
            // testing using peek
            String actual = emptyQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 1;
            int actual = emptyQueue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // testing with full queue

        Bottle.resetBottleCounter();
        LinkedBottleQueue fullQueue = new LinkedBottleQueue(5);
        fullQueue.enqueue(new Bottle("RED"));
        fullQueue.enqueue(new Bottle("BLUE"));
        fullQueue.enqueue(new Bottle("GREEN"));
        fullQueue.enqueue(new Bottle("ORANGE"));
        fullQueue.enqueue(new Bottle("PINK"));

        {
          // testing exceptions
          {
            // exception using enqueue on full queue
            try {
              fullQueue.enqueue(new Bottle("ERROR"));
              return false;
            } catch (IllegalStateException e) {

            } catch (Exception e) {

              return false;
            }
          }
        }
        {
          {
            // testing using peek
            String actual = fullQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 5;
            int actual = fullQueue.size();
            if (expected != actual) {
              return false;
            }
          }
          {
            // testing dequeue
            String actual = fullQueue.dequeue().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing new peek
            String actual = fullQueue.peek().toString();
            String expected = "SN2BLUE:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
        }
      }
      {
        // testing with partial queue
        Bottle.resetBottleCounter();
        LinkedBottleQueue partialQueue = new LinkedBottleQueue(3);
        {
          // using enqueue
          partialQueue.enqueue(new Bottle("RED"));
          partialQueue.enqueue(new Bottle("BLUE"));

          {
            // testing using peek
            String actual = partialQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 2;
            int actual = partialQueue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // testing with queueing and dequeueing
        Bottle.resetBottleCounter();
        LinkedBottleQueue queue = new LinkedBottleQueue(3);
        {
          // using enqueue
          queue.enqueue(new Bottle("RED"));
          queue.enqueue(new Bottle("BLUE"));

          {
            // testing using peek
            String actual = queue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 2;
            int actual = queue.size();
            if (expected != actual) {
              return false;
            }
          }
          {
            // dequeueing
            String actual = queue.dequeue().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing using peek
            String actual = queue.peek().toString();
            String expected = "SN2BLUE:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 1;
            int actual = queue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // enqueueing and dequeueing to its limit
        Bottle.resetBottleCounter();
        LinkedBottleQueue queue = new LinkedBottleQueue(3);
        queue.enqueue(new Bottle("RED"));
        queue.enqueue(new Bottle("BLUE"));
        queue.enqueue(new Bottle("GREEN"));
        {
          // exception using enqueue on full queue
          try {
            queue.enqueue(new Bottle("ERROR"));
            return false;
          } catch (IllegalStateException e) {

          } catch (Exception e) {

            return false;
          }
        }
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        {
          // exception using dequeue on empty queue
          try {
            queue.dequeue();
            return false;
          } catch (NoSuchElementException e) {

          } catch (Exception e) {
            return false;
          }
        }

      }
    }
    // test copy method
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue originalQueue = new LinkedBottleQueue(5);
      originalQueue.enqueue(new Bottle("RED"));
      originalQueue.enqueue(new Bottle("BLUE"));
      originalQueue.enqueue(new Bottle("GREEN"));

      LinkedBottleQueue copyQueue = (LinkedBottleQueue) originalQueue.copy();

      // dequeueing from originalQueue
      originalQueue.dequeue();
      {
        // checking original queue
        {
          // testing using peek
          String actual = originalQueue.peek().toString();
          String expected = "SN2BLUE:Empty:Open";
          if (!actual.equals(expected)) {
            return false;
          }
        }
        {
          // testing size
          int expected = 2;
          int actual = originalQueue.size();
          if (expected != actual) {
            return false;
          }
        }
      }
      {
        // checking copy queue
        {
          // testing using peek
          String actual = copyQueue.peek().toString();
          String expected = "SN1RED:Empty:Open";
          if (!actual.equals(expected)) {
            System.out.println(actual);
            return false;
          }
        }
        {
          // testing size
          int expected = 3;
          int actual = copyQueue.size();
          if (expected != actual) {
            return false;
          }
        }
      }

    }
    // test toString method
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(5);
      queue.enqueue(new Bottle("RED"));
      queue.enqueue(new Bottle("BLUE"));
      queue.enqueue(new Bottle("GREEN"));
      {
        // testing toString
        String actual = queue.toString();
        String expected = "SN1RED:Empty:Open\nSN2BLUE:Empty:Open\nSN3GREEN:Empty:Open";
        if (!actual.equals(expected)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the
   * CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one
   *         bug is detected
   */
  public static boolean circularBottleQueueTester() {

    // test constructor - verify fields and exception behavior
    {
      {
        // testing exceptions
        {
          // when capacity is 0
          try {
            CircularBottleQueue errorBottleQueue = new CircularBottleQueue(0);
            return false;
          } catch (IllegalArgumentException e) {

          } catch (Exception e) {
            return false;
          }
        }
        {
          // when capacity is negative
          try {
            CircularBottleQueue errorBottleQueue = new CircularBottleQueue(-5);
            return false;
          } catch (IllegalArgumentException e) {

          } catch (Exception e) {
            return false;
          }
        }
      }
      {
        CircularBottleQueue queue = new CircularBottleQueue(5);

        {
          // testing isEmpty
          boolean expected = true;
          boolean actual = queue.isEmpty();
          if (expected != actual) {
            return false;
          }
        }
        {
          // testing size
          int expected = 0;
          int actual = queue.size();
          if (expected != actual) {
            return false;
          }
        }
      }
    }

    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all 3 methods
     * on an empty queue 2) all 3 methods on a full queue 3) all 3 methods on a partially
     * filled queue 4) Verify queue contents and size after a sequence of enqueue-dequeue
     * and dequeue-enqueue 5) Enqueue until queue is full and dequeue until queue is empty
     */
    {
      {
        // testing with empty queue
        Bottle.resetBottleCounter();
        CircularBottleQueue emptyQueue = new CircularBottleQueue(5);
        {
          // testing exceptions
          {
            // exception using dequeue on empty queue
            try {
              emptyQueue.dequeue();
              return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
              return false;
            }
          }
          {
            // exception using enqueue
            try {
              emptyQueue.enqueue(null);
              return false;
            } catch (NullPointerException e) {

            } catch (Exception e) {
              return false;
            }
          }
          {
            // exception using peek
            try {
              emptyQueue.peek();
              return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
              return false;
            }
          }
        }
        {
          // using enqueue
          emptyQueue.enqueue(new Bottle("RED"));
          {
            // testing using peek
            String actual = emptyQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 1;
            int actual = emptyQueue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // testing with full queue

        Bottle.resetBottleCounter();
        CircularBottleQueue fullQueue = new CircularBottleQueue(5);
        fullQueue.enqueue(new Bottle("RED"));
        fullQueue.enqueue(new Bottle("BLUE"));
        fullQueue.enqueue(new Bottle("GREEN"));
        fullQueue.enqueue(new Bottle("ORANGE"));
        fullQueue.enqueue(new Bottle("PINK"));

        {
          // testing exceptions
          {
            // exception using enqueue on full queue
            try {
              fullQueue.enqueue(new Bottle("ERROR"));
              return false;
            } catch (IllegalStateException e) {

            } catch (Exception e) {

              return false;
            }
          }
        }
        {
          {
            // testing using peek
            String actual = fullQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 5;
            int actual = fullQueue.size();
            if (expected != actual) {
              return false;
            }
          }
          {
            // testing dequeue
            String actual = fullQueue.dequeue().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing new peek
            String actual = fullQueue.peek().toString();
            String expected = "SN2BLUE:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
        }
      }
      {
        // testing with partial queue
        Bottle.resetBottleCounter();
        CircularBottleQueue partialQueue = new CircularBottleQueue(3);
        {
          // using enqueue
          partialQueue.enqueue(new Bottle("RED"));
          partialQueue.enqueue(new Bottle("BLUE"));

          {
            // testing using peek
            String actual = partialQueue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 2;
            int actual = partialQueue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // testing with queueing and dequeueing
        Bottle.resetBottleCounter();
        CircularBottleQueue queue = new CircularBottleQueue(3);
        {
          // using enqueue
          queue.enqueue(new Bottle("RED"));
          queue.enqueue(new Bottle("BLUE"));

          {
            // testing using peek
            String actual = queue.peek().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 2;
            int actual = queue.size();
            if (expected != actual) {
              return false;
            }
          }
          {
            // dequeueing
            String actual = queue.dequeue().toString();
            String expected = "SN1RED:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing using peek
            String actual = queue.peek().toString();
            String expected = "SN2BLUE:Empty:Open";
            if (!actual.equals(expected)) {
              return false;
            }
          }
          {
            // testing size
            int expected = 1;
            int actual = queue.size();
            if (expected != actual) {
              return false;
            }
          }
        }
      }
      {
        // enqueueing and dequeueing to its limit
        Bottle.resetBottleCounter();
        CircularBottleQueue queue = new CircularBottleQueue(3);
        queue.enqueue(new Bottle("RED"));
        queue.enqueue(new Bottle("BLUE"));
        queue.enqueue(new Bottle("GREEN"));
        {
          // exception using enqueue on full queue
          try {
            queue.enqueue(new Bottle("ERROR"));
            return false;
          } catch (IllegalStateException e) {

          } catch (Exception e) {

            return false;
          }
        }
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        {
          // exception using dequeue on empty queue
          try {
            queue.dequeue();
            return false;
          } catch (NoSuchElementException e) {

          } catch (Exception e) {
            return false;
          }
        }

      }
    }

    // test copy method
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue originalQueue = new CircularBottleQueue(5);
      originalQueue.enqueue(new Bottle("RED"));
      originalQueue.enqueue(new Bottle("BLUE"));
      originalQueue.enqueue(new Bottle("GREEN"));

      CircularBottleQueue copyQueue = (CircularBottleQueue) originalQueue.copy();

      // dequeueing from originalQueue
      originalQueue.dequeue();
      {
        // checking original queue
        {
          // testing using peek
          String actual = originalQueue.peek().toString();
          String expected = "SN2BLUE:Empty:Open";
          if (!actual.equals(expected)) {
            return false;
          }
        }
        {
          // testing size
          int expected = 2;
          int actual = originalQueue.size();
          if (expected != actual) {
            return false;
          }
        }
      }
      {
        // checking copy queue
        {
          // testing using peek
          String actual = copyQueue.peek().toString();
          String expected = "SN1RED:Empty:Open";
          if (!actual.equals(expected)) {
            System.out.println(actual);
            return false;
          }
        }
        {
          // testing size
          int expected = 3;
          int actual = copyQueue.size();
          if (expected != actual) {
            return false;
          }
        }
      }

    }
    // test toString method
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);
      queue.enqueue(new Bottle("RED"));
      queue.enqueue(new Bottle("BLUE"));
      queue.enqueue(new Bottle("GREEN"));
      {
        // testing toString
        String actual = queue.toString();
        String expected = "SN1RED:Empty:Open\nSN2BLUE:Empty:Open\nSN3GREEN:Empty:Open";
        if (!actual.equals(expected)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the
   * BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one
   *         bug is detected
   */
  public static boolean bottleQueueIteratorTester() {

    /*
     * test 01: Create a LinkedBottleQueue with at least n bottles and use the
     * bottleQueueIterator to traverse the queue. Verify if all the bottles are returned
     * correctly
     */
    {
      Bottle.resetBottleCounter();
      LinkedBottleQueue queue = new LinkedBottleQueue(5);
      queue.enqueue(new Bottle("RED"));
      queue.enqueue(new Bottle("BLUE"));
      queue.enqueue(new Bottle("GREEN"));
      queue.enqueue(new Bottle("PINK"));

      LinkedBottleQueue queueCopy = (LinkedBottleQueue) queue.copy();


      Iterator<Bottle> iterator = queue.iterator();
      Bottle.resetBottleCounter();
      Bottle[] expected = new Bottle[] {new Bottle("RED"), new Bottle("BLUE"),
          new Bottle("GREEN"), new Bottle("PINK")};

      Bottle[] actual = new Bottle[4];
      int actualCounter = 0;

      while (iterator.hasNext()) {
        actual[actualCounter] = iterator.next();
        actualCounter++;
      }

      if (queue.isEmpty() || !queue.toString().equals(queueCopy.toString())) {
        return false;
      }
      for (int i = 0; i < expected.length; i++) {
        if (!expected[i].toString().equals(actual[i].toString())) {
          return false;
        }
      }

    }

    /*
     * test 02: Create a CircularBottleQueue with at least n bottles and use the
     * bottleQueueIterator to traverse the queue. Verify if all the bottles are returned
     * correctly
     */
    {
      Bottle.resetBottleCounter();
      CircularBottleQueue queue = new CircularBottleQueue(5);
      queue.enqueue(new Bottle("RED"));
      queue.enqueue(new Bottle("BLUE"));
      queue.enqueue(new Bottle("GREEN"));
      queue.enqueue(new Bottle("PINK"));

      CircularBottleQueue queueCopy = (CircularBottleQueue) queue.copy();


      Iterator<Bottle> iterator = queue.iterator();
      Bottle.resetBottleCounter();
      Bottle[] expected = new Bottle[] {new Bottle("RED"), new Bottle("BLUE"),
          new Bottle("GREEN"), new Bottle("PINK")};

      Bottle[] actual = new Bottle[4];
      int actualCounter = 0;

      while (iterator.hasNext()) {
        actual[actualCounter] = iterator.next();
        actualCounter++;
      }

      if (queue.isEmpty() || !queue.toString().equals(queueCopy.toString())) {
        return false;
      }
      for (int i = 0; i < expected.length; i++) {
        if (!expected[i].toString().equals(actual[i].toString())) {
          return false;
        }
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
    System.out.println(
        "bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out.println(
        "linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println("circularBottleQueueTester: "
        + (circularBottleQueueTester() ? "Pass" : "Failed!"));

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
