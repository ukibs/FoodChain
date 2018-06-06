package com.mygdx.game.Transport;

/**
 * Created by jimmy on 06/06/18.
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseButton;
import com.mygdx.game.BaseLevel;
import com.mygdx.game.Constants;


import java.util.ArrayList;

/**
 * Created by jimmy on 05/06/18.
 */

public class Transport extends BaseLevel {
    public static float move = 50;
    public static float speed = 20;
    public static float currentSpeed = 0;
    float x;
    float initialSpeedX;
    float speedBarPosition;
    float barDimension;
    float indicatorDimension;
    float initialIndicator;
    float temperatureBarPosition;
    ArrayList<Box> boxObject = new ArrayList<Box>();
    float spawnTime;
    Box box;
    //BaseButton start;
    BaseButton increase;
    BaseButton decrease;
    BaseButton speedIncrease;
    BaseButton speedDecrease;

    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO * 1.5f, Constants.HEIGHT_RATIO * 1);

    public Transport(){


    }

    @Override
    public void changeLevel(SpriteBatch batch) {

    }

    @Override
    public void tutorial(SpriteBatch batch) {

    }

    @Override
    public void GUI(SpriteBatch batch) {
        batch.draw(Assets.getInstance().background, Constants.WIDTH_RATIO * -5, Constants.HEIGHT_RATIO * -5, Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        batch.draw(Assets.getInstance().truck,Constants.WIDTH_RATIO * -3, Constants.HEIGHT_RATIO * -3 , Constants.WIDTH_RATIO * 7, Constants.HEIGHT_RATIO* 7);
        //Temperature bar
        batch.draw(Assets.getInstance().temperature, temperatureBarPosition, Constants.HEIGHT_RATIO * -2, barDimension, Constants.HEIGHT_RATIO * 1);
        batch.draw(Assets.getInstance().indicator, initialIndicator + x, Constants.HEIGHT_RATIO* -3,indicatorDimension, Constants.HEIGHT_RATIO * 1);
        //Speed bar
        batch.draw(Assets.getInstance().speedometer,speedBarPosition, Constants.HEIGHT_RATIO * -2f, barDimension, Constants.HEIGHT_RATIO * 1);
        batch.draw(Assets.getInstance().indicator,initialSpeedX + currentSpeed,Constants.HEIGHT_RATIO* -3,indicatorDimension, Constants.HEIGHT_RATIO * 1);

        speedDecrease.render(batch);
        speedIncrease.render(batch);
        increase.render(batch);
        decrease.render(batch);
    }
    private void moveIndicator(float value){
        if(initialIndicator + x + value > temperatureBarPosition &&
                initialIndicator + x + value < temperatureBarPosition + barDimension - indicatorDimension){
            x += value;
        }
    }
    private void moveSpeedometer(float value) {
        if (initialSpeedX + currentSpeed + value > speedBarPosition &&
                initialSpeedX + currentSpeed + value < initialSpeedX + barDimension - indicatorDimension) {
            currentSpeed += value;
        }
    }
    private void checkTemperature(){
        if (initialSpeedX + currentSpeed  < speedBarPosition + (barDimension / 3)){
            System.out.println("too hot");
            worldController.currentScore -= 0.01;
        }
        if(initialSpeedX + currentSpeed  > speedBarPosition + (barDimension * 2/3) - indicatorDimension) {
            System.out.println("too cold");
            worldController.currentScore -= 0.01;
        }

//        } else{
//            worldController.currentScore -= 0.01;
//        }

    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        this.elapsedTime += elapsedTime;
        spawnTime += elapsedTime;
        speedIncrease.update(elapsedTime);
        speedDecrease.update(elapsedTime);
        increase.update(elapsedTime);
        decrease.update(elapsedTime);
        if (spawnTime > 8){
            moveSpeedometer(MathUtils.random(-20,20));
            moveIndicator(MathUtils.random(-20,20));
            spawnTime = 0;
        }
        checkTemperature();
//        if (spawnTime > 2) {
//            boxObject.add(new Box(worldController));
//            spawnTime = 0;
//        }
//        for (int i = 0; i < boxObject.size(); i++) {
//            boxObject.get(i).update(elapsedTime);
//        }
        //  box.update(elapsedTime);
    }

    @Override
    public void arcadeButtonControllers(int buttonIndex) {
        switch (buttonIndex){
            case 0:
                speedIncrease.buttonFuction();
                break;
            case 1:
                speedDecrease.buttonFuction();
                break;
            case 3:
                increase.buttonFuction();
                break;
            case 4:
                decrease.buttonFuction();
                break;
        }
    }

    @Override
    public void arcadeAxis(char axis, int value) {

    }

    @Override
    public void init() {
        initialSpeedX = Constants.WIDTH_RATIO * 3;
        barDimension = Constants.WIDTH_RATIO * 3;

        initialIndicator = Constants.WIDTH_RATIO * -3;
        temperatureBarPosition = Constants.WIDTH_RATIO * -4.5f;

        indicatorDimension = Constants.WIDTH_RATIO * 0.5f;
        speedBarPosition = Constants.WIDTH_RATIO*1.5f;

        x = 0;
        currentSpeed = 0;

        increase = new BaseButton(Assets.getInstance().blueButton,"",worldController,Constants.dimension(-2,-4),Constants.dimension(1,1)) {
            @Override
            public void buttonFuction() {
                moveIndicator(move);
            }
        };
        decrease = new BaseButton(Assets.getInstance().whiteButton,"",worldController,Constants.dimension(-4,-4),Constants.dimension(1,1)) {
            @Override
            public void buttonFuction() {
                moveIndicator(-move);
            }
        };
        speedIncrease = new BaseButton(Assets.getInstance().blueButton,"",worldController,Constants.dimension(4,-4),Constants.dimension(1,1)) {
            @Override
            public void buttonFuction() {
                moveSpeedometer(speed);
            }
        };
        speedDecrease = new BaseButton(Assets.getInstance().whiteButton,"",worldController,Constants.dimension(2,-4),Constants.dimension(1,1)) {
            @Override
            public void buttonFuction() {
                moveSpeedometer(-speed);
            }
        };
    }

    public void checkCollision() {
        for (int i = 0; i < boxObject.size(); i++) {
            Box currentBox = boxObject.get(i);
            if (currentBox.getBounds().overlaps(box.getBounds())) {
                if (!(currentBox.position.y > box.position.y + box.dimension.y * 4 / 5 &&
                        currentBox.position.x > box.position.x &&
                        currentBox.position.x + currentBox.dimension.x < box.position.x + box.dimension.x)) {
                    worldController.currentScore -= 20;
                }
                boxObject.remove(i);
            }
        }
    }


}

