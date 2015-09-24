package com.khamekaze.idontwanttoliveonthisplanetanymore.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.idontwanttoliveonthisplanetanymore.entity.Player;
import com.khamekaze.idontwanttoliveonthisplanetanymore.input.InputManager;

public class Game {
	
	private Player player;
	private Texture bg;
	private Sprite bgSprite;
	
	public Game() {
		
		player = new Player();
		bg = new Texture("bg.png");
		bgSprite = new Sprite(bg);
		bgSprite.setSize(bg.getWidth(), bg.getHeight());
		bgSprite.setPosition(0, 0);
	}
	
	public void update(InputManager inputManager) {
		player.update();
		handleInput(inputManager);
		applyGravity();
		
	}
	
	public void render(SpriteBatch sb) {
		bgSprite.draw(sb);
		player.render(sb);
	}
	
	public void handleInput(InputManager inputManager) {
		if(inputManager.checkIfPressed()) {
			if(player.getGrounded())
				player.setY(player.getStartY() + 1);
			player.setGrounded(false);
			player.setVelocity(50);
			System.out.println("CLICKED");
		}
	}
	
	public void applyGravity() {
		if(!player.getGrounded()) {
			if(player.getVelocity() < -150)
				player.setVelocity(-150);

			else
				player.setVelocity(player.getVelocity() - 1);

			if(player.getY() <= player.getStartY()) {
				player.setVelocity(0);
				player.setY(player.getStartY());
				player.setGrounded(true);
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}

}
