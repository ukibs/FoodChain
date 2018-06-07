package com.mygdx.game.Singletons;

import com.badlogic.gdx.Gdx;
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
        String explain = "";
        if(win)
        {
            batch.draw(Assets.getInstance().win, Constants.WIDTH_RATIO*(-3.7f), Constants.HEIGHT_RATIO*(1.7f), Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*2);
            switch (gameMode) {
                case Harvest:
                    explain = "You have complete the first part of the chain, well done";
                    break;
                case Transport:
                    explain = "You have complete the second part of the chain, well done";
                    break;
                case Restaurant:
                    explain = "You have complete the third part of the chain, well done";
                    break;
                case Supermarket:
                    explain = "You have complete the last part of the chain, well done\n Now the difficulty has increased, try it harder!";
                    break;
            }
        }
        else
        {
            batch.draw(Assets.getInstance().lose, Constants.WIDTH_RATIO*(-3.7f), Constants.HEIGHT_RATIO*(1.7f), Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*2);
            switch (gameMode) {
                case Harvest:
                    explain = "Food that falls to the floor is wasted\n You have lost a lot of food\n" +
                            "Try to do it better";
                    break;
                case Transport:
                    explain = "Cold chain interrupted can spoil the food\n You have lost a lot of food\n" +
                            "Try to do it better";
                    break;
                case Restaurant:
                    explain = "People leave so much food in restaurants that can be taken to home\n You have lost a lot of food\n" +
                            "Try to do it better";
                    break;
                case Supermarket:
                    explain = "In the supermarket food goes out of date when not managed properly\nSome supermarkets collaborate with donations\n You have lost a lot of food\n" +
                            "Try to do it better";
                    break;
            }
        }
        font.getData().setScale(Gdx.graphics.getWidth()*0.0007f);
        font.draw(batch, explain, Constants.WIDTH_RATIO * -3.5f, Constants.HEIGHT_RATIO * 0.8f, Constants.WIDTH_RATIO*7, 1, true);

    }
}
