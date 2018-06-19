package ethos.model.players.packets.commands.all;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

import java.util.Optional;

/**
 * Open the forums in the default web browser.
 * 
 * @author Emiel
 */
public class Forums extends Command {

	@Override
	public void execute(Player c, String input) {
		c.sendMessage("<col=ff0000><shad=000000>Forums coming soon</shad></col>");
		//c.getPA().sendFrame126("https://icarusps.com/community/index.php", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens a web page with our forums");
	}

}
