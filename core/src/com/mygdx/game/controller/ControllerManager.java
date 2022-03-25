package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class ControllerManager {
    private Stack<Controller> controllers;

    private static ControllerManager instance = null;

    private ControllerManager(){
        controllers = new Stack<Controller>();
    }

    public static final ControllerManager getInstance(){
        if (instance == null){
            instance = new ControllerManager();
        }
        return instance;
    }

    public void push(Controller state){
        controllers.push(state);
        Gdx.input.setInputProcessor(state.getView());
    }

    public void pop(){
        controllers.pop();
    }

    public void set(Controller state){
        controllers.pop();
        controllers.push(state);
        Gdx.input.setInputProcessor(state.getView());
    }

    public Controller getCurrent()
    {
        return this.controllers.peek();
    }

    public void update(float dt){
        controllers.peek().update(dt);
    }

}
