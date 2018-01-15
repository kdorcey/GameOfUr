package com.royalgameofur.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.royalgameofur.game.GameOfUrDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameOfUrDemo.width;
		config.height = GameOfUrDemo.height;
		config.title = GameOfUrDemo.title;
		new LwjglApplication(new GameOfUrDemo(), config);
	}
}
