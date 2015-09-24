package com.khamekaze.idontwanttoliveonthisplanetanymore.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class InputManager {
	
	private Rectangle mouseHitbox;
	private Vector3 input;
	
	public InputManager() {
		input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		mouseHitbox = new Rectangle(input.x, input.y, 10, 10);
	}
	
	public void update() {
		input.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		mouseHitbox.setX(input.x - 5);
		mouseHitbox.setY(input.y - 5);
	}
	
	public boolean checkIfPressed() {
		return Gdx.input.justTouched();
	}
	
	public boolean getIntersecting(Rectangle rect) {
		return mouseHitbox.overlaps(rect);
	}
	
	public Rectangle getMouseHitbox() {
		return mouseHitbox;
	}
}
