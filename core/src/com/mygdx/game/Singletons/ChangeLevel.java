package com.mygdx.game.Singletons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants;
import com.mygdx.game.WorldController;

/**
 * Created by USUARIO on 07/06/2018.
 */

public class ChangeLevel {
    public static void render(SpriteBatch batch, WorldController.GameMode gameMode, boolean win, BitmapFont font)
    {
        if(win)
        {
            batch.draw(Assets.getInstance().win, Constants.WIDTH_RATIO*(-3.7f), Constants.HEIGHT_RATIO*(1.7f), Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*2);
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
            batch.draw(Assets.getInstance().lose, Constants.WIDTH_RATIO*(-3.7f), Constants.HEIGHT_RATIO*(1.7f), Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*2);
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
