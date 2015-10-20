package com.khamekaze.idontwanttoliveonthisplanetanymore.game;

import java.math.BigInteger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.entity.Player;
import com.khamekaze.idontwanttoliveonthisplanetanymore.gui.UpgradesGUI;
import com.khamekaze.idontwanttoliveonthisplanetanymore.input.InputManager;
import com.khamekaze.idontwanttoliveonthisplanetanymore.points.ScoreManager;
import com.khamekaze.idontwanttoliveonthisplanetanymore.upgrade.Upgrade;

public class Game {
	
	private Player player;
	private Texture bg;
	private Sprite bgSprite;
	private ScoreManager scoreManager;
	private Texture distanceHUDTexture, boostsHUDTexture, hudBgTexture, energyPSTexture, hudDistance;
	private Sprite distanceHUDSprite, boostsHUDSprite, hudBgSprite, energyPSSprite, mPSSprite, hudDistanceSprite;
	private Vector2 origin, shipPos;
	private BigInteger distance = new BigInteger("0");
	private BigInteger divider = new BigInteger("100");
	private int speed = 10;
	
	float startDistanceCheck = 0, endDistanceCheck = 0, metersPerSecond = 0, posStart = 0, endPos = 0;
	
	private ShapeRenderer renderer;
	
	private UpgradesGUI upgradesGUI;
	
	public Game() {
		
		player = new Player();
		bg = new Texture("bg.png");
		bgSprite = new Sprite(bg);
		bgSprite.setSize(MainGame.WIDTH, bg.getHeight());
		bgSprite.setPosition(0, 0);
		scoreManager = new ScoreManager(this);
		shipPos = new Vector2();
		origin = new Vector2();
		
		hudDistance = new Texture("hudDistance.png");
		hudDistanceSprite = new Sprite(hudDistance);
		hudDistanceSprite.setSize(250, 250);
		hudDistanceSprite.setPosition(-25, MainGame.HEIGHT - 175);
		
		energyPSTexture = new Texture("hudDistance.png");
		energyPSSprite = new Sprite(energyPSTexture);
		energyPSSprite.setSize(180, 180);
		energyPSSprite.setPosition(-25, MainGame.HEIGHT - hudDistanceSprite.getHeight() - 30);
		
		renderer = new ShapeRenderer();
		upgradesGUI = new UpgradesGUI();
	}
	
	public void update(InputManager inputManager) {
		player.update();
		moveBackground();
		handleInput(inputManager);
		applyGravity();
		shipPos.set(MainGame.WIDTH / 2, player.getY());
		origin.set(MainGame.WIDTH / 2, bgSprite.getY());
		
		upgradesGUI.update();
		
		scoreManager.setCurrentPosition((int) (shipPos.y - origin.y));
		
		scoreManager.update();
		
		applyForceFromUpgrades();
		
		startDistanceCheck += Gdx.graphics.getDeltaTime();
		
		if(startDistanceCheck > 1 && startDistanceCheck < 10) {
			posStart = shipPos.y - origin.y;
			startDistanceCheck = 10;
		}
		
		if(startDistanceCheck > 11 && startDistanceCheck < 11.3f) {
			endPos = shipPos.y - origin.y;
			metersPerSecond = endPos - posStart;
			metersPerSecond = metersPerSecond / 100;
			if(metersPerSecond < 0)
				metersPerSecond = 0;
			startDistanceCheck = 0;
		}
		
		
	}
	
	public void render(SpriteBatch sb) {
		if(bgSprite.getY() > -bgSprite.getHeight() - 50)
			bgSprite.draw(sb);
//		hudBgSprite.draw(sb);
//		mPSSprite.draw(sb);
//		distanceHUDSprite.draw(sb);
		
		energyPSSprite.draw(sb);
//		boostsHUDSprite.draw(sb);
		
		hudDistanceSprite.draw(sb);
		
		player.render(sb);
		scoreManager.render(sb);
		upgradesGUI.render(sb);
	}
	
	public void handleInput(InputManager inputManager) {
		if(inputManager.checkIfGameFieldPressed(upgradesGUI) && !upgradesGUI.getIsOpen()) {
			if(player.getGrounded())
				player.setY(player.getStartY() + 1);
			player.setGrounded(false);
			player.setVelocity(speed);
			scoreManager.calculateScore();
		} else if(inputManager.checkIfUpgradesMenuButtonPressed(upgradesGUI)) {
			if(upgradesGUI.getIsOpen()) {
				upgradesGUI.setOpen(false);
			} else if(!upgradesGUI.getIsOpen()) {
				upgradesGUI.setOpen(true);
			}
		}
		
		if(upgradesGUI.getIsOpen()) {
			for(Upgrade u : upgradesGUI.getUpgrades()) {
				if(inputManager.checkIfMenuButtonPressed(upgradesGUI, u.getIncreaseButton().getHitbox())) {
					if(scoreManager.getUnusedDistance() >= u.getValue()) {
						scoreManager.setUnusedDistance(scoreManager.getUnusedDistance() - (int) u.getValue());
						u.setAmount(u.getAmount() + 1);
						u.calculateValue();
					}
				} else if(inputManager.checkIfMenuButtonPressed(upgradesGUI, u.getDecreaseButton().getHitbox())) {
					if(u.getAmount() > 0) {
						scoreManager.setUnusedDistance(scoreManager.getUnusedDistance() + u.getSellValue());
						u.sell();
						u.setAmount(u.getAmount() - 1);
					}
				}
			}
		}
	}
	
	public void applyForceFromUpgrades() {
			if(upgradesGUI.getUpgradeMenu().getExcecuteEffect()) {
				scoreManager.setUnusedDistance(scoreManager.getUnusedDistance() + upgradesGUI.getUpgradeMenu().getTotalDistanceFromUpgrades());
				scoreManager.setTotalDistance(scoreManager.getTotalDistance() + upgradesGUI.getUpgradeMenu().getTotalDistanceFromUpgrades());
				if(player.getGrounded() && upgradesGUI.getUpgradeMenu().getVelocityFromUpgrades() > 0)
					player.setY(player.getStartY() + 1);
				player.setGrounded(false);
				player.setVelocity((int) upgradesGUI.getUpgradeMenu().getVelocityFromUpgrades());
				if(player.getY() > MainGame.HEIGHT / 2) {
					player.setY(MainGame.HEIGHT / 2);
				}
				upgradesGUI.getUpgradeMenu().setExcecuteEffect(false);
		}
	}
	
	public void applyGravity() {
		if(!player.getGrounded()) {
			if(player.getVelocity() < -50)
				player.setVelocity(-50);

			else
				player.setVelocity(player.getVelocity() - 1);

			if(player.getY() <= player.getStartY()) {
				player.setVelocity(0);
				player.setY(player.getStartY());
				player.setGrounded(true);
			}
		}
	}
	
	public void moveBackground() {
		if(player.getY() >= MainGame.HEIGHT / 2) {
			player.setY(MainGame.HEIGHT / 2);
			bgSprite.translateY(-player.getVelocity());
			
		}
		
		if(player.getVelocity() <= 0 && !player.getGrounded() && bgSprite.getY() >= 0) {
			player.setY(player.getY() - 5);
			bgSprite.setY(0);
			player.setVelocity(-1);
		}
	}
	
	public UpgradesGUI getUpgradesGUI() {
		return upgradesGUI;
	}
	
	public Sprite getBackground() {
		return bgSprite;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ScoreManager getScoreManager() {
		return scoreManager;
	}
	
	public float getMetersPerSecond() {
		return metersPerSecond;
	}

}
