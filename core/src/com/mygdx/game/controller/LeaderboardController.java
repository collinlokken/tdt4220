package com.mygdx.game.controller;

import com.mygdx.game.view.help.HelpView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

public class LeaderboardController extends Controller<LeaderboardView>{
    private static LeaderboardController instance = null;

    private LeaderboardController(){
        super(LeaderboardView.getInstance());
    }

    public static final LeaderboardController getInstance(){
        if (instance == null){
            instance = new LeaderboardController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
