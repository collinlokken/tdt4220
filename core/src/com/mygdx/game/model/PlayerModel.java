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
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class PlayerModel extends Model{
    private static PlayerModel instance = null;
    private int lifePoints;
    private Rectangle collisionBox;

    private ArrayList<String> activePowerupIds;
    private float justLostLifeTimer;

    private static final int GRAVITY = -25;
    private Vector2 velocity;
    private Vector2 position;
    private int width;
    private int height;
    private boolean movingUp = false;
    private boolean lowerEdge = false;
    private boolean upperEdge = false;
    private float score = 0;
    private float startSpeed = 500;



    private PlayerModel(){
        super();
        lifePoints = 3;
        justLostLifeTimer = 0;
        activePowerupIds = new ArrayList<>();
        collisionBox = new Rectangle(0, 0, 10, 10);

        velocity = new Vector2(0, 0);
        position = new Vector2(0, 0);
        collisionBox = new Rectangle(0, 0, 0, 0);
        gameSpeed = startSpeed;
    }

    public void setCollisionBox(float x, float y, float width, float height){
        collisionBox.setPosition(x, y);
        collisionBox.setSize(width, height);
    }

    public boolean getBottom(){
        return this.lowerEdge;
    }

    public boolean getDirection(){
        return this.movingUp;
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

    @Override
    public void interact(PlayerModel playerModel) {

    }

    public float getScore(){
        return this.score;
    }

    public void addScore(float score){
        this.score += score;
    }

    public void update(float dt) {
        score += dt*11;

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
        gameSpeed += 5*dt;

        if (justLostLifeTimer > 0) {
            justLostLifeTimer -= dt;
            if (justLostLifeTimer < 0) {
                removePowerup("justlostlife");
            }
        }


        this.setCollisionBox(this.getPosition().x, this.getPosition().y, this.width, this.height);

    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    public boolean collides(Rectangle rectangle){
        return collisionBox.overlaps(rectangle);
    }

    public void addPowerup(String powerupId){
        System.out.println("Add powerup: "+ powerupId);
        for (String id : activePowerupIds){
            if (id.equals(powerupId)){
                return;
            }
        }
        activePowerupIds.add(powerupId);

    }
    public void removePowerup(String powerupId){
        System.out.println("Removing powerup: "+ powerupId);
        for (String id : activePowerupIds){
            if (id.equals(powerupId)){
                activePowerupIds.remove(id);
                System.out.println("Active powerups after remove: " +  activePowerupIds.toString());
                return;
            }
        }

    }

    public boolean hasPowerup(String powerupId){
        //System.out.println(activePowerupIds);
        for (String id : activePowerupIds){
            if (id.equals(powerupId)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getActivePowerupIds(){
        return this.activePowerupIds;
    }

    public void increaseLifepoints(){
        if (lifePoints < 3){
            lifePoints += 1;
        }
        System.out.println("lifepoints: " + lifePoints);
    }

    public void decreaseLifePoints(){
        if (!hasPowerup("justlostlife")) {
            lifePoints -= 1;
            addPowerup("justlostlife");
            justLostLifeTimer = 2;
            System.out.println("lifepoints: " + lifePoints);
        }
    }
    public int getLifePoints(){
        return lifePoints;
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

    public float getSpeed(){ return gameSpeed; }

    public float getJustLostLifeTimer() {return justLostLifeTimer;}

    public void reset(){
        lifePoints = 3;
        score = 0;
        gameSpeed = startSpeed;
        activePowerupIds.clear();
        this.position.x = Gdx.graphics.getWidth()/3;
        this.position.y = 0;
    }


}
