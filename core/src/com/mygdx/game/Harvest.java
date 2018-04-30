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
        fruits = new ArrayList<Fruit>();
        for(int i = 0; i < 10; i++){ fruits.add(new Fruit());}
        basket = new Basket();
    }

    @Override
    public void GUI(SpriteBatch batch)
    {
        for(int i = 0; i < fruits.size(); i++) {fruits.get(i).render(batch);}
        basket.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        time += elapsedTime;

        if(time < Constants.LEVEL_TIME)
        {
            for(int i = 0; i < fruits.size(); i++) {fruits.get(i).update(elapsedTime);}
            basket.update(elapsedTime);
        }
        else worldController.finishLevel();
    }


}
