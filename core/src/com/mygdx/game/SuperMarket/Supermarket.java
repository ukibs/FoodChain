package com.mygdx.game.SuperMarket;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;

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
    FruitPackButton[][] fruitPackButtons;

    // Buttons to sales/donate
    BaseButton salesButton;
    BaseButton donateButton;
    BaseButton trashButton;

    //
    int currentShelfIndex = -1;
    int currentPackIndex = -1;

    //
    int currentMoney = 1000;

    //
    ClientManager clientManager;

    @Override
    public void init(){

        //
        clientManager = new ClientManager(this);

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
        fruitPackButtons = new FruitPackButton[shelvesAmount][3];

        // And finally the sales/donate, and trash
        salesButton = new BaseButton(Assets.getInstance().button, "Sales", worldController,
                new Vector2(Constants.WIDTH_RATIO * 10,Constants.HEIGHT_RATIO * 10), buttonDimension) {
            @Override
            public void buttonFuction() {
                PutPackInSales();
                System.out.println("Moving to sales ");
            }
        };
        donateButton = new BaseButton(Assets.getInstance().button, "Donate", worldController,
                new Vector2(Constants.WIDTH_RATIO * 10,Constants.HEIGHT_RATIO * 10), buttonDimension) {
            @Override
            public void buttonFuction() {
                DonatePack();
                System.out.println("Donating ");
            }
        };
        trashButton = new BaseButton(Assets.getInstance().button, "Trash", worldController,
                new Vector2(Constants.WIDTH_RATIO * 10,Constants.HEIGHT_RATIO * 10), buttonDimension) {
            @Override
            public void buttonFuction() {
                ThrowPackToTrash();
                System.out.println("Throwing food ");
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
        trashButton.render(batch);

        //
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, currentMoney + "", Constants.WIDTH_RATIO * 4, Constants.HEIGHT_RATIO * 4);

        //
        clientManager.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        //
        clientManager.update(elapsedTime);
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
        trashButton.update(elapsedTime);
    }

    //
    void CreateFruitPack(int shelfIndex, final String packName){

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
        System.out.println("Buying " + packName);

        //
        fruitPackButtons[shelfIndex][packIndex] = new FruitPackButton(Assets.getInstance().button, packName,
                worldController, position, buttonDimension, shelfIndex, packIndex) {
            @Override
            public void buttonFuction() {
                // Here we will move the donate and sales buttons to their correspondant postion
                if(timeToExpire > 0.0f)
                    MoveSalesAndDonateButtons(shelfIndex, packIndex);
                else
                    MoveTrashButton(shelfIndex, packIndex);
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
        currentPackIndex = packIndex;
        currentShelfIndex = shelfIndex;
    }

    //
    void MoveTrashButton(int shelfIndex, int packIndex){
        float offsetY =  buttonDimension.y / 2;
        trashButton.position = new Vector2(shelfButtons[shelfIndex].position.x,
                fruitPackButtons[shelfIndex][packIndex].position.y - offsetY);
        currentPackIndex = packIndex;
        currentShelfIndex = shelfIndex;
    }

    //
    void DonatePack(){
        fruitPackButtons[currentShelfIndex][currentPackIndex] = null;
        HideSalesAndDonateButtons();
    }

    //
    void PutPackInSales(){
        HideSalesAndDonateButtons();
    }

    //
    private void ThrowPackToTrash() {
        fruitPackButtons[currentShelfIndex][currentPackIndex] = null;
        HideSalesAndDonateButtons();
    }

    //
    void HideSalesAndDonateButtons(){
        salesButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        donateButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        trashButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
    }
}
