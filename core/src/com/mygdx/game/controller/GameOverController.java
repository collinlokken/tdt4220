package com.mygdx.game.controller;

import com.mygdx.game.view.GameOverView;

public class GameOverController extends Controller<GameOverView>{

    private static GameOverController instance = null;

    private GameOverController(){
        super(GameOverView.getInstance());
    }

    public static final GameOverController getInstance(){
        if (instance == null){
            instance = new GameOverController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
