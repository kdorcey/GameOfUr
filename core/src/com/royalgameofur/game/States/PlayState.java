package com.royalgameofur.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.royalgameofur.game.GameLogic.MoveManager;
import com.royalgameofur.game.GameOfUrDemo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyle on 1/16/2018.
 */

public class PlayState extends State {
    private Texture board;
    //squares go from left to right from 0-7. 4 and 5 are invisible in rows 0 and 1
    //rows 0 and 2 have safe spaces at 0 and 6
    //row 2 has a safe space at 3
    private static Sprite square[][];
    private Texture dicePool;
    private Texture diceRoll0, diceRoll1, diceRoll2, diceRoll3;
    private static Texture background;

    private Sprite dice;

    private StoneObjects stoneObjectsManager;

    private HashMap<Integer, Sprite> blackStonePoolTextures;
    private HashMap<Integer, Sprite> whiteStonePoolTextures;

    private ArrayList<Integer> whiteStonesOnBoard;
    private ArrayList<Integer> blackStonesOnBoard;

    private MoveManager gameRunner;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        gameRunner = new MoveManager();

        background = new Texture("PlayStateBackground.jpg");
        diceRoll0 = new Texture("DiceRoll0.png");
        diceRoll1 = new Texture("DiceRoll1.png");
        diceRoll2 = new Texture("DiceRoll2.png");
        diceRoll3 = new Texture("DiceRoll3.png");
        dicePool = diceRoll0;

        whiteStonePoolTextures = fillStonePoolTextures(1);
        blackStonePoolTextures = fillStonePoolTextures(2);

        dice = new Sprite (new TextureRegion(dicePool));
        dice.setPosition(102, 0); //convert these to ratios

        square = new Sprite[8][3];

        blackStonesOnBoard = new ArrayList<Integer>(); //used to keep track of white stones on board
        whiteStonesOnBoard = new ArrayList<Integer>(); //used to keep track of black stones on board

        createSquares();

