//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Abhinandan Saha
// Campus ID: 9083610239
// WiscEmail: asaha33@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// TODO fill the above UNIT QUIZ FILE HEADER out!!!

import java.util.EmptyStackException;
// NO Additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class named FancyStackInteger. The implementation of this class
// requires the use of a provided generic class named DoublyLinkedNode defined in a
// separate file. You will need to complete the implementation of the FancyStackInteger
// class with respect to the provided requirements in the TODO tags for full credit.
////////////////////////////////////////////////////////////////////////////////
//
// DO NOT make changes to the provided method signatures.
// DO NOT make changes to the the fully implemented provided methods.
// String comparisons if applicable are CASE SENSITIVE.
//
// No variables outside of any method should be added to this class.
//
// No additional methods should be added to this class.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// SIX methods SHOULD be completed in this file:
// Constructor of the class FancyStackInteger
// isEmpty()
// size()
// peek()
// push()
// pop()
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE
//
////////////////////////////////////////////////////////////////////////////////


/**
 * This class models a doubly linked stack implemented as a connection of DoublyLinkedNodes.
 * Elements stored in this fancy stack are of type Integer, and the top of this stack is its tail.
 * 
 * This fancy stack is defined by its head and tail references of type DoublyLinkedNode, and an int
 * primitive type field to keep track of the size of the stack.
 * 
 * The tail of the linked stack represents its top, meaning that elements are pushed and popped from
 * the tail of the stack. In addition, only the element at the tail of the stack is directly
 * accessible by calling the peek method.
 * 
 * This fancy stack is implemented using a dummy node at its tail (top of the stack).
 * 
 * The data stored in the dummy node is NOT part of the contents of the stack.
 * 
 * The size of the stack does NOT include the dummy node.
 * 
 * The integer stored at the dummy node is zero.
 * 
 * Elements are pushed before the dummy node.
 * 
 * The peek method returns the previous of the dummy node.
 * 
 * For instance, if 10, 20, and 30 are pushed in this order to a FancyStackInteger named stack, the
 * linked node representation of the stack is as follows:
 * 
 * head -> 10 <-> 20 <-> 30 <-> 0 <- tail (top of the stack) The tail always refers to the dummy
 * node whose data is 0 (zero)
 * 
 * stack.peek() must return 30 stack.size() must return 3
 * 
 * Note carefully that: head does not have a previous node and tail does not have a next node
 */
public class FancyStackInteger {

  private DoublyLinkedNode<Integer> head; // head of the linked list implementing this stack.
  private DoublyLinkedNode<Integer> tail; // head of the linked list implementing this stack.
  // The tail reference represents the top of this stack, meaning the extremity of the list from
  // where elements are pushed in and popped off the stack.

  private int size; // Total number of elements stored in this stack. This excludes the dummy node

  /**
   * Creates a new empty FancyStack
   */
  public FancyStackInteger() {
    // TODO complete the implementation of this constructor

    // Create a new DoublyLinkedNode whose data is 0 (zero) and has no next or previous nodes
    DoublyLinkedNode node = new DoublyLinkedNode(0);
    DoublyLinkedNode node2 = new DoublyLinkedNode(0);

    // Set both head and tail to reference the created node
    this.head = node;
    this.tail = node2;

    // Set size to zero [OPTIONAL since zero is the default value for int]
    this.size = 0;
  }


  /**
   * Checks whether this stack is empty, meaning contains zero elements.
   * 
   * @return true if the size of this stack is zero
   */
  public boolean isEmpty() {
    // TODO complete the implementation of this method
    if (this.size() == 0) {
      return true;
    }

    return false; // default return statement added to resolve compiler errors
  }

  /**
   * Gets the number of elements stored in this stack. The dummy node is not included in the size of
   * the stack.
   * 
   * @return the size of this stack
   */
  public int size() {
    // TODO complete the implementation of this method
    return this.size; // default return statement added to resolve compiler errors
  }

  /**
   * Returns the element at the top of this fancy stack, meaning the data at the node before the
   * dummy node.
   * 
   * @return the integer at the top of this fancy stack, meaning the data at the previous of tail.
   * 
   * @throws EmptyStackException if this stack is empty
   */
  public Integer peek() {
    if (isEmpty())
      throw new EmptyStackException();

    int nodeVal = this.tail.getPrevious().getData();

    return nodeVal; // default return statement added to resolve compiler errors
  }

