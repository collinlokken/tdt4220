package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;

public class StripaSurvivor extends ApplicationAdapter {
	public static final int WIDTH = 1110;
	public static final int HEIGHT = 540;
	public static final String TITLE = "Stripa Survivor";
	private ControllerManager controllerManager;
	static FireBaseInterface _FBIC;

	public StripaSurvivor(FireBaseInterface FBIC)
	{
		_FBIC = FBIC;
	};

	public static FireBaseInterface getFirebaseInterface () {
		return _FBIC;
	}

	@Override
	public void create () {
		controllerManager = ControllerManager.getInstance();
		LoginController login = LoginController.getInstance();
		//MainMenuController mainMenu = MainMenuController.getInstance();
		//GameController game = GameController.getInstance();
		controllerManager.push(login);
		_FBIC.SetOnValueChangedListener("message");
		_FBIC.SetValueInDBb("message","TDT4240 er keeeegt");
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
