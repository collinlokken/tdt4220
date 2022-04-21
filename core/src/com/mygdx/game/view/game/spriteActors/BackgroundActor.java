package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.view.mainMenu.Background;

public class BackgroundActor extends Actor {

    private float speed;
    private Sprite background1;
    private Sprite background2;

    public BackgroundActor(float speed){
        this.speed = speed;
        Texture texture1 = new Texture(Gdx.files.internal("background1.png"));
        background1 = new Sprite(texture1);
        background1.setPosition(0,0);
        background1.setSize(texture1.getWidth()*Gdx.graphics.getHeight()/texture1.getHeight(), Gdx.graphics.getHeight());

        Texture texture2 = new Texture(Gdx.files.internal("background2.png"));
        background2 = new Sprite(texture2);
        background2.setPosition(background1.getWidth(),0);
        background2.setSize(texture2.getWidth()*Gdx.graphics.getHeight()/texture2.getHeight(), Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        background1.draw(batch);
        background2.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        background1.setX(background1.getX()-delta*speed);
        background2.setX(background2.getX()-delta*speed);

        if (background1.getWidth()+background1.getX() < 0){
            background1.setX(background2.getX()+background2.getWidth());
        }
        if (background2.getWidth()+background2.getX() < 0){
            background2.setX(background1.getX()+background1.getWidth());
        }

        speed += 5*delta;
    }
}
