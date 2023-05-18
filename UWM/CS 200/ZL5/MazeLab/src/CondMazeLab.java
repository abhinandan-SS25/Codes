///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Code to solve multiple varying difficulty mazes
// Course:          CS200, Fall 2022
//
// Author:          Abhinandan Saha
// Email:           asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
// "No help given or received."

/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


import java.util.Random;
public class CondMazeLab extends Maze 
{
    public static void main(String[] args) {
       
        // Step 1: Run Maze Simulation
        CondMazeLab myMaze = new CondMazeLab();
    }

    public void step() 
    {
        Random rand = new Random();
        // Steps 2 and 3: Implement HERE
        if (puss.isFacingWall()) {

            if (rand.nextInt(2)==0) {
                puss.left();
            }
            else {
                puss.right();
            }


            if (puss.isFacingWall()) {
                puss.left();
                puss.left();
            }
        }
        else if (puss.isFacingGully()) {
            if (puss.isTipToeing()) {
                puss.stopTipToe();
            }
            if (puss.isInBoots()) {
                puss.takeOffBoots();
            }
            puss.jump();
        }
        else if (puss.isFacingDog()) {
            if (puss.isInBoots()) {
                puss.takeOffBoots();
            }
            puss.startTipToe();
            puss.forward();
        }
        else if (puss.isFacingMud()) {
            if (puss.isTipToeing()) {
                puss.stopTipToe();
            }
            puss.putOnBoots();
            puss.forward();
        }
        else {
            puss.forward();
            if (rand.nextInt(2)==0) {
                puss.left();
            }
            else {
                puss.right();
            }
        }
    }
    
    public CondMazeLab() { super(true); }
}