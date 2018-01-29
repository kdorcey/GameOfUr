package com.royalgameofur.game.GameLogic_CurrentlyUnused;

/**
 * Created by Kyle on 1/28/2018.
 */

public class StoneTest {

    public static int moveCount = 0;

    public static void move(int x){
        moveCount++;
    }
    public static int getMoveCount(){
        return moveCount;
    }
}
