package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.view.game.EntityActor;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoronaVirusActor extends EntityActor<CoronaVirus>
{

    private  static  final Texture virusTexture = new Texture(Gdx.files.internal("virus.png"));

    private Sprite sprite;

    @Override
    protected void initialize()
    {
        this.sprite = new Sprite(virusTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    public CoronaVirusActor(CoronaVirus entity)
    {
        super(entity);
    }

    @Override
    protected void update(float dt)
    {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
