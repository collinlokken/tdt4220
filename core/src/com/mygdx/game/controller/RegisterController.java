package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.model.User;
import com.mygdx.game.view.LoginView;
import com.mygdx.game.view.MainMenuView;
import com.mygdx.game.view.RegisterView;

public class RegisterController extends Controller<RegisterView>{
    private static RegisterController instance = null;
    Texture emptyUsernameTexture = new Texture("empty_username.png");
    Texture emptyPasswordTexture = new Texture("empty_password.png");
    Texture pwdNotEqual = new Texture("pwd_not_equal.png");


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

    public void registerUserInDB(String uname, String pwd, String pwd1) {
        if (uname.equals("")){
            this.view.addModal(emptyUsernameTexture);
        } else if (pwd.equals("")) {
            this.view.addModal(emptyPasswordTexture);
        } else if (!pwd.equals(pwd1)) {
            this.view.addModal(pwdNotEqual);
        } else {
            User usr = new User(uname, pwd);
            StripaSurvivor.getFirebaseInterface().SetValueInDBb("users/"+usr.getUuid().toString(), usr.toMap());
            LoginController.getInstance().loginWithCredentials(uname, pwd);
            LoginView.getInstance().stopMusic();
            MainMenuView.getInstance().playMusic();
            this.switchState(MainMenuController.getInstance());
        }
    }
}
