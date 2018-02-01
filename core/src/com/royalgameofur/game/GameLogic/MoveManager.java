package com.royalgameofur.game.GameLogic;

import com.royalgameofur.game.States.StoneObjects;
import java.util.ArrayList;
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



    public MoveManager(){
        //playerNumber will always either be 1 or 2 (representing player 1 and 2 respectively)
        dice = new Random();
        turnCount = 0;

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

        diceRollPermisssion = true;

    }

    public void rollDice(){
        diceRoll = dice.nextInt(4);
        if(diceRoll ==0){
            turnCount++;
            System.out.println("TURN SKIPPED");
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

    //used to check if a players turn is in progress
    public void diceRollInProgress(){
        diceRollPermisssion = false;
    }

    public void nextTurn(){
        turnCount++;
        diceRollPermisssion  = true;
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
        whiteStonesInUse.remove(whiteStoneNumber);
    }
    public void resetBlackStones(int blackStoneNumber){
        unusedBlackStones.push(blackStoneNumber);
        blackStonesInUse.remove(blackStoneNumber);
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
}
