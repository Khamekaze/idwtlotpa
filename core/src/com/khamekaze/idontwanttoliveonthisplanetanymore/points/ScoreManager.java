package com.khamekaze.idontwanttoliveonthisplanetanymore.points;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
	private int boostsPerSecond = 0;
	
	private BitmapFont udFont, boostsPerSecondFont, cdFont, mpsFont;
	private GlyphLayout layout;
	
	private Game game;
	
	public ScoreManager(Game game) {
		totalDistance = 0;
		unusedDistance = 0;
		this.game = game;
		udFont = new BitmapFont();
		boostsPerSecondFont = new BitmapFont();
		cdFont = new BitmapFont();
		mpsFont = new BitmapFont();
		cdFont.getData().setScale(2, 2);
		cdFont.setColor(Color.valueOf("f12e2e"));
		udFont.getData().setScale(2, 2);
		udFont.setColor(Color.valueOf("f12e2e"));
		boostsPerSecondFont.getData().setScale(1.5f, 1.5f);
		boostsPerSecondFont.setColor(Color.valueOf("f12e2e"));
		mpsFont.getData().setScale(1.5f, 1.5f);
		mpsFont.setColor(Color.valueOf("f12e2e"));
		layout = new GlyphLayout();
	}
	
	public void update() {
		currentDistance =(currentPosition / divider);
		game.getPlayer().setCurrentDistance(currentDistance);
		if(game.getUpgradesGUI().getUpgradeMenu().getTotalDistanceFromUpgrades() > 0) {
			boostsPerSecond = (int) (game.getUpgradesGUI().getUpgradeMenu().getTotalDistanceFromUpgrades() /
					game.getUpgradesGUI().getUpgradeMenu().getDelay());
		}
		if(currentDistance >= 100000) {
			
		}
	}
	
	public void calculateScore() {
		unusedDistance += 1;
		totalDistance += 1;
	}
	
	public void render(SpriteBatch sb) {
		layout.setText(udFont, String.valueOf(unusedDistance));
		udFont.draw(sb, String.valueOf(unusedDistance), MainGame.WIDTH - layout.width - 20, MainGame.HEIGHT - 20);
		layout.setText(boostsPerSecondFont, String.valueOf(boostsPerSecond) + "E/s");
		boostsPerSecondFont.draw(sb, String.valueOf(boostsPerSecond) + "E/s", MainGame.WIDTH - layout.width - 20, MainGame.HEIGHT - 112);
		cdFont.draw(sb, String.valueOf(currentDistance) + "m", 20, MainGame.HEIGHT - 20);
		layout.setText(mpsFont, String.valueOf(game.getMetersPerSecond()));
		mpsFont.draw(sb, String.valueOf((int) game.getMetersPerSecond()) + "M/s", 20, MainGame.HEIGHT - 112);
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
	
	public void setBoostsPerSecond(int boosts) {
		this.boostsPerSecond = boosts;
	}

}
