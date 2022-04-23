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
    private boolean isInteracting = false;

    public Stand(float scale){
        texture = new Texture(Gdx.files.internal("stand.png"));
        float scaleMultiplyer = scale*Gdx.graphics.getHeight()/(texture.getHeight());
        collisionBox= new Rectangle(1.5f*Gdx.graphics.getWidth(), 0, texture.getWidth()*scaleMultiplyer, texture.getHeight()*scaleMultiplyer);


    }

    @Override
    public void interact(PlayerModel playerModel) {
        if (!(playerModel.hasPowerup(powerupId))&& !isInteracting){
            playerModel.decreaseLifePoints();

        }
        else if (!isInteracting){
            playerModel.removePowerup(powerupId);
            isInteracting = true;
            System.out.println("---------");
            System.out.println(powerupId);
            System.out.println("---------");
        }

    }

    @Override
    public void update(float dt) {
        //System.out.println(collisionBox);
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

    public Texture getTexture(){
        return texture;
    }

    public void reset(){
        getCollisionBox().setX((float) (Gdx.graphics.getWidth()*1.5));
        isInteracting=false;
    }
}
