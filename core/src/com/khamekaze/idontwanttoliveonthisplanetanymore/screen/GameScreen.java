package com.khamekaze.idontwanttoliveonthisplanetanymore.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.game.Game;

public class GameScreen extends Screen {
	
	private Game game;

	@Override
	public void create() {
		game = new Game();
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
	}

	@Override
	public void update() {
//		if(game.getPlayer().getY() > camera.viewportHeight / 2) {
//			camera.translate(0, game.getPlayer().getVelocity());
//			if(camera.position.y < camera.viewportHeight / 2)
//				camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
//			System.out.println("IN THE AIR");
//		} else if(game.getPlayer().getY() <= Gdx.graphics.getHeight() / 2) {
////			camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
//			System.out.println("GROUNDED");
//		}
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		game.update(inputManager);
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
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
