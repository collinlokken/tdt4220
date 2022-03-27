package com.mygdx.game.controller;

import com.mygdx.game.view.RegisterView;

public class RegisterController extends Controller<RegisterView>{
    private static RegisterController instance = null;

    private RegisterController(ControllerManager controllerManager){
        super(controllerManager, RegisterView.getInstance());
    }

    public static final RegisterController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new RegisterController(controllerManager);
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
