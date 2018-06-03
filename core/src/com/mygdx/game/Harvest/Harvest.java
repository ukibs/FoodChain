package com.mygdx.game.Harvest;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public class Harvest extends BaseLevel {

    ArrayList<Fruit> fruits;
    Basket basket;
    float spawnTime;

    public void init()
    {
        elapsedTime = 0;
        spawnTime = 0;
        fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit());
        basket = new Basket(worldController);
    }

    @Override
    public void changeLevel(SpriteBatch batch) {
    }

    @Override
    public void tutorial(SpriteBatch batch) {
        batch.draw(Assets.getInstance().basket, 5, 5, 50, 50);
    }

    @Override
    public void GUI(SpriteBatch batch)
    {
        batch.draw(Assets.getInstance().harvestBackground, Constants.WIDTH_RATIO*-5, Constants.HEIGHT_RATIO*-5, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO*10);
        for(int i = 0; i < fruits.size(); i++) {fruits.get(i).render(batch);}
        basket.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        this.elapsedTime += elapsedTime;
        spawnTime += elapsedTime;
            if(spawnTime > 2)
            {
                fruits.add(new Fruit());
                spawnTime = 0;
            }
            for(int i = 0; i < fruits.size(); i++) {fruits.get(i).update(elapsedTime);}
            basket.update(elapsedTime);
            checkCollision();
    }

    @Override
    public void arcadeAxis(char axis, int value)
    {
        if(axis == 'x')
        {
            basket.setDirection(value);
        }
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

        for(int i = 0; i < fruits.size(); i++)
        {
            Fruit currentFruit = fruits.get(i);
            if(currentFruit.position.y < basket.position.y)
            {
                fruits.remove(i);
                worldController.currentScore += 20;
                System.out.println(worldController.currentScore);
            }
        }
    }
}
