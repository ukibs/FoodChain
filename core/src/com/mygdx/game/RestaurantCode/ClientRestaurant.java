package com.mygdx.game.RestaurantCode;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;

/**
 * Created by USUARIO on 07/05/2018.
 */

public class ClientRestaurant extends GameObject {

    public Sprite face;
    float elapsedTime;
    Restaurant restaurantLevel;
    public int currentOrder;
    public int numberOrders;

    public ClientRestaurant(Restaurant restaurant){
        active = false;
        this.dimension = new Vector2(Constants.WIDTH_RATIO * 1, Constants.HEIGHT_RATIO * 1);
        this.restaurantLevel = restaurant;
    }

    public void init()
    {
        active = true;
        numberOrders = MathUtils.random(1, 10);
        currentOrder = MathUtils.random(0, 3);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(!active)
            return;
        //
        batch.draw(face, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elpasedTime) {
        if(!active)
            return;
        //
        this.elapsedTime += elpasedTime;
        if(this.elapsedTime > 5 && numberOrders > 0){
            this.elapsedTime = 0;
        }
        else if(elapsedTime > 2 && numberOrders == 0)
        {

        }
    }

    //
    public void takeOrder()
    {
        currentOrder = MathUtils.random(0, 3);
        numberOrders--;
    }
}
