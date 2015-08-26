import java.util.Map;

import javafx.scene.image.Image;


public class Equipment {
	
	String name;
	String path;
	private Image icon;
	
	private int bonusMinPhysicalDamage;
	private int bonusMaxPhysicalDamage;
	private int bonusMinMagicDamage;
	private int bonusMaxMagicDamage;
	private int bonusSpeed;
	private int bonusHP;
	private int bonusMP;
	private int bonusNaturalArmor;
	private int bonusMagicArmor;
	private int bonusCritChance;
	private int price;
	
	Equipment(String name){
		this.name = name;
		path = "data/equipment/" + name + "/";
		if (Utility.pathExists(path)) {
			loadEquipmentStats();
		}
		if (Utility.pathExists(path + "sprite/icon.png"))
			setIcon(new Image("file:" + path + "sprite/icon.png"));
	}
	
	private void loadEquipmentStats() {

		Map<String, Integer> listOfStats = Utility.getStatsList(path
				+ name + "_Stats.txt");
		bonusMinPhysicalDamage = listOfStats.get("bonusMinPhysicalDamage");
		bonusMaxPhysicalDamage = listOfStats.get("bonusMaxPhysicalDamage");
		bonusMinMagicDamage = listOfStats.get("bonusMinMagicDamage");
		bonusMaxMagicDamage = listOfStats.get("bonusMaxMagicDamage");
		bonusSpeed = listOfStats.get("bonusSpeed");
		bonusHP = listOfStats.get("bonusHP");
		bonusMP = listOfStats.get("bonusMP");
		bonusNaturalArmor = listOfStats.get("bonusNaturalArmor");
		bonusMagicArmor = listOfStats.get("bonusMagicArmor");
		bonusCritChance = listOfStats.get("bonusCritChance");
		price=listOfStats.get("price");

	}

	
	public int getBonusMinPhysicalDamage() {
		return bonusMinPhysicalDamage;
	}

	public void setBonusMinPhysicalDamage(int bonusMinPhysicalDamage) {
		this.bonusMinPhysicalDamage = bonusMinPhysicalDamage;
	}

	public int getBonusMaxPhysicalDamage() {
		return bonusMaxPhysicalDamage;
	}

	public void setBonusMaxPhysicalDamage(int bonusMaxPhysicalDamage) {
		this.bonusMaxPhysicalDamage = bonusMaxPhysicalDamage;
	}

	public int getBonusMinMagicDamage() {
		return bonusMinMagicDamage;
	}

	public void setBonusMinMagicDamage(int bonusMinMagicDamage) {
		this.bonusMinMagicDamage = bonusMinMagicDamage;
	}

	public int getBonusMaxMagicDamage() {
		return bonusMaxMagicDamage;
	}

	public void setBonusMaxMagicDamage(int bonusMaxMagicDamage) {
		this.bonusMaxMagicDamage = bonusMaxMagicDamage;
	}

	public int getBonusSpeed() {
		return bonusSpeed;
	}

	public void setBonusSpeed(int bonusSpeed) {
		this.bonusSpeed = bonusSpeed;
	}

	public int getBonusHP() {
		return bonusHP;
	}

	public void setBonusHP(int bonusHP) {
		this.bonusHP = bonusHP;
	}

	public int getBonusMP() {
		return bonusMP;
	}

	public void setBonusMP(int bonusMP) {
		this.bonusMP = bonusMP;
	}

	public int getBonusNaturalArmor() {
		return bonusNaturalArmor;
	}

	public void setBonusNaturalArmor(int bonusNaturalArmor) {
		this.bonusNaturalArmor = bonusNaturalArmor;
	}

	public int getBonusMagicArmor() {
		return bonusMagicArmor;
	}

	public void setBonusMagicArmor(int bonusMagicArmor) {
		this.bonusMagicArmor = bonusMagicArmor;
	}

	public int getBonusCritChance() {
		return bonusCritChance;
	}

	public void setBonusCritChance(int bonusCritChance) {
		this.bonusCritChance = bonusCritChance;
	}
	public int getPrice() {
		return price;
	}

	public String getName(){
		return name;
	}

	public Image getIcon() {
		return icon;
	}

	private void setIcon(Image icon) {
		this.icon = icon;
	}
}
