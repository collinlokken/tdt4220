package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.mygdx.game.model.CoffeeStandShield;
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
        Stand stand1Model = new Stand(3000, 0.4f, 500);
        ObstacleActor stand1Actor = new ObstacleActor(stand1Model.getTexture(), (int)stand1Model.getCollisionBox().x, (int)stand1Model.getCollisionBox().y,(int) stand1Model.getCollisionBox().getWidth(),(int) stand1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(stand1Model, stand1Actor));
        GameView.getInstance().addActor(stand1Actor);

        CoffeeStandShield coffee1Model = new CoffeeStandShield(0.3f,500,5);
        ObstacleActor coffie1Actor = new ObstacleActor(coffee1Model.getTexture(), (int) coffee1Model.getCollisionBox().x, (int) coffee1Model.getCollisionBox().y, (int)coffee1Model.getCollisionBox().getWidth(), (int)coffee1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(coffee1Model,coffie1Actor));
        GameView.getInstance().addActor(coffie1Actor);

        LifepointItem lifepoint1Model = new LifepointItem(0.075f,500);
        ObstacleActor lifepoint1Actor = new ObstacleActor(lifepoint1Model.getTexture(),(int) lifepoint1Model.getCollisionBox().x, (int) lifepoint1Model.getCollisionBox().y, (int)lifepoint1Model.getCollisionBox().getWidth(), (int)lifepoint1Model.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(lifepoint1Model, lifepoint1Actor));
        GameView.getInstance().addActor(lifepoint1Actor);

        for (int i = 0; i < 3; i++) {
            CoronaVirusObstacle virus1Model = new CoronaVirusObstacle(0.1f,500);
            ObstacleActor virus1Actor = new ObstacleActor(virus1Model.getTexture(),(int) virus1Model.getCollisionBox().x, (int) virus1Model.getCollisionBox().y, (int)virus1Model.getCollisionBox().getWidth(), (int)virus1Model.getCollisionBox().getHeight());
            modelActors.add(new GameControllerModelActorHelper(virus1Model, virus1Actor));
            GameView.getInstance().addActor(virus1Actor);
        }

        CoronaVirusShield maskModel = new CoronaVirusShield(0.05f,500,5);
        ObstacleActor maskActor = new ObstacleActor(maskModel.getTexture(),(int) maskModel.getCollisionBox().x, (int) maskModel.getCollisionBox().y, (int)maskModel.getCollisionBox().getWidth(), (int)maskModel.getCollisionBox().getHeight());
        modelActors.add(new GameControllerModelActorHelper(maskModel, maskActor));
        GameView.getInstance().addActor(maskActor);

        /*playerActor.getSprite().setPosition(150, GameView.getInstance().getCamera().viewportHeight - 150);
        playerActor.getSprite().setSize(150, 150);*/
        playerModel.setPosition(GameView.getInstance().getPlayerActor().getSprite().getX(), GameView.getInstance().getPlayerActor().getSprite().getY());
        playerModel.setCollisionBox(playerModel.getPosition().x, playerModel.getPosition().y, playerModel.getWidth(), playerModel.getHeight());
        playerModel.setWidth((int)GameView.getInstance().getPlayerActor().getSprite().getWidth());
        playerModel.setHeight((int)GameView.getInstance().getPlayerActor().getSprite().getHeight());
        modelActors.add(new GameControllerModelActorHelper(playerModel, playerActor));

    }

    public void touchedDown(boolean direction){
        if (direction) {
            playerModel.moveUp();
        }
        else {playerModel.moveDown();}

        playerActor.setActorPosition((int) playerModel.getPosition().x, (int) playerModel.getPosition().y);

    }

    public static final GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {
        for (GameControllerModelActorHelper modelActor : modelActors){
            modelActor.getModel().update(dt);
            modelActor.getActor().setActorPosition((int) modelActor.getModel().getCollisionBox().getX(), (int) modelActor.getModel().getCollisionBox().getY());

            if((playerModel != modelActor.getModel()) && playerModel.collides(modelActor.getModel().getCollisionBox())){
                modelActor.getModel().interact(playerModel);

            }
        }
        if (playerModel.getLifePoints() < 1){
            //TODO game over screen
            System.out.println("Game Over");
            playerModel.reset();
            ControllerManager.getInstance().set(MainMenuController.getInstance());
        }
    }
}
