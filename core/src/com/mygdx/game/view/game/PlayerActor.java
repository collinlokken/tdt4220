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
    private Sprite sprite;

    private PlayerActor(Texture texture, int x, int y, int width, int height){
        super(new Sprite(texture, x, y, width, height));

    }

    public static final PlayerActor getInstance(Texture texture, int x, int y, int width, int height){
        if (instance == null){
            instance = new PlayerActor(texture, x, y, width, height);
        }
        return instance;
    }


}
