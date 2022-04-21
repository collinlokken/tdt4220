package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;

public class Stand extends Model{
    private float xRespawnPosition;
    private Rectangle collisionBox;
    private Texture texture;
    private int width;
    private int height;
    private String powerupId = "stand";

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Stand(float xPos, float yPos, float scale, float xRespawn, float speed){
        this.speed = speed;
        this.xRespawnPosition = xRespawn;
        texture = new Texture(Gdx.files.internal("stand.png"));
        this.width = (int) (texture.getWidth()*scale);
        this.height = (int) (texture.getHeight()*scale);
        collisionBox= new Rectangle(xPos, yPos, width, height);


    }

    @Override
    public void interact(PlayerModel playerModel) {
        if (!(playerModel.hasPowerup(powerupId))){
            playerModel.decreaseLifePoints();
        }
    }

    @Override
    public void update(float dt) {
        //System.out.println(collisionBox);
        if (collisionBox.getX() + collisionBox.width < 0){
            collisionBox.setX(xRespawnPosition);
            return;
        }

        collisionBox.setX(collisionBox.getX()-speed*dt);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public Texture getTexture(){
        return texture;
    }
}
