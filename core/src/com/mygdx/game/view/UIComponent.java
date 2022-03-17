package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;

public class UIComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    private Texture activeTexture;
    private boolean visible;

    public UIComponent(int x, int y, int width, int height, Texture activeTexture, boolean visible){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.activeTexture = activeTexture;
        this.visible = visible;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setActiveTexture(Texture texture){
        this.activeTexture = texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Texture getActiveTexture() {
        return activeTexture;
    }

    public void show(){
        this.visible = true;
    }

    public void hide(){
        this.visible = false;
    }

    public void dispose(){
        this.activeTexture.dispose();
    }

    public boolean isVisible(){
        return this.visible;
    }

}
