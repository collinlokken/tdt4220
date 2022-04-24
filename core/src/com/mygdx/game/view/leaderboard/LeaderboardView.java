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
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("blackboard2.png"))));
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
        double position = highScores.size()/3+1;
        Label pos = new Label(""+(int) (position), glassySkin, "font", "white");
        Label uname = new Label(""+highScore.getUsername(), glassySkin, "font", "white");
        Label score = new Label(""+highScore.getScore(), glassySkin, "font", "white");

        int yOffset = 200;  // margin top
        float screenHeight = getCamera().viewportHeight;
        float xOffset = (float) (getCamera().viewportWidth*0.28);  // margin left
        float lineHeight = (float) (screenHeight*0.07);  // y distance between displayed scores
        float fontSize = screenHeight/400;

        float recordY = (float) (screenHeight-yOffset-lineHeight*position);
        int recordPadding = 200;  // x distance between position, username and score

        pos.setPosition(xOffset,recordY);
        uname.setPosition(xOffset + recordPadding - recordPadding/3,recordY);
        score.setPosition(xOffset + recordPadding*2 + recordPadding/3,recordY);


        pos.setFontScale(fontSize);
        uname.setFontScale(fontSize);
        score.setFontScale(fontSize);

        this.addActor(pos);
        this.highScores.add(pos);
        this.addActor(uname);
        this.highScores.add(uname);
        this.addActor(score);
        this.highScores.add(score);
    }

    public static final LeaderboardView getInstance(){
        if (instance == null){
            instance = new LeaderboardView();
        }
        return instance;
    }
}
