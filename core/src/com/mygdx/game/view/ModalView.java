package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.ModalController;

public class ModalView extends View<ModalController> {

    private static ModalView instance = null;

    private final Skin mySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private TextButton textButton = new TextButton("OK", mySkin);

    private Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("wrong_login.png"))));

    public ModalView(){

        this.bg.setSize((float) (getCamera().viewportWidth*0.4),(float) (getCamera().viewportHeight*0.4));
        int modalWidth = (int)this.bg.getWidth();
        int modalHeight = (int)this.bg.getHeight();
        float modalOriginX = (getCamera().viewportWidth-modalWidth)/2;
        float modalOriginY = (getCamera().viewportHeight-modalHeight)/2;
        this.bg.setPosition(modalOriginX, modalOriginY);

        this.textButton.setPosition(modalOriginX+(modalWidth-textButton.getWidth())/2, modalOriginY+(modalHeight-textButton.getWidth())/2);
        this.textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ModalController.getInstance().popState();
            }
        });

        this.addActor(this.bg);
        this.addActor(this.textButton);
    }

    public static final ModalView getInstance(){
        if (instance == null){
            instance = new ModalView();
        }
        return instance;
    }
}
