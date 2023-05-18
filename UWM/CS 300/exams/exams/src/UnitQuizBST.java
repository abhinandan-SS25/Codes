import java.util.Iterator;

///////////////////////////// UNIT QUIZ FILE HEADER ////////////////////////////
// Full Name: Abhinandan Saha
// Campus ID: 9083610239
// WiscEmail: asaha33@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// This file contains THREE classes:
// 
//  1) UnitQuizBST - a very simple binary search tree
//  2) BinIterator - an iterator which uses UnitQuizBST's methods to iterate
//                   through the values in the tree from largest to smallest
//  3) BinNode -     the binary node that UnitQuizBST is constructed with
// 
// Classes 2 and 3 are provided to you in their entirety. You will need to complete 
// the implementation of the BST class with respect to the instructions in the 
// TODO tags for full credit.
//
// Do not modify the other two classes in any way.
////////////////////////////////////////////////////////////////////////////////
//
// DO NOT make changes to the provided method signatures.
// DO NOT make changes to the fully implemented provided methods, beyond adding print 
// statements to tester methods (and then only if you want to).
//
// No variables outside of any method should be added to this class.
//
// No additional methods should be added to this class.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// SIX methods are left to be completed in this file:
// 1. countNodes()
// 2. constructor of UnitQuizBST
// 3. getMax()
// 4. getPrev()
// 5. getPrevHelper()
// 6. iteratorTest()
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE!!
//
////////////////////////////////////////////////////////////////////////////////

/**
 * A basic, immutable binary search tree. Your task is to complete the methods in
 * this class so that the BST can be traversed in REVERSE order - that is, from its
 * largest value to its smallest.
 * 
 * The provided Iterator class will perform this operation if you are successful;
 * verify the performance of your implementations using the tester methods at the
 * bottom of this class. You will need to complete one of them!
 */
public class UnitQuizBST implements Iterable<String> {

  // data fields
  private BinNode root; // the BinNode representing the root of this BST
  private int size;     // the number of values in this BinNode
  
  /**
   * A RECURSIVE method which counts the number of nodes in a subtree rooted at the BinNode
   * provided. This method performs a full traversal of the subtree and returns the number 
   * of nodes present; you may use any traversal order you like.
   * 
   * @see countNodesTest() below for an example of how this is supposed to work
   * 
   * @param root the root BinNode of this subtree; may be null
   * @return the number of nodes in the subtree rooted at the given BinNode
   */
  public static int countNodes(BinNode root) {
    // TODO: Base case - the subtree is empty
    if (root == null) {
      return 0;
    }
    // TODO: Recursive case - the subtree is NOT empty

    return countNodes(root.getLeft()) + 1 + countNodes(root.getRight()); // default return statement added to avoid compiler errors.
  }
  
  /**
   * Creates a fixed BST object using the system of BinNodes passed in as a parameter. Use the
   * above method to help get the correct size!
   * 
   * @param root the root node of a BST, created elsewhere by hooking up BinNode objects. This is 
   * ASSUMED to be a valid, standard BST; you do NOT need to verify.
   */
  public UnitQuizBST(BinNode root) {
    this.root = root;
    // TODO: initialize the size field to the number of nodes in the BST rooted at the given node
    this.size = countNodes(root);
  }
  
  /**
   * Accessor method for size; provided.
   * @return the current value of the size data field for this UnitQuizBST
   */
  public int size() { return this.size; }
  
  /**
   * Provided overloaded form of getMax, finds the maximum value in the entire BST.
   * @return the largest String in this BST
   */
  public String getMax() { return getMax(root); }
  
  /**
   * Finds the largest value in the subtree rooted at current. May be implemented iteratively
   * OR recursively, your choice.
   * 
   * @param current the root of the subtree whose largest value we are looking for
   * @return the largest value in the subtree rooted at current
   */
  public String getMax(BinNode current) { 
    // TODO: find the largest value in the subtree rooted at current
    if (current.getRight() == null) {
      return current.getData();
    }
    else {
      return getMax(current.getRight());
    }
  }
  
