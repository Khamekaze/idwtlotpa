package com.khamekaze.idontwanttoliveonthisplanetanymore.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.khamekaze.idontwanttoliveonthisplanetanymore.gui.Button;
import com.khamekaze.idontwanttoliveonthisplanetanymore.gui.UpgradesGUI;
import com.khamekaze.idontwanttoliveonthisplanetanymore.screen.ScreenManager;

public class InputManager implements InputProcessor {
	
	private Rectangle mouseHitbox;
	private Vector3 input;
	
	public InputManager() {
		input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		mouseHitbox = new Rectangle(input.x, input.y, 10, 10);
	}
	
	public void update() {
		input.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		ScreenManager.getCurrentScreen().camera.unproject(input);
		mouseHitbox.setX(input.x - 5);
		mouseHitbox.setY(input.y - 5);
	}
	
	public boolean checkIfUpgradesMenuButtonPressed(UpgradesGUI gui) {
		boolean touched = false;
		for(Button b : gui.getButtons()) {
			if(Gdx.input.justTouched() && mouseHitbox.overlaps(b.getHitbox())) {
				
				touched = true;
			}
		}
		return touched;
	}
	
	public boolean checkIfMenuButtonPressed(UpgradesGUI gui, Rectangle rect) {
		boolean touched = false;
		
		if(Gdx.input.justTouched() && mouseHitbox.overlaps(rect)) {
			touched = true;
		}
		
		return touched;
	}
	
	public boolean checkIfGameFieldPressed(UpgradesGUI gui) {
		boolean touched = false;
		
		for(Button b : gui.getButtons()) {
			if(Gdx.input.justTouched() && !mouseHitbox.overlaps(b.getHitbox())) {
				touched = true;
			}
		}
		
		return touched;
	}
	
	public boolean getIntersecting(Rectangle rect) {
		return mouseHitbox.overlaps(rect);
	}
	
	public Rectangle getMouseHitbox() {
		return mouseHitbox;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
