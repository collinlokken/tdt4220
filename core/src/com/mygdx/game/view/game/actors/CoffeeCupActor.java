package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.CoffeeCup;
import com.mygdx.game.view.game.EntityActor;

public class CoffeeCupActor extends EntityActor<CoffeeCup> {

    private  static  final Texture coffeeTexture = new Texture(Gdx.files.internal("coffee.png"));
    private Sprite sprite;

    public CoffeeCupActor(CoffeeCup entity) {
        super(entity);
    }

    @Override
    protected void initialize() {
        this.sprite = new Sprite(coffeeTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    @Override
    protected void update(float dt) {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
