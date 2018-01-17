package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 *CHANGE THESE PROTECTED VARIABLES EW
 *
 * Created by Kyle on 1/15/2018.
 */

public abstract class State {
    protected OrthographicCamera camera; //camera
    protected Vector3 mouse; //xyz coordinante system
    protected GameStateManager gsm; //allows overlayed states (think pause screens)

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    public abstract void update(float deltaTime); //timebetween frames
    public abstract void dispose();
    public abstract void render (SpriteBatch sb); //container for all the stuff we want to render
}
