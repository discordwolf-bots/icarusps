package ethos.model.players.combat;

public enum WeaponPerks {
	
	/**
	 * @levelRequired = minimum level before you use this perk tree
	 * @minDamage = how much damage a successful hit will deal at a minimum (can still hit a 0)
	 * @maxDamage = how much damage a successful hit can hit up to
	 * @critical = a 1 in ? chance to deal a critical hit (1.5x damage)
	 * @poisonChance = a 1 in ? chance to poison an opponent
	 * @poisonDamage = how much damage the poison will start at when successful
	 * @weaponSpeed = how many ticks the attack speed will be reduced by
	 */
	LEVEL_10(10, 0, 0, 0, 0, 0, 0),
	LEVEL_20(20, 0, 0, 0, 0, 0, 0),
	LEVEL_30(30, 0, 0, 0, 0, 0, 0),
	LEVEL_40(40, 0, 0, 0, 0, 0, 0),
	LEVEL_50(50, 0, 0, 0, 0, 0, 0),
	LEVEL_60(60, 0, 0, 0, 0, 0, 0),
	LEVEL_70(70, 0, 0, 0, 0, 0, 0),
	LEVEL_80(80, 0, 0, 0, 0, 0, 0),
	LEVEL_90(90, 0, 0, 0, 0, 0, 0),
	LEVEL_99(99, 0, 0, 0, 0, 0, 1);
	
	private int levelRequired;
	private int poisonChance, poisonDamage;
	private int critical;
	private int minDamage, maxDamage;
	private int weaponSpeed;
	
	
	// Constructor
	private WeaponPerks(int levelRequired, int minDamage, int maxDamage, int critical, int poisonChance, int poisonDamage, int weaponSpeed) {
		this.levelRequired = levelRequired;
		this.poisonChance = poisonChance;
		this.poisonDamage = poisonDamage;
		this.critical = critical;
		this.weaponSpeed = weaponSpeed;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}
	
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public int getPoisonChance() {
		return poisonChance;
	}
	
	public int getPoisonDamage() {
		return poisonDamage;
	}
	
	public int getCritical() {
		return critical;
	}
	
	public int getWeaponSpeed() {
		return weaponSpeed;
	}
	
	public int getMinDamage() {
		return minDamage;
	}
	
	public int getMaxDamage() {
		return maxDamage;
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