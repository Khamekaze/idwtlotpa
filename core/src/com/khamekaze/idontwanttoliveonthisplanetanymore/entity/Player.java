package com.khamekaze.idontwanttoliveonthisplanetanymore.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;

public class Player {
	private Texture shipTexture = new Texture("spaceship.png");
	private Sprite shipSprite;
	private int currentDistance = 0;
	private int x, y;
	private int startY;
	private int width, height;
	
	private int velocity = 0;
	
	private boolean grounded = true;
	
	public Player() {
		shipSprite = new Sprite(shipTexture);
		shipSprite.setSize(68, 120);
		shipSprite.setPosition(MainGame.WIDTH / 2 - 34, 120);
		x = (int) shipSprite.getX();
		y = (int) shipSprite.getY();
		startY = (int) shipSprite.getY();
		width = (int) shipSprite.getWidth();
		height = (int) shipSprite.getHeight();
	}
	
	public void update() {
		if(y < MainGame.HEIGHT / 2) {
			y += velocity;
			shipSprite.setY(y);
		}
	}
	
	public void render(SpriteBatch sb) {
		shipSprite.draw(sb);
	}
	
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public boolean getGrounded() {
		return grounded;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(float x) {
		this.x = (int) x;
		shipSprite.setX(x); 
	}
	
	public int getX() {
		return x;
	}

}
