//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods and modelling Moving Thing class derived from Things
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
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
 * This class models moving thing objects. A moving thing is defined by its speed and to which
 * direction it is facing (right or left).
 * 
 * @author abhinandan
 *
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {
  protected boolean isFacingRight; // indicates whether this MovingThing is facing right or not
  protected int speed; // movement speed of this MovingThing

  /**
   * Creates a new MovingThing and sets its speed, image file, and initial x and y position.
   * 
   * @param x             x-coordinate of this moving thing in the display window
   * @param y             y-coordinate of this moving thing in the display window
   * @param speed         speed of this moving thing
   * @param imageFileName filename of the image of this MovingThing, for instance "name.png"
   */
  public MovingThing(float x, float y, int speed, String imageFileName) {
    super(x, y, imageFileName);
    this.speed = speed;
    this.isFacingRight = true;
  }

  /**
   * Draws this MovingThing at its current position. The implementation details of this method is
   * fully provided in the write-up of p05.
   */
  public void draw() {
    // draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their
   * speeds.
   * 
   * @param other the MovingThing object to be compared.
   * @return zero if this object and other have the same speed, a negative integer if the speed of
   *         this moving object is less than the speed of other, and a positive integer otherwise.
   * 
   */
  public int compareTo(MovingThing other) {
    if (other.speed == this.speed) {
      return 0;
    } else if (other.speed > this.speed) {
      return -1;
    } else {
      return 1;
    }
  }
}
