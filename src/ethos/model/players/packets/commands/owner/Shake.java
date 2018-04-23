package ethos.model.players.packets.commands.owner;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Shake extends Command {

	@Override
	public void execute(Player player, String input) {
		if (input.equalsIgnoreCase("reset")) {
			player.getPA().resetCamera();
			//player.getPA().shakeScreen(1, 0, 0, 0);
		} else {
			String[] args = input.split(" ");
			int amount = Integer.parseInt(args[0]);
			int speed = Integer.parseInt(args[1]);
			player.getPA().shakeScreen(amount, speed, amount, speed);
		}
		
	}

}
