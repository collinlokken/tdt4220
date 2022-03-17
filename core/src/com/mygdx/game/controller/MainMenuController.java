package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.view.MainMenuView;

public class MainMenuController extends Controller<MainMenuView>{

    private static MainMenuController instance = null;


    private MainMenuController(ControllerManager controllerManager) {
        super(controllerManager, MainMenuView.getInstance());
    }

    public static final MainMenuController getInstance(ControllerManager controllerManager){
        if (instance == null){
            return new MainMenuController(controllerManager);
        }
        return instance;
    }





}
