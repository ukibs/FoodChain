package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class WorldController {

	Vector2 currentTouch;

	public enum GameMode{

		MainMenu,
		Harvest,
		Restaurant,
		Transport,
		Supermarket
	}

	MainMenu menu;
	Harvest harvestLevel;

	int state = 0;

	public OrthographicCamera camera;

	public GameMode gameMode;

	public WorldController()
	{
		currentTouch = new Vector2(-1,-1);
		gameMode = GameMode.MainMenu;
	}

	void init(){
		menu = new MainMenu(this);
		harvestLevel = new Harvest();
	}

	public void update(float delta) {
		switch (gameMode){
			case MainMenu:
				menu.update(delta);
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
		currentTouch = new Vector2(-1, -1);
	}

	void InitiateLevel(){
		switch (gameMode){
			case MainMenu:
				state = 0;
				break;
			case Harvest:
				state = 1;
				harvestLevel.init(this);
				break;
			case Restaurant:

				break;
			case Transport:

				break;
			case Supermarket:

				break;
		}
	}

	void setTouch(float x, float y)
	{
		currentTouch = new Vector2(x,y);
	}

	public Vector2 getTouch(){
		return currentTouch;
	}

	public void finishLevel(){

	}
}
