package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.view.game.Animation;
import java.util.ArrayList;

public abstract class GameActor<T extends Entity> extends Actor {

    private Animation animation;
    protected ArrayList<Animation> playerAnimations;

    protected Batch batch;

    private boolean initialized = false;

    protected T entity;



    protected PositionComponent getPosition()
    {
        PositionComponent position = this.entity.getComponent(PositionComponent.class);
        if(position == null)
            throw new IllegalStateException("This actor does not have a position");
        return position;
    }

    protected HitboxComponent getHitbox()
    {
        HitboxComponent hitbox = this.entity.getComponent(HitboxComponent.class);
        if(hitbox == null)
            throw new IllegalStateException("This actor does not have a hitbox");
        return  hitbox;
    }

    @Override
    public float getX()
    {
        return (float)((this.getPosition().getX() / this.entity.getGame().getWidth())*this.getStage().getCamera().viewportWidth);
    }


    @Override
    public float getY()
    {
        return (float)((this.getPosition().getY() / this.entity.getGame().getHeight())*this.getStage().getCamera().viewportHeight);
    }

    @Override
    public float getWidth()
    {
        return  (float)((this.getHitbox().getWidth() / this.entity.getGame().getWidth())*this.getStage().getCamera().viewportWidth);
    }

    @Override
    public float getHeight()
    {
        return  (float)((this.getHitbox().getHeight() / this.entity.getGame().getHeight())*this.getStage().getCamera().viewportHeight);
    }

    public GameActor(int numberOfAnimations, ArrayList<Texture> textures, T entity){
        this.playerAnimations = new ArrayList<>();
        this.entity= entity;
        for (int i=0; i < numberOfAnimations; i++){
            this.animation = new Animation(textures.size()/numberOfAnimations, 0.3f, new ArrayList<>(textures.subList(i*textures.size()/numberOfAnimations, i*textures.size()/numberOfAnimations+textures.size()/numberOfAnimations)));
            this.playerAnimations.add(this.animation);
        }

    }

    public T getEntity() {
        return this.entity;
    }

    public ArrayList<Animation> getAnimations(){
        return this.playerAnimations;
    }


    protected Sprite sprite;
    protected void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.draw(batch);
    }
}
