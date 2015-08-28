package Game.Objects;

import java.util.Map;
import javafx.scene.image.Image;
import Game.Buttons.*;
import Game.Objects.*;
import Game.Scenes.*;
import Game.Utility.*;

public class Champion {

	private boolean printLog = true;

	private String name;
	private String path;
	private String story;

	Image icon;

	// champion basic stats
	private int minPhysicalDamage;
	private int maxPhysicalDamage;
	private int minMagicDamage;
	private int maxMagicDamage;
	private int speed;
	private int HP;
	private int MP;
	private int naturalArmor;
	private int magicArmor;
	private int critChance;

	// champion effective stats (when all modifiers are applied, are
	// recalculated after every action)
	private int effectiveMinPhysicalDamage;
	private int effectiveMaxPhysicalDamage;
	private int effectiveMinMagicDamage;
	private int effectiveMaxMagicDamage;
	private int effectiveSpeed;
	private int effectiveHP;
	private int effectiveMP;
	private int currentSpeed;
	private int currentHP;
	private int currentMP;
	private int effectiveNaturalArmor;
	private int effectiveMagicArmor;
	private int effectiveCritChance;

	private int speedPoints; // actual speed of champion, how many points does
								// he need to get before any action
	private boolean isAlive;

	private Equipment[] sockets = new Equipment[4];// array containing all the
													// equipment

	// that character carry

	// CONSTRUCTOR. takes character name and loads his/hers initial stats.
	public Champion(String name) {
		this.name = name;
		path = "data/champions/" + name + "/";
		if (Utility.pathExists(path)) {
			loadChampionStats();
		}
		story = Utility.getText(path + name + "_Story.txt");
		if (Utility.pathExists(path + "sprite/icon.png"))
			icon = new Image("file:" + path + "sprite/icon.png");

	}

	// method loadChampionStats() used only in constructor
	private void loadChampionStats() {

		Map<String, Integer> listOfStats = Utility.getStatsList(path + name
				+ "_Stats.txt");
		String statName = "";
		try {
			statName = "minPhysicalDamage";
			minPhysicalDamage = listOfStats.get(statName);
			statName = "maxPhysicalDamage";
			maxPhysicalDamage = listOfStats.get(statName);
			statName = "minMagicDamage";
			minMagicDamage = listOfStats.get(statName);
			statName = "maxMagicDamage";
			maxMagicDamage = listOfStats.get(statName);
			statName = "speed";
			speed = listOfStats.get(statName);
			statName = "HP";
			HP = listOfStats.get(statName);
			statName = "MP";
			MP = listOfStats.get(statName);
			statName = "naturalArmor";
			naturalArmor = listOfStats.get(statName);
			statName = "magicArmor";
			magicArmor = listOfStats.get(statName);
			statName = "critChance";
			critChance = listOfStats.get(statName);
		} catch (NullPointerException e) {
			String message = "Can't find " + statName + " stat in file " + path
					+ name + "_Stats.txt";
			Utility.showMessage(message);
		}
		// apply initial stats to effective stats before any equipment bonuses
		effectiveMinPhysicalDamage = minPhysicalDamage;
		effectiveMaxPhysicalDamage = maxPhysicalDamage;
		effectiveMinMagicDamage = minMagicDamage;
		effectiveMaxMagicDamage = maxMagicDamage;
		effectiveSpeed = speed;
		effectiveHP = HP;
		effectiveMP = MP;
		effectiveNaturalArmor = naturalArmor;
		effectiveMagicArmor = magicArmor;
		effectiveCritChance = critChance;

	}

	private void applyEquipmentStats(Equipment item) {
		effectiveMinPhysicalDamage = effectiveMinPhysicalDamage
				+ item.getBonusMinPhysicalDamage();
		effectiveMaxPhysicalDamage = effectiveMaxPhysicalDamage
				+ item.getBonusMaxPhysicalDamage();
		effectiveMinMagicDamage = effectiveMinMagicDamage
				+ item.getBonusMinMagicDamage();
		effectiveMaxMagicDamage = effectiveMaxMagicDamage
				+ item.getBonusMaxMagicDamage();
		effectiveSpeed = effectiveSpeed + item.getBonusSpeed();
		effectiveHP = effectiveHP + item.getBonusHP();
		effectiveMP = effectiveMP + item.getBonusMP();
		effectiveNaturalArmor = effectiveNaturalArmor
				+ item.getBonusNaturalArmor();
		effectiveMagicArmor = effectiveMagicArmor + item.getBonusMagicArmor();
		effectiveCritChance = effectiveCritChance + item.getBonusCritChance();
		GameMechanics.checkStats(this);
	}

