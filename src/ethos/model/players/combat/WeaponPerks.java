package ethos.model.players.combat;

public enum WeaponPerks {
	
	LEVEL_10(10, 1, 0, 0),
	LEVEL_20(20, 1, 0, 0),
	LEVEL_30(30, 1, 0, 0),
	LEVEL_40(40, 1, 0, 0),
	LEVEL_50(50, 1, 0, 0),
	LEVEL_60(60, 1, 0, 0),
	LEVEL_70(70, 1, 0, 0),
	LEVEL_80(80, 1, 0, 0),
	LEVEL_90(90, 1, 0, 0),
	LEVEL_99(99, 1, 0, 1);
	
	private int accuracy;
	private int critical;
	private int weaponSpeed;
	private int levelRequired;
	
	private WeaponPerks(int levelRequired, int accuracy, int critical, int weaponSpeed) {
		this.levelRequired = levelRequired;
		this.accuracy = accuracy;
		this.critical = critical;
		this.weaponSpeed = weaponSpeed;
	}
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public int getCritical() {
		return critical;
	}
	
	public int getWeaponSpeed() {
		return weaponSpeed;
	}
	
	
	/**
	 * Finds the highest level of perk you can use based on your level
	 * @param level
	 * @return
	 */
	
	public static WeaponPerks forLevel(int level) {
		WeaponPerks currentMax = null;
		for(WeaponPerks perk : values()) {
			if(level >= perk.levelRequired) {
				currentMax = perk;
			}
		}
		return currentMax;
	}
	
}