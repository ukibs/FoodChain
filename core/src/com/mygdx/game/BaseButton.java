package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Martin on 30/04/2018.
 */

public abstract class BaseButton extends GameObject {

    Texture buttonImage;
    String buttonText;
    WorldController worldController;

    public BaseButton(Texture buttonImage, String buttonText, WorldController worldController){
        this.buttonText = buttonText;
        this.buttonImage = buttonImage;
        this.worldController = worldController;
        position = new Vector2( 0, 0);
        dimension = new Vector2(2,1);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(buttonImage, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elpasedTime) {
        Vector2 currentTouch = worldController.getTouch();
        if(currentTouch.x > position.x && currentTouch.y > position.y &&
                currentTouch.x < position.x + dimension.x &&
                currentTouch.y < position.y + dimension.y)
            buttonFuction();
    }

    public abstract void buttonFuction();
}
