package com.mygdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.Controller;

public abstract class View<T extends Controller> {
    protected OrthographicCamera cam;

    protected  T controller;

    protected View(){
        cam = new OrthographicCamera();
    }

    public void setController(T controller){
        this.controller = controller;
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
