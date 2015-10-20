package com.khamekaze.idontwanttoliveonthisplanetanymore.upgrade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.gui.Button;

public class Upgrade {
	
	private Sprite sprite, background;
	private Texture texture, bgTexture;
	private int amount = 0;
	private float value;
	private int sellValue;
	private int distancePerExcecution = 0;
	private float velocityIncrease = 0;
	private boolean unlocked = false;
	private boolean canAfford = false;
	private Button increase, decrease;
	private int x, y, width, height;
	private BitmapFont font;
	private int originalValue;
	private float effect;
	private boolean excecuteEffect = false;
	private GlyphLayout layout;
	
	public Upgrade(int value) {
		bgTexture = new Texture("upgradeContainer.png");
		background = new Sprite(bgTexture);
		this.value = value;
		sellValue = value / 2;
		originalValue = value;
		effect = 0.1f;
		if(originalValue > 10 && value < 600) {
			velocityIncrease = value / 100;
		} else if(value > 20000 && value < 30000) {
			velocityIncrease = originalValue / 500;
		} else if(value > 80000 && value < 600000) {
			velocityIncrease = originalValue / 600;
		} else if(value > 1000000) {
			velocityIncrease = originalValue / 1200;
		} else {
			velocityIncrease = 0;
		}
		System.out.println("VEL: " + velocityIncrease);
		increase = new Button("increase");
		decrease = new Button("decrease");
		increase.setSize(50, 50);
		decrease.setSize(50, 50);
		width = (int) background.getWidth();
		height = (int) background.getHeight();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		layout = new GlyphLayout();
	}
	
	public void update() {
		
	}
	
	public void calculateValue() {
		value = value + (amount * (originalValue / 10));
		sellValue = (int) value / 2;
	}
	
	public void sell() {
		value = value - (amount * (originalValue / 10));
		sellValue = (int) value / 2;
	}
	
	public GlyphLayout getGlyph() {
		return layout;
	}
	
	public float getVelocityIncrease() {
		return velocityIncrease;
	}
	
	public void setExcecuteEffect(boolean setExcecute) {
		this.excecuteEffect = setExcecute;
	}
	
	public void setDistancePerExcecution(int distance) {
		this.distancePerExcecution = distance;
	}
	
	public int getDistancePerExcecution() {
		return distancePerExcecution;
	}
	
	public boolean getExcecuteEffect() {
		return excecuteEffect;
	}
	
	public float getEffect() {
		return effect;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		decrease.setPosition((int)(MainGame.WIDTH - increase.getHitbox().getWidth()), y);
		increase.setPosition((int) MainGame.WIDTH - width, y);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public int getSellValue() {
		return sellValue;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Button getIncreaseButton() {
		return increase;
	}
	
	public Button getDecreaseButton() {
		return decrease;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Sprite getBackgroundSprite() {
		return background;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
