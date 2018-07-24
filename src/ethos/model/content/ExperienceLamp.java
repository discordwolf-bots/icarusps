package ethos.model.content;

import java.text.NumberFormat;

import ethos.model.players.Player;
import ethos.model.players.skills.Skill;

public class ExperienceLamp {
	
	public Player player;
	
	public ExperienceLamp(Player player) {
		this.player = player;
	}
	
	private static int startButtons = 146235;
	
	
	public void registerClick(int button) {
		int skillID = button - startButtons;
		
		if(button == 147000) skillID = 21;
		player.antiqueSelect = skillID;
		String skillName = Skill.forId(skillID).name().toLowerCase();
		
		int level = player.playerLevel[skillID];
		
		skillName = skillName.substring(0, 1).toUpperCase() + skillName.substring(1);
		player.getPA().sendFrame126(skillName + ":", 37607);
		player.getPA().sendFrame126("Current Level: @whi@" + player.playerLevel[skillID], 37608);
		player.getPA().sendFrame126("Reward: @whi@" + NumberFormat.getInstance().format((level*1000)) + " XP", 37609);
	}
	
	public void gainExperience() {
		player.usingLamp = false;
		if(!player.getItems().playerHasItem(2528, 1)) {
			player.getPA().closeAllWindows();
			return;
		}
		
		player.getPA().addSkillXP(player.playerLevel[player.antiqueSelect]*1000, player.antiqueSelect, true);
		int skillIcon = 45 + player.antiqueSelect;
		String skillName = Skill.forId(player.antiqueSelect).name().toLowerCase();
		skillName = skillName.substring(0, 1).toUpperCase() + skillName.substring(1);
		player.sendMessage("<img=" + skillIcon + "> You gain " + NumberFormat.getInstance().format((player.playerLevel[player.antiqueSelect]*1000)) + " experience in " + skillName);
		player.getItems().deleteItem2(2528, 1);
		
		if(!player.getItems().playerHasItem(2528, 1)) {
			player.getPA().closeAllWindows();
			return;
		}
	}
	
	public boolean experienceClicking(Player player, int button) {
		if(button >= startButtons && button <= startButtons+20 || button == 147000) {
			registerClick(button);
			return true;
		}
		if(button == 147059) {
			gainExperience();
			return true;
		}
		if(button == 146226) {
			player.getPA().closeAllWindows();
			return true;
		}
		return false;
	}

}
