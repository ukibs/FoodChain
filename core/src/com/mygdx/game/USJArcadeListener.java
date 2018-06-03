package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
/**
 * Created by usuario on 15/05/2018.
 */

public class USJArcadeListener implements ControllerListener {

    WorldController worldController;

    USJArcadeListener(WorldController wC){
        worldController = wC;
    }

    @Override
    public void connected(Controller controller) {
        //Do nothing
    }

    @Override
    public void disconnected(Controller controller) {
        //Do nothing
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        if(Controllers.getControllers().indexOf(controller,true) == 0) {
            worldController.buttonClicked(buttonCode);
            return true;
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        //Do nothing
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        float speed = Constants.SPRITE_SPEED;

        //Controller 1
        if(Controllers.getControllers().indexOf(controller,true) == 0){

            //y axis
            if(axisCode == 0){

                //up
                if(value == -1){
                    worldController.axisMoved('y', -1);
                    return true;
                }
                //down
                else if(value == 1){
                    worldController.axisMoved('y', 1);
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    worldController.axisMoved('y', 0);
                    return true;
                }

            }
            //x axis
            else if(axisCode == 1){

                //left
                if(value == -1){
                    worldController.axisMoved('x', -1);
                    return true;
                }
                //rigth
                else if(value == 1){
                    worldController.axisMoved('x', 1);
                    return true;
                }
                //center
                else if(value <0.1 && value>-0.1){
                    worldController.axisMoved('x', 0);
                    return true;
                }

            }

        }
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
