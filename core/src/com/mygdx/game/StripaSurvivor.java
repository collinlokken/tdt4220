package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;

import sun.rmi.runtime.Log;

public class StripaSurvivor extends ApplicationAdapter {
	public static final int WIDTH = 1120;
	public static final int HEIGHT = 700;

	public static final String TITLE = "Stripa Survivor";
	private ControllerManager controllerManager;

	@Override
	public void create () {
		controllerManager = ControllerManager.getInstance();
		LoginController login = LoginController.getInstance(controllerManager);
		controllerManager.push(login);
		Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		controllerManager.update(Gdx.graphics.getDeltaTime());
		controllerManager.getCurrent().getView().act();
		controllerManager.getCurrent().getView().draw();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}


}
