package com.mygdx.game.controller;

import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.UserSession;
import com.mygdx.game.view.LoginView;
import com.mygdx.game.view.MainMenuView;

public class LoginController extends Controller<LoginView>{
    private static LoginController instance = null;
    private UserSession userSession = new UserSession();

    private LoginController(){
        super(LoginView.getInstance());
    }

    public static final LoginController getInstance(){
        if (instance == null){
            instance = new LoginController();
        }
        return instance;
    }

    public void loginWithCredentials(String uname, String pwd){
        StripaSurvivor.getFirebaseInterface().retrieveUserFromCredentials(uname, pwd);
    }

    public void logOutUser(){
        getUserSession().setToNull();
    }

    public void loginCallback(){
        if(userSession.isLoggedIn()){
            this.view.stopMusic();
            MainMenuView.getInstance().playMusic();
            this.switchState(MainMenuController.getInstance());
        } else {
            this.view.addModal();
        }
    }

    @Override
    public void update(float dt) {}

    public UserSession getUserSession() {
        return userSession;
    }
}
