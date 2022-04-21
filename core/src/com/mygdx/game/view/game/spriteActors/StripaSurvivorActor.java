package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class StripaSurvivorActor extends Actor {

    private Sprite sprite;
    private Vector2 position;

    public StripaSurvivorActor(Sprite sprite){
        this.sprite = sprite;
        this.position.x = this.sprite.getX();
        this.position.y = this.sprite.getY();
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void setActorPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setWidth(0);
        this.setHeight(0);
    }
}
