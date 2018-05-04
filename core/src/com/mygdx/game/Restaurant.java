package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by USUARIO on 01/05/2018.
 */

public class Restaurant extends BaseLevel {

    BaseButton food1;
    BaseButton food2;
    BaseButton food3;
    BaseButton food4;

    BaseButton foodBox;

    @Override
    public void init() {

        food1 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-3, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {

            }
        };

        food2 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-2, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {

            }
        };

        food3 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(-1, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {

            }
        };

        food4 = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(0, -4), Constants.dimension(0.8f, 2)) {
            @Override
            public void buttonFuction() {

            }
        };

        foodBox = new BaseButton(Assets.getInstance().button, "", worldController, Constants.dimension(1.5f, -4.2f), Constants.dimension(3, 2.4f)) {
            @Override
            public void buttonFuction() {

            }
        };
    }

    @Override
    public void GUI(SpriteBatch batch) {
        food1.render(batch);
        food2.render(batch);
        food3.render(batch);
        food4.render(batch);
        foodBox.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        food1.update(elapsedTime);
        food2.update(elapsedTime);
        food3.update(elapsedTime);
        food4.update(elapsedTime);
        foodBox.update(elapsedTime);
    }
}
