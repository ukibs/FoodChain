package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public class Harvest extends BaseLevel {

    ArrayList<Fruit> fruits;
    Basket basket;
    float spawnTime = 0;

    float time;

    public void init()
    {
        fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit());
        basket = new Basket(worldController);
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
        spawnTime += elapsedTime;
        if(time < Constants.LEVEL_TIME)
        {
            if(spawnTime > 2)
            {
                fruits.add(new Fruit());
                spawnTime = 0;
            }
            for(int i = 0; i < fruits.size(); i++) {fruits.get(i).update(elapsedTime);}
            basket.update(elapsedTime);
            checkCollision();
        }
        else worldController.finishLevel();
    }

    public void checkCollision()
    {
        for(int i = 0; i < fruits.size(); i++)
        {
            Fruit currentFruit = fruits.get(i);
            if(currentFruit.getBounds().overlaps(basket.getBounds()))
            {
                if(!(currentFruit.position.y > basket.position.y+basket.dimension.y*4/5 &&
                        currentFruit.position.x > basket.position.x &&
                        currentFruit.position.x+ currentFruit.dimension.x < basket.position.x+basket.dimension.x))
                {
                    worldController.currentScore += 20;
                    System.out.println(worldController.currentScore);
                }
                fruits.remove(i);
            }
        }
    }
}
