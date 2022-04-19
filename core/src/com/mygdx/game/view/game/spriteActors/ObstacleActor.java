package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.Obstacle;

public class ObstacleActor extends Actor {
    private Sprite sprite;
    private Obstacle obstacle;

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(obstacle.getCollisionBox().getX(), obstacle.getCollisionBox().getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public ObstacleActor(Obstacle obstacle){
        sprite = new Sprite(obstacle.getTexture(), (int) obstacle.getCollisionBox().getWidth(), (int) obstacle.getCollisionBox().getHeight());
        this.obstacle = obstacle;

    }
}
