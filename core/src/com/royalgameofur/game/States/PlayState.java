package com.royalgameofur.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
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
    private static AssetManager assets;
    private Sprite square[][];
    private Sprite pauseGear;
    private Sprite helpButton;
    private final Sprite whiteTurnNotification;
    private final Sprite blackTurnNotification;
    private static Texture background;
    private boolean blackTurn;
    private boolean whiteTurn;
    private boolean rollDiceTexture;

    private StoneObjects stoneObjectsManager;

    private HashMap<Integer, Sprite> blackStonePoolTextures;
    private HashMap<Integer, Sprite> whiteStonePoolTextures;
    private HashMap<Integer, Sprite> boardSquareTextures;
    private HashMap<Integer, Sprite> diceTextures;
    private HashMap<Integer, Sprite> finishedBlackStoneTextures;
    private HashMap<Integer, Sprite> finishedWhiteStoneTextures;

    private ArrayList<Integer> whiteStonesOnBoard;
    private ArrayList<Integer> blackStonesOnBoard;

    private MoveManager gameRunner;
    private Vector3 touchPoint = new Vector3();

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, GameOfUrDemo.width, GameOfUrDemo.height);
        blackTurn = false;
        whiteTurn = false;
        rollDiceTexture = true;
        blackTurnNotification = new Sprite(new TextureRegion(new Texture ("BlackTurn.png")));
        blackTurnNotification.setPosition(20,200);
        whiteTurnNotification = new Sprite(new TextureRegion (new Texture("WhiteTurn.png")));
        whiteTurnNotification.setPosition(400,200);

        gameRunner = new MoveManager();

        pauseGear = new Sprite (new TextureRegion(new Texture("PauseGear.png")));
        pauseGear.setPosition(400,20);
        helpButton = new Sprite (new TextureRegion(new Texture("Help.png")));
        helpButton.setPosition(20,20);
        background = new Texture("PlayStateBackground.jpg");

        diceTextures = new HashMap<Integer, Sprite>();
        diceTextures.put(-2, new Sprite(new TextureRegion(new Texture("RollAgain.png"))));
        diceTextures.put(-1, new Sprite(new TextureRegion(new Texture("RollDice.png"))));
        diceTextures.put(0, new Sprite(new TextureRegion(new Texture("TurnSkipped.png"))));
        diceTextures.put(1, new Sprite(new TextureRegion(new Texture("DiceRoll1.png"))));
        diceTextures.put(2, new Sprite(new TextureRegion(new Texture("DiceRoll2.png"))));
        diceTextures.put(3, new Sprite(new TextureRegion(new Texture("DiceRoll3.png"))));
        diceTextures.put(4, new Sprite(new TextureRegion(new Texture("DiceRoll4.png"))));


        whiteStonePoolTextures = fillStonePoolTextures(1);
        blackStonePoolTextures = fillStonePoolTextures(2);

        finishedWhiteStoneTextures = fillFinishedStoneTextures(1);
        finishedBlackStoneTextures = fillFinishedStoneTextures(2);



        square = new Sprite[8][3];

        blackStonesOnBoard = new ArrayList<Integer>(); //used to keep track of white stones on board
        whiteStonesOnBoard = new ArrayList<Integer>(); //used to keep track of black stones on board

        boardSquareTextures = new HashMap<Integer, Sprite>();
        boardSquareTextures.put(0, new Sprite(new TextureRegion(new Texture("invisibleSquare.jpg"))));
        boardSquareTextures.put(1, new Sprite(new TextureRegion(new Texture("Space1.png"))));
        boardSquareTextures.put(2, new Sprite(new TextureRegion(new Texture("Space2.png"))));
        boardSquareTextures.put(3, new Sprite(new TextureRegion(new Texture("Space3.png"))));
        boardSquareTextures.put(4, new Sprite(new TextureRegion(new Texture("Space4.png"))));
        boardSquareTextures.put(5, new Sprite(new TextureRegion(new Texture("Space5.png"))));
        boardSquareTextures.put(6, new Sprite(new TextureRegion(new Texture("SafeSpace.png"))));

        square = createSquares(boardSquareTextures);

        stoneObjectsManager = new StoneObjects(2);
    }



    private static HashMap<Integer, Sprite> fillFinishedStoneTextures(int stonePoolColor){
        HashMap<Integer, Sprite> filledHashMap = new HashMap<Integer, Sprite>();
        if(stonePoolColor ==1){
            filledHashMap.put(0, new Sprite(new TextureRegion(new Texture("PlayStateBackground.jpg"))));
            filledHashMap.get(0).setSize(80,200);
            filledHashMap.put(1, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones1.png"))));
            filledHashMap.put(2, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones2.png"))));
            filledHashMap.put(3, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones3.png"))));
            filledHashMap.put(4, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones4.png"))));
            filledHashMap.put(5, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones5.png"))));
            filledHashMap.put(6, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones6.png"))));
            filledHashMap.put(7, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones7.png"))));
        }
        else if(stonePoolColor ==2){
            filledHashMap.put(0, new Sprite(new TextureRegion(new Texture("PlayStateBackground.jpg"))));
            filledHashMap.get(0).setSize(80,200);
            filledHashMap.put(1, new Sprite(new TextureRegion(new Texture("FinishedBlackStones1.png"))));
            filledHashMap.put(2, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones2.png"))));
            filledHashMap.put(3, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones3.png"))));
            filledHashMap.put(4, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones4.png"))));
            filledHashMap.put(5, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones5.png"))));
            filledHashMap.put(6, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones6.png"))));
            filledHashMap.put(7, new Sprite(new TextureRegion(new Texture("FinishedWhiteStones7.png"))));
        }
        return filledHashMap;
    }

    private static HashMap<Integer, Sprite> fillStonePoolTextures(int stonePoolColor){
        //color =1 when white, color =2 when black
        HashMap<Integer, Sprite> filledHashMap = new HashMap<Integer, Sprite>();
        if(stonePoolColor ==1){
            filledHashMap.put(0, new Sprite(new TextureRegion(new Texture("PlayStateBackground.jpg"))));
            filledHashMap.get(0).setSize(160,120);
            filledHashMap.put(1,new Sprite(new TextureRegion(new Texture("WhiteStonePool1.png"))));
            filledHashMap.put(2,new Sprite(new TextureRegion(new Texture("WhiteStonePool2.png"))));
            filledHashMap.put(3,new Sprite(new TextureRegion(new Texture("WhiteStonePool3.png"))));
            filledHashMap.put(4,new Sprite(new TextureRegion(new Texture("WhiteStonePool4.png"))));
            filledHashMap.put(5,new Sprite(new TextureRegion(new Texture("WhiteStonePool5.png"))));
            filledHashMap.put(6,new Sprite(new TextureRegion(new Texture("WhiteStonePool6.png"))));
            filledHashMap.put(7,new Sprite(new TextureRegion(new Texture("WhiteStonePool7.png"))));
        }
        else if(stonePoolColor ==2) {
            filledHashMap.put(0, new Sprite(new TextureRegion(new Texture("PlayStateBackground.jpg"))));
            filledHashMap.get(0).setSize(160,120);
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


    private static Sprite[][] createSquares(HashMap<Integer, Sprite> boardSquareTextures){
        //will probably be used to instantiate "Square" objects later on but rn it's just adding the square sprites
        //squares are 88x88
        Sprite square[][] = new Sprite[8][3];
        int squareCounter = 0;
        int textureSelect = 0;
        int yPosition = 0;
        int xPosition = 108;



        for(int y=0; y<8; y++){
            for(int x=0; x<3; x++){

                if ((y==4 && x==0) || (y==4 && x==2) || (y==5 && x==0) || (y==5 && x==2) ){
                    //square[y][x] = boardSquareTextures.get(0);
                    textureSelect = 0;

                }
                else if((y==0 && x==0)||(y==0 && x==2)||(y==3 && x==1) || (y==6 && x==0) ||(y== 6 && x==2)){
                    //square[y][x] = boardSquareTextures.get(6);
                    textureSelect = 6;
                }
                else if((y==1 && x==0) || (y == 1 && x==2) || (y==3 && x==0) || (y==3 && x==2) || (y==6 && x==1)){
                    //square[y][x] = new Sprite(new Texture("square0.0.jpg"));
                    //square[y][x] = boardSquareTextures.get(1);
                    textureSelect = 1;
                }
                else if((y==2 && x==0) || (y==2 && x ==2) || (y==1 && x==1) || (y==4 && x==1)||(y==7 && x ==1)){
                    //square[y][x] = boardSquareTextures.get(2);
                    textureSelect = 2;

                }
                else if(y==0 && x==1){
                    //square[y][x] = boardSquareTextures.get(3);
                    textureSelect = 3;

                }
                else if((y==2 && x==1) || (y==5 && x==1)){
                    //square[y][x] = boardSquareTextures.get(4);
                    textureSelect = 4;

                }
                else if((y==7 && x==0)||(y==7 && x==2)){
                    //square[y][x] = boardSquareTextures.get(5);
                    textureSelect =5;

                }


                square[y][x] = new Sprite (boardSquareTextures.get(textureSelect));
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
        return square;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {

            camera.unproject(touchPoint.set(Gdx.input.getX(0),  Gdx.input.getY(0), 0));
            System.out.println(touchPoint.x+", "+ touchPoint.y);
            handleDiceClick();
            handleStonePoolClick();
            handleStoneClick();
            if(helpButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)){
                gsm.push(new HelpState(gsm));
            }


        }
    }
    private void handleDiceClick(){
        //checks if the dices were clicked and if it rolling the dice is a legal move
        if (diceTextures.get(gameRunner.getDiceRoll()).getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && gameRunner.diceRollPermisssionStatus() == true) {

            gameRunner.diceRollInProgress();
            gameRunner.rollDice();

        }
    }

    private void handleStoneClick(){
        //runs through the array of stones and checks if the ones on the board have been clicked
        for(int whiteStones:gameRunner.getWhiteStonesInUse()){
            StoneObjects stoneToCheck = gameRunner.getWhiteStones()[whiteStones]; //holds stone that is being checked

            if(stoneToCheck.getStone().getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && gameRunner.getPlayerTurnNumber() ==1
                    && gameRunner.diceRollPermisssionStatus() == false && gameRunner.legalMoveCheck(1,whiteStones))
            {

                gameRunner.stoneNextTurn(stoneToCheck);

            }
        }
        //same as above but for black stones
        for(int blackStones:gameRunner.getBlackStonesInUse()){
            StoneObjects stoneToCheck = gameRunner.getBlackStones()[blackStones];
            if(stoneToCheck.getStone().getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && gameRunner.getPlayerTurnNumber() == 2
                    && gameRunner.diceRollPermisssionStatus() == false && gameRunner.legalMoveCheck(2, blackStones))
            {
                gameRunner.stoneNextTurn(stoneToCheck);

            }
        }
    }

    private void handleStonePoolClick(){
        //detects if the black stone pool has been clicked
        if((blackStonePoolTextures.get(gameRunner.getUnusedBlackStones().size()).getBoundingRectangle().contains(touchPoint.x, touchPoint.y))
                && gameRunner.getPlayerTurnNumber() == 2 && gameRunner.diceRollPermisssionStatus() == false
                && gameRunner.stonePoolLegalMoveCheck(2) == true)
        {

            //gameRunner.deployBlackStone().stoneClicked (gameRunner.getDiceRoll());
            //gameRunner.nextTurn();
            gameRunner.stoneNextTurn(gameRunner.deployBlackStone());
        }

        //checks if the white stone pool was clicked
        if (whiteStonePoolTextures.get(gameRunner.getUnusedWhiteStones().size()).getBoundingRectangle().contains(touchPoint.x, touchPoint.y)
                && gameRunner.getPlayerTurnNumber() ==1 && gameRunner.diceRollPermisssionStatus() == false
                && gameRunner.stonePoolLegalMoveCheck(1) == true)
        {
            //gameRunner.deployWhiteStone().stoneClicked(gameRunner.getDiceRoll());
            //gameRunner.nextTurn();
            gameRunner.stoneNextTurn(gameRunner.deployWhiteStone());
        }
    }

    @Override
    public void update(float deltaTime) {
        gameRunner.winCheck();
        handleInput();
        if(gameRunner.getTurnCount()%2 != 0){
            whiteTurn = true;
            blackTurn = false;
        }
        else if(gameRunner.getTurnCount()%2 == 0 || gameRunner.getTurnCount()==0){
            whiteTurn = false;
            blackTurn = true;
        }



    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0, GameOfUrDemo.width, GameOfUrDemo.height);
        pauseGear.draw(sb);
        helpButton.draw(sb);
        for(int y=0; y<8; y++){
            for(int x=0; x<3; x++){
                square[y][x].draw(sb);
            }
        }

        if(whiteTurn){
            whiteTurnNotification.draw(sb);

        }
        if(blackTurn){
            blackTurnNotification.draw(sb);
        }

        Sprite dice;
        dice = diceTextures.get(gameRunner.getDiceTexture());
        dice.setPosition(102, 0);
        dice.draw(sb);

        finishedBlackStoneTextures.get(gameRunner.getCompletedBlackStoneCount()).setPosition(0,600);
        finishedBlackStoneTextures.get(gameRunner.getCompletedBlackStoneCount()).draw(sb);
        finishedWhiteStoneTextures.get(gameRunner.getCompleteWhiteStoneCount()).setPosition(400,600);
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
            if(stoneToUpdate.getTotalMoves() != 15) {
                stoneToUpdate.getStone().draw(sb);
            }
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
            if(stoneToUpdate.getTotalMoves() !=15) {
                stoneToUpdate.getStone().draw(sb);
            }
        }
    }



}
