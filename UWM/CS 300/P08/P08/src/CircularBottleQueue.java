//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Class and methods for the Bottle circular queues implementation
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
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  private Bottle[] bottles; // holds the array of bottles
  private int front;// front of the queue
  private int back;// back of the queue
  private int size;// size of the queue

  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows: the bottles
   * oversize array to an empty array of Bottle objects whose length is the input capacity, its siz
   * to zero, and both its front and back to -1.
   *
   * @param capacity defining the number of bottles the queue can hold
   * @throws IllegalArgumentException when capacity is not positive
   */
  public CircularBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive.");
    }
    this.front = -1;
    this.back = -1;
    this.bottles = new Bottle[capacity];
    this.size = 0;
  }

  /**
   * Returns the number of bottles in the queue
   *
   * @return size of the queue
   */
  @Override
  public int size() {
    // size = 0
    if (this.front == -1 && this.back == -1) {
      return 0;
    }
    // size 1
    if (this.front == this.back) {
      return 1;
    }
    // if front is before back
    else if (this.front < this.back) {
      int size = 0;
      for (int i = this.front; i <= this.back; i++) {
        size++;
      }
      return size;
    }
    // if front is after back in array
    else {
      int size = 0;
      // count elements from front to end of array
      for (int i = this.front; i < this.bottles.length; i++) {
        size++;
      }
      // count elements from beginning of array to back
      for (int j = 0; j <= this.back; j++) {
        size++;
      }
      return size;
    }
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @return true if full else false
   */
  @Override
  public boolean isFull() {
    if (this.size() != this.bottles.length) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @return true if empty else false
   */
  @Override
  public boolean isEmpty() {
    if (this.size() != 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle Bottle to add to the queue
   * @throws IllegalStateException when queue is full
   * @throws NullPointerException  when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (bottle == null) {
      throw new NullPointerException();
    }
    if (this.isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (this.isEmpty()) {
      this.front = 0;
      this.back = 0;
      this.bottles[0] = bottle;
      this.size++;
    } else {
      bottles[(back + 1) % this.bottles.length] = bottle;
      this.back = (back + 1) % this.bottles.length;
      this.size++;
    }
  }

  /**
   * Removes and returns the first bottle in the queue.
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queueu is empty.");
    }
    if (this.size == 1) {
      this.size--;
      Bottle curBack = bottles[this.front];
      bottles[this.front] = null;
      this.front = -1;
      this.back = -1;
      return curBack;
    }
    Bottle curBack = bottles[this.front];
    bottles[this.front] = null;
    this.front = (this.front + 1) % this.bottles.length;
    this.size--;

    return curBack;
  }

  /**
   * Returns an iterator to traverse the queue.
   *
   * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());
  }

  /**
   * Returns a deep copy of this Queue
   *
   * @return a deep copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    CircularBottleQueue bottles_copy = new CircularBottleQueue(this.bottles.length);

    Bottle cur = this.bottles[this.front];
    int index = this.front;
    while (index != this.back) {
      bottles_copy.bottles[index] = this.bottles[index];
      index = (index + 1) % this.bottles.length;
    }
    // sets the properties to the copy
    bottles_copy.bottles[this.back] = this.bottles[this.back];
    bottles_copy.front = this.front;
    bottles_copy.back = this.back;
    bottles_copy.size = this.size;
    return bottles_copy;
  }

  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    return this.bottles[this.front];
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ..
   * SN:Filled/Empty:Capped/Open The string should not contain a newline character at the end.
   *
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }

    Iterator queueIterator = this.iterator();

    Bottle currNode = (Bottle) queueIterator.next();

    String rep = "" + currNode.toString();

    while (queueIterator.hasNext()) {
      rep = rep + "\n" + queueIterator.next().toString();
    }

    return rep;
  }

}
