package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.LifePointItem;
import com.mygdx.game.view.game.EntityActor;

public class LifePointItemActor extends EntityActor<LifePointItem>
{


    private  static  final Texture lifePointTexture = new Texture(Gdx.files.internal("heart.png"));

    private Sprite sprite;

    @Override
    protected void initialize()
    {
        this.sprite = new Sprite(lifePointTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    public LifePointItemActor(LifePointItem entity)
    {
        super(entity);
    }

    @Override
    protected void update(float dt)
    {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
