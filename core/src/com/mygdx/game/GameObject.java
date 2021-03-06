package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	
	public Vector2 position;
	public Vector2 dimension;
	
	Vector2 origin;
	float rotation;
	
	Vector2 speed;
	private Rectangle bounds;

	public boolean active = true;

	public GameObject(){
		bounds = new Rectangle();
	}

	public Rectangle getBounds(){
		bounds.set(position.x,position.y,dimension.x,dimension.y);
		return bounds;
	}
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void update(float elpasedTime);
	
	
	
}
	
