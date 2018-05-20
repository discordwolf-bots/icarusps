package ethos.model.players.sacrifice;

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
		UNCOMMON, COMMON, RARE, VERY_RARE
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
				new GameItem(4012, 1),
				new GameItem(454, 500),  
				new GameItem(1778, 500), 
				new GameItem(10551, 1),
				new GameItem(775, 1), 
				new GameItem(2572, 1), 
				new GameItem(775, 1), 
				new GameItem(2572, 1), 
				new GameItem(775, 1), 
				new GameItem(2572, 1), 
				new GameItem(13442, 25), 
				new GameItem(11937, 25), 
				new GameItem(2577, 1), 	
				new GameItem(775, 1), 
				new GameItem(2572, 1), 
				new GameItem(1514, 20),
				new GameItem(1516, 30), 
				new GameItem(1518, 40), 
				new GameItem(1520, 50), 
				new GameItem(396, 100), 
				new GameItem(395, 1),
				new GameItem(1603, 1),
				new GameItem(1603, 1),
				new GameItem(395, 1),
				new GameItem(1603, 1),
				new GameItem(1604, 15),
				new GameItem(67, 15),
				new GameItem(1606, 15),
				new GameItem(1608, 15),
				new GameItem(537, 25),
				new GameItem(4587),
				new GameItem(5680),
				new GameItem(1434),
				new GameItem(7090),
				new GameItem(3751),
				new GameItem(3749),
				new GameItem(3755),
				new GameItem(3753),
				new GameItem(4587),
				new GameItem(5680),
				new GameItem(1434),
				new GameItem(7090),
				new GameItem(3749),
				new GameItem(4587),
				new GameItem(5680),
				new GameItem(1434),
				new GameItem(7090),
				new GameItem(3751),
				new GameItem(1610, 15),
				new GameItem(226, 15),  
				new GameItem(448, 70),
				new GameItem(450, 50),  
				new GameItem(995, 5000),  
				new GameItem(7409, 1), 
				new GameItem(1704, 1),
				new GameItem(13307, 100), 
				new GameItem(2996, 200), 
				new GameItem(13067, 100), 
				new GameItem(12753, 1))
		);
		
	items.put(Rarity.UNCOMMON,
			Arrays.asList(
					new GameItem(4566), 
					new GameItem(452, 45), 
					new GameItem(12428), 
					new GameItem(6739, 1),
					new GameItem(71, 20),
					new GameItem(12439), 
					new GameItem(12397), 				
					new GameItem(452, 500), 
					new GameItem(2364, 350),
					new GameItem(12412), 
					new GameItem(12357), 
					new GameItem(13442, 75), 
					new GameItem(11937, 75), 
					new GameItem(12351),
					new GameItem(1419), 
					new GameItem(1305, 1),
					new GameItem(11235, 1),
					new GameItem(4084), 
					new GameItem(4212),				
					new GameItem(4708),
					new GameItem(4710),
					new GameItem(4712),
					new GameItem(4714),
					new GameItem(11840),
					new GameItem(11731, 1),							
					new GameItem(12848),		
					new GameItem(2513),
					new GameItem(9740),
					new GameItem(11230, 40),
					new GameItem(811, 50),
					new GameItem(11128),
					new GameItem(11130),
					new GameItem(4716),
					new GameItem(4718),
					new GameItem(4720),
					new GameItem(4722),
					new GameItem(4724),
					new GameItem(4726),
					new GameItem(4728),
					new GameItem(4730),				
					new GameItem(4732),
					new GameItem(4734),
					new GameItem(4736),
					new GameItem(4738),
					new GameItem(4740),
					new GameItem(4745),	
					new GameItem(7158),
					new GameItem(4747),
					new GameItem(4749),
					new GameItem(1514, 50),
					new GameItem(1516, 50), 
					new GameItem(1518, 60), 
					new GameItem(11944, 25),
					new GameItem(1520, 70), 
					new GameItem(4751),
					new GameItem(4753),
					new GameItem(19670, 25),
					new GameItem(4755),
					new GameItem(4757),
					new GameItem(4759),							
					new GameItem(4224),
					new GameItem(12432),
					new GameItem(12422),
					new GameItem(12424),
					new GameItem(12426),
					new GameItem(12379), 
					new GameItem(12391), 
					new GameItem(12849), 
					new GameItem(4151), 
					new GameItem(12786),
					new GameItem(12783), 
					new GameItem(1615, 1),
					new GameItem(12798), 
					new GameItem(13307, 200), 
					new GameItem(2996, 400), 
					new GameItem(12337))
	);
		
		items.put(Rarity.RARE,
				Arrays.asList(
						new GameItem(11818, 1), 
						new GameItem(11820, 1), 
						new GameItem(11822, 1), 
						new GameItem(13442, 100), 
						new GameItem(11937, 100),
						new GameItem(11941, 1), 
						new GameItem(1038, 1), 
						new GameItem(1040, 1), 
						new GameItem(1042, 1),
						new GameItem(995, 10000000),			
						new GameItem(1044, 1), 
						new GameItem(1046, 1), 
						new GameItem(1048, 1), 
						new GameItem(1050, 1), 
						new GameItem(1037, 1), 
						new GameItem(1053, 1),
						new GameItem(1055, 1), 
						new GameItem(1057, 1), 
						new GameItem(13307, 400),
						new GameItem(2996, 600),  
						new GameItem(12437, 1), 
						new GameItem(12424, 1), 
						new GameItem(12426, 1), 
						new GameItem(12422, 1),
						new GameItem(12359, 1), 
						new GameItem(12849, 1), 
						new GameItem(12802, 1), 
						new GameItem(12800, 1), 
						new GameItem(12798, 1),
						new GameItem(2697, 1),
						new GameItem(12373, 1)));
	}
	
	public void SacrificeItem(int itemID) {
		GameItem item = new GameItem(itemID, 1);//TODO: CHANGE TO item.getAmount()
		c.sendMessage("Exchanging your item (" + Item.getItemName(itemID) + ")...");
		
		//If the item used on the chest is a ticket then exchange the tickets
		for(int i = 5020; i <= 5023; i++) {
			if(itemID == i) {
				ExchangeTickets(item);
				break;
			}
		}
		
		//If the item used on the chest is a ticket then return out of the method
		if(itemID == 5020 || itemID == 5021 || itemID == 5022 || itemID == 5023)
			return;
		
		//Delete the item used on the chest
		c.getItems().deleteItem(itemID, 1);
		
		//Depending on the rarity of the item it will give you the equivalent in ticket
		switch(GetRarity(item)) {
			case COMMON:
				c.getItems().addItemUnderAnyCircumstance(5020, 1);//Common Ticket
				break;
				
			case UNCOMMON:
				c.getItems().addItemUnderAnyCircumstance(5021, 1);//Uncommon Ticket
				break;
				
			case RARE:
				c.getItems().addItemUnderAnyCircumstance(5022, 1);//Rare Ticket
				break;
				
			case VERY_RARE:
				c.getItems().addItemUnderAnyCircumstance(5023, 1);//Very Rare Ticket
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
