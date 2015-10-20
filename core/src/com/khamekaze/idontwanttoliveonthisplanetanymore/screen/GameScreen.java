package com.khamekaze.idontwanttoliveonthisplanetanymore.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.game.Game;

public class GameScreen extends Screen {
	
	private Game game;
	private ShapeRenderer renderer;
	private int skyY = 0;
	private Texture spaceText;

	@Override
	public void create() {
		game = new Game();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		Gdx.input.setInputProcessor(inputManager);
		renderer = new ShapeRenderer();
		System.out.println(Color.CYAN);
		spaceText = new Texture("spacetexture.png");
	}

	@Override
	public void update() {
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		inputManager.update();
		game.update(inputManager);
		skyY = (int) game.getBackground().getY() / 2000;
	}

	@Override
	public void render(SpriteBatch sb) {
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, MainGame.WIDTH, MainGame.HEIGHT, Color.valueOf("191f23"), Color.valueOf("191f23"), Color.valueOf("191f23"), Color.valueOf("191f23"));
		renderer.rect(0, skyY, MainGame.WIDTH, MainGame.HEIGHT * 8, Color.valueOf("b0d0e3"), Color.valueOf("b0d0e3"), Color.valueOf("191f23"), Color.valueOf("191f23"));
		renderer.end();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(spaceText, 0, 0, MainGame.WIDTH, MainGame.HEIGHT);
		game.render(sb);
		sb.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
	    camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
