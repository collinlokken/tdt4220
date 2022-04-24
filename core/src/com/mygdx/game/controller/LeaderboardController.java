package com.mygdx.game.controller;

import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.HighScore;
import com.mygdx.game.view.help.HelpView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

import java.util.ArrayList;

public class LeaderboardController extends Controller<LeaderboardView>{
    private static LeaderboardController instance = null;

    private LeaderboardController(){
        super(LeaderboardView.getInstance());
    }

    public static final LeaderboardController getInstance(){
        if (instance == null){
            instance = new LeaderboardController();
        }
        StripaSurvivor.getFirebaseInterface().getAllHighScores();
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
