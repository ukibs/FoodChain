package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


public class WorldController {

	Vector2 currentTouch;

	public enum GameMode{

		MainMenu,
		Harvest,
		Restaurant,
		Transport,
		Supermarket

	}

	public OrthographicCamera camera;

	public GameMode gameMode;

	public WorldController() {


	}

	void init(){

	}

	public void update(float delta) {
		currentTouch = new Vector2(-1, -1);
	}

	void InitiateLevel(){
		switch (gameMode){
			case MainMenu:

				break;
			case Harvest:

				break;
			case Restaurant:

				break;
			case Transport:

				break;
			case Supermarket:

				break;
		}
	}

	void setTouch(float x, float y){
		currentTouch = new Vector2(x,y);
	}

	public Vector2 getTouch(){
		return currentTouch;
	}

	void FinishLevel(){

	}
}
