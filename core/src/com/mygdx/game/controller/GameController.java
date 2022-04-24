package com.mygdx.game.controller;

import com.mygdx.game.model.Game;
import com.mygdx.game.model.game.entity.Player;

import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.PlayerActor;

import java.util.ArrayList;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;


    private Game game;

    private GameController() {
        super(GameView.getInstance());
        this.game = new Game();
    }

    public static GameController getInstance()
    {
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    public  void startGame() //TODO Call this when appropriate
    {
        this.game = new Game();
    }

    public  void onTouchDown() //TODO Call this from view!
    {
        this.game.getPlayerEntity().startBoosting();
    }

    public  void onTouchUp() //TODO Call this from view!
    {
        this.game.getPlayerEntity().stopBoosting();
    }


    @Override
    public void update(float dt)
    {
        this.game.update(dt);
    }
}
