package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.view.game.GameView;
import com.mygdx.game.view.game.PlayerActor;
import java.awt.SystemColor;

import java.util.ArrayList;

public class PlayerModel{
    private static PlayerModel instance = null;
    private int lifePoints;
    private Rectangle collisionBox;
    private PlayerActor playerActor;
    private float timeLeftCoffeePowerup;


    private static final int GRAVITY = -25;
    private Vector2 velocity;
    private Vector2 position;
    private int width;
    private int height;
    private boolean movingUp = false;
    private boolean lowerEdge = false;
    private boolean upperEdge = false;


    private PlayerModel(){
        super();
        lifePoints = 3;
        collisionBox = new Rectangle(0, 0, 10, 10);
        playerActor = PlayerActor.getInstance(new Texture(Gdx.files.internal("barrySprite.png")));
        GameView.getInstance().addActor(playerActor);
        velocity = new Vector2(0, 0);
        position = new Vector2(0, 0);
    }

    public static final PlayerModel getInstance(){
        if (instance == null){
            instance = new PlayerModel();
        }
        return instance;
    }

    public void setPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    public void update(float dt) {
        if (lowerEdge){
            velocity.y = 0;
        }

        if (upperEdge){
            velocity.y = 0;
        }

        if (movingUp){
            velocity.add(0, -(GRAVITY-10));
        }

        else {
            velocity.add(0, GRAVITY);
        }

        velocity.scl(dt);

        this.setPosition(this.getPosition().x, this.getPosition().y + velocity.y);

        if (this.getPosition().y <= 0){
            lowerEdge = true;
            this.setPosition(this.getPosition().x, 0);
        }
        else if (this.getPosition().y >= GameView.getInstance().getCamera().viewportHeight - this.getHeight()){
            upperEdge = true;
            this.setPosition(this.getPosition().x, GameView.getInstance().getCamera().viewportHeight - this.getHeight());
        }
        else {
            upperEdge = false;
            lowerEdge = false;
        }

        velocity.scl(1/dt);

    }

    public boolean collides(Rectangle rectangle){
        return collisionBox.overlaps(rectangle);
    }

    public boolean hasCoffeePowerup(){
        return timeLeftCoffeePowerup > 0;    }

    public void setCoffeePowerup(float duration){
        timeLeftCoffeePowerup += duration;
    }

    public void decreaseLifePoints(){
        lifePoints -= 1;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void moveUp() {
        velocity.y = 100;
        movingUp = true;
    }

    public void moveDown() {
        movingUp = false;
    }

    public void setWidth(int w){
        width = w;
    }

    public void setHeight(int h){
        height = h;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }


}
