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
    protected PlayerAnimation playerAnimation;

    public StripaSurvivorActor(int x, int y, int width, int height, Texture... textures){
        this.playerAnimation = playerAnimation.getInstance(textures.length, 0.1f, textures);
        this.playerAnimation.getSpriteFrame().setPosition((float)x, (float)y);
        this.playerAnimation.getSpriteFrame().setSize((float)width, (float)width);
        this.position = new Vector2(this.playerAnimation.getSpriteFrame().getX(), this.playerAnimation.getSpriteFrame().getY());
        setBounds(playerAnimation.getSpriteFrame().getX(), playerAnimation.getSpriteFrame().getY(), playerAnimation.getSpriteFrame().getWidth(), playerAnimation.getSpriteFrame().getHeight());

        /*
        else{
            this.sprite = new Sprite(texture);
            this.sprite.setPosition((float)x, (float)y);
            this.sprite.setSize((float)width, (float)width);
            this.position = new Vector2(this.sprite.getX(), this.sprite.getY());
            setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        }*/
    }

    /*public void setActorPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public Sprite getSprite(){
        return sprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setWidth(0);
        this.setHeight(0);
    }*/


    public void setActorPosition(float x, float y) {
        System.out.println(x);
        this.playerAnimation.setSpritePosition(x, y);
        this.playerAnimation.setSpriteSize((int)this.playerAnimation.getSpriteFrame().getWidth(), (int)this.playerAnimation.getSpriteFrame().getWidth());
    }


    public Sprite getSprite() {
        return this.playerAnimation.getSpriteFrame();
    }


    public void draw(Batch batch, float parentAlpha) {
        this.playerAnimation.getSpriteFrame().draw(batch);

    }


    public void act(float delta) {
        this.playerAnimation.update(delta);
        this.setWidth(0);
        this.setHeight(0);
    }
}
