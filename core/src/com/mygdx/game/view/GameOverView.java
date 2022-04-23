package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.MainMenuController;

public class GameOverView extends View<GameOverController>{
    private static GameOverView instance = null;

    private float finalScore;
    private Image screenshotBg;

    private GameOverView(){
        super();

        Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("blackboard.png"))));
        bg.setPosition(0.25f*getCamera().viewportWidth, 0.25f*getCamera().viewportHeight);
        bg.setSize(this.getCamera().viewportWidth/2, this.getCamera().viewportHeight/2);

        Image quit = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("quit.png"))));
        quit.setPosition(bg.getX()+bg.getWidth()/6, bg.getY()+bg.getHeight()/5);
        quit.setSize(bg.getWidth()/6, bg.getHeight()/6);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                screenshotBg.remove();
                ControllerManager.getInstance().set(MainMenuController.getInstance());
                GameOverController.getInstance().switchState(MainMenuController.getInstance());

            }
        });

        Image restart = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("replay.png"))));
        restart.setPosition(bg.getX()+bg.getWidth()/2.5f, bg.getY()+bg.getHeight()/5);
        restart.setSize(bg.getWidth()/6, bg.getHeight()/6);
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                screenshotBg.remove();
                ControllerManager.getInstance().set(GameController.getInstance());
                GameOverController.getInstance().switchState(GameController.getInstance());
            }
        });

        Image highscore = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("highscore.png"))));
        highscore.setPosition(bg.getX()+bg.getWidth()/1.5f, bg.getY()+bg.getHeight()/5);
        highscore.setSize(bg.getWidth()/5, bg.getHeight()/6);
        highscore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                screenshotBg.remove();
                ControllerManager.getInstance().set(LeaderboardController.getInstance());
                GameOverController.getInstance().switchState(LeaderboardController.getInstance());
            }
        });

        this.addActor(bg);
        this.addActor(quit);
        this.addActor(restart);
        this.addActor(highscore);

    }

    public static final GameOverView getInstance(){
        if (instance == null){
            instance =  new GameOverView();
        }
        return instance;
    }

    public void setBackground(Image image){
        this.screenshotBg = image;
        screenshotBg.setPosition(0,0);
        this.addActor(screenshotBg);
        screenshotBg.toBack();
    }

}
