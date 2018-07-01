package ethos.model.content;

import ethos.Server;
import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.items.GameItem;
import ethos.model.items.Item;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.util.Misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrystalChest {

	private static final int KEY = 989;
	private static final int DRAGONSTONE = 1617;
	private static final int KEY_HALVE1 = 985;
	private static final int KEY_HALVE2 = 987;
	private static final int ANIMATION = 881;
	
	private static String rarity = ""; 

	private static final Map<Rarity, List<GameItem>> items = new HashMap<>();

	static {
		items.put(Rarity.COMMON, Arrays.asList(
				new GameItem(140, 10), 
				new GameItem(374, 50), 
				new GameItem(380, 100), 
				new GameItem(995, 100000), 
				new GameItem(1127, 1),
				new GameItem(2435, 2),
				new GameItem(1163, 1), 
				new GameItem(1201, 1), 
				new GameItem(1303, 1), 
				new GameItem(1712, 1),
				new GameItem(2677, 1), 
				new GameItem(441, 25), 
				new GameItem(454, 25), 
				new GameItem(1516, 20), 
				new GameItem(1512, 35), 
				new GameItem(208, 15), 
				new GameItem(565, 250), 
				new GameItem(560, 250), 
				new GameItem(71, 25), 
				new GameItem(1632, 1), 
				new GameItem(537, 10), 
				new GameItem(384, 15), 
				new GameItem(4131, 1)
				)
			);
		
		items.put(Rarity.UNCOMMON, Arrays.asList(
				new GameItem(386, 20), 
				new GameItem(990, 3), 
				new GameItem(995, 500000), 
				new GameItem(1305, 1), 
				new GameItem(1377, 1),
				new GameItem(2368, 1), 
				new GameItem(2801, 1), 
				new GameItem(3027, 10), 
				new GameItem(3145, 15), 
				new GameItem(4587, 1), 
				new GameItem(6688, 10),
				new GameItem(222, 100), 
				new GameItem(226, 100),
				new GameItem(224, 100), 
				new GameItem(240, 100),  
				new GameItem(3139, 150), 
				new GameItem(246, 100), 
				new GameItem(232, 100), 
				new GameItem(11840, 1)
				)
			);
		
		items.put(Rarity.RARE, Arrays.asList(
				new GameItem(20703,1),
				new GameItem(995,1000000),
				new GameItem(9096),
				new GameItem(9097),
				new GameItem(9098),
				new GameItem(9099),
				new GameItem(776),
				new GameItem(7509),
				new GameItem(11968),
				new GameItem(9100),
				new GameItem(9101),
				new GameItem(9084),
				new GameItem(20791,1),
				new GameItem(6199,1),
				new GameItem(21307),				
				new GameItem(1333,1)
				)
			);
		
		items.put(Rarity.VERY_RARE, Arrays.asList(
				new GameItem(7141)
				)
			);
				
	}

	private static GameItem randomChestRewards(Player c, int max, int mult) {
		
		int random = Misc.random(max);
		// Apply a multiplier if the player is in Donator Zone
		random *= mult;
		
		double common = max * 0.8;
		double uncommon = max * 0.17; uncommon += common;
		double rare = max * 0.029; rare += uncommon;
		
		boolean announceme = false;
		
		List<GameItem> itemList;
		
		if(random <= common) {
			rarity = "Common";
			itemList = items.get(Rarity.COMMON);
		} else if( random > common && random <= uncommon) {
			rarity = "Uncommon";
			itemList = items.get(Rarity.UNCOMMON);
		} else if( random > uncommon && random <= rare) {
			rarity = "Rare";
			itemList = items.get(Rarity.RARE);
		} else {
			// Very Rare loot
			announceme = true;
			rarity = "Very Rare";
			itemList = items.get(Rarity.VERY_RARE);
		}
		
		return Misc.getRandomItem(itemList);
	}

	public static void makeKey(Player c) {
		if (c.getItems().playerHasItem(KEY_HALVE1, 1) && c.getItems().playerHasItem(KEY_HALVE2, 1)) {
			c.getItems().deleteItem(KEY_HALVE1, 1);
			c.getItems().deleteItem(KEY_HALVE2, 1);
			c.getItems().addItem(KEY, 1);
		}
	}

	public static void searchChest(Player c) {
		if (c.getItems().playerHasItem(KEY)) {
			c.getItems().deleteItem(KEY, 1);
			c.startAnimation(ANIMATION);
			c.getItems().addItem(DRAGONSTONE, 1);
			GameItem reward = Boundary.isIn(c, Boundary.DONATOR_ZONE) && c.getRights().isOrInherits(Right.DONATOR) ? randomChestRewards(c, 3500, 2) : randomChestRewards(c, 3500, 1);
			if (!c.getItems().addItem(reward.getId(), reward.getAmount())) {
				Server.itemHandler.createGroundItem(c, reward.getId(), c.getX(), c.getY(), c.heightLevel, reward.getAmount());
			}
			String rarityColour = "2b42b4";
			if(rarity.equals("Uncommon")) rarityColour = "069a26";
			if(rarity.equals("Rare")) rarityColour = "988f3b";
			if(rarity.equals("Very Rare")) rarityColour = "ad3cae";
			Achievements.increase(c, AchievementType.LOOT_CRYSTAL_CHEST, 1);
			c.sendMessage("<col=" + rarityColour + ">You stick your hand in the chest and pull out " + reward.getAmount() + "x " + Item.getItemName(reward.getId()));
		} else {
			c.sendMessage("@blu@The chest is locked, you need a crystal key to open this!");
		}
	}

	enum Rarity {
		UNCOMMON, COMMON, RARE, VERY_RARE
	}

}