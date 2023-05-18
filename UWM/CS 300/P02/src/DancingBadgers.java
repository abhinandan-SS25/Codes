//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dancing Badgers Application Part 1
// Course: CS 300 Spring 2023
//
// Author: Abhinandan Saha
// Email: asaha33@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Pair programming not allowed for program
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// No help taken or received
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;

/**
 * This class contains the variables and methods required for the application to run and the desired
 * changes to take effect in response to specific user actions.
 * 
 * @author Abhinandan Saha
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Badger[] badgers; // perfect size array storing the badgers
  // present in this application
  private static Random randGen; // Generator of random numbers

  /**
   * Called when the window first opens up and lets us define and control how the window should be
   * on startup.
   */
  public static void setup() {
    randGen = new Random();
    // load the image of the background
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    badgers = new Badger[5];
  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    // Draw all badgers
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] == null) {
        continue;
      } else {
        badgers[i].draw();
      }
    }

  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter
   *
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   *         image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {
    float BadgerX = Badger.getX();
    float BadgerY = Badger.getY();
    PImage BadgerImage = Badger.image();
    float[] widthRange = {BadgerX - BadgerImage.width / 2, BadgerX + BadgerImage.width / 2};
    float[] heightRange = {BadgerY - BadgerImage.height / 2, BadgerY + BadgerImage.height / 2};

    if (((Utility.mouseX() >= widthRange[0]) && (Utility.mouseX() <= widthRange[1]))
        && ((Utility.mouseY() >= heightRange[0]) && (Utility.mouseY() <= heightRange[1]))) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.length; i++) {
      Badger badger = badgers[i];
      if (badger != null) {
        if (isMouseOver(badger)) {
          badger.startDragging();
          break;
        }
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null) {
        badgers[i].stopDragging();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    if (Utility.key() == 'B' || Utility.key() == 'b') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] == null) {
          badgers[i] = new Badger((float) randGen.nextInt(Utility.width()),
              (float) randGen.nextInt(Utility.height()));
          break;
        }
      }
    } else if (Utility.key() == 'R' || Utility.key() == 'r') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] != null && isMouseOver(badgers[i])) {
          badgers[i] = null;
        }
      }
    }
  }

  /**
   * Starts the application.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    Utility.runApplication();

  }
}
