//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods test the methods and behavior of Password, PasswordNode and PasswordStorage
//////////////// classes
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
 * Class to test the methods and behavior of Password, PasswordNode and PasswordStorage classes
 */
public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the protected data
   * field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * 
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {

    try {
      PasswordStorage storage1 = new PasswordStorage(Attribute.OCCURRENCE);
      PasswordStorage storage2 = new PasswordStorage(Attribute.HASHED_PASSWORD);
      PasswordStorage storage3 = new PasswordStorage(Attribute.STRENGTH_RATING);

      // Checking getComparison()
      if (storage1.getComparisonCriteria() != Attribute.OCCURRENCE) {
        return false;
      }
      if (storage2.getComparisonCriteria() != Attribute.HASHED_PASSWORD) {
        return false;
      }
      if (storage3.getComparisonCriteria() != Attribute.STRENGTH_RATING) {
        return false;
      }

      // Checking isEmpty on new strorage objects
      if (!storage1.isEmpty() || !storage2.isEmpty() || !storage3.isEmpty()) {
        return false;
      }

      // Check size of storage objects
      if (storage1.size() != 0 || storage2.size() != 0 || storage3.size() != 0) {
        return false;
      }

      // Checking private root for storages
      if (storage1.root != null || storage2.root != null || storage3.root != null) {
        return false;
      }

      // passed
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Validates the Password class compareTo() method. Create at least two DIFFERENT Password objects
   * and compare them on each of the Attribute values. See the writeup for details on how the
   * various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, false otherwise
   */
  public static boolean testPasswordCompareTo() {

    Password p1 = new Password("password", 1000);
    Password p2 = new Password("StronkPass12#", 23);

    // compare on occurence attribute
    if (p1.compareTo(p2, Attribute.OCCURRENCE) <= 0) {
      return false;
    }
    // compare on strength_rating attribute
    if (p1.compareTo(p2, Attribute.STRENGTH_RATING) >= 0) {
      return false;
    }
    // compare on hashed_password attribute
    if (p1.compareTo(p2, Attribute.HASHED_PASSWORD) >= 0) {
      return false;
    }

    // passed
    return true;
  }

  /**
   * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all possible
   * configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
    // creating password objects
    Password p1 = new Password("password", 1000);
    Password p2 = new Password("StronkPass12#", 23);
    Password p3 = new Password("Hello", 23);
    Password p4 = new Password("Hi1", 35);
    Password p5 = new Password("Hi2", 89);
    Password p6 = new Password("Hi3", 78);
    Password p7 = new Password("Hi4", 11);
    Password p8 = new Password("Hi5", 25);
    Password p9 = new Password("Hi6", 98);

    // creating new nodes using the password objects
    PasswordNode n1 = new PasswordNode(p1);
    PasswordNode n2 = new PasswordNode(p2);
    PasswordNode n3 = new PasswordNode(p3);
    PasswordNode n4 = new PasswordNode(p4);
    PasswordNode n5 = new PasswordNode(p5);
    PasswordNode n6 = new PasswordNode(p6);
    PasswordNode n7 = new PasswordNode(p7);
    PasswordNode n8 = new PasswordNode(p8);
    PasswordNode n9 = new PasswordNode(p9);

    // Creating a tree
    PasswordNode n10 = new PasswordNode(p4, n1, n2);
    n2.setRight(n5);
    n1.setLeft(n3);
    n1.setRight(n6);
    n6.setLeft(n7);
    n7.setRight(n8);
    n7.setLeft(n9);

    // Checking isLeafNode()
    if (n6.isLeafNode() && !n4.isLeafNode() && !n9.isLeafNode()) {
      return false;
    }

    // Checking hasLeftChild()
    if (n4.hasLeftChild() && n4.getLeft() == null || !n1.hasLeftChild()
        || !n1.getLeft().getPassword().equals(n3.getPassword())) {
      return false;
    }



    // Checking hasRightChild()
    if (n6.hasRightChild() && n6.getRight() == null || !n2.hasRightChild()
        || !n2.getRight().getPassword().equals(n5.getPassword())) {
      return false;
    }

    // Checking no of children
    if (n7.numberOfChildren() != 2 && n1.numberOfChildren() != 2 && n4.numberOfChildren() != 0
        && n6.numberOfChildren() != 1) {
      return false;
    }

    // passed
    return true;
  }

  /**
   * this method tests the expected behavior of the toString method of the PasswordStorage class
   *
   * @return true if expected behavior is observed else false
   *
   */
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
          + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();

      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * method checks if the bst is valid and maintains the ordering of the tree
   *
   * @return true if expected behavior else false
   *
   */
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep


      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);

      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
            .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }


      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }


    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the lookup method defined on PasswordStorage. Checks on the tree types of attributes.
   *
   * @return true if the tester passes and false otherwise
   */
  public static boolean testLookup() {
    try {
      {// Creating the bst with occurance attribute
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        PasswordNode p4Node = new PasswordNode(p4);
        PasswordNode p3Node = new PasswordNode(p3);
        PasswordNode p7Node = new PasswordNode(p7);
        PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
        PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
        PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
        PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);
        bst.root = rootNode;

        if (!bst.isValidBST()) {
          return false;
        }

        Password key = new Password("keyPass", 1002);
        Password key1 = new Password("pas1", 2090);

        // checking if the correct password is returned for a node that exists.
        if (bst.lookup(key).compareTo(p5, Attribute.OCCURRENCE) != 0) {
          return false;
        }
        if (bst.lookup(key1).compareTo(p7, Attribute.OCCURRENCE) != 0) {
          return false;
        }

        // checking output for a key that dosnt exist in bst
        if (bst.lookup(new Password("la", 300)) != null) {
          return false;
        }
      }
      {// Creating the bst with strength_rating attribute
        PasswordStorage bst = new PasswordStorage(Attribute.STRENGTH_RATING);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        bst.addPassword(p4);
        bst.addPassword(p3);
        bst.addPassword(p2);
        bst.addPassword(p);
        bst.addPassword(p7);
        bst.addPassword(p5);

        if (!bst.isValidBST()) {
          return false;
        }

        Password key = p4;
        Password key1 = p7;

        //checking if the correct password is returned for a node that exists.
        if (bst.lookup(key).compareTo(p4, Attribute.STRENGTH_RATING) != 0) {
          return false;
        } if (bst.lookup(key1).compareTo(p7, Attribute.STRENGTH_RATING) != 0) {
          return false;
        }

        //checking output for a key that doesn't exist in bst
        if (bst.lookup(new Password("la", 300)) != null) { return false; }
      }
      {// Creating the bst with hashed_password attribute
        PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        bst.addPassword(p4);
        bst.addPassword(p3);
        bst.addPassword(p2);
        bst.addPassword(p);
        bst.addPassword(p7);
        bst.addPassword(p6);
        bst.addPassword(p5);

        if (!bst.isValidBST()) {
          return false;
        }

        Password key = p4;
        Password key1 = p7;

        // checking if the correct password is returned for a node that exists.
        if (bst.lookup(key).compareTo(p4, Attribute.HASHED_PASSWORD) != 0) {
          return false;
        }
        if (bst.lookup(key1).compareTo(p7, Attribute.HASHED_PASSWORD) != 0) {
          return false;
        }

        // checking output for a key that doesn't exist in bst
        if (bst.lookup(new Password("la", 300)) != null) {
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * tests the behavior of the getBestPassword and getWorstPassword methods on Password Storage
   * object
   *
   * @return true if it works as expected and false if it doesn't
   */
  public static boolean testbestandWorstPassword() {
    {
      // testing exception for empty tree
      PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

      try {
        bst.getBestPassword();
        return false;
      } catch (NoSuchElementException n) {

      } catch (Exception e) {
        return false;
      }
    }
    {
      // testing exception for empty tree
      PasswordStorage bst = new PasswordStorage(Attribute.STRENGTH_RATING);

      try {
        bst.getWorstPassword();
        return false;
      } catch (NoSuchElementException n) {

      } catch (Exception e) {
        return false;
      }
    }
    {
      // bst created with occurence attribute
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1234567890", 1000);
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST()) {
        return false;
      }

      // worst p4 and best p6
      if (!bst.getBestPassword().equals(p6) && !bst.getWorstPassword().equals(p4)) {
        return false;
      }
    }
    {
      // Creating the bst with strength_rating attribute
      PasswordStorage bst = new PasswordStorage(Attribute.STRENGTH_RATING);

      Password p = new Password("1234567890", 1000);
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p7 = new Password("abc123", 2090);

      bst.addPassword(p3);
      bst.addPassword(p4);
      bst.addPassword(p2);
      bst.addPassword(p);
      bst.addPassword(p7);
      bst.addPassword(p5);

      if (!bst.isValidBST()) {
        return false;
      }

      // worst p4 and best p6
      if (!bst.getBestPassword().equals(p) && !bst.getWorstPassword().equals(p2)) {
        return false;
      }
    }
    {
      // Creating the bst with hashed_password attribute
      PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

      Password p = new Password("1234567890", 1000);
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      bst.addPassword(p4);
      bst.addPassword(p3);
      bst.addPassword(p2);
      bst.addPassword(p);
      bst.addPassword(p7);
      bst.addPassword(p6);
      bst.addPassword(p5);

      if (!bst.isValidBST()) {
        return false;
      }

      // worst p3 and best p
      if (!bst.getBestPassword().equals(p3) && !bst.getWorstPassword().equals(p)) {
        return false;
      }
    }

    return true;
  }

  /**
   * tests the behavior of addPassword in PasswordStorage
   *
   * @return true if expected behavior is found or false
   */
  public static boolean testAddPassword() {
    try {
      {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("pass", 100);
        bst.addPassword(p);

        if (!bst.toString().equals(p.toString() + "\n") || bst.size() != 1) {
          return false;
        }
      }
      {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("pass", 100);
        bst.addPassword(p);
        try {
          bst.addPassword(new Password("po", 100));
          return false;
        } catch (IllegalArgumentException i) {

        } catch (Exception e) {
          return false;
        }

        if (!bst.toString().equals(p.toString() + "\n") || bst.size() != 1) {
          return false;
        }
      }
      {
        // bst with occurence attribute
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        // size one only returns 1 thing
        Password p = new Password("1234567890", 1000);
        // big tree returns in-order traversal
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        PasswordNode p4Node = new PasswordNode(p4);
        PasswordNode p3Node = new PasswordNode(p3);
        PasswordNode p7Node = new PasswordNode(p7);
        PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
        PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
        PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
        PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);
        bst.root = rootNode;

        if (!bst.isValidBST()) {
          return false;
        }

        PasswordNode p1N = new PasswordNode(new Password("new", 1001));
        PasswordNode p2N = new PasswordNode(new Password("new1", 300));
        PasswordNode p3N = new PasswordNode(new Password("new2", 2236));
        PasswordNode p4N = new PasswordNode(new Password("new3", 115));
        bst.addPassword(p1N.getPassword());
        bst.addPassword(p2N.getPassword());
        bst.addPassword(p3N.getPassword());
        bst.addPassword(p4N.getPassword());

        String expected = p4N.getPassword().toString() + "\n" + p4.toString() + "\n"
            + p2N.getPassword().toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n"
            + p.toString() + "\n" + p1N.getPassword().toString() + "\n" + p5.toString() + "\n"
            + p7.toString() + "\n" + p6.toString() + "\n" + p3N.getPassword().toString() + "\n";
        String actual = bst.toString();

        if (!bst.isValidBST()) {
          return false;
        }

        if (!actual.equals(expected)) {
          return false;
        }
      }
      {
        // Creating the bst with strength_rating attribute
        PasswordStorage bst = new PasswordStorage(Attribute.STRENGTH_RATING);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p7 = new Password("abc123", 2090);

        PasswordNode p7Node = new PasswordNode(p7);
        PasswordNode p3Node = new PasswordNode(p3, p7Node, null);
        PasswordNode p5Node = new PasswordNode(p5);
        PasswordNode p2Node = new PasswordNode(p2, null, p5Node);
        PasswordNode p4Node = new PasswordNode(p4, p2Node, p3Node);
        PasswordNode rootNode = new PasswordNode(p, p4Node, null);
        bst.root = rootNode;

        PasswordNode p4N = new PasswordNode(new Password("new3", 115));
        bst.addPassword(p4N.getPassword());

        if (!bst.isValidBST()) {
          return false;
        }
        String expected = p2.toString() + "\n" + p5.toString() + "\n" + p4N.getPassword().toString()
            + "\n" + p4.toString() + "\n" + p7.toString() + "\n" + p3.toString() + "\n"
            + p.toString() + "\n";
        String actual = bst.toString();

        if (!bst.isValidBST()) {
          return false;
        }

        if (!actual.equals(expected)
            && !p5Node.getRight().getPassword().equals(p4N.getPassword())) {
          return false;
        }
      }
      {
        // Creating the bst with hashed_password attribute
        PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p7 = new Password("abc123", 2090);

        PasswordNode p7Node = new PasswordNode(p7);
        PasswordNode p5Node = new PasswordNode(p5);
        PasswordNode p4Node = new PasswordNode(p4, null, p5Node);
        PasswordNode p3Node = new PasswordNode(p3, p4Node, null);
        PasswordNode p2Node = new PasswordNode(p2, p7Node, p3Node);
        PasswordNode rootNode = new PasswordNode(p, null, p2Node);
        bst.root = rootNode;

        PasswordNode p4N = new PasswordNode(new Password("new3", 115));
        bst.addPassword(p4N.getPassword());

        if (!bst.isValidBST()) {
          return false;
        }
        String expected = p.toString() + "\n" + p7.toString() + "\n" + p4N.getPassword().toString()
            + "\n" + p2.toString() + "\n" + p4.toString() + "\n" + p5.toString() + "\n"
            + p3.toString() + "\n";
        String actual = bst.toString();

        if (!bst.isValidBST()) {
          return false;
        }

        if (!actual.equals(expected)
            && !p7Node.getRight().getPassword().equals(p4N.getPassword())) {
          return false;
        }
      }
      {
        // Adding multiple elements
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        String expected =
            p4 + "\n" + p2 + "\n" + p3 + "\n" + p + "\n" + p5 + "\n" + p7 + "\n" + p + "\n";

        bst.addPassword(p4);
        bst.addPassword(p3);
        bst.addPassword(p2);
        bst.addPassword(p);
        bst.addPassword(p7);
        bst.addPassword(p6);
        bst.addPassword(p5);

        if (!bst.isValidBST()) {
          return false;
        }

        if (bst.size() != 7 && !expected.equals(bst.toString())) {
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * tests the behavior of removePassword
   *
   * @return true if behaves expectedly or false otherwise
   */
  public static boolean testRemovePassword() {
    try {
      {
        // test for bst with single element
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("pass", 100);
        bst.addPassword(p);

        if (!bst.toString().equals(p.toString() + "\n") || bst.size() != 1) {
          return false;
        }

        bst.removePassword(p);

        if (!bst.toString().equals("") || bst.size() != 0) {
          return false;
        }
      }
      {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("pass", 100);
        bst.addPassword(p);
        bst.addPassword(new Password("password", 200));

        bst.removePassword(p);

        bst.addPassword(new Password("password", 100));
      }
      {
        // remove from empty bst
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        try {
          bst.removePassword(new Password("po", 100));
          return false;
        } catch (NoSuchElementException i) {

        } catch (Exception e) {
          return false;
        }

        if (!bst.toString().equals("") || bst.size() != 0) {
          return false;
        }
      }
      {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("b", 765);

        bst.addPassword(p);
        bst.addPassword(p2);
        bst.addPassword(p3);

        bst.removePassword(p);

        if (!bst.toString().equals(p2 + "\n" + p3 + "\n")) {
          return false;
        }

        if (!bst.isValidBST()) {
          return false;
        }

        bst.removePassword(p3);
        if (!bst.toString().equals(p2 + "\n")) {
          return false;
        }
      }
      {
        // bst with occurrence attribute
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p0 = new Password("ajiojd", 150);
        Password p = new Password("1234567890", 100);
        Password p2 = new Password("test", 75);
        Password p3 = new Password("iloveyou", 50);
        Password p4 = new Password("qwerty", 80);
        Password p5 = new Password("admin", 78);
        Password p6 = new Password("password", 85);

        bst.addPassword(p0);
        bst.addPassword(p);
        bst.addPassword(p2);
        bst.addPassword(p3);
        bst.addPassword(p4);
        bst.addPassword(p5);
        bst.addPassword(p6);

        bst.removePassword(p);

        String expected = p3 + "\n" + p2 + "\n" + p5 + "\n" + p4 + "\n" + p6 + "\n" + p0 + "\n";

        if (!bst.isValidBST()) {
          return false;
        }

        if (!bst.toString().equals(expected)) {
          return false;
        }
      }
      {
        // bst with occurrence attribute
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p6 = new Password("password", 2232);
        Password p7 = new Password("abc123", 2090);

        PasswordNode p4Node = new PasswordNode(p4);
        PasswordNode p3Node = new PasswordNode(p3);
        PasswordNode p7Node = new PasswordNode(p7);
        PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
        PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
        PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
        PasswordNode rootNode = new PasswordNode(p, p2Node, p5Node);
        PasswordNode p1N = new PasswordNode(new Password("new", 110));
        PasswordNode p2N = new PasswordNode(new Password("new", 300));
        PasswordNode p3N = new PasswordNode(new Password("tr", 100));
        bst.root = rootNode;
        bst.addPassword(p1N.getPassword());
        bst.addPassword(p2N.getPassword());
        bst.addPassword(p3N.getPassword());

        // removing root of tree
        bst.removePassword(p);

        String expected = p3N.getPassword() + "\n" + p1N.getPassword() + "\n" + p4 + "\n"
            + p2N.getPassword() + "\n" + p2 + "\n" + p3 + "\n" + p5 + "\n" + p7 + "\n" + p6 + "\n";

        if (!bst.isValidBST()) {
          return false;
        }

        if (!bst.toString().equals(expected) && !p3Node.getRight().getPassword().equals(p5)
            && !p3Node.getLeft().getPassword().equals(p2)) {
          return false;
        }

        // remove an element from left subtree
        bst.removePassword(p2);

        String expected1 = p3N.getPassword() + "\n" + p1N.getPassword() + "\n" + p4 + "\n"
            + p2N.getPassword() + "\n" + p3 + "\n" + p5 + "\n" + p7 + "\n" + p6 + "\n";

        if (!bst.isValidBST()) {
          return false;
        }

        if (!bst.toString().equals(expected1) && !p3Node.getRight().getPassword().equals(p4)
            && !p3Node.getLeft().getPassword().equals(p2)) {
          return false;
        }
      }
      {
        // Creating the bst with hashed_password attribute
        PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p7 = new Password("abc123", 2090);

        bst.addPassword(p);
        bst.addPassword(p2);
        bst.addPassword(p3);
        bst.addPassword(p4);
        bst.addPassword(p5);
        bst.addPassword(p7);

        PasswordNode p4N = new PasswordNode(new Password("new3", 115));
        bst.addPassword(p4N.getPassword());

        if (!bst.isValidBST()) {
          return false;
        }

        // remove from right subtree
        bst.removePassword(p3);

        if (!bst.isValidBST()) {
          return false;
        }

        String expected = p.toString() + "\n" + p7.toString() + "\n" + p4N.getPassword().toString()
            + "\n" + p2.toString() + "\n" + p4.toString() + "\n" + p5.toString() + "\n";
        String actual = bst.toString();

        if (!actual.equals(expected)) {
          return false;
        }

        // remove leaf node
        bst.removePassword(p4N.getPassword());

        if (!bst.isValidBST()) {
          return false;
        }

        String expected1 = p.toString() + "\n" + p7.toString() + "\n" + p2.toString() + "\n"
            + p4.toString() + "\n" + p5.toString() + "\n";
        String actual1 = bst.toString();

        if (!actual1.equals(expected1)) {
          return false;
        }
      }
      {
        // Creating the bst with strength_rating attribute
        PasswordStorage bst = new PasswordStorage(Attribute.STRENGTH_RATING);

        Password p = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 1002);
        Password p7 = new Password("abc123", 2090);

        bst.addPassword(p);
        bst.addPassword(p2);
        bst.addPassword(p3);
        bst.addPassword(p4);
        bst.addPassword(p5);
        bst.addPassword(p7);

        PasswordNode p4N = new PasswordNode(new Password("new3", 115));
        bst.addPassword(p4N.getPassword());

        if (!bst.isValidBST()) {
          return false;
        }

        bst.removePassword(p3);

        if (!bst.isValidBST()) {
          return false;
        }
        String expected = p2.toString() + "\n" + p5.toString() + "\n" + p4N.getPassword().toString()
            + "\n" + p4.toString() + "\n" + p7.toString() + "\n" + p.toString() + "\n";
        String actual = bst.toString();

        if (!actual.equals(expected)) {
          return false;
        }
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * runs runAllTests() to verify the methods of Password, PasswordNode and PasswordStorage classes
   *
   * @param args unused
   */
  public static void main(String[] args) {
    runAllTests();
  }

  /**
   * runs all the tests to verify their behavior
   *
   * @return true if all tests pass and false otherwise
   */
  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();
    boolean test = testbestandWorstPassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out
        .println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!
    System.out.println("Best and worst password: " + (test ? "PASS" : "FAIL"));

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
