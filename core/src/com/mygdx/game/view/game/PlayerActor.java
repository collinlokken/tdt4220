package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

public class PlayerActor extends StripaSurvivorActor {
    private static PlayerActor instance = null;



    private PlayerActor(int x, int y, int width, int height, int numberOfAnimations, Texture... textures){
        super(x, y, width, height, numberOfAnimations, textures);
    }

    public static final PlayerActor getInstance(int x, int y, int width, int height, int numberOfAnimations, Texture... textures){
        if (instance == null){
            instance = new PlayerActor(x, y, width, height, numberOfAnimations, textures);
        }
        return instance;
    }



}