  /**
   * Accessor method for the PREVIOUS value (predecessor) in the BST.
   * 
   * @see getPrevTest() below for an example of how this is supposed to work
   * 
   * @param query the String whose predecessor we are searching for
   * @return the predecessor String, or null if there is no predecessor
   * @throws IllegalArgumentException if the query value is not found in the tree
   */
  public String getPrev(String query) {
    // TODO: call the recursive helper method with appropriate starting parameters
    try {
      return getPrevHelper(query, this.root, null);
    } catch (IllegalStateException e) {
      throw new IllegalArgumentException("Query \"" + query + "\" not found in BST");
    }
    // HINT: if the tree contained only ONE value, what would its predecessor be?
     // default return statement added to avoid compiler errors.
  }
  
  /**
   * RECURSIVE helper method for getPrev, partially provided for you. You must complete
   * base case 2, where the value is found.
   * 
   * @param query the String whose predecessor we are searching for
   * @param current the current BinNode we are searching in
   * @param potentialPrev largest String value found so far which is smaller than the query, or null
   *        if we have not seen any Strings smaller than the query
   * @return the predecessor String, or null if there is no predecessor
   * @throws IllegalArgumentException if the query value is not found in the tree
   */
  private String getPrevHelper(String query, BinNode current, String potentialPrev) {
    // Base case 1, the query value was not found
    if (current == null) throw new IllegalArgumentException("Query \""+query+"\" not found in BST");
    
    // Compare the query to the value in the current node
    int compare = query.compareTo(current.getData());
    
    // Recursive cases: we are still looking for the query value
    if (compare < 0) return getPrevHelper(query, current.getLeft(), potentialPrev);
    if (compare > 0) return getPrevHelper(query, current.getRight(), current.getData());
    
    // TODO: Base case 2, the query value was found!
    
    // CASE 1: the current node has a left subtree, return the predecessor as in P09
    if (current.getLeft() != null) {
      return getMax(current.getLeft());
    }
    // CASE 2: the current node does NOT have a left subtree, use the potentialPrev argument
    else {
      return potentialPrev;
    }
  }
  
  /**
   * Provided method to create and return an iterator over this BST.
   * @return a BinIterator initialized to the largest value in the current BST
   */
  @Override
  public Iterator<String> iterator() {
    return new BinIterator(this);
  }
  
  // CHECKPOINT: save your code! There is one more method for you to complete below...
  
  ////////////////////////////////////// TESTER METHODS //////////////////////////////////////
  
  // NOTE: you MAY add print statements to these methods. Console output will not be graded.
  
  /**
   * Provided tester method to verify the performance of countNodes() on various configurations.
   * @return true if all tests pass; false otherwise
   */
  public static boolean countNodesTest() {
    // NOTE: this tree has the following structure:
    //     ╔═══╗        
    //     ║ B ║        
    //     ╚═╤═╝        
    //   ┌───┴───┐      
    // ╔═╧═╗   ╔═╧═╗    
    // ║ A ║   ║ C ║    
    // ╚═══╝   ╚═╤═╝    
    //           └───┐  
    //             ╔═╧═╗
    //             ║ D ║
    //             ╚═══╝
    BinNode nodeA = new BinNode("A");
    BinNode nodeD = new BinNode("D");
    BinNode nodeC = new BinNode("C", null, nodeD);
    BinNode nodeB = new BinNode("B", nodeA, nodeC);
    
    try {
      if (countNodes(nodeA) != 1) return false; // leaf node
      if (countNodes(nodeC) != 2) return false; // one child
      if (countNodes(nodeB) != 4) return false; // root node, full tree count
    } catch (Exception e) { return false; }
    
    UnitQuizBST countTest = new UnitQuizBST(nodeB);
    if (countTest.size() != 4) return false;         // verify that the size has been set correctly
    
    return true;
  }
  
