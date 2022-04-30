package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    private Image board = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("pausebg.png"))));
    private Image resume = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("resume.png"))));
    private Image quit = new Image(new TextureRegionDrawable(new Texture(Gdx.files.internal("quit.png"))));

    private PauseView(){

        this.board.setSize(this.getCamera().viewportWidth/2f, this.getCamera().viewportHeight/2);
        this.board.setPosition(0.25f*this.getCamera().viewportWidth, 0.25f*this.getCamera().viewportHeight);
        this.addActor(board);

        this.resume.setSize(this.getCamera().viewportWidth/7f, this.getCamera().viewportHeight/7f);
        this.resume.setPosition(0.5f*this.getCamera().viewportWidth-1.1f*resume.getWidth(), this.getCamera().viewportHeight/2- resume.getHeight());
        this.resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                bg.remove();
                ControllerManager.getInstance().pop();
            }
        });
        this.addActor(resume);

        this.quit.setSize(this.getCamera().viewportWidth/7f, this.getCamera().viewportHeight/7f);
        this.quit.setPosition(0.5f*this.getCamera().viewportWidth, this.getCamera().viewportHeight/2- quit.getHeight());
        this.quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                bg.remove();
                GameController.getInstance().endGame();
                PauseController.getInstance().switchState(MainMenuController.getInstance());
            }
        });
        this.addActor(quit);
    }

    public static final PauseView getInstance() {
        if (instance == null) {
            instance = new PauseView();
        }
        return instance;
    }
    public void setBackground(Image bg){
        this.bg = bg;
        this.bg.setPosition(0,0);
        this.addActor(this.bg);
        this.bg.toBack();
    }
}
