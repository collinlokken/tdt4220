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
    private PlayerActor playerActor;

    private ArrayList<String> activePowerupIds;
    private ArrayList<Float> activePowerupTimers;

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
        activePowerupIds = new ArrayList<>();
        activePowerupTimers = new ArrayList<>();
        collisionBox = new Rectangle(0, 0, 10, 10);

        velocity = new Vector2(0, 0);
        position = new Vector2(0, 0);
        collisionBox = new Rectangle(0, 0, 0, 0);
    }

    public void setCollisionBox(float x, float y, float width, float height){
        collisionBox.setPosition(x, y);
        collisionBox.setSize(width, height);
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
    public void interact(PlayerModel player) {

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


        //Decrease powerup timers
        for (int i = 0; i < activePowerupTimers.size(); i++) {
            activePowerupTimers.set(i, activePowerupTimers.get(i)-dt);
            //System.out.println(activePowerupTimers.get(i));
            if (activePowerupTimers.get(i) < 0){
                System.out.println("remove powerup: "+ activePowerupIds.get(i));
                activePowerupIds.remove(i);
                activePowerupTimers.remove(i);
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

    public void addPowerup(String powerupId, float powerupDuration){
        System.out.println("Add powerup: "+ powerupId);
        for (String id : activePowerupIds){
            if (id.equals(powerupId)){
                activePowerupTimers.set(activePowerupIds.indexOf(id),powerupDuration);
                return;
            }
        }
        activePowerupIds.add(powerupId);
        //System.out.println(activePowerupIds);
        activePowerupTimers.add(powerupDuration);
        //System.out.println(activePowerupTimers);

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
    public void increaseLifepoints(){
        lifePoints += 1;
        System.out.println("lifepoints: " + lifePoints);
    }

    public void decreaseLifePoints(){
        if (!hasPowerup("justlostlife")) {
            lifePoints -= 1;
            addPowerup("justlostlife", 2f);
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

    public void reset(){
        lifePoints = 3;
        activePowerupTimers.clear();
        activePowerupIds.clear();
    }


}
