package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import sun.rmi.runtime.Log;

/**
 * Created by Martin on 30/04/2018.
 */

public class Supermarket extends BaseLevel {

    //
    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO*2, Constants.HEIGHT_RATIO*1.5f);
    private static final int shelvesAmount = 5;

    // Buttons to buy fruit
    BaseButton[] shelfButtons;

    // Buttons to interact with the fruit packs
    BaseButton[][] fruitPackButtons;

    // Buttons to sales/donate
    BaseButton salesButton;
    BaseButton donateButton;

    @Override
    public void init(){

        //
        String packName = "";
        shelfButtons = new BaseButton[shelvesAmount];
        for (int i = 0; i < shelfButtons.length; i++){
            switch (i){
                case 0:
                    packName = "Meat";
                    break;
                case 1:
                    packName = "Fish";
                    break;
                case 2:
                    packName = "Frutalizas";
                    break;
                case 3:
                    packName = "Corticoides";
                    break;
                case 4:
                    packName = "Gluten";
                    break;
            }
            final String finalPackName = packName;
            Vector2 position = new Vector2(Constants.WIDTH_RATIO * (i * 2 - 5), Constants.HEIGHT_RATIO *(1.5f));
            final int finalI = i;
            shelfButtons[i] = new BaseButton(Assets.getInstance().button, "Buy: " + finalPackName,
                    worldController, position, buttonDimension){
                @Override
                public void buttonFuction() {
                    CreateFruitPack(finalI, finalPackName);
                }
            };
        }
        // Then the fruit pack buttons
        fruitPackButtons = new BaseButton[shelvesAmount][3];

        // And finally the sales/donate
        salesButton = new BaseButton(Assets.getInstance().button, "Sales", worldController,
                new Vector2(Constants.WIDTH_RATIO * (-5),Constants.HEIGHT_RATIO * (-5)), buttonDimension) {
            @Override
            public void buttonFuction() {
                System.out.println("Moving to sales ");
            }
        };
        donateButton = new BaseButton(Assets.getInstance().button, "Donate", worldController,
                new Vector2(Constants.WIDTH_RATIO * (2),Constants.HEIGHT_RATIO * (-5)), buttonDimension) {
            @Override
            public void buttonFuction() {
                System.out.println("Donating ");
            }
        };
    }

    @Override
    public void GUI(SpriteBatch batch) {
        //
        for(int i = 0; i < shelfButtons.length; i++){
            shelfButtons[i].render(batch);
        }
        //
        for (int i = 0; i < fruitPackButtons.length; i++){
            for (int j = 0; j < fruitPackButtons[i].length; j++){
                if(fruitPackButtons[i][j] != null)
                    fruitPackButtons[i][j].render(batch);
            }
        }
        //
        donateButton.render(batch);
        salesButton.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        //
        for(int i = 0; i < shelfButtons.length; i++){
            shelfButtons[i].update(elapsedTime);
        }
        //
        for (int i = 0; i < fruitPackButtons.length; i++){
            for (int j = 0; j < fruitPackButtons[i].length; j++){
                if(fruitPackButtons[i][j] != null)
                    fruitPackButtons[i][j].update(elapsedTime);
            }
        }
        //
        donateButton.update(elapsedTime);
        salesButton.update(elapsedTime);
    }

    //
    void CreateFruitPack(int shelfIndex, String packName){

        //
        int packIndex;
        for(packIndex = 0; packIndex < fruitPackButtons[shelfIndex].length; packIndex++){
            if(fruitPackButtons[shelfIndex][packIndex] == null)
                break;
        }
        if(packIndex == fruitPackButtons[shelfIndex].length) return;

        //
        Vector2 position = new Vector2(shelfButtons[shelfIndex].position.x,
                Constants.HEIGHT_RATIO *((-packIndex * 1.5f)));
        System.out.println("Comprando " + packName);

        //
        fruitPackButtons[shelfIndex][packIndex] = new FruitPackButton(Assets.getInstance().button, packName,
                worldController, position, buttonDimension, shelfIndex, packIndex) {
            @Override
            public void buttonFuction() {
                // Here we will move the donate and sales buttons to their correspondant postion
                MoveSalesAndDonateButtons(shelfIndex, packIndex);
            }
        };
    }

    //
    void MoveSalesAndDonateButtons(int shelfIndex, int packIndex){
        float offsetX =  buttonDimension.x / 2;
        float offsetY =  buttonDimension.y / 2;
        salesButton.position = new Vector2(shelfButtons[shelfIndex].position.x - offsetX,
                fruitPackButtons[shelfIndex][packIndex].position.y - offsetY);
        donateButton.position = new Vector2(shelfButtons[shelfIndex].position.x + offsetX,
                fruitPackButtons[shelfIndex][packIndex].position.y - offsetY);
    }
}
