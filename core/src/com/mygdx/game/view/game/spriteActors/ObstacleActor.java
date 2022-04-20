package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Obstacle;

public class ObstacleActor extends Actor {
    private Sprite sprite;
    private int posX;
    private int posY;

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(posX, posY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public ObstacleActor(Texture texture, int width, int height){
        sprite = new Sprite(texture);
        sprite.setSize(width,height);
    }
    public void setActorPosition(int x, int y){
        posX = x;
        posY = y;
    }
}
