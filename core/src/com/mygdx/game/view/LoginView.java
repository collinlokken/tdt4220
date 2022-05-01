package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.RegisterController;
import com.mygdx.game.view.game.GameView;

public class LoginView extends View {

    private static LoginView instance = null;

    private Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private Skin metalSkin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

    private Texture wrongLoginTexture = new Texture(Gdx.files.internal("wrong_login.png"));
    private Image backGroundActor;
    private TextButton textButtonActor;

    private Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("login_bg.png"))));
    private ImageButton loginButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("login.png")))));

    private final TextField usernameField = new TextField("", metalSkin);
    private final TextField passwordField = new TextField("", metalSkin);

    private Label startText = new Label("Don't have an account? Click", glassySkin, "font", "black");
    private Image text = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("here.png"))));
    private Label endText = new Label("to register!", glassySkin, "font", "black");

    private TextButton textButton = new TextButton("OK", glassySkin);

    private LoginView(){

        this.setBackGroundMusic(Gdx.audio.newMusic(Gdx.files.internal("skrillex.mp3")));
        GameView.getInstance().stopMusic();
        MainMenuView.getInstance().stopMusic();
        this.playMusic();

        this.bg.setPosition(0, 0);
        this.bg.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        this.addActor(this.bg);

        this.usernameField.setPosition((float) (getCamera().viewportWidth*0.20),(float) (getCamera().viewportHeight*0.335));
        this.usernameField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));
        this.usernameField.setMessageText("Username");

        this.passwordField.setPosition((float) (getCamera().viewportWidth*0.45),(float) (getCamera().viewportHeight*0.335));
        this.passwordField.setSize((float) (getCamera().viewportWidth*0.2), (float) (getCamera().viewportHeight*0.05));
        this.passwordField.setPasswordCharacter('*');
        this.passwordField.setPasswordMode(true);
        this.passwordField.setMessageText("Password");

        this.loginButton.setPosition((float) (getCamera().viewportWidth*0.7),(float) (getCamera().viewportHeight*0.322));
        this.loginButton.setSize((float) (getCamera().viewportWidth*0.08), (float) (getCamera().viewportHeight*0.08));
        this.loginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LoginController.getInstance().loginWithCredentials(usernameField.getText(), passwordField.getText());
            }
        });


        this.startText.setPosition((float) (getCamera().viewportWidth*0.28),(float) (getCamera().viewportHeight*0.18));
        this.startText.setFontScale(getCamera().viewportHeight/550);
        this.text.setPosition((float) (getCamera().viewportWidth*0.54),(float) (getCamera().viewportHeight*0.15));
        this.text.setWidth(getCamera().viewportWidth/12);
        this.text.setHeight(getCamera().viewportHeight/12);
        this.endText.setPosition((float) (getCamera().viewportWidth*0.62),(float) (getCamera().viewportHeight*0.18));
        this.endText.setFontScale(getCamera().viewportHeight/550);

        this.text.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                instance.controller.switchState(RegisterController.getInstance());
            }
        });

        this.addActor(this.startText);
        this.addActor(this.text);
        this.addActor(this.endText);
        this.addActor(this.usernameField);
        this.addActor(this.passwordField);
        this.addActor(this.loginButton);

    }

    public void addModal(){

        Image bg = new Image(new TextureRegionDrawable(this.wrongLoginTexture));
        bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));
        int modalWidth = wrongLoginTexture.getWidth();
        int modalHeight = wrongLoginTexture.getHeight();
        float modalOriginX = (getCamera().viewportWidth-modalWidth)/2;
        float modalOriginY = (getCamera().viewportHeight-modalHeight)/2;
        bg.setPosition(modalOriginX, modalOriginY);

        this.textButton.setPosition(modalOriginX+(modalWidth-this.textButton.getWidth())/2, modalOriginY+(modalHeight-this.textButton.getWidth())/2);
        this.textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                removeModal();
            }
        });

        this.addActor(bg);
        this.addActor(this.textButton);

        this.backGroundActor = bg;
        this.textButtonActor = textButton;
    }

    public void removeModal(){
        this.backGroundActor.remove();
        this.textButtonActor.remove();
    }

    private void clearInputFields(){
        this.usernameField.setText("");
        this.usernameField.setMessageText("Username");
        this.passwordField.setText("");
        this.passwordField.setMessageText("Password");
    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        instance.clearInputFields();
        return instance;
    }


}
