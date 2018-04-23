package ethos.model.minigames.raids;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import ethos.Server;
import ethos.model.content.instances.InstancedAreaManager;
import ethos.model.content.instances.SingleInstancedArea;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.skills.agility.AgilityHandler;
import ethos.world.Clan;
import ethos.world.objects.GlobalObject;

public class Raids {
	
	public int[] damageDealt = new int[30];
	
	private SingleInstancedArea raidInstance;
	
	public static final int START_X = 3305;
	public static final int START_Y = 5196;
	
	public int raidHeight;
	public int activeBraziers;
	private int raidTotal;
	
	private NPC iceDemon;
	private NPC tekton;
	private NPC muttadile;
	private NPC vasa;
	
	public boolean brazier1 = false;
	public boolean brazier2 = false;
	public boolean brazier3 = false;
	public boolean brazier4 = false;
	
	private NPC[] lizardShaman;
	private NPC[] skeletalMages;
	private NPC[] deathly;
	private NPC[] vanguards;
	private NPC[] greatOlm;
	public GlobalObject[] greatOlmObj;
	
	private Player player;
	private Player mvp;
	
	public Raids(Player player) {
		this.player = player;
	}
	
	public void initRaids() {
		this.killAllSpawns();
		this.reset();
		
		activeBraziers = 0;
		raidHeight = InstancedAreaManager.getSingleton().getNextOpenHeight(Boundary.RAIDS);
		raidInstance = new RaidInstance(player, Boundary.RAIDS, raidHeight);
		raidInstance.getPlayer();
		
		player.getPA().removeAllWindows();
		
		/*CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (container.getOwner() == null || player == null || player.isDead || player.getRaids() == null) {
					container.stop();
					return;
				}
				
				int cycle = container.getTotalTicks();
				
				if (cycle == 1) {
					player.getPA().sendScreenFade("", 1, 3);
				} else if (cycle == 6) {
					teleportToStart();
					player.getPA().sendScreenFade("", -1, 3);
					container.stop();
				}
			}
		}, 1);*/
		
		teleportToStart();
		InstancedAreaManager.getSingleton().add(raidHeight, raidInstance);
		spawnSkeletals();
	}
	
	public void reset() {
		this.damageDealt = new int[30];
		this.raidTotal = 0;
		this.mvp = null;
		this.iceDemon = null;
		this.tekton = null;
		this.muttadile = null;
		this.vasa = null;
		this.lizardShaman = null;
		this.skeletalMages = null;
		this.deathly = null;
		this.vanguards = null;
		this.greatOlm = null;
		this.brazier1 = false;
		this.brazier2 = false;
		this.brazier3 = false;
		this.brazier4 = false;
	}
	
	public void teleportToStart() {
		player.getPA().movePlayer(START_X, START_Y, raidHeight);
	}
	
