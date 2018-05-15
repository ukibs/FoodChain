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
	public Texture button;
	public Texture[] faces;
	public Texture[] food;
	static Assets instance;
	public Texture header;
	public Texture[] wastedBar;

	
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
