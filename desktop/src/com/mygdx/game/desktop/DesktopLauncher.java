package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.StripaSurvivor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = StripaSurvivor.WIDTH;
		config.height = StripaSurvivor.HEIGHT;
		config.title = StripaSurvivor.TITLE;
		new LwjglApplication(new StripaSurvivor(new DesktopInterfaceClass()), config);
	}
}
