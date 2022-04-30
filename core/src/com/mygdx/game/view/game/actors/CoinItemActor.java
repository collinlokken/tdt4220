package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.entity.CoinItem;
import com.mygdx.game.view.game.EntityActor;

public class CoinItemActor extends EntityActor<CoinItem> {

    private  static  final Texture coinTexture = new Texture(Gdx.files.internal("dogecoin.png"));
    private Sprite sprite;

    public CoinItemActor(CoinItem entity) {
        super(entity);
    }

    @Override
    protected void initialize() {
        this.sprite = new Sprite(coinTexture);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.renderSprite(this.sprite);
    }

    @Override
    protected void update(float dt) {
        this.sprite.setPosition(this.getX(), this.getY());
    }
}