	public boolean handleObjects(int objectId, int objX, int objY) {
		
		if (objectId == 29750) {
			if (player.getY() != 5367) {
				return false;
			}
			
			int newX = 10;
			int newY = 0;
			
			if (player.getX() <= 3318) {
				newX = 3323 - player.getX();
			} else if (player.getX() > 3318) {
				newX = 3313 - player.getX();
			}
			
			player.sendMessage("Moving: "+newX+" "+newY+"");
			player.getAgilityHandler().move(player, newX, newY, AgilityHandler.LOG_EMOTE, -1);
			return true;
		}
		
		Player raidLeader = this.getRaidLeader();
		
		if (raidLeader == null) {
			return false;
		}
		
		RaidInstance instance = (RaidInstance) raidLeader.getRaids().getRaidInstance();
		
		if (instance == null) {
			player.sendMessage("null instance");
			return false;
		}
		
		if (objectId == 29789) {
			if (objX == 3307 && objY == 5205) { // To lizard shaman
				player.getPA().movePlayer(3307, 5208);
				return true;
			}
				
			if (objX == 3311 && objY == 5276) { // Lizard Shaman Exit to Vasa
				int left = getLizardsRemaining(raidLeader);
				if (left > 0) {
					player.sendMessage("You can not leave until the room has been cleared.");
					player.sendMessage("There "+(left == 1 ? "is" : "are")+" "+left+" Lizard Shaman"+(left == 1 ? "" : "s")+" remaining.");
					return true;
				}
				player.getPA().movePlayer(3311, 5279);
				return true;
			}
				
			if (objX == 3311 && objY == 5308) { // Vasa exit to Vanguards
				if (!raidLeader.getRaids().isVasaDead()) {
					player.sendMessage("You can not leave until Vasa has been defeated.");
					return true;
				}
				player.getPA().movePlayer(3311, 5311);
				return true;
			}
			
			if (objX == 3311 && objY == 5341) { // vanguard exit to ice demon
				int left = getVanguardsRemaining(raidLeader);
				if (left > 0) {
					player.sendMessage("You can not leave until the room has been cleared.");
					player.sendMessage("There "+(left == 1 ? "is" : "are")+" "+left+" Vanguard"+(left == 1 ? "" : "s")+" remaining.");
					return true;
				}
				player.getPA().movePlayer(3312, 5344);
				return true;
			}
			
			if (objX == 3310 && objY == 5370) { // ice demon exit to thieving room
				if (!raidLeader.getRaids().isIceDemonDead()) {
					player.sendMessage("You can not leave until Ice Demon has been defeated.");
					return true;
				}
				player.getPA().movePlayer(3311, 5373);
				return true;
			} 
		
			
			if (objX == 3318 && objY == 5400) { // thieving room exit to Mages
				player.getPA().movePlayer(3312, 5218, raidHeight + 1);
				return true;
			} 
			
			if (objX == 3309 && objY == 5274) { // mage exit to tekton
				int left = getMysticsRemaining(raidLeader);
				if (left > 0) {
					player.sendMessage("You can not leave until the room has been cleared.");
					player.sendMessage("There "+(left == 1 ? "is" : "are")+" "+left+" Skeletal Mystic"+(left == 1 ? "" : "s")+" remaining.");
					return true;
				}
				player.getPA().movePlayer(3310, 5277);
			} 
			
			if (objX == 3310 && objY == 5306) { // tekton exit to muttadiles
				if (!raidLeader.getRaids().isTektonDead()) {
					player.sendMessage("You can not leave until Tekton has been defeated.");
					return true;
				}
				player.getPA().movePlayer(3311, 5309);
			}
			
			if (objX == 3308 && objY == 5337) { // muttadiles exit to magers/rangers
				if (!raidLeader.getRaids().isMuttadileDead()) {
					player.sendMessage("You can not leave until Muttadile has been defeated.");
					return true;
				}
				player.getPA().movePlayer(3309, 5340);
			}
			
			if (objX == 3309 && objY == 5369) { // magers/rangers exit to Great Olm
				int left = getDeathlyRemaining(raidLeader);
				if (left > 0) {
					player.sendMessage("You can not leave until the room has been cleared.");
					player.sendMessage("There "+(left == 1 ? "is" : "are")+" "+left+" enem"+(left == 1 ? "y" : "ies")+" remaining.");
					return true;
				}
				player.getPA().movePlayer(3276, 5159, raidHeight);
			}
			return true;
		}
		
		if (objectId == 29734) { // to great olm
			player.getPA().movePlayer(3233, 5720, raidHeight);
			return true;
		}
		
		if (objectId == 29996) { // to great olm
			player.getPA().movePlayer(3233, 5720, raidHeight);
			return true;
		}
		
		if (objectId == 29879) { // olm barrier
			player.getPA().movePlayer(3233, 5730, raidHeight);
			player.getPA().shakeScreen(3, 3, 3, 3);
			player.olmShake = true;
			spawnOlm();
			return true;
		}

		if (objectId == 30066) {
			if (player.specRestore > 0) {
				int seconds = ((int)Math.floor(player.specRestore * 0.6));
				player.sendMessage("You have to wait another " + seconds + " seconds to use the energy well.");
				return true;
			}
			
			player.specRestore = 120;
			player.specAmount = 10.0;
			player.getItems().addSpecialBar(player.playerEquipment[player.playerWeapon]);
			player.playerLevel[5] = player.getPA().getLevelForXP(player.playerXP[5]);
			player.getHealth().removeAllStatuses();
			player.getHealth().reset();
			player.getPA().refreshSkill(5);
			player.sendMessage("You touch the energy well");
			return true;
		}
		
		player.sendMessage("Clicked "+objectId+" at "+objX+", "+objY+"");
		return false;
	}

