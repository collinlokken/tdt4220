package com.mygdx.game.controller;

import com.mygdx.game.view.RegisterView;

public class RegisterController extends Controller<RegisterView>{
    private static RegisterController instance = null;

    private RegisterController(){
        super(RegisterView.getInstance());
    }

    public static final RegisterController getInstance(){
        if (instance == null){
            instance = new RegisterController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
