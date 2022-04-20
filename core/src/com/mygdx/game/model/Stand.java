package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.spriteActors.ObstacleActor;

public class Stand extends Obstacle{
    private float xRespawnPosition;
    private Rectangle collisionBox;
    private Texture texture;

    public Stand(float xPos, float yPos, float xRespawn, float speed){
        this.speed = speed;
        this.xRespawnPosition = xRespawn;
        texture = new Texture(Gdx.files.internal("stand.png"));
        collisionBox= new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
        GameView.getInstance().addActor(new ObstacleActor(this));


    }

    @Override
    public void interact(PlayerModel player) {
        if (!(player.hasCoffeePowerup())){
            player.decreaseLifePoints();
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
