package com.royalgameofur.game.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.royalgameofur.game.GameLogic.MoveManager;
import com.royalgameofur.game.GameOfUrDemo;

/**
 * Created by Kyle on 1/16/2018.
 */

public class PlayState extends State {
    private Texture board;
    //squares go from left to right from 0-7. 4 and 5 are invisible in rows 0 and 1
    //rows 0 and 2 have safe spaces at 0 and 6
    //row 2 has a safe space at 3

    private static Sprite square[][];
    private Texture stones[];
    private Texture enemyStones[];
    private Texture blackStonePool1, blackStonePool2, blackStonePool3, blackStonePool4, blackStonePool5, blackStonePool6, blackStonePool7;
    private Texture blackStonePoolTexture;
    private Texture whiteStonePool1, whiteStonePool2, whiteStonePool3, whiteStonePool4, whiteStonePool5, whiteStonePool6, whiteStonePool7;
    private Texture whiteStonePoolTexture;
    private Texture dicePool;
    private Texture diceRoll0, diceRoll1, diceRoll2, diceRoll3;
    private static Texture background;

    private Sprite dice;
    private Sprite blackStonePool;
    private Sprite whiteStonePool;

    private StonePosition stonePositionManager;

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

        blackStonePool1 = new Texture("BlackStonePool1.png");
        blackStonePool2 = new Texture("BlackStonePool2.png");
        blackStonePool3 = new Texture("BlackStonePool3.png");
        blackStonePool4 = new Texture("BlackStonePool4.png");
        blackStonePool5 = new Texture("BlackStonePool5.png");
        blackStonePool6 = new Texture("BlackStonePool6.png");
        blackStonePool7 = new Texture("BlackStonePool7.png");


        blackStonePoolTexture = new Texture("BlackStonePool7.png");
        whiteStonePoolTexture = new Texture("WhiteStonePool7.png");

        dice = new Sprite (new TextureRegion(dicePool));
        dice.setPosition(102, 0); //convert these to ratios
        blackStonePool = new Sprite(new TextureRegion(blackStonePoolTexture));
        blackStonePool.setPosition(20,500); //convert these to ratios
        whiteStonePool = new Sprite(new TextureRegion(whiteStonePoolTexture));
        whiteStonePool.setPosition(300,500);


        square = new Sprite[8][3];
        stones = new Texture[7];
        enemyStones = new Texture[7];

        createSquares(); //THIS MUST BE DEFINED BEFORE STONE POSITION

        stonePositionManager = new StonePosition(2,square);



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

            //checks that it is a legal time to "roll" the dice

            //updates dice texture based on dice roll
            if (dice.getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                    && gameRunner.diceRollPermisssionStatus() == true) {

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

            //checks if it is blacks turn
            if (gameRunner.getPlayerTurnNumber() ==2 && gameRunner.diceRollPermisssionStatus() == false) {
                System.out.println("Black Move");

                //checks if the player stone pool is clicked
                if((blackStonePool.getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY()))){
                    stonePositionManager.stoneClicked(gameRunner.getDiceRoll());
                    gameRunner.nextTurn();
                }
                //checks if an individual stone was pressed



            }
            if (whiteStonePool.getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())
                    && gameRunner.getPlayerTurnNumber() ==1 && gameRunner.diceRollPermisssionStatus() == false) {
                System.out.println("White Move");
                gameRunner.nextTurn();
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
        blackStonePool.draw(sb);
        whiteStonePool.draw(sb);

        if((stonePositionManager.getLastX() != stonePositionManager.getCurrentX()) || (stonePositionManager.getLastY() != stonePositionManager.getCurrentY())){
            if(stonePositionManager.getLastX() != stonePositionManager.getCurrentX()){
                stonePositionManager.moveX();
            }
            if(stonePositionManager.getLastY() != stonePositionManager.getCurrentY()){
                stonePositionManager.moveY();
            }
        }
        stonePositionManager.getStone().setPosition(stonePositionManager.getLastX(), stonePositionManager.getLastY());
        stonePositionManager.getStone().draw(sb);

        sb.end();
    }
}
