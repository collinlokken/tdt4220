package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.CoronaVirusShieldComponent;
import com.mygdx.game.model.game.component.HealthComponent;
import com.mygdx.game.model.game.component.StandShieldComponent;

public class ProtectionAgainstActor extends Actor
{
    private Game game;
    private  static  final Sprite coffeeSprite = new Sprite(new Texture(Gdx.files.internal("stand.png")));
    private  static  final Sprite maskSprite = new Sprite(new Texture(Gdx.files.internal("virus.png")));

    private int screenHeight;
    private  int screenWidth;
    public  ProtectionAgainstActor(int screenWidth, int screenHeight,Game game)
    {
        this.game = game;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        coffeeSprite.setSize((1/8f)*screenHeight, (1/8f)*screenHeight);
        coffeeSprite.setPosition((13f/16f)*this.screenWidth, this.screenHeight - ProtectionAgainstActor.coffeeSprite.getHeight());
        maskSprite.setSize((1/8f)*screenHeight, (1/8f)*screenHeight);
        maskSprite.setPosition((float)(coffeeSprite.getX()+coffeeSprite.getWidth()), this.screenHeight - ProtectionAgainstActor.maskSprite.getHeight());


    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(this.game.getPlayerEntity() == null)
            return;

        if(this.game.getPlayerEntity().hasComponentOfType(CoronaVirusShieldComponent.class))
            ProtectionAgainstActor.maskSprite.draw(batch);

        if(this.game.getPlayerEntity().hasComponentOfType(StandShieldComponent.class))
            ProtectionAgainstActor.coffeeSprite.draw(batch);

    }

}
