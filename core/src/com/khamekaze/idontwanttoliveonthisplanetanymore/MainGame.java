package com.khamekaze.idontwanttoliveonthisplanetanymore;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.idontwanttoliveonthisplanetanymore.screen.GameScreen;
import com.khamekaze.idontwanttoliveonthisplanetanymore.screen.ScreenManager;

public class MainGame extends ApplicationAdapter {
	
	public static int WIDTH = 480, HEIGHT = 700;
	
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().render(batch);
		
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().update();
		
	}
	
	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resize(width, height);
		
	}
	
	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();

	}
	
	@Override
	public void pause() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
		
	}
}
