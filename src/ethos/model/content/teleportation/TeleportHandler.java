package ethos.model.content.teleportation;

import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.world.ItemHandler;
//import ethos.model.content.teleport.TeleportExecutor;
//import ethos.model.content.teleport.Position;
import ethos.Config;

import ethos.model.content.achievement_diary.western_provinces.WesternDiaryEntry;
import ethos.model.items.GroundItem;
import ethos.model.content.achievement_diary.ardougne.ArdougneDiaryEntry;
import ethos.model.content.achievement_diary.falador.FaladorDiaryEntry;
import ethos.model.content.achievement_diary.kandarin.KandarinDiaryEntry;
import ethos.model.content.achievement_diary.fremennik.FremennikDiaryEntry;
import ethos.model.content.achievement_diary.morytania.MorytaniaDiaryEntry;
import ethos.model.content.achievement_diary.lumbridge_draynor.LumbridgeDraynorDiaryEntry;
import ethos.event.impl.WheatPortalEvent;

/**
 * Teleport Handler Class - Used for our new teleporting system Probably should
 * have used enums, oh well..
 * 
 * @author Tyler
 */
public class TeleportHandler {

	private Player c;

	public TeleportHandler(Player player) {
		this.c = player;
	}

	String[] monsterNames = { "Select your Monster Teleport", "Rock Crabs", "Cows", "Yaks", "Bob's Island",
			"Desert Bandits", "Elf Warriors", "Dagannoths", "Chickens", "Slayer Tower", "Brimhaven Dungeon",
			"Taverley Dungeon", "Stronghold Cave", "Relleka Dungeon", "Mithril Dragons", "Asgarnian Ice Cave",
			"Catacombs", "Cave Kraken", "Smoke Devils", "", "" };
	String[] minigameNames = { "Select your Minigame Teleport", "Raids", "Warriors Guild", "Pest Control",
			"Fight Caves", "Barrows", "Clan Wars", "Shayzien Assault", "Mage Arena", "Duel Arena", "", "", "", "", "",
			"", "", "", "", "", "" };
	String[] bossNames = { "Select your Boss Teleport", "Barrelchest", "Dagannoth Kings",
			"King Black Dragon@red@(Wild)", "Giant Mole", "Kalphite Queen", "God Wars Dungeon", "Corporeal Beast",
			"Dagannoth Mother", "Kraken", "Zulrah", "Cerberus", "Smoke Devil", "Abyssal Sire", "Demonic Gorillas",
			"Lizardman Shaman", "", "", "", "", "" };
	String[] wildernessNames = { "Select your Wilderness Teleport", "Green Dragons @red@(10)", "Mage Bank",
			"Dark Castle @red@(15)", "Hill Giants Multi @red@(18)", "Wildy Agility Course @red@(52)",
			"Vet'ion @red@(40) ", "Callisto @red@(43)", "Scorpia @red@(54)", "Venenatis @red@(28)",
			"Chaos Elemental @red@(50)", "Chaos Fanatic @red@(41)", "Crazy Archaeologist @red@(23)",
			"Skeletal Wyverns @red@(48)", "East Dragons @red@(18)", "Wildy Volcano @red@(53)", "Chaos Altar @red@(15)",
			"Lava Dragons @red@(43)", "", "", "" };
	String[] cityNames = { "Select your City Teleport", "Varrock", "Yanille", "Edgeville", "Lumbridge", "Ardougne",
			"Neitiznot", "Karamja", "Falador", "Taverly", "Camelot", "Catherby", "Al Kharid", "Morytania", "Shilo Village", "Waterbirth", "Lletya", "Burthorpe", "Entrana", "Draynor", "Trollheim" };
	String[] donatorNames = { "Select your Donator Teleport", "Donator Zone", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "" };
	String[] otherNames = { "Select your Skilling Teleport", "Imp hunting", "Lands End", "Fishing",
			"Woodcutting Guild", "Farming Patches", "Agility - Grace", "Hunter", "Puro Puro", "Mining", "", "",
			"", "", "", "", "", "", "", "", "" };

