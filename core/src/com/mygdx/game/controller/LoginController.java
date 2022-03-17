package com.mygdx.game.controller;

import com.mygdx.game.view.LoginView;

public class LoginController extends Controller<LoginView>{
    private static LoginController instance = null;

    private LoginController(ControllerManager controllerManager){
        super(controllerManager, LoginView.getInstance());
    }

    public static final LoginController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new LoginController(controllerManager);
        }
        return instance;
    }
}
