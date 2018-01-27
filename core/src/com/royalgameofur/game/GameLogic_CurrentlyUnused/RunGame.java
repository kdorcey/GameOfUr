package com.royalgameofur.game.GameLogic_CurrentlyUnused;

import java.util.Random;
import java.util.Scanner;

public class RunGame {
    public static void runLocally (){
        Random diceRoll = new Random();
        Scanner reader = new Scanner(System.in);
        GameBoard testBoard = new GameBoard();


        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player currentPlayer;
        Player lastPlayer;
        int turnCount =1;
        //int roll = diceRoll.nextInt(3);

        while (true) {

            if (turnCount % 2 == 0) {
                //player 1 will always go first
                currentPlayer = player2;
                lastPlayer = player1;
            } else {
                currentPlayer = player1;
                lastPlayer = player2;
            }

            //int roll = Integer.parseInt(reader.nextLine());
            int roll = diceRoll.nextInt(3);

            System.out.println(currentPlayer + " roll: " + roll);

            System.out.println("pick stone to move");
            int stoneToMove = Integer.parseInt(reader.nextLine());

            if (currentPlayer.checkValidMove(stoneToMove, roll)) {
                currentPlayer.stoneToMove(stoneToMove, roll, lastPlayer);
                testBoard.updateBoard();
                turnCount++;

            } else {
                System.out.println("Invalid move pick different stone");
            }

            if (GameBoard.winCheck(currentPlayer)) {
                System.out.println(currentPlayer + "wins!");
                break;
            }
        }
    }


    public static void nextMove(){

    }

}

