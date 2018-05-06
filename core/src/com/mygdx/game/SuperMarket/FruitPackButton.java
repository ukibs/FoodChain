package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.BaseButton;
import com.mygdx.game.WorldController;

/**
 * Created by Martin on 01/05/2018.
 */

public abstract class FruitPackButton extends BaseButton {

    int shelfIndex;
    int packIndex;

    int quantity = 20;
    float timeToExpire = 30;

    boolean onSale = false;

    public FruitPackButton(Texture buttonImage, String buttonText, WorldController worldController,
                           Vector2 position, Vector2 dimension, int shelfIndex, int packIndex) {
        super(buttonImage, buttonText, worldController, position, dimension);
        this.shelfIndex = shelfIndex;
        this.packIndex = packIndex;
        active = false;
    }

    @Override
    public void update(float elapsedTime){
        //
        if(!active) return;
        //
        timeToExpire -= elapsedTime;
        super.update(elapsedTime);
    }

    @Override
    public void render(SpriteBatch batch){
        //
        if(!active) return;
        //
        super.render(batch);
        font.draw(batch, quantity + "", position.x, position.y+dimension.y - 10);
        font.draw(batch, (int)timeToExpire + "", position.x, position.y+dimension.y - 20);
    }

    //
    // TODO: Revisar funcionamioento
    public int BuyStuff(){
        // First decide an amount of pieces to buy
        int piecesToBuy = MathUtils.random(1, 10);
        //
        piecesToBuy = Math.min(piecesToBuy, quantity);
        quantity -= piecesToBuy;
        if(quantity == 0) active = false;
        //
        int moneySpent;
        if(!onSale) moneySpent = piecesToBuy * 10;
        else    moneySpent = piecesToBuy * 5;
        return moneySpent;
    }

}
