package com.mygdx.game.view;

public class GameOverView extends View{
    private static GameOverView instance = null;

    private GameOverView(float finalScore){
        super();



    }

    public static final GameOverView getInstance(float finalScore){
        if (instance == null){
            return new GameOverView(finalScore);
        }
        return instance;
    }
}
