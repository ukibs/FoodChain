package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by USUARIO on 07/06/2018.
 */

public class Tutorials {
    public static void render(SpriteBatch batch, WorldController.GameMode gameMode)
    {
        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            switch (gameMode) {
                case Harvest:
                    batch.draw(Assets.getInstance().header, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO);
                    break;
                case Transport:
                    break;
                case Restaurant:
                    break;
                case Supermarket:
                    break;
            }
        }
        else
        {
            switch (gameMode) {
                case Harvest:
                    batch.draw(Assets.getInstance().header, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO);
                    break;
                case Transport:
                    break;
                case Restaurant:
                    break;
                case Supermarket:
                    break;
            }
        }
    }
}
