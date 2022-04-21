package com.mygdx.game.view.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.modal.ModalController;
import com.mygdx.game.view.View;

public class ModalView extends View<ModalController> {

    private static ModalView instance = null;

    public ModalView(){
        super();

        final Skin mySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));

        Texture texture = new Texture("wrong_login.png");
        Image bg = new Image(new TextureRegionDrawable(texture));
        bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));
        int modalWidth = texture.getWidth();
        int modalHeight = texture.getHeight();
        float modalOriginX = (getCamera().viewportWidth-modalWidth)/2;
        float modalOriginY = (getCamera().viewportHeight-modalHeight)/2;
        bg.setPosition(modalOriginX, modalOriginY);

        TextButton textButton = new TextButton("OK", mySkin);
        textButton.setPosition(modalOriginX+(modalWidth-textButton.getWidth())/2, modalOriginY+(modalHeight-textButton.getWidth())/2);
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ControllerManager.getInstance().pop(); // remove itself from the stack so that the state below is showed again
            }
        });

        this.addActor(bg);
        this.addActor(textButton);
    }

    public static final ModalView getInstance(){
        if (instance == null){
            instance = new ModalView();
        }
        return instance;
    }
}
