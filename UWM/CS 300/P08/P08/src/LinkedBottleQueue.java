//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Class and methods for the Bottle linked queues implementation
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
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and 
 * removed with respect to the First In First Out (FIFO) scheduling policy.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  private LinkedNode<Bottle> front; // front node of queue
  private LinkedNode<Bottle> back; // back node of queue
  private int size; // size of queue
  private int capacity; // capacity of the queue

  /**
   * Initializes the fields of this queue including its capacity. A newly created queue must be
   * empty, meaning that both its front and back are null and its size is zero.
   *
   * @param capacity Positive integer defining the max number of bottles the queue can hold
   * @throws IllegalArgumentException when the capacity is not positive (meaning less or equal to
   *                                  zero).
   */
  public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.front = null;
    this.back = null;
    this.size = 0;
    this.capacity = capacity;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ..
   * SN:Filled/Empty:Capped/Open
   *
   * The string should not contain a newline character at the end.
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

  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException When queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return this.front.getData();
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @return true if empty else false
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0 && this.front == null && this.back == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @return true if full else false
   */
  @Override
  public boolean isFull() {
    if (this.size == this.capacity) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes and returns the first bottle in the queue
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    LinkedNode<Bottle> current;
    if (this.size == 1) {
      current = this.front;
      this.front = null;
      this.back = null;
      this.size--;
    } else {
      current = this.front;
      LinkedNode<Bottle> next = this.front.getNext();

      this.front = next;
      this.size--;
    }

    return current.getData();
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle bottle to add to the queue
   * @throws IllegalStateException when queue is full
   * @throws NullPointerException  when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (bottle == null) {
      throw new NullPointerException("Bottle to be added is empty.");
    }
    if (this.isFull()) {
      throw new IllegalStateException("Queue is full");
    }

    LinkedNode<Bottle> newBottle = new LinkedNode<Bottle>(bottle);
    if (this.isEmpty()) {
      this.front = newBottle;
      this.back = newBottle;
      size++;
    } else {
      LinkedNode<Bottle> currBack = this.back;
      currBack.setNext(newBottle);
      this.back = newBottle;
      this.size++;
    }
  }

  /**
   * Returns the number of bottles in the queue
   *
   * @return size of the queue
   */
  @Override
  public int size() {
    int size = 0;
    LinkedNode<Bottle> currNode = this.front;

    while (currNode != null) {
      currNode = currNode.getNext();
      size++;
    }
    return size;
  }

  /**
   * Returns an iterator for traversing the queue's items
   *
   * @return iterator to traverse the LinkedListQueue
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());
  }

  /**
   * Returns a deep copy of this queue.
   *
   * @return deep copy of this queue.
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copy = new LinkedBottleQueue(this.capacity);

    LinkedNode<Bottle> curNode = this.front;
    while (curNode != null) {
      copy.enqueue(curNode.getData());
      curNode = curNode.getNext();
    }
    return copy;
  }
}
