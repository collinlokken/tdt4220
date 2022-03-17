package com.mygdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class View<T extends Controller> {
    protected OrthographicCamera cam;

    protected List<UIComponent> UIcomponents = new ArrayList<UIComponent>();

    protected  T controller;

    protected View(){
        cam = new OrthographicCamera();
    }

    public void setController(T controller){
        this.controller = controller;
    }

    protected void addComponents(UIComponent ...components){
        for (UIComponent comp : components){
            this.UIcomponents.add(comp);
        }
    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public void render(SpriteBatch sb){
        sb.begin();
        for (UIComponent component : UIcomponents) {
            if (component.isVisible()){
                sb.draw(component.getActiveTexture(), component.getX(), component.getY(), component.getWidth(), component.getHeight());
            }
        }
        sb.end();
    }
    public void dispose(){
        for (UIComponent component : UIcomponents) {
            component.dispose();
        }
    }

}
