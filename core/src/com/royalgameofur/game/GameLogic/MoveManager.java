package com.royalgameofur.game.GameLogic;

import com.royalgameofur.game.GameLogic_CurrentlyUnused.Player;
import com.royalgameofur.game.States.StonePosition;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kyle on 1/28/2018.
 */

public class MoveManager {
    private Random dice; //random number generator used to simulate dicerolls
    private int diceRoll; //dice roll
    private int turnCount; //integer used to count each turn (used mostly to determine whos turn it is)
    private boolean diceRollPermisssion; //used to check if a new dice roll can be made (returns false if it can't)
    private StonePosition blackStones[]; //used to track stone locations
    private StonePosition whiteStones[]; //used to track white stone locations




    public MoveManager(){
        //playerNumber will always either be 1 or 2 (representing player 1 and 2 respectively)
        dice = new Random();
        turnCount = 0;


        diceRollPermisssion = true;

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

    public boolean diceRollPermisssionStatus(){
        return diceRollPermisssion;
    }

    public void diceRollInProgress(){
        diceRollPermisssion = false;
    }

    public void nextTurn(){
        turnCount++;
        diceRollPermisssion  = true;
    }
    public int getTurnCount(){
        return turnCount;
    }




    public void rollDice(){
        diceRoll = dice.nextInt(4);
    }
    public int getDiceRoll(){
        return diceRoll;
    }
}
