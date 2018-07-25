package ethos.model.content;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ethos.event.CycleEvent;
import ethos.event.CycleEventContainer;
import ethos.event.CycleEventHandler;
import ethos.model.items.GameItem;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.util.Misc;

/**
 * Revamped a simple means of receiving a random item based on chance.
 * 
 * @author Jason MacKeigan
 * @date Oct 29, 2014, 1:43:44 PM
 */
public class DailyGearBox extends CycleEvent {

	/**
	 * The item id of the mystery box required to trigger the event
	 */
	public static final int MYSTERY_BOX = 20703;

	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static Map<Rarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 */
	static {
		items.put(Rarity.COMMON, //
			Arrays.asList(
                new GameItem(12696, 50),// super combat potion-noted
				new GameItem(13307, 200),// blood money 
				new GameItem(565, 2000),// blood rune
				new GameItem(560, 2500),// death rune 
				new GameItem(6129),// rockshell plate 
				new GameItem(6130),// rockshell legs 
				new GameItem(2579),// wizzard boots  
				new GameItem(990, 5),// crystal key
				new GameItem(20703, 2),// daily gear box
				new GameItem(20791, 2),// daily skill box 
				new GameItem(4109),// light mystic hat  
				new GameItem(4113),// light mystic skirt  
				new GameItem(4111),// light mystick robe top  
				new GameItem(1632, 20),// uncut dragonstone
				new GameItem(7460),// rune gloves
				new GameItem(6686, 50),// saradomin brews-noted   
				new GameItem(4131),// rune boots
				new GameItem(386, 500),// cooked sharks-noted   
				new GameItem(995, 500000),// gold-aka gp-  
				new GameItem(2801))// medium clue scroll
		);
		
	items.put(Rarity.UNCOMMON,
			Arrays.asList(
					new GameItem(13307, 500),// blood money
					new GameItem(6199, 1),// mbox   
					new GameItem(3025, 75),// super restore(4)-noted  
					new GameItem(11944, 50),// lava dragon bones-noted  
					new GameItem(9244, 50),// dragonbolts(e)
					new GameItem(3842),// unholy book
					new GameItem(12851),// amulet of the damned
					new GameItem(11773),// berserker ring (i)
					new GameItem(990, 15),// crystal key
					new GameItem(20791, 3),// daily skill crate
					new GameItem(20703, 3),// daily gear crate   
					new GameItem(3840),// holy book 
					new GameItem(3844),// book of balance   
					new GameItem(6524),// obby shield  
					new GameItem(6528),// obby maul  
					new GameItem(6522, 500),// obby rings  
					new GameItem(3204),// d hally  
					new GameItem(20000),// d scim (or)  
					new GameItem(4214),// crystal bow full  
					new GameItem(9245, 50),// onyx bolts (e)  
					new GameItem(1377),// dragon battle axe  
					new GameItem(11230, 200),// dragon throwing darts  
					new GameItem(4153),// g maul  
					new GameItem(995, 1000000),// gold coins    
					new GameItem(2801, 3))// medium clue scroll
	);
		
		items.put(Rarity.RARE,
				Arrays.asList(
						new GameItem(11840),// dragon boots
						new GameItem(13307, 1500),// blood money
						new GameItem(10551),// fighter torso
						new GameItem(1409),// ibans staff
						new GameItem(13442, 500),// cooked angler fish-noted
						new GameItem(4675),// ancient staff
						new GameItem(6199, 3),// mbox
						new GameItem(990, 25),// crystal keys
						new GameItem(6889),// mages book  
						new GameItem(11230, 250),// dragon darts
						new GameItem(12696, 50),// super combat potions
						new GameItem(4151),// abbysal whip
						new GameItem(6585),// amulet of fury
						new GameItem(20791, 5),// skill crate
						new GameItem(20703, 5),//  gear box
						new GameItem(4716),// dh helm
						new GameItem(4720),// dh platebody
						new GameItem(4722),// dh legs
						new GameItem(4718),// dh great axe
						new GameItem(4708),// ahrims hood
						new GameItem(4710),// ahrims staff
						new GameItem(4712),// ahrims robe top
						new GameItem(4714),// ahrims robe bottom
						new GameItem(4753),// v helm
						new GameItem(4756),// v flail
						new GameItem(4759),// v brassard
						new GameItem(4757),// v skirt
						new GameItem(4745),// torags helm
						new GameItem(4749),// torags hammers
						new GameItem(4751),// torags platebody
						new GameItem(4747),// torags legs
						new GameItem(4724),// guthans helm
						new GameItem(4726),// guthans spear
						new GameItem(4728),// guthans body
						new GameItem(4730),// guthans chain legs
						new GameItem(4732),// karls coif
						new GameItem(4734),// karls crossbow
						new GameItem(4736),// karls top
						new GameItem(4738),// karls bottom
						new GameItem(995, 3000000),// gp
						new GameItem(2722, 3)));// hard clue scroll
	}

