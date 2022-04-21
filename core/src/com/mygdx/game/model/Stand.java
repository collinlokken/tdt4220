package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;

public class Stand extends Model{
    private Rectangle collisionBox;
    private Texture texture;
    private String powerupId = "stand";
    private float speed;

    public Stand(float xPos, float scale, float speed){
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("stand.png"));
        collisionBox= new Rectangle(xPos, 0, texture.getWidth()*scale, texture.getHeight()*scale);


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
            reset();
            return;
        }

        collisionBox.setX(collisionBox.getX()-speed*dt);

        speed += 5*dt;
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public Texture getTexture(){
        return texture;
    }

    public void reset(){
        getCollisionBox().setX((float) (Gdx.graphics.getWidth()*1.5));
    }
}
