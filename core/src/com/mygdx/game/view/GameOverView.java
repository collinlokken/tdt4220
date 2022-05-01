package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.view.game.GameView;

public class GameOverView extends View<GameOverController>{

    private static GameOverView instance = null;

    private Image screenshotBg;
    private Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("blackboard.png"))));
    private Image quit = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("quit.png"))));
    private Image restart = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("replay.png"))));
    private Image highscore = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("highscore.png"))));

    private GameOverView(){

        this.bg.setPosition(0.25f*getCamera().viewportWidth, 0.25f*getCamera().viewportHeight);
        this.bg.setSize(this.getCamera().viewportWidth/2, this.getCamera().viewportHeight/2);

        this.quit.setPosition(bg.getX()+bg.getWidth()/6, bg.getY()+bg.getHeight()/5);
        this.quit.setSize(bg.getWidth()/6, bg.getHeight()/6);
        this.quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screenshotBg.remove();
                MainMenuView.getInstance().playMusic();
                controller.switchState(MainMenuController.getInstance());
            }
        });

        this.restart.setPosition(bg.getX()+bg.getWidth()/2.5f, bg.getY()+bg.getHeight()/5);
        this.restart.setSize(bg.getWidth()/6, bg.getHeight()/6);
        this.restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screenshotBg.remove();
                GameView.getInstance().playMusic();
                controller.switchState(GameController.getInstance());
            }
        });

        this.highscore.setPosition(bg.getX()+bg.getWidth()/1.5f, bg.getY()+bg.getHeight()/5);
        this.highscore.setSize(bg.getWidth()/5, bg.getHeight()/6);
        this.highscore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screenshotBg.remove();
                MainMenuView.getInstance().playMusic();
                controller.switchState(LeaderboardController.getInstance());
            }
        });

        this.addActor(this.bg);
        this.addActor(this.quit);
        this.addActor(this.restart);
        this.addActor(this.highscore);

    }

    public static final GameOverView getInstance(){
        if (instance == null){
            instance =  new GameOverView();
        }
        return instance;
    }

    public void setBackground(Image image){
        this.screenshotBg = image;
        this.screenshotBg.setPosition(0,0);
        this.addActor(this.screenshotBg);
        this.screenshotBg.toBack();
    }

}
