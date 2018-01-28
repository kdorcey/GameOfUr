package com.royalgameofur.game.GameLogic_CurrentlyUnused;

/**
 * Created by Kyle on 1/27/2018.
 */
import java.util.Random;
public class GUIRunGame {

    private static Random diceRoll = new Random();


    public static int diceRoll(){
        int roll = diceRoll.nextInt(4);
        return roll;
    }
}
