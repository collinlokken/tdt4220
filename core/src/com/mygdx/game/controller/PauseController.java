package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.view.PauseView;

public class PauseController extends Controller<PauseView>{
    private static PauseController instance = null;
    private PauseView pauseView;

    private PauseController(){
        super(PauseView.getInstance());
        this.pauseView = (PauseView) this.getView();
    }

    public static final PauseController getInstance(Image bg){
        if (instance == null){
            instance = new PauseController();
        }
        instance.pauseView.setBackground(bg);
        return instance;
    }


    @Override
    public void update(float dt) {

    }
}
