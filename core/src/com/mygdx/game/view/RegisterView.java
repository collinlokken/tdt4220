package com.mygdx.game.view;

import com.mygdx.game.controller.RegisterController;
import com.mygdx.game.view.mainMenu.Background;

public class RegisterView extends View<RegisterController> {
    private static RegisterView instance = null;

    private RegisterView(){
        super();
        this.addActor(new Background());
    }

    public static final RegisterView getInstance(){
        if (instance == null){
            instance = new RegisterView();
        }
        return instance;
    }
}
