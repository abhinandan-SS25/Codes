//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: The main class for the p05 Dancing Badgers III program
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

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

/**
 * This is the main class for the p05 Dancing Bangers III program
 * 
 * @author Abhinandan
 *
 */
public class DancingBadgers extends PApplet {

	private static DanceStep[] badgersDanceSteps = new DanceStep[] { DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT,
			DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT,
			DanceStep.UP }; // array storing badgers dance show steps
	private static float[][] startDancePositions = new float[][] { { 300, 250 }, { 364, 250 }, { 428, 250 },
			{ 492, 250 }, { 556, 250 } }; // array storing
											// the positions
											// of the dancing
											// badgers at the
											// start of the
											// dance show
	private static PImage backgroundImage; // background image
	private static int badgersCountMax; // Maximum number of Badger objects allowed in the basketball
										// court
	private boolean danceShowOn; // Tells whether the dance show is on.
	private static Random randGen; // Generator of random numbers
	private static ArrayList<Thing> things; // ArrayList storing Thing objects

	/**
	 * Sets the size of the display window of this graphic application
	 */
	@Override
	public void settings() {
		this.size(800, 600);
	}

	/**
	 * Defines initial environment properties of this graphic application. This
	 * method initializes all the data fields defined in this class.
	 */
	@Override
	public void setup() {
		Thing.setProcessing(this);
		Badger.setProcessing(this);

		// Initial Setup
		randGen = new Random();
		badgersCountMax = 5;
		backgroundImage = this.loadImage("images" + File.separator + "background.png"); // loading the
																						// background
																						// image
		things = new ArrayList<>();

		// Adding things
		things.add(new Thing(50, 50, "target.png"));
		things.add(new Thing(750, 550, "target.png"));
		things.add(new Thing(750, 50, "shoppingCounter.png"));
		things.add(new Thing(50, 550, "shoppingCounter.png"));
		things.add(new StarshipRobot(things.get(0), things.get(2), 3));
		things.add(new StarshipRobot(things.get(1), things.get(3), 5));
		things.add(new Basketball(50, 300));
		things.add(new Basketball(750, 300));

		this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
		this.textAlign(3, 3); // sets the alignment of the text
		this.imageMode(3); // interprets the x and y position of an image to its center
		this.focused = true; // confirms that this screen is "focused", meaning
		// it is active and will accept mouse and keyboard input.
	}

	/**
	 * Callback method that draws and updates the application display window. This
	 * method runs in an infinite loop until the program exits.
	 */
	@Override
	public void draw() {
		this.background(this.color(255, 218, 185));
		this.image(backgroundImage, this.width / 2, this.height / 2);

		// draws all object in things array
		for (int i = 0; i < things.size(); i++)
			things.get(i).draw();
	}

	/**
	 * Callback method called each time the user presses the mouse. This method
	 * iterates through the list of things. If the mouse is over a Clickable object,
	 * it calls its mousePressed method, then returns.
	 */
	@Override
	public void mousePressed() {
		for (int i = 0; i < things.size(); i++) {
			Thing thingObject = things.get(i);
			if (thingObject instanceof Clickable && thingObject.isMouseOver()) {
				((Clickable) thingObject).mousePressed(); // Casts to Clickable type and then calls function
				return;
			}
		}
	}

	/**
	 * Callback method called each time the mouse is released. This method calls the
	 * mouseReleased() method on every Clickable object stored in the things list.
	 */
	@Override
	public void mouseReleased() {
		for (int i = 0; i < things.size(); i++) {
			Thing thingObject = things.get(i);
			if (thingObject instanceof Clickable) {
				((Clickable) thingObject).mouseReleased(); // Casts to Clickable type and then calls function
			}
		}
	}

