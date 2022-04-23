package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.view.GameOverView;

public class GameOverController extends Controller<GameOverView>{

    private static GameOverController instance = null;
    private GameOverView gameOverView;

    private GameOverController(){
        super(GameOverView.getInstance());
        gameOverView = (GameOverView) getView();
    }

    public static final GameOverController getInstance(Image image){
        if (instance == null){
            instance = new GameOverController();
        }
        instance.gameOverView.setBackground(image);
        return instance;
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
