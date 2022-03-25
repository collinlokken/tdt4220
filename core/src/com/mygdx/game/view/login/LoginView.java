package com.mygdx.game.view.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.view.View;

public class LoginView extends View {
    private static LoginView instance = null;

    private LoginView(){
        super();
        Skin skin = new Skin();
        TextField usernameField = new TextField("brukernavn", skin);
        usernameField.setPosition(500,500);
        TextField passwordField = new TextField("passord", skin);
        passwordField.setPosition(500,300);
        ImageButton loginButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("play.png"))));
        loginButton.setPosition(500,100);
    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        return instance;
    }


}
