package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.Controller;

public abstract class View<T extends Controller> extends Stage {

    protected T controller;
    protected Music backGroundMusic;

    protected View() {
        super(new ScreenViewport());
    }

    public void setController(T controller) {
        this.controller = controller;
    }

    protected void setBackGroundMusic(Music music){
        this.backGroundMusic = music;
        this.backGroundMusic.setLooping(true);
        this.backGroundMusic.setVolume(1f);
    }

    public void playMusic(){
        this.backGroundMusic.play();
    }

    public void stopMusic(){
        this.backGroundMusic.stop();
    }

}
