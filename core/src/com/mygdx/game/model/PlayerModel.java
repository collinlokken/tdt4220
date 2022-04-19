package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.view.game.GameView;

import java.awt.SystemColor;

public class PlayerModel extends Actor {
    private static PlayerModel instance = null;
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("player.png")));

    private static final int GRAVITY = -25;
    private Vector2 velocity;
    private Vector2 position;
    private boolean movingUp = false;
    private boolean lowerEdge = false;
    private boolean upperEdge = false;



    private float timeLeftFacemaskPowerup;
    private float timeLeftCoffeePowerup;
    private float timeLeftCookbookPowerup;
    private float timeLeftBongPowerup;

    private Rectangle collisionBox;

    private PlayerModel(){
        super();
        velocity = new Vector2(0, 0);
        position = new Vector2(0, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

    }



    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(lowerEdge);
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

        velocity.scl(delta);

        sprite.setPosition(sprite.getX(), sprite.getY()+velocity.y);

        if (sprite.getY() <= 0){
            lowerEdge = true;
            sprite.setPosition(sprite.getX(), 0);
        }
        else if (sprite.getY() >= GameView.getInstance().getCamera().viewportHeight-sprite.getHeight()){
            upperEdge = true;
            sprite.setPosition(sprite.getX(), GameView.getInstance().getCamera().viewportHeight-sprite.getHeight());
        }
        else {
            upperEdge = false;
            lowerEdge = false;
        }



        velocity.scl(1/delta);
        this.setWidth(0);
        this.setHeight(0);

    }

    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public static final PlayerModel getInstance(){
        if (instance == null){
            instance = new PlayerModel();
        }
        return instance;
    }



    public void update(float dt) {
        timeLeftFacemaskPowerup-=dt;
        if (timeLeftFacemaskPowerup > 0){
            //view should render a facemask
        }
    }

    public boolean collides(Rectangle rectangle){
        return collisionBox.overlaps(rectangle);
    }

    public void moveUp() {
        velocity.y = 100;
        movingUp = true;
    }

    public void moveDown() {
        movingUp = false;

    }
}
