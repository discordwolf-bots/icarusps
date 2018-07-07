package ethos.model.players.combat;

public enum WeaponPerks {
	
	/**
	 * @levelRequired = minimum level before you use this perk tree
	 * 
	 * @poisonChance = a 1 in ? chance to poison an opponent
	 * @poisonDamage = how much damage the poison will start at when successful
	 * 
	 * @critical = a 1 in ? chance to deal a critical hit (1.5x damage)
	 * 
	 * @minDamage = how much damage a successful hit will deal at a minimum (can still hit a 0)
	 * @maxDamage = how much damage a successful hit can hit up to
	 * 
	 * @weaponSpeed = how many ticks the attack speed will be reduced by
	 * 
	 * @lifeSteal = percent chance to heal yourself
	 * @lifeStealAmount = percent of the damage you will heal
	 */
	LEVEL_10(10, 0, 0, 5, 0, 0, 0, 0, 0),
	LEVEL_20(20, 0, 0, 5, 5, 0, 0, 0, 0),
	LEVEL_30(30, 0, 0, 10, 5, 5, 0, 0, 0),
	LEVEL_40(40, 0, 0, 10, 5, 10, 0, 0, 0),
	LEVEL_50(50, 30, 3, 10, 5, 10, 0, 10, 25),
	LEVEL_60(60, 30, 5, 15, 5, 10, 0, 10, 30),
	LEVEL_70(70, 20, 5, 15, 7, 10, 0, 15, 30),
	LEVEL_80(80, 20, 5, 20, 7, 10, 0, 15, 40),
	LEVEL_90(90, 10, 6, 20, 7, 10, 0, 20, 40),
	LEVEL_99(99, 10, 6, 25, 10, 10, 1, 20, 50);
	
	private int levelRequired;
	private int poisonChance, poisonDamage;
	private int critical;
	private int minDamage, maxDamage;
	private int weaponSpeed;
	private int lifeSteal;
	private int lifeStealAmount;
	
	
	// Constructor
	private WeaponPerks(int levelRequired, int poisonChance, int poisonDamage, int critical, int minDamage, int maxDamage, int weaponSpeed, int lifeSteal, int lifeStealAmount) {
		this.levelRequired = levelRequired;
		this.poisonChance = poisonChance;
		this.poisonDamage = poisonDamage;
		this.critical = critical;
		this.weaponSpeed = weaponSpeed;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.lifeSteal = lifeSteal;
		this.lifeStealAmount = lifeStealAmount;
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
	
	public int getMinDamage() {
		return minDamage;
	}
	
	public int getMaxDamage() {
		return maxDamage;
	}
	
	public int getWeaponSpeed() {
		return weaponSpeed;
	}
	
	public int getLifeSteal() {
		return lifeSteal;
	}
	
	public int getLifeStealAmount() {
		return lifeStealAmount;
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