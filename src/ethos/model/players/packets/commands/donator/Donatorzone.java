package ethos.model.players.packets.commands.donator;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Teleports the player to the donator zone.
 * 
 * @author Emiel
 */
public class Donatorzone extends Command {

	@Override
	public void execute(Player c, String input) {
		if (c.inTrade || c.inDuel || c.inWild()) {
			return;
		}
		c.getPA().startTeleport(2953, 3880, 0, "modern");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to the donator zone");
	}

}
