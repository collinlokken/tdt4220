package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.Stand;
import com.mygdx.game.view.game.EntityActor;

public class StandActor extends EntityActor<Stand>
{
    private  static  final Texture standTexture = new Texture(Gdx.files.internal("stand.png"));

    private Sprite sprite;

    public  StandActor(Stand entity){
        super(entity);
    }
    @Override
    protected void initialize()
    {
        this.sprite = new Sprite(standTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    @Override
    protected void update(float dt) {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
