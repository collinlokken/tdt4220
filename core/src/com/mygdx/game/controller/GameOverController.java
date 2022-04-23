package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.view.GameOverView;

public class GameOverController extends Controller<GameOverView>{

    private static GameOverController instance = null;

    private GameOverController(){
        super(GameOverView.getInstance());
    }

    public static final GameOverController getInstance(Image bg){
        if (instance == null){
            instance = new GameOverController();
        }
        instance.setBackground(bg);
        return instance;
    }

    @Override
    public void update(float dt) {

    }

    private void setBackground(Image image){
        GameOverView.getInstance().setPausedBackground(image);
    }
}
