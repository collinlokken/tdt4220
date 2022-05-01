package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.view.PauseView;

public class PauseController extends Controller<PauseView>{
    private static PauseController instance = null;

    private PauseController(){
        super(PauseView.getInstance());
    }

    public static final PauseController getInstance(Image bg){
        if (instance == null){
            instance = new PauseController();
        }
        instance.view.setBackground(bg);
        return instance;
    }

    public static final PauseController getInstance(){
        if (instance == null){
            instance = new PauseController();
        }
        return instance;
    }

    @Override
    public void update(float dt) { }
}
