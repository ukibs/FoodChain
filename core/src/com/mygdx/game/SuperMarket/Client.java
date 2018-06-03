package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants;
import com.mygdx.game.GameObject;

/**
 * Created by Martin on 05/05/2018.
 */

public class Client extends GameObject {

    public Sprite face;
    float elapsedTime;
    Supermarket supermarket;
    public int currentShelf;
    public float stayTime;

    public Client(Supermarket supermarket, float stayTime){
        active = false;
        this.dimension = new Vector2(Constants.WIDTH_RATIO * 1, Constants.HEIGHT_RATIO * 1);
        this.supermarket = supermarket;
        this.stayTime = stayTime;
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
        if(this.elapsedTime > 1.5f){
            this.elapsedTime = 0;
            BuyStuffAndLeave();
        }
    }

    //
    void CheckShelf(){

    }

    //
    void BuyStuffAndLeave(){
        //
        supermarket.BuyProduct(currentShelf, 0);
        // And deactivate
        active = false;
    }
}
