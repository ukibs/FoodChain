package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EndLevel.EndLevel;
import com.mygdx.game.Harvest.Harvest;
import com.mygdx.game.RestaurantCode.Restaurant;
import com.mygdx.game.SuperMarket.Supermarket;


public class WorldController {

	Vector2 currentTouch;
	Vector2 longTouch;

	public enum GameMode{

		MainMenu,
		Harvest,
		Restaurant,
		Transport,
		Supermarket,
		End
	}

	MainMenu menu;
	Harvest harvestLevel;
	Restaurant restaurantLevel;
	Supermarket supermarketLevel;
	EndLevel endLevel;

	boolean inPractice = false;
	public float currentScore = 0;

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
		restaurantLevel = new Restaurant();
		supermarketLevel = new Supermarket();
		endLevel = new EndLevel();
	}

	public void update(float delta) {
		switch (gameMode){
			case MainMenu:
				menu.update(delta);
				break;
			case Harvest:
				harvestLevel.update(delta);
				break;
			case Transport:

				break;
			case Restaurant:
				restaurantLevel.update(delta);
				break;
			case Supermarket:
				supermarketLevel.update(delta);
				break;
			case End:
				endLevel.update(delta);
				break;
		}
		currentTouch = new Vector2(-100000, -10000000);
	}

	void InitiateLevel(){
		switch (gameMode){
			case MainMenu:
				menu.init();
				break;
			case Harvest:
				harvestLevel.baseInit(this);
				break;
			case Transport:

				break;
			case Restaurant:
				restaurantLevel.baseInit(this);
				break;
			case Supermarket:
				supermarketLevel.baseInit(this);
				break;
			case End:
				endLevel.baseInit(this);
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

	public void finishLevel(boolean win){
		if(inPractice || !win)
		{
			gameMode = GameMode.MainMenu;
			InitiateLevel();
		}
		else {
			switch (gameMode) {
				case Harvest:
					gameMode = GameMode.Restaurant;
					InitiateLevel();
					break;
				case Transport:

					break;
				case Restaurant:
					gameMode = GameMode.Supermarket;
					InitiateLevel();
					break;
				case Supermarket:
					gameMode = GameMode.End;
					InitiateLevel();
					break;
				case End:
					gameMode = GameMode.MainMenu;
					InitiateLevel();
					break;
			}
		}
	}
}
