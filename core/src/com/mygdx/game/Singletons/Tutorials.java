package com.mygdx.game.Singletons;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Constants;
import com.mygdx.game.WorldController;

/**
 * Created by USUARIO on 07/06/2018.
 */

public class Tutorials {
    public static void render(SpriteBatch batch, WorldController.GameMode gameMode, BitmapFont font)
    {
        String explain;
        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            switch (gameMode) {
                case Harvest:
                    explain = "Touch in the screen to move the basket \n Collect all the fruit \n The food waste bar will fill if the fruit falls or is caught wrong \n You lose if waste bar is completed";
                    batch.draw(Assets.getInstance().header, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO, Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO);
                    font.getData().setScale(Gdx.graphics.getWidth()*0.0007f);
                    font.draw(batch, explain, Constants.WIDTH_RATIO * -3.5f, Constants.HEIGHT_RATIO * -4.3f, Constants.WIDTH_RATIO*5, 1, true);
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
                    explain = "Move the basket with the joystick. \n Collect all the fruit \n The food waste bar will fill if the fruit falls or is caught wrong \n You lose if waste bar is completed ";
                    font.getData().setScale(Gdx.graphics.getWidth()*0.0007f);
                    font.draw(batch, explain, Constants.WIDTH_RATIO * -3.5f, Constants.HEIGHT_RATIO * 2.8f, Constants.WIDTH_RATIO*7, 1, true);
                    break;
                case Transport:
                    explain = "Maintain the temperature and the speed in the correct values \n If not the food will be waste \n You lose if waste bar is completed";
                    font.getData().setScale(Gdx.graphics.getWidth()*0.0007f);
                    font.draw(batch, explain, Constants.WIDTH_RATIO * -3.5f, Constants.HEIGHT_RATIO * 2.8f, Constants.WIDTH_RATIO*7, 1, true);
                    batch.draw(Assets.getInstance().transportArcadeControls, Constants.WIDTH_RATIO*-2f, Constants.HEIGHT_RATIO*-2.5f, Constants.WIDTH_RATIO*4, Constants.HEIGHT_RATIO*3);
                    break;
                case Restaurant:
                    explain = "Click on the food to serve it to customers\n" +
                            "If you do not attend them quickly they will get angry and leave food that will be added to the waste bar\n You lose if waste bar is completed";
                    font.getData().setScale(Gdx.graphics.getWidth()*0.0007f);
                    font.draw(batch, explain, Constants.WIDTH_RATIO * -3.5f, Constants.HEIGHT_RATIO * 2.8f, Constants.WIDTH_RATIO*7, 1, true);
                    break;
                case Supermarket:
                    break;
            }
        }
    }
}
