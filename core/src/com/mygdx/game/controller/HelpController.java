package com.mygdx.game.controller;

import com.mygdx.game.view.HelpView;

public class HelpController extends Controller<HelpView>{

    private static HelpController instance = null;

    private HelpController(){
        super(HelpView.getInstance());
    }

    public static final HelpController getInstance(){
        if (instance == null){
            instance = new HelpController();
        }
        return instance;
    }

    @Override
    public void update(float dt) { }
}
