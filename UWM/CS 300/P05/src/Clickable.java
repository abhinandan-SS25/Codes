//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Interface defining Clickable
// Course:   CS 300 Spring 2023
//
// Author:   Abhinandan Saha
// Email:    asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// No pair programming
// 
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help taken
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This interface models Clickable objects in a graphic application
 *
 */
public interface Clickable {
  
  /**
   * Implements the behavior to be run each time the mouse is pressed.
   */
  public void mousePressed();
  
  /**
   * Implements the behavior to be run each time the mouse is released.
   */
  public void mouseReleased();

}
