package com.mygdx.game.controller;

import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.User;
import com.mygdx.game.view.login.LoginView;

public class LoginController extends Controller<LoginView>{
    private static LoginController instance = null;

    private LoginController(){
        super(LoginView.getInstance());
    }

    public static final LoginController getInstance(){
        if (instance == null){
            instance = new LoginController();
        }
        return instance;
    }

    public void LoginWithCredentials(String uname, String pwd){
        StripaSurvivor.getFirebaseInterface().retrieveUserFromCredentials(uname, pwd);
    }

    public void RegisterUserInDB(String uname, String pwd) {
        User usr = new User(uname, pwd);
        StripaSurvivor.getFirebaseInterface().SetValueInDBb("users/"+usr.getUuid().toString(), usr.toMap());

    }

    @Override
    public void update(float dt) {

    }
}
