package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public class Harvest extends BaseLevel {

    ArrayList<Fruit> fruits;
    Basket basket;

    float time;

    public void init(WorldController wC)
    {
        worldController = wC;
        fruits = new ArrayList<Fruit>(10);
        for(int i = 0; i < fruits.size(); i++){ fruits.add(new Fruit());}
        basket = new Basket();
    }

    @Override
    public void GUI(SpriteBatch batch)
    {
        for(Fruit fruit: fruits)
        {
            fruit.render(batch);
        }

        basket.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        time += elapsedTime;

        if(time < Constants.LEVEL_TIME)
        {
            for(Fruit fruit: fruits) {fruit.update(elapsedTime);}
            basket.update(elapsedTime);
        }
    }

    
}
