package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private ShapeRenderer shapeRenderer;

    private GameController() {
        super(GameView.getInstance());
        modelActors = new ArrayList<>();
        playerModel = PlayerModel.getInstance();
        playerActor = GameView.getInstance().getPlayerActor();


        Stand stand1Model = new Stand(3000, 0, 0.4f, 3000, 500);
        ObstacleActor stand1Actor = new ObstacleActor(stand1Model.getTexture(), (int)stand1Model.getCollisionBox().x, (int)stand1Model.getCollisionBox().y, stand1Model.getWidth(), stand1Model.getHeight());
        modelActors.add(new GameControllerModelActorHelper(stand1Model, stand1Actor));
        GameView.getInstance().addActor(stand1Actor);

        /*playerActor.getSprite().setPosition(150, GameView.getInstance().getCamera().viewportHeight - 150);
        playerActor.getSprite().setSize(150, 150);*/
        playerModel.setPosition(GameView.getInstance().getPlayerActor().getSprite().getX(), GameView.getInstance().getPlayerActor().getSprite().getY());
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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(playerModel.getPosition().x, playerModel.getPosition().y, playerActor.getSprite().getWidth(), playerActor.getSprite().getHeight());
        shapeRenderer.end();
        for (GameControllerModelActorHelper modelActor : modelActors){
            modelActor.getModel().update(dt);
            modelActor.getActor().setActorPosition((int) modelActor.getModel().getCollisionBox().getX(), (int) modelActor.getModel().getCollisionBox().getY());
            if((playerModel != modelActor.getModel()) && playerModel.collides(modelActor.getModel().getCollisionBox())){
                System.out.println("KOLLISJONQ!!");
                modelActor.getModel().interact(playerModel);
            }
        }
    }
}
