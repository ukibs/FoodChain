package com.mygdx.game.EndLevel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;

/**
 * Created by Martin on 08/05/2018.
 */

public class EndLevel extends BaseLevel {

    BaseButton returnButton;
    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO*2, Constants.HEIGHT_RATIO*1.5f);

    @Override
    public void GUI(SpriteBatch batch) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "CONGRATULATIONS", Constants.WIDTH_RATIO * 4, Constants.HEIGHT_RATIO * 4);
        font.draw(batch, "The food is in good hands with you!", Constants.WIDTH_RATIO * 4, Constants.HEIGHT_RATIO * 4);
        font.draw(batch, "Your score: ", Constants.WIDTH_RATIO * 4, Constants.HEIGHT_RATIO * 4);
        font.draw(batch, worldController.currentScore + "", Constants.WIDTH_RATIO * 4, Constants.HEIGHT_RATIO * 4);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {

    }

    @Override
    public void init() {

        returnButton = new BaseButton(Assets.getInstance().button, "Sales", worldController,
                new Vector2(Constants.WIDTH_RATIO * 10,Constants.HEIGHT_RATIO * 10), buttonDimension) {
            @Override
            public void buttonFuction() {
                worldController.finishLevel();
            }
        };

    }
}
