package com.mygdx.game.controller;

import com.mygdx.game.model.Model;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.leaderboard.LeaderboardView;

import java.util.ArrayList;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;
    private ArrayList<Model> models;
    private PlayerModel playerModel = PlayerModel.getInstance();

    private GameController(){
        super(GameView.getInstance());
        models = new ArrayList<>();
    }

    public void touchedDown(boolean direction){
        if (direction) {
            playerModel.moveUp();
        }
        else {playerModel.moveDown();}


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
        for (Model model : models){
            model.update(dt);
            if(playerModel.collides(model.getCollisionBox())){
                model.interact(playerModel);
            }
        }
        //render

    }
}
