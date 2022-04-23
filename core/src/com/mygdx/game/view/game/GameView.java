package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.PauseController;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.View;
import com.mygdx.game.view.game.spriteActors.BackgroundActor;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.help.HelpView;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class GameView extends View<GameController> {
    private static GameView instance = null;
    private int playerWidth = (int) (getCamera().viewportHeight*0.125);
    private int playerHeight = (int) (getCamera().viewportHeight*0.13);


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


    protected Texture shieldTexture = new Texture(Gdx.files.internal("shield.png"));
    PlayerItem shield = new PlayerItem(-playerWidth/4, -playerWidth/4, (int)(playerWidth*1.5), (int)(playerWidth*1.5), 1, new ArrayList<>(Arrays.asList(shieldTexture)));
    protected Texture flame1 = new Texture(Gdx.files.internal("flame1.png"));
    protected Texture flame2 = new Texture(Gdx.files.internal("flame2.png"));
    protected Texture flame3 = new Texture(Gdx.files.internal("flame3.png"));
    PlayerItem flames = new PlayerItem(-playerWidth/3, -playerHeight+3, playerWidth, playerHeight*5, 1, new ArrayList<>(Arrays.asList(flame1, flame2, flame3)));
    protected Texture star1 = new Texture(Gdx.files.internal("star1.png"));
    protected Texture star2 = new Texture(Gdx.files.internal("star2.png"));
    protected Texture star3 = new Texture(Gdx.files.internal("star3.png"));
    protected Texture star4 = new Texture(Gdx.files.internal("star4.png"));
    PlayerItem stars = new PlayerItem((int)(playerWidth*0.1), (int)(playerHeight*0.9), (int)(playerHeight*0.8), (int)(playerHeight*0.2), 1, new ArrayList<>(Arrays.asList(star1, star2, star3, star4)));

    private ArrayList<PlayerItem> playerItems = new ArrayList<PlayerItem>(Arrays.asList(shield, flames, stars));

    private float speed;

    private PlayerActor playerActor = PlayerActor.getInstance((int)(getCamera().viewportHeight/3), 0, playerWidth, playerHeight, 2, playerItems, textures);

    private Label scoreText;
    private Label activePowerups;
    private static  final DecimalFormat df = new DecimalFormat("0.0");
    private Array<Image> lifePointImages = new Array<>();
    private Array<Image> powerupImages = new Array<>();

    private Sound died = Gdx.audio.newSound(Gdx.files.internal("aghh.ogg"));
    private Music music;

    private GameView(){
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
                GameController.getInstance().touchedDown(true);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                GameController.getInstance().touchedDown(false);
            }
        });

        //PAUSE BUTTON
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("pause.png"))));
        pauseButton.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        pauseButton.setPosition(0, (float) (getCamera().viewportHeight)-pauseButton.getHeight());
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("PAUSE");
                ControllerManager.getInstance().push(PauseController.getInstance(new Image(ScreenUtils.getFrameBufferTexture())));
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
            image.setPosition((i*(image.getWidth()+10))+2*pauseButton.getWidth(), getCamera().viewportHeight-image.getHeight()-5);
            this.addActor(image);
        }
        this.addActor(this.playerActor);
        this.addActor(this.scoreText);



        this.addActor(pauseButton);
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
        for (Image image : this.lifePointImages){
            image.remove();
        }
        if (lifePoints == 0){
            this.draw();
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

    public void setSpeed(float speed){
        this.speed = speed;
    }
    public float getSpeed(){return speed;}


}
