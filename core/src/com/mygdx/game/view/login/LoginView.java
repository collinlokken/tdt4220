package com.mygdx.game.view.login;

import com.mygdx.game.view.View;

public class LoginView extends View {
    private static LoginView instance = null;

    private LoginView(){
        super();
    }

    public static final LoginView getInstance(){
        if (instance == null){
            return new LoginView();
        }
        return instance;
    }


}
