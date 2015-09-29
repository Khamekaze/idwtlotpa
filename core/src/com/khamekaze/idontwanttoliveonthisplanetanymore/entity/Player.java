package com.khamekaze.idontwanttoliveonthisplanetanymore.entity;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;

public class Player {
	private Texture shipTexture = new Texture("spaceship.png");
	private Sprite shipSprite;
	private int currentDistance = 0;
	private int x, y;
	private int startY;
	private int width, height;
	private boolean boosting = false;
	private Texture boostFrameOne, boostFrameTwo, boostFrameThree, boostFrameFour, boostFrameFive;
	private Sprite spriteBoostOne, spriteBoostTwo, spriteBoostThree, spriteBoostFour, spriteBoostFive;
	private Animation boosterAnim;
	private Random rand = new Random();
	
	private float time;
	
	private int velocity = 0;
	
	private boolean grounded = true;
	
	public Player() {
		shipSprite = new Sprite(shipTexture);
		shipSprite.setSize(100, 120);
		shipSprite.setPosition(MainGame.WIDTH / 2 - 50, 120);
		x = (int) shipSprite.getX();
		y = (int) shipSprite.getY();
		startY = 120;
		width = (int) shipSprite.getWidth();
		height = (int) shipSprite.getHeight();
		
		boostFrameOne = new Texture("booster1.png");
		spriteBoostOne = new Sprite(boostFrameOne);
		boostFrameTwo = new Texture("booster2.png");
		spriteBoostTwo = new Sprite(boostFrameTwo);
		boostFrameThree = new Texture("booster3.png");
		spriteBoostThree = new Sprite(boostFrameThree);
		boostFrameFour = new Texture("booster4.png");
		spriteBoostFour = new Sprite(boostFrameFour);
		boostFrameFive = new Texture("booster5.png");
		spriteBoostFive = new Sprite(boostFrameFive);
		
		spriteBoostOne.setSize(50, 85);
		spriteBoostTwo.setSize(50, 85);
		spriteBoostThree.setSize(50, 85);
		spriteBoostFour.setSize(50, 85);
		spriteBoostFive.setSize(50, 85);
		
		boosterAnim = new Animation(1/30f, new TextureRegion(spriteBoostOne.getTexture()), new TextureRegion(spriteBoostTwo.getTexture()),
									new TextureRegion(spriteBoostThree.getTexture()), new TextureRegion(spriteBoostFour.getTexture()),
									new TextureRegion(spriteBoostFive.getTexture()));
		boosterAnim.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	public void update() {
		
		shipSprite.setX(MainGame.WIDTH / 2 - 50);
		
		int changeX = rand.nextInt(4) - 2;
		int changeY = rand.nextInt(8) - 4;
		
		if(y < MainGame.HEIGHT / 2) {
			y += velocity;
			shipSprite.setY(y);
		} else if(velocity >= 350) {
			shipSprite.setY(MainGame.HEIGHT / 2);
		}
		
		if(velocity > 0) {
			boosting = true;
			if(currentDistance < 100000) {
				shipSprite.setX(shipSprite.getX() + changeX);
				shipSprite.setY(shipSprite.getY() + changeY);
			
				if(shipSprite.getY() < MainGame.HEIGHT / 2 && y >= MainGame.HEIGHT / 2) {
					shipSprite.setY(MainGame.HEIGHT / 2);
				} else if(shipSprite.getY() > MainGame.HEIGHT / 2 + 10 && y >= MainGame.HEIGHT / 2) {
					shipSprite.setY(MainGame.HEIGHT / 2);
				}
			}
		} else {
			shipSprite.setX(MainGame.WIDTH / 2 - 50);
			boosting = false;
		}
	}
	
	public void render(SpriteBatch sb) {
		if(boosting) {
			sb.draw(boosterAnim.getKeyFrame(time += Gdx.graphics.getDeltaTime()), shipSprite.getX() + 25, shipSprite.getY() - 50, 50, 50);
		}
		shipSprite.draw(sb);
	}
	
	public void setCurrentDistance(int distance) {
		this.currentDistance = distance;
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
