package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Assets {


	ArrayList<Texture> fruitHarvest;
	public Texture button;
	public Texture[] faces;
	static Assets instance;

	
	private Assets() {
		//
		button = new Texture(Gdx.files.internal("button.png"));
		//
		faces = new Texture[4];
		faces[0] = new Texture(Gdx.files.internal("Clients/HappyFace.png"));
		faces[1] = new Texture(Gdx.files.internal("Clients/LittleConvicedFace.png"));
		faces[2] = new Texture(Gdx.files.internal("Clients/RetardFace.png"));
		faces[3] = new Texture(Gdx.files.internal("Clients/ScaredFace.png"));
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
