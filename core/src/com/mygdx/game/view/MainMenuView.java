package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.LoginController;
import com.mygdx.game.controller.MainMenuController;


public class MainMenuView extends View<MainMenuController> {

    private static MainMenuView instance = null;

    private Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("bg.png"))));
    private ImageButton playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("play.png"))));
    private Button helpButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("help_button.png"))));
    private Button leaderboardButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("leaderboard.png"))));
    private Button logoutButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("logout.png"))));

    private MainMenuView() {

        this.background.setPosition(0, 0);
        this.background.setSize(getCamera().viewportWidth, getCamera().viewportHeight);
        this.addActor(this.background);

        this.playButton.setSize(64, 64);
        this.playButton.setPosition((float) (getCamera().viewportWidth*0.5-playButton.getWidth()/2), (float) (getCamera().viewportHeight*0.27));
        this.playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuController.getInstance().switchState(GameController.getInstance());
            }
        });
        this.addActor(this.playButton);

        this.helpButton.setSize(64, 64);
        this.helpButton.setPosition((float) (getCamera().viewportWidth*0.75-64/2), (float) (getCamera().viewportHeight*0.27));
        this.helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuController.getInstance().switchState(HelpController.getInstance());
            }
        });
        this.addActor(this.helpButton);

        this.leaderboardButton.setSize(64, 64);
        this.leaderboardButton.setPosition((float) (getCamera().viewportWidth*0.25-64/2), (float) (getCamera().viewportHeight*0.27));
        this.leaderboardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuController.getInstance().switchState(LeaderboardController.getInstance());
            }
        });
        this.addActor(this.leaderboardButton);

        this.logoutButton.setSize(64, 64);
        this.logoutButton.setPosition((float) (getCamera().viewportWidth*0.95), (float) (getCamera().viewportHeight*0.90));
        this.logoutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LoginController.getInstance().logOutUser();
                MainMenuController.getInstance().switchState(LoginController.getInstance());
            }
        });
        this.addActor(this.logoutButton);

    }

    public static final MainMenuView getInstance(){
        if (instance == null){
            instance = new MainMenuView();
        }
        return instance;
    }



}
