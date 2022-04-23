package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.view.game.Animation;
import java.util.ArrayList;

public abstract class StripaSurvivorActor extends Actor {

    private Animation animation;
    protected ArrayList<Animation> playerAnimations;

    public StripaSurvivorActor(int x, int y, int width, int height, int numberOfAnimations, ArrayList<Texture> textures){
        this.playerAnimations = new ArrayList<>();

        for (int i=0; i < numberOfAnimations; i++){
            this.animation = new Animation(textures.size()/numberOfAnimations, 0.1f, new ArrayList<>(textures.subList(i*textures.size()/numberOfAnimations, i*textures.size()/numberOfAnimations+textures.size()/numberOfAnimations)));
            this.animation.getSpriteFrame().setPosition((float)x, (float)y);
            this.animation.getSpriteFrame().setSize((float)width, (float)height);
            this.playerAnimations.add(this.animation);
        }

    }

    public ArrayList<Animation> getAnimations(){
        return this.playerAnimations;
    }

    public void setActorPosition(float x, float y) {
        for (Animation animation : this.playerAnimations){
            animation.setSpritePosition(x, y);
            animation.setSpriteSize((int) animation.getSpriteFrame().getWidth(), (int) animation.getSpriteFrame().getWidth());
        }
    }

    public Sprite getSprite() {
        return this.animation.getSpriteFrame();
    }


    public void draw(Batch batch, float parentAlpha) {
        this.playerAnimations.get(0).getSpriteFrame().draw(batch);
    }


    public void act(float delta) {
        this.playerAnimations.get(0).update(delta);
    }
}
