package ethos.model.players.packets.commands.owner;

import java.util.Map.Entry;

import org.apache.commons.lang3.text.WordUtils;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Send the item IDs of all matching items to the player.
 * 
 * @author Emiel
 *
 */
public class Getid extends Command {

	@Override
	public void execute(Player c, String input) {
		if (input.length() < 3) {
			c.sendMessage("You must give at least 3 letters of input to narrow down the item.");
			return;
		}
		
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
			for (int i = 8144; i < 8195; i++) {
				c.getPA().sendFrame126("", i);
			}
			// The frames that it can see
			int[] frames = { 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173,
					8174, 8175, 8176, 8177, 8178, 8179, 8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194 };
			c.getPA().sendFrame126("@dre@Search for @blu@" + input + "", 8144);
			c.getPA().sendFrame126("", 8145);
			
			int frameIndex = 0;
			for (int j = 0; j < Server.itemHandler.ItemList.length; j++) {
				if(frameIndex > frames.length - 1) 
					break; 
				if (Server.itemHandler.ItemList[j] != null && Server.itemHandler.ItemList[j].itemDescription != null
						&& !Server.itemHandler.ItemList[j].itemDescription.equalsIgnoreCase("null")) {
					if (Server.itemHandler.ItemList[j].itemName.replace("_", " ").toLowerCase().contains(input.toLowerCase())) {
						c.getPA().sendFrame126("<col=663399><shad=000000>" + Server.itemHandler.ItemList[j].itemName.replace("_", " ") + "</shad></col> : @blu@" + Server.itemHandler.ItemList[j].itemId , frames[frameIndex]);
						frameIndex++;
					}
				}
			}
			c.getPA().showInterface(8134);
	}
}
