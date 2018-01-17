package com.royalgameofur.game.States;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kyle on 1/16/2018.
 */

public class PlayState extends State {
    private Texture background;
    private Texture board;
    //squares go from left to right from 0-7. 4 and 5 are invisible in rows 0 and 1
    //rows 0 and 2 have safe spaces at 0 and 6
    //row 2 has a safe space at 3
    private Texture square[];
    private Texture stones[];
    private Texture enemyStones[];
    private Texture dice;
    private Texture selfStonePool;
    private Texture enemyStonePool;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        square = new Texture[24];
        stones = new Texture[7];
        enemyStones = new Texture[7];
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

    }
}
