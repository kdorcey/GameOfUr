package com.royalgameofur.game.States;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Texture dice;
    private Texture selfStonePool;
    private Texture enemyStonePool;
    private static Texture background;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("PlayStateBackground.jpg");
        square = new Sprite[8][3];
        stones = new Texture[7];
        enemyStones = new Texture[7];
        createSquares();
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

    }

    @Override
    public void update(float deltaTime) {

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
        sb.end();
    }
}
