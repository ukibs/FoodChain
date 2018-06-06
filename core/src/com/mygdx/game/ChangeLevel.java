package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by USUARIO on 07/06/2018.
 */

public class ChangeLevel {
    public static void render(SpriteBatch batch, WorldController.GameMode gameMode, boolean win)
    {
        if(win)
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
