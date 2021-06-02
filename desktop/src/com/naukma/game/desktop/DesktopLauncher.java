package com.naukma.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.naukma.game.Main;

/**
 * Start libgdx for Desktop
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "#define Failure us";
//		config.width = 1200;
//		config.height = 900;
		config.width = 1600;
		config.height = 900;
		new LwjglApplication(new Main(), config);
	}
}
