package com.mygdx.game.controller;

import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;

    private GameController(ControllerManager controllerManager){
        super(controllerManager, GameView.getInstance());
    }

    public static final GameController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new GameController(controllerManager);
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
