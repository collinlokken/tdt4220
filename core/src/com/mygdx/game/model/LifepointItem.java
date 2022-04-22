package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class LifepointItem extends Model{
    private float speed;
    private Texture texture;
    private Rectangle collisionBox;
    private Random rand;

    public LifepointItem( float scale, float speed) {
        this.speed = speed;
        rand = new Random();
        texture = new Texture(Gdx.files.internal("heart.png"));
        collisionBox = new Rectangle((float) (rand.nextFloat()*(Gdx.graphics.getWidth() * 4.55)), rand.nextFloat()*(Gdx.graphics.getHeight()-texture.getHeight() * scale), texture.getWidth() * scale, texture.getHeight() * scale);
    }

    @Override
    public void interact(PlayerModel playerModel) {
        playerModel.increaseLifepoints();
        reset();
    }

    @Override
    public void update(float dt) {
        if (collisionBox.getX() + collisionBox.width < 0){
            reset();
            return;
        }
        collisionBox.setX(collisionBox.getX()-speed*dt);

        speed += 5*dt;
    }

    @Override
    public void reset() {
        collisionBox.setX((Gdx.graphics.getWidth() + rand.nextFloat()*(Gdx.graphics.getWidth() * 15)));
        collisionBox.setY(rand.nextFloat()*(Gdx.graphics.getHeight()-getCollisionBox().getHeight()));
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
