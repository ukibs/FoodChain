package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class Fruit extends GameObject {
    Texture fruitTexture;

    public Fruit()
    {
        fruitTexture = Assets.getInstance().fruitHarvest.get(MathUtils.random(0, Assets.getInstance().fruitHarvest.size()));
        dimension = new Vector2(1,1);
        position = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()-dimension.x), Gdx.graphics.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(fruitTexture, position.x, position.y);
    }

    @Override
    public void update(float elpasedTime) {
        position.y -= Constants.SPRITE_SPEED * elpasedTime;
    }
}
