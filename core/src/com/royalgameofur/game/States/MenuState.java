package com.royalgameofur.game.States;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.royalgameofur.game.GameOfUrDemo;

/**
 * Created by Kyle on 1/15/2018.
 */

public class MenuState extends State {
    private Texture title;
    private Texture background;
    private Texture playLocalButtonTexture;
    private Texture hostOnlineButtonTexture;
    private Texture playOnlineButtonTexture;
    private Sprite playLocalButton;
    private Sprite hostOnlineButton;
    private Sprite playOnlineButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        title = new Texture("BetterTitle.jpg");
        background = new Texture("Background.jpg");
        playLocalButtonTexture = new Texture("NewLocal.jpg");
        playOnlineButtonTexture = new Texture("NewOnline.jpg");
        hostOnlineButtonTexture = new Texture("HostOnline.jpg");

        playLocalButton = new Sprite(playLocalButtonTexture);
        playLocalButton.setPosition((GameOfUrDemo.width / 2) - (playLocalButtonTexture.getWidth() / 2), GameOfUrDemo.height * .575f);
        playLocalButton.rotate90(true);
        System.out.println("left corner " + playLocalButton.getX() + " , " + playLocalButton.getY() + ", width: " + playLocalButton.getWidth() + ", height: " + playLocalButton.getHeight());
        hostOnlineButton = new Sprite(hostOnlineButtonTexture);
        hostOnlineButton.setBounds((GameOfUrDemo.width / 2) - hostOnlineButtonTexture.getWidth() / 2, GameOfUrDemo.height * .325f, hostOnlineButtonTexture.getWidth(), hostOnlineButtonTexture.getHeight());
        playOnlineButton = new Sprite(playOnlineButtonTexture);
        playOnlineButton.setBounds((GameOfUrDemo.width / 2) - (playOnlineButton.getWidth() / 2), GameOfUrDemo.height * .075f, playOnlineButtonTexture.getWidth(), playOnlineButtonTexture.getHeight());
        
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            //for some reason when drawing sprites it draws from top left to bottom right. When drawing rectangles it's bottom left to top right.
            Rectangle playLocalButtonListener = new Rectangle(playLocalButton.getX(),GameOfUrDemo.height-playLocalButton.getY()-playLocalButton.getHeight(), playLocalButton.getWidth(), playLocalButton.getHeight());
            Rectangle hostOnlineButtonListener = new Rectangle(hostOnlineButton.getX(),GameOfUrDemo.height-hostOnlineButton.getY()-hostOnlineButton.getHeight(), hostOnlineButton.getWidth(), hostOnlineButton.getHeight());
            Rectangle playOnlineButtonListener = new Rectangle(playOnlineButton.getX(),GameOfUrDemo.height-playOnlineButton.getY()-playOnlineButton.getHeight(), playOnlineButton.getWidth(), playOnlineButton.getHeight());
            if (playLocalButtonListener.contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("worked");
                gsm.set(new PlayState(gsm));
                dispose();
            }
            if (hostOnlineButtonListener.contains(Gdx.input.getX(), Gdx.input.getY())){
                System.out.println("host online pressed");
            }
            if (playOnlineButtonListener.contains(Gdx.input.getX(), Gdx.input.getY())){
                System.out.println("play online pressed");
            }

        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameOfUrDemo.width, GameOfUrDemo.height);
        sb.draw(title, (float) (GameOfUrDemo.width - (GameOfUrDemo.width * .92)), (float) ((GameOfUrDemo.height * .75)));
        playLocalButton.draw(sb);
        hostOnlineButton.draw(sb);
        playOnlineButton.draw(sb);
        sb.draw(playLocalButton, (GameOfUrDemo.width / 2) - (playLocalButtonTexture.getWidth() / 2), GameOfUrDemo.height * .575f);
        sb.end();
    }

    @Override
    public void dispose() {
        //deletes all menu assets when game transitions from start up menu to game
        background.dispose();
        title.dispose();
        playLocalButtonTexture.dispose();
        playOnlineButtonTexture.dispose();
    }

}