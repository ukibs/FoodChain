package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class MainMenu extends GameObject {
    WorldController worldController;
    Texture background;
    BaseButton play;
    BaseButton practice;
    BaseButton backMenu;
    BaseButton harvest;
    BaseButton transport;
    BaseButton restaurant;
    BaseButton supermarket;

    boolean inPractice = false;

    MainMenu(WorldController worldController)
    {
        this.worldController = worldController;
        play = new BaseButton(Assets.getInstance().button, "Play", worldController,
                new Vector2(-Constants.WIDTH_RATIO*4, Constants.HEIGHT_RATIO), new Vector2(Constants.WIDTH_RATIO*3,
                Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        practice = new BaseButton(Assets.getInstance().button, "Practice", worldController,
                new Vector2(-Constants.WIDTH_RATIO*4, -Constants.HEIGHT_RATIO*2), new Vector2(Constants.WIDTH_RATIO*3,
                Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                inPractice = true;
            }
        };

        harvest = new BaseButton(Assets.getInstance().button, "Harvest", worldController,
                new Vector2(-Constants.WIDTH_RATIO*4, Constants.HEIGHT_RATIO), new Vector2(Constants.WIDTH_RATIO*3,
                Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };


        supermarket = new BaseButton(Assets.getInstance().button, "Super", worldController,
                new Vector2(Constants.WIDTH_RATIO, -Constants.HEIGHT_RATIO*2), new Vector2(Constants.WIDTH_RATIO*3,
                Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Supermarket;
                worldController.InitiateLevel();
            }
        };

    }

    public void init()
    {
        inPractice = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().button, -Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(!inPractice)
        {
            play.render(batch);
            practice.render(batch);
        }
        else {
            harvest.render(batch);
            supermarket.render(batch);
        }
    }

    @Override
    public void update(float elpasedTime) {
        if(!inPractice)
        {
            play.update(elpasedTime);
            practice.update(elpasedTime);
        }
        else {
            harvest.update(elpasedTime);
            supermarket.update(elpasedTime);
        }
    }
}
