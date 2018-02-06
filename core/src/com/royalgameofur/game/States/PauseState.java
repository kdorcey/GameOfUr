package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kyle on 2/5/2018.
 */

public class PauseState extends State {
    private Texture background;
    private Sprite resumeButton, newGameButton, returnToMenuButton;

    public PauseState(GameStateManager gsm){
        super(gsm);
        background = new Texture("PlayStateBackground.png");
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
