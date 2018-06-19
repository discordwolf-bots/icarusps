package ethos.model.minigames.raids.bosses;

import ethos.model.content.dailytasks.DailyTasks;
import ethos.model.content.dailytasks.DailyTasks.PossibleTasks;
import ethos.model.minigames.raids.RaidMonsters;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;

public class SkeletalMystic {
	
	public static boolean needRespawn = false;
	public static int respawnTimer = 0;
	public static int deathCount = 0;
	
	public static void rewardPlayerss(Player player) {
		PlayerHandler.nonNullStream().filter(p -> Boundary.isIn(p, Boundary.SKELETAL_MYSTICS)).forEach(p -> {
			if (deathCount == 4) {
				int reward = p.getSkeletalMysticDamageCounter();
				p.sendMessage("@dre@Skeletal Mystics have been killed! Rewards have been dealt out!");
				p.sendMessage("@dre@You dealt " + reward + " damage toward skeletal mystics; granting " + reward + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + reward);
				DailyTasks.increase(p, PossibleTasks.SKELETAL_MYSTICS_RAID);
				p.getRaids().setDamage(RaidMonsters.SKELETAL_MYSTIC, 0);
				p.setSkeletalMysticDamageCounter(0);
			} else {
				p.sendMessage("@dre@" + deathCount + "/4 Skeletal Mystics have been killed so far.");
			}
		});
		
		if (deathCount == 4) {
			deathCount = 0;
			respawnTimer = 20;
			needRespawn = true;
		}
	}

}
