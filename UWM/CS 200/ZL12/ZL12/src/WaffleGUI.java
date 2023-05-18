/*
Normally comments are intended for someone reading the code that is already familiar
with the Java libraries and concepts.  Since this is a first introduction to many of
these and not even an expectation of CS200, I've added a bunch of explanatory comments
throughout intended to help guide interested CS200 students.
*/

//AWT (Abstract Windowing Toolkit) is the original Java GUI (Graphical User Interface)
//but has differences depending on the platform (Mac, Windows, etc) that it is run on.
//https://docs.oracle.com/javase/8/docs/technotes/guides/awt/index.html
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//Swing also is a Java GUI and is intended to have the same look and feel independent of
//the platform.
//https://docs.oracle.com/javase/8/docs/technotes/guides/swing/index.html
//JavaFX is newer but there is still a lot of Swing code so understanding Swing
//is valuable: https://www.infiraise.com/what-is-the-best-java-framework-for-desktop-applications/
import javax.swing.*;

/**
 * This is to create the main panel containing the grid and number of swaps remaining message to be
 * put into the main window.
 */
class MainPanel extends JPanel implements ActionListener {
    //A JPanel is a Swing visual component container. Since MainPanel extends from JPanel,
    //MainPanel is also a JPanel but with some specialization such as layout and specific
    //components within it. Implementing ActionListener means this class will have
    //an actionPerformed method that will be called, as configured below, when a ToggleButton is
    //clicked.

    //Colors and font for the buttons
    //the color numbers were obtained by using Windows Paint Color Picker on an image
    //from our inspiration: https://wafflegame.net/
    private static final Color CORRECT_COLOR = new Color(111, 176, 92);
    private static final Color POSITION_WRONG_COLOR = new Color(233, 186, 58);
    private static final Color NOT_IN_WORD_COLOR = new Color(237, 239, 241);
    private static final Font LETTER_FONT = new Font("Arial", Font.BOLD, 25);

    //The following are all instance variables (not class/static) so in theory we could
    //instantiate multiple MainPanels that are different, such as a multiple waffle game.
    //Currently, we only create 1 instance.

    //waffle puzzle, solution and hints arrays
    private char[][] puzzle;
    private char[][] solution;
    private Hint[][] hints;

    //save all the references to the buttons in the correct locations so we can find
    //the coordinates when we know the button reference, useful in the actionPerformed method.
    private JToggleButton[][] buttons;

    //JToggleButton is a Swing component we are using for each letter in the waffle.
    //These are used for keeping track of the first button clicked and the second so that their
    //letters can be swapped. These are automatically initialized to null, however, we are
    //explicitly doing so since null is a sentinel value we look for and not everyone may realize
    //they are automatically initialized to null;
    private JToggleButton firstButton = null;
    private JToggleButton secondButton = null;

    //Swing component showing the user how many swaps are remaining
    private JLabel swapsRemainingLabel = new JLabel();

    private int swapsRemaining = 15;

