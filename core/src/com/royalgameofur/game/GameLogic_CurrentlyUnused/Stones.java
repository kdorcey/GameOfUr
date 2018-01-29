package com.royalgameofur.game.GameLogic_CurrentlyUnused;

import java.util.HashMap;

/***
 * THE GameBoard CLASS MUST BE INSTANTIATED FIRST
 */
public class Stones {
    private Player owner; //represents player 1 or 2
    private HashMap<Integer, BoardSquare> playerMoveSet = new HashMap<Integer, BoardSquare>();
    private int spacesMoved; //0 starting location, 14 ending location
    private static BoardSquare boardLocation[][];
    private int xboardPosition; //where the stone is placed on the board (-1 starting position, 8 ending location)
    private int yboardPosition;
    private boolean stoneFinished;

    public Stones(Player owner){
        boardLocation = GameBoard.getBoardLocation();
        this.owner = owner;
        spacesMoved = 0;
        xboardPosition = -1;
        yboardPosition = -1;
        stoneFinished = false;
    }



    private void coverTracks(){
        if(spacesMoved != 0){
            playerMoveSet.get(spacesMoved).playerOffSquare();
        }
    }

    public void moveStone (int diceRoll){
        coverTracks();
        spacesMoved += diceRoll;
        if(spacesMoved == 14){
            stoneFinished = true;
            System.out.println("StonePosition complete!");
        }
        else {
            playerMoveSet.get(spacesMoved).setPlayerOnSquareBoolean();
            playerMoveSet.get(spacesMoved).setPlayerNumberOnSquare(owner);
            playerMoveSet.get(spacesMoved).setStoneOnSquare(this);
        }

    }

    public boolean moveStoneCheck (int diceRoll){
        boolean validMoveStone = false;
        int testMove = spacesMoved + diceRoll;
        System.out.println("test move: "+testMove);

        if(testMove <14 && diceRoll !=0) //checks that the stone exits with an exact throw
        {
            if (playerMoveSet.get(testMove).isPlayerOnSquare() == true) //checks if a player is on the square
            {
                if (playerMoveSet.get(testMove).getPlayerNumberOnSquare().getPlayerNumber() == owner.getPlayerNumber())  //checks if the player on the square is yourself
                {
                    validMoveStone = false;
                }
                else
                {
                    //reset other player's stone
                    validMoveStone = true;
                    playerMoveSet.get(testMove).getStoneOnSquare().resetStone();

                }
            }
            else{
                validMoveStone = true;
            }
        }
        else if (testMove == 14){
            validMoveStone = true;
        }
        else if(diceRoll ==0){
            validMoveStone = true;
        }
        else{
            validMoveStone = false;
        }

        return validMoveStone;
    }

    public void resetStone(){
        spacesMoved = 0;
        xboardPosition = -1;
        yboardPosition = -1;
    }



    /**
     * move order:
     * player 1: (3,0)->(2,0)->(1,0)->(0,0)->(0,1)->(1,1)->(2,1)...->(7,1)->(7,0)->(6,0)->out
     * player 2: (3,2)->(2,2)->(1,2)->(0,2)->(0,1)->(1,1)->(2,1)...->(7,1)->(7,2)->(6,2)->out
     * @param
     *
     */
    public void defineMoveSet(){
        int playerY =0; //the board has a player specific area. As only that player can move in that zone and each stone is player specific this determines that stones moveset

        if(owner.getPlayerNumber() == 1){
            playerY = 0;
        }
        else if (owner.getPlayerNumber() == 2){
            playerY = 2;
        }

        playerMoveSet.put(0, null);
        playerMoveSet.put(1, boardLocation[3][playerY]);
        playerMoveSet.put(2, boardLocation[2][playerY]);
        playerMoveSet.put(3, boardLocation[1][playerY]);
        playerMoveSet.put(4, boardLocation[0][playerY]);
        playerMoveSet.put(5, boardLocation[0][1]);
        playerMoveSet.put(6, boardLocation[1][1]);
        playerMoveSet.put(7, boardLocation[3][1]);
        playerMoveSet.put(8, boardLocation[4][1]);
        playerMoveSet.put(9, boardLocation[5][1]);
        playerMoveSet.put(10, boardLocation[6][1]);
        playerMoveSet.put(11, boardLocation[7][1]);
        playerMoveSet.put(12, boardLocation[7][playerY]);
        playerMoveSet.put(13, boardLocation[6][playerY]);

    }
    public boolean isStoneFinished(){
        return stoneFinished;
    }

}
