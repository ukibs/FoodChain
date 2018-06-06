package com.mygdx.game.Transport;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;
import com.mygdx.game.WorldController;
import com.mygdx.game.WorldRenderer;

import java.util.ArrayList;



public class Box extends GameObject{
    private ArrayList<Box> box = new ArrayList<Box>();
    private float weight;
    private int width;
    private int height;
    float direction;
    float boxSpeed = 0;
    private WorldController controller;
    private Texture boxTexture;

    public Box(WorldController worldController){
        controller = worldController;
        dimension = Constants.dimension(2,1);
        position = new Vector2(-dimension.x/2, Constants.HEIGHT_RATIO*(-4)-Constants.HEIGHT_RATIO/2);
        direction = 0;
        boxSpeed = 20 * (worldController.level + 1);
        boxTexture = Assets.getInstance().transport[MathUtils.random(0, 3)];
        position = new Vector2(MathUtils.random(-Constants.WIDTH_RATIO*4, Constants.WIDTH_RATIO*4-dimension.x), Constants.dimension(0, 4).y);
    }


    @Override
    public void render(SpriteBatch batch) {
        batch.draw(boxTexture, position.x, position.y, dimension.x, dimension.y);
    }

    @Override
    public void update(float elpasedTime) {
        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            position.x = controller.longTouch().x - dimension.x / 2;
        }
        else {
            position.x += direction * boxSpeed * elpasedTime;
        }

    }
}
