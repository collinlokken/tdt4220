package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
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


        /*playerModel.update(dt);
        for (Model model : models){
            model.update(dt);
            if(playerModel.collides(model.getCollisionBox())){
                model.interact(playerModel);
            }
        }
        //*/

    }
}
