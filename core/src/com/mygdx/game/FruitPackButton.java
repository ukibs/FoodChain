package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Martin on 01/05/2018.
 */

public abstract class FruitPackButton extends BaseButton {

    int shelfIndex;
    int packIndex;

    int quantity = 20;
    float timeToExpire = 30;

    public FruitPackButton(Texture buttonImage, String buttonText, WorldController worldController,
                           Vector2 position, Vector2 dimension, int shelfIndex, int packIndex) {
        super(buttonImage, buttonText, worldController, position, dimension);
        this.shelfIndex = shelfIndex;
        this.packIndex = packIndex;
    }

    @Override
    public void update(float elapsedTime){
        timeToExpire -= elapsedTime;
        super.update(elapsedTime);
    }

    @Override
    public void render(SpriteBatch batch){
        super.render(batch);
        font.draw(batch, quantity + "", position.x, position.y+dimension.y - 10);
        font.draw(batch, (int)timeToExpire + "", position.x, position.y+dimension.y - 20);
    }

}
