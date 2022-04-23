package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.PauseController;


public class PauseView extends View<PauseController>{
    private static PauseView instance = null;
    private Image bg;

    private PauseView(){
        super();
        //BACKGROUND IMAGE
        Image board = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("pausebg.png"))));
        board.setSize(this.getCamera().viewportWidth/2f, this.getCamera().viewportHeight/2);
        board.setPosition(0.25f*this.getCamera().viewportWidth, 0.25f*this.getCamera().viewportHeight);
        this.addActor(board);

        //RESUME BUTTON
        Image resume = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("resume.png"))));
        resume.setSize(this.getCamera().viewportWidth/7f, this.getCamera().viewportHeight/7f);
        resume.setPosition(0.5f*this.getCamera().viewportWidth-1.1f*resume.getWidth(), this.getCamera().viewportHeight/2- resume.getHeight());
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                bg.remove();
                ControllerManager.getInstance().pop();
            }
        });
        this.addActor(resume);

        //QUIT BUTTON
        Image quit = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("quit.png"))));
        quit.setSize(this.getCamera().viewportWidth/7f, this.getCamera().viewportHeight/7f);
        quit.setPosition(0.5f*this.getCamera().viewportWidth, this.getCamera().viewportHeight/2- quit.getHeight());
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event, x, y);
                bg.remove();
                GameController.getInstance().reset();
                ControllerManager.getInstance().pop();
                ControllerManager.getInstance().set(MainMenuController.getInstance());
            }
        });
        this.addActor(quit);
    }

    public static final PauseView getInstance() {
        System.out.println("getInstance");
        if (instance == null) {
            instance = new PauseView();
        }
        return instance;
    }
    public void setBackground(Image bg){
        this.bg = bg;
        bg.setPosition(0,0);
        this.addActor(bg);
        bg.toBack();
        System.out.println("SetBG");
    }
    }
