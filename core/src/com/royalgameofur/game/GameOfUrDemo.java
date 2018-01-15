package com.royalgameofur.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.royalgameofur.game.States.GameStateManager;
import com.royalgameofur.game.States.MenuState;

public class GameOfUrDemo extends ApplicationAdapter {
	public static final int width  = 480;
	public static final int height = 800;

	public static final String title = "The Royal Game of Ur";

	private GameStateManager gsm;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	

}
