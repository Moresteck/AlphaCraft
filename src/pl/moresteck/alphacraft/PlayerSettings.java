package pl.moresteck.alphacraft;

import java.io.File;

public class PlayerSettings {
	public String player;
	public PlayerSettingsManager manager;

	public PlayerSettings(String player) {
		this.player = player;
		this.manager = new PlayerSettingsManager(player, new File("world/players", player + ".settings"));
	}

	public boolean getArrowShoot() {
		return this.manager.a("bow", true);
	}

	public boolean getSheepShear() {
		return this.manager.a("shear", true);
	}

	public boolean getEatFood() {
		return this.manager.a("eat", true);
	}

	public void setEatFood(boolean value) {
		this.manager.setter.setProperty("eat", Boolean.toString(value));
	}

	public void setArrowShoot(boolean value) {
		this.manager.setter.setProperty("bow", Boolean.toString(value));
	}

	public void setSheepShear(boolean value) {
		this.manager.setter.setProperty("shear", Boolean.toString(value));
	}
}
