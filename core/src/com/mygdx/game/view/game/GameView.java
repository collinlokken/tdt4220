package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.View;
import com.mygdx.game.view.game.spriteActors.BackgroundActor;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.help.HelpView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GameView extends View<GameController> {
    private static GameView instance = null;

    private Texture playerTexture1 = new Texture(Gdx.files.internal("player1.png"));
    private Texture playerTexture2 = new Texture(Gdx.files.internal("player2.png"));
    private Texture playerTexture3 = new Texture(Gdx.files.internal("player3.png"));
    private Texture playerTexture4 = new Texture(Gdx.files.internal("player4.png"));
    private Texture playerTexture5 = new Texture(Gdx.files.internal("player_activeJetpack1.png"));
    private Texture playerTexture6 = new Texture(Gdx.files.internal("player_activeJetpack2.png"));
    private Texture playerTexture7 = new Texture(Gdx.files.internal("player_activeJetpack3.png"));
    private Texture playerTexture8 = new Texture(Gdx.files.internal("player_activeJetpack3.png"));
    private Texture playerTexture9 = new Texture(Gdx.files.internal("player_flying.png"));
    private Texture playerTexture10 = new Texture(Gdx.files.internal("player_flying.png"));
    private Texture playerTexture11 = new Texture(Gdx.files.internal("player_flying.png"));
    private Texture playerTexture12 = new Texture(Gdx.files.internal("player_flying.png"));




    private int playerWidth = 150;
    private int playerHeight = 150;
    private PlayerActor playerActor = PlayerActor.getInstance(250, (int)getCamera().viewportHeight-playerHeight, playerWidth, playerHeight, 3, playerTexture1, playerTexture2, playerTexture3, playerTexture4, playerTexture5, playerTexture6, playerTexture7, playerTexture8, playerTexture9, playerTexture10, playerTexture11, playerTexture12);

    private Label scoreText;
    private static  final DecimalFormat df = new DecimalFormat("0.0");
    private Array<Image> lifePointImages;

    private GameView(){
        this.lifePointImages = new Array<Image>();

        Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        BackgroundActor ba = new BackgroundActor(400);
        ba.setPosition(0, 0);
        ba.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        ba.addListener(new ClickListener(){
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

        Skin skin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
        this.scoreText = new Label("", skin, "font", "black");
        this.scoreText.setPosition((float) (getCamera().viewportWidth/2-scoreText.getWidth()),(float) (getCamera().viewportHeight-20));
        this.scoreText.setFontScale(getCamera().viewportHeight/350);

        Image life1 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        Image life2 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        Image life3 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        this.lifePointImages.add(life1, life2, life3);

        this.addActor(ba);
        for (int i=0; i<this.lifePointImages.size; i++){
            Image image = this.lifePointImages.get(i);
            image.setSize(25, 25);
            image.setPosition((i*(image.getWidth()+10))+5, getCamera().viewportHeight-image.getHeight()-5);
            this.addActor(image);
        }
        this.addActor(this.playerActor);
        this.addActor(this.scoreText);

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

    public void setLifePoints(int lifePoints){
        //HENT UT ALLE ACTORS I RIKTIG I REKKEFØLGE, OG LEGG ANTALLET INN IGJEN BASERT PÅ HVOR MANGE LIFEPOINTS DET ER
        for (Image image : this.lifePointImages){
            image.remove();
        }
        for (int i=0; i<lifePoints; i++){
            this.addActor(this.lifePointImages.get(i));
        }
    }

    public void setScore(float s){
        this.scoreText.setText("Score: "+df.format(s));
    }

}
