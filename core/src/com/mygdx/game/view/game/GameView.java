package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.View;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.help.HelpView;

import java.util.ArrayList;

public class GameView extends View<GameController> {
    private static GameView instance = null;

    private Texture playerTexture1 = new Texture(Gdx.files.internal("player1.png"));
    private Texture playerTexture2 = new Texture(Gdx.files.internal("player2.png"));
    private Texture playerTexture3 = new Texture(Gdx.files.internal("player3.png"));
    private Texture playerTexture4 = new Texture(Gdx.files.internal("player4.png"));

    private int playerWidth = 150;
    private int playerHeight = 150;
    private PlayerActor playerActor = PlayerActor.getInstance(250, (int)getCamera().viewportHeight-playerHeight, playerWidth, playerHeight, playerTexture1, playerTexture2, playerTexture3, playerTexture4);

    private Music music;




    private GameView(){
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("test_background.png"))));
        background.setPosition(0, 0);
        background.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();
        background.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                GameController.getInstance().touchedDown(true);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                GameController.getInstance().touchedDown(false);
            }
        });

        this.addActor(background);
        this.addActor(playerActor);

    }

    public PlayerActor getPlayerActor() {
        return playerActor;
    }

    public static final GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }

}
