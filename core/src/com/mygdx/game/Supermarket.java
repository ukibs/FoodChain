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
    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO*3, Constants.HEIGHT_RATIO*2);

    //
    WorldController worldController;

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
        shelfButtons = new BaseButton[2];
        for (int i = 0; i < shelfButtons.length; i++){
            switch (i){
                case 0:
                    packName = "Oranges";
                    break;
                case 1:
                    packName = "Tomatoes";
                    break;
            }
            final String finalPackName = packName;
            Vector2 position = new Vector2(i * 1, 0);
            final int finalI = i;
            shelfButtons[i] = new BaseButton(Assets.getInstance().button, finalPackName, worldController, position, buttonDimension){
                @Override
                public void buttonFuction() {
                    System.out.println("Comprando " + finalPackName);
                    CreateFruitPack(finalI);
                }
            };
        }
        // Then the fruit pack buttons
        // TODO: Remember to revise the shlef lenght
        fruitPackButtons = new BaseButton[2][3];

        // And finally the sales/donate
        salesButton = new BaseButton(Assets.getInstance().button, "Sales", worldController, new Vector2(1, 0), buttonDimension) {
            @Override
            public void buttonFuction() {
                System.out.println("Moving to sales ");
            }
        };
        donateButton = new BaseButton(Assets.getInstance().button, "Donate", worldController, new Vector2(1, 1), buttonDimension) {
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
    void CreateFruitPack(int shelfIndex){
        int fruitPackPosition = 0;
        // TODO: Make a class FruitPackButton
        fruitPackButtons[shelfIndex][fruitPackPosition] = new BaseButton(Assets.getInstance().button, "Fruit",
                worldController, new Vector2(shelfIndex * 1, fruitPackPosition * 1), buttonDimension) {
            @Override
            public void buttonFuction() {
                // Here we will move the donate and sales buttons to their correspondant postion
            }
        };
    }
}