	public void spawnSkeletals() {
		muttadile 	= NPCHandler.spawn(7563, 3306, 5330, raidHeight + 1, 1, 750, 25, 400, 400);
		vasa 		= NPCHandler.spawn(7566, 3309, 5292, raidHeight, -1, 650, 25, 250, 300);
		iceDemon 	= NPCHandler.spawn(7585, 3310, 5368, raidHeight, -1, 750, 45, 350, 300);
		tekton 		= NPCHandler.spawn(7544, 3310, 5293, raidHeight + 1, -1, 1200, 45, 450, 300);
		
		lizardShaman = new NPC[] {
			NPCHandler.spawn(7573, 3304, 5262, raidHeight, 1, 350, 25, 300, 300),
			NPCHandler.spawn(7573, 3312, 5258, raidHeight, 1, 350, 25, 300, 300),
			NPCHandler.spawn(7573, 3303, 5262, raidHeight, 1, 350, 25, 300, 300)
		};
		
		skeletalMages = new NPC[] {
			NPCHandler.spawn(7604, 3318, 5262, raidHeight + 1, -1, 250, 25, 400, 250),
			NPCHandler.spawn(7605, 3303, 5262, raidHeight + 1, -1, 250, 25, 500, 250),
			NPCHandler.spawn(7606, 3312, 5258, raidHeight + 1, -1, 250, 25, 400, 250)
		};
		
		deathly = new NPC[] {
			NPCHandler.spawn(7559, 3316, 5370, raidHeight + 1, -1, 150, 25, 100, 100), // deathly ranger
			NPCHandler.spawn(7559, 3317, 5369, raidHeight + 1, -1, 150, 25, 100, 100), // deathly ranger
			NPCHandler.spawn(7559, 3318, 5370, raidHeight + 1, -1, 150, 30, 100, 100), // deathly ranger
			
			NPCHandler.spawn(7560, 3319, 5363, raidHeight + 1, -1, 150, 25, 100, 100), // deathly mager
			NPCHandler.spawn(7560, 3318, 5364, raidHeight + 1, -1, 150, 25, 100, 100), // deathly mager
			NPCHandler.spawn(7560, 3317, 5363, raidHeight + 1, -1, 150, 30, 100, 100), // deathly mager
		};
		
		vanguards = new NPC[] {
			NPCHandler.spawn(7527, 3317, 5328, raidHeight, -1, 300, 25, 140, 200), // melee vanguard
			NPCHandler.spawn(7528, 3308, 5324, raidHeight, -1, 300, 25, 140, 200), // range vanguard
			NPCHandler.spawn(7529, 3307, 5334, raidHeight, -1, 300, 25, 140, 200), // magic vanguard
		};
	}
	
	public void spawnOlm() {
		if (greatOlm != null && !isOlmDead()) {
			return;
		}
		
		greatOlmObj = new GlobalObject[] {
			new GlobalObject(29881, 3220, 5738, raidHeight, 3, 10, -1, -1),
			new GlobalObject(29884, 3220, 5743, raidHeight, 3, 10, -1, -1),
			new GlobalObject(29887, 3220, 5733, raidHeight, 3, 10, -1, -1)
		};
		
		Arrays.stream(greatOlmObj).forEach(object -> Server.getGlobalObjects().add(object));
		
		greatOlm = new NPC[] { 
			NPCHandler.spawn(7553, 3223, 5733, raidHeight, -1, 500, 33, 272, 272), // left claw
			NPCHandler.spawn(7554, 3223, 5737, raidHeight, -1, 1600, 33, 272, 272), // olm head
			NPCHandler.spawn(7555, 3223, 5742, raidHeight, -1, 500, 33, 272, 272), // right claw
		};
	}
	
	public void killAllSpawns() {
		if (iceDemon != null)
			iceDemon.isDead = true;
		if (tekton != null)
			tekton.isDead = true;
		if (muttadile != null)
			muttadile.isDead = true;
		if (vasa != null)
			vasa.isDead = true;
		
		if (lizardShaman != null)
			Arrays.stream(lizardShaman).filter(Objects::nonNull).forEach(npc -> npc.isDead = true);
		if (skeletalMages != null)
			Arrays.stream(skeletalMages).filter(Objects::nonNull).forEach(npc -> npc.isDead = true);
		if (vanguards != null)
			Arrays.stream(vanguards).filter(Objects::nonNull).forEach(npc -> npc.isDead = true);
		if (deathly != null)
			Arrays.stream(deathly).filter(Objects::nonNull).forEach(npc -> npc.isDead = true);
		if (this.greatOlm != null) {
			Arrays.stream(greatOlm).filter(Objects::nonNull).forEach(npc -> npc.isDead = true);
			Arrays.stream(greatOlmObj).filter(Objects::nonNull).forEach(object -> Server.getGlobalObjects().remove(object));
		}
	}
	
	public static void lightBrazer(Player player, int id, int objX, int objY) {
		Player raidLeader = player.getRaids().getRaidLeader();
		
		if (raidLeader == null) {
			return;
		}
		
		if (!player.getItems().playerHasItem(20799, 1)) {
			player.sendMessage("You need some kindling to light this brazier!");
			return;
		}
		
		int height = raidLeader.getRaids().getRaidInstance().getHeight();
		
		Server.getGlobalObjects().replace(
				new GlobalObject(29747, objX, objY, height, 0, 10, 200, -1),
				new GlobalObject(29748, objX, objY, height, 0, 10, 200, 29747));
		
		player.getItems().deleteItem(20799, 1);
		
		if (objX == 3307 && objY == 5368)
			raidLeader.getRaids().lightBrazier1(true);
		if (objX == 3309 && objY == 5365)
			raidLeader.getRaids().lightBrazier2(true);
		if (objX == 3312 && objY == 5365)
			raidLeader.getRaids().lightBrazier3(true);
		if (objX == 3314 && objY == 5368)
			raidLeader.getRaids().lightBrazier4(true);
		
		raidLeader.sendMessage("Someone has lit one of the braziers!");
	}
	
