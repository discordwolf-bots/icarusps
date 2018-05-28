package ethos.model.minigames.raids.bosses;

import ethos.model.content.dailytasks.DailyTasks;
import ethos.model.content.dailytasks.DailyTasks.PossibleTasks;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.util.Misc;

public class Vasa {

	public static boolean needRespawn = false;
	public static int respawnTimer = 0;
	public static int deathCount = 0;
	
	/*private static Boundary MYSTICS1 = new Boundary(1, 1, 1, 1);
	private static Boundary MYSTICS2 = new Boundary(1, 1, 1, 1);
	private static Boundary MYSTICS3 = new Boundary(1, 1, 1, 1);
	private static Boundary MYSTICS4 = new Boundary(1, 1, 1, 1);
	private static Boundary MYSTICS5 = new Boundary(1, 1, 1, 1);
	private static Boundary MYSTICS6 = new Boundary(1, 1, 1, 1);*/
	
	public static void rewardPlayers(Player player) {
		PlayerHandler.nonNullStream().filter(p -> Boundary.isIn(p, Boundary.SKELETAL_MYSTICS))
		.forEach(p -> {
			if (deathCount == 4) {
				int reward = p.getSkeletalMysticDamageCounter();
				p.sendMessage("@dre@Skeletal Mystics have been killed! Rewards have been dealt out!");
				p.sendMessage("@dre@You dealt " + p.getSkeletalMysticDamageCounter() + " damage toward skeletal mystics; granting " + reward + " raid points.");
				//p.getItems().addItemUnderAnyCircumstance(995, reward);
				p.setRaidPoints(p.getRaidPoints() + p.getSkeletalMysticDamageCounter());
				DailyTasks.increase(p, PossibleTasks.SKELETAL_MYSTICS_RAID);
				if (Misc.random(20) == 0) {
					p.getItems().addItemUnderAnyCircumstance(405, 1);
					p.sendMessage("@pur@You noticed a Slayer Casket has dropped and picked it up right away.");
				}
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
