package com.mygdx.game.controller;

import com.mygdx.game.view.help.HelpView;
import com.mygdx.game.view.login.LoginView;

public class HelpController extends Controller<HelpView>{
    private static HelpController instance = null;

    private HelpController(ControllerManager controllerManager){
        super(controllerManager, HelpView.getInstance());
    }

    public static final HelpController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new HelpController(controllerManager);
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
