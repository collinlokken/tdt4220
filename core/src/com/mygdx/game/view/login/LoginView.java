package com.mygdx.game.view.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;
import com.mygdx.game.view.RegisterView;
import com.mygdx.game.view.View;
import com.mygdx.game.view.mainMenu.Background;

import jdk.tools.jmod.Main;

public class LoginView extends View {
    private static LoginView instance = null;

    private LoginView(){
        super();
        Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

        Image bg = new Image(new TextureRegionDrawable(new Texture("login_bg.png")));
        bg.setPosition(0, 0);
        bg.setSize(StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT);
        this.addActor(bg);

        final TextField usernameField = new TextField("", skin);
        usernameField.setPosition((float) (StripaSurvivor.WIDTH*0.25),(float) (StripaSurvivor.HEIGHT*0.34));
        usernameField.setMessageText("Username");

        final TextField passwordField = new TextField("", skin);
        passwordField.setPosition((float) (StripaSurvivor.WIDTH*0.45),(float) (StripaSurvivor.HEIGHT*0.34));
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setMessageText("Password");

        ImageButton loginButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("login.png"))));
        loginButton.setPosition((float) (StripaSurvivor.WIDTH*0.66),(float) (StripaSurvivor.HEIGHT*0.322));
        loginButton.setSize(55, 55);
        loginButton.addListener(new ClickListener(){
            @Override //TODO Send username + pass to database to authenticate
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("username = " + usernameField.getText());
                System.out.println("password = " + passwordField.getText());
                ControllerManager.getInstance().set(MainMenuController.getInstance(ControllerManager.getInstance())); //I View??
            }
        });

        Skin mySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));

        Label startText = new Label("Don't have an account? Click", mySkin, "font", "black");
        Label text = new Label("here", mySkin, "font", "dark-cyan");
        Label endText = new Label("to register!", mySkin, "font", "black");
        startText.setPosition((float) (StripaSurvivor.WIDTH*0.3),(float) (StripaSurvivor.HEIGHT*0.18));
        text.setPosition((float) (StripaSurvivor.WIDTH*0.53),(float) (StripaSurvivor.HEIGHT*0.18));
        endText.setPosition((float) (StripaSurvivor.WIDTH*0.57),(float) (StripaSurvivor.HEIGHT*0.18));

        text.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                ControllerManager.getInstance().set(RegisterController.getInstance(ControllerManager.getInstance()));
            }
        });

        this.addActor(startText);
        this.addActor(text);
        this.addActor(endText);
        this.addActor(usernameField);
        this.addActor(passwordField);
        this.addActor(loginButton);


    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        return instance;
    }


}
