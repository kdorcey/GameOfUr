package com.royalgameofur.game.GameLogic;

import com.royalgameofur.game.States.StoneObjects;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Random;

/**
 * Created by Kyle on 1/28/2018.
 */

public class MoveManager {
    private Random dice; //random number generator used to simulate dicerolls
    private int diceRoll; //dice roll
    private int turnCount; //integer used to count each turn (used mostly to determine whos turn it is)
    private boolean diceRollPermisssion; //used to check if a new dice roll can be made (returns false if it can't)
    private StoneObjects blackStones[]; //used to track stone locations
    private StoneObjects whiteStones[]; //used to track white stone locations

    private Stack<Integer> unusedWhiteStones;
    private ArrayList<Integer> whiteStonesInUse;
    private Stack<Integer> unusedBlackStones;
    private ArrayList<Integer> blackStonesInUse;

    private int completedBlackStoneCount;
    private int completeWhiteStoneCount;

    private int diceTexture;

    private boolean win;
    private boolean blackWin;
    private boolean whiteWin;

    Scanner reader;


    public MoveManager(){
        //playerNumber will always either be 1 or 2 (representing player 1 and 2 respectively)
        dice = new Random();
        diceTexture = -1;

        reader = new Scanner(System.in);
        turnCount = 0;

        diceRoll = -1;

        completedBlackStoneCount = 0;
        completeWhiteStoneCount = 0;

        unusedWhiteStones = new Stack<Integer>();
        unusedBlackStones = new Stack<Integer>();

        whiteStonesInUse = new ArrayList<Integer>();
        blackStonesInUse = new ArrayList<Integer>();

        blackStones = new StoneObjects[7];
        whiteStones = new StoneObjects[7];

        for(int i =0; i<7; i++){
            whiteStones[i] = new StoneObjects(1);
            blackStones[i] = new StoneObjects(2);

            unusedWhiteStones.add(i);
            unusedBlackStones.add(i);
        }
        win = false;
        blackWin = false;
        whiteWin = false;

        diceRollPermisssion = true;
    }


    public boolean stonePoolLegalMoveCheck(int colorToCheck){
        boolean poolLegalCheck = true;
        StoneObjects testStone = new StoneObjects(1);
        ArrayList<Integer> stonesInUse = new ArrayList();
        StoneObjects[] stonesToCheck = new StoneObjects[]{};

        if(colorToCheck == 1){
            int testStoneNumber = unusedWhiteStones.get(0);
            testStone = whiteStones[testStoneNumber];
            stonesInUse = whiteStonesInUse;
            stonesToCheck = whiteStones;

        }
        else if (colorToCheck == 2){
            int testStoneNumber = unusedBlackStones.get(0);
            testStone = blackStones[testStoneNumber];
            stonesInUse = blackStonesInUse;
            stonesToCheck = blackStones;
        }

        if(stonesInUse.size()>0){
            for(int stonesToCheckNumber: stonesInUse) {
                if(testStone.testMove(diceRoll).equals(stonesToCheck[stonesToCheckNumber].getCurrentLocation())){
                    poolLegalCheck = false;
                }
            }
        }
        return poolLegalCheck;


    }

    public boolean legalMoveCheck(int colorToCheck, int stoneNumberToCheck){
        //colorToCheck = 1 when white, colorToCheck = 2 when black
        boolean legalCheck = true;
        Point testMove = new Point();
        StoneObjects stoneToCheck = new StoneObjects(1);

        if(colorToCheck == 1){stoneToCheck = whiteStones[stoneNumberToCheck];}
        else if(colorToCheck ==2){stoneToCheck = blackStones[stoneNumberToCheck];}

        testMove = stoneToCheck.testMove(diceRoll);

        //makes sure that the stone is only finishing with a legal
        if(stoneToCheck.getTotalMoves() + diceRoll >15){
            legalCheck = false;
        }
        else{
            //checks that a player doesn't land on their own piece
            if(colorToCheck == 1){
                for(int whiteStoneToCheck:whiteStonesInUse){
                    if(!whiteStones[whiteStoneToCheck].isFinished()
                            &&whiteStones[whiteStoneToCheck].getCurrentLocation().equals(testMove)) {
                        legalCheck = false;

                    }
                }

                //in the event that it would land on an enemy stone, this makes sure that
                //the enemy stone is not on a safe space
                for(int blackStonesToCheck:blackStonesInUse){
                    if(!blackStones[blackStonesToCheck].isFinished()
                            &&blackStones[blackStonesToCheck].getCurrentLocation().equals(testMove)){
                        if(blackStones[blackStonesToCheck].isStoneOnSafeSpace()) {
                                legalCheck = false;
                            }

                        }
                    }
                }

            else if(colorToCheck ==2){
                for(int blackStoneToCheck:blackStonesInUse){
                    if(blackStones[blackStoneToCheck].getCurrentLocation().equals(testMove)){
                        legalCheck = false;
                    }

                }
                //in the event that it would land on an enemy stone, this makes sure that
                //the enemy stone is not on a safe space
                for(int whiteStoneToCheck:whiteStonesInUse){
                    if(whiteStones[whiteStoneToCheck].getCurrentLocation().equals(testMove)){
                        if(whiteStones[whiteStoneToCheck].isStoneOnSafeSpace()){
                            legalCheck = false;


                        }
                    }
                }
            }

        }
        return legalCheck;
    }


