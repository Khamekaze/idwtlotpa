package com.khamekaze.idontwanttoliveonthisplanetanymore.points;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private float alpha = 1.0f;
	private int energyPerSecX = MainGame.WIDTH / 2, energyPerSecY = MainGame.HEIGHT - 312;
	private int upwardsY = 0;
	
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
		cdFont.setColor(Color.valueOf("FFFFFF"));
		udFont.getData().setScale(2, 2);
		udFont.setColor(Color.valueOf("FFFFFF"));
		boostsPerSecondFont.getData().setScale(1.5f, 1.5f);
		boostsPerSecondFont.setColor(Color.valueOf("FFFFFF"));
		mpsFont.getData().setScale(1.5f, 1.5f);
		mpsFont.setColor(Color.valueOf("FFFFFF"));
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
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			unusedDistance = 999999999;
		}
	}
	
	public void calculateScore() {
		unusedDistance += 1;
		totalDistance += 1;
	}
	
	public void render(SpriteBatch sb) {
		layout.setText(udFont, String.valueOf(unusedDistance));
		udFont.draw(sb, String.valueOf(unusedDistance), MainGame.WIDTH - layout.width - 20, MainGame.HEIGHT - 20);
		
		layout.setText(cdFont, String.valueOf(currentDistance) + "m");
		cdFont.draw(sb, String.valueOf(currentDistance) + "m", 100 - (layout.width / 2), MainGame.HEIGHT - 45);
		
		layout.setText(mpsFont, String.valueOf((int) game.getMetersPerSecond()) + "M/s");
		mpsFont.draw(sb, String.valueOf((int) game.getMetersPerSecond()) + "M/s", 70 - (layout.width / 2), MainGame.HEIGHT - 187);
		
		if(game.getUpgradesGUI().getUpgradeMenu().getRenderEnergyPerSec()) {
			boostsPerSecondFont.setColor(1, 1, 1, alpha);
			boostsPerSecondFont.draw(sb, "+" + String.valueOf(boostsPerSecond), energyPerSecX, energyPerSecY + upwardsY);
			upwardsY += 1;
			alpha -= 0.05f;
			if(alpha < 0) {
				upwardsY = 0;
				alpha = 1;
				game.getUpgradesGUI().getUpgradeMenu().setRenderEnergyPerSec(false);
			}
		}
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
