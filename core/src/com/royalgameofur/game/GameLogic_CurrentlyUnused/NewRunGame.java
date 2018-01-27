package com.royalgameofur.game.GameLogic_CurrentlyUnused;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NewRunGame {
    private Player player1;
    private Player player2;
    private Player localPlayer;
    private Player connectedPlayer;
    private GameBoard gameBoard;
    private int turnCount;
    private Scanner reader = new Scanner (System.in);
    private Random diceRoll = new Random();

    public NewRunGame(int player1or2){
        this.gameBoard = new GameBoard();
        player1 = new Player(1);
        player2 = new Player(2);

        if(player1or2 ==1) {
            localPlayer = player1;
            connectedPlayer = player2;
        }
        else{
            localPlayer = player2;
            connectedPlayer = player1;
        }
        turnCount = 1;
    }
    public NewRunGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public void selfNextMove(){
        int rollz = diceRoll.nextInt(3);
        //int rollz = Integer.parseInt(reader.nextLine());
        System.out.println("rollz: "+rollz);

        if(rollz !=0) {
            System.out.println("pick stone to move");
            int stoneToMove = Integer.parseInt(reader.nextLine());
            if (localPlayer.checkValidMove(stoneToMove, rollz)) {
                localPlayer.stoneToMove(stoneToMove, rollz, connectedPlayer);
                gameBoard.updateBoard();
                turnCount++;
                try {
                    OnlineGame.sendData(rollz, stoneToMove);
                } catch (IOException error) {
                    System.out.println("Error sending move from host " + error);
                }

            } else {
                System.out.println("Invalid move pick different stone");
            }
        }
        else if (rollz == 0){
            System.out.println("Move skipped!");
            gameBoard.updateBoard();
            turnCount++;

            try {
                OnlineGame.sendData(rollz, 9);
            } catch (IOException error) {
                System.out.println("Error sending move from host " + error);
            }
        }
    }

    public void otherPlayerMove(int enemyRoll, int enemyStoneMove) {
        if (enemyRoll != 0) {

            connectedPlayer.checkValidMove(enemyStoneMove,enemyRoll); //this is a formality that needs to be done in order to check for a "sorry!" move
            connectedPlayer.stoneToMove(enemyStoneMove, enemyRoll, localPlayer);
            gameBoard.updateBoard();
            turnCount++;


        } else if (enemyRoll == 0){
            gameBoard.updateBoard();
            turnCount++;
        }
    }




}
