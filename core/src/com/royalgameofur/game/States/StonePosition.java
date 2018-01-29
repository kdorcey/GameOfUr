package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.royalgameofur.game.GameLogic_CurrentlyUnused.StoneTest;
import com.royalgameofur.game.GameLogic_CurrentlyUnused.Stones;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Kyle on 1/28/2018.
 */

public class StonePosition{
    private Vector2 stonePosition;
    private Vector2 velocity;

    private Texture stoneTexture;
    private Sprite stone;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;
    private Stones playerStones[];

    public StonePosition (int color, Sprite square[][]) {
        //color = 0 if black, color = 1 white
        

        stonePosition = new Vector2(20, 500);
        velocity = new Vector2(0,0);

        stoneTexture = new Texture("SquareBlackStoneTemp.png");
        stone = new Sprite (new TextureRegion(stoneTexture));
        currentX = 20;
        currentY = 500;
        lastX =20;
        lastY=500;
    }
    public void update(float dt){
        velocity.add(currentX,currentY);
        velocity.scl(dt);
        stonePosition.add(velocity.x,velocity.y);
        velocity.scl(1/dt);
    }

    public void stoneClicked(int diceRoll){
        StoneTest.move(diceRoll);
        System.out.println("diceroll " +diceRoll);

        if(StoneTest.getMoveCount() == 1){
            //stone.translate(88,-148);
            currentX = 108;
            currentY = 364;
        }
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

}
