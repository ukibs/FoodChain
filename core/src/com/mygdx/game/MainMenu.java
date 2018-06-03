package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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

    //ArrayList<String> scoreTable;
    BitmapFont text;

    MainMenu(WorldController worldController)
    {
        this.worldController = worldController;
        play = new BaseButton(Assets.getInstance().button, "Play", worldController, Constants.dimension(-4, 1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        practice = new BaseButton(Assets.getInstance().button, "Practice", worldController, Constants.dimension(-4, -2), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.inPractice = true;
            }
        };

        harvest = new BaseButton(Assets.getInstance().button, "Harvest", worldController, Constants.dimension(-4, 1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        transport = new BaseButton(Assets.getInstance().button, "Transport", worldController, Constants.dimension(1,1), Constants.dimension(3, 2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Transport;
                worldController.InitiateLevel();
            }
        };

        restaurant = new BaseButton(Assets.getInstance().button, "Restaurant", worldController, Constants.dimension(-4, -2), Constants.dimension(3,2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Restaurant;
                worldController.InitiateLevel();
            }
        };

        supermarket = new BaseButton(Assets.getInstance().button, "Super", worldController, Constants.dimension(1, -2), Constants.dimension(3,2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Supermarket;
                worldController.InitiateLevel();
            }
        };

        backMenu = new BaseButton(Assets.getInstance().button, "Menu", worldController, Constants.dimension(-4.5f, -4), Constants.dimension(1.5f, 1)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.MainMenu;
                worldController.InitiateLevel();
            }
        };
        /*scoreTable = new ArrayList<String>();
        scoreTable.add("2");
        scoreTable.add("5");
        scoreTable.add("7");
        scoreTable.add("9");
        scoreTable.add("100");*/
        text = new BitmapFont();
    }

    public void init()
    {
        worldController.inPractice = false;
        worldController.currentScore = 100;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().header, -Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(!worldController.inPractice)
        {
            play.render(batch);
            practice.render(batch);
            text.draw(batch, "Ranking:", Constants.WIDTH_RATIO *1f, Constants.HEIGHT_RATIO*3);
            for(int i = 0; i < 5; i++)
            {
                text.draw(batch,
                        worldController.scoreNames.get(i) + " - " + worldController.scores[i],
                        Constants.WIDTH_RATIO * 0.5f, Constants.HEIGHT_RATIO * (2f - i * 1f));
            }
        }
        else {
            harvest.render(batch);
            transport.render(batch);
            restaurant.render(batch);
            supermarket.render(batch);
            backMenu.render(batch);
        }
    }

    @Override
    public void update(float elpasedTime) {
        if(!worldController.inPractice)
        {
            play.update(elpasedTime);
            practice.update(elpasedTime);
        }
        else {
            harvest.update(elpasedTime);
            transport.update(elpasedTime);
            restaurant.update(elpasedTime);
            supermarket.update(elpasedTime);
            backMenu.update(elpasedTime);
        }
    }
}
