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

    private GameOverView(){
        super();

        Image bg = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("blackboard.png"))));
        bg.setPosition(getCamera().viewportWidth/3, getCamera().viewportHeight/3);
        bg.setSize(getCamera().viewportWidth/3, getCamera().viewportHeight/3);

        Image quit = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("quit.png"))));
        quit.setPosition(getCamera().viewportWidth/3+100, getCamera().viewportHeight/3+100);
        quit.setSize(100, 50);
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                GameOverController.getInstance().switchState(MainMenuController.getInstance());
            }
        });

        Image restart = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("replay.png"))));
        restart.setPosition(getCamera().viewportWidth/3+200, getCamera().viewportHeight/3+100);
        restart.setSize(100, 50);
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                GameOverController.getInstance().switchState(GameController.getInstance());
            }
        });

        Image highscore = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("highscore.png"))));
        highscore.setPosition(getCamera().viewportWidth/3+300, getCamera().viewportHeight/3+100);
        highscore.setSize(100, 50);
        highscore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
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
            return new GameOverView();
        }
        return instance;
    }
}
