package com.royalgameofur.game.GameLogic_CurrentlyUnused;

/***
 * GAMEBOARD MUST BE INSTANTIATED BEFORE THE PLAYER OBJECTS
 */
public class GameBoard {
    public static final BoardSquare[][] boardLocation = new BoardSquare[8][3];
    public static int turnCount;
    private static int player1or2;

    public GameBoard(){
        //setBoard();
    }



    private static void setBoard(){
        for(int y=0; y<3; y++){
            for(int x=0; x<8; x++){

                boardLocation[x][y] = new BoardSquare(x,y);

                //sets "safe squares"
                if((x==0 && y!=1) || (x==3 && y==1) || (x==6 && y!=1)){
                    boardLocation[x][y].setSafeSquare();
                }

                //hides squares that will not be shown
                if ((x==4 && y==0) || (x==4 && y==2) || (x==5 && y==0) || (x==5 && y==2) ){
                    boardLocation[x][y].setSquareInvisible();
                }
            }
        }
        updateBoard();
    }



    public static void updateBoard(){
        System.out.println("------------------------");
        for(int y=0; y<3; y++){
            for (int x=0; x<8; x++){
                if(boardLocation[x][y].isSquareVisible()){
                    if(boardLocation[x][y].isPlayerOnSquare()){
                        int playerOnSquare = boardLocation[x][y].getPlayerNumberOnSquare().getPlayerNumber();
                        if(playerOnSquare ==1){
                            System.out.print("O");
                        }
                        else{
                            System.out.print("Q");
                        }
                    }
                    else{
                        System.out.print("X");
                    }

                }
                else{
                    System.out.print(" ");
                }

            }
            System.out.println();
        }

    }


    public static BoardSquare[][] getBoardLocation() {
        return boardLocation;
    }


    public static boolean winCheck(Player lastPlayerToMove){
        boolean win = false;
        int finishedStoneCount = 0;

        for(int i=0; i<7; i++){
            if(lastPlayerToMove.getPlayerStones()[i].isStoneFinished()){
                finishedStoneCount++;
            }
        }

        if(finishedStoneCount ==7){
            win = true;
        }

        return win;
    }

}
