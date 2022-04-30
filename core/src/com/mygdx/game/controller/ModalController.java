package com.mygdx.game.controller;

import com.mygdx.game.view.ModalView;

public class ModalController extends Controller<ModalView> {
    private static ModalController instance = null;

    protected ModalController() { super(ModalView.getInstance()); }

    public static final ModalController getInstance(){
        if (instance == null){
            instance = new ModalController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {}
}
