package ethos.model.content;

import ethos.model.players.Player;

public enum SkillcapePerks {
		ATTACK(new int[][] { { 9747, 9748, 21360 } }),
		STRENGTH(new int[][] { { 9750, 9751, 21362 } }),
		DEFENCE(new int[][] { { 9753, 9754, 21361 } }),
		RANGING(new int[][] { { 9756, 9757, 21364 } }),
		PRAYER(new int[][] { { 9759, 9760, 21365 } }),
		MAGIC(new int[][] { { 9762, 9763, 21366 } }),
		RUNECRAFTING(new int[][] { { 9765, 9766, 21380 } }),
		HITPOINTS(new int[][] { { 9768, 9769, 21363 } }),
		AGILITY(new int[][] { { 9771, 9772, 21376 } }),
		HERBLORE(new int[][] { { 9774, 9775, 21375 } }),
		THIEVING(new int[][] { { 9777, 9778, 21377 } }),
		CRAFTING(new int[][] { { 9780, 9781, 21372 } }),
		FLETCHING(new int[][] { { 9783, 9784, 21369 } }),
		SLAYER(new int[][] { { 9786, 9787, 21378 } }),
		MINING(new int[][] { { 9792, 9793, 21374 } }),
		SMITHING(new int[][] { { 9795, 9796, 21373 } }),
		FISHING(new int[][] { { 9798, 9799, 21370 } }),
		COOKING(new int[][] { { 9801, 9802, 21367 } }),
		FIREMAKING(new int[][] { { 9804, 9805, 21371 } }),
		WOODCUTTING(new int[][] { { 9807, 9808, 21368 } }),
		FARMING(new int[][] { { 9810, 9811, 21379 } }),
		HUNTER(new int[][] { { 9948, 9949, 21381 } }),
		MAX_CAPE(new int[][] { { 13280 } }),
		ARDOUGNE_MAX_CAPE(new int[][] { { 20760 } }),
		FIRE_MAX_CAPE(new int[][] { { 13329 } }),
		AVAS_MAX_CAPE(new int[][] { { 13337 } }),
		SARADOMIN_MAX_CAPE(new int[][] { { 13331 } }),
		ZAMORAK_MAX_CAPE(new int[][] { { 13333 } }),
		GUTHIX_MAX_CAPE(new int[][] { { 13335 } 
	});
	
	public static final int[] MAX_CAPE_IDS = { 13280, 13329, 13337, 13331, 13333, 13335, 20760 };
		
	public static final SkillcapePerks[] MAX_CAPES = { MAX_CAPE, ARDOUGNE_MAX_CAPE, FIRE_MAX_CAPE, AVAS_MAX_CAPE, SARADOMIN_MAX_CAPE, ZAMORAK_MAX_CAPE, GUTHIX_MAX_CAPE };
	
	private int[][] skillcapes;

	SkillcapePerks(int[][] skillcapes) {
		this.skillcapes = skillcapes;
	}
	
	public int[][] getSkillcape() {
		return skillcapes;
	}
	
	static int MAX_CAPE_ID = 13280, MAX_CAPE_HOOD = 13281;
	
