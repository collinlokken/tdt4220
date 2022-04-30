package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.User;
import com.mygdx.game.view.GameOverView;
import com.mygdx.game.view.LoginView;

public class GameOverController extends Controller<GameOverView>{

    private static GameOverController instance = null;

    private GameOverController(){
        super(GameOverView.getInstance());
    }


    public static final GameOverController getInstance(Image image, float score){
        if (instance == null){
            instance = new GameOverController();
        }
        instance.view.setBackground(image);
        User user = LoginController.getInstance().getUserSession().getUser();
        LoginView.getInstance().stopMusic();
        if (user != null){
            StripaSurvivor.getFirebaseInterface().handleUserHighScore(user.getUuid(), score);
        }
        return instance;
    }

    public static final GameOverController getInstance(){
        if (instance == null){
            instance = new GameOverController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {}
}
