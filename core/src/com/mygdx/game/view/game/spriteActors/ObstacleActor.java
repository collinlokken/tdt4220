package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.view.game.PlayerItem;

import java.util.ArrayList;
import java.util.Arrays;

public class ObstacleActor extends StripaSurvivorActor {

    public ObstacleActor(Texture texture, int x, int y, int width, int height){
        super(x, y, width, height, 1, new ArrayList<Texture>(Arrays.asList(texture)));

    }

}
