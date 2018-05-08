package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Constants {

	public final static int SPRITE_SPEED = 80;

	public final static float LEVEL_TIME = 10;
	
	public final static float VIEWPORT_WIDTH = 10;
	public final static float VIEWPORT_HEIGTH = 10;
	
	public final static float WIDTH_RATIO = Gdx.graphics.getWidth()/10;
	public final static float HEIGHT_RATIO = Gdx.graphics.getHeight()/10;

	public static Vector2 dimension(float x, float y)
	{
		return new Vector2(WIDTH_RATIO*x, HEIGHT_RATIO*y);
	}
}
