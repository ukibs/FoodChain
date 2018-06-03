package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.WorldController;

/**
 * Created by Martin on 01/05/2018.
 */

public abstract class FruitPackButton extends BaseButton {

    int shelfIndex;
    int packIndex;

    int quantity = 6;
    float timeToExpire;
    float currentTimeToExpire;


    boolean onSale = false;

    Color currentColor = Color.WHITE;

    public FruitPackButton(Texture buttonImage, String buttonText, WorldController worldController,
                           Vector2 position, Vector2 dimension, int shelfIndex, int packIndex) {
        super(buttonImage, buttonText, worldController, position, dimension);
        this.shelfIndex = shelfIndex;
        this.packIndex = packIndex;
        active = false;
        // We'll work this
        timeToExpire = 20 - (1 * worldController.level);
        currentTimeToExpire = timeToExpire;
    }

    @Override
    public void update(float elapsedTime){
        //
        if(!active) return;
        //
        currentTimeToExpire -= elapsedTime;
        float tintValue = currentTimeToExpire/timeToExpire;
        currentColor = new Color(tintValue, tintValue, tintValue, 1);
        super.update(elapsedTime);
    }

    @Override
    public void render(SpriteBatch batch){
        //
        if(!active) return;
        //
        batch.setColor(currentColor);
        super.render(batch);
        batch.setColor(Color.WHITE);
        //font.draw(batch, quantity + "", position.x, position.y+dimension.y - 10);
        //font.draw(batch, (int) currentTimeToExpire + "", position.x, position.y+dimension.y - 20);
    }

    //
    // TODO: Revisar funcionamioento
    public int BuyStuff(){
        // First decide an amount of pieces to buy
        int piecesToBuy = MathUtils.random(1, 3);
        //
        piecesToBuy = Math.min(piecesToBuy, quantity);
        quantity -= piecesToBuy;
        buttonImage = Assets.getInstance().fruitPacks[shelfIndex][5 - quantity];
        if(quantity == 0) active = false;
        //
        int moneySpent;
        if(!onSale) moneySpent = piecesToBuy * 10;
        else    moneySpent = piecesToBuy * 5;
        return moneySpent;
    }

}
