package ethos.model.players.combat.melee;

import java.util.Objects;

import ethos.Config;
import ethos.Server;
import ethos.model.multiplayer_session.MultiplayerSessionFinalizeType;
import ethos.model.multiplayer_session.MultiplayerSessionStage;
import ethos.model.multiplayer_session.MultiplayerSessionType;
import ethos.model.multiplayer_session.duel.DuelSession;
import ethos.model.multiplayer_session.duel.DuelSessionRules.Rule;
import ethos.model.players.Boundary;
import ethos.model.players.Player;

/**
 * Handles prayers drain and switching
 * 
 * @author 2012
 * @author Organic
 */

public class CombatPrayer {
	
	public static final int THICK_SKIN = 0, BURST_OF_STRENGTH = 1,
			CLARITY_OF_THOUGHT = 2, SHARP_EYE = 3, MYSTIC_WILL = 4,
			ROCK_SKIN = 5, SUPERHUMAN_STRENGTH = 6, IMPROVED_REFLEXES = 7,
			RAPID_RESTORE = 8, RAPID_HEAL = 9, PROTECT_ITEM = 10,
			HAWK_EYE = 11, MYSTIC_LORE = 12, STEEL_SKIN = 13,
			ULTIMATE_STRENGTH = 14, INCREDIBLE_REFLEXES = 15,
			PROTECT_FROM_MAGIC = 16, PROTECT_FROM_RANGED = 17,
			PROTECT_FROM_MELEE = 18, EAGLE_EYE = 19, MYSTIC_MIGHT = 20,
			RETRIBUTION = 21, REDEMPTION = 22, SMITE = 23, CHIVALRY = 24,
			PIETY = 25;

	public static double[] prayerData = { 1, // Thick Skin.
			1, // Burst of Strength.
			1, // Clarity of Thought.
			1, // Sharp Eye.
			1, // Mystic Will.
			2, // Rock Skin.
			2, // SuperHuman Strength.
			2, // Improved Reflexes.
			0.4, // Rapid restore.
			0.6, // Rapid Heal.
			0.6, // Protect Items.
			1.5, // Hawk eye.
			2, // Mystic Lore.
			4, // Steel Skin.
			4, // Ultimate Strength.
			4, // Incredible Reflexes.
			4, // Protect from Magic.
			4, // Protect from Missiles.
			4, // Protect from Melee.
			4, // Eagle Eye.
			4, // Mystic Might.
			1, // Retribution.
			2, // Redemption.
			6, // Smite.
			8, // Chivalry.
			8, // Piety.
	};

	public static void handlePrayerDrain(Player c) {
		c.usingPrayer = false;
		double toRemove = 0.0;
		if (c.isDead || c.getHealth().getAmount() <= 0)
			return;
		for (int j = 0; j < prayerData.length; j++) {
			if (c.prayerActive[j]) {
				toRemove += prayerData[j] / 30;
				c.usingPrayer = true;
			}
		}
		if (toRemove > 0) {
			toRemove /= (1 + (0.035 * c.playerBonus[11]));
		}
		c.prayerPoint -= toRemove;
		if (c.prayerPoint <= 0) {
			c.prayerPoint = 1.0 + c.prayerPoint;
			reducePrayerLevel(c);
		}
	}

	public static void reducePrayerLevel(Player c) {
		if (c.playerLevel[5] - 1 > 0) {
			c.playerLevel[5] -= 1;
		} else {
			c.sendMessage("You have run out of prayer points!");
			c.playerLevel[5] = 0;
			resetPrayers(c);
			c.prayerId = -1;
		}
		c.getPA().refreshSkill(5);
	}

	public static void resetPrayers(Player c) {
		for (int i = 0; i < c.prayerActive.length; i++) {
			c.prayerActive[i] = false;
			c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
		}
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}
	
