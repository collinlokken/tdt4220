package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.model.Stand;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.leaderboard.LeaderboardView;

import java.util.ArrayList;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;
    private ArrayList<GameControllerModelActorHelper> modelActors;
    private PlayerModel playerModel;

    private GameController() {
        super(GameView.getInstance());
        modelActors = new ArrayList<>();
        playerModel = PlayerModel.getInstance();

        Stand stand1Model = new Stand(3000, 0, 0.4f, 3000, 500);
        ObstacleActor stand1Actor = new ObstacleActor(stand1Model.getTexture(), stand1Model.getWidth(), stand1Model.getHeight());
        modelActors.add(new GameControllerModelActorHelper(stand1Model, stand1Actor));
        GameView.getInstance().addActor(stand1Actor);

        GameView.getInstance().getPlayerActor().getSprite().setPosition(150, GameView.getInstance().getCamera().viewportHeight - 150);
        GameView.getInstance().getPlayerActor().getSprite().setSize(150, 150);
        playerModel.setPosition(150, GameView.getInstance().getCamera().viewportHeight - 150);
        playerModel.setWidth(150);
        playerModel.setHeight(150);
    }

    public void touchedDown(boolean direction){
        if (direction) {
            playerModel.moveUp();
        }
        else {playerModel.moveDown();}

        GameView.getInstance().getPlayerActor().getSprite().setPosition(playerModel.getPosition().x, playerModel.getPosition().y);

    }

    public static final GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {
        playerModel.update(dt);
        GameView.getInstance().getPlayerActor().getSprite().setPosition(playerModel.getPosition().x, playerModel.getPosition().y);
        for (GameControllerModelActorHelper modelActor : modelActors){
            modelActor.getModel().update(dt);
            modelActor.getActor().setActorPosition((int) modelActor.getModel().getCollisionBox().getX(), (int) modelActor.getModel().getCollisionBox().getY());
            if(playerModel.collides(modelActor.getModel().getCollisionBox())){
                System.out.println("KOLLISJONQ!!");
                modelActor.getModel().interact(playerModel);
            }
        }
    }
}
