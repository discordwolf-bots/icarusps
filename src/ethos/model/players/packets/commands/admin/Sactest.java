package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.model.players.sacrifice.*;

/**
 * Open the banking interface.
 * 
 * @author Emiel
 */
public class Sactest extends Command {

	private Sacrifice sacrifice;
	@Override
	public void execute(Player c, String input) {
		sacrifice = new Sacrifice(c);
		sacrifice.SacrificeItem(Integer.parseInt(input));
	}
}
