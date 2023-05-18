//////////////// FILE HEADER //////////////////////////
//
// Title: File implementing the Thing Class
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// No Pair programming availed.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help given or received.
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;

/**
 * This class models a Thing object in the P03 Dancing Badgers programming assignment that serve as
 * the destination and source for the Starship Robots to deliver food to and from.
 *
 */
public class Thing {

  private static processing.core.PApplet processing; // PApplet object that represents the display
                                                     // window
  private processing.core.PImage image; // Thing's image
  private float x; // Thing's x-position in the display window
  private float y; // Thing's y-position in the display window

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x:             x-position of this Thing
   * @param y:             y-position of this Thing
   * @param imageFilename: filename of the image to be loaded for this object
   */
  public Thing(float x, float y, String imageFilename) {
    // Initializing fields
    this.x = x;
    this.y = y;
    this.image = processing.loadImage("images" + File.separator + imageFilename);
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   * 
   */
  public void draw() {
    processing.image(this.image, x, y);
  }

  /**
   * Sets the PApplet object display window where this Thing will be drawn. The processing PApplet
   * static data field should be set to Badger.getProcessing() since Badgers and Thing objects are
   * going to be displayed to the same screen.
   * 
   */
  public static void setProcessing() {
    Thing.processing = Badger.getProcessing();
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return image;
  }

  /**
   * Returns the x-position of this thing in the display window
   * 
   * @return the X coordinate of the thing position
   */
  public float getX() {
    return this.x;
  }

  /**
   * Returns the y-position of this thing in the display window
   * 
   * @return the y-position of the thing
   */
  public float getY() {
    return this.y;
  }

  /**
   * Sets the x-position of this thing in the display window
   * 
   * @param x - the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this thing in the display window
   * 
   * @param y the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }
}
