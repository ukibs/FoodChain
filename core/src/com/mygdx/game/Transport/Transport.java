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

/**
 * Created by jimmy on 05/06/18.
 */

public class Transport extends BaseLevel {
    public static float move = 20;
    public static float speed = 20;

    float currentSpeed;
    float currentTemperature;

    float initialSpeedIndicator;
    float speedBarPosition;
    float barDimension;
    float indicatorDimension;
    float initialTemperatureIndicator;
    float temperatureBarPosition;

    float spawnTime;

    BaseButton increase;
    BaseButton decrease;
    BaseButton speedIncrease;
    BaseButton speedDecrease;

    Vector2 buttonDimension = new Vector2(Constants.WIDTH_RATIO * 1.5f, Constants.HEIGHT_RATIO * 1);

    @Override
    public void GUI(SpriteBatch batch) {
        batch.draw(Assets.getInstance().background, Constants.WIDTH_RATIO * -5, Constants.HEIGHT_RATIO * -5, Constants.WIDTH_RATIO * 10, Constants.HEIGHT_RATIO * 10);
        batch.draw(Assets.getInstance().truck,Constants.WIDTH_RATIO * -3, Constants.HEIGHT_RATIO * -3 , Constants.WIDTH_RATIO * 7, Constants.HEIGHT_RATIO* 7);
        //Temperature bar
        batch.draw(Assets.getInstance().temperature, temperatureBarPosition, Constants.HEIGHT_RATIO * -2, barDimension, Constants.HEIGHT_RATIO * 1);
        batch.draw(Assets.getInstance().indicator, initialTemperatureIndicator + currentTemperature, Constants.HEIGHT_RATIO* -3,indicatorDimension, Constants.HEIGHT_RATIO * 1);
        //Speed bar
        batch.draw(Assets.getInstance().speedometer,speedBarPosition, Constants.HEIGHT_RATIO * -2f, barDimension, Constants.HEIGHT_RATIO * 1);
        batch.draw(Assets.getInstance().indicator, initialSpeedIndicator + currentSpeed,Constants.HEIGHT_RATIO* -3,indicatorDimension, Constants.HEIGHT_RATIO * 1);

        speedDecrease.render(batch);
        speedIncrease.render(batch);
        increase.render(batch);
        decrease.render(batch);
    }
    private void moveIndicator(float value){
        if(initialTemperatureIndicator + currentTemperature + value > temperatureBarPosition &&
                initialTemperatureIndicator + currentTemperature + value < temperatureBarPosition + barDimension - indicatorDimension){
            currentTemperature += value;

        }
    }
    private void moveSpeedometer(float value) {
        if (initialSpeedIndicator + currentSpeed + value > speedBarPosition &&
                initialSpeedIndicator + currentSpeed + value < speedBarPosition + barDimension - indicatorDimension) {
            currentSpeed += value;
        }
    }
    private void checkTemperature(){
        if (initialTemperatureIndicator + currentTemperature + indicatorDimension/2 < temperatureBarPosition + (barDimension / 3)){
            worldController.currentScore -= 0.01;
        }
        if(initialTemperatureIndicator + currentTemperature  + indicatorDimension > temperatureBarPosition + (barDimension * 2/3)) {
            worldController.currentScore -= 0.01;
        }
    }

    @Override
    public void LevelUpdate(float elapsedTime) {
        this.elapsedTime += elapsedTime;
        spawnTime += elapsedTime;
        speedIncrease.update(elapsedTime);
        speedDecrease.update(elapsedTime);
        increase.update(elapsedTime);
        decrease.update(elapsedTime);
        if (spawnTime > 1f)
        {
            moveSpeedometer(MathUtils.random(-20 + 10 * worldController.level, 20 + 10 * worldController.level));
            moveIndicator(MathUtils.random(-20 + 10 * worldController.level, 20 + 10 * worldController.level));
            spawnTime = 0;
        }
        checkTemperature();
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
        barDimension = Constants.WIDTH_RATIO * 3;
        indicatorDimension = Constants.WIDTH_RATIO * 0.5f;

        speedBarPosition = Constants.WIDTH_RATIO*1.5f;
        temperatureBarPosition = Constants.WIDTH_RATIO * -4.5f;

        initialTemperatureIndicator = temperatureBarPosition + barDimension/2 - indicatorDimension/2;
        initialSpeedIndicator = speedBarPosition + barDimension/2 - indicatorDimension/2;

        currentTemperature = 0;
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
        speedIncrease = new BaseButton(Assets.getInstance().blueButton,"",worldController,Constants.dimension(3.5f,-4),Constants.dimension(1,1)) {
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
}