    /**
     * This initializes a panel for a specific puzzle and its solution.
     *
     * @param puzzle Contains the starting state of a waffle puzzle.
     * @param solution Contains the solution of the waffle puzzle.
     */
    public MainPanel(char[][] puzzle, char[][] solution) {
        //if this was a standalone class then more parameter checking would be beneficial. Since
        //this is only intended to be called from WaffleGUI, also in this file, and we
        // aren't testing this separately for partial credit, so additional
        //checks aren't believed to be necessary.

        this.puzzle = puzzle;
        this.solution = solution;
        this.hints = new Hint[puzzle.length][puzzle[0].length];
        this.buttons = new JToggleButton[puzzle.length][puzzle[0].length];

        //BorderLayout organizes the container into North, South, East, West and Center areas.
        //We will put the waffle grid in the Center and then the swapsRemainingLabel in the
        //South.
        this.setLayout(new BorderLayout());

        //build a panel that contains all the grid buttons, one button per grid position.
        JPanel wafflePanel = new JPanel();
        GridLayout gridLayout = new GridLayout(puzzle.length, puzzle[0].length);
        wafflePanel.setLayout(gridLayout);
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[row].length; col++) {
                //create a new button for each position, the text argument was only for
                //debug purposes and will be overwritten.
                JToggleButton button = new JToggleButton("" + row + "," + col);

                //setting the font for the text on the button.
                button.setFont(LETTER_FONT);

                //When the button is clicked the argument is the instance having the
                // actionPerformed method that will be called.  The answer is 'this' since this
                // class implements ActionListener which includes the method actionPerformed below.
                button.addActionListener(this);

                //add the button to the panel, the buttons will be organized in a grid layout
                //since we set the layout above for the whole wafflePanel.
                wafflePanel.add(button);

                //save the reference to each button.  We will use this later to find the
                //coordinates when we have the button reference in the actionPerformed method.
                buttons[row][col] = button;
            }
        }

        //add the waffle panel (grid) to the center of the MainPanel
        this.add(wafflePanel, BorderLayout.CENTER);

        //center the label in the south area of the MainPanel
        swapsRemainingLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(swapsRemainingLabel, BorderLayout.SOUTH);

        //update the panel with the initial puzzle and hints.
        updatePanel();
    }

    /**
     * This is called initially and after each swap to update the buttons and the swaps remaining
     * to show the current state of the game.
     */
    public void updatePanel() {
        WaffleGame.identifyHints(puzzle, solution, hints);

        //update the background colors of each button to give the hints.
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[row].length; col++) {
                buttons[row][col].setText("" + puzzle[row][col]);
                switch (hints[row][col]) {
                    case CORRECT:
                        buttons[row][col].setBackground(CORRECT_COLOR);
                        break;
                    case WRONG_POSITION:
                        buttons[row][col].setBackground(POSITION_WRONG_COLOR);
                        break;
                    case NOT_IN_WORD:
                        buttons[row][col].setBackground(NOT_IN_WORD_COLOR);
                        break;
                    case BLANK:
                        //disable to button so it can't be clicked.
                        buttons[row][col].setEnabled(false);
                        break;
                }
            }
        }

        //update the text on the label with the current swaps remaining.
        swapsRemainingLabel.setText(swapsRemaining + " SWAPS REMAINING");

        boolean success = WaffleGame.waffleCompleted(puzzle, solution);
        if (success || swapsRemaining <= 0) {

            //prevent interaction with the buttons
            for (int row = 0; row < puzzle.length; row++) {
                for (int col = 0; col < puzzle[row].length; col++) {
                    buttons[row][col].setEnabled(false);
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Congratulations!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sorry - no swaps remaining.");
            }
        }

        //repaint (redraw) this component on the screen based on the changes we made above.
        this.repaint();
    }

    /**
     * This method is called each time a button is clicked since we added an instance of this class
     * as the actionListener to each button.
     *
     * @param event Contains information about the event such as the button that was clicked.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        //get the button that was pressed.  Since this should only have been added as the
        //actionListener for JToggleButton this should always be true, but just being safe...
        if ( event.getSource() instanceof JToggleButton) {
            //since we now know the source button reference is a JToggleButton we down cast
            //to JToggleButton to use it.
            JToggleButton button = (JToggleButton) event.getSource();

            //identify the first button click and the second.  The second click results in the
            //buttons swapping characters.
            if (firstButton == null) {
                firstButton = button;
                button.setSelected(true);  //shows indented
            } else if (firstButton == button) {  //same button a second time
                button.setSelected(false);  //return to un-indented
                firstButton = null;
            } else {
                //have two buttons selected, so lets swap the characters
                secondButton = button;

                //find coordinates of characters so that we can call WaffleGame.swap to update
                // our puzzle data structure.
                int sourceRow = -1;
                int sourceCol = -1;
                int currentRow = -1;
                int currentCol = -1;
                for (int row = 0; row < buttons.length; row++) {
                    for (int col = 0; col < buttons[row].length; col++) {
                        if (buttons[row][col] == secondButton) {
                            currentRow = row;
                            currentCol = col;
                        }
                        if (buttons[row][col] == firstButton) {
                            sourceRow = row;
                            sourceCol = col;
                        }
                    }
                }

                //do the swapping within the puzzle data structure
                if (WaffleGame.swap(puzzle, sourceRow, sourceCol, currentRow, currentCol)) {

                    //unselect and clear the first and second buttons, getting ready for future
                    //button clicks.
                    secondButton.setSelected(false);
                    firstButton.setSelected(false);
                    secondButton = null;
                    firstButton = null;
                    swapsRemaining--;

                    //update panel which will generate new hints and update the grid with the
                    //current data structure (puzzle)
                    updatePanel();
                }
            }
        }
    }
}

/**
 * This is the class that creates the Waffle GUI (Graphical User Interface) and
 * configures the game.
 */
public class WaffleGUI {

    /**
     * This prompts the user for a puzzle number, loads the puzzle and starts the game.
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException {
        final String WAFFLE_FILENAME = "waffles.txt";
        final int WAFFLE_SIZE = 5;
        char[][] puzzle = new char[WAFFLE_SIZE][WAFFLE_SIZE];
        char[][] solution = new char[WAFFLE_SIZE][WAFFLE_SIZE];

        //obtain a valid puzzle number from the user.
        int puzzleNumber = 0;
        boolean havePuzzleNumber = false;
        do {
            String puzzleNumberStr = JOptionPane.showInputDialog(
                    "Enter a waffle puzzle number from 1 to 10.", 1);
            if (puzzleNumberStr == null) {  //cancel pressed
                return;
            }
            try {
                puzzleNumber = Integer.parseInt(puzzleNumberStr);
                havePuzzleNumber = puzzleNumber >= 1 && puzzleNumber <= 10;
            } catch (NumberFormatException e) {
                havePuzzleNumber = false;
            }
        } while (!havePuzzleNumber);

        String loadPuzzleResult = WaffleGame.loadPuzzle(WAFFLE_FILENAME, puzzleNumber, puzzle, solution);
        if (loadPuzzleResult != null) {
            JOptionPane.showMessageDialog(null, loadPuzzleResult,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //create the window
        JFrame window = new JFrame();
        window.setTitle("Waffle #" + puzzleNumber);

        //create the panel with the grid and swaps remaining, put it into the center
        //of the main window, set the size and make it visible.
        MainPanel puzzlePanel = new MainPanel(puzzle, solution);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.getContentPane().add(puzzlePanel, BorderLayout.CENTER);
        window.setSize(320, 240);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
