package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Obstacle;

public class ObstacleActor extends StripaSurvivorActor {

    public ObstacleActor(Texture texture, int x, int y, int width, int height){
        super(texture, x, y, width, height, false);

    }

}
