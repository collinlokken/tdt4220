package com.mygdx.game.view.help;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.view.View;

public class HelpView extends View<HelpController> {
    private static HelpView instance = null;

    private HelpView(){
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("help_eksempel.png"))));
        background.setPosition(0, 0);
        background.setSize(StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT);
        this.addActor(background);

    }

    public static final HelpView getInstance(){
        if (instance == null){
            instance = new HelpView();
        }
        return instance;
    }
}
