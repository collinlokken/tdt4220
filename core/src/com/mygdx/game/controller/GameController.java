package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.model.CoffeeStandShield;
import com.mygdx.game.model.CoinItem;
import com.mygdx.game.model.CoronaVirusObstacle;
import com.mygdx.game.model.CoronaVirusShield;
import com.mygdx.game.model.Items;
import com.mygdx.game.model.LifepointItem;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.model.Stand;

import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.PlayerActor;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;
import com.mygdx.game.view.leaderboard.LeaderboardView;

import java.util.ArrayList;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;
    private ArrayList<GameControllerModelActorHelper> modelActors;
    private PlayerModel playerModel;
    private PlayerActor playerActor;


    private GameController() {
        super(GameView.getInstance());
        modelActors = new ArrayList<>();
        playerModel = PlayerModel.getInstance();
        playerActor = GameView.getInstance().getPlayerActor();


        //Init obstacles and items
        Stand stand1Model = new Stand( 0.4f);
        ObstacleActor stand1Actor = new ObstacleActor(stand1Model.getTexture(), (int)stand1Model.getCollisionBox().x, (int)stand1Model.getCollisionBox().y,(int) stand1Model.getCollisionBox().getWidth(),(int) stand1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(stand1Model, stand1Actor));
        GameView.getInstance().addActor(stand1Actor);


        CoffeeStandShield coffee1Model = new CoffeeStandShield(0.2f,5);
        ObstacleActor coffie1Actor = new ObstacleActor(coffee1Model.getTexture(), (int) coffee1Model.getCollisionBox().x, (int) coffee1Model.getCollisionBox().y, (int)coffee1Model.getCollisionBox().getWidth(), (int)coffee1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(coffee1Model,coffie1Actor));
        GameView.getInstance().addActor(coffie1Actor);

        LifepointItem lifepoint1Model = new LifepointItem(0.125f);
        ObstacleActor lifepoint1Actor = new ObstacleActor(lifepoint1Model.getTexture(),(int) lifepoint1Model.getCollisionBox().x, (int) lifepoint1Model.getCollisionBox().y, (int)lifepoint1Model.getCollisionBox().getWidth(), (int)lifepoint1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(lifepoint1Model, lifepoint1Actor));
        GameView.getInstance().addActor(lifepoint1Actor);

        for (int i = 0; i < 3; i++) {
            CoronaVirusObstacle virus1Model = new CoronaVirusObstacle(0.2f);
            ObstacleActor virus1Actor = new ObstacleActor(virus1Model.getTexture(),(int) virus1Model.getCollisionBox().x, (int) virus1Model.getCollisionBox().y, (int)virus1Model.getCollisionBox().getWidth(), (int)virus1Model.getCollisionBox().getHeight());
            modelActors.add(new GameControllerModelActorHelper(virus1Model, virus1Actor));
            GameView.getInstance().addActor(virus1Actor);
        }


        CoronaVirusShield maskModel = new CoronaVirusShield(0.125f,5);
        ObstacleActor maskActor = new ObstacleActor(maskModel.getTexture(),(int) maskModel.getCollisionBox().x, (int) maskModel.getCollisionBox().y, (int)maskModel.getCollisionBox().getWidth(), (int)maskModel.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(maskModel, maskActor));
        GameView.getInstance().addActor(maskActor);

        for (int i = 0; i < 5; i++) {
            CoinItem coinModel = new CoinItem(0.125f, i);
            ObstacleActor coinActor = new ObstacleActor(coinModel.getTexture(), (int) coinModel.getCollisionBox().x, (int) coinModel.getCollisionBox().y, (int) coinModel.getCollisionBox().getWidth(), (int) coinModel.getCollisionBox().getHeight());
            modelActors.add(new GameControllerModelActorHelper(coinModel, coinActor));
            GameView.getInstance().addActor(coinActor);
        }

        playerModel.setPosition(GameView.getInstance().getPlayerActor().getSprite().getX(), GameView.getInstance().getPlayerActor().getSprite().getY());
        playerModel.setWidth((int)GameView.getInstance().getPlayerActor().getSprite().getWidth());
        playerModel.setHeight((int)GameView.getInstance().getPlayerActor().getSprite().getHeight());
        playerModel.setCollisionBox(playerModel.getPosition().x, playerModel.getPosition().y, playerModel.getWidth(), playerModel.getHeight());
        modelActors.add(new GameControllerModelActorHelper(playerModel, playerActor));

    }

    public void touchedDown(boolean direction){
        if (direction) {
            playerModel.moveUp();
        }
        else {
            playerModel.moveDown();
        }
        playerActor.setActorPosition((int) playerModel.getPosition().x, (int) playerModel.getPosition().y);

    }

    public static final GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;

    }

    public void updatePowerup(){
        if (playerModel.getActivePowerupIds().contains("virus") && playerModel.getActivePowerupIds().contains("stand")){
            GameView.getInstance().setActivePowerups("virus", "stand");
            GameView.getInstance().getPlayerActor().setShield(true);
        }
        else if (playerModel.getActivePowerupIds().contains("virus")){
            GameView.getInstance().getPlayerActor().setShield(true);
            GameView.getInstance().setActivePowerups("virus");
        }
        else if (playerModel.getActivePowerupIds().contains("stand")){
            GameView.getInstance().getPlayerActor().setShield(true);
            GameView.getInstance().setActivePowerups("stand");
        }
        else {
            GameView.getInstance().getPlayerActor().setShield(false);
            GameView.getInstance().setActivePowerups("notAPowerupID");
        }
        if (playerModel.getActivePowerupIds().contains("justlostlife")){
            GameView.getInstance().getPlayerActor().blinking(true);
        }
        else{
            GameView.getInstance().getPlayerActor().blinking(false);
        }
    }

    @Override
    public void update(float dt) {
        this.updatePowerup();
        if (playerModel.getBottom()){
            playerActor.setActiveAnimation(PlayerActor.ANIMATION_TYPES.RUNNING);
        }
        else if (playerModel.getDirection()){
            playerActor.setActiveAnimation(PlayerActor.ANIMATION_TYPES.FLYING);
            playerActor.setFlames(true);
        }
        else{
            playerActor.setActiveAnimation(PlayerActor.ANIMATION_TYPES.FLYING);
            playerActor.setFlames(false);
        }
        for (GameControllerModelActorHelper modelActor : modelActors){
            modelActor.getModel().update(dt);
            modelActor.getActor().setActorPosition((int) modelActor.getModel().getCollisionBox().getX(), (int) modelActor.getModel().getCollisionBox().getY());

            if((playerModel != modelActor.getModel()) && playerModel.collides(modelActor.getModel().getCollisionBox())){
                modelActor.getModel().interact(playerModel);
            }
        }

        GameView.getInstance().setScore(playerModel.getScore());
        GameView.getInstance().setLifePoints(playerModel.getLifePoints());
        GameView.getInstance().setSpeed(playerModel.getSpeed()-100);


        if (playerModel.getLifePoints() < 1){
            this.reset();
            GameView.getInstance().playSound();
            this.pushState(GameOverController.getInstance(new Image(ScreenUtils.getFrameBufferTexture())));

        }
    }
    public void reset(){
        for (GameControllerModelActorHelper modelActor : modelActors) {
            modelActor.getModel().reset();
        }
    }


}
