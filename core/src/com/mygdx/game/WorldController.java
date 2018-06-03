package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EndLevel.EndLevel;
import com.mygdx.game.Harvest.Harvest;
import com.mygdx.game.RestaurantCode.Restaurant;
import com.mygdx.game.SuperMarket.Supermarket;

import java.util.ArrayList;


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
	public int currentScore = 100;

	public int level;

	public OrthographicCamera camera;

	public GameMode gameMode;

	public ArrayList<String> scoreNames = new ArrayList<String>(5);
	public int[] scores = {5,4,3,2,1};

	private Preferences prefs;

	public WorldController()
	{
		currentTouch = new Vector2(-1,-1);
		gameMode = GameMode.MainMenu;

		prefs = Gdx.app.getPreferences("My Preferences");
		level = prefs.getInteger("level");
	}

	void init(){
		menu = new MainMenu(this);
		harvestLevel = new Harvest();
		restaurantLevel = new Restaurant();
		supermarketLevel = new Supermarket();
		endLevel = new EndLevel();
		//
		for(int i = 0; i < 5; i++)
			scoreNames.add("AAA");
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
			if(!win) {
				if(level != 0) level--;
				prefs.putInteger("level", level);
			}
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
					level++;
					prefs.putInteger("level", level);
					InitiateLevel();
					break;
			}
		}
	}

	public void saveScore(String playerName){
		//
		int i;
		for(i = 0; i < scores.length; i++){
			if(currentScore > scores[i]){
				break;
			}
		}
		//
		int[] newScores = new int[5];
		for(int j = 0; j < 5; j++){
			if(j == i)
				newScores[j] = currentScore;
			else if(j > i)
				newScores[j] = scores[j-1];
			else
				newScores[j] = scores[j];
		}
		//
		scoreNames.add(i, playerName);
		scores = newScores;
	}

	public void buttonClicked(int buttonCode)
	{
		if(buttonCode == 9)
		{
			Gdx.app.exit();
		}

		switch (gameMode) {
			case Harvest:
				harvestLevel.arcadeButtonControllers(buttonCode);
				break;
			case Transport:
				break;
			case Restaurant:
				restaurantLevel.arcadeButtonControllers(buttonCode);
				break;
			case Supermarket:
				supermarketLevel.arcadeButtonControllers(buttonCode);
				break;
			case End:
				endLevel.arcadeButtonControllers(buttonCode);
				break;
		}
	}

	public void axisMoved(char axis, int value)
	{
		switch (gameMode) {
			case Harvest:
				harvestLevel.arcadeAxis(axis, value);
				break;
			case Transport:
				break;
			case Supermarket:
				supermarketLevel.arcadeAxis(axis, value);
				break;
			case End:
				endLevel.arcadeAxis(axis, value);
				break;
		}
	}
}
