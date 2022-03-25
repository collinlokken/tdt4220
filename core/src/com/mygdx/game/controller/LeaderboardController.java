package com.mygdx.game.controller;

import com.mygdx.game.view.help.HelpView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

public class LeaderboardController extends Controller<LeaderboardView>{
    private static LeaderboardController instance = null;

    private LeaderboardController(ControllerManager controllerManager){
        super(controllerManager, LeaderboardView.getInstance());
    }

    public static final LeaderboardController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new LeaderboardController(controllerManager);
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
