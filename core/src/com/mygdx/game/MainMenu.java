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
    BaseButton supermarket;

    MainMenu(WorldController worldController)
    {
        this.worldController = worldController;
        play = new BaseButton(Assets.getInstance().button, "Play", worldController, new Vector2(-Constants.WIDTH_RATIO*4, Constants.HEIGHT_RATIO), new Vector2(Constants.WIDTH_RATIO*3, Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Harvest;
                worldController.InitiateLevel();
            }
        };

        supermarket = new BaseButton(Assets.getInstance().button, "Super", worldController, new Vector2(-Constants.WIDTH_RATIO*4, -Constants.HEIGHT_RATIO*2), new Vector2(Constants.WIDTH_RATIO*3, Constants.HEIGHT_RATIO*2)) {
            @Override
            public void buttonFuction() {
                worldController.gameMode = WorldController.GameMode.Supermarket;
                worldController.InitiateLevel();
            }
        };

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().button, -Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        play.render(batch);
        supermarket.render(batch);
    }

    @Override
    public void update(float elpasedTime) {
        play.update(elpasedTime);
        supermarket.update(elpasedTime);
    }
}
