package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class CoinItem extends Model{
    private float speed;
    private Texture texture;
    private Rectangle collisionBox;
    private Random rand;
    private float scoreValue = 50;
    private int coinNumber;
    private static float nextStartPositionX;
    private static float nextStartPositionY;

    public CoinItem( float scale, float speed, int coinNumber) {
        this.speed = speed;
        this.coinNumber = coinNumber;
        rand = new Random();
        texture = new Texture(Gdx.files.internal("dogecoin.png"));
        if (coinNumber == 0) {
            nextStartPositionX = (float) (rand.nextFloat() * (Gdx.graphics.getWidth() * 4.55));
            nextStartPositionY = rand.nextFloat() * (Gdx.graphics.getHeight() - texture.getHeight() * scale);
        }
        collisionBox = new Rectangle(nextStartPositionX + 1.5f*coinNumber*(texture.getWidth()*scale), nextStartPositionY, texture.getWidth() * scale, texture.getHeight() * scale);

    }

    @Override
    public void interact(PlayerModel playerModel) {
        playerModel.addScore(scoreValue);
        reset();
    }

    @Override
    public void update(float dt) {
        if (collisionBox.getX() + collisionBox.width < 0){
            reset();
            return;
        }
        collisionBox.setX(collisionBox.getX()-speed*dt);
        if (coinNumber == 0){
            nextStartPositionX -= speed*dt;
        }

        speed += 5*dt;
    }

    @Override
    public void reset() {
        if (coinNumber == 0) {
            nextStartPositionX = (float)(Gdx.graphics.getWidth() + rand.nextFloat() * (Gdx.graphics.getWidth() * 4.55));
            nextStartPositionY = rand.nextFloat() * (Gdx.graphics.getHeight() - collisionBox.getHeight());
        }
        collisionBox.setX(nextStartPositionX + 1.5f*coinNumber*collisionBox.getWidth());
        collisionBox.setY(nextStartPositionY);
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
