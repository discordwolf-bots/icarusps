package ethos.model.players.packets.commands.owner;

import ethos.model.content.instances.InstancedAreaManager;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Raidtest extends Command {

	@Override
	public void execute(Player player, String input) {
		if (player.getRaids().getRaidInstance() != null)
			InstancedAreaManager.getSingleton().disposeOf(player.getRaids().getRaidInstance());
		player.getRaids().initRaids();
	}
}
