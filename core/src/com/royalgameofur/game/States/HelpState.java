package com.royalgameofur.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.royalgameofur.game.GameOfUrDemo;

/**
 * Created by Kyle on 2/4/2018.
 */

public class HelpState extends State {
    private Sprite backButton;
    private Texture header, freeSpaceExample, stoneExample, diceExapmle, dash, freeSpaceText, stoneText, diceText, background;

    protected HelpState(GameStateManager gsm) {
        super(gsm);
        backButton = new Sprite(new TextureRegion(new Texture("BackButton.png")));
        backButton.setPosition(40,40);
        header = new Texture("RulesHeader.png");
        freeSpaceExample = new Texture("SafeSpace.png");
        stoneExample = new Texture("StoneHelpPic.png");
        diceExapmle = new Texture("DiceHelpPic.png");
        dash = new Texture("Dash.png");
        freeSpaceText = new Texture("SafeSpaceRules.png");
        stoneText = new Texture("StoneRules.png");
        diceText = new Texture("DiceRules.png");
        background = new Texture("PlayStateBackground.jpg");

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(backButton.getBoundingRectangle().contains(Gdx.input.getX(), GameOfUrDemo.height - Gdx.input.getY())){
                gsm.pop();
                dispose();
            }

        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void dispose() {
        background.dispose();
        header.dispose();
        freeSpaceExample.dispose();
        stoneExample.dispose();
        dash.dispose();
        freeSpaceExample.dispose();
        stoneExample.dispose();
        stoneText.dispose();
        diceExapmle.dispose();
        diceText.dispose();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, GameOfUrDemo.width,GameOfUrDemo.height);
        sb.draw(header,40,600);
        sb.draw(freeSpaceExample,40,472);
        sb.draw(dash, 164, 516);
        sb.draw(freeSpaceText,260,420);
        sb.draw(stoneExample,40,272);
        sb.draw(dash, 164,316);
        sb.draw(stoneText, 260, 220);
        sb.draw(diceExapmle,40,92);
        sb.draw(dash, 164,136);
        sb.draw(diceText,260,40);
        backButton.draw(sb);
        sb.end();


    }
}
