package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {

	OrthographicCamera camera;
	SpriteBatch batch;
	WorldController controller;
	ShapeRenderer shape;
    FPSLogger fps;

	
	
	public WorldRenderer(WorldController controller) {
		
		this.controller = controller;
        batch = new SpriteBatch();
        fps = new FPSLogger();
        shape = new ShapeRenderer();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGTH);
		init();
	}
	
	private void init() {

		//batch.enableBlending();
		//batch.setBlendFunction(GL20.GL_SRC_ALPHA,  GL20.GL_ONE_MINUS_SRC_ALPHA );

		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set(0,0,0);
		camera.update();

	}
	
	public void render() {

        fps.log();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Line);
		shape.setColor(Color.RED);

		// Render de los game objects
		switch (controller.gameMode){
			case MainMenu:
				controller.menu.render(batch);
				break;
			case Harvest:
				controller.harvestLevel.render(batch);
				break;
			case Transport:

				break;
			case Restaurant:
				controller.restaurantLevel.render(batch);
				break;
			case Supermarket:
				controller.supermarketLevel.render(batch);
				break;
		}
		
		batch.end();
		shape.end();
			
	}
	
	public void resize(int width, int height) {
		
		//camera.viewportWidth = (Constants.VIEWPORT_HEIGTH/height) * width;
		//camera.update();
		
	}
	
	public void dispose() {
		batch.dispose();
	}
	
}
