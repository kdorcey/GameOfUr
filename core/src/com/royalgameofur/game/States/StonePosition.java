package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.royalgameofur.game.GameLogic.PlayerStones;
import com.royalgameofur.game.GameLogic_CurrentlyUnused.StoneTest;
import com.royalgameofur.game.GameLogic_CurrentlyUnused.Stones;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Kyle on 1/28/2018.
 */

public class StonePosition{


    private Texture stoneTexture;
    private Sprite stone;
    private int color;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;
    private int totalMoves; //each square on the board corresponds to a "total" move count
    private Stones playerStones[];

    private  HashMap<Integer, Point2D.Double> whiteMoveSet = new HashMap<Integer, Point2D.Double>();
    private  HashMap<Integer, Point2D.Double> blackMoveSet = new HashMap<Integer, Point2D.Double>();

    public StonePosition (int color, Sprite square[][]) {
        //color = 1 if white, color = 2 black
        this.color = color;
        this.whiteMoveSet = defineMoveSet(1);
        this.blackMoveSet = defineMoveSet(2);
        stoneTexture = new Texture("SquareBlackStoneTemp.png");
        stone = new Sprite (new TextureRegion(stoneTexture));
        currentX = 20;
        currentY = 500;
        lastX =20;
        lastY=500;
        totalMoves = 0;
    }


    public void stoneClicked(int diceRoll){
        totalMoves += diceRoll;
        System.out.println("diceroll " +diceRoll);
        System.out.println("previous X: "+currentX+"current y "+currentY);

        if(diceRoll !=0) {
            if (color == 1) {
                currentX = (int) whiteMoveSet.get(totalMoves).getX();
                currentY = (int) whiteMoveSet.get(totalMoves).getY();
            } else if (color == 2) {
                currentX = (int) blackMoveSet.get(totalMoves).getX();
                currentY = (int) blackMoveSet.get(totalMoves).getY();
            }
        }
        System.out.println("currentX" +currentX+" currenty "+currentY );
    }


    public void moveX(){
        if(currentX>lastX){
            lastX++;
        }
        else if (currentX<lastX){
            lastX--;
        }
        else{
            System.out.println("end x "+lastX+" = "+currentX);
        }
    }
    public void moveY(){
        if(currentY>lastY){
            lastY++;
        }
        else if (currentY<lastY){
            lastY--;
        }
        else{
            System.out.printf("end y "+lastY +" = "+currentY);
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

    public Sprite getStone(){
        return stone;
    }

    public static HashMap defineMoveSet(int playerNumber){
        HashMap<Integer, Point2D.Double> moveSet = new HashMap<Integer, Point2D.Double>();

        int x = 0;
        int y = 364;
        for(int i = 0; i<=15; i++){
            x = 196;

            //sets all x values
            if(playerNumber == 1){
                if(i==0 || i == 15){
                    System.out.println("white");
                    x = 0;
                }
                else if(i<=4 || i> 12 && i != 15) {
                    x = 284;
                }

            }
            else if (playerNumber == 2){
                if(i == 0 || i == 15){
                    System.out.println("black");
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
            System.out.println(i+" "+x+", "+y);
            if(i == 0){
                y = 364;
            }
        }



        return moveSet;
    }


}