  /**
   * Adds toAdd to the tail of this fancy doubly linked stack.
   * 
   * @param toAdd new element to be pushed at the tail of this stack.
   * @throws IllegalArgumentException if toAdd is zero.
   * @throws NullPointerException     if toAdd is null.
   */
  public void push(Integer toAdd) {
    if (toAdd == null) {
      throw new NullPointerException("It is NOT allowed to push null references into this stack.");
    }
    if (toAdd.equals(0)) {
      throw new IllegalArgumentException("It is NOT allowed to push zeros into this stack.");
    }

    // TODO complete the implementation of this method

    // insert toAdd at the tail of this stack and connect nodes correctly
    // Keep in mind that tail always refer to the dummy node. The toAdd element should be inserted
    // at the previous position of tail.
    // The previous of head is always null.
    DoublyLinkedNode nodeAdd = new DoublyLinkedNode(toAdd);

    // Drawing diagrams of doubly connected nodes helps implementing this behavior
    if (isEmpty()) {
      nodeAdd.setPrevious(this.head);
      nodeAdd.setNext(this.tail);
      this.head.setNext(nodeAdd);
      this.tail.setPrevious(nodeAdd);
    } else {
      nodeAdd.setPrevious(this.tail.getPrevious());
      nodeAdd.setNext(this.tail);
      this.tail.getPrevious().setNext(nodeAdd);
      this.tail.setPrevious(nodeAdd);
    }
    this.head.setPrevious(null);

    // increment size
    this.size++;
  }

  /**
   * Returns and removes the element at the top of this stack, meaning the previous of tail.
   * 
   * The dummy node must not be removed from the stack!
   * 
   * @return the integer at the top of this fancy stack, meaning the previous of tail.
   * 
   * @throws EmptyStackException if this stack is empty
   */
  public Integer pop() {
    if (isEmpty())
      throw new EmptyStackException();
    // TODO complete the implementation of this method
    // Keep in mind that tail is the top of this stack. It directly refers to the dummy node.

    // 1. save the data to be returned (the previous of tail)
    DoublyLinkedNode<Integer> prev = this.tail.getPrevious();

    // 2. remove the node storing the element at the top of the stack (the previous of tail) and
    // connect nodes accordingly

    // Drawing diagrams of doubly connected nodes helps implementing this behavior
    if (size == 1) {
      this.head.setNext(this.tail);
      this.tail.setPrevious(this.head);
    } else {
      this.tail.setPrevious(prev.getPrevious());
      prev.getPrevious().setNext(this.tail);
    }

    // 3. decrement size
    this.size--;

    // 4. return the saved data

    return prev.getData(); // default return statement added to resolve compiler errors
  }

  /**
   * Returns a string representation of the data stored in all the nodes connected in this fancy
   * stack starting from its top (tail) including the dummy node. Integers are separated by one
   * space.
   * 
   * @return a string representation of the data stored in all the nodes connected in this fancy
   *         stack.
   * 
   */
  @Override
  public String toString() {
    String s = "";
    DoublyLinkedNode<Integer> current = tail; // current points to the top of this stack
    // traverse the stack from its tail to its head
    while (current != null) {
      s += current.getData() + " "; // add the data at the current node
      current = current.getPrevious(); // go backward
    }
    return s.trim();
  }

  /**
   * Demo for this class
   */
  public static void demo() {
    FancyStackInteger stack = new FancyStackInteger();
    System.out.println("Empty newly created stack: " + stack.isEmpty());
    stack.push(1); // 1 pushed at the tail of stack
    stack.push(2); // 2 pushed at the tail of stack
    stack.push(3); // 3 pushed at the tail of stack
    stack.push(4); // 4 pushed at the tail of stack
    System.out.println("stack.peek(): " + stack.peek());
    System.out.println("stack.size(): " + stack.size());
    System.out.println("Integer representation of the stack:");
    System.out.println(stack);

    // Expected displayed output:
    // Empty newly created stack: true
    // stack.peek(): 4
    // stack.size(): 4
    // Integer representation of the connected nodes starting at the tail (top):
    // 0 4 3 2 1

  }

  /**
   * Main method to run the demo
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    demo();
  }
}
