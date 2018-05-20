package ethos.model.content;

import ethos.event.CycleEvent;
import ethos.event.CycleEventContainer;
import ethos.event.CycleEventHandler;
import ethos.model.items.GameItem;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.util.Misc;

import java.util.*;

/**
 * Revamped a simple means of receiving a random item based on chance.
 * 
 * @author Jason MacKeigan
 * @date Oct 29, 2014, 1:43:44 PM
 */
public class PvmCasket extends CycleEvent {

	/**
	 * The item id of the PvM Casket required to trigger the event
	 */
	public static final int MYSTERY_BOX = 405; //Casket

	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static Map<Rarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 */
	static {
		items.put(Rarity.COMMON, 
			Arrays.asList(
					new GameItem(386, 250),// cooked sharks 
					new GameItem(995, 10000),// gp 
					new GameItem(987, 5),// loop half
					new GameItem(560, 2500),// death rune
					new GameItem(565, 2500),// blood rune
					new GameItem(562, 3000),//  chaos rune
					new GameItem(566, 2500),// soul rune
					new GameItem(985, 5),// tooth half
					new GameItem(6128),// rockshell helm
					new GameItem(405, 3),// pvm casket
					new GameItem(13226),// herbsack
					new GameItem(11230, 500),// dragon darts
					new GameItem(12934, 2500),// zulrah scales
					new GameItem(6129),// rockshell plate
					new GameItem(19484, 250),// dragon javelin
					new GameItem(9243, 500),// diamond b olts e
					new GameItem(9244, 300),// dragon bolts e
					new GameItem(6130),// rockshell platelegs
					new GameItem(537, 75),// dragon bones
					new GameItem(1705, 10),// amjulet of glory
					new GameItem(1215, 5),// dragon dagger
					new GameItem(3145, 300),// cooked karambwan
					new GameItem(2441, 50),// super strength
					new GameItem(2437, 50),// super attack
					new GameItem(6686, 50),// saradomin brews
					new GameItem(20791, 2),// skill crate
					new GameItem(20703, 2),// gear box
					new GameItem(6528),// obsidian maul
					new GameItem(13307, 500),// blood money
					new GameItem(1377),// dragon battleaxe
					new GameItem(3204),// dragon hally
					new GameItem(7158),// dragon 2h
					new GameItem(1187),// dragon square shield
					new GameItem(6524),// obby shield
					new GameItem(3025, 50),  // super restore(4)
					new GameItem(995, 500000))// gp
					
			);
			
		items.put(Rarity.UNCOMMON,
				Arrays.asList(
						new GameItem(861),// magic short bow
						new GameItem(537, 250),// dragon bones
						new GameItem(6568, 3),// obsidian cape
						new GameItem(4212),// new crystal bow
						new GameItem(4724),// guthans helm
						new GameItem(4726),// guthans spear
						new GameItem(4728),// guthans chain body
						new GameItem(4730),// guthans chain skirt
						new GameItem(4736),// karls leather top
						new GameItem(4738),// karls crossbow
						new GameItem(11840),// dragon boots
						new GameItem(405, 5),// pvm casket
						new GameItem(19675),// arc light
						new GameItem(12696, 100),// super combat potion
						new GameItem(6686, 200),// saradomin brew
						new GameItem(3025, 200),// super restore (4)
						new GameItem(13307, 750),// blood moeny
						new GameItem(6522, 400),// obby rings
						new GameItem(6536),// obby shield		
						new GameItem(392, 500),// manta ray
						new GameItem(1409),// ibans staff
						new GameItem(995, 1000000))// gp
						
		);
			
			items.put(Rarity.RARE,
					Arrays.asList(
							new GameItem(13307, 1500),// blood money
							new GameItem(990, 20),// ckeys
							new GameItem(11128),// berserker neclass
							new GameItem(11286),// dragonic vissage
							new GameItem(13442, 1000),// angler fish
							new GameItem(11937, 1000),// dark crabs
							new GameItem(11889),// z hasta
							new GameItem(2513),// d chainbody
							new GameItem(2577),// ranger boots
							new GameItem(10551),// fighter torso
							new GameItem(10548),// fighter hat
							new GameItem(6914),// master wand
							new GameItem(19529),// zenyte shard
							new GameItem(6889),// mages book
							new GameItem(13271),// abby dagger
							new GameItem(13263),// abby bludgeon
							new GameItem(12831),// blessed spirit shield
							new GameItem(12904),// toxic staff of the dead
							new GameItem(11907),// trident of the seas
							new GameItem(12851),// amulet of the dead
							new GameItem(12004),// kraken tentacle
							new GameItem(19481),// heavy balista
							new GameItem(11798),// godsword blade
							new GameItem(12785),// ring of wealth i
							new GameItem(11944, 200), // lava dragon bones
							new GameItem(11840),// d boots
							new GameItem(11235),// d bow
							new GameItem(6585),// fury
							new GameItem(4151, 1),// whip
							new GameItem(995, 4000000),// gp
							new GameItem(6199, 5),// mbox
							new GameItem(11818, 1)));// gs shard 1
							
							
	}

