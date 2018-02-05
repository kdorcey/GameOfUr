package com.royalgameofur.game.States;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Kyle on 1/15/2018.
 */

public class GameStateManager {
    private Stack<State> states;


    public GameStateManager(){
        states = new Stack<State>();

    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose(); //takes stuff off the states no need to return
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float deltaTime) {
        //takes in the time between renders
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch sb){
        //takes in the container for everything
        states.peek().render(sb);
    }
}
