package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Martin on 30/04/2018.
 */

public class Supermarket extends BaseLevel {

    //
    WorldController worldController;

    // Buttons to buy fruit
    BaseButton[] shelfButtons;

    // Buttons to interact with the fruit packs
    BaseButton[] orangesButtons;
    BaseButton[] tomatoesButtons;

    // Buttons to sales/donate
    BaseButton salesButton;
    BaseButton donateButton;

    public Supermarket(WorldController worldController){
        this.worldController = worldController;
        init();

    }

    void init(){
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
            /*shelfButtons[i] = new BaseButton(Assets.getInstance().button, finalPackName, worldController){
                @Override
                public void buttonFuction() {
                    System.out.println("Comprando " + finalPackName);
                }
            };*/
            //shelfButtons[i].position.x = i * 1;
            //shelfButtons[i].position.y = 0;
        }
    }

    @Override
    public void GUI(SpriteBatch batch) {

    }

    @Override
    public void LevelUpdate(float elapsedTime) {

    }
}
