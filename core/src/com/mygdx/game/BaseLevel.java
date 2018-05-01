package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public abstract class BaseLevel extends GameObject {

    private ArrayList<GameObject> levelObjects;
    protected WorldController worldController;
    private BaseButton menuButton;

    public void baseInit(WorldController worldController)
    {
        this.worldController = worldController;
        menuButton = new BaseButton(Assets.getInstance().button, "Menu",worldController,
                new Vector2(-Constants.WIDTH_RATIO*4, Constants.HEIGHT_RATIO*4+Constants.HEIGHT_RATIO/4),new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO/2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.MainMenu;
                worldController.InitiateLevel();
            }
        };
        init();
    }

    @Override
    public void render(SpriteBatch batch) {
        GUI(batch);
        //Cabecera
        batch.draw(Assets.getInstance().button, Constants.dimension(-5,4).x,Constants.dimension(-5,4).y, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO);
        menuButton.render(batch);
    }

    @Override
    public void update(float elpasedTime) {
        menuButton.update(elpasedTime);
        LevelUpdate(elpasedTime);
    }

    public abstract void GUI(SpriteBatch batch);

    public abstract void LevelUpdate(float elapsedTime);

    public abstract void init();
}
