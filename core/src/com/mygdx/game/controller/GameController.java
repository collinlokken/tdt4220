package com.mygdx.game.controller;

import com.mygdx.game.model.Model;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

import java.util.ArrayList;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;

    private ArrayList<Model> models;
    private PlayerModel playerModel;

    private GameController(ControllerManager controllerManager){
        super(controllerManager, GameView.getInstance());
        models = new ArrayList<>();
    }

    public static final GameController getInstance(ControllerManager controllerManager){
        if (instance == null){
            instance = new GameController(controllerManager);
        }
        return instance;
    }

    @Override
    public void update(float dt) {
        playerModel.update(dt);
        for (Model model : models){
            model.update(dt);
            if(playerModel.collides(model.getCollisionBox())){
                model.interact(playerModel);
            }
        }
        //render

    }
}
