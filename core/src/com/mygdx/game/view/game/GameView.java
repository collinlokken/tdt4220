package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.view.View;
import com.mygdx.game.view.help.HelpView;

public class GameView extends View<GameController> {
    private static GameView instance = null;

    private GameView(){
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("game_eksempel.png"))));
        background.setPosition(0, 0);
        background.setSize(StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT);
        this.addActor(background);

    }

    public static final GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }
}
