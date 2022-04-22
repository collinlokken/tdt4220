package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;

import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

public class PlayerActor extends StripaSurvivorActor {
    private static PlayerActor instance = null;


    private PlayerActor(int x, int y, int width, int height, int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures){
        super(x, y, width, height, numberOfAnimations, playerItems, textures);

    }

    public static final PlayerActor getInstance(int x, int y, int width, int height, int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures){
        if (instance == null){
            instance = new PlayerActor(x, y, width, height, numberOfAnimations, playerItems, textures);
        }
        return instance;
    }

    public void setShield(boolean shield){
        if (shield){
            this.shield = true;
        }
        else{
            this.shield = false;
        }
    }







}
