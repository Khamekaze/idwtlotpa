package com.khamekaze.idontwanttoliveonthisplanetanymore.upgrade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;

public class UpgradeMenu {
	
	private Upgrade upgradeOne, upgradeTwo, upgradeThree, upgradeFour, upgradeFive;
	private Array<Upgrade> upgrades;
	private boolean isOpen = false;
	private Rectangle menuContainer;
	
	private float delay = 2f;
	private float gettingDelta = 0f;
	private boolean excecuteEffect = false;
	
	private int distancePerEx = 0;
	private int totalAmountOfUpgrades = 0;
	private int totalDistanceFromUpgrades = 0;
	
	private float totalSpeedFromUpgrades = 0;
	
	public UpgradeMenu() {
		upgrades = new Array<Upgrade>();
		upgradeOne = new Upgrade(10);
		upgradeOne.setDistancePerExcecution(1);
		upgradeTwo = new Upgrade(500);
		upgradeTwo.setDistancePerExcecution(2);
		upgradeThree = new Upgrade(25000);
		upgradeThree.setDistancePerExcecution(15);
		upgradeFour = new Upgrade(500000);
		upgradeFour.setDistancePerExcecution(100);
		upgradeFive = new Upgrade(2000000);
		upgradeFive.setDistancePerExcecution(1000);
		
		upgrades.add(upgradeOne);
		upgrades.add(upgradeTwo);
		upgrades.add(upgradeThree);
		upgrades.add(upgradeFour);
		upgrades.add(upgradeFive);
		
		menuContainer = new Rectangle();
		int menuWidth = upgradeOne.getWidth(), menuHeight = 0;
		for(Upgrade u : upgrades) {
			menuHeight += u.getHeight();
		}
		menuContainer.setSize(menuWidth, menuHeight);
		menuContainer.setPosition(MainGame.WIDTH - menuContainer.getWidth(), menuContainer.getHeight());
		
		for(int i = 0; i < 5; i++) {
			upgrades.get(i).setPosition((int) menuContainer.getX(),(int) menuContainer.getY() - upgrades.get(i).getHeight() * i);
		}
	}
	
	public void update() {
		
		gettingDelta += Gdx.graphics.getDeltaTime();
		
		excecuteEffect = false;
		
		float currentEffect = 0;
		distancePerEx = 0;
		totalAmountOfUpgrades = 0;
		totalSpeedFromUpgrades = 0;
		totalDistanceFromUpgrades = 0;
		
		for(Upgrade u : upgrades) {
			if(u.getAmount() > 0) {
				for(int i = 0; i < u.getAmount(); i++) {
					currentEffect += u.getEffect();
				}
			}
			
			if(u.getAmount() > 0) {
				delay = 2.0f;
				delay = delay - currentEffect;
				for(int i = 0; i < u.getAmount(); i++) {
					totalAmountOfUpgrades++;
					distancePerEx += u.getDistancePerExcecution();
				}
				totalSpeedFromUpgrades += u.getVelocityIncrease() * u.getAmount();
			}
		}
		
		if(delay <= 0.1f) {
			delay = 0.1f;
		}
		
		if(gettingDelta >= delay && currentEffect > 0) {
//			System.out.println(totalSpeedFromUpgrades);
			gettingDelta = 0;
			excecuteEffect = true;
		}
		totalDistanceFromUpgrades = distancePerEx;
		
	}
	
	public void render(SpriteBatch sb) {
		if(isOpen) {
			for(Upgrade u : upgrades) {
				
				u.getGlyph().setText(u.getFont(), String.valueOf(u.getAmount()));
				
				sb.draw(u.getBackgroundSprite().getTexture(), u.getX(), u.getY());
				sb.draw(u.getIncreaseButton().getSprite().getTexture(),
						u.getIncreaseButton().getHitbox().getX(),
						u.getIncreaseButton().getHitbox().getY());
				sb.draw(u.getDecreaseButton().getSprite().getTexture(),
						u.getDecreaseButton().getHitbox().getX(),
						u.getDecreaseButton().getHitbox().getY());
				u.getFont().draw(sb, String.valueOf(u.getValue()),
						u.getX() + u.getIncreaseButton().getHitbox().getWidth() + 10,
								(float) u.getY() + 25);
				u.getFont().draw(sb, "OWNED",
								 u.getX() + u.getWidth() - u.getDecreaseButton().getHitbox().getWidth() - 60,
								 (float) u.getY() + 45);
				u.getFont().draw(sb, String.valueOf(u.getAmount()),
						 u.getX() + u.getWidth() - u.getDecreaseButton().getHitbox().getWidth() - u.getGlyph().width - 2,
						 (float) u.getY() + 20);
				
			}
		}
	}
	
	public float getDelay() {
		return delay;
	}
	
	public float getVelocityFromUpgrades() {
		return totalSpeedFromUpgrades;
	}
	
	public int getTotalDistanceFromUpgrades() {
		return totalDistanceFromUpgrades;
	}
	
	public void setExcecuteEffect(boolean excecute) {
		this.excecuteEffect = excecute;
	}
	
	public boolean getExcecuteEffect() {
		return excecuteEffect;
	}
	
	public Array<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
}
