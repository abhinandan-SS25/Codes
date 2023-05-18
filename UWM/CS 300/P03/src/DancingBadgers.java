//////////////// FILE HEADER //////////////////////////
//
// Title: File implementing the Dancing Badgers
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
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Badger II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // background image
  private static ArrayList<Badger> badgers; // array storing badger objects
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax; // maximum Badgers in ArrayList
  private static ArrayList<Thing> things; // Arraylist to hold all things
  private static ArrayList<StarshipRobot> robots; // arraylist storing StarshipRobot objects

  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    Thing.setProcessing(); // Sets the thing object display window
    StarshipRobot.setProcessing(); // Sets the robot object display window
    badgersCountMax = 5;

    // Initializing and storing the required assets
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    things = new ArrayList<Thing>();
    robots = new ArrayList<StarshipRobot>();
    randGen = new Random();

    // Creating the things
    Thing thing1 = new Thing(50, 50, "target.png");
    Thing thing2 = new Thing(750, 550, "target.png");
    Thing thing3 = new Thing(750, 50, "shoppingCounter.png");
    Thing thing4 = new Thing(50, 550, "shoppingCounter.png");

    // Adding the new things
    things.add(thing1);
    things.add(thing2);
    things.add(thing3);
    things.add(thing4);

    // Creating the robots
    StarshipRobot robot1 = new StarshipRobot(thing3, thing1, 3);
    StarshipRobot robot2 = new StarshipRobot(thing4, thing2, 3);

    // Adding the robots
    robots.add(robot1);
    robots.add(robot2);
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int j = 0; j < things.size(); j++) {
      things.get(j).draw(); // drawing the thing objects
    }
    for (int e = 0; e < robots.size(); e++) {
      robots.get(e).draw(); // drawing the robot objects
    }
    for (int i = 0; i < badgers.size(); i++) {
      badgers.get(i).draw(); // drawing the badger objects
    }

    robots.get(0).go(); // moving the first robot
    robots.get(1).go(); // moving the second robot

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i).isMouseOver()) {
        badgers.get(i).startDragging();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.size(); i++)
      badgers.get(i).stopDragging();
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        if (badgers.size() != 5) {
          badgers
              .add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
        }
        break;
      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i).isMouseOver()) {
            badgers.remove(i);
            break;
          }
        }
        break;
    }
  }
}
