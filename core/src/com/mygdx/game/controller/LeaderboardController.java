package com.mygdx.game.controller;

import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.HighScore;
import com.mygdx.game.view.LeaderboardView;

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

    public static void highScoreCallback(ArrayList<HighScore> highScores) {
        LeaderboardView.getInstance().removeHighScoresFromView();
        for(HighScore highScore : highScores) {
            LeaderboardView.getInstance().addHighScore(highScore);
        }
        LeaderboardView.getInstance().renderAllHighScores();
    }

    @Override
    public void update(float dt) { }
}
