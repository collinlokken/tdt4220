package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.view.game.actors.Animation;
import java.util.ArrayList;
import java.util.Collection;

public abstract class EntityActor<T extends Entity> extends Actor {
    protected T entity;

    private boolean initialized = false;

    protected Collection<Animation> animations = new ArrayList<>();
    protected Collection<Sprite> sprites = new ArrayList<>();


    protected abstract void initialize();



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

    public EntityActor(T entity)
    {
        this.entity= entity;
        this.setTouchable(Touchable.disabled);

    }



    public T getEntity() {
        return this.entity;
    }



    protected void renderAnimation(Animation animation)
    {
        this.animations.add(animation);
    }

    protected void removeAnimation(Animation animation)
    {
        this.animations.remove(animation);
    }

    protected void renderSprite(Sprite sprite)
    {
        this.sprites.add(sprite);
    }

    protected void removeSprite(Sprite sprite)
    {
        this.sprites.remove(sprite);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
       for(Sprite sprite : this.sprites) {
           sprite.draw(batch);
       }
       for(Animation animation : this.animations)
       {
           animation.getSpriteFrame().draw(batch);
       }
    }

    @Override
    public void act(float dt)
    {
        if(!this.initialized)
        {
            this.initialize();
            this.initialized = true;
        }
        this.update(dt);

    }

    protected abstract  void update(float dt);


}
