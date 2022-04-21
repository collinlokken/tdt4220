package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class CoffeeStandShield extends Model{
    private Rectangle collisionBox;
    private float duration;
    private String powerupId = "stand";

    public CoffeeStandShield(float xPos, float yPos, float xWidth, float yWidth, float speed, float duration) {
        collisionBox = new Rectangle(xPos, yPos, xWidth, yWidth);
        this.speed = speed;
        this.duration = duration;

    }

    @Override
    public void interact(PlayerModel playerModel) {
        playerModel.addPowerup(powerupId,duration);
    }

    @Override
    public void update(float dt) {
        if (collisionBox.getX() + collisionBox.width < 0){
            return;
        }
        collisionBox.setX(collisionBox.getX()-speed*dt);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
