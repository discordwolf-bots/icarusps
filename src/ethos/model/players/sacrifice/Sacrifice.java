package ethos.model.players.sacrifice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ethos.model.items.GameItem;
import ethos.model.items.Item;
import ethos.model.players.Player;

public class Sacrifice {
	
	private Player c;

	public Sacrifice(Player c) {
		this.c = c;
	}
	
	public static enum Rarity {
		UNCOMMON, COMMON, RARE, VERY_RARE, LEGENDARY
	}
	
	/**
	 * A map containing a List of {@link GameItem}'s that contain items relevant to their rarity.
	 */
	private static Map<Rarity, List<GameItem>> items = new HashMap<>();

	/**
	 * Stores an array of items into each map with the corresponding rarity to the list
	 * 
	 * This is a list of all items allowed to be used on the Black Chest if item is not on the list then
	 * it is defaulted to COMMON
	 */
	static {
		items.put(Rarity.COMMON, 
			Arrays.asList(
				new GameItem(1519),
				new GameItem(1521)
			)
		);
		
	items.put(Rarity.UNCOMMON,
			Arrays.asList(
				new GameItem(1048),
				new GameItem(1050)
			)
	);
		
		items.put(Rarity.RARE,
				Arrays.asList(
						new GameItem(995),
						new GameItem(1046)
				)
		);
		
		items.put(Rarity.VERY_RARE,
				Arrays.asList(
						new GameItem(4151),
						new GameItem(4153)
				)
		);
		
		items.put(Rarity.LEGENDARY,
				Arrays.asList(
						new GameItem(11802)
				)
		);
	}
	
	private List<Integer> sacrificeList = new ArrayList<>();
	
	public void SacrificeItem(int itemID) {
		GameItem item = new GameItem(itemID, 1);//TODO: CHANGE TO item.getAmount()
		c.sendMessage("Exchanging your item (" + Item.getItemName(itemID) + ")...");
		
		//If the item used on the chest is a ticket then exchange the tickets
		for(int i = 5020; i <= 5023; i++) {
			if(itemID == i) {
				ExchangeTickets(item);
				continue;
			}
		}
		
		//If the item used on the chest is a ticket then return out of the method
		if(itemID == 5020 || itemID == 5021 || itemID == 5022 || itemID == 5023)
			return;
		
		//Delete the item used on the chest
		
		int amount = c.getItems().getItemAmount(itemID);
		c.getItems().deleteItem(itemID, amount);
		
		//Depending on the rarity of the item it will give you the equivalent in ticket
		switch(GetRarity(item)) {
			case COMMON:
				c.getItems().addItemUnderAnyCircumstance(5020, amount);//Common Ticket
				break;
				
			case UNCOMMON:
				c.getItems().addItemUnderAnyCircumstance(5021, amount);//Uncommon Ticket
				break;
				
			case RARE:
				c.getItems().addItemUnderAnyCircumstance(5022, amount);//Rare Ticket
				break;
				
			case VERY_RARE:
				c.getItems().addItemUnderAnyCircumstance(5023, amount);//Very Rare Ticket
				break;
			
			default:
				
				break;
			
		}
	}
	
	public void ExchangeTickets(GameItem item) {
		if(!c.getItems().playerHasItem(item.getId(), 5)) {
			c.sendMessage("You need atleast 5 tickets to get an item of the next rarity!");
		} else {
			switch(item.getId()) {
				case 5020:
					c.getItems().deleteItem(5020, 5);
					c.getItems().addItemUnderAnyCircumstance(GetRandomItemFromRarity(Rarity.UNCOMMON).getId(), 1);
					break;
					
				case 5021:
					c.getItems().deleteItem(5021, 5);
					c.getItems().addItemUnderAnyCircumstance(GetRandomItemFromRarity(Rarity.RARE).getId(), 1);
					break;
					
				case 5022:
					c.getItems().deleteItem(5022, 5);
					c.getItems().addItemUnderAnyCircumstance(GetRandomItemFromRarity(Rarity.VERY_RARE).getId(), 1);
					break;
					
				case 5023:
					c.sendMessage("You already have the highest rarity of items!");
					break;
					
				default:
					c.sendMessage("You cannot convert that item!");
					break;
			}
		}
	}
	
	public void SacrificeAll() {
		AddItemsToSacrificeList();
		for(int i = 0; i < sacrificeList.size(); i++) {
			SacrificeItem(sacrificeList.get(i));
		}
	}
	
	public void AddItemsToSacrificeList() {
		if(!sacrificeList.isEmpty())
			sacrificeList.clear();
	
		for(int i = 0; i < 28; i++) {
			GameItem item = new GameItem(c.playerItems[i]);
			System.out.println(item.getId());
			if(CanSacrificeItem(item.getId())) {
				sacrificeList.add(item.getId()-1);
			}
		}
	}
	
	public boolean CanSacrificeItem(int itemID) {
		GameItem item = new GameItem(itemID);
		for (Map.Entry<Rarity, List<GameItem>> _item : items.entrySet()) {
			List<GameItem> itemList = _item.getValue();
			for(GameItem x : itemList) {
				if(x.getId() == item.getId()-1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public GameItem GetRandomItemFromRarity(Rarity rarity) { //Gets a random item from the given rarity
		Random rand = new Random();
		GameItem[] values = null;
		for (Map.Entry<Rarity, List<GameItem>> _item : items.entrySet()) {
			Rarity itemRarity = _item.getKey();
			List<GameItem> itemList = _item.getValue();
			if(itemRarity == rarity) {
				values = (GameItem[])itemList.toArray();
			}
		}
		return values[rand.nextInt(values.length)];
	}
	
	public Rarity GetRarity(GameItem item){ //Gets the rarity from the given item (If the item is not in the array it will be defaulted to COMMON)
		for (Map.Entry<Rarity, List<GameItem>> _item : items.entrySet()) {
			Rarity itemRarity = _item.getKey();
			List<GameItem> itemList = _item.getValue();
			
			for(GameItem x : itemList) {
				if(x.getId() == item.getId()) {
					c.sendMessage("You have received a " + itemRarity.toString().toLowerCase() + " ticket!");
					return itemRarity;
				}
			}
		}
		return Rarity.COMMON;
	}
	
}