	public Player getRaidLeader() {
		Clan clan = player.clan;
		
		if (clan == null) {
			return null;
		}
		
		Optional<Player> raidLeader =  Arrays.stream(PlayerHandler.players)
				.filter(Objects::nonNull)
				.filter(pl -> pl.getName().equalsIgnoreCase(clan.getFounder()))
				.findFirst();
		
		if (!raidLeader.isPresent() || raidLeader.get().getRaids().getRaidInstance() == null) {
			return null;
		}
		
		return raidLeader.get();
	}
	
	public boolean isMuttadileDead() {
		return muttadile != null && muttadile.isDead;
	}
	
	public boolean isIceDemonDead() {
		return iceDemon != null && iceDemon.isDead;
	}
	
	public boolean isTektonDead() {
		return tekton != null && tekton.isDead;
	}
	
	public boolean isVasaDead() {
		return vasa != null && vasa.isDead;
	}
	
	public int getMysticsRemaining(Player raidLeader) {
		return (int) Arrays.stream(raidLeader.getRaids().skeletalMages).filter(n -> !n.isDead).count();
	}
	
	public int getVanguardsRemaining(Player raidLeader) {
		return (int) Arrays.stream(raidLeader.getRaids().vanguards).filter(n -> !n.isDead).count();
	}
	
	public int getLizardsRemaining(Player raidLeader) {
		return (int) Arrays.stream(raidLeader.getRaids().lizardShaman).filter(n -> !n.isDead).count();
	}
	
	public int getDeathlyRemaining(Player raidLeader) {
		return (int) Arrays.stream(raidLeader.getRaids().deathly).filter(n -> !n.isDead).count();
	}
	
	public boolean isInAcid() {
		return Server.getGlobalObjects().get(30032, player.getX(), player.getY(), player.getHeight()) != null;
	}
	
	public void createAcidPool() {
		GlobalObject object = new GlobalObject(30032, player.getX(), player.getY(), player.getHeight(), 3, 10, 50, -1);
		Server.getGlobalObjects().add(object);
	}
	
	public int getDamage(RaidMonsters monster) {
		return damageDealt[monster.ordinal()];
	}
	
	public int getTotalDamage() {
		return Arrays.stream(damageDealt).sum();
	}
	
	public void setDamage(RaidMonsters monster, int amount) {
		this.damageDealt[monster.ordinal()] = amount;
	}
	
	public void increaseDamage(RaidMonsters monster, int amount) {
		this.damageDealt[monster.ordinal()] += amount;
	}
	
	public void resetDamage() {
		this.damageDealt = new int[10];
	}
	
	public int getRaidTotal() {
		return raidTotal;
	}
	
	public void setRaidTotal(int amount) {
		this.raidTotal = amount;
	}
	
	public boolean isOlmDead() {
		Player raidLeader = player.getRaids().getRaidLeader();
		
		if (raidLeader == null) {
			return false;
		}
		
		return greatOlm != null && Arrays.stream(raidLeader.getRaids().greatOlm).filter(n -> !n.isDead).count() == 0;
	}
	
	public boolean handsDead() {
		Player raidLeader = player.getRaids().getRaidLeader();
		
		if (raidLeader == null) {
			return false;
		}
		
		if (raidLeader.getRaids().greatOlm ==  null) {
			player.sendMessage("Null Olm?");
			return false;
		}
		
		return raidLeader.getRaids().greatOlm[0].isDead && raidLeader.getRaids().greatOlm[2].isDead;
	}
	
	public void lightBrazier1(boolean value) {
		this.brazier1 = value;
	}
	public void lightBrazier2(boolean value) {
		this.brazier2 = value;
	}
	public void lightBrazier3(boolean value) {
		this.brazier3 = value;
	}
	public void lightBrazier4(boolean value) {
		this.brazier4 = value;
	}
	
	public boolean isBraziersLit() {
		return brazier1 && brazier2 && brazier3 && brazier4 && brazier4;
	}
	
	public void setMvp(Player player) {
		this.mvp = player;
	}
	
	public Player getMvp() {
		return mvp;
	}
	
	public SingleInstancedArea getRaidInstance() {
		return raidInstance;
	}
	
}
