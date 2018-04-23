package ethos.model.players.packets.commands.owner;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.world.objects.GlobalObject;

/**
 * Spawn a specific Object.
 * 
 * @author Emiel
 *
 */
public class Object extends Command {

	@Override
	public void execute(Player c, String input) {
		String[] args = input.split(" ");
		if (args.length < 2) {
			GlobalObject object = new GlobalObject(Integer.parseInt(args[0]), c.getX(), c.getY(), c.getHeight(), 3, 10, 50, -1);
			Server.getGlobalObjects().add(object);
			c.sendMessage("Object: " + Integer.parseInt(args[0]) + ", Type: 10");
		} else {
			GlobalObject object = new GlobalObject(Integer.parseInt(args[0]), c.getX(), c.getY(), c.getHeight(), Integer.parseInt(args[1]), 10, 50, -1);
			Server.getGlobalObjects().add(object);
			c.sendMessage("Object: " + Integer.parseInt(args[0]) + ", Type: 10");
		}
	}
}
