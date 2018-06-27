package ethos.model.players.combat;

/**
 * 
 * @author Wolf#4408
 *
 */

public enum WeaponMastery {
	
	WHIP(0, 
			new int[] { 
				4151, // Abyssal Whip
				12773, // Volcanic Whip
				12774, // Frozen Whip
				12006 // Tentacle
			},
		"Whip"
	),
	
	SWORD(1, 
			new int[] {
				1305, // Dragon Longsword
				6607 // White Sword
			},
		"Whip"
	),
	
	AXE(2, 
			new int[] {
				1377, // Dragon Battleaxe
				13263 // Abyssal Bludgeon
			},
		"Whip"
	),
	
	BARROWS(3, 
			new int[] {
				4755, // Verac Flail
				4726, // Guthan Warspear
				4747, // Torag Hammers
				4718 // Dharok's Greataxe
			},
		"Whip"
	),
	
	SCIMITAR(4, 
			new int[] {
				4587, // Dragon Scimitar
				20000 // Dragon Scimitar (or)
			},
		"Whip"
	), 
	
	DAGGER(5, 
			new int[] {
				5698, // Dragon Dagger (p++)
				13265, // Abyssal Dagger
				13267, // Abyssal Dagger (p)
				13269, // Abyssal Dagger (p+)
				13271 // Abyssal Dagger (p++)
			},
		"Whip"
	),
	
	BOW(6, 
			new int[] {
					
			},
		"Whip"
	),
	
	CROSSBOW(7, 
			new int[] {
					
			},
		"Whip"
	),
	
	THROWN(8, 
			new int[] {
					
			},
		"Whip"
	),
	
	MAGIC(9, 
			new int[] {
					
			},
		"Whip"
	),
	
	UNARMED(10, 
			new int[] {
				-1 // Unarmed
			},
		"Whip"
	);
	
	private int slotID;
	private int[] weaponID;
	private String masteryName;
	
	
	private WeaponMastery(int slotID, int[] weaponID, String masteryName) {
		this.slotID = slotID;
		this.weaponID = weaponID;
		this.masteryName = masteryName;
	}
	
	public int getSlot() {
		return slotID;
	}
	
	public int[] getWeapons() {
		return weaponID;
	}
	
	public String getMasteryName() {
		return masteryName;
	}
	
	
	
	public static int getMaxSlot() {
		int currentMax = 0;
		for(WeaponMastery mastery : values()) {
			if(mastery.slotID >= currentMax)
				currentMax = mastery.slotID;
		}
		return currentMax;
	}
	
	
	
	/**
	 * Gets a mastery definition based on a slot
	 * @param itemID
	 * @return
	 */
	
	public static WeaponMastery forSlot(int slotID) {
		for(WeaponMastery mastery : values()) {
			if(mastery.slotID == slotID) {
				return mastery;
			}
		}
		return null;
	}
	
	/**
	 * Gets a mastery definition based on a weapon ID
	 * @param itemID
	 * @return
	 */
	
	public static WeaponMastery forWeapon(int itemID) {
		for(WeaponMastery mastery : values()) {
			for(int weaponID : mastery.weaponID) {
				if(weaponID == itemID) {
					return mastery;
				}
			}
		}
		return null;
	}
	
}