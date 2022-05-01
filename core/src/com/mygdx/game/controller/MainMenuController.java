package com.mygdx.game.controller;

import com.mygdx.game.view.MainMenuView;

public class MainMenuController extends Controller<MainMenuView>{

    private static MainMenuController instance = null;

    private MainMenuController() {
        super(MainMenuView.getInstance());
    }

    public static final MainMenuController getInstance(){
        if (instance == null){
            instance = new MainMenuController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {}
}
