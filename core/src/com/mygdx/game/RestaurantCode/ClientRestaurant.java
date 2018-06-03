package com.mygdx.game.RestaurantCode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;

/**
 * Created by USUARIO on 07/05/2018.
 */

public class ClientRestaurant extends GameObject {

    public Texture face;
    float elapsedTime;
    Restaurant restaurantLevel;
    public int currentOrder;
    public int numberOrders;
    int actionTime;
    ClientState clientState;
    BitmapFont font;

    public enum ClientState{
        Ordering,
        Eating,
        Going
    }

    public ClientRestaurant(Restaurant restaurant, Vector2 position){
        active = false;
        this.dimension = Constants.dimension(1, 3.5f);
        this.restaurantLevel = restaurant;
        this.position = position;
        font = new BitmapFont();
        face = Assets.getInstance().clients[MathUtils.random(0,3)];
    }

    public void init()
    {
        clientState = ClientState.Ordering;
        active = true;
        numberOrders = MathUtils.random(1, 10);
        currentOrder = MathUtils.random(0, 3);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(!active)
            return;
        //
        batch.draw(Assets.getInstance().think, position.x, position.y+Constants.HEIGHT_RATIO*2.8f, dimension.x, dimension.y/2f);
        //
        batch.draw(face, position.x, position.y, dimension.x, dimension.y);
        switch (clientState)
        {
            case Ordering:
                batch.draw(Assets.getInstance().food[currentOrder], position.x+Constants.WIDTH_RATIO*0.35f, position.y+Constants.HEIGHT_RATIO*3.5f, dimension.x/3, dimension.y/5f);
                break;
            case Eating:

                break;
            case Going:
                batch.draw(Assets.getInstance().food[4], position.x+Constants.WIDTH_RATIO*0.35f, position.y+Constants.HEIGHT_RATIO*3.3f, dimension.x/3, dimension.y/3.3f);
                break;
        }
    }

    @Override
    public void update(float elpasedTime) {
        if(!active)
            return;
        //
        this.elapsedTime += elpasedTime;

        switch (clientState)
        {
            case Ordering:
            if(elapsedTime > 7)
            {
                elapsedTime = 0;
                active = false;
            }
            break;
            case Eating:
                if(elapsedTime > 3)
                {
                    elapsedTime = 0;
                    if(numberOrders == 0)
                    {
                        clientState = ClientState.Going;
                    }
                    else clientState = ClientState.Ordering;
                }
                break;
            case Going:
                if(elapsedTime > 2)
                {
                    restaurantLevel.wastedFood();
                    active = false;
                }
        }
    }

    //
    public void takeOrder()
    {
        if(numberOrders>1) {
            currentOrder = MathUtils.random(0, 3);
            numberOrders--;
            clientState = ClientState.Eating;
        }
        else if(numberOrders == 1)
        {
            numberOrders--;
            currentOrder = 4;
            clientState = ClientState.Eating;
        }
        else active = false;
    }
}
