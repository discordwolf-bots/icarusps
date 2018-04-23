package ethos.model.minigames.raids.bosses;

import java.util.Arrays;
import java.util.Objects;

import ethos.Server;
import ethos.model.minigames.raids.RaidMonsters;
import ethos.model.npcs.NPC;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;

public class RaidRewards {

	public static void handleRewards(NPC npc) {
		RaidMonsters monster = RaidMonsters.getMonsterById(npc.npcType);
		
		if (monster == null) {
			return;
		}
		
		String npcName = Misc.capitalizeJustFirst(monster.name().replaceAll("_", " "));
		
		Arrays.stream(PlayerHandler.players)
			.filter(Objects::nonNull)
			.filter(p -> monster.isInBounds(p) && npc.getHeight() == p.getHeight()).forEach(p -> {
				int damage = p.getRaids().getDamage(monster);
				
				String prefix = Misc.startsWithVowel(npcName) ? "An " : "A ";
				
				if (monster.getIds().length == 1) {
					prefix = "";
				}
				
				p.setRaidPoints(p.getRaidPoints() + damage);
				String df = Misc.insertCommas(Integer.toString(damage));
				
				p.sendMessage(prefix + npcName+" has fallen. You receive "+df+" raid points.");
				p.sendMessage("You now have "+p.getRaidPoints()+" Raid Points.");
				p.getRaids().setDamage(monster, 0);
				
				if (npc.npcType == 7554) {
					Player raidLeader = p.getRaids().getRaidLeader();
					
					if (raidLeader == null)
						return;
					
					if (raidLeader.getRaids().getMvp() == null) {
						raidLeader.getRaids().setMvp(getMostDamage(npc, monster));
					}
					
					if (raidLeader.getIndex() == p.getIndex()) {
						Server.getGlobalObjects().add(new GlobalObject(-1, 3232, 5749, p.getRaids().raidHeight, 3, 10, 200, 30018)); // removes crystal for 120 seconds
						Server.getGlobalObjects().add(new GlobalObject(26273, 3235, 5749, p.getRaids().raidHeight, 1, 10, 200, -1)); // spawns rewards chest for 120 seconds
					}
					
					p.getPA().resetCamera();
					p.sendMessage("<col=FF0000>[Raid] Congratulations, Great Olm has been defeated!</col>");
					p.sendMessage("<col=FF0000>[Raid] Most damage dealt: "+raidLeader.getRaids().getMvp().getName()+"</col>");
				}
			}
		);
	}
	
	public static Player getMostDamage(NPC npc, RaidMonsters monster) {
		return Arrays.stream(PlayerHandler.players)
			.filter(Objects::nonNull)
			.filter(p -> Boundary.isInBounds(p, monster.getBoundary()) && npc.getHeight() == p.getHeight())
			.sorted((p1, p2) -> Integer.compare(p2.getRaids().getRaidTotal(), p1.getRaids().getRaidTotal()))
			.findFirst().get();
	}
	
	public static void givePoints(Player player, int npcId, int amount) {
		if (player == null) {
			return;
		}
		
		RaidMonsters monster = RaidMonsters.getMonsterById(npcId);
		
		if (monster == null) {
			return;
		}
		
		Boundary bounds = monster.getBoundary();
		
		if (!Boundary.isInBounds(player, bounds)) {
			return;
		}
		
		player.getRaids().increaseDamage(monster, amount);
		player.getRaids().setRaidTotal(player.getRaids().getRaidTotal() + amount);
	}
}
