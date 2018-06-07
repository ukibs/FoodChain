package com.mygdx.game.Singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Assets {

	static Assets instance;

	//Bases
	public Texture button;
	public Texture header;
	public Texture[] wastedBar;
	public Texture board;
	public Texture win;
	public Texture lose;

	//Menu
	public Texture menuBackground;
	public Texture score;

	//Harvest
	public Texture[] fruitHarvest;
	public Texture basket;
	public Texture harvestBackground;

	//Transport
	public Texture truck;
	public Texture background;
	public Texture temperature;
	public Texture indicator;
	public Texture speedometer;
	public Texture blueButton;
	public Texture whiteButton;

	//Restaurant
	public Texture[] faces;
	public Texture[] food;
	public Texture[] clients;
	public Texture think;
	public Texture restaurantBackground;

	// Supermarket
	public Texture shelf;
	public Texture[][] fruitPacks;
	public Texture superBackground;

	//Victory
	public Texture victoryBackground;

	//Controls
	public Texture transportArcadeControls;
	public Texture restaurantArcadeControls;

	private Assets() {
		//
		header = new Texture(Gdx.files.internal("header.jpg"));
		wastedBar = new Texture[2];
		wastedBar[0] = new Texture(Gdx.files.internal("barFood.png"));
		wastedBar[1] = new Texture(Gdx.files.internal("waste.jpg"));
		board = new Texture(Gdx.files.internal("board.png"));
		win = new Texture("win.png");
		lose = new Texture("lose.png");
		//
		button = new Texture(Gdx.files.internal("button.png"));
		//
		menuBackground = new Texture(Gdx.files.internal("menuBackground.png"));
		score = new Texture(Gdx.files.internal("score.png"));
		//
		basket = new Texture(Gdx.files.internal("basket.png"));
		fruitHarvest = new Texture[4];
		fruitHarvest[0] = new Texture(Gdx.files.internal("Fruit/apple.png"));
		fruitHarvest[1] = new Texture(Gdx.files.internal("Fruit/lemon.png"));
		fruitHarvest[2] = new Texture(Gdx.files.internal("Fruit/grape.png"));
		fruitHarvest[3] = new Texture(Gdx.files.internal("Fruit/watermelon.png"));
		harvestBackground = new Texture(Gdx.files.internal("harvestBackground.jpg"));
		//
		truck = new Texture(Gdx.files.internal("Transport/truck.png"));
		background = new Texture(Gdx.files.internal("Transport/background.png"));
		temperature = new Texture(Gdx.files.internal("Transport/temperature.png"));
		indicator = new Texture(Gdx.files.internal("Transport/indicator.png"));
		blueButton = new Texture(Gdx.files.internal("Transport/blue_button.png"));
		whiteButton = new Texture(Gdx.files.internal("Transport/white_button.png"));
		speedometer = new Texture(Gdx.files.internal("Transport/speedometer.png"));
		//
		faces = new Texture[4];
		faces[0] = new Texture(Gdx.files.internal("Clients/HappyFace.png"));
		faces[1] = new Texture(Gdx.files.internal("Clients/LittleConvicedFace.png"));
		faces[2] = new Texture(Gdx.files.internal("Clients/RetardFace.png"));
		faces[3] = new Texture(Gdx.files.internal("Clients/ScaredFace.png"));
		//
		food = new Texture[5];
		food[0] = new Texture(Gdx.files.internal("Food/hamburguer.png"));
		food[1] = new Texture(Gdx.files.internal("Food/caracoles.png"));
		food[2] = new Texture(Gdx.files.internal("Food/meat.png"));
		food[3] = new Texture(Gdx.files.internal("Food/pizza.png"));
		food[4] = new Texture(Gdx.files.internal("Food/box.png"));
		//
		clients = new Texture[4];
		clients[0] = new Texture(Gdx.files.internal("Clients/client1.png"));
		clients[1] = new Texture(Gdx.files.internal("Clients/client2.png"));
		clients[2] = new Texture(Gdx.files.internal("Clients/client3.png"));
		clients[3] = new Texture(Gdx.files.internal("Clients/client4.png"));
		//
		think = new Texture(Gdx.files.internal("Clients/think.png"));
		//
		restaurantBackground = new Texture(Gdx.files.internal("restaurantBackground.jpg"));
		// Supermarket
		//Shelf
		shelf = new Texture(Gdx.files.internal("FruitShelves/Shelf.png"));
		// Fruitpacks
		fruitPacks = new Texture[5][6];
		// Bananas
		fruitPacks[0][0] = new Texture("FruitShelves/Bananas0.png");
		fruitPacks[0][1] = new Texture("FruitShelves/Bananas1.png");
		fruitPacks[0][2] = new Texture("FruitShelves/Bananas2.png");
		fruitPacks[0][3] = new Texture("FruitShelves/Bananas3.png");
		fruitPacks[0][4] = new Texture("FruitShelves/Bananas4.png");
		fruitPacks[0][5] = new Texture("FruitShelves/Bananas5.png");
		// Apples
		fruitPacks[1][0] = new Texture("FruitShelves/Apples0.png");
		fruitPacks[1][1] = new Texture("FruitShelves/Apples1.png");
		fruitPacks[1][2] = new Texture("FruitShelves/Apples2.png");
		fruitPacks[1][3] = new Texture("FruitShelves/Apples3.png");
		fruitPacks[1][4] = new Texture("FruitShelves/Apples4.png");
		fruitPacks[1][5] = new Texture("FruitShelves/Apples5.png");
		// Carrots
		fruitPacks[2][0] = new Texture("FruitShelves/Carrot0.png");
		fruitPacks[2][1] = new Texture("FruitShelves/Carrot1.png");
		fruitPacks[2][2] = new Texture("FruitShelves/Carrot2.png");
		fruitPacks[2][3] = new Texture("FruitShelves/Carrot3.png");
		fruitPacks[2][4] = new Texture("FruitShelves/Carrot4.png");
		fruitPacks[2][5] = new Texture("FruitShelves/Carrot5.png");
		// Tomatoes
		fruitPacks[3][0] = new Texture("FruitShelves/Tomatoes0.png");
		fruitPacks[3][1] = new Texture("FruitShelves/Tomatoes1.png");
		fruitPacks[3][2] = new Texture("FruitShelves/Tomatoes2.png");
		fruitPacks[3][3] = new Texture("FruitShelves/Tomatoes3.png");
		fruitPacks[3][4] = new Texture("FruitShelves/Tomatoes4.png");
		fruitPacks[3][5] = new Texture("FruitShelves/Tomatoes5.png");
		// Grapes
		fruitPacks[4][0] = new Texture("FruitShelves/Grapes0.png");
		fruitPacks[4][1] = new Texture("FruitShelves/Grapes1.png");
		fruitPacks[4][2] = new Texture("FruitShelves/Grapes2.png");
		fruitPacks[4][3] = new Texture("FruitShelves/Grapes3.png");
		fruitPacks[4][4] = new Texture("FruitShelves/Grapes4.png");
		fruitPacks[4][5] = new Texture("FruitShelves/Grapes5.png");
		// Background
		superBackground = new Texture("superBackground.jpg");

		//Victory
		victoryBackground = new Texture("victoryBackground.png");

		//Controls
		transportArcadeControls = new Texture("Controls/transportArcadeControls.png");
		restaurantArcadeControls = new Texture("Controls/restaurantControlsArcade.png");

		//
		Gdx.app.debug("ASSETS", "Assets created");
	}

	public static Assets getInstance() {

		if(instance == null)
			instance = new Assets();

		return instance;

	}
}
