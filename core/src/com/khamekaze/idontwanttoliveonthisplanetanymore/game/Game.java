package com.khamekaze.idontwanttoliveonthisplanetanymore.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.entity.Player;
import com.khamekaze.idontwanttoliveonthisplanetanymore.input.InputManager;
import com.khamekaze.idontwanttoliveonthisplanetanymore.points.ScoreManager;

public class Game {
	
	private Player player;
	private Texture bg;
	private Sprite bgSprite;
	private ScoreManager scoreManager;
	private Vector2 origin, shipPos;
	
	public Game() {
		
		player = new Player();
		bg = new Texture("bg.png");
		bgSprite = new Sprite(bg);
		bgSprite.setSize(bg.getWidth(), bg.getHeight());
		bgSprite.setPosition(0, 0);
		scoreManager = new ScoreManager(this);
		shipPos = new Vector2();
		origin = new Vector2();
	}
	
	public void update(InputManager inputManager) {
		player.update();
		moveBackground();
		handleInput(inputManager);
		applyGravity();
		shipPos.set(MainGame.WIDTH / 2, player.getY());
		origin.set(MainGame.WIDTH / 2, bgSprite.getY());
		scoreManager.setCurrentPosition((int) (shipPos.y - origin.y));
		scoreManager.update();
	}
	
	public void render(SpriteBatch sb) {
		if(bgSprite.getY() > -bgSprite.getHeight() - 50)
			bgSprite.draw(sb);
		player.render(sb);
		scoreManager.render(sb);
	}
	
	public void handleInput(InputManager inputManager) {
		if(inputManager.checkIfPressed()) {
			if(player.getGrounded())
				player.setY(player.getStartY() + 1);
			player.setGrounded(false);
			player.setVelocity(20);
		}
	}
	
	public void applyGravity() {
		if(!player.getGrounded()) {
			if(player.getVelocity() < -50)
				player.setVelocity(-50);

			else
				player.setVelocity(player.getVelocity() - 1);

			if(player.getY() <= player.getStartY()) {
				player.setVelocity(0);
				player.setY(player.getStartY());
				player.setGrounded(true);
			}
		}
	}
	
	public void moveBackground() {
		if(player.getY() >= MainGame.HEIGHT / 2) {
			bgSprite.translateY(-player.getVelocity());
		}
		
		if(player.getVelocity() < 0 && bgSprite.getY() >= 0) {
			player.setY(player.getY() - 5);
			bgSprite.setY(0);
			player.setVelocity(-1);
		}
	}
	
	public Sprite getBackground() {
		return bgSprite;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ScoreManager getScoreManager() {
		return scoreManager;
	}

}
