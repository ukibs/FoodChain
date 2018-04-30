package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Martin on 29/04/2018.
 */

public abstract class BaseLevel extends GameObject {

    private ArrayList<GameObject> levelObjects;
    protected WorldController worldController;

    @Override
    public void render(SpriteBatch batch) {
        GUI(batch);
        //Cabecera
       /* for(GameObject gameObject : levelObjects){
            gameObject.render(batch);
        }*/
    }

    @Override
    public void update(float elpasedTime) {
        LevelUpdate(elpasedTime);
        /*for(GameObject gameObject : levelObjects){
            gameObject.update(elpasedTime);
        }*/
    }

    public abstract void GUI(SpriteBatch batch);

    public abstract void LevelUpdate(float elapsedTime);
}
