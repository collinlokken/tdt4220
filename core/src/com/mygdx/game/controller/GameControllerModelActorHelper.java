package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Model;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

public class GameControllerModelActorHelper {
    private StripaSurvivorActor actor;
    private Model model;

    public GameControllerModelActorHelper(Model model, StripaSurvivorActor actor){
        this.actor = actor;
        this.model = model;
    }

    public StripaSurvivorActor getActor() {
        return actor;
    }

    public Model getModel() {
        return model;
    }
}
