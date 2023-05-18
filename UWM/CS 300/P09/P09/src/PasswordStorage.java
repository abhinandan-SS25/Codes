//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods to model the PasswordStorage class
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

import java.util.NoSuchElementException;

/**
 * Methods and fields to model the PasswordStorage class
 */
public class PasswordStorage {

  protected PasswordNode root; // the root of this BST that contains passwords
  private int size; // how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; // what password information to use to determine
                                               // order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * 
   * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.root = null;
    this.size = 0;
    this.COMPARISON_CRITERIA = comparisonCriteria;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * 
   * @return the Attribute that is being used to make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return this.COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * 
   * @return the size of this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines whether or not this tree is empty.
   * 
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.root == null && this.size() == 0) {
      return true;
    }
    return false;
  }

  /**
   * Provides in-order String representation of this BST, with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * 
   * @return this BST as a string
   */
  @Override
  public String toString() {
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to create the string representation of this
   * tree.
   * 
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
    if (currentNode == null) {
      return "";
    }
    if (currentNode.isLeafNode()) {
      return currentNode.getPassword().toString() + "\n";
    }
    if (!currentNode.hasLeftChild()) {
      return currentNode.getPassword().toString() + "\n" + toStringHelper(currentNode.getRight());
    }
    if (!currentNode.hasRightChild()) {
      return toStringHelper(currentNode.getLeft()) + currentNode.getPassword().toString() + "\n";
    }
    return toStringHelper(currentNode.getLeft()) + currentNode.getPassword().toString() + "\n"
        + toStringHelper(currentNode.getRight()); // TODO
  }

  /**
   * Determines whether or not this tree is actually a valid BST.
   * 
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
  }

  /**
   * Recurisvely determines if the the tree rooted at the current node is a valid BST. That is,
   * every value to the left of currentNode is "less than" the value in currentNode and every value
   * to the right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound,  the smallest possible password
   * @param upperBound,  the largest possible password
   * @return true if the tree rooted at currentNode is a BST, false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound,
      Password upperBound) {
    // MUST BE IMPLEMENTED RECURSIVELY

    // BASE CASE 1: the tree rooted at currentNode is empty, which does not violate any BST rules
    if (currentNode == null) {
      return true;
    }

    // BASE CASE 2: the current Password is outside of the upper OR lower bound for this subtree,
    // which is against
    // the rules for a valid BST
    if (currentNode.getPassword().compareTo(lowerBound, this.COMPARISON_CRITERIA) < 0
        || currentNode.getPassword().compareTo(upperBound, this.COMPARISON_CRITERIA) > 0) {
      return false;
    }
    // If we do not have a base case situation, we must use recursion to verify currentNode's child
    // subtrees

    // RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater than lowerBound
    // and less than
    // currentNode's Password; return false if this property is NOT satisfied
    boolean leftV = isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword());

    // RECURSIVE CASE 2: Check that the right subtree contains only Passwords greater than
    // currentNode's Password
    // and less than upperBound; return false if this property is NOT satisfied
    boolean rightV =
        isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(), upperBound);

    // COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case 1 and 2 are
    // valid
    return leftV && rightV;
  }

  /**
   * Returns the password that matches the criteria of the provided key. Ex. if the COMPARISON
   * CRITERIA is OCCURRENCE and the key has an occurrence of 10 it will return the password stored
   * in the tree with occurrence of 10
   * 
   * @param key, the password that contains the information for the password we are searching for
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  }

  /**
   * Recursive helper method to find the matching password in this BST
   * 
   * @param key,         password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
    // THIS MUST BE IMPLEMENTED RECURSIVELY

    // Base case: current node is empty
    if (currentNode == null) {
      return null;
    }
    // Base case: match
    if (key.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) == 0) {
      return currentNode.getPassword();
    }
    // Base case: reached a leaf element and hasn't matched
    /*
     * else if (currentNode.isLeafNode()) { return null; }
     */

    if (key.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) < 0) {
      return lookupHelper(key, currentNode.getLeft());
    } else {
      return lookupHelper(key, currentNode.getRight());
    }
  }

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Empty BST");
    }
    PasswordNode bestNode = this.root;
    while (!bestNode.isLeafNode()) {
      if (!bestNode.hasRightChild()) {
        return bestNode.getPassword();
      }
      bestNode = bestNode.getRight();
    }

    return bestNode.getPassword();
  }

  /**
   * Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Empty BST");
    }
    PasswordNode worstNode = this.root;
    while (!worstNode.isLeafNode()) {
      if (!worstNode.hasRightChild()) {
        return worstNode.getPassword();
      }
      worstNode = worstNode.getLeft();
    }

    return worstNode.getPassword();
  }

  /**
   * Adds the Password to this BST.
   * 
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  public void addPassword(Password toAdd) {
    Boolean added = addPasswordHelper(toAdd, this.root);
    if (!added) {
      throw new IllegalArgumentException("Element already exists");
    } else {
      this.size++;
    }
  }

  /**
   * Recursive helper that traverses the tree and adds the password where it belongs
   * 
   * @param toAdd,       the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
    // Base case: tree empty
    if (this.isEmpty()) {
      this.root = new PasswordNode(toAdd);
      return true;
    }

    // Case: left and right empty
    if (currentNode.isLeafNode()) {
      if (toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) < 0) {
        currentNode.setLeft(new PasswordNode(toAdd));
        return true;
      } else if (toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) > 0) {
        currentNode.setRight(new PasswordNode(toAdd));
        return true;
      } else {
        return false;
      }
    }

    // Case: left child empty and toAdd is lesser than currentNode
    if (!currentNode.hasLeftChild()
        && toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) < 0) {
      currentNode.setLeft(new PasswordNode(toAdd));
      return true;
    }

    // Case: right child empty and toAdd is greater than currentNode
    if (!currentNode.hasRightChild()
        && toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) > 0) {
      currentNode.setRight(new PasswordNode(toAdd));
      return true;
    }

    // Case: both children exist
    if (toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) < 0) {
      return addPasswordHelper(toAdd, currentNode.getLeft());
    } else if (toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) > 0) {
      return addPasswordHelper(toAdd, currentNode.getRight());
    } else {
      return false;
    }
  }

  /**
   * Removes the matching password from the tree
   * 
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
    PasswordNode newRNode = removePasswordHelper(toRemove, this.root);

    // Helper returns null if no recursion occurs, so handling size=1 is necessary (Consider as base
    // case)
    if (this.size() == 1) {
      if (toRemove.compareTo(this.root.getPassword(), this.COMPARISON_CRITERIA) == 0) {
        this.root = null;
        this.size--;
        return;
      } else {
        throw new NoSuchElementException("Tree doesn't contain the element");
      }
    }
    if (newRNode == null) {
      throw new NoSuchElementException("Tree doesn't contain the element");
    } else {
      this.size--;
    }
  }

  /**
   * Recursive helper method to that removes the password from this BST.
   * 
   * @param toRemove,    the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of this subtree now that toRemove has been
   *         removed. This may still be currentNode, or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
    // BASE CASE: current tree is empty
    if (currentNode == null) {
      return null;
    }

    // RECURSIVE CASE: toRemove is in the left subtree, continue searching
    if (toRemove.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) < 0) {
      currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
      return currentNode;
    }

    // RECURSIVE CASE: toRemove is in the right subtree, continue searching
    if (toRemove.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA) > 0) {
      currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
      return currentNode;
    }

    // otherwise we found the node to remove!

    // BASE CASE: current node has no children
    if (currentNode.isLeafNode()) {
      return null;
    }
    // BASE CASE(S): current node has one child (one for the left and right respectively)
    if (currentNode.numberOfChildren() == 1) {
      if (this.root.getPassword().equals(currentNode.getPassword())) {
        if (currentNode.hasLeftChild()) {
          this.root = currentNode.getLeft();
          return currentNode.getLeft();
        } else {
          this.root = currentNode.getLeft();
          return currentNode.getRight();
        }
      }
      if (currentNode.hasLeftChild()) {
        return currentNode.getLeft();
      } else {
        return currentNode.getRight();
      }
    }
    // RECURSIVE CASE: currentNode has 2 children
    // 1)Find the predecessor password [HINT: Write a private helper method!]
    Password predecessor = findPredecessor(currentNode).getPassword();

    // 2)Make new node for the predecessor password. It should have same left and right subtree as
    // the current node.
    PasswordNode predecessorNode =
        new PasswordNode(predecessor, currentNode.getLeft(), currentNode.getRight());

    // 4)Remove the (duplicate) predecessor from the current tree and update the left subtree
    if (this.root.getPassword().equals(currentNode.getPassword())) {
      // 3)Replace currentNode with the new predecessor node
      this.root = predecessorNode;
      this.root.setLeft(removePasswordHelper(predecessor, this.root.getLeft()));
    } else {
      // 3)Replace currentNode with the new predecessor node
      currentNode = predecessorNode;
      currentNode.setLeft(removePasswordHelper(predecessor, currentNode.getLeft()));
    }
    return currentNode; // LEAVE THIS LINE AS IS
  }

  private PasswordNode findPredecessor(PasswordNode currNode) {
    PasswordNode bestNode = currNode.getLeft();
    while (!bestNode.isLeafNode()) {
      if (!bestNode.hasRightChild()) {
        return bestNode;
      }
      bestNode = bestNode.getRight();
    }

    return bestNode;
  }
}
