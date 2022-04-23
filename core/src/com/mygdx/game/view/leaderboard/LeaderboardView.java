package com.mygdx.game.view.leaderboard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.view.View;
import com.mygdx.game.view.help.HelpView;

public class LeaderboardView extends View<LeaderboardController> {
    private static LeaderboardView instance = null;

    private LeaderboardView(){
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("leaderboard_eksempel.png"))));
        background.setPosition(0, 0);
        background.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        background.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                LeaderboardController.getInstance().switchState(MainMenuController.getInstance());
            }
        });
        this.addActor(background);

    }

    public static final LeaderboardView getInstance(){
        if (instance == null){
            instance = new LeaderboardView();
        }
        return instance;
    }
}
