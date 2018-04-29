package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class SpriteGenerator {

	public static Sprite createProceduralSprite() {
		
		Pixmap pm = new Pixmap(100, 100, Format.RGBA8888);
		
		pm.setColor(1, 0, 0, 0.5f);
		pm.fill();
		
		pm.setColor(0,1,0,0.5f);
		
		pm.fillRectangle(10, 10, 40, 40);
		
		Texture t = new Texture(pm);
		
		Sprite s = new Sprite(t);
		
		s.setX(2);
		s.setY(1);
		
		s.setSize(1, 1);
		
		s.setOriginCenter();
		
		return s;
	}
	
	
	public static Sprite createSpriteFromImage(Texture texture) {
		
		Sprite s = new Sprite(texture);
		
		s.setX(MathUtils.random(-5f,5f));
		s.setY(MathUtils.random(-5f,5f));
		
		s.setSize(MathUtils.random(0.5f, 2f), MathUtils.random(0.5f, 2f));
		
		s.setOriginCenter();
		
		return s;
	}
	
	
}
