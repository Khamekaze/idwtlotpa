package com.khamekaze.idontwanttoliveonthisplanetanymore.points;

import java.math.BigInteger;

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
	private int divider = 100;
	private int addVel = 0;
	private int subVel = 0;
	
	private BitmapFont udFont, totalFont, cdFont;
	
	private Game game;
	
	public ScoreManager(Game game) {
		totalDistance = 0;
		unusedDistance = 16000000;
		this.game = game;
		udFont = new BitmapFont();
		totalFont = new BitmapFont();
		cdFont = new BitmapFont();
	}
	
	public void update() {
		currentDistance = (currentPosition / divider);
		if(currentDistance >= 100000) {
			System.out.println("SPACE!");
		}
	}
	
	public void calculateScore() {
		unusedDistance += 1;
		totalDistance += 1;
	}
	
	public void render(SpriteBatch sb) {
		udFont.draw(sb, "UNUSED BOOSTS: " + String.valueOf(unusedDistance), MainGame.WIDTH / 2, MainGame.HEIGHT - 50);
		totalFont.draw(sb, "LIFETIME BOOSTS: " + String.valueOf(totalDistance), MainGame.WIDTH / 2, MainGame.HEIGHT - 100);
		cdFont.draw(sb, "DISTANCE TRAVELED: " + String.valueOf(currentDistance) + "m", MainGame.WIDTH / 2, MainGame.HEIGHT - 150);
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
	
	public void setCurrentPosition(int position) {
		this.currentPosition = position;
	}

}
