package ethos.model.minigames.raids.bosses;

import ethos.model.players.Player;

public class GreatOlm {
	
	public static boolean isInArea(Player p) {
		return (p.getX() >= 3220 && p.getY() >= 5710 && p.getX() <= 3246 && p.getY() <= 5755);
	}
	
	public static boolean isInCombatArea(Player p) {
		return (p.getX() >= 3227 && p.getY() >= 5731 && p.getX() <= 3237 && p.getY() <= 5748);
	}


}
