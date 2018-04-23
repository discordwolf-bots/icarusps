package ethos.model.players.packets.commands.owner;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Spawn a specific Object.
 * 
 * @author Emiel
 *
 */
public class Objectanim extends Command {

	@Override
	public void execute(Player c, String input) {
		//String[] args = input.split(" ");
		//Server.getGlobalObjects().add(new GlobalObject(Integer.parseInt(args[0]), c.absX, c.absY, c.getHeight(), 0, 10, -1, -1)); 
		
		//Server.getGlobalObjects().add(new GlobalObject(29881, 3220, 5738, raidHeight, 3, 10, -1, -1));
		//Server.getGlobalObjects().add(new GlobalObject(29884, 3220, 5743, raidHeight, 3, 10, -1, -1));
		//Server.getGlobalObjects().add(new GlobalObject(29887, 3220, 5733, raidHeight, 3, 10, -1, -1));
		
		c.getPA().sendPlayerObjectAnimation(c, 3220, 5738, Integer.parseInt(input), 10, 3, c.getHeight());
		c.sendMessage("Object Animation: "+input+"");
		
		/*CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (container.getOwner() == null || c == null || c.isDead) {
					container.stop();
					return;
				}
				//7377
				int cycle = 7500 - container.getTotalTicks();
				
				c.getPA().sendPlayerObjectAnimation(c, 3220, 5738, cycle, 10, 3, c.getHeight());
				c.sendMessage("Animation: "+cycle+"");
				
				if (cycle == 0) {
					container.stop();
				}
			}
		}, 3);*/
		
	}
}
