package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class MainMenu extends GameObject {
    WorldController worldController;
    Texture background;
    BaseButton play;

    MainMenu(WorldController worldController)
    {
        this.worldController = worldController;
        play = new BaseButton(Assets.getInstance().button, "Play", worldController) {
            @Override
            public void buttonFuction() {
                worldController.InitiateLevel();
                System.out.println("Hola");
            }
        };


    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().button, -Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        play.render(batch);
    }

    @Override
    public void update(float elpasedTime) {
        play.update(elpasedTime);
    }
}
