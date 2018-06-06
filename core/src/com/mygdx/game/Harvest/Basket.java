package com.mygdx.game.Harvest;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;
import com.mygdx.game.WorldController;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class Basket extends GameObject
{
    WorldController controller;
    float direction;
    float basketSpeed;

    Basket(WorldController worldController)
    {
        controller = worldController;
        dimension = Constants.dimension(2,1);
        position = new Vector2(-dimension.x/2, Constants.HEIGHT_RATIO*(-4)-Constants.HEIGHT_RATIO/2);
        direction = 0;
        basketSpeed = 400 * (worldController.level + 10);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().basket, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elpasedTime)
    {
        position.x += direction * basketSpeed * elpasedTime;
        position.x = MathUtils.clamp(position.x, Constants.WIDTH_RATIO*-5, Constants.WIDTH_RATIO*5-dimension.x);
    }

    public void setDirection(float direction)
    {
        this.direction = direction;
    }
}