	public static void resetOverHeads(Player c) {
		c.prayerActive[16] = false;
		c.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
		c.prayerActive[17] = false;
		c.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
		c.prayerActive[18] = false;
		c.getPA().sendFrame36(c.PRAYER_GLOW[18], 0);
		c.prayerActive[23] = false;
		c.getPA().sendFrame36(c.PRAYER_GLOW[23], 0);
		c.prayerActive[16] = false;
		c.prayerActive[17] = false;
		c.prayerActive[18] = false;
		c.prayerActive[23] = false;
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}
	
	public static int[] getTurnOff(int id) {
		int[] turnOff = new int[0];
		switch (id) {
		case THICK_SKIN:
			turnOff = new int[] { ROCK_SKIN, STEEL_SKIN, CHIVALRY, PIETY };
			break;
		case ROCK_SKIN:
			turnOff = new int[] { THICK_SKIN, STEEL_SKIN, CHIVALRY, PIETY };
			break;
		case STEEL_SKIN:
			turnOff = new int[] { THICK_SKIN, ROCK_SKIN, CHIVALRY, PIETY };
			break;
		case CLARITY_OF_THOUGHT:
			turnOff = new int[] { IMPROVED_REFLEXES, INCREDIBLE_REFLEXES,
					CHIVALRY, PIETY };
			break;
		case IMPROVED_REFLEXES:
			turnOff = new int[] { CLARITY_OF_THOUGHT, INCREDIBLE_REFLEXES,
					CHIVALRY, PIETY };
			break;
		case INCREDIBLE_REFLEXES:
			turnOff = new int[] { IMPROVED_REFLEXES, CLARITY_OF_THOUGHT,
					CHIVALRY, PIETY };
			break;
		case BURST_OF_STRENGTH:
			turnOff = new int[] { SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH,
					SHARP_EYE, MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE, EAGLE_EYE,
					MYSTIC_MIGHT, CHIVALRY, PIETY };
			break;
		case SUPERHUMAN_STRENGTH:
			turnOff = new int[] { BURST_OF_STRENGTH, ULTIMATE_STRENGTH,
					SHARP_EYE, MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE, EAGLE_EYE,
					MYSTIC_MIGHT, CHIVALRY, PIETY };
			break;
		case ULTIMATE_STRENGTH:
			turnOff = new int[] { SUPERHUMAN_STRENGTH, BURST_OF_STRENGTH,
					SHARP_EYE, MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE, EAGLE_EYE,
					MYSTIC_MIGHT, CHIVALRY, PIETY };
			break;
		case SHARP_EYE:
			turnOff = new int[] { MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE,
					EAGLE_EYE, MYSTIC_MIGHT, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case HAWK_EYE:
			turnOff = new int[] { MYSTIC_WILL, SHARP_EYE, MYSTIC_LORE,
					EAGLE_EYE, MYSTIC_MIGHT, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case EAGLE_EYE:
			turnOff = new int[] { MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE,
					SHARP_EYE, MYSTIC_MIGHT, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case MYSTIC_WILL:
			turnOff = new int[] { SHARP_EYE, HAWK_EYE, MYSTIC_LORE, EAGLE_EYE,
					MYSTIC_MIGHT, BURST_OF_STRENGTH, SUPERHUMAN_STRENGTH,
					ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT, IMPROVED_REFLEXES,
					INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case MYSTIC_LORE:
			turnOff = new int[] { MYSTIC_WILL, HAWK_EYE, SHARP_EYE, EAGLE_EYE,
					MYSTIC_MIGHT, BURST_OF_STRENGTH, SUPERHUMAN_STRENGTH,
					ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT, IMPROVED_REFLEXES,
					INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case MYSTIC_MIGHT:
			turnOff = new int[] { MYSTIC_WILL, HAWK_EYE, MYSTIC_LORE,
					EAGLE_EYE, SHARP_EYE, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVALRY, PIETY };
			break;
		case PROTECT_FROM_MAGIC:
			turnOff = new int[] { REDEMPTION, SMITE, RETRIBUTION,
					PROTECT_FROM_RANGED, PROTECT_FROM_MELEE };
			break;
		case PROTECT_FROM_RANGED:
			turnOff = new int[] { REDEMPTION, SMITE, RETRIBUTION,
					PROTECT_FROM_MAGIC, PROTECT_FROM_MELEE };
			break;
		case PROTECT_FROM_MELEE:
			turnOff = new int[] { REDEMPTION, SMITE, RETRIBUTION,
					PROTECT_FROM_RANGED, PROTECT_FROM_MAGIC };
			break;
		case RETRIBUTION:
			turnOff = new int[] { REDEMPTION, SMITE, PROTECT_FROM_MELEE,
					PROTECT_FROM_RANGED, PROTECT_FROM_MAGIC };
			break;
		case REDEMPTION:
			turnOff = new int[] { RETRIBUTION, SMITE, PROTECT_FROM_MELEE,
					PROTECT_FROM_RANGED, PROTECT_FROM_MAGIC };
			break;
		case SMITE:
			turnOff = new int[] { REDEMPTION, RETRIBUTION, PROTECT_FROM_MELEE,
					PROTECT_FROM_RANGED, PROTECT_FROM_MAGIC };
			break;
		case CHIVALRY:
			turnOff = new int[] { SHARP_EYE, MYSTIC_WILL, HAWK_EYE,
					MYSTIC_LORE, EAGLE_EYE, MYSTIC_MIGHT, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, PIETY, THICK_SKIN,
					ROCK_SKIN, STEEL_SKIN };
			break;
		case PIETY:
			turnOff = new int[] { SHARP_EYE, MYSTIC_WILL, HAWK_EYE,
					MYSTIC_LORE, EAGLE_EYE, MYSTIC_MIGHT, BURST_OF_STRENGTH,
					SUPERHUMAN_STRENGTH, ULTIMATE_STRENGTH, CLARITY_OF_THOUGHT,
					IMPROVED_REFLEXES, INCREDIBLE_REFLEXES, CHIVALRY,
					THICK_SKIN, ROCK_SKIN, STEEL_SKIN };
			break;
		}
		return turnOff;
	}

	public static void activatePrayer(Player c, int i) {
		//System.out.println("ID: " + i);
		if (i == 24) {
			if (c.playerLevel[1] < 65) {
				c.sendMessage("You must have a defence level of 70 to use this prayer.");
				return;
			}
		}
		if (i == 25) {
			if (c.playerLevel[1] < 70) {
				c.sendMessage("You must have a defence level of 70 to use this prayer.");
				return;
			}
		}
		if (Server.getMultiplayerSessionListener().inSession(c, MultiplayerSessionType.TRADE)) {
			c.sendMessage("You cannot activate prayers whilst trading!");
			return;
		}
		if (Boundary.isIn(c, Boundary.RFD)) {
			resetPrayers(c);
			return;
		}
		if (Boundary.isIn(c, Boundary.DUEL_ARENA)) {
			DuelSession session = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
			if (Objects.nonNull(session)) {
				if (session.getRules().contains(Rule.NO_PRAYER)) {
					c.sendMessage("Prayer has been disabled for this duel.");
					resetPrayers(c);
					return;
				}
			}
		}
		DuelSession duelSession = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
		if (Objects.nonNull(duelSession) && duelSession.getStage().getStage() > MultiplayerSessionStage.REQUEST
				&& duelSession.getStage().getStage() < MultiplayerSessionStage.FURTHER_INTERATION) {
			c.sendMessage("You have declined the duel.");
			duelSession.getOther(c).sendMessage("The challenger has declined the duel.");
			duelSession.finish(MultiplayerSessionFinalizeType.WITHDRAW_ITEMS);
			return;
		}
		if (c.isDead || c.getHealth().getAmount() <= 0) {
			return;
		}
		if (c.clanWarRule[3]) {
			c.sendMessage("You are not allowed to use prayer during this war!");
			resetPrayers(c);
			return;
		}
		int[] defPray = { 0, 5, 13, 24, 25 };
		int[] strPray = { 1, 6, 14, 24, 25 };
		int[] atkPray = { 2, 7, 15, 24, 25 };
		int[] rangePray = { 3, 11, 19 };
		int[] magePray = { 4, 12, 20 };

		if (c.playerLevel[5] > 0 || !Config.PRAYER_POINTS_REQUIRED) {
			if (c.getPA().getLevelForXP(c.playerXP[5]) >= c.PRAYER_LEVEL_REQUIRED[i] || !Config.PRAYER_LEVEL_REQUIRED) {
				boolean headIcon = false;
				switch (i) {

				case 0:
				case 5:
				case 13:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < defPray.length; j++) {
							if (defPray[j] != i) {
								c.prayerActive[defPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[defPray[j]], 0);
							}
						}
					}
					break;
				case 1:
				case 6:
				case 14:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}
						}
					}
					break;

				case 2:
				case 7:
				case 15:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}
						}
					}
					break;

				case 3:// range prays
				case 11:
				case 19:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}
						}
					}
					break;
				case 4:
				case 12:
				case 20:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}
						}
					}
					break;
				case 10:
					c.lastProtItem = System.currentTimeMillis();
					c.protectItem = !c.protectItem;
					break;

				case 16:
				case 17:
				case 18:
					if (System.currentTimeMillis() - c.stopPrayerDelay < 5000) {
						c.sendMessage("You have been injured and can't use this prayer!");
						c.getPA().sendFrame36(c.PRAYER_GLOW[16], 0);
						c.getPA().sendFrame36(c.PRAYER_GLOW[17], 0);
						c.getPA().sendFrame36(c.PRAYER_GLOW[18], 0);
						return;
					}
					if (i == 16)
						c.protMageDelay = System.currentTimeMillis();
					else if (i == 17)
						c.protRangeDelay = System.currentTimeMillis();
					else if (i == 18)
						c.protMeleeDelay = System.currentTimeMillis();
				case 21:
				case 22:
				case 23:
					headIcon = true;
					for (int p = 16; p < 24; p++) {
						if (i != p && p != 19 && p != 20) {
							c.prayerActive[p] = false;
							c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);
						}
					}
					break;
				case 24:
				case 25:
					if (c.prayerActive[i] == false) {
						for (int j = 0; j < atkPray.length; j++) {
							if (atkPray[j] != i) {
								c.prayerActive[atkPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[atkPray[j]], 0);
							}
						}
						for (int j = 0; j < strPray.length; j++) {
							if (strPray[j] != i) {
								c.prayerActive[strPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[strPray[j]], 0);
							}
						}
						for (int j = 0; j < rangePray.length; j++) {
							if (rangePray[j] != i) {
								c.prayerActive[rangePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[rangePray[j]], 0);
							}
						}
						for (int j = 0; j < magePray.length; j++) {
							if (magePray[j] != i) {
								c.prayerActive[magePray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[magePray[j]], 0);
							}
						}
						for (int j = 0; j < defPray.length; j++) {
							if (defPray[j] != i) {
								c.prayerActive[defPray[j]] = false;
								c.getPA().sendFrame36(c.PRAYER_GLOW[defPray[j]], 0);
							}
						}
					}
					break;
				}

				if (!headIcon) {
					if (c.prayerActive[i] == false) {
						c.prayerActive[i] = true;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 1);
					} else {
						c.prayerActive[i] = false;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
					}
				} else {
					if (c.prayerActive[i] == false) {
						c.prayerActive[i] = true;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 1);
						c.headIcon = c.PRAYER_HEAD_ICONS[i];
						c.getPA().requestUpdates();
					} else {
						c.prayerActive[i] = false;
						c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
					}
				}
			} else {
				c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
				c.getPA().sendFrame126("You need a @blu@Prayer level of " + c.PRAYER_LEVEL_REQUIRED[i] + " to use " + c.PRAYER_NAME[i] + ".", 357);
				c.getPA().sendFrame126("Click here to continue", 358);
				c.getPA().sendFrame164(356);
				c.nextChat = -1;
			}
		} else {
			c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
			c.sendMessage("You have run out of prayer points!");
		}

	}
}