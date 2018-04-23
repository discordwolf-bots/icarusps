package ethos.model.players.packets.commands.all;

import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.model.players.packets.commands.Command;

import java.util.Optional;

/**
 * New Starter - Temp @Tyler
 */
public class Starter extends Command {

	@Override
	public void execute(Player c, String input) {
		if(!c.getRights().isOrInherits(Right.IRONMAN)) {
			c.getPA().addStarter();
		} else {
			c.sendMessage("You cannot use this command as an <col=ff0000><shad=000000>Ironman</shad></col>");
		}

	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Gives you a starter after reset");
	}

}
