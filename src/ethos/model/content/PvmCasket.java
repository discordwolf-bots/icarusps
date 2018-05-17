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
					new GameItem(386, 250),  
					new GameItem(995, 10000), 
					new GameItem(987, 5),
					new GameItem(560, 2500),
					new GameItem(565, 2500),
					new GameItem(562, 3000),
					new GameItem(566, 2500),
					new GameItem(985, 5),
					new GameItem(6128),
					new GameItem(405, 3),
					new GameItem(13226),
					new GameItem(11230, 500),
					new GameItem(12934, 2500),
					new GameItem(6129),
					new GameItem(19484, 250),
					new GameItem(9243, 500),
					new GameItem(9244, 300),
					new GameItem(6130),
					new GameItem(537, 75), 
					new GameItem(1704, 10), 
					new GameItem(1215, 5), 
					new GameItem(3145, 300),
					new GameItem(2441, 50),
					new GameItem(2437, 50),
					new GameItem(6686, 50),
					new GameItem(20791, 2),
					new GameItem(20703, 2),
					new GameItem(6528),
					new GameItem(13307, 500),
					new GameItem(1377),
					new GameItem(3204),
					new GameItem(7158),
					new GameItem(1187),
					new GameItem(6524),
					new GameItem(3025, 50),  
					new GameItem(995, 500000))
					
			);
			
		items.put(Rarity.UNCOMMON,
				Arrays.asList(
						new GameItem(861),
						new GameItem(537, 250),
						new GameItem(6568, 3),
						new GameItem(13116),
						new GameItem(4212),
						new GameItem(4724),
						new GameItem(4726),
						new GameItem(4728),
						new GameItem(4730),
						new GameItem(4736),
						new GameItem(4738),
						new GameItem(11840),
						new GameItem(11663),
						new GameItem(11664),
						new GameItem(11665),
						new GameItem(8839),
						new GameItem(405, 5),
						new GameItem(8840),
						new GameItem(6737),
						new GameItem(6733),
						new GameItem(6731),
						new GameItem(19675),
						new GameItem(12696, 100),
						new GameItem(6686, 200),
						new GameItem(3025, 200),
						new GameItem(13307, 750),
						new GameItem(6536, 3),
						new GameItem(6524, 3),
						new GameItem(6525, 3),
						new GameItem(6526, 3),
						new GameItem(6522, 400),
						new GameItem(6527, 3),			
						new GameItem(392, 500), 
						new GameItem(1409),
						new GameItem(995, 1000000))
						
		);
			
			items.put(Rarity.RARE,
					Arrays.asList(
							new GameItem(13307, 1500),
							new GameItem(990, 20),
							new GameItem(11128),
							new GameItem(11286),
							new GameItem(13442, 1000),
							new GameItem(11937, 1000),
							new GameItem(11889),
							new GameItem(2513),
							new GameItem(2577),
							new GameItem(10551),
							new GameItem(10548),
							new GameItem(6914),
							new GameItem(19529),
							new GameItem(6889),
							new GameItem(13271),
							new GameItem(13263),
							new GameItem(12831),
							new GameItem(12904),
							new GameItem(11907),
							new GameItem(12851),
							new GameItem(12004),
							new GameItem(19481),
							new GameItem(13072),
							new GameItem(11798),
							new GameItem(13073),
							new GameItem(12785),
							new GameItem(11944, 200), 
							new GameItem(11840),
							new GameItem(11235),
							new GameItem(6585),
							new GameItem(4151, 1),
							new GameItem(995, 4000000),
							new GameItem(6199, 5),
							new GameItem(11818, 1)));
							
							
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