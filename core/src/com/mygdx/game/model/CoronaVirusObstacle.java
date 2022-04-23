package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class CoronaVirusObstacle extends Model{
    private Rectangle collisionBox;
    private Texture texture;
    private String powerupId = "virus";
    private Random rand;

    public CoronaVirusObstacle(float scale) {
        rand = new Random();
        texture = new Texture(Gdx.files.internal("virus.png"));
        float scaleMultiplyer = scale*Gdx.graphics.getHeight()/(texture.getHeight());
        collisionBox = new Rectangle(rand.nextFloat()*2*Gdx.graphics.getWidth(), rand.nextFloat()*(Gdx.graphics.getHeight()-texture.getHeight() * scaleMultiplyer), texture.getWidth() * scaleMultiplyer, texture.getHeight() * scaleMultiplyer);

    }

    @Override
    public void interact(PlayerModel playerModel) {
        reset();
        if (!(playerModel.hasPowerup(powerupId))){
            playerModel.decreaseLifePoints();

        }
        else{
            playerModel.removePowerup(powerupId);
        }
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
    public void reset() {
        getCollisionBox().setX(Gdx.graphics.getWidth() + rand.nextFloat()*Gdx.graphics.getWidth());
        getCollisionBox().setY(rand.nextFloat()*(Gdx.graphics.getHeight()-getCollisionBox().getHeight()));

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
