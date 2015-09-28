package com.khamekaze.idontwanttoliveonthisplanetanymore.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.khamekaze.idontwanttoliveonthisplanetanymore.MainGame;
import com.khamekaze.idontwanttoliveonthisplanetanymore.upgrade.Upgrade;
import com.khamekaze.idontwanttoliveonthisplanetanymore.upgrade.UpgradeMenu;

public class UpgradesGUI {
	
	private Array<Button> buttons;
	private Array<Upgrade> upgrades;
	private Button openUpgradesButton;
	private boolean open = false;
	private UpgradeMenu upgradeMenu;
	
	public UpgradesGUI() {
		openUpgradesButton = new Button("openUpgradesMenuButton", MainGame.WIDTH - 50, 0, 50, 50);
		buttons = new Array<Button>();
		buttons.add(openUpgradesButton);
		upgradeMenu = new UpgradeMenu();
		upgrades = upgradeMenu.getUpgrades();
	}
	
	public void update() {
		upgradeMenu.update();
	}
	
	public void render(SpriteBatch sb) {
		for(Button b : buttons) {
			b.getSprite().draw(sb);
		}
		upgradeMenu.render(sb);
	}
	
	public Array<Button> getButtons() {
		return buttons;
	}
	
	public Array<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
		upgradeMenu.setIsOpen(open);
	}
	
	public boolean getIsOpen() {
		return open;
	}
	
	public UpgradeMenu getUpgradeMenu() {
		return upgradeMenu;
	}

}