	public void loadMonsterTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(monsterNames[j], ids[j]);
		}
	}

	public void loadMinigameTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(minigameNames[j], ids[j]);
		}
	}

	public void loadBossTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(bossNames[j], ids[j]);
		}
	}

	public void loadWildernessTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(wildernessNames[j], ids[j]);
		}
	}

	public void loadCityTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(cityNames[j], ids[j]);
		}
	}

	public void loadDonatorTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(donatorNames[j], ids[j]);
		}
	}

	public void loadOtherTab() {
		int[] ids = { 65033, 65051, 65053, 65055, 65057, 65059, 65061, 65063, 65065, 65067, 65069, 65071, 65073, 65075,
				65077, 65079, 65081, 65083, 65085, 65087, 65089 };
		for (int j = 0; j <= 20; j++) {
			c.getPA().sendFrame126(otherNames[j], ids[j]);
		}
	}

	public void loadTab(Player player, int tab) {
		if (player.teleSelected == 0) {
			loadMonsterTab();
		} else if (player.teleSelected == 1) {
			loadMinigameTab();
		} else if (player.teleSelected == 2) {
			loadBossTab();
		} else if (player.teleSelected == 3) {
			loadWildernessTab();
		} else if (player.teleSelected == 4) {
			loadCityTab();
		} else if (player.teleSelected == 5) {
			loadDonatorTab();
		} else if (player.teleSelected == 6) {
			loadOtherTab();
		}
	}

	public void selection(Player player, int i) {
		player.teleSelected = i;
		loadTab(player, i);
	}

	public boolean teleportCheck(Player player) {
		/*
		 * if (!player.modeTut) { player.
		 * sendMessage("You must finish the tutorial before teleporting anywhere.");
		 * return false; }
		 */
		return true;
	}

	public void handleTeleports(Player player, int Id) {
		if (player.inWild() && player.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			// player.sendMessage("You can not open a teleport interface while above level
			// 20 wilderness.");
			return;
		}
		
		if(player.getRights().isOrInherits(Right.HCIM_DEAD)) {
			return;
		}

		switch (Id) {
		// Handle Magic Book Teleports
		case 4140: // Monsters
		case 50235:
		case 117112:
			player.getPA().showInterface(65000);
			selection(player, 0);
			//player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 4143: // Minigames
		case 50245:
		case 117123:
			 player.getPA().showInterface(65000);
			 selection(player, 1);
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 4146: // Bosses
		case 50253:
		case 117131:
			 player.getPA().showInterface(65000);
			 selection(player, 2);
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 4150: // Wilderness
		case 51005:
		case 117154:
			 player.getPA().showInterface(65000);
			 selection(player, 3);
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 6004: // City
		case 51013:
		case 117162:
			 player.getPA().showInterface(65000);
			 selection(player, 4);
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 6005: // Donator
		case 51023:
		case 117186:
			 if (c.getRights().isOrInherits(Right.DONATOR) || c.getRights().isOrInherits(Right.MODERATOR)) {
			 player.getPA().showInterface(65000);
			 selection( player, 5);
			 } else {
			 player.sendMessage("You must be a donator to use this.");
			 }
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 29031: // Other
		case 51031:
		case 117194:
			 player.getPA().showInterface(65000);
			 selection(player, 6);
//			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;
		case 51039:
		case 72038:
		case 117210:
			player.sendMessage("If you wish to teleport, please talk to Wizard Mizgog in Edgeville.");
			break;

		// Start of Tabs
		case 253234: // Monsters Tab
			selection(player, 0);
			break;
		case 253237: // Minigames Tab
			selection(player, 1);
			break;
		case 253240: // Bosses Tab
			selection(player, 2);
			break;
		case 253243: // Wilderness Tab
			selection(player, 3);
			break;
		case 253246: // City Tab
			selection(player, 4);
			break;
		case 253249: // Donator Tab
			// if (player.getRights().isDonator() || player.getRights().isSuperDonator() ||
			// player.getRights().isLegendaryDonator() ||
			// player.getRights().isExtremeDonator() ||
			// c.getRights().isOrInherits(Right.OWNER))
			selection(player, 5);
			// else
			// player.sendMessage("You must be a donator to use this.");
			break;
		case 253252: // Other Tab
			selection(player, 6);
			break;
		// End of Tabs

		// Start of Buttons
		case 254026: // Button 1
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Rock Crabs
				player.getPA().startTeleport(2673, 3710, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[1] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Raids
				player.getPA().startTeleport(1245, 3561, 0, "modern"); // change
				player.sendMessage("Teleporting to " + minigameNames[1] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Barrelchest
				player.getPA().startTeleport(2910, 3612, 0, "modern");// change
				player.sendMessage("Teleporting to " + bossNames[1] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Green Dragons
				player.getPA().startTeleport(2976, 3591, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[1] + ".");
			} else if (player.teleSelected == 4) { // City - Varrock
				player.getPA().startTeleport(3210, 3424, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[1] + ".");
			} else if (player.teleSelected == 5) { // Donator - Donator Zone
				if (c.amDonated >= 10 || c.getRights().isOrInherits(Right.OWNER)) {
					player.getPA().startTeleport(2135, 4913, 0, "modern");
					player.sendMessage("Teleporting to " + donatorNames[1] + ".");
				} else {
					player.sendMessage("You must be a donator to access this.");
					return;
				}
			} else if (player.teleSelected == 6) { // Other - Imp hunting
				player.getPA().startTeleport(3041, 4534, 0, "modern"); // change
				player.sendMessage("Teleporting to " + otherNames[1] + ".");
			}
			break;
		case 254028: // Button 2
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monster - Cow
				player.getPA().startTeleport(3260, 3272, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[2] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Warriors Guild
				player.getPA().startTeleport(2874, 3546, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[2] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Daggononoth Kings
				player.getPA().startTeleport(1913, 4367, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[2] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Mage Bank
				player.getPA().startTeleport(2539, 4716, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[2] + ".");
			} else if (player.teleSelected == 4) { // City - Yanille
				player.getPA().startTeleport(2606, 3093, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[2] + ".");
			} else if (player.teleSelected == 6) { // Other - Lands end
				player.getPA().startTeleport(1504, 3419, 0, "modern"); // change
				player.sendMessage("Teleporting to " + otherNames[2] + ".");
			}
			break;

		case 254030:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monster - Yaks
				player.getPA().startTeleport(2326, 3801, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[3] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Pest Control
				player.getPA().startTeleport(2660, 2648, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[3] + ".");
				player.getDiaryManager().getWesternDiary().progress(WesternDiaryEntry.PEST_CONTROL_TELEPORT);
			} else if (player.teleSelected == 2) { // Bosses - King Black Dragon
				player.getPA().startTeleport(3005, 3849, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[3] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Dark Castle
				player.getPA().startTeleport(3020, 3632, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[3] + ".");
			} else if (player.teleSelected == 4) { // City - Edgeville
				player.getPA().startTeleport(3093, 3493, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[3] + ".");
			} else if (player.teleSelected == 6) { // Other - fishing
				player.getPA().startTeleport(1589, 3571, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[3] + ".");
			}
			break;
		case 254032:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Bob's Island
				player.getPA().startTeleport(2524, 4775, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[4] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Fight Caves
				player.getPA().startTeleport(2444, 5179, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[4] + ".");
				player.sendMessage("The minigame entrance can be found to the south!");
			} else if (player.teleSelected == 2) { // Bosses - Giant Mole
				player.getPA().startTeleport(1760, 5161, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[4] + ".");
				// player.sendMessage("Right click and Look-inside mole hills to fight the Giant
				// Mole!");
			} else if (player.teleSelected == 3) { // Wilderness - Hill Giants Multi
				player.getPA().startTeleport(3304, 3657, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[4] + ".");
			} else if (player.teleSelected == 4) { // City - Lumbridge
				player.getPA().startTeleport(3222, 3218, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[4] + ".");
				c.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.LUMBRIDGE_TELEPORT);
			} else if (player.teleSelected == 6) { // Other - Woodcutting Guild
				player.getPA().startTeleport(1658, 3505, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[4] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254034:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Desert Bandit
				player.getPA().startTeleport(3176, 2987, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[5] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Barrows
				player.getPA().startTeleport(3565, 3316, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[5] + ".");
				GroundItem spade = new GroundItem(952, 3565, 3316, 0, 1, 60, player.getName());
				ItemHandler.createGlobalItem(spade);
			} else if (player.teleSelected == 2) { // Bosses - Kalphite Queen Tunnels
				player.getPA().startTeleport(3510, 9496, 2, "modern");
				player.sendMessage("Teleporting to " + bossNames[5] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Wilderness Agility Course
				player.getPA().startTeleport(3003, 3934, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[5] + ".");
			} else if (player.teleSelected == 4) { // City - Ardougne
				player.getPA().startTeleport(2662, 3305, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[5] + ".");
				c.getDiaryManager().getArdougneDiary().progress(ArdougneDiaryEntry.TELEPORT_ARDOUGNE);
			} else if (player.teleSelected == 6) { // Other - Farming Patches
				player.getPA().startTeleport(3003, 3376, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[5] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254036:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Elf Warrior
				player.getPA().startTeleport(2897, 2725, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[6] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Clan Wars
				player.getPA().startTeleport(3387, 3158, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[6] + ".");
			} else if (player.teleSelected == 2) { // Bosses - God Wars Dungeon
				player.getPA().startTeleport(2881, 5310, 2, "modern");
				player.sendMessage("Teleporting to " + bossNames[6] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Vet'ion
				player.getPA().startTeleport(3200, 3794, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[6] + ".");
			} else if (player.teleSelected == 4) { // City - Neitiznot
				player.getPA().startTeleport(2321, 3804, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[6] + ".");
			} else if (player.teleSelected == 6) { // Other - Agility - Grace
				player.getPA().startTeleport(1504, 3626, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[6] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254038:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Dagannoths
				player.getPA().startTeleport(2442, 10147, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[7] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Shayzien Assault
				player.getPA().startTeleport(1461, 3689, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[7] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Corporeal Beast
				player.getPA().startTeleport(2964, 4382, 2, "modern");
				player.sendMessage("Teleporting to " + bossNames[7] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Callisto
				player.getPA().startTeleport(3325, 3845, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[7] + ".");
			} else if (player.teleSelected == 4) { // City - Karamja
				player.getPA().startTeleport(2948, 3147, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[7] + ".");
			} else if (player.teleSelected == 6) { // Other - beginner Hunter
				player.getPA().startTeleport(1580, 3437, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[7] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254040:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Chickens
				player.getPA().startTeleport(3236, 3295, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[8] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Mage Arena
				player.getPA().startTeleport(2541, 4716, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[8] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Daggonoth Mother
				player.getPA().startTeleport(2513, 4635, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[8] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Scorpia
				player.getPA().startTeleport(3233, 3945, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[8] + ".");
			} else if (player.teleSelected == 4) { // City - Falador
				player.getPA().startTeleport(2964, 3378, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[8] + ".");
				c.getDiaryManager().getFaladorDiary().progress(FaladorDiaryEntry.TELEPORT_TO_FALADOR);
			//} else if (player.teleSelected == 6) { // Other - Gnome Theving
				if (WheatPortalEvent.xLocation > 0 && WheatPortalEvent.yLocation > 0) {
					player.getPA().spellTeleport(WheatPortalEvent.xLocation + 1, WheatPortalEvent.yLocation + 1, 0);
				} else {
					player.sendMessage("There is currently no portal available, wait 5 minutes.");
					return;
				}
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254042:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Slayer Tower
				player.getPA().startTeleport(3428, 3538, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[9] + ".");
			} else if (player.teleSelected == 1) { // Minigames - Duel Arena
				player.getPA().startTeleport(3365, 3266, 0, "modern");
				player.sendMessage("Teleporting to " + minigameNames[9] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Kraken
				player.getPA().startTeleport(2280, 10022, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[9] + ".");
				player.sendMessage("When you are ready, enter the cave to the South-East to start the instance.");
			} else if (player.teleSelected == 3) { // Wilderness - Venenatis
				player.getPA().startTeleport(3345, 3754, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[9] + ".");
			} else if (player.teleSelected == 4) { // City - Taverly
				player.getPA().startTeleport(2928, 3451, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[9] + ".");
			} else if (player.teleSelected == 6) { // Other - Mining Guild
				player.getPA().startTeleport(3058, 9776, 0, "modern");
				player.sendMessage("Teleporting to " + otherNames[9] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254044:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Brimhaven Dungeon
				player.getPA().startTeleport(2712, 9564, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[10] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Zulrah - Instanced
				player.getPA().startTeleport(2202, 3056, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[10] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Chaos Elemental
				player.getPA().startTeleport(3281, 3910, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[10] + ".");
			} else if (player.teleSelected == 4) { // City - Camelot
				player.getPA().startTeleport(2757, 3477, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[10] + ".");
				c.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.CAMELOT_TELEPORT);
			}
			break;
		case 254046:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Taverley Dungeon
				player.getPA().startTeleport(2884, 9796, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[11] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Cerberus
				player.getPA().startTeleport(2873, 9847, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[11] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Chaos Fanatic
				player.getPA().startTeleport(2981, 3836, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[11] + ".");
			} else if (player.teleSelected == 4) { // City - Catherby
				player.getPA().startTeleport(2813, 3447, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[11] + ".");
				c.getDiaryManager().getKandarinDiary().progress(KandarinDiaryEntry.CATHERY_TELEPORT);
			}
			break;
		case 254048:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Stronghold Slayer Dungeon
				player.getPA().startTeleport(2432, 3423, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[12] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Thermonuclear Smoke Devil
				if (player.playerLevel[18] < 93) {
					player.sendMessage("You need a Slayer level of 93 to kill these.");
					return;
				}
				player.getPA().startTeleport(2376, 9452, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[12] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Crazy Archaeologist
				player.getPA().startTeleport(2984, 3713, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[12] + ".");
			} else if (player.teleSelected == 4) { // City - Al Kharid
				player.getPA().startTeleport(3293, 3174, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[12] + ".");
			} /*
				 * else if (c.getRights().isOrInherits(Right.VIP) ||
				 * c.getRights().isOrInherits(Right.OWNER)) { if (player.teleSelected == 5) { //
				 * Legendary - Runecrafting player.getPA().showInterface(26100);
				 * //player.getPA().startTeleport(1992,4530, 3, "modern");
				 * player.sendMessage("Opening "+donatorNames[12]+" Interface."); } } else {
				 * player.sendMessage("Suggest Something on the forums for this spot!");
				 */
			break;
		case 254050:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Relleka Dungeon
				player.getPA().startTeleport(2808, 10002, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[13] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Abyssal Sire
				player.getPA().startTeleport(3039, 4788, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[13] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Skeletal Wyverns
				player.getPA().startTeleport(2963, 3916, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[13] + ".");
			} else if (player.teleSelected == 4) { // City - Morytania
				player.getPA().startTeleport(3511, 3480, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[13] + ".");
				c.getDiaryManager().getMorytaniaDiary().progress(MorytaniaDiaryEntry.MORYTANIA_SWAMP);
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254052:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Mithril Dragons
				player.getPA().startTeleport(1746, 5323, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[14] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Demonic Gorillas
				player.getPA().startTeleport(2130, 5647, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[14] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - East Dragons
				player.getPA().startTeleport(3351, 3659, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[14] + ".");
			} else if (player.teleSelected == 4) { // City - Shilo Village
				player.getPA().startTeleport(2827, 2995, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[14] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254054:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Asgarnian Ice Cave
				player.getPA().startTeleport(3029, 9582, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[15] + ".");
			} else if (player.teleSelected == 2) { // Bosses - Lizardman Shaman
				player.getPA().startTeleport(1469, 3687, 0, "modern");
				player.sendMessage("Teleporting to " + bossNames[15] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Wildy Volcano
				player.getPA().startTeleport(3366, 3935, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[15] + ".");
			} else if (player.teleSelected == 4) { // City - Waterbirth Tele
				player.getPA().startTeleport(2508, 3725, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[15] + ".");
				c.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.WATERBIRTH_TELEPORT);
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254056:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Catacombs
				player.getPA().startTeleport(1664, 10050, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[16] + ".");
				player.sendMessage("Click on the statue to enter the Catacombs!");
			} else if (player.teleSelected == 3) { // Wilderness - Chaos Altar
				player.getPA().startTeleport(3236, 3628, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[16] + ".");
			} else if (player.teleSelected == 4) { // City - Lletya
				player.getPA().startTeleport(2352, 3162, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[16] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254058:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Cave Kraken
				player.getPA().startTeleport(2277, 10001, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[17] + ".");
			} else if (player.teleSelected == 3) { // Wilderness - Lava Dragons
				player.getPA().startTeleport(3202, 3860, 0, "modern");
				player.sendMessage("Teleporting to " + wildernessNames[17] + ".");
			} else if (player.teleSelected == 4) { // City - Brimhaven
				player.getPA().startTeleport(2898, 3546, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[17] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254060:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 0) { // Monsters - Smoke Devils
				if (player.playerLevel[18] < 93) {
					player.sendMessage("You need a Slayer level of 93 to kill these.");
					return;
				}
				player.getPA().startTeleport(2404, 9415, 0, "modern");
				player.sendMessage("Teleporting to " + monsterNames[18] + ".");
			} else if (player.teleSelected == 4) { // City - Entrana
				player.getPA().startTeleport(2827, 3336, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[18] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
		case 254062:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 4) { // City - Draynor
				player.getPA().startTeleport(3077, 3252, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[19] + ".");
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		case 254064:
			if (!teleportCheck(player))
				return;
			if (player.teleSelected == 4) { // City - Trollheim
				player.getPA().startTeleport(2911, 3612, 0, "modern");
				player.sendMessage("Teleporting to " + cityNames[20] + ".");
				c.getDiaryManager().getFremennikDiary().progress(FremennikDiaryEntry.TROLLHEIM_TELEPORT);
			} else {
				player.sendMessage("Suggest Something on the forums for this spot!");
			}
			break;
		// End of Buttons
		}
	}
}