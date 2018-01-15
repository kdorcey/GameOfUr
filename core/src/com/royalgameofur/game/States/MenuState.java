package com.royalgameofur.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.royalgameofur.game.GameOfUrDemo;

/**
 * Created by Kyle on 1/15/2018.
 */

public class MenuState extends State {
    private Texture title;
    private Texture background;
    private Texture playLocalButton;
    private Texture playOnlineButton;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        title = new Texture("Title.jpg");
        background = new Texture("Background.jpg");
        playLocalButton = new Texture ("NewLocal.jpg");
        playOnlineButton = new Texture("NewOnline.jpg");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameOfUrDemo.width, GameOfUrDemo.height);
        sb.draw(title,((GameOfUrDemo.width/2) - title.getWidth()/2), ((GameOfUrDemo.height/2)- playLocalButton.getHeight()/2));
        sb.draw(playLocalButton, ((GameOfUrDemo.width/2) - playLocalButton.getWidth()/2), ((GameOfUrDemo.height/3)- playLocalButton.getHeight()/2));
        sb.draw(playOnlineButton, ((GameOfUrDemo.width/2) - playOnlineButton.getWidth()/2), ((GameOfUrDemo.height/6)- playOnlineButton.getWidth()/2));
        sb.end();


    }
}
