package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.HighScore;

import java.util.ArrayList;

public class LeaderboardView extends View<LeaderboardController> {

    private static LeaderboardView instance = null;

    private Skin glassySkin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private ArrayList<Label> highScoreLabels = new ArrayList<>();
    private ArrayList<HighScore> highScores = new ArrayList<>();
    private Image background = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("blackboard2.png"))));
    private Image backButton = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("back.png"))));
    private int maxHighScores = 10;

    private LeaderboardView(){
        this.background.setPosition(0, 0);
        this.background.setSize(getCamera().viewportWidth, getCamera().viewportHeight);

        this.backButton.setSize(getCamera().viewportHeight/7, getCamera().viewportHeight/10);
        this.backButton.setPosition(getCamera().viewportWidth - 2*this.backButton.getWidth(), getCamera().viewportHeight - 2*this.backButton.getHeight());

        this.backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                controller.switchState(MainMenuController.getInstance());
                removeHighScoresFromView();
            }
        });
        this.addActor(this.background);
        this.addActor(this.backButton);

    }

    public void addHighScore(HighScore highScore){
        highScores.add(highScore);
    }

    public void renderAllHighScores() {
        for(int i = 0; i < this.maxHighScores; i++) {
            addHighScoreToView(highScores.get(i));
        }
    }

    public void removeHighScoresFromView() {
        for(Label highScore : highScoreLabels){
            highScore.remove();
        }
        highScoreLabels.clear();
        highScores.clear();
    }

    public void addHighScoreToView(HighScore highScore){
        int position = highScoreLabels.size()/3+1;
        Label pos = new Label(""+position, glassySkin, "font", "white");
        Label uname = new Label(""+highScore.getUsername(), glassySkin, "font", "white");
        Label score = new Label(String.format("%.1f",highScore.getScore()), glassySkin, "font", "white");

        int yOffset = 130;  // margin top
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
        this.highScoreLabels.add(pos);
        this.addActor(uname);
        this.highScoreLabels.add(uname);
        this.addActor(score);
        this.highScoreLabels.add(score);
    }

    public static final LeaderboardView getInstance(){
        if (instance == null){
            instance = new LeaderboardView();
        }
        return instance;
    }
}
