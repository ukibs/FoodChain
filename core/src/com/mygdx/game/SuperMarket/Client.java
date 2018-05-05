package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;

/**
 * Created by Martin on 05/05/2018.
 */

public class Client extends GameObject {

    public Sprite face;

    public Client(){
        active = false;
        this.dimension = new Vector2(Constants.WIDTH_RATIO * 1, Constants.HEIGHT_RATIO * 1);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(!active)
            return;
        //
        face.draw(batch);
    }

    @Override
    public void update(float elpasedTime) {
        if(!active)
            return;

    }
}
