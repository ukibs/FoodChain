package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;



public class WorldController {

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

}
