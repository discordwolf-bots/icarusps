package ethos.model.players.packets.commands.all;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

import java.util.Optional;

/**
 * Opens the store page in the default web browser.
 * 
 * @author Emiel
 */
public class Donate extends Command {

	@Override
	public void execute(Player c, String input) {
		c.sendMessage("<col=ff0000><shad=000000>Donations coming soon</shad></col>");
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page with our donation store");
	}

}
