package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Martin on 30/04/2018.
 */

public abstract class BaseButton extends GameObject {

    Texture buttonImage;
    public String buttonText;
    public BitmapFont font;
    WorldController worldController;

    public BaseButton(Texture buttonImage, String buttonText, WorldController worldController, Vector2 position, Vector2 dimension){
        font = new BitmapFont();
        this.buttonText = buttonText;
        this.buttonImage = buttonImage;
        this.worldController = worldController;
        this.position = new Vector2(position);
        this.dimension = new Vector2(dimension);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(buttonImage, position.x, position.y, dimension.x, dimension.y);
        font.setColor(Color.BLACK);
        font.draw(batch, buttonText, position.x, position.y+dimension.y);
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
