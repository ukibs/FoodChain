package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Assets {


	public Texture[] fruitHarvest;
	public Texture basket;
	public Texture harvestBackground;
	public Texture button;
	public Texture[] faces;
	public Texture[] food;
	public Texture restaurantBackground;
	static Assets instance;
	public Texture header;
	public Texture[] wastedBar;
	// Supermarket
	public Texture shelf;
	public Texture[][] fruitPacks;
	
	private Assets() {
		//
		header = new Texture(Gdx.files.internal("header.jpg"));
		wastedBar = new Texture[2];
		wastedBar[0] = new Texture(Gdx.files.internal("barFood.png"));
		wastedBar[1] = new Texture(Gdx.files.internal("waste.jpg"));
		//
		button = new Texture(Gdx.files.internal("button.png"));
		//
		basket = new Texture(Gdx.files.internal("basket.png"));
		fruitHarvest = new Texture[4];
		fruitHarvest[0] = new Texture(Gdx.files.internal("Fruit/apple.png"));
		fruitHarvest[1] = new Texture(Gdx.files.internal("Fruit/lemon.png"));
		fruitHarvest[2] = new Texture(Gdx.files.internal("Fruit/grape.png"));
		fruitHarvest[3] = new Texture(Gdx.files.internal("Fruit/watermelon.png"));
		harvestBackground = new Texture(Gdx.files.internal("harvestBackground.jpg"));
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
		//
		Gdx.app.debug("ASSETS", "Assets created");
	}
	
	public static Assets getInstance() {
		
		if(instance == null)
			instance = new Assets();
		
		return instance;
		
	}
	
	public Texture GetRandomFace(){
		return faces[MathUtils.random(faces.length - 1)];
	}

}
