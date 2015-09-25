package com.khamekaze.idontwanttoliveonthisplanetanymore.points;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.game.Game;

public class ScoreManager {
	
	private int totalDistance;
	private int unusedDistance;
	private int currentDistance = 0;
	private int currentPosition = 0;
	private int addVel = 0;
	private int subVel = 0;
	
	private BitmapFont udFont, totalFont, cdFont;
	
	private Game game;
	
	public ScoreManager(Game game) {
		totalDistance = 0;
		unusedDistance = 0;
		this.game = game;
		udFont = new BitmapFont();
		totalFont = new BitmapFont();
		cdFont = new BitmapFont();
	}
	
	public void calculateScore() {
		if(Gdx.input.justTouched()) {
			unusedDistance += 1;
			totalDistance += 1;
		}
		
		currentDistance = (currentPosition / 100);
	} 
	
	public void update() {
		calculateScore();
	}
	
	public void render(SpriteBatch sb) {
		udFont.draw(sb, String.valueOf(unusedDistance), MainGame.WIDTH / 2, MainGame.HEIGHT - 50);
		totalFont.draw(sb, String.valueOf(totalDistance), MainGame.WIDTH / 2, MainGame.HEIGHT - 100);
		cdFont.draw(sb, String.valueOf(currentDistance), MainGame.WIDTH / 2, MainGame.HEIGHT - 150);
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getUnusedDistance() {
		return unusedDistance;
	}

	public void setUnusedDistance(int unusedDistance) {
		this.unusedDistance = unusedDistance;
	}
	
	public int getcCurrentDistance() {
		return currentDistance;
	}
	
	public void setCurrentDistance(int distance) {
		this.currentDistance = distance;
	}
	
	public void setCurrentPosition(int position) {
		this.currentPosition = position;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}

}
