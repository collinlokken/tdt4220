package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.view.game.PlayerAnimation;

public abstract class StripaSurvivorActor extends Actor {

    private Sprite sprite;
    private Vector2 position;
    private boolean isPlayer;
    private PlayerAnimation playerAnimation;
    private int width;
    private int height;

    public StripaSurvivorActor(Texture texture, int x, int y, int width, int height, boolean isPlayer){
        this.isPlayer = isPlayer;
        if (this.isPlayer){
            playerAnimation = playerAnimation.getInstance(new TextureRegion(texture), 4, 0.4f);
            playerAnimation.getSpriteFrame().setPosition(x, y);
            playerAnimation.getSpriteFrame().setSize(width, height);
            this.position = new Vector2(playerAnimation.getSpriteFrame().getX(), playerAnimation.getSpriteFrame().getX());
            setBounds(playerAnimation.getSpriteFrame().getX(), playerAnimation.getSpriteFrame().getX(), playerAnimation.getSpriteFrame().getWidth(), playerAnimation.getSpriteFrame().getHeight());
            this.width = width;
            this.height = height;
        }
        else {
            this.sprite = new Sprite(texture);
            this.sprite.setPosition(x, y);
            this.sprite.setSize(width, height);
            this.position = new Vector2(this.sprite.getX(), this.sprite.getY());
            setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        }
    }

    public void setActorPosition(float x, float y){
        if (this.isPlayer){
            playerAnimation.getSpriteFrame().setPosition(x, y);
        }
        else{
            this.sprite.setPosition(x, y);
        }

    }

    public Sprite getSprite(){
        if (this.isPlayer){
            return playerAnimation.getSpriteFrame();
        }
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.isPlayer){
            playerAnimation.getSpriteFrame().draw(batch);
        }
        else{
            sprite.draw(batch);
        }

    }

    @Override
    public void act(float delta) {
        if (this.isPlayer){
            playerAnimation.update(delta);
            playerAnimation.setSpriteSize(this.width, this.height);
        }
        else{
            super.act(delta);
        }
        this.setWidth(0);
        this.setHeight(0);

    }
}
