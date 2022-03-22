package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;

public class StripaSurvivor extends ApplicationAdapter {
	public static final int WIDTH = 1120;
	public static final int HEIGHT = 700;

	public static final String TITLE = "Stripa Survivor";
	private ControllerManager controllerManager;
	private static SpriteBatch sb;

	public static SpriteBatch  getSpriteBatchInstance()
	{
		if(sb == null)
			sb = new SpriteBatch();
		return sb;
	}
	@Override
	public void create () {
		controllerManager = ControllerManager.getInstance();
		MainMenuController mainMenu = MainMenuController.getInstance(controllerManager);
		controllerManager.push(mainMenu);
	}

	@Override
	public void render () {
		controllerManager.update(Gdx.graphics.getDeltaTime());
		controllerManager.getCurrent().getView().render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}


}
