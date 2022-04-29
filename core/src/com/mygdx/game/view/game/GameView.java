package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.GameObserver;
import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.PauseController;
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
    private Game game;
    private Map<Entity, Actor> actors = new HashMap();

    public static final GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }

    private Skin skin = new Skin(Gdx.files.internal("glassyui/glassy-ui.json"));
    private Label scoreText;
    private Label activePowerups;

    private static final DecimalFormat df = new DecimalFormat("0.0");
    private Array<Image> powerupImages = new Array<>();

    private ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("pause.png"))));
    private Image miniStand = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("stand.png"))));
    private Image miniVirus = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("virus.png"))));

    private Sound died = Gdx.audio.newSound(Gdx.files.internal("aghh.ogg"));
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));

    private GameView() {}

    public void setScore(float s){
        this.scoreText.setText("Score: "+df.format(s));
    }

    public void playGameOverSound(){
        this.music.stop();
        this.died.play(0.5f);
    }

    public void startMusic(){
        this.music.setLooping(true);
        this.music.setVolume(1f);
        this.music.play();
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
    public void onEntityComponentAdded(Game game, Entity entity, Component component) {}

    @Override
    public void onEntityComponentRemoved(Game game, Entity entity, Component component) {}

    @Override
    public void onGameEnded(Game game, Player player, float score)
    {
        //TODO: Her må vi sørge for at onGameEnded ikke blir kalt ETTER at scoren resettes
        System.out.println("GAME OVER. SCORE: "+this.game.getPlayerEntity().getComponent(ScoreComponent.class).getValue());
        this.controller.switchState(GameOverController.getInstance(new Image(ScreenUtils.getFrameBufferTexture()), this.game.getPlayerEntity().getComponent(ScoreComponent.class).getValue()));
    }

    @Override
    public void onGameStarted(Game game)
    {
        this.startMusic();

        BackgroundActor ba = new BackgroundActor((int)this.getWidth(), (int)this.getHeight());
        ba.setPosition(0, 0);
        ba.setSize(this.getWidth(), this.getHeight());
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

        pauseButton.setSize(this.getHeight()/10, this.getHeight()/10);
        pauseButton.setPosition(0, this.getHeight() - pauseButton.getHeight());
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameController.getInstance().pushState(PauseController.getInstance(new Image(ScreenUtils.getFrameBufferTexture())));
            }
        });

        this.scoreText = new Label("", this.skin, "font", "black");
        this.scoreText.setPosition(this.getWidth()/3,(float)(this.getHeight()*0.95));
        this.scoreText.setFontScale(this.getHeight()/300);
        this.activePowerups = new Label("Protection against:", this.skin, "font", "black");
        this.activePowerups.setPosition((float)(this.getWidth()*0.63),(float)(this.getHeight()*0.93));
        this.activePowerups.setFontScale(this.getHeight()/350);

        this.addActor(ba);
        this.addActor(new HealthbarActor((int)this.getHeight(), this.game));
        this.addActor(new ProtectionAgainstActor((int)this.getWidth(), (int)this.getHeight(), this.game));
        this.addActor(this.scoreText);
        this.addActor(this.pauseButton);
        this.addActor(this.activePowerups);

        this.miniStand.setSize((int)(this.getHeight()*0.08), (int)(this.getHeight()*0.08));
        this.miniStand.setPosition((float)(this.getWidth()*0.88),(float)(this.getHeight()*this.miniStand.getHeight()));

        this.miniVirus.setSize((int)(this.getHeight()*0.08), (int)(this.getHeight()*0.08));
        this.miniVirus.setPosition((float)(this.getWidth()*0.88+miniStand.getWidth()),(float)(this.getHeight()-1.1*this.miniVirus.getHeight()));

        this.powerupImages.add(this.miniStand, this.miniVirus);

    }

}
