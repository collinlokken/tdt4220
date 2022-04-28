package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HealthComponent;

public class HealthbarActor extends Actor
{
    private Game game;
    private  static  final Sprite heartSprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));

    private int screenHeight;
    public  HealthbarActor(int screenHeight, Game game)
    {
        this.game = game;
        this.screenHeight = screenHeight;
        heartSprite.setSize((1/8f)*screenHeight, (1/8f)*screenHeight);
        System.out.println(heartSprite.getWidth());

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        int healthPoints;
        if(this.game.getPlayerEntity() == null)
            healthPoints = 0;
        else
            healthPoints = this.game.getPlayerEntity().getComponent(HealthComponent.class).getValue();
        for(int i = 0; i < healthPoints; i++)
        {
            heartSprite.setPosition(i*heartSprite.getWidth() + 0.1f*this.getWidth(), this.screenHeight - heartSprite.getHeight());
            System.out.println(heartSprite.getX() + " , " + heartSprite.getY());
            heartSprite.draw(batch);
        }
    }

}