	/**
	 * Allows us to check wether or not a player is wearing one of the capes
	 * @param player
	 * @return
	 */
	public boolean isWearing(Player player) {
		for (int[] set : skillcapes) {
			for (int setItem : set) {
				if (player.getItems().isWearingItem(setItem, player.playerCape)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isWearingMaster(Player player) {
		for (int[] set : skillcapes) {
			if(set.length == 3) {
				if(player.getItems().isWearingItem(set[2], player.playerCape))
					return true;
			}
		}
		return false;
	}
	
	public static boolean isWearingMaxCape(Player player) {
		if (MAX_CAPE.isWearing(player) || 
			FIRE_MAX_CAPE.isWearing(player) || 
			AVAS_MAX_CAPE.isWearing(player) || 
			SARADOMIN_MAX_CAPE.isWearing(player) || 
			ZAMORAK_MAX_CAPE.isWearing(player) || 
			ARDOUGNE_MAX_CAPE.isWearing(player) || 
			GUTHIX_MAX_CAPE.isWearing(player)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Purchasing max cape
	 * @param player		The player who is purchasing the max cape
	 */
	public static void purchaseMaxCape(Player player) {
		if (!player.getItems().playerHasItem(995, 10_000_000)) {
			player.sendMessage("You must have 10,000,000 coins to do this");
			player.getPA().closeAllWindows();
			return;
		} else {
			player.getItems().addItem(MAX_CAPE_ID, 1);
			player.getItems().addItem(MAX_CAPE_HOOD, 1);
			player.getItems().deleteItem(995, 10_000_000);
			player.getDH().sendItemStatement("Mac grunts and hands over his cape, pocketing your \\n money swiftly.", MAX_CAPE_ID);
		}
	}
	
	/**
	 * Mixing items with max cape
	 */
	public static void mixCape(Player player, String cape) {

		if (!player.getItems().playerHasItem(MAX_CAPE_ID) || !player.getItems().playerHasItem(MAX_CAPE_HOOD)) {
			player.sendMessage("You must have a max cape and hood in order to do this.");
			return;
		}

		player.getItems().deleteItem(MAX_CAPE_ID, 1);
		player.getItems().deleteItem(MAX_CAPE_HOOD, 1);
		switch (cape) {
		
		case "FIRE":
			if (!player.getItems().playerHasItem(6570)) {
				player.sendMessage("You must have a firecape in order to do this.");
				return;
			}
			player.getItems().deleteItem(6570, 1);
			player.getItems().addItem(13329, 1);
			player.getItems().addItem(13330, 1);
			player.getDH().sendItemStatement("You've combined the fire cape and max cape.", 13329);
			break;
			
		case "SARADOMIN":
			if (!player.getItems().playerHasItem(2412)) {
				player.sendMessage("You must have a saradomin cape in order to do this.");
				return;
			}
			player.getItems().deleteItem(2412, 1);
			player.getItems().addItem(13331, 1);
			player.getItems().addItem(13332, 1);
			player.getDH().sendItemStatement("You've combined the saradomin cape and max cape.", 13331);
			break;
			
		case "ZAMORAK":
			if (!player.getItems().playerHasItem(2414)) {
				player.sendMessage("You must have a zamorak cape in order to do this.");
				return;
			}
			player.getItems().deleteItem(2414, 1);
			player.getItems().addItem(13333, 1);
			player.getItems().addItem(13334, 1);
			player.getDH().sendItemStatement("You've combined the zamorak cape and max cape.", 13333);
			break;
			
		case "GUTHIX":
			if (!player.getItems().playerHasItem(2413)) {
				player.sendMessage("You must have a guthix in order to do this.");
				return;
			}
			player.getItems().deleteItem(2413, 1);
			player.getItems().addItem(13335, 1);
			player.getItems().addItem(13336, 1);
			player.getDH().sendItemStatement("You've combined the guthix cape and max cape.", 13335);
			break;
			
		case "AVAS":
			if (!player.getItems().playerHasItem(10499)) {
				player.sendMessage("You must have a accumulator in order to do this.");
				return;
			}
			player.getItems().deleteItem(10499, 1);
			player.getItems().addItem(13337, 1);
			player.getItems().addItem(13338, 1);
			player.getDH().sendItemStatement("You've combined the accumulator and max cape.", 13337);
			break;
			
		case "ARDOUGNE":
			if (!player.getItems().playerHasItem(13124)) {
				player.sendMessage("You must have an ardougne cloak(4) in order to do this.");
				return;
			}
			player.getItems().deleteItem(13124, 1);
			player.getItems().addItem(20760, 1);
			player.getItems().addItem(20764, 1);
			player.getDH().sendItemStatement("You've combined the cloak and max cape.", 20760);
			break;
		}
	}

}
