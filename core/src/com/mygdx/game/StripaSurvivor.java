package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;

public class StripaSurvivor extends ApplicationAdapter {
	public static final int WIDTH = 1120;
	public static final int HEIGHT = 700;

	public static final String TITLE = "Stripa Survivor";
	private ControllerManager controllerManager;

	@Override
	public void create () {
		controllerManager = ControllerManager.getInstance();
		MainMenuController mainMenu = MainMenuController.getInstance(controllerManager);
		//LoginController login = LoginController.getInstance(controllerManager);
		controllerManager.push(mainMenu);
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