    public void rollDice(){
        diceRoll = dice.nextInt(5);
        diceTexture = diceRoll;
        if(diceRoll ==0){
            turnCount++;
            diceTexture = 0;
            diceRollPermisssion = true;

        }
    }
    public int getPlayerTurnNumber(){
        //returns 1 if player 1's turn, 2 if player 2's
        if(turnCount%2 ==0){
            return 2;
        }
        else{
            return 1;
        }
    }

    //used to set that a players turn is in progress
    public void diceRollInProgress(){
        diceTexture = -1;
        diceRollPermisssion = false;
    }

    public void stoneNextTurn(StoneObjects stoneJustMoved){
        stoneJustMoved.stoneClicked(diceRoll);

        if(stoneJustMoved.getColor() ==1){
            for(int i = 0; i<blackStones.length; i++){
                if(stoneJustMoved.getCurrentLocation().equals(blackStones[i].getCurrentLocation())){
                    resetBlackStones(i);
                }
            }
        }
        if(stoneJustMoved.getColor() ==2){
            for(int i = 0; i<whiteStones.length; i++){
                if(stoneJustMoved.getCurrentLocation().equals(whiteStones[i].getCurrentLocation())){
                    resetWhiteStone(i);
                }
            }
        }

        //fixes an issue that would result in a "go again" not working
        try{Thread.sleep(20);} catch (InterruptedException e){System.out.println("Sleep error in MovementManager "+e);}

        if(stoneJustMoved.isStoneOnSafeSpace() == true){
            diceRollPermisssion = true;
            diceTexture = -2;
        }
        else{
            if(stoneJustMoved.getTotalMoves() == 15){
                if(stoneJustMoved.getColor() ==1){

                    completeWhiteStoneCount++;
                }
                else if (stoneJustMoved.getColor() ==2){
                    completedBlackStoneCount++;
                }
            }
            nextTurn();
        }
    }
    public void nextTurn(){
        turnCount++;
        diceRollPermisssion  = true;
        diceTexture = -1;
    }

    public boolean winCheck(){
        if(completedBlackStoneCount ==15){
            blackWin = true;
            whiteWin = false;
            win = true;
        }
        else if (completeWhiteStoneCount ==15){
            whiteWin = true;
            blackWin = false;
            win = true;
        }
        else{
            whiteWin = false;
            blackWin = false;
            win = false;
        }
        return win;

    }



    public StoneObjects deployWhiteStone(){
        int whiteStoneToDeploy = unusedWhiteStones.pop();
        whiteStonesInUse.add(whiteStoneToDeploy);
        return whiteStones[whiteStoneToDeploy];
    }
    public StoneObjects deployBlackStone(){
        int blackStoneToDeploy = unusedBlackStones.pop();
        blackStonesInUse.add(blackStoneToDeploy);
        return blackStones[blackStoneToDeploy];
    }

    public void resetWhiteStone(int whiteStoneNumber){
        unusedWhiteStones.push(whiteStoneNumber);
        whiteStonesInUse.remove((Integer)whiteStoneNumber);
        whiteStones[whiteStoneNumber].resetStone();
    }
    public void resetBlackStones(int blackStoneNumber){
        unusedBlackStones.push(blackStoneNumber);
        blackStonesInUse.remove((Integer)blackStoneNumber);
        blackStones[blackStoneNumber].resetStone();
    }

    public ArrayList<Integer> getWhiteStonesInUse(){
        return whiteStonesInUse;
    }
    public ArrayList<Integer> getBlackStonesInUse(){
        return blackStonesInUse;
    }

    public  StoneObjects[] getBlackStones(){
        return blackStones;
    }
    public StoneObjects[] getWhiteStones(){
        return whiteStones;
    }
    public Stack<Integer> getUnusedWhiteStones(){
        return unusedWhiteStones;
    }
    public Stack<Integer> getUnusedBlackStones(){
        return unusedBlackStones;
    }

    public boolean diceRollPermisssionStatus(){
        return diceRollPermisssion;
    }
    public int getTurnCount(){
        return turnCount;
    }
    public int getDiceRoll(){
        return diceRoll;
    }
    public int getDiceTexture(){
        return diceTexture;
    }

    public int getCompletedBlackStoneCount(){
        return completedBlackStoneCount;
    }
    public int getCompleteWhiteStoneCount(){
        return completeWhiteStoneCount;
    }

}
