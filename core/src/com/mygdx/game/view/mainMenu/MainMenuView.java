package com.mygdx.game.view.mainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.view.View;

public class MainMenuView extends View<MainMenuController> {

    private static MainMenuView instance = null;

    private MainMenuView() {
        super();
        // BACKGROUND OF MAIN MENU
        Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("bg.png"))));
        background.setPosition(0, 0);
        background.setSize(StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT);
        this.addActor(background);

        // PLAYBUTTON
        Button playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("play.png"))));
        playButton.setSize(64, 64);
        playButton.setPosition((float) (StripaSurvivor.WIDTH*0.5-64/2), (float) (StripaSurvivor.HEIGHT*0.25));
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("play");
            }
        });
        this.addActor(playButton);

        // HELP BUTTON
        Button helpButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("help_button.png"))));
        helpButton.setSize(64, 64);
        helpButton.setPosition((float) (StripaSurvivor.WIDTH*0.75-64/2), (float) (StripaSurvivor.HEIGHT*0.25));
        helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("help");
            }
        });
        this.addActor(helpButton);

        // LEADERBOARD BUTTON
        Button leaderboardButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("leaderboard.png"))));
        leaderboardButton.setSize(64, 64);
        leaderboardButton.setPosition((float) (StripaSurvivor.WIDTH*0.25-64/2), (float) (StripaSurvivor.HEIGHT*0.25));
        leaderboardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("leaderboard");
            }
        });
        this.addActor(leaderboardButton);

        // LOGOUT BUTTON
        Button logoutButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("logout.png"))));
        logoutButton.setSize(64, 64);
        logoutButton.setPosition((float) (StripaSurvivor.WIDTH-67), (float) (StripaSurvivor.HEIGHT-73));
        logoutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("logout");
            }
        });
        this.addActor(logoutButton);

        // MUSIC
        Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        //music.play();
    }

    public static final MainMenuView getInstance(){
        if (instance == null){
            return new MainMenuView();
        }
        return instance;
    }



}
