package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
    protected BitmapFont textFont;
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

        startLevel = new BaseButton(Assets.getInstance().button, "Start", worldController, Constants.dimension(-2, -4.5f),Constants.dimension(4, 1)) {
            @Override
            public void buttonFuction() {
                state = LevelState.InGame;
            }
        };

        nextLevelButton = new BaseButton(Assets.getInstance().button, "Next", worldController, Constants.dimension(-2, -4.5f),Constants.dimension(4, 1)) {
            @Override
            public void buttonFuction() {
                nextLevel = false;
                worldController.finishLevel(win);
            }
        };
        time = new BitmapFont();
        state = LevelState.Tutorial;
        elapsedTime = 0;
        init();
    }

    @Override
    public void render(SpriteBatch batch) {
        drawHeader(batch);
        switch (state)
        {
            case Tutorial:
                batch.draw(Assets.getInstance().button, Constants.WIDTH_RATIO*(-3.8f), Constants.HEIGHT_RATIO*(-4.7f), Constants.WIDTH_RATIO*7.5f, Constants.HEIGHT_RATIO*8.5f);
                Tutorials.render(batch, worldController.gameMode);
                startLevel.render(batch);
                break;
            case End:
                //Cuadro de texto
                batch.draw(Assets.getInstance().button, Constants.WIDTH_RATIO*(-3.8f), Constants.HEIGHT_RATIO*(-4.7f), Constants.WIDTH_RATIO*7.5f, Constants.HEIGHT_RATIO*8.5f);
                ChangeLevel.render(batch, worldController.gameMode, win);
                nextLevelButton.render(batch);
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

    public void baseButtonControllers(int buttonIndex)
    {
        switch (state) {
            case Tutorial:
                if(buttonIndex == 0)
                {
                    startLevel.buttonFuction();
                }
                break;
            case InGame:
                if(buttonIndex == 6)
                {
                    menuButton.buttonFuction();
                }
                break;
            case End:
                if(buttonIndex == 0)
                {
                    nextLevelButton.buttonFuction();
                }
                break;
        }
        arcadeButtonControllers(buttonIndex);
    }

    public void drawHeader(SpriteBatch batch)
    {
        GUI(batch);
        //Cabecera
        batch.draw(Assets.getInstance().header, Constants.dimension(-5,4).x,Constants.dimension(-5,4).y, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO);
        batch.draw(Assets.getInstance().wastedBar[0], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f, Constants.HEIGHT_RATIO*0.75f);
        batch.draw(Assets.getInstance().wastedBar[1], Constants.WIDTH_RATIO*(-2), Constants.dimension(0, 4.1f).y, Constants.WIDTH_RATIO*5.5f*(worldController.maxScore()-worldController.currentScore)/worldController.maxScore(), Constants.HEIGHT_RATIO*0.75f);
        menuButton.render(batch);
        time.getData().setScale(Gdx.graphics.getWidth()*0.0015f);
        time.draw(batch, "Time: "+((int)elapsedTime), Constants.WIDTH_RATIO*(3.9f), Constants.dimension(0, 4.6f).y);
    }

    public void checkWasted()
    {
        if(worldController.currentScore <= 0)
        {
            state = LevelState.End;
            win = false;
            SoundManager.getInstance().play(SoundManager.levelLose);
        }
    }

    void checkVictory()
    {
        if(elapsedTime >= Constants.LEVEL_TIME)
        {
            state = LevelState.End;
            win = true;
            SoundManager.getInstance().play(SoundManager.levelComplete);
        }
    }

    public abstract void GUI(SpriteBatch batch);

    public abstract void LevelUpdate(float elapsedTime);

    public abstract void arcadeButtonControllers(int buttonIndex);

    public abstract void arcadeAxis(char axis, int value);

    public abstract void init();
}
