package ethos.model.players.packets.commands.admin;

import ethos.Config;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Give the player virtually infinite HP, Special Attack energy and Prayer.
 * 
 * @author Emiel
 */
public class Godmode extends Command {

	@Override
	public void execute(Player c, String input) {
		if (c.inGodmode()) {
			c.playerLevel[Config.ATTACK] = c.getLevelForXP(c.playerXP[Config.ATTACK]);
			c.getPA().refreshSkill(Config.ATTACK);
			c.playerLevel[Config.STRENGTH] = c.getLevelForXP(c.playerXP[Config.STRENGTH]);
			c.getPA().refreshSkill(Config.STRENGTH);
			c.getHealth().setMaximum(c.getLevelForXP(c.playerXP[Config.HITPOINTS]));
			c.getHealth().reset();
			c.playerLevel[Config.PRAYER] = c.getLevelForXP(c.playerXP[Config.PRAYER]);
			c.getPA().refreshSkill(Config.PRAYER);
			c.specAmount = 10.0;
			c.getPA().requestUpdates();
			c.setSafemode(false);
			c.setGodmode(false);
			c.sendMessage("Godmode deactivated. Return to base for debriefing.");
		} else {
			c.playerLevel[Config.STRENGTH] = 9999;
			c.getPA().refreshSkill(Config.STRENGTH);
			c.playerLevel[Config.ATTACK] = 9999;
			c.getPA().refreshSkill(Config.ATTACK);
			c.getHealth().setMaximum(9999);
			c.getHealth().reset();
			c.playerLevel[Config.PRAYER] = 9999;
			c.getPA().refreshSkill(Config.PRAYER);
			c.specAmount = 9999;
			c.getPA().requestUpdates();
			c.setSafemode(true);
			c.setGodmode(true);
			c.sendMessage("Godmode activated. Good luck soldier!");
		}
	}
}
