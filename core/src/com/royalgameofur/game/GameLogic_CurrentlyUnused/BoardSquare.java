package com.royalgameofur.game.GameLogic_CurrentlyUnused;

public class BoardSquare {

    private boolean squareVisible;
    private boolean safeSquare; //true if safe space
    private boolean playerOnSquare; //true if player is on space
    private Player playerNumberOnSquare;
    private Stones stoneOnSquare;
    private int xLocation; //x location of square on board
    private int yLocation; //y location of square on board
    private final int xStartLocation = -1;
    private final int yStartLocation = -1;
    private final int xEndLocation = 10;
    private final int yEndLocation = 10;

    public BoardSquare(int xLocation, int yLocation)
    {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        squareVisible = true;
        safeSquare = false;
        playerOnSquare = false;
    }

    public void setSquareInvisible(){
        squareVisible = false;
    }
    public void setSafeSquare(){
        safeSquare = true;
    }

    public void setPlayerOnSquareBoolean(){
        this.playerOnSquare = true;
    }
    public void setPlayerNumberOnSquare(Player stoneOwner){
        this.playerNumberOnSquare = stoneOwner;
    }
    public void setStoneOnSquare(Stones stoneOnSquare){
        this.stoneOnSquare = stoneOnSquare;
    }
    public Stones getStoneOnSquare(){
        return stoneOnSquare;
    }
    public Player getPlayerNumberOnSquare(){
        return playerNumberOnSquare;
    }

    public void playerOffSquare(){
        playerOnSquare = false;
        playerNumberOnSquare = null;
    }
    public boolean isSquareVisible() { return squareVisible; }
    public boolean isSafeSquare() { return safeSquare; }
    public boolean isPlayerOnSquare() {
        return playerOnSquare; }
    public int getxLocation() { return xLocation; }
    public int getyLocation() { return yLocation; }





}
