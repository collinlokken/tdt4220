package com.mygdx.game.view.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;
import com.mygdx.game.view.View;
import com.mygdx.game.view.mainMenu.Background;

import jdk.tools.jmod.Main;

public class LoginView extends View {
    private static LoginView instance = null;

    private LoginView(){
        super();
        Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

        final TextField usernameField = new TextField("", skin);
        usernameField.setPosition(250,197);
        usernameField.setMessageText("Username");

        final TextField passwordField = new TextField("", skin);
        passwordField.setPosition(460,197);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setMessageText("Password");

        ImageButton loginButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("play.png"))));
        loginButton.setPosition(700,180);
        loginButton.addListener(new ClickListener(){
            @Override //TODO Send username + pass to database
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("username = " + usernameField.getText());
                System.out.println("password = " + passwordField.getText());
                ControllerManager.getInstance().set(MainMenuController.getInstance(ControllerManager.getInstance())); //I View??
            }
        });

        ImageButton registerButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("register.png"))));
        registerButton.setPosition(StripaSurvivor.WIDTH/2-100,100);
        registerButton.setSize(200,50);
        registerButton.addListener(new ClickListener() {
            @Override //TODO Send username + pass to database
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ControllerManager.getInstance().set(RegisterController.getInstance()); //I View??
            }
        });
        this.addActor(new Background());
        this.addActor(usernameField);
        this.addActor(passwordField);
        this.addActor(loginButton);
        this.addActor(registerButton);
        Gdx.input.setInputProcessor(this);

    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        return instance;
    }


}
