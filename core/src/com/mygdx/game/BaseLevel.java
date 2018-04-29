package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public abstract class BaseLevel extends GameObject {

    private ArrayList<GameObject> levelObjects;

    @Override
    public void render(SpriteBatch batch) {
        GUI();
        //Cabecera
        for(GameObject gameObject : levelObjects){
            gameObject.render(batch);
        }
    }

    @Override
    public void update(float elpasedTime) {
        LevelUpdate();
        for(GameObject gameObject : levelObjects){
            gameObject.update(elpasedTime);
        }
    }

    public abstract void GUI();

    public abstract void LevelUpdate();
}
