package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.PlayerModel;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

public class PlayerActor extends StripaSurvivorActor {
    private static PlayerActor instance = null;

    private PlayerActor(int x, int y, int width, int height, Texture... textures){
        super(x, y, width, height, textures);
    }

    public static final PlayerActor getInstance(int x, int y, int width, int height, Texture... textures){
        if (instance == null){
            instance = new PlayerActor(x, y, width, height, textures);
        }
        return instance;
    }
/*
    @Override
    public void setActorPosition(float x, float y) {
        System.out.println(x);
        this.playerAnimation.setSpritePosition(x, y);
        this.playerAnimation.setSpriteSize((int)this.playerAnimation.getSpriteFrame().getWidth(), (int)this.playerAnimation.getSpriteFrame().getWidth());
    }

    @Override
    public Sprite getSprite() {
        return this.playerAnimation.getSpriteFrame();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.playerAnimation.getSpriteFrame().draw(batch);

    }

    @Override
    public void act(float delta) {
        this.playerAnimation.update(delta);
        this.setWidth(0);
        this.setHeight(0);
    }*/

}