	private void nullifyEquipmentStats() {
		effectiveMinPhysicalDamage = minPhysicalDamage;
		effectiveMaxPhysicalDamage = maxPhysicalDamage;
		effectiveMinMagicDamage = minMagicDamage;
		effectiveMaxMagicDamage = maxMagicDamage;
		effectiveSpeed = speed;
		effectiveHP = HP;
		effectiveMP = MP;
		effectiveNaturalArmor = naturalArmor;
		effectiveMagicArmor = magicArmor;
		effectiveCritChance = critChance;
	}

	public void setEquipment(int socketNumber, Equipment item) {
		if (sockets[socketNumber] == null) {
			sockets[socketNumber] = item;
			applyEquipmentStats(item);
		} else {
			String message = "This socket is already ocupied with "
					+ sockets[socketNumber].getName();
			Utility.showMessage(message);
		}
	}

	public void removeEquipment(int socketNumber) {
		if (sockets[socketNumber] != null) {
			sockets[socketNumber] = null;
			applyEquipment();
		} else {
			String message = "This socket is already empty";
			Utility.showMessage(message);
		}
	}

	public void applyEquipment() {
		nullifyEquipmentStats();
		for (Equipment item : sockets) {
			if (item != null) {
				applyEquipmentStats(item);
			}
		}
	}

	public void receiveDamage(int n) {
		currentHP = currentHP - n;
		if (currentHP <= 0) {
			currentHP = 0;
			setIsAlive(false);
		}
		if (currentHP > effectiveHP) {
			currentHP = effectiveHP;
		}

		if (printLog) {
			String message = getName() + " has " + getCurrentHP() + "/"
					+ getEffectiveHP() + " HP left" + "\n";
			String add = "";
			if (!isAlive) {
				add = getName() + " is Dead!" + "\n";
			}
			Utility.showMessage(message + add);
		}
	}