	/**
	 * The player object that will be triggering this event
	 */
	private Player player;

	/**
	 * Constructs a new PvM Casket to handle item receiving for this player and this player alone
	 * 
	 * @param player the player
	 */
	public PvmCasket(Player player) {
		this.player = player;
	}

	/**
	 * Opens a PvM Casket if possible, and ultimately triggers and event, if possible.
	 *
	 */
	public void open() {
		if (System.currentTimeMillis() - player.lastMysteryBox < 150 * 4) {
			return;
		}
		if (player.getItems().freeSlots() < 2) {
			player.sendMessage("You need atleast two free slots to open a PvM Casket.");
			return;
		}
		if (!player.getItems().playerHasItem(MYSTERY_BOX)) {
			player.sendMessage("You need PvM Casket to do this.");
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
		int coins = 50000 + Misc.random(15000);
		int coinsDouble = 100000 + Misc.random(50000);
		int random = Misc.random(100);
		List<GameItem> itemList = random < 55 ? items.get(Rarity.COMMON) : random >= 55 && random <= 80 ? items.get(Rarity.UNCOMMON) : items.get(Rarity.RARE);
		GameItem item = Misc.getRandomItem(itemList);
		GameItem itemDouble = Misc.getRandomItem(itemList);

		if (Misc.random(10) == 0) {
			player.getItems().addItem(995, coins + coinsDouble);
			player.getItems().addItem(item.getId(), item.getAmount());
			player.getItems().addItem(itemDouble.getId(), itemDouble.getAmount());
			player.sendMessage("You receive <col=255>" + item.getAmount() + " x " + ItemAssistant.getItemName(item.getId()) + "</col>, and <col=255>"
					+ Misc.insertCommas(Integer.toString(coins)) + "</col>GP.");
			player.sendMessage("You receive <col=255>" + itemDouble.getAmount() + " x " + ItemAssistant.getItemName(itemDouble.getId()) + "</col>, and <col=255>"
					+ Misc.insertCommas(Integer.toString(coins)) + "</col>GP.");
			PlayerHandler.executeGlobalMessage("<img=10>" + Misc.formatPlayerName(player.playerName) + " just got very lucky and hit the double!");
			PlayerHandler.executeGlobalMessage("<img=10>" + Misc.formatPlayerName(player.playerName) + " has received <col=255>" + item.getAmount() + " x " + ItemAssistant.getItemName(item.getId())
					+ "</col> and <col=255>" + itemDouble.getAmount() + " x " + ItemAssistant.getItemName(itemDouble.getId()) + "</col> from a PvM Casket.");
		} else {
			player.getItems().addItem(995, coins);
			player.getItems().addItem(item.getId(), item.getAmount());
			player.sendMessage("You receive <col=255>" + item.getAmount() + " x " + ItemAssistant.getItemName(item.getId()) + "</col>, and <col=255>"
					+ Misc.insertCommas(Integer.toString(coins)) + "</col>GP.");
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