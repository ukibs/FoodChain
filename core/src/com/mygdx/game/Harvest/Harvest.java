package com.mygdx.game.Harvest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;
import com.mygdx.game.SoundManager;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

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
        fruits.add(new Fruit(worldController));
        basket = new Basket(worldController);
    }

    @Override
    public void GUI(SpriteBatch batch)
    {
        batch.draw(Assets.getInstance().harvestBackground, Constants.WIDTH_RATIO*-5, Constants.HEIGHT_RATIO*-5, Constants.WIDTH_RATIO*10, Constants.HEIGHT_RATIO*10);
        for(int i = 0; i < fruits.size(); i++) {fruits.get(i).render(batch);}
        basket.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime)
    {
        this.elapsedTime += elapsedTime;
        spawnTime += elapsedTime;
            if(spawnTime > 2)
            {
                fruits.add(new Fruit(worldController));
                spawnTime = 0;
            }
            for(int i = 0; i < fruits.size(); i++) {fruits.get(i).update(elapsedTime);}
            basket.update(elapsedTime);
            checkCollision();
    }

    @Override
    public void arcadeButtonControllers(int buttonIndex) {

    }

    @Override
    public void arcadeAxis(char axis, int value)
    {
        if(axis == 'x')
        {
            basket.setDirection(value);
        }
    }

    public void moveBasket(Vector2 position)
    {
        if(position.x == -100000)
        {
            basket.setDirection(0);
        }
        else if(position.x > 0)
        {
            basket.setDirection(1);
            System.out.println("Derecha " + position.x);
        }
        else
        {
            basket.setDirection(-1);
            System.out.println("Izquierda " + position.x);
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
                    worldController.currentScore -= 20;
                    SoundManager.getInstance().play(SoundManager.fruitLost);
                }
                else SoundManager.getInstance().play(SoundManager.getFruit);
                fruits.remove(i);
            }
        }

        for(int i = 0; i < fruits.size(); i++)
        {
            Fruit currentFruit = fruits.get(i);
            if(currentFruit.position.y < basket.position.y)
            {
                fruits.remove(i);
                worldController.currentScore -= 20;
                SoundManager.getInstance().play(SoundManager.fruitLost);
            }
        }
    }
}