	/**
	 * Gets the number of Badger objects present in the basketball arena
	 * 
	 * @return the number of Badger objects present in the basketball arena
	 */
	public int badgersCount() {
		int count = 0;
		for (int i = 0; i < things.size(); i++) {
			if (things.get(i) instanceof Badger) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Sets the badgers start dance positions. The start dance positions of the
	 * badgers are provided in the startDancePositions array. The array
	 * startDancePositions contains badgersCountMax dance positions. If there are
	 * fewer Badger objects in the basketball arena, they will be assigned the first
	 * positions.
	 */
	private void setStartDancePositions() {
		int dancePosition = 0;
		for (int i = 0; i < things.size(); i++) {
			if (things.get(i) instanceof Badger) {
				Badger badgerObject = (Badger) things.get(i);
				badgerObject.x = startDancePositions[dancePosition][0];
				badgerObject.y = startDancePositions[dancePosition][1];
				dancePosition++;
			}
		}
	}

	/**
	 * Callback method called each time the user presses a key. b-key: If the b-key
	 * is pressed and the danceShow is NOT on, a new badger is added to the list of
	 * things. Up to badgersCountMax can be added to the basketball arena.
	 * 
	 * c-key: If the c-key is pressed, the danceShow is terminated (danceShowOn set
	 * to false), and ALL MovingThing objects are removed from the list of things.
	 * This key removes MovingThing objects ONLY.
	 * 
	 * d-key: This key starts the dance show if the danceShowOn was false, and there
	 * is at least one Badger object in the basketball arena. Starting the dance
	 * show, sets the danceShowOn to true, sets the start dance positions of the
	 * Badger objects, and calls the startDancing() method on every Badger object
	 * present in the list of things. Pressing the d-key when the danceShowOn is
	 * true or when there are no Badger objects present in the basketball arena has
	 * no effect.
	 * 
	 * r-key: If the danceShow is NOT on and the r-key is pressed when the mouse is
	 * over a Badger object, this badger is removed from the list of things. If the
	 * mouse is over more than one badger, the badger at the lowest index position
	 * will be deleted.
	 * 
	 * s-key: If the s-key is pressed, the dancdShow is terminated and all the
	 * Badger objects stop dancing. Pressing the s-key does not remove any thing.
	 */
	@Override
	public void keyPressed() {
		switch (Character.toUpperCase(this.key)) {
		case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
					// present in the field is not reached
			if (badgersCount() < badgersCountMax && !danceShowOn) {
				things.add(new Badger(randGen.nextInt(this.width), randGen.nextInt(this.height), badgersDanceSteps));
			}
			break;
		case 'C': // terminates dance show and removes all moving things
			danceShowOn = false;
			// Terminating danceshow
			for (int i = 0; i < things.size(); i++) {
				if (things.get(i) instanceof Badger) {
					Badger badgerObject = (Badger) things.get(i);
					badgerObject.stopDancing();
				}
			}
			// removing moving things
			int index = 0;
			while (index < things.size()) {
				while (index < things.size() && things.get(index) instanceof MovingThing) {
					things.remove(index);
				}
				index++;
			}
			break;
		case 'D': // Start danceshows
			if (badgersCount() > 0 && !danceShowOn) {
				danceShowOn = true;
				this.setStartDancePositions(); // sets the initial dance positions of all badgers
				for (int i = 0; i < things.size(); i++) {
					if (things.get(i) instanceof Badger) {
						Badger badgerObject = (Badger) things.get(i);
						badgerObject.startDancing();
					}
				}
			}
			break;
		case 'R': // delete the badger being pressed
			if (!danceShowOn) {
				for (int i = 0; i < things.size(); i++) {
					if (things.get(i).isMouseOver() && things.get(i) instanceof Badger) {
						things.remove(i);
						break;
					}
				}
			}
			break;
		case 'S': // stops the danceshow
			danceShowOn = false;
			for (int i = 0; i < things.size(); i++) {
				if (things.get(i) instanceof Badger) {
					Badger badgerObject = (Badger) things.get(i);
					badgerObject.stopDancing();
				}
			}
			break;
		}
	}

	/**
	 * Driver method to run this graphic application
	 * 
	 * @param args unused
	 */
	public static void main(String args[]) {
		PApplet.main("DancingBadgers");
	}

}
