package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class StripaSurvivorActor extends Actor {

    private Sprite sprite;
    private Vector2 position;

    public StripaSurvivorActor(Texture texture, int x, int y, int width, int height){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition((float)x, (float)y);
        this.sprite.setSize((float)width, (float)width);
        this.position = new Vector2(this.sprite.getX(), this.sprite.getY());
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
