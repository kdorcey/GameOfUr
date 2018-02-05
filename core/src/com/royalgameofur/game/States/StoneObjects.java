package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.royalgameofur.game.GameLogic_CurrentlyUnused.Stones;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Kyle on 1/28/2018.
 */

public class StoneObjects {


    private Texture stoneTexture;
    private Sprite stone;
    private int color;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;
    private Point2D.Double currentLocation;
    private int totalMoves; //each square on the board corresponds to a "total" move count
    private Stones playerStones[];
    private java.util.List<Integer> safeSpaces;
    private boolean onSafeSpace;
    private boolean finished;
    private boolean goAgain; //this is only true immediately after a stone lands on a safe space. After that player goes again it is set to false

    private  HashMap<Integer, Point2D.Double> whiteMoveSet = new HashMap<Integer, Point2D.Double>();
    private  HashMap<Integer, Point2D.Double> blackMoveSet = new HashMap<Integer, Point2D.Double>();

    public StoneObjects(int color) {
        //color = 1 if white, color = 2 black
        finished = false;
        this.color = color;
        this.safeSpaces = Arrays.asList(4,8,14);
        this.whiteMoveSet = defineMoveSet(1);
        this.blackMoveSet = defineMoveSet(2);

        if(color==1){
            stoneTexture = new Texture("SquareWhiteStoneTemp.png");
            currentX = 300;
            currentY = 500;

            lastX = currentX;
            lastY = currentY;

        }
        else if(color ==2){
            stoneTexture = new Texture("SquareBlackStoneTemp.png");
            currentX = 20;
            currentY = 500;
            lastX = currentX;
            lastY = currentY;


        }

        stone = new Sprite (new TextureRegion(stoneTexture));

        totalMoves = 0;
    }

    public Point2D.Double getCurrentLocation(){
        return currentLocation;
    }
    public boolean isStoneOnSafeSpace(){
        return onSafeSpace;
    }

    public Point2D.Double testMove(int diceRoll){
        int testTotalMove = totalMoves + diceRoll;
        Point2D.Double moveSetToReturn = new Point2D.Double();

        if(color ==1){
            moveSetToReturn = whiteMoveSet.get(testTotalMove);
        }
        else if(color ==2){
            moveSetToReturn = blackMoveSet.get(testTotalMove);
        }

        return moveSetToReturn;
    }


    public void stoneClicked(int diceRoll){
        totalMoves += diceRoll;
        if (totalMoves == 15) {
            finished = true;
        }
        if(diceRoll !=0) {
            if (color == 1) {
                currentX = (int) whiteMoveSet.get(totalMoves).getX();
                currentY = (int) whiteMoveSet.get(totalMoves).getY();
                currentLocation = whiteMoveSet.get(totalMoves);
            } else if (color == 2) {
                currentX = (int) blackMoveSet.get(totalMoves).getX();
                currentY = (int) blackMoveSet.get(totalMoves).getY();
                currentLocation = blackMoveSet.get(totalMoves);
            }

            if(safeSpaces.contains(totalMoves)){
                onSafeSpace = true;
                goAgain = true;
            }
            else{
                onSafeSpace = false;
                goAgain = false;
            }

        }
    }


    public void moveX(){
        if(currentX>lastX){
            lastX += 2;
        }
        else if (currentX<lastX){
            lastX -= 2;
        }

    }
    public void moveY(){
        if(currentY>lastY){
            lastY+=2;
        }
        else if (currentY<lastY){
            lastY-=2;
        }

    }

    public int getLastX(){
        return lastX;
    }
    public int getLastY(){
        return lastY;
    }
    public int getCurrentX(){
        return currentX;
    }
    public int getCurrentY(){
        return currentY;
    }
    public int getTotalMoves() {return totalMoves;}
    public Sprite getStone(){
        return stone;
    }
    public int getColor() {
        return color;
    }
    public void resetStone(){
        totalMoves = 0;
        if(color == 1 ){
            currentX = 300;
            currentY = 500;
            lastX = currentX;
            lastY = currentY;
            currentLocation = whiteMoveSet.get(totalMoves);
        }
        else if (color == 2){
            currentX = 20;
            currentY = 500;
            lastX = currentX;
            lastY = currentY;
            currentLocation = blackMoveSet.get(totalMoves);
        }
    }

    public static HashMap defineMoveSet(int colorNumber){
        //colorNumber ==1 white, colorNumber ==2 black
        HashMap<Integer, Point2D.Double> moveSet = new HashMap<Integer, Point2D.Double>();

        int x = 0;
        int y = 364;
        for(int i = 0; i<15; i++){
            x = 196;

            //sets all x values
            if(colorNumber == 1){
                if(i==0 || i == 15){
                    x = 0;
                }
                else if(i<=4 || i> 12 && i != 15) {
                    x = 284;
                }

            }
            else if (colorNumber == 2){
                if(i == 0 || i == 15){
                    x = 480;
                }
                else if (i<=4 || i>12 && i !=15){
                    x = 108;
                }
            }
            /////
            ///sets all y values
            if(i == 0){
                y = 0;
            }
            if(i<=4 && i != 1){
                y -= 88;
            }
            else if (i>5 && i<13){
                y += 88;
            }
            else if(i == 14){
                y = 628;
            }
            else if (i == 15){
                y = 800;
            }


            moveSet.put(i, new Point2D.Double(x,y));

            if(i == 0){
                y = 364;
            }
        }

        if(colorNumber ==2){
            moveSet.put(15, new Point2D.Double(40,680));
        }
        else if (colorNumber ==1){
            moveSet.put(15, new Point2D.Double(440, 680));
        }



        return moveSet;
    }

    public  HashMap<Integer, Point2D.Double> getWhiteMoveSet(){
        return whiteMoveSet;
    }
    public HashMap<Integer, Point2D.Double> getBlackMoveSet(){
        return blackMoveSet;
    }
    public boolean isFinished(){
        return finished;
    }


}
