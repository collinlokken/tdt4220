package com.mygdx.game.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.PlayerModel;

public class PlayerActor extends Actor {
    private static PlayerActor instance = null;
    private Sprite sprite;

    private PlayerActor(Texture texture){
        super();
        sprite = new Sprite(texture);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public static final PlayerActor getInstance(Texture texture){
        if (instance == null){
            instance = new PlayerActor(texture);
        }
        return instance;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setHeight(0);
        this.setWidth(0);
    }

    public Sprite getSprite(){
        return sprite;
    }

}
