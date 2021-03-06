package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Martin on 29/04/2018.
 */

public class Input implements InputProcessor {

    private WorldController worldController;

    Input(WorldController worldController){
        this.worldController = worldController;

        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
    }

    public void update(float delta) {

    }

    @Override
    public boolean keyDown(int keycode) {
        //processInput
        if(keycode == (com.badlogic.gdx.Input.Keys.A)) {
            worldController.axisMoved('x', -1);
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.D)) {
            worldController.axisMoved('x', 1);
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.W)) {
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.S)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == (com.badlogic.gdx.Input.Keys.A)) {
            worldController.axisMoved('x', 0);
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.D)) {
            worldController.axisMoved('x', 0);
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.W)) {
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.S)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 pos = new Vector3();
        pos.set(screenX, screenY, 0);
        worldController.camera.unproject(pos);
        worldController.setTouch(pos.x, pos.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
       // worldController.setTouch(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