	@Override
	public String toString() {
		return "Name=" + name + "\n" + "minPhysicalDamage=" + minPhysicalDamage
				+ "\n" + "maxPhysicalDamage=" + maxPhysicalDamage + "\n"
				+ "minMagicDamage=" + minMagicDamage + "\n" + "maxMagicDamage="
				+ maxMagicDamage + "\n" + "speed=" + speed + "\n" + "HP=" + HP
				+ "\n" + "MP=" + MP + "\n" + "naturalArmor=" + naturalArmor
				+ "\n" + "magicArmor=" + magicArmor + "\n" + "critChance="
				+ critChance + "\n" + "\n" + "effectiveMinPhysicalDamage="
				+ effectiveMinPhysicalDamage + "\n"
				+ "effectiveMaxPhysicalDamage=" + effectiveMaxPhysicalDamage
				+ "\n" + "effectiveMinMagicDamage=" + effectiveMinMagicDamage
				+ "\n" + "effectiveMaxMagicDamage=" + effectiveMaxMagicDamage
				+ "\n" + "effectiveSpeed=" + effectiveSpeed + "\n"
				+ "effectiveHP=" + effectiveHP + "\n" + "effectiveMP="
				+ effectiveMP + "\n" + "effectiveNaturalArmor="
				+ effectiveNaturalArmor + "\n" + "effectiveMagicArmor="
				+ effectiveMagicArmor + "\n" + "effectiveCritChance="
				+ effectiveCritChance + "\n" + "speedPoints=" + speedPoints
				+ "\n" + "\n" + "currentSpeed=" + currentSpeed + "\n"
				+ "currentHP=" + currentHP + "\n" + "currentMP=" + currentMP
				+ "\n";
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(boolean status) {
		isAlive = status;
	}

	public void initializeChampion() {
		currentHP = effectiveHP;
		currentMP = effectiveMP;
		currentSpeed = 0;
		setIsAlive(true);
	}

	public int getMinPhysicalDamage() {
		return minPhysicalDamage;
	}

	public void setMinPhysicalDamage(int minPhysicalDamage) {
		this.minPhysicalDamage = minPhysicalDamage;
	}

	public int getMaxPhysicalDamage() {
		return maxPhysicalDamage;
	}

	public void setMaxPhysicalDamage(int maxPhysicalDamage) {
		this.maxPhysicalDamage = maxPhysicalDamage;
	}

	public int getMinMagicDamage() {
		return minMagicDamage;
	}

	public void setMinMagicDamage(int minMagicDamage) {
		this.minMagicDamage = minMagicDamage;
	}

	public int getMaxMagicDamage() {
		return maxMagicDamage;
	}

	public void setMaxMagicDamage(int maxMagicDamage) {
		this.maxMagicDamage = maxMagicDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public int getNaturalArmor() {
		return naturalArmor;
	}

	public void setNaturalArmor(int naturalArmor) {
		this.naturalArmor = naturalArmor;
	}

	public int getMagicArmor() {
		return magicArmor;
	}

	public void setMagicArmor(int magicArmor) {
		this.magicArmor = magicArmor;
	}

	public int getCritChance() {
		return critChance;
	}

	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}

	public int getEffectiveMinPhysicalDamage() {
		return effectiveMinPhysicalDamage;
	}

	public void setEffectiveMinPhysicalDamage(int effectiveMinPhysicalDamage) {
		this.effectiveMinPhysicalDamage = effectiveMinPhysicalDamage;
	}

	public int getEffectiveMaxPhysicalDamage() {
		return effectiveMaxPhysicalDamage;
	}

	public void setEffectiveMaxPhysicalDamage(int effectiveMaxPhysicalDamage) {
		this.effectiveMaxPhysicalDamage = effectiveMaxPhysicalDamage;
	}

	public int getEffectiveMinMagicDamage() {
		return effectiveMinMagicDamage;
	}

	public void setEffectiveMinMagicDamage(int effectiveMinMagicDamage) {
		this.effectiveMinMagicDamage = effectiveMinMagicDamage;
	}

	public int getEffectiveMaxMagicDamage() {
		return effectiveMaxMagicDamage;
	}

	public void setEffectiveMaxMagicDamage(int effectiveMaxMagicDamage) {
		this.effectiveMaxMagicDamage = effectiveMaxMagicDamage;
	}

	public int getEffectiveSpeed() {
		return effectiveSpeed;
	}

	public void setEffectiveSpeed(int effectiveSpeed) {
		this.effectiveSpeed = effectiveSpeed;
	}

	public int getEffectiveHP() {
		return effectiveHP;
	}

	public void setEffectiveHP(int effectiveHP) {
		this.effectiveHP = effectiveHP;
	}

	public int getEffectiveMP() {
		return effectiveMP;
	}

	public void setEffectiveMP(int effectiveMP) {
		this.effectiveMP = effectiveMP;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getCurrentMP() {
		return currentMP;
	}

	public void setCurrentMP(int currentMP) {
		this.currentMP = currentMP;
	}

	public int getEffectiveNaturalArmor() {
		return effectiveNaturalArmor;
	}

	public void setEffectiveNaturalArmor(int effectiveNaturalArmor) {
		this.effectiveNaturalArmor = effectiveNaturalArmor;
	}

	public int getEffectiveMagicArmor() {
		return effectiveMagicArmor;
	}

	public void setEffectiveMagicArmor(int effectiveMagicArmor) {
		this.effectiveMagicArmor = effectiveMagicArmor;
	}

	public int getEffectiveCritChance() {
		return effectiveCritChance;
	}

	public void setEffectiveCritChance(int effectiveCritChance) {
		this.effectiveCritChance = effectiveCritChance;
	}

	public int getSpeedPoints() {
		return speedPoints;
	}

	public void setSpeedPoints(int speedPoints) {
		this.speedPoints = speedPoints;
	}

	public Equipment getEquipment(int n) {
		return sockets[n];
	}

	public String getStory() {
		return story;
	}

	public Image getIcon(){
		return icon;
	}

	public String getName() {
		return name;
	}
}
