//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods to model the PasswordNode class
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// NO Pair programming
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help taken or recieved
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Class to represent a binary search tree (BST) node that contains only Password objects.
 * 
 * @author Michelle & Abhinandan Saha
 */
public class PasswordNode {

  private Password password; // the password data this node stores
  private PasswordNode left; // a reference to node that is the left child
  private PasswordNode right; // a reference to the node that is the right child

  /**
   * 1-argument constructor that sets the only data of the node.
   * 
   * @param password the password for this node to store
   * 
   * @author Michelle
   */
  public PasswordNode(Password password) {
    this.password = password;
  }

  /**
   * 3-argument constructor that sets all three data field
   * 
   * @param password, password the password for this node to store
   * @param left,     the reference to the node that is the left child
   * @param right,    the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode(Password password, PasswordNode left, PasswordNode right) {
    this(password);
    this.left = left;
    this.right = right;
  }

  /**
   * Setter for left data field
   * 
   * @param left, the reference to the node to be the left child
   * 
   * @author Michelle
   */
  public void setLeft(PasswordNode left) {
    this.left = left;
  }

  /**
   * Setter for right data field
   * 
   * @param right, the reference to the node to be the right child
   * 
   * @author Michelle
   */
  public void setRight(PasswordNode right) {
    this.right = right;
  }

  /**
   * Getter for left data field
   * 
   * @return the reference to the node that is the left child
   * 
   * @author Michelle
   */
  public PasswordNode getLeft() {
    return this.left;
  }

  /**
   * Getter for right data field
   * 
   * @return the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode getRight() {
    return this.right;
  }

  /**
   * Getter for password data field
   * 
   * @return the password object that this node stores
   * 
   * @author Michelle
   */
  public Password getPassword() {
    return this.password;
  }

  /**
   * Determines if the current node is a leaf node
   * 
   * @return true if this node is a leaf, false otherwise
   * 
   * @author Abhinandan Saha
   */
  public boolean isLeafNode() {
    if (this.getLeft() == null && this.getRight() == null) {
      return true;
    }
    return false;
  }

  /**
   * Determines if the current node has a right child
   * 
   * @return true if this node has a right child, false otherwise
   * 
   * @author Abhinandan Saha
   */
  public boolean hasRightChild() {
    if (this.getRight() != null && this.getRight() instanceof PasswordNode) {
      return true;
    }
    return false;
  }

  /**
   * Determines if the current node has a left child
   * 
   * @return true if this node has a left child, false otherwise
   * 
   * @author Abhinandan Saha
   */
  public boolean hasLeftChild() {
    if (this.getLeft() != null && this.getLeft() instanceof PasswordNode) {
      return true;
    }
    return false;
  }

  /**
   * Determines how many children nodes this node has. RECALL: Nodes in a binary tree can have AT
   * MOST 2 children
   * 
   * @return The number of children this node has
   * 
   * @author Abhinandan Saha
   */
  public int numberOfChildren() {
    int children = 0;
    if (this.isLeafNode()) {
      return 0;
    }
    if (this.hasLeftChild()) {
      children++;
    }
    if (this.hasRightChild()) {
      children++;
    }
    return children;
  }

}
