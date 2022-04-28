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
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.GameObserver;
import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.HealthComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.View;
import com.mygdx.game.view.game.actors.BackgroundActor;
import com.mygdx.game.view.game.actors.HealthbarActor;
import com.mygdx.game.view.game.actors.ProtectionAgainstActor;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GameView extends View<GameController> implements GameObserver
{
    private static GameView instance = null;

    private  Game game;

    private Map<Entity, Actor> actors = new HashMap();


    public static final GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }



    private Label scoreText;
    private Label activePowerups;
    private static  final DecimalFormat df = new DecimalFormat("0.0");
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
            System.out.println(e.getClass().getSimpleName());
            System.out.println("Unhandled exception occurred when creating actor: " + e.getMessage());
        }
    }

    @Override
    public void onEntityRemoved(Game game, Entity entity)
    {
        this.actors.get(entity).remove();
    }

    @Override
    public void onEntityComponentAdded(Game game, Entity entity, Component component) {

    }

    @Override
    public void onEntityComponentRemoved(Game game, Entity entity, Component component)
    {

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

        BackgroundActor ba = new BackgroundActor((int)this.getWidth());
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


        this.addActor(ba);
        this.addActor(new HealthbarActor((int)this.getCamera().viewportHeight, this.game));
        this.addActor(new ProtectionAgainstActor((int)this.getCamera().viewportWidth, (int)this.getCamera().viewportHeight, this.game));
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
