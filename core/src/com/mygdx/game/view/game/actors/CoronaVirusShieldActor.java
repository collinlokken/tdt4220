package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.CoronaVirusShield;
import com.mygdx.game.view.game.EntityActor;

public class CoronaVirusShieldActor extends EntityActor<CoronaVirusShield> {

    private  static  final Texture facemaskTexture = new Texture(Gdx.files.internal("facemask.png"));
    private Sprite sprite;

    public CoronaVirusShieldActor(CoronaVirusShield entity) {
        super(entity);
    }

    @Override
    protected void initialize() {
        this.sprite = new Sprite(facemaskTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    @Override
    protected void update(float dt) {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
