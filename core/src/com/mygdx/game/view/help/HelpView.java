package com.mygdx.game.view.help;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.view.View;

import java.util.ArrayList;

public class HelpView extends View<HelpController> {
    private static HelpView instance = null;



    private HelpView(){
        Image firstSlide = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("tutorials.png"))));
       // Image secondSlide = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("tutorialsBiggerText.png"))));
       // Image thirdSlide = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("tutorialsBiggerText.png"))));

       // ArrayList<Image> images = new ArrayList<Image>();
       // images.add(firstSlide);
       // images.add(secondSlide);
       // images.add(thirdSlide);
        firstSlide.setPosition(0, 0);
        firstSlide.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        firstSlide.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                HelpController.getInstance().switchState(MainMenuController.getInstance());
            }
        });
        this.addActor(firstSlide);

        // nextButton
        // ImageButton nextButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("heloNextButton2.png"))));
        // nextButton.setSize(64, 64);
        // nextButton.setPosition((float) (getCamera().viewportWidth*0.93-nextButton.getWidth()/2), (float) (getCamera().viewportHeight*0.50));
        // nextButton.addListener(new ClickListener(){
        //    @Override
        //    public void clicked(InputEvent event, float x, float y) {
        //        super.clicked(event, x, y);

        //    }
    //    });
        // this.addActor(nextButton);

    }

    public static final HelpView getInstance(){
        if (instance == null){
            instance = new HelpView();
        }
        return instance;
    }
    //public void nextSlide


}
