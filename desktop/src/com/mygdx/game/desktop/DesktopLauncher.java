package com.mygdx.game.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;

public class DesktopLauncher {

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Graphics.DisplayMode desktop = LwjglApplicationConfiguration.getDesktopDisplayMode();
		config.setFromDisplayMode(desktop);
		config.fullscreen = true;

		new LwjglApplication(new MainGame(), config);
	}
}
