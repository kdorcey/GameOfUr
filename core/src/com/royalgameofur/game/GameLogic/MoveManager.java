package com.royalgameofur.game.GameLogic;

import com.royalgameofur.game.GameLogic_CurrentlyUnused.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kyle on 1/28/2018.
 */

public class MoveManager {
    private Random dice;
    private int diceRoll;
    private PlayerStones blackStones[]; //used to track stone locations
    private PlayerStones whiteStones[]; //used to track white stone locations

    public MoveManager(){
        //playerNumber will always either be 1 or 2 (representing player 1 and 2 respectively)
        dice = new Random();

        for(int i=0; i<7; i++){
            blackStones[i] = new PlayerStones();
            whiteStones[i] = new PlayerStones();
        }
    }



    public void rollDice(){
        diceRoll = dice.nextInt(3);
    }
    public int getDiceRoll(){
        return diceRoll;
    }
}
