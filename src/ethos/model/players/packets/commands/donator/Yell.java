package ethos.model.players.packets.commands.donator;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.Commands;
import ethos.model.players.packets.commands.Command;

/**
 * Tells the player they need to be a donator to use this feature.
 * 
 * @author Emiel
 */
public class Yell extends Command {

	@Override
	public void execute(Player player, String input) {
		Command yell = Commands.COMMAND_MAP.get("ethos.model.players.packets.commands.all.Yell");
		yell.execute(player, input);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Sends a global chat message");
	}

	@Override
	public Optional<String> getParameter() {
		return Optional.of("message");
	}
}
