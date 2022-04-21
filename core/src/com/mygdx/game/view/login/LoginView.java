package com.mygdx.game.view.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;
import com.mygdx.game.controller.modal.ModalController;
import com.mygdx.game.view.View;

public class LoginView extends View {
    private static LoginView instance = null;
    private Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private Texture wrongLoginTexture = new Texture("wrong_login.png");
    private Image backGroundActor;
    private TextButton textButtonActor;

    private LoginView(){
        super();
        Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));
        Image bg = new Image(new TextureRegionDrawable(new Texture("login_bg.png")));
        bg.setPosition(0, 0);
        bg.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        this.addActor(bg);


        final TextField usernameField = new TextField("", skin);
        usernameField.setPosition((float) (getCamera().viewportWidth*0.20),(float) (getCamera().viewportHeight*0.335));
        usernameField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));
        usernameField.setMessageText("Username");

        final TextField passwordField = new TextField("", skin);
        passwordField.setPosition((float) (getCamera().viewportWidth*0.45),(float) (getCamera().viewportHeight*0.335));
        passwordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setMessageText("Password");



        ImageButton loginButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("login.png"))));
        loginButton.setPosition((float) (getCamera().viewportWidth*0.7),(float) (getCamera().viewportHeight*0.322));
        loginButton.setSize((float) (getCamera().viewportWidth*0.08), (float) (getCamera().viewportHeight*0.08));
        loginButton.addListener(new ClickListener(){
            @Override //TODO Send username + pass to database to authenticate
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                LoginController.getInstance().loginWithCredentials(usernameField.getText(), passwordField.getText());
            }
        });

        Skin mySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));

        Label startText = new Label("Don't have an account? Click", mySkin, "font", "black");
        Label text = new Label("here", mySkin, "font", "dark-cyan");
        Label endText = new Label("to register!", mySkin, "font", "black");
        startText.setPosition((float) (getCamera().viewportWidth*0.315),(float) (getCamera().viewportHeight*0.18));
        startText.setFontScale(getCamera().viewportHeight/550);
        text.setPosition((float) (getCamera().viewportWidth*0.53),(float) (getCamera().viewportHeight*0.18));
        text.setFontScale(getCamera().viewportHeight/550);
        endText.setPosition((float) (getCamera().viewportWidth*0.57),(float) (getCamera().viewportHeight*0.18));
        endText.setFontScale(getCamera().viewportHeight/550);

        text.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                ControllerManager.getInstance().set(RegisterController.getInstance());
            }
        });

        this.addActor(startText);
        this.addActor(text);
        this.addActor(endText);
        this.addActor(usernameField);
        this.addActor(passwordField);
        this.addActor(loginButton);


    }

    public void addModal(){

        Image bg = new Image(new TextureRegionDrawable(wrongLoginTexture));
        System.out.println(bg);
        bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));
        int modalWidth = wrongLoginTexture.getWidth();
        int modalHeight = wrongLoginTexture.getHeight();
        float modalOriginX = (getCamera().viewportWidth-modalWidth)/2;
        float modalOriginY = (getCamera().viewportHeight-modalHeight)/2;
        bg.setPosition(modalOriginX, modalOriginY);

        TextButton textButton = new TextButton("OK", glassySkin);
        textButton.setPosition(modalOriginX+(modalWidth-textButton.getWidth())/2, modalOriginY+(modalHeight-textButton.getWidth())/2);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                removeModal();
            }
        });


        addActor(bg);
        addActor(textButton);

        this.backGroundActor = bg;
        this.textButtonActor = textButton;
    }

    public void removeModal(){
        this.backGroundActor.remove();
        this.textButtonActor.remove();
    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        return instance;
    }


}
