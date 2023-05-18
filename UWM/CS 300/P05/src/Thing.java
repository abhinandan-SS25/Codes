//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods and modelling Thing class
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

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display
 * window of a graphic application.
 *
 */
public class Thing {
  // Fields defined to draw a Thing in the application display window
  protected static PApplet processing; // PApplet object that represents the display window
  private PImage image; // image of this graphic thing
  protected float x; // x-position of this thing in the display window
  protected float y; // y-position of this thing in the display window

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x             x-coordinate of this thing in the display window
   * @param y             y-coordinate of this thing in the display window
   * @param imageFilename filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    // Set drawing parameters
    this.image = processing.loadImage("images" + File.separator + imageFilename);
    // sets the position of this decoration object
    this.x = x;
    this.y = y;
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    // draw the thing at its current position
    processing.image(this.image, this.x, this.y);

  }

  /**
   * 
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public PImage image() {
    return this.image;
  }

  /**
   * Checks if the mouse is over this Thing object
   * 
   * @return true if the mouse is over this Thing, otherwise returns false.
   */
  public boolean isMouseOver() {
    int thingWidth = this.image().width;
    int thingHeight = this.image().height;

    return processing.mouseX >= this.x - thingWidth / 2
        && processing.mouseX <= this.x + thingWidth / 2
        && processing.mouseY >= this.y - thingHeight / 2
        && processing.mouseY <= this.y + thingHeight / 2;
  }
}