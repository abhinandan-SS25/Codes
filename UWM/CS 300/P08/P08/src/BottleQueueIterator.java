//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Class and methods for Iterator for the Bottle queues
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not
 * empty, Bottle objects are iterated over from the front to the back of the queue. No more Bottle
 * objects are returned by this iterator when all the Bottle objects are traversed (returned). This
 * Iterator iterates over any queue which implements the QueueADT<Bottle> interface. It uses the
 * QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 */
public class BottleQueueIterator implements Iterator<Bottle> {

  private QueueADT<Bottle> bottleQueue; // Field to hold the internal copy of bottle queue

  /**
   * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
   * deep copy of the queue passed as input.
   *
   * @param queue The queue to iterate over, should implement the QueueADT interface.
   * @throws IllegalArgumentException when queue is null
   */
  public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
    if (queue == null) {
      throw new IllegalArgumentException("Queue is null.");
    }
    this.bottleQueue = queue.copy();
  }

  /**
   * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is 
   * not iterated over
   *
   * @return true if element after this exists
   */
  public boolean hasNext() {
    if (bottleQueue.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Returns the next bottle in the iteration
   *
   * @return Bottle The next bottle in the iteration
   * @throws NoSuchElementException if there are no more elements in the iteration
   */
  public Bottle next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return bottleQueue.dequeue();
  }
}
