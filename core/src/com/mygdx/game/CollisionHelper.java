package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class CollisionHelper {

	
	public static boolean simpleCollision(Sprite s1,Sprite s2) {
		if(s1.getBoundingRectangle().overlaps(s2.getBoundingRectangle())) {
			
			return true;
		}
		return false;

		//equivalent to:
		//return s1.getBoundingRectangle().overlaps(s2.getBoundingRectangle());
	}

	public static boolean simpleCollision(GameObject s1,GameObject s2) {
		if(s1.getBounds().overlaps(s2.getBounds())) {

			return true;
		}
		return false;


		//equivalent to:
		//return s1.getBounds().overlaps(s2.getBounds());
	}
}
