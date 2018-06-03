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
    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*1);
    Vector2 shelfDimension = new Vector2(Constants.WIDTH_RATIO*1.5f, Constants.HEIGHT_RATIO*4);
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
    int clientSatisfaction = 100;

    //
    ClientManager clientManager;

    @Override
    public void init(){

        //
        clientManager = new ClientManager(this, worldController.level);

        //
        String packName = "";
        shelfButtons = new BaseButton[shelvesAmount];
        for (int i = 0; i < shelfButtons.length; i++){
            switch (i){
                case 0:
                    packName = "Bananas";
                    break;
                case 1:
                    packName = "Tomatoes";
                    break;
                case 2:
                    packName = "Carrots";
                    break;
                case 3:
                    packName = "Apples";
                    break;
                case 4:
                    packName = "Graples";
                    break;
            }
            final String finalPackName = packName;
            Vector2 position = new Vector2(Constants.WIDTH_RATIO * (i * 2 - 4.8f), Constants.HEIGHT_RATIO *(1.5f));
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
        /*for (int i = 0; i < fruitPackButtons.length; i++) {
            for (int j = 0; j < fruitPackButtons[i].length; j++) {
                CreateFruitPack(i, shelfButtons[i].);
            }
        }*/


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

        //
        elapsedTime = 0;
        clientSatisfaction = 100;
        currentMoney = 1000;

        // TODO: Decidir que aumentar con el nivel
    }

    @Override
    public void changeLevel(SpriteBatch batch) {
        // TODO: Pintar en GUI lo que pasa al perder/ganar
    }

    @Override
    public void tutorial(SpriteBatch batch) {

    }

    @Override
    public void GUI(SpriteBatch batch) {
        //
        for (int i = 0; i < shelfButtons.length; i++) {
            batch.draw(Assets.getInstance().shelf,
                    shelfButtons[i].position.x,
                    shelfButtons[i].position.y - shelfDimension.y,
                    shelfDimension.x, shelfDimension.y);
        }
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
        font.draw(batch, clientSatisfaction + "", Constants.WIDTH_RATIO * -4, Constants.HEIGHT_RATIO * 4);
        //
        clientManager.render(batch);
    }

    @Override
    public void LevelUpdate(float elapsedTime) {

        // TODO: Make arcade controls

        //
        this.elapsedTime += elapsedTime;
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

        //
        CheckDefeat();

    }

    //
    void CreateFruitPack(int shelfIndex, final String packName){

        //
        if(currentMoney < 100) return;

        // Search for a free place
        int packIndex;
        for(packIndex = 0; packIndex < fruitPackButtons[shelfIndex].length; packIndex++){
            if(fruitPackButtons[shelfIndex][packIndex] == null || !fruitPackButtons[shelfIndex][packIndex].active)
                break;
        }
        // If there is no one return
        if(packIndex == fruitPackButtons[shelfIndex].length) return;

        // Decide the position for the button
        Vector2 position = new Vector2(shelfButtons[shelfIndex].position.x,
                Constants.HEIGHT_RATIO *((-packIndex * 1.5f)));
        System.out.println("Buying " + packName);

        // And create it
        fruitPackButtons[shelfIndex][packIndex] = new FruitPackButton(Assets.getInstance().fruitPacks[shelfIndex][0], "",
                worldController, position, buttonDimension, shelfIndex, packIndex) {
            @Override
            public void buttonFuction() {
                // Here we will move the donate and sales buttons to their correspondant postion
                if(currentTimeToExpire > 0.0f)
                    MoveSalesAndDonateButtons(shelfIndex, packIndex);
                else
                    MoveTrashButton(shelfIndex, packIndex);
            }
        };
        //
        currentMoney -= 100;
        //
        fruitPackButtons[shelfIndex][packIndex].active = true;
    }

    //
    void ActivateFruitPack(int shelfIndex){

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
        // Lose global score
        worldController.currentScore -= 10;
        HideSalesAndDonateButtons();
    }

    //
    void HideSalesAndDonateButtons(){
        salesButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        donateButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        trashButton.position = new Vector2(Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
    }

    //
    public void BuyProduct(int shelfIndex, int purchaseType){
        // For now don't use the type of

        //
        if(CheckIfContainsOutOfDateFood(shelfIndex)) {
            clientSatisfaction -= 10;
            return;
        }
        //
        int i;
        for(i = 0; i < fruitPackButtons[shelfIndex].length; i++){
            if(fruitPackButtons[shelfIndex][i] != null && fruitPackButtons[shelfIndex][i].active){
                //int amount = MathUtils.random(1, 10);
                currentMoney += fruitPackButtons[shelfIndex][i].BuyStuff();
                break;
            }
        }
        // If there is no food in the shelf
        if(i == fruitPackButtons[shelfIndex].length){
            clientSatisfaction -= 10;
        }
    }

    //
    boolean CheckIfContainsOutOfDateFood(int shelfIndex){
        int i;
        for(i = 0; i < fruitPackButtons[shelfIndex].length; i++){
            if(fruitPackButtons[shelfIndex][i] != null &&
                    fruitPackButtons[shelfIndex][i].active &&
                    fruitPackButtons[shelfIndex][i].currentTimeToExpire <= 0) {
                return true;
            }
        }
        return false;
    }

    //
    void CheckDefeat(){
        if(clientSatisfaction <= 0){
            worldController.finishLevel(false);
        }
    }
}
