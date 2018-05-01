package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by USUARIO on 30/04/2018.
 */

public class Basket extends GameObject
{
    WorldController controller;
    Basket(WorldController worldController)
    {
        controller = worldController;
        dimension = Constants.dimension(2,1);
        position = new Vector2(-dimension.x/2, Constants.HEIGHT_RATIO*(-4)-Constants.HEIGHT_RATIO/2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.getInstance().button, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elpasedTime) {
        position.x = controller.longTouch().x - dimension.x/2;
    }
}
