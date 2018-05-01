package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class WorldController {

	Vector2 currentTouch;
	Vector2 longTouch;

	public enum GameMode{

		MainMenu,
		Harvest,
		Restaurant,
		Transport,
		Supermarket
	}

	MainMenu menu;
	Harvest harvestLevel;

	Supermarket supermarketLevel;

	float currentScore = 0;

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
		supermarketLevel = new Supermarket();
	}

	public void update(float delta) {
		switch (gameMode){
			case MainMenu:
				menu.update(delta);
				break;
			case Harvest:
				harvestLevel.update(delta);
				break;
			case Restaurant:

				break;
			case Transport:

				break;
			case Supermarket:
				supermarketLevel.update(delta);
				break;
		}
		currentTouch = new Vector2(-1, -1);
	}

	void InitiateLevel(){
		switch (gameMode){
			case MainMenu:
				menu.init();
				break;
			case Harvest:
				harvestLevel.baseInit(this);
				break;
			case Restaurant:

				break;
			case Transport:

				break;
			case Supermarket:
				supermarketLevel.baseInit(this);
				break;
		}
	}

	void setTouch(float x, float y)
	{
		currentTouch = new Vector2(x,y);
	}

	void setLongTouch(float x, float y)
	{
		longTouch = new Vector2(x, y);
	}

	public Vector2 getTouch(){
		return currentTouch;
	}

	public Vector2 longTouch()
	{
		return longTouch;
	}

	public void finishLevel(){
		switch (gameMode){
			case Harvest:
				gameMode = GameMode.Supermarket;
				InitiateLevel();
				break;
			case Restaurant:

				break;
			case Transport:

				break;
			case Supermarket:
				supermarketLevel.baseInit(this);
				break;
		}
	}
}
