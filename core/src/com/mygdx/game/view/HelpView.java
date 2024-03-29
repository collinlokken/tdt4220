package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.controller.MainMenuController;

import jdk.tools.jmod.Main;

public class HelpView extends View<HelpController> {

    private static HelpView instance = null;
    private Image firstSlide = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("ssHelpView.png"))));
    private Image backButton = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("back.png"))));

    private HelpView(){

        this.firstSlide.setPosition(0, 0);
        this.firstSlide.setSize(getCamera().viewportWidth, getCamera().viewportHeight);

        this.backButton.setSize(getCamera().viewportHeight/7, getCamera().viewportHeight/10);
        this.backButton.setPosition(getCamera().viewportWidth - this.backButton.getWidth(), getCamera().viewportHeight - this.backButton.getHeight());
        this.backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                controller.switchState(MainMenuController.getInstance());
            }
        });

        this.addActor(this.firstSlide);
        this.addActor(this.backButton);

    }

    public static final HelpView getInstance(){
        if (instance == null){
            instance = new HelpView();
        }
        return instance;
    }

}
