package com.mygdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class View<T extends Controller> {
    protected OrthographicCamera cam;
    protected Stage stage;

    protected  T controller;

    protected View(){
        this.stage = new Stage(new ScreenViewport(), StripaSurvivor.getSpriteBatchInstance());
    }

    public void setController(T controller){
        this.controller = controller;
    }

    public void render(float dt){
        this.stage.act(dt);
    }
    public void dispose(){
        this.stage.dispose();
    }

}
