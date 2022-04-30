package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.mygdx.game.view.game.GameView;
//import com.mygdx.game.view.mainMenu.Background;


public class BackgroundActor extends Actor {
    private Sprite background1;
    private Sprite background2;
    private float speed;

    public BackgroundActor(float screenHeight, float speed){

        this.speed = speed;

        Texture texture1 = new Texture(Gdx.files.internal("background1.png"));
        background1 = new Sprite(texture1);
        background1.setPosition(0,0);
        background1.setSize(texture1.getWidth()*screenHeight/texture1.getHeight(), screenHeight);

        Texture texture2 = new Texture(Gdx.files.internal("background2.png"));
        background2 = new Sprite(texture2);
        background2.setPosition(background1.getWidth(),0);
        background2.setSize(texture2.getWidth()*screenHeight/texture2.getHeight(), screenHeight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        background1.draw(batch);
        background2.draw(batch);
    }

    private float time = 0;

    @Override
    public void act(float delta) {

        this.time += delta;

        //TODO: Fikse matten her sånn at fartsøkningen til entities i ECS-verden matcher fartsøkningen til bakgrunnen

        background1.setX((float)(background1.getX() - this.speed-Math.pow(1, -this.time)));
        background2.setX((float)(background2.getX() - this.speed-Math.pow(1, -this.time)));

        if (background1.getWidth() + background1.getX() < 0){
            background1.setX(background2.getX() + background2.getWidth());
        }
        if (background2.getWidth() + background2.getX() < 0){
            background2.setX(background1.getX() + background1.getWidth());
        }


    }
}
