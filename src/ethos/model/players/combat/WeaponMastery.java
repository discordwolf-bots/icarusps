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
				20155, // gilded 2h sword
				6527, // obby maul sword
				6523, // obby sword
				21009, // dragon sword
				12808, // saradomin blessed sword (full)
				12809, //  saradomin blessed sword
				11838, //  saradomin sword
				12426, // 3rd age long sword
				7140, // harry's cutlass
				7141, // lucky cutlass
				7142, // rapier
				11902, // leaf- bladed sword
				1305, // Dragon Longsword
				1319, // rune 2h sword
				1303, // rune longsword
				1289, // rune short sword
				11037, // brine sabre
				7185, // Dragon 2h Sword
				6607, // White Longsword
				6605, // White sword
				6609, // White 2h Sword
				11802, // Armadyl Godsword
				20368, // Armadyl Godsword (or)
				11804, // Bandos Godsword
				20370, // Bandos Godsword (or)
				11806, // Saradomin Godsword
				20372, // Saradomin Godsword (or)
				11808, // Zamorak Godsword
				20374 // Zamorak Godsword (or)
			},
		"Sword"
	),
	
	AXE(2, 
			new int[] {
				1373, // rune battle axe
				1377, // dragon battle axe
				6589, // white battle axe
				20727, // leaf-bladed battleaxe
				1377, // Dragon Battleaxe
				13263 // Abyssal Bludgeon
			},
		"Axe"
	),
	
	BARROWS(3, 
			new int[] {
				4710, //  ahrims staff
				4755, // Verac Flail
				4726, // Guthan Warspear
				4747, // Torag Hammers
				4718 // Dharok's Greataxe
			},
		"Barrows"
	),
	
	SCIMITAR(4, 
			new int[] {
				12389, // gilded scimtar
				1333, // rune scimtar
				4587, // Dragon Scimitar
				20000, // Dragon Scimitar (or)
				6611 // White Scimitar
			},
		"Scimitar"
	), 
	
	DAGGER(5, 
			new int[] {
				1213, // rune dagger
				1229, // rune dagger (p)
				5678, // rune dagger (P+)
				5696, // rune dagger (p++)
				6525, // obby looking dagger
				1215, // dragon dagger
				1231, // dragon dagger (p)
				5680, // dragon dagger (p+)
				5698, // Dragon Dagger (p++)
				13265, // Abyssal Dagger
				13267, // Abyssal Dagger (p)
				13269, // Abyssal Dagger (p+)
				13271 // Abyssal Dagger (p++)
			},
		"Dagger"
	),
	
	BOW(6, 
			new int[] {
				855, // yew longbow
				857, // yew shortbow
				12424, // 3rd age bow
				12765, // green dark bow
				12766, // blue dark bow
				12767, // yellow dark bow
				12768, // white dark bow
				861, // Magic Shortbow
				859, // Magic Longbow
				11235, // Dark Bow
				20997, // Twisted Bow
				4214, // Crystal Bow
				4215, // crystal bow 9/10
				4216, // crystal bow 8/10
				4217, // crystal bow 7/10
				4218, // crystal bow 6/10
				4219, // crystal bow 5/10
				4220, // crystal bow 4/10
				4221, // crystal bow 3/10
				4222, // crystal bow 2/10
				4223, // crystal bow 1/10
			},
		"Bow"
	),
	
	CROSSBOW(7, 
			new int[] {
				19481, // heavy balista
				19478, // light balista
				21012, // dragon hunter crossbow
				4734, // karils cross bow
				9185, // Rune C'Bow
				11785 // Armadyl C'Bow
			},
		"Crossbow"
	),
	
	PROJECTILE(8, 
			new int[] {
				811, //rune dart
				817, // rune dart (p)
				5634, // rune dart (p+)
				5641, // rune dart (p++)
				868, // rune knife
				876, // rune kife (p)
				5660, // rune knife (p+)
				5667, // rune (p++)
				6522, // obby rings
				20849, // dragon throwing axe
				11230, // Dragon Dart
				10033, // Chinchompa
				10034, // Red Chinchompa
				11959, // Black Chinchompa
				12926 // Toxic Blowpipe
			},
		"Projectile"
	),
	
	MAGIC(9, 
			new int[] {
				
			},
		"Magic"
	),
	
	UNARMED(10, 
			new int[] {
				-1 // Unarmed
			},
		"Unarmed"
	),
	
	WARHAMMER(11, 
			new int[] {
				21003, // elder maul
				4153, // granite maul
				6528, // obby maul
				1347, // rune war hammer
				13576 // Dragon Warhammer
			},
		"Warhammer"
	),
	
	MACE(12, 
			new int[] {
				1432, // rune mace
				1434 // Dragon Mace
			},
		"Mace"
	),
	
	SPEAR(13, 
			new int[] {
				6599, // white hally
				20158, // gilded spear
				20161, // gilded hasta
				3202, // rune hally
				1247, // rune spear
				1261, // rune spear(p)
				5714, // rune spear (P+)
				5728, // rune spear (p++)
				1256, // d spear p
				5716, // d spear P+
				5730, // d spear p++
				13092, // c hally full
				13093, // c hally 9/10
				13094, // c hally 8/10
				13095, // c hally 7/10
				13096, // c hally 6/10
				13097, // c hally 5/10
				13098, // c hally 4/10
				13099, // c hally 3/10
				13100, // c hally 2/10
				13101, // c hally 1/10
				3204, // Dragon Halberd
				1249, // Dragon Spear
				4158, // Leaf-Bladed Spear
				11824, // Zamorakian Spear
				11889 // Zamorakian Hasta
			},
		"Spear"
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