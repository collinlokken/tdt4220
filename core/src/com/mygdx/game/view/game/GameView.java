package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.view.game.spriteActors.BackgroundActor;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class GameView extends View<GameController> implements GameObserver
{
    private static GameView instance = null;

    private float speed;

    private  Game game;

    private ArrayList<Texture> textures = new ArrayList<Texture>(Arrays.asList(
            new Texture(Gdx.files.internal("player1.png")),
            new Texture(Gdx.files.internal("player2.png")),
            new Texture(Gdx.files.internal("player3.png")),
            new Texture(Gdx.files.internal("player4.png")),
            new Texture(Gdx.files.internal("player_flying.png")),
            new Texture(Gdx.files.internal("player_flying.png")),
            new Texture(Gdx.files.internal("player_flying.png")),
            new Texture(Gdx.files.internal("player_flying.png"))
    ));

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



    protected Texture shieldTexture;
    PlayerItem shield;
    protected Texture flame1;
    protected Texture flame2;
    protected Texture flame3;
    PlayerItem flames;
    protected Texture star1;
    protected Texture star2;
    protected Texture star3;
    protected Texture star4;
    PlayerItem stars;

    private ArrayList<PlayerItem> playerItems;


    private PlayerActor playerActor;

    private Label scoreText;
    private Label activePowerups;
    private static  final DecimalFormat df = new DecimalFormat("0.0");
    private Array<Image> lifePointImages = new Array<>();
    private Array<Image> powerupImages = new Array<>();

    private Sound died = Gdx.audio.newSound(Gdx.files.internal("aghh.ogg"));
    private Music music;

    private GameView(){
    }

    public PlayerActor getPlayerActor() {
        return playerActor;
    }



    public void setLifePoints(int lifePoints){
        for (Image image : this.lifePointImages){
            image.remove();
        }
        for (int i=0; i<lifePoints; i++){
            this.addActor(this.lifePointImages.get(i));
        }
    }

    public void setActivePowerups(String... ids){
        for (Image image : this.powerupImages){
            image.remove();
        }
        for (String id : ids){
            if (id.equals("virus")){
                this.addActor(this.powerupImages.get(1));
            }
            else if(id.equals("stand")){
                this.addActor(this.powerupImages.get(0));
            }
        }
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

    }

    @Override
    public void onEntityRemoved(Game game, Entity entity) {

    }

    @Override
    public void onGameEnded(Game game, Player player, float score) {

    }

    @Override
    public void onGameStarted(Game game)
    {
        shieldTexture = new Texture(Gdx.files.internal("shield.png"));

        shield = new PlayerItem(this.game.getPlayerEntity(), 1, new ArrayList<>(Arrays.asList(shieldTexture)));
        flame1 = new Texture(Gdx.files.internal("flame1.png"));
        flame2 = new Texture(Gdx.files.internal("flame2.png"));
        flame3 = new Texture(Gdx.files.internal("flame3.png"));
        flames = new PlayerItem(this.game.getPlayerEntity(), 1, new ArrayList<>(Arrays.asList(flame1, flame2, flame3)));
        star1 = new Texture(Gdx.files.internal("star1.png"));
        star2 = new Texture(Gdx.files.internal("star2.png"));
        star3 = new Texture(Gdx.files.internal("star3.png"));
        star4 = new Texture(Gdx.files.internal("star4.png"));
        stars = new PlayerItem(this.game.getPlayerEntity(), 1, new ArrayList<>(Arrays.asList(star1, star2, star3, star4)));
        playerItems = new ArrayList<PlayerItem>(Arrays.asList(shield, flames, stars));
        playerActor = new PlayerActor(2, playerItems, textures, this.game.getPlayerEntity());
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
        this.addActor(this.playerActor);
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
