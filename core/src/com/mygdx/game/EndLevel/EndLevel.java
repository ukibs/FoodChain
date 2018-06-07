package com.mygdx.game.EndLevel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Singletons.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;

/**
 * Created by Martin on 08/05/2018.
 */

public class EndLevel extends BaseLevel {

    BaseButton returnButton;
    BaseButton[] charUpButtons;
    BaseButton[] charDownButtons;
    int[] chars = {65,65,65};
    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO*2, Constants.HEIGHT_RATIO*1.5f);
    Vector2 miniButtonDimension = new Vector2(Constants.WIDTH_RATIO, Constants.HEIGHT_RATIO*0.75f);

    @Override
    public void GUI(SpriteBatch batch) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "CONGRATULATIONS", 0, Constants.HEIGHT_RATIO * 3);
        font.draw(batch, "The food is in good hands with you!", 0, Constants.HEIGHT_RATIO * 2f);
        font.draw(batch, "Your score: ", 0, Constants.HEIGHT_RATIO * 1);
        font.draw(batch, worldController.currentScore + "", 0, Constants.HEIGHT_RATIO * 0f);
        for(int i = 0; i < chars.length; i++){

            charUpButtons[i].render(batch);
            charDownButtons[i].render(batch);
            Vector2 position = Constants.dimension(i-1, -1);
            font.draw(batch, (char)chars[i] + "", position.x, position.y);
        }
        //
        returnButton.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        for(int i = 0; i < chars.length; i++) {
            charUpButtons[i].update(elapsedTime);
            charDownButtons[i].update(elapsedTime);
        }
        returnButton.update(elapsedTime);
    }

    @Override
    public void arcadeButtonControllers(int buttonIndex) {

    }

    @Override
    public void arcadeAxis(char axis, int value) {

    }

    @Override
    public void init() {
        state = LevelState.InGame;
        returnButton = new BaseButton(Assets.getInstance().button, "Confirm", worldController,
                Constants.dimension(-1, -4), buttonDimension) {
            @Override
            public void buttonFuction() {
                char characters[] = {(char)chars[0], (char)chars[1], (char)chars[2]};
                String playerName =
                        String.valueOf(characters[0]) + "   " + String.valueOf(characters[1]) + "   " + String.valueOf(characters[2]);
                worldController.saveScore(playerName);
                worldController.finishLevel(true);
            }
        };
        SpawnCharButtons();
    }

    void SpawnCharButtons(){
        // Frist assign sizes
        charUpButtons = new BaseButton[3];
        charDownButtons = new BaseButton[3];
        // Then up buttons
        for(int i = 0; i < charUpButtons.length; i++){
            final int finalI = i;
            charUpButtons[i] = new BaseButton(Assets.getInstance().button, "Up", worldController,
                    Constants.dimension(i - 1, -1), miniButtonDimension) {
                @Override
                public void buttonFuction() {
                    chars[finalI] --;
                    if(chars[finalI] < 65) chars[finalI] = 90;
                }
            };
        }
        // And then down buttons
        for(int i = 0; i < charDownButtons.length; i++){
            final int finalI = i;
            charDownButtons[i] = new BaseButton(Assets.getInstance().button, "Down", worldController,
                    Constants.dimension(i - 1, -2), miniButtonDimension) {
                @Override
                public void buttonFuction() {
                    chars[finalI] ++;
                    if(chars[finalI] > 90) chars[finalI] = 65;
                }
            };
        }
    }

}
