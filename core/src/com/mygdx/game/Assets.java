package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Assets {


	ArrayList<Texture> fruitHarvest;
	static Assets instance;

	
	private Assets() {
		

		
		Gdx.app.debug("ASSETS", "Assets created");


		
	}
	
	public static Assets getInstance() {
		
		if(instance == null)
			instance = new Assets();
		
		return instance;
		
	}
	
	
}
