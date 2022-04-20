package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
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

    private GameController(){
        super(GameView.getInstance());
        modelActors = new ArrayList<>();
        playerModel = PlayerModel.getInstance();

        Stand stand1Model = new Stand(3000,0, 0.5f,3000,500);
        ObstacleActor stand1Actor = new ObstacleActor(stand1Model.getTexture(), stand1Model.getWidth(),stand1Model.getHeight());
        modelActors.add(new GameControllerModelActorHelper(stand1Model, stand1Actor));
        GameView.getInstance().addActor(stand1Actor);

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
