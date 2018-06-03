package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.compression.lzma.Base;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public abstract class BaseLevel extends GameObject {

    public enum LevelState
    {
        Tutorial,
        InGame,
        End
    }

    private ArrayList<GameObject> levelObjects;
    private BitmapFont time;
    protected WorldController worldController;
    private BaseButton nextLevelButton;
    private BaseButton startLevel;


    protected BaseButton menuButton;
    protected LevelState state;
    protected boolean nextLevel = false;
    protected BitmapFont textNextLevel;
    protected String finishLevelText;
    protected float elapsedTime = 0;
    protected boolean win;

    public void baseInit(WorldController worldController)
    {
        this.worldController = worldController;
        win = false;
        menuButton = new BaseButton(Assets.getInstance().button, "Menu",worldController,
                new Vector2(-Constants.WIDTH_RATIO*4.5f, Constants.HEIGHT_RATIO*4+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.MainMenu;
                worldController.InitiateLevel();
            }
        };

        startLevel = new BaseButton(Assets.getInstance().button, "Start",worldController,
                new Vector2(-Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO*-3+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                state = LevelState.InGame;
            }
        };

        nextLevelButton = new BaseButton(Assets.getInstance().button, "Next",worldController,
                new Vector2(-Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO*-3+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                nextLevel = false;
                worldController.finishLevel(win);
            }
        };
        textNextLevel = new BitmapFont();
        time = new BitmapFont();
        state = LevelState.Tutorial;
        init();
    }

    @Override
    public void render(SpriteBatch batch) {
        drawHeader(batch);
        switch (state)
        {
            case Tutorial:
                batch.draw(Assets.getInstance().button, Constants.WIDTH_RATIO*(-3.8f), Constants.HEIGHT_RATIO*(-3.8f), Constants.WIDTH_RATIO*7.5f, Constants.HEIGHT_RATIO*7.5f);
                startLevel.render(batch);
                tutorial(batch);
                break;
            case End:
                //Cuadro de texto
                batch.draw(Assets.getInstance().button, Constants.WIDTH_RATIO*(-3.8f), Constants.HEIGHT_RATIO*(-3.8f), Constants.WIDTH_RATIO*7.5f, Constants.HEIGHT_RATIO*7.5f);
                nextLevelButton.render(batch);
                changeLevel(batch);
                textNextLevel.draw(batch, finishLevelText, Constants.WIDTH_RATIO*(-3.6f), Constants.HEIGHT_RATIO*(3f));
                break;
        }
    }

    @Override
    public void update(float elpasedTime) {
        switch (state)
        {
            case Tutorial:
                startLevel.update(elpasedTime);
                break;
            case InGame:
                menuButton.update(elpasedTime);
                LevelUpdate(elpasedTime);
                checkWasted();
                checkVictory();
                break;
            case End:
                nextLevelButton.update(elpasedTime);
                break;
        }
    }

    public abstract void changeLevel(SpriteBatch batch);

    public abstract void tutorial(SpriteBatch batch);

    public abstract void GUI(SpriteBatch batch);

    public abstract void LevelUpdate(float elapsedTime);

    public void arcadeButtonControllers(int buttonIndex)
    {
        if(buttonIndex == 9)
        {
            menuButton.buttonFuction();
        }
    }

    public abstract void init();

    public void drawHeader(SpriteBatch batch)
    {
        GUI(batch);
        //Cabecera
        batch.draw(Assets.getInstance().header, Constants.dimension(-5,4).x,Constants.dimension(-5,4).y, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO);
        batch.draw(Assets.getInstance().wastedBar[0], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f, Constants.HEIGHT_RATIO*0.75f);
        batch.draw(Assets.getInstance().wastedBar[1], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f*(100-worldController.currentScore)/100, Constants.HEIGHT_RATIO*0.75f);
        menuButton.render(batch);
        time.getData().setScale(Gdx.graphics.getWidth()*0.0015f);
        time.draw(batch, "Time: "+((int)elapsedTime), Constants.WIDTH_RATIO*(3.9f), Constants.dimension(0, 4.6f).y);
    }

    public void checkWasted()
    {
        if(worldController.currentScore >= 100)
        {
            state = LevelState.End;
            win = false;
        }
    }

    void checkVictory()
    {
        if(elapsedTime >= Constants.LEVEL_TIME)
        {
            state = LevelState.End;
            win = true;
        }
    }
}
