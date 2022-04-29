package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;

import java.util.ArrayList;

public class RegisterView extends View<RegisterController> {
    private static RegisterView instance = null;
    Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    ArrayList<Actor> modalActors = new ArrayList<>();
    Texture emptyUsernameTexture = new Texture("empty_username.png");
    Texture emptyPasswordTexture = new Texture("empty_password.png");

    private RegisterView(){
        super();
        Image bg = new Image(new TextureRegionDrawable(new Texture("register_bg.png")));
        bg.setPosition(0, 0);
        bg.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        this.addActor(bg);

        Skin metalSkin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

        final TextField usernameField = new TextField("", metalSkin);
        usernameField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.8));
        usernameField.setMessageText("Username");
        usernameField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));


        final TextField passwordField = new TextField("", metalSkin);
        passwordField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.6));
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setMessageText("Password");
        passwordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));


        final TextField confirmPasswordField = new TextField("", metalSkin);
        confirmPasswordField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.4));
        confirmPasswordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setMessageText("Confirm password");
        confirmPasswordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));


        this.addActor(usernameField);
        this.addActor(passwordField);
        this.addActor(confirmPasswordField);

        Button registerButton = new TextButton("Create account", glassySkin, "small");
        registerButton.setPosition((float) (getCamera().viewportWidth*0.08), (float) (getCamera().viewportHeight*0.2));
        registerButton.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.1));
        registerButton.addListener(new ClickListener(){
            @Override //TODO add user in database
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                if (usernameField.getText().equals("")){
                    addModal("username");
                } else if (passwordField.getText().equals("")) {
                    addModal("password");
                } else {
                    LoginController.getInstance().registerUserInDB(usernameField.getText(), passwordField.getText());
                    usernameField.setMessageText("Username");
                    passwordField.setMessageText("Password");
                    confirmPasswordField.setMessageText("Confirm password");
                    LoginController.getInstance().switchState(MainMenuController.getInstance());
                }
            }
        });

        this.addActor(registerButton);

    }

    public void addModal(String empty){
        Image bg;
        if (empty.equals("username")){
            bg = new Image(new TextureRegionDrawable(emptyUsernameTexture));
        } else {
            bg = new Image(new TextureRegionDrawable(emptyPasswordTexture));
        }

        bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));
        int modalWidth = emptyPasswordTexture.getWidth();
        int modalHeight = emptyPasswordTexture.getHeight();
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

        this.modalActors.add(bg);
        this.modalActors.add(textButton);
    }

    public void removeModal(){
        for(Actor actor : modalActors){
            actor.remove();
        }
    }

    public static final RegisterView getInstance(){
        if (instance == null){
            instance = new RegisterView();
        }
        return instance;
    }
}
