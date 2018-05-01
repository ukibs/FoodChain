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

        if(Gdx.input.isTouched())
        {
            Vector3 pos = new Vector3();
            pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            worldController.camera.unproject(pos);
            worldController.setLongTouch(pos.x, pos.y);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        //processInput
        float speed = Constants.SPRITE_SPEED;
        //man.setSpeed(0,0);
        if(keycode == (com.badlogic.gdx.Input.Keys.A)) {
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.D)) {
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
        float speed = Constants.SPRITE_SPEED;

        if(keycode == (com.badlogic.gdx.Input.Keys.A)) {
            return true;
        }
        if(keycode == (com.badlogic.gdx.Input.Keys.D)) {
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
        //worldController.setTouch(screenX, screenY);
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
        worldController.setTouch(screenX, screenY);
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
