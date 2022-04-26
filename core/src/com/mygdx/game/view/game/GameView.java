package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.GameObserver;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.View;
import com.mygdx.game.view.game.actors.BackgroundActor;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GameView extends View<GameController> implements GameObserver
{
    private static GameView instance = null;

    private float speed;

    private  Game game;

    private Map<Entity, Actor> actors = new HashMap();

    public static final GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }
    public float getSpeed(){return this.speed;}




    private Label scoreText;
    private Label activePowerups;
    private static  final DecimalFormat df = new DecimalFormat("0.0");
    private Array<Image> lifePointImages = new Array<>();
    private Array<Image> powerupImages = new Array<>();

    private Sound died = Gdx.audio.newSound(Gdx.files.internal("aghh.ogg"));
    private Music music;

    private GameView()
    {

    }


    public void setScore(float s){
        this.scoreText.setText("Score: "+df.format(s));
    }


    public void playSound(){
        this.music.stop();
        this.died.play(0.5f);
    }

    public void initialize(Game game)
    {
        this.game = game;
        game.addObserver(this);
    }


    @Override
    public void onEntityAdded(Game game, Entity entity) {
        try
        {
            Actor entityActor = EntityActorFactory.getInstance().createActor(entity);
            this.actors.put(entity, entityActor);
            this.addActor(entityActor);
        }
        catch(Exception e)
        {
            System.out.println("Unhandled exception occurred when creating actor: " + e.getMessage());
        }
    }

    @Override
    public void onEntityRemoved(Game game, Entity entity)
    {
        this.actors.get(entity).remove();
    }

    @Override
    public void onGameEnded(Game game, Player player, float score)
    {

    }

    @Override
    public void onGameStarted(Game game)
    {


        //playerActor = new PlayerActor(2, playerItems, textures, this.game.getPlayerEntity());
        music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(1f);
        music.play();

        BackgroundActor ba = new BackgroundActor();
        ba.setPosition(0, 0);
        ba.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        ba.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                GameController.getInstance().onTouchDown();
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                GameController.getInstance().onTouchUp();
            }
        });

        Skin skin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
        this.scoreText = new Label("", skin, "font", "black");
        this.scoreText.setPosition(getCamera().viewportWidth/3,(float)(getCamera().viewportHeight*0.95));
        this.scoreText.setFontScale(getCamera().viewportHeight/300);

        Image life1 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        Image life2 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        Image life3 = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("heart.png"))));
        this.lifePointImages.add(life1, life2, life3);

        this.addActor(ba);
        for (int i=0; i<this.lifePointImages.size; i++){
            Image image = this.lifePointImages.get(i);
            image.setSize((int)(getCamera().viewportHeight*0.1), (int)(getCamera().viewportHeight*0.1));
            image.setPosition((i*(image.getWidth()+10))+5, getCamera().viewportHeight-image.getHeight()-5);
            this.addActor(image);
        }
        this.addActor(this.scoreText);

        this.activePowerups = new Label("Protection against:", skin, "font", "black");
        this.activePowerups.setPosition((float)(getCamera().viewportWidth*0.65),(float)(getCamera().viewportHeight*0.93));
        this.activePowerups.setFontScale(getCamera().viewportHeight/350);

        this.addActor(this.activePowerups);

        Image miniStand = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("stand.png"))));
        miniStand.setSize((int)(getCamera().viewportHeight*0.1), (int)(getCamera().viewportHeight*0.1));
        miniStand.setPosition((float)(getCamera().viewportWidth*0.88),(float)(getCamera().viewportHeight*0.9));
        Image miniVirus = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("virus.png"))));
        miniVirus.setSize((int)(getCamera().viewportHeight*0.1), (int)(getCamera().viewportHeight*0.1));
        miniVirus.setPosition((float)(getCamera().viewportWidth*0.88+miniStand.getWidth()),(float)(getCamera().viewportHeight*0.9));
        this.powerupImages.add(miniStand, miniVirus);
    }
}