        stoneObjectsManager = new StoneObjects(2);
    }
    private static HashMap<Integer, Sprite> fillStonePoolTextures(int stonePoolColor){
        //color =1 when white, color =2 when black
        HashMap<Integer, Sprite> filledHashMap = new HashMap<Integer, Sprite>();
        if(stonePoolColor ==1){
            filledHashMap.put(1,new Sprite(new TextureRegion(new Texture("WhiteStonePool1.png"))));
            filledHashMap.put(2,new Sprite(new TextureRegion(new Texture("WhiteStonePool2.png"))));
            filledHashMap.put(3,new Sprite(new TextureRegion(new Texture("WhiteStonePool3.png"))));
            filledHashMap.put(4,new Sprite(new TextureRegion(new Texture("WhiteStonePool4.png"))));
            filledHashMap.put(5,new Sprite(new TextureRegion(new Texture("WhiteStonePool5.png"))));
            filledHashMap.put(6,new Sprite(new TextureRegion(new Texture("WhiteStonePool6.png"))));
            filledHashMap.put(7,new Sprite(new TextureRegion(new Texture("WhiteStonePool7.png"))));
        }
        else if(stonePoolColor ==2) {
            filledHashMap.put(1,new Sprite(new TextureRegion(new Texture("BlackStonePool1.png"))));
            filledHashMap.put(2,new Sprite(new TextureRegion(new Texture("BlackStonePool2.png"))));
            filledHashMap.put(3,new Sprite(new TextureRegion(new Texture("BlackStonePool3.png"))));
            filledHashMap.put(4,new Sprite(new TextureRegion(new Texture("BlackStonePool4.png"))));
            filledHashMap.put(5,new Sprite(new TextureRegion(new Texture("BlackStonePool5.png"))));
            filledHashMap.put(6,new Sprite(new TextureRegion(new Texture("BlackStonePool6.png"))));
            filledHashMap.put(7,new Sprite(new TextureRegion(new Texture("BlackStonePool7.png"))));
        }

        return filledHashMap;
    }


    private static void createSquares(){
        //will probably be used to instantiate "Square" objects later on but rn it's just adding the square sprites
        //squares are 88x88
        int yPosition = 0;
        int xPosition = 108;
        for(int y=0; y<8; y++){
            for(int x=0; x<3; x++){
                if ((y==4 && x==0) || (y==4 && x==2) || (y==5 && x==0) || (y==5 && x==2) ){
                    square[y][x] = new Sprite(new Texture("invisibleSquare.jpg"));

                }
                else{
                    square[y][x] = new Sprite(new Texture("square0.0.jpg"));
                }
                if(y==0){
                    yPosition=100;
                }
                if(x==0){
                    xPosition = 108;
                }
                square[y][x].setPosition(xPosition,yPosition);

                xPosition +=88;
            }
            yPosition += 88;
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            System.out.println(Gdx.input.getX() + ", " + (Gdx.input.getY()));

            if (dice.getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                    && gameRunner.diceRollPermisssionStatus() == true) {

            //MAKE THIS LESS WORSE//////////////////////////////////////
                gameRunner.diceRollInProgress();
                gameRunner.rollDice();
                System.out.println("Dice Rolled " + gameRunner.getDiceRoll());
                if (gameRunner.getDiceRoll() == 0) {
                    dice.setTexture(diceRoll0);
                } else if (gameRunner.getDiceRoll() == 1) {
                    dice.setTexture(diceRoll1);
                } else if (gameRunner.getDiceRoll() == 2) {
                    dice.setTexture(diceRoll2);
                } else if (gameRunner.getDiceRoll() == 3) {
                    dice.setTexture(diceRoll3);
                }
            }
            ////////////////////////////////////////////////////////////

            //detects if the black stone pool has been clicked, might make this its own  method
            if((blackStonePoolTextures.get(gameRunner.getUnusedBlackStones().size()).getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY()))
                    && gameRunner.getPlayerTurnNumber() == 2 && gameRunner.diceRollPermisssionStatus() == false)
            {
                System.out.println("Black Move");
                updateStonePool();

                gameRunner.deployBlackStone().stoneClicked(gameRunner.getDiceRoll());
                gameRunner.nextTurn();
            }

            //checks if the white stone pool was clicked, might make this it's own method
            if (whiteStonePoolTextures.get(gameRunner.getUnusedWhiteStones().size()).getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                    && gameRunner.getPlayerTurnNumber() ==1 && gameRunner.diceRollPermisssionStatus() == false)
            {
                System.out.println("White Move");
                gameRunner.deployWhiteStone().stoneClicked(gameRunner.getDiceRoll());
                gameRunner.nextTurn();
            }

            //checks if a white stone has been clicked
            for(int whiteStones:gameRunner.getWhiteStonesInUse()){
                StoneObjects stoneToCheck = gameRunner.getWhiteStones()[whiteStones];

                if(stoneToCheck.getStone().getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                        && gameRunner.getPlayerTurnNumber() ==1 && gameRunner.diceRollPermisssionStatus() == false){
                    stoneToCheck.stoneClicked(gameRunner.getDiceRoll());
                }
            }
            //checks if a black stone has been clicked
            for(int blackStones:gameRunner.getBlackStonesInUse()){
                StoneObjects stoneToCheck = gameRunner.getBlackStones()[blackStones];

                if(stoneToCheck.getStone().getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                        && gameRunner.getPlayerTurnNumber() == 2 && gameRunner.diceRollPermisssionStatus() == false){
                    stoneToCheck.stoneClicked(gameRunner.getDiceRoll());
                }
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameOfUrDemo.width, GameOfUrDemo.height);
        for(int y=0; y<8; y++){
            for(int x=0; x<3; x++){
                square[y][x].draw(sb);
            }
        }
        dice.draw(sb);

        blackStonePoolTextures.get(gameRunner.getUnusedBlackStones().size()).setPosition(20,500);
        blackStonePoolTextures.get(gameRunner.getUnusedBlackStones().size()).draw(sb);
        whiteStonePoolTextures.get(gameRunner.getUnusedWhiteStones().size()).setPosition(300,500);
        whiteStonePoolTextures.get(gameRunner.getUnusedWhiteStones().size()).draw(sb);

        updateStonePosition(sb);

        sb.end();
    }

    private void updateStonePosition(SpriteBatch sb){
        for(int blackStoneNumber : gameRunner.getBlackStonesInUse()){
            StoneObjects stoneToUpdate = gameRunner.getBlackStones()[blackStoneNumber];

            if((stoneToUpdate.getLastX() != stoneToUpdate.getCurrentX()) || (stoneToUpdate.getLastY() != stoneToUpdate.getCurrentY())){
                if(stoneToUpdate.getLastX() != stoneToUpdate.getCurrentX()){
                    stoneToUpdate.moveX();
                }
                if(stoneToUpdate.getLastY() != stoneToUpdate.getCurrentY()){
                    stoneToUpdate.moveY();
                }
            }

            stoneToUpdate.getStone().setPosition(stoneToUpdate.getLastX(), stoneToUpdate.getLastY());
            stoneToUpdate.getStone().draw(sb);
        }

        for(int whiteStoneNumber : gameRunner.getWhiteStonesInUse()){
            StoneObjects stoneToUpdate = gameRunner.getWhiteStones()[whiteStoneNumber];

            if((stoneToUpdate.getLastX() != stoneToUpdate.getCurrentX()) || (stoneToUpdate.getLastY() != stoneToUpdate.getCurrentY())){
                if(stoneToUpdate.getLastX() != stoneToUpdate.getCurrentX()){
                    stoneToUpdate.moveX();
                }
                if(stoneToUpdate.getLastY() != stoneToUpdate.getCurrentY()){
                    stoneToUpdate.moveY();
                }
            }

            stoneToUpdate.getStone().setPosition(stoneToUpdate.getLastX(), stoneToUpdate.getLastY());
            stoneToUpdate.getStone().draw(sb);
        }
    }

    private void updateStonePool(){

    }

}
