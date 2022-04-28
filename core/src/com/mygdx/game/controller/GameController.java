package com.mygdx.game.controller;

import com.mygdx.game.model.game.Game;

import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.view.game.GameView;

public class GameController extends Controller<GameView>{
    private static GameController instance = null;

    private Game game;

    private GameController() {
        super(GameView.getInstance());
        this.game = Game.getInstance(this.view.getWidth() / this.view.getHeight());
        this.view.initialize(this.game);
    }

    public static GameController getInstance()
    {
        if(instance == null)
            instance = new GameController();
        return instance;
    }


    public  void onTouchDown() //TODO Call this from view!
    {
        if(this.game.isStarted())
            this.game.getPlayerEntity().startBoosting();
    }

    public void onTouchUp() //TODO Call this from view!
    {
        if(this.game.isStarted())
            this.game.getPlayerEntity().stopBoosting();
    }

    @Override
    public void update(float dt)
    {
        if(!this.game.isStarted())
            this.game.startGame();
        this.game.update(dt);
        if(this.game.isStarted())
            this.view.setScore(this.game.getPlayerEntity().getComponent(ScoreComponent.class).getValue());
    }
}
