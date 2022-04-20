package com.mygdx.game.controller;

import com.mygdx.game.model.Model;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;

public class GameControllerModelActorHelper {
    private ObstacleActor actor;
    private Model model;

    public GameControllerModelActorHelper(Model model, ObstacleActor actor){
        this.actor = actor;
        this.model = model;
    }

    public ObstacleActor getActor() {
        return actor;
    }

    public Model getModel() {
        return model;
    }
}
