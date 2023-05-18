//////////////// FILE HEADER //////////////////////////
//
// Title: File implementing the StarShip Robot Class
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
import processing.core.PImage;

/**
 * This class models Starship Robot objects delivering food to badger fans
 *
 */
public class StarshipRobot {
  private static processing.core.PApplet processing; // PApplet object representing the display
                                                     // window where this StarshipRobot is going to
                                                     // be drawn.
  private int speed; // movement speed of this StarshipRobot
  private processing.core.PImage image; // image of this StarshipRobot of type PImage
  private float x; // x-position of this StarshipRobot in the display window
  private float y; // y-position of this StarshipRobot in the display window
  private Thing source; // source point of this StarshipRobot at its current journey delivering food
                        // to badgers
  private Thing destination; // destination point of this StarshipRobot at its current journey
                             // delivering food to badgers

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The (x,y) position of
   * this StarshipRobot is set to the (x,y) position of source.
   * 
   * @param source:      Thing object referring to the start point of this StarshipRobot
   * @param destination: Thing object referring to the destination point of this StarshipRobot
   * @param speed:       movement speed of this StarshipRobot.
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    // Initializing the fields
    this.source = source;
    this.destination = destination;
    this.speed = speed;
    this.x = source.getX();
    this.y = source.getY();
    this.image = processing.loadImage("images" + File.separator + "starshipRobot.png");
  }

  /**
   * Returns a reference to the image of this starship robot
   * 
   * @return the image of type PImage of the starship robot object
   */
  public PImage image() {
    // returns a reference to the PImage of the current StarshipRobot object
    return this.image;
  }

  /**
   * Returns the x-position of this starship robot in the display window
   * 
   * @return the X coordinate of the starship robot position
   */
  public float getX() {
    // returns the x-position of the current StarshipRobot object
    return this.x;
  }

  /**
   * Returns the y-position of this starship robot in the display window
   * 
   * @return the y-position of the starship robot
   */
  public float getY() {
    // returns the y-position of the current StarshipRobot object
    return this.y;
  }

  /**
   * Sets the x-position of this starship robot in the display window
   * 
   * @param x: x - the x-position to set
   */
  public void setX(float x) {
    // sets the x-position of the current StarshipRobot object
    this.x = x;
  }

  /**
   * Sets the y-position of this starship robot in the display window
   * 
   */
  public void setY(float y) {
    // sets the y-position of the current StarshipRobot object
    this.y = y;
  }

  /**
   * Sets the PApplet object display window where this StarshipRobot will be drawn. The processing
   * PApplet data field is set to Badger.processing since Badger and StarshipRobot objects are going
   * to be displayed to the same screen.
   * 
   */
  public static void setProcessing() {
    // sets the processing PApplet static field to the processing
    // of the Badger class.
    processing = Badger.getProcessing();
  }

  /**
   * Draws this StarshipRobot to the display window while it is in action delivering food
   * 
   */
  public void draw() {
    // draws this StarshipRobot to the display window at its current
    // (x,y) position by calling processing.image() method
    processing.image(this.image, x, y);
  }

  /**
   * Helper method to move this StarshipRobot towards its destination
   * 
   */
  private void moveTowardsDestination() {
    float dx = this.destination.getX() - this.x;
    float dy = this.destination.getY() - this.y;

    int d = (int) Math.sqrt((dx * dx) + (dy * dy));

    float newX = this.x + ((this.speed * dx) / d); // New X position after moving one unit
    float newY = this.y + ((this.speed * dy) / d); // New Y position after moving one unit

    // Sets new X and Y for the object after moving one unit
    this.setX(newX);
    this.setY(newY);
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing: a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   */
  public boolean isOver(Thing thing) {
    int thingWidth = thing.image().width;
    int thingHeight = thing.image().height;
    int robotWidth = this.image().width;
    int robotHeight = this.image().height;

    // Checks if the robot is over the shopping counter
    return (this.getX() + robotWidth / 2) >= thing.getX() - thingWidth / 2
        && (this.getX() - robotWidth / 2) <= thing.getX() + thingWidth / 2
        && (this.getY() + robotHeight / 2) >= thing.getY() - thingHeight / 2
        && (this.getY() - robotHeight / 2) <= thing.getY() + thingHeight / 2;
  }

  /**
   * Implements the behavior of this StarshipRobot going back-and-forth between its source and
   * destination.
   * 
   */
  public void go() {
    // Checks if robot overlaps source and switches the destination and source if it is.
    if (isOver(this.destination)) {
      Thing temp = this.destination;
      this.destination = this.source;
      this.source = temp;
    } else {
      // Moves robot forward by one unit
      moveTowardsDestination();
    }

  }
}
