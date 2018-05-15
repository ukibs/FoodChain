package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public abstract class BaseLevel extends GameObject {

    private ArrayList<GameObject> levelObjects;
    private BitmapFont time;
    protected WorldController worldController;
    private BaseButton menuButton;
    private BaseButton nextLevelButton;
    protected boolean nextLevel = false;
    protected BitmapFont textNextLevel;
    protected String finishLevelText;
    protected float elapsedTime = 0;

    public void baseInit(WorldController worldController)
    {
        this.worldController = worldController;
        menuButton = new BaseButton(Assets.getInstance().button, "Menu",worldController,
                new Vector2(-Constants.WIDTH_RATIO*4.5f, Constants.HEIGHT_RATIO*4+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.MainMenu;
                worldController.InitiateLevel();
            }
        };
        nextLevelButton = new BaseButton(Assets.getInstance().button, "Next",worldController,
                new Vector2(-Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO*-3+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                nextLevel = false;
                worldController.finishLevel();
            }
        };
        textNextLevel = new BitmapFont();
        time = new BitmapFont();
        init();
    }

    @Override
    public void render(SpriteBatch batch) {
        GUI(batch);
        //Cabecera
        batch.draw(Assets.getInstance().header, Constants.dimension(-5,4).x,Constants.dimension(-5,4).y, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO);
        batch.draw(Assets.getInstance().wastedBar[0], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f, Constants.HEIGHT_RATIO*0.75f);
        batch.draw(Assets.getInstance().wastedBar[1], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f*worldController.currentScore/100, Constants.HEIGHT_RATIO*0.75f);
        menuButton.render(batch);
        time.getData().setScale(Gdx.graphics.getWidth()*0.0015f);
        time.draw(batch, "Time: "+((int)elapsedTime), Constants.WIDTH_RATIO*(3.9f), Constants.dimension(0, 4.6f).y);

        if(nextLevel)
        {
            //Cuadro de texto
            batch.draw(Assets.getInstance().button, Constants.WIDTH_RATIO*(-3.8f), Constants.HEIGHT_RATIO*(-3.8f), Constants.WIDTH_RATIO*7.5f, Constants.HEIGHT_RATIO*7.5f);
            nextLevelButton.render(batch);
            changeLevel(batch);
            textNextLevel.draw(batch, finishLevelText, Constants.WIDTH_RATIO*(-3.6f), Constants.HEIGHT_RATIO*(3f));
        }
    }

    @Override
    public void update(float elpasedTime) {
        if(!nextLevel)
        {
            menuButton.update(elpasedTime);
            LevelUpdate(elpasedTime);
        }
        else nextLevelButton.update(elpasedTime);
    }

    public abstract void changeLevel(SpriteBatch batch);

    public abstract void GUI(SpriteBatch batch);

    public abstract void LevelUpdate(float elapsedTime);

    public abstract void init();
}