  /**
   * Provided tester method to verify the performance of getPrev() on various configurations.
   * @return true if all tests pass; false otherwise
   */
  public static boolean getPrevTest() {
    // NOTE: this tree has the following structure:
    //     ╔═══╗    
    //     ║ B ║    
    //     ╚═╤═╝    
    //   ┌───┴───┐  
    // ╔═╧═╗   ╔═╧═╗
    // ║ A ║   ║ C ║
    // ╚═══╝   ╚═══╝
    BinNode nodeA = new BinNode("A");
    BinNode nodeC = new BinNode("C");
    BinNode nodeB = new BinNode("B", nodeA, nodeC);
    UnitQuizBST prevTest = new UnitQuizBST(nodeB);
    
    String aPrev, bPrev, cPrev;
    try {
      bPrev = prevTest.getPrev("B");
      cPrev = prevTest.getPrev("C");
      aPrev = prevTest.getPrev("A");
    } catch (Exception e) { return false; }
    
    if (bPrev == null || !bPrev.equals("A")) return false; // easy mode
    if (cPrev == null || !cPrev.equals("B")) return false; // harder!
    if (aPrev != null) return false; // there is no previous!!
    
    return true;
  }
  
  /**
   * Tester method to verify the performance of the provided Iterator object, which uses the
   * getMax() and getPrev() methods from UnitQuizBST defined above
   * 
   * @return true if all tests pass; false otherwise
   */
  public static boolean iteratorTest() {
    // TODO: create a BST containing multiple nodes - feel free to use one from the tests above
    BinNode nodeA = new BinNode("A");
    BinNode nodeD = new BinNode("D");
    BinNode nodeC = new BinNode("C", null, nodeD);
    BinNode nodeB = new BinNode("B", nodeA, nodeC);
    UnitQuizBST itTest = new UnitQuizBST(nodeB);

    // TODO: create an array containing the Strings from this BST in their expected order
    // For example, the BST in countNodesTest would have the expected traversal D C B A
    String[] expected = new String[]{"D", "C", "B", "A"};
    
    // TODO: Using an ENHANCED FOR LOOP, verify that the iterator returns the strings in the 
    // expected order
    int i = 0;
    for (String q:itTest) {
      if (!expected[i].equals(q)) {
        return false;
      }
      i++;
    }
    
    // TODO: verify that the iterator has returned ALL the strings you expect it to!
    if (i != itTest.size()) {
      return false;
    }
    
    // TODO: update the return statement below to reflect that all tests have passed:
    return true;  // default return statement added to avoid compiler errors.
  }
  
  /**
   * Provided, runs the tester methods.
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("countNodes: "+(countNodesTest()?"PASS":"FAIL"));
    System.out.println("getPrev: "+(getPrevTest()?"PASS":"FAIL"));
    System.out.println("iterator: "+(iteratorTest()?"PASS":"FAIL"));
  }
}

/**
 * Provided: an iterator for UnitQuizBST, which traverses the elements of the BST from
 * largest to smallest
 */
class BinIterator implements Iterator<String> {
  
  private String curr;
  private UnitQuizBST bst;
  
  public BinIterator(UnitQuizBST tree) {
    bst = tree;
    curr = bst.getMax();
  }

  @Override
  public boolean hasNext() {
    return curr != null;
  }

  @Override
  public String next() {
    String retval = curr;
    curr = bst.getPrev(curr);
    return retval;
  }
  
}

/**
 * A simple binary node containing a String
 */
class BinNode {
  
  private String data;
  private BinNode left, right;
  
  public BinNode(String data) { this(data, null, null); }
  public BinNode(String data, BinNode left, BinNode right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  public String getData() { return this.data; }
  public BinNode getLeft() { return this.left; } 
  public BinNode getRight() { return this.right; }
  
  public void setLeft(BinNode left) { this.left = left; }
  public void setRight(BinNode right) { this.right = right; }
  
}