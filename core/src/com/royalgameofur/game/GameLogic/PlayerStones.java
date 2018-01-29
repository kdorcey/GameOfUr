package com.royalgameofur.game.GameLogic;

/**
 * Created by Kyle on 1/28/2018.
 */
import java.util.HashMap;
public class PlayerStones {
    private int spacesMoved;
    private int playerNumber;
    

    public PlayerStones(int playerNumber){
        //will always be 0 or 1. 0 is black, 1 is white
        this.playerNumber = playerNumber;
        spacesMoved = 0;

    }


    public void moveStone(int diceRoll){
        spacesMoved += diceRoll;
    }
    public int getSpacesMoved(){
        return spacesMoved;
    }


}
