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
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.RegisterController;

import java.util.ArrayList;

import jdk.internal.net.http.common.Log;

public class RegisterView extends View<RegisterController> {

    private static RegisterView instance = null;

    Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    Skin metalSkin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

    private final TextField usernameField = new TextField("", metalSkin);
    private final TextField passwordField = new TextField("", metalSkin);
    private final TextField confirmPasswordField = new TextField("", metalSkin);

    ArrayList<Actor> modalActors = new ArrayList<>();

    private Button registerButton = new TextButton("Create account", glassySkin, "small");

    private Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("register_bg.png"))));

    private Image backButton = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("back.png"))));

    private RegisterView(){

        this.backButton.setSize(getCamera().viewportHeight/7, getCamera().viewportHeight/10);
        this.backButton.setPosition(getCamera().viewportWidth - this.backButton.getWidth(), getCamera().viewportHeight - this.backButton.getHeight());
        this.backButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                clearInputFields();
                controller.switchState(LoginController.getInstance());
            }
        });

        this.bg.setPosition(0, 0);
        this.bg.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        this.addActor(this.bg);

        this.usernameField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.8));
        this.usernameField.setMessageText("Username");
        this.usernameField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));


        this.passwordField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.6));
        this.passwordField.setPasswordCharacter('*');
        this.passwordField.setPasswordMode(true);
        this.passwordField.setMessageText("Password");
        this.passwordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));

        this.confirmPasswordField.setPosition((float) (getCamera().viewportWidth*0.08),(float) (getCamera().viewportHeight*0.4));
        this.confirmPasswordField.setPasswordCharacter('*');
        this.confirmPasswordField.setPasswordMode(true);
        this.confirmPasswordField.setMessageText("Confirm password");
        this.confirmPasswordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));


        this.addActor(this.usernameField);
        this.addActor(this.passwordField);
        this.addActor(this.confirmPasswordField);
        this.addActor(this.backButton);


        this.registerButton.setPosition((float) (getCamera().viewportWidth*0.08), (float) (getCamera().viewportHeight*0.2));
        this.registerButton.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.1));
        this.registerButton.addListener(new ClickListener(){
            @Override //TODO add user in database
            public void clicked(InputEvent event, float x, float y){
                instance.controller.registerUserInDB(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText());
            }
        });

        this.addActor(this.registerButton);

    }

    public void addModal(Texture texture){
        Image bg = new Image(new TextureRegionDrawable(texture));
        bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));

        int modalWidth = texture.getWidth();
        int modalHeight = texture.getHeight();

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

    private void clearInputFields(){
        this.usernameField.setText("");
        this.usernameField.setMessageText("Username");
        this.passwordField.setText("");
        this.passwordField.setMessageText("Password");
        this.confirmPasswordField.setText("");
        this.confirmPasswordField.setMessageText("Confirm password");
    }

    public static final RegisterView getInstance(){
        if (instance == null){
            instance = new RegisterView();
        }
        return instance;
    }
}
