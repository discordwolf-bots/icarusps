package ethos.model.players.packets.commands.all;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Site extends Command {

	@Override
	public void execute(Player c, String input) {
		String[] args = input.split(" ");
		
		switch (args[0]) {
		
		case "":
			c.sendMessage("Usage: ::site forums");
			break;
		case "home":
			c.sendMessage("<col=ff0000><shad=000000>Website coming soon</shad></col>");
			break;
		case "forums":
			c.sendMessage("<col=ff0000><shad=000000>Forums coming soon</shad></col>");
			break;
		case "discord":
			c.getPA().sendString("https://discord.gg/QWzRp6c", 12000);
			break;	
		case "donate":
			//c.getPA().sendFrame126("https://icarusps.com/store", 12000);
			c.sendMessage("<col=ff0000><shad=000000>Store coming soon</shad></col>");
			break;
		case "highscores":
			c.sendMessage("<col=ff0000><shad=000000>Highscores coming soon</shad></col>");
			break;
		case "vote":
			c.sendMessage("<col=ff0000><shad=000000>Vote coming soon</shad></col>");
			break;
		case "news":
			c.sendMessage("<col=ff0000><shad=000000>News coming soon</shad></col>");
			break;
		case "updates":
			c.sendMessage("<col=ff0000><shad=000000>Updates coming soon</shad></col>");
			break;
		case "knowledge":
			c.sendMessage("<col=ff0000><shad=000000>Knowledge Base coming soon</shad></col>");
			break;
		case "apply":
			c.sendMessage("<col=ff0000><shad=000000>Applications coming soon</shad></col>");
			break;
		case "events":
			c.sendMessage("<col=ff0000><shad=000000>Events coming soon</shad></col>");
			break;
		case "guides":
			c.sendMessage("<col=ff0000><shad=000000>Guides coming soon</shad></col>");
			break;
		case "media":
			c.sendMessage("<col=ff0000><shad=000000>Media coming soon</shad></col>");
			break;
		case "goals":
			c.getPA().sendString("", 12000);
			break;
		case "market":
			c.getPA().sendString("", 12000);
			break;
		case "clans":
			c.getPA().sendString("", 12000);
			break;
		case "help":
			c.getPA().sendString("", 12000);
			break;
		case "suggest":
			c.getPA().sendString("", 12000);
			break;
		case "poll":
			c.getPA().sendString("", 12000);
			break;
		case "maps":
			c.getPA().sendString("", 12000);
			break;
		}
	}
	@Override
	public Optional<String> getDescription() {
		return Optional.of("You can visit all our different sites using this command");
	}
}
