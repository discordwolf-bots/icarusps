package ethos.model.players.packets.commands.owner;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Addxp extends Command {

	@Override
	public void execute(Player c, String input) {
		String[] args = input.split("-");
		if(args.length < 2) {
			c.sendMessage("<col=ff0000><shad=000000>Error!</shad></col> Correct syntax: ::addxp-skillid-xp");
			return;
		}
		c.getPA().addSkillXP(Integer.parseInt(args[1]), Integer.parseInt(args[0]), true);
	}

}
