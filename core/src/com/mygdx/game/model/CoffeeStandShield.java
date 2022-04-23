package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class CoffeeStandShield extends Model{
    private Rectangle collisionBox;
    private float duration;
    private String powerupId = "stand";
    private Texture texture;
    private Random rand;

    public CoffeeStandShield( float scale, float duration) {
        this.duration = duration;
        rand = new Random();
        texture = new Texture(Gdx.files.internal("coffee.png"));
        float scaleMultiplyer = scale*Gdx.graphics.getHeight()/(texture.getHeight());
        collisionBox = new Rectangle((float) (rand.nextFloat()*(Gdx.graphics.getWidth() * 4.55)), rand.nextFloat()*(Gdx.graphics.getHeight()-texture.getHeight() * scaleMultiplyer), texture.getWidth() * scaleMultiplyer, texture.getHeight() * scaleMultiplyer);


    }

    @Override
    public void interact(PlayerModel playerModel) {
        playerModel.addPowerup(powerupId);
        reset();
    }

    @Override
    public void update(float dt) {
        if (collisionBox.getX() + collisionBox.width < 0){
            reset();
            return;
        }
        collisionBox.setX(collisionBox.getX()-gameSpeed*dt);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public void reset(){
        collisionBox.setX((float) (Gdx.graphics.getWidth() + rand.nextFloat()*(Gdx.graphics.getWidth() * 15)));
        collisionBox.setY(rand.nextFloat()*(Gdx.graphics.getHeight()-getCollisionBox().getHeight()));
    }
}
