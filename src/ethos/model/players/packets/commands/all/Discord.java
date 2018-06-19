package ethos.model.players.packets.commands.all;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Opens the vote page in the default web browser.
 * 
 * @author Emiel
 */
public class Discord extends Command {

	@Override
	public void execute(Player c, String input) {
		c.sendMessage("<col=00ff00><shad=000000>Loading Discord</shad></col>");
		c.getPA().sendString("https://discord.gg/wqMpNB", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Invites you to our Discord server");
	}

}
