//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Methods and modelling Starship class
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
 * This class models StarshipRobot objects which can be triggered to move or do some actions.
 * 
 * @author abhinandan
 *
 */
public class StarshipRobot extends MovingThing {

  private Thing source; // source point of this StarshipRobot at its current journey delivering food
                        // to badgers
  private Thing destination; // destination point of this StarshipRobot at its current journey
                             // delivering food to badgers

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The start point of
   * this this StarshipRobot is set to the (x,y) position of source. When created, a StarshipRobot
   * object must face its destination. Checking whether source.x is less than destination.x can help
   * determining whether a starship robot is facing right or not.
   * 
   * @param source      Thing object representing the start point of this StarshipRobot
   * @param destination Thing object representing the destination point of this StarshipRobot
   * @param speed       movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    super(source.x, source.y, speed, "starshipRobot.png");
    this.destination = destination;
    this.source = source;

    if (destination.x > source.x) {
      this.isFacingRight = true;
    } else {
      this.isFacingRight = false;
    }
  }

  /**
   * Draws this StarshipRobot to the display window while it is in motion delivering food. This
   * method first prompts this StarshipRobot to go. Then, it draws it to the display window. Think
   * of partial overriding to draw this StarshipRobot as its image is not directly accessed from
   * here. The super.draw() can do so!
   */
  @Override
  public void draw() {
    this.go();
    super.draw();
  }

  /**
   * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
   * back-and-forth between its source and destination.
   * 
   * If the starship robot is over its destination, this method: - switches the source and
   * destination, - switches the value of isFacingRight to its opposite (!isFacingRight), so that
   * the starship robot faces the opposite direction.
   */
  public void go() {
    moveTowardsDestination();
    // switch source and destination if this StarshipRobot reached its destination
    if (this.isOver(this.destination)) {
      Thing d = destination;
      destination = source;
      source = d;
      isFacingRight = !isFacingRight;
    }
  }

  /**
   * Helper method to move this StarshipRobot towards its destination
   */
  private void moveTowardsDestination() {
    float dx = destination.x - this.x; // x-move towards destination
    float dy = destination.y - this.y; // y-move towards destination
    int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
    if (d != 0) { // move!
      this.x += speed * dx / d;
      this.y += speed * dy / d;
    }
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   */
  public boolean isOver(Thing thing) {
    // (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)
    float x1 = x - this.image().width / 2;
    float x2 = x + this.image().width / 2;
    float y1 = y - this.image().height / 2;
    float y2 = y + this.image().height / 2;

    float x3 = thing.x - thing.image().width / 2;
    float x4 = thing.x + thing.image().width / 2;
    float y3 = thing.y - thing.image().height / 2;
    float y4 = thing.y + thing.image().height / 2;

    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
  }

}