	/**
	 * The player object that will be triggering this event
	 */
	private Player player;

	/**
	 * Constructs a new myster box to handle item receiving for this player and this player alone
	 * 
	 * @param player the player
	 */
	public DailyGearBox(Player player) {
		this.player = player;
	}

	/**
	 * Opens a mystery box if possible, and ultimately triggers and event, if possible.
	 * 
	 * @param player the player triggering the evnet
	 */
	public void open() {
		if (System.currentTimeMillis() - player.lastMysteryBox < 150 * 4) {
			return;
		}
		if (player.getItems().freeSlots() < 2) {
			player.sendMessage("You need atleast two free slots to open a mystery box.");
			return;
		}
		if (!player.getItems().playerHasItem(MYSTERY_BOX)) {
			player.sendMessage("You need a daily gear box to do this.");
			return;
		}
		player.getItems().deleteItem(MYSTERY_BOX, 1);
		player.lastMysteryBox = System.currentTimeMillis();
		CycleEventHandler.getSingleton().stopEvents(this);
		CycleEventHandler.getSingleton().addEvent(this, this, 2);
	}

	/**
	 * Executes the event for receiving the mystery box
	 */
	@Override
	public void execute(CycleEventContainer container) {
		if (player.disconnected || Objects.isNull(player)) {
			container.stop();
			return;
		}
		int random = Misc.random(10);
		List<GameItem> itemList = random < 5 ? items.get(Rarity.COMMON) : random >= 5 && random <= 8 ? items.get(Rarity.UNCOMMON) : items.get(Rarity.RARE);
		GameItem item = Misc.getRandomItem(itemList);
		GameItem itemDouble = Misc.getRandomItem(itemList);
		
		if (Misc.random(200) == 1) {
			PlayerHandler.executeGlobalMessage("[<col=CC0000>Daily Box</col>] @cr20@ <col=255>" + player.playerName
					+ "</col> hit the jackpot on a Daily Gear Box!");
			switch(Misc.random(2)) {
			case 0:
				player.getItems().addItemUnderAnyCircumstance(12004, 1);
				break;
			case 1:
				player.getItems().addItemUnderAnyCircumstance(11286, 1);
				break;
			case 2:
				player.getItems().addItemUnderAnyCircumstance(11907, 1);
				break;
			}
		}

		if (Misc.random(25) == 0) {
			player.getItems().addItem(item.getId(), item.getAmount());
			player.getItems().addItem(itemDouble.getId(), itemDouble.getAmount());
			player.sendMessage("You receive <col=255>" + item.getAmount() + " x " + ItemAssistant.getItemName(item.getId()) + "</col>.");
			player.sendMessage("You receive <col=255>" + itemDouble.getAmount() + " x " + ItemAssistant.getItemName(itemDouble.getId()) + "</col>.");
		} else {
			//player.getItems().addItem(995, coins);
			player.getItems().addItem(item.getId(), item.getAmount());
			player.sendMessage("You receive <col=255>" + item.getAmount() + " x " + ItemAssistant.getItemName(item.getId()) + "</col>.");
		}
		container.stop();
	}

	/**
	 * Represents the rarity of a certain list of items
	 */
	enum Rarity {
		UNCOMMON, COMMON, RARE
	}

}