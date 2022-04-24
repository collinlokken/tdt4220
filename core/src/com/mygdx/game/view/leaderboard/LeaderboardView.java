package com.mygdx.game.view.leaderboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.HighScore;
import com.mygdx.game.view.View;
import com.mygdx.game.view.help.HelpView;

import java.util.ArrayList;
import java.util.Map;

public class LeaderboardView extends View<LeaderboardController> {
    private static LeaderboardView instance = null;
    private Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private ArrayList<Label> highScores = new ArrayList<>();

    private LeaderboardView(){
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("leaderboard_eksempel.png"))));
        background.setPosition(0, 0);
        background.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        background.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                ControllerManager.getInstance().set(MainMenuController.getInstance());
                for(Label highScore : highScores){
                    highScore.remove();
                }
                highScores.clear();
            }
        });
        this.addActor(background);

    }

    public void addHighScoreToView(HighScore highScore){
        Label label = new Label(highScore.getUsername() +"   "+ highScore.getScore(), glassySkin, "font", "red");
        label.setPosition((float) (getCamera().viewportWidth*0.28),(float) (getCamera().viewportHeight-getCamera().viewportHeight*0.1*(highScores.size()+1)));
        label.setFontScale(getCamera().viewportHeight/550);
        this.addActor(label);
        this.highScores.add(label);
    }

    public static final LeaderboardView getInstance(){
        if (instance == null){
            instance = new LeaderboardView();
        }
        return instance;
    }
}
