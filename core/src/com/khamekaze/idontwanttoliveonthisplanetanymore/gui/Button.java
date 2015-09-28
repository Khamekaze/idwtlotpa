package com.khamekaze.idontwanttoliveonthisplanetanymore.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;

public class Button {

	private Sprite sprite;
	private Texture texture;
	private int width, height;
	private int x, y;
	private Rectangle hitBox;
	
	public Button(String name) {
		texture = new Texture(name + ".png");
		sprite = new Sprite(texture);
		hitBox = new Rectangle();
	}
	
	public Button(String name, int x, int y) {
		texture = new Texture(name + ".png");
		sprite = new Sprite(texture);
		this.x = x;
		this.y = y;
		hitBox = new Rectangle();
	}
	
	public Button(String name, int x, int y, int width, int height) {
		texture = new Texture(name + ".png");
		sprite = new Sprite(texture);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		sprite.setSize(width, height);
		sprite.setPosition(x, y);
		hitBox = new Rectangle((float) x, (float) y, (float) width, (float) height);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		hitBox.setX((float) x);
		hitBox.setY((float) y);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		sprite.setSize(width, height);
		hitBox.setWidth(width);
		hitBox.setHeight(height);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Rectangle getHitbox() {
		return hitBox;
	}
	
}
