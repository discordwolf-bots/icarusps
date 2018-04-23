package ethos.model.players.combat.magic;

import java.util.stream.IntStream;

import ethos.Config;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.util.Misc;

public class MagicMaxHit {

	public static int mageAttack(Player c) {
		double equipmentBonus = c.playerBonus[3];
		double magicLevel = c.playerLevel[6];
		double magicPrayer = c.prayerActive[4] ? 1.05 : c.prayerActive[12] ? 1.10 : c.prayerActive[20] ? 1.15 : 1.0;
		double accuracy = (((equipmentBonus + magicLevel) * 1.4) * magicPrayer);
		double modifier = 1.10;
		if (c.fullVoidMage()) {
			modifier *= 1.5;
		}
		if (c.npcIndex > 0 && c.getSlayer().getTask().isPresent()) {
			NPC npc = NPCHandler.npcs[c.npcIndex];
			if (npc != null && c.getSlayer().getTask().get().matches(npc.getDefinition().getNpcName()) || npc.npcType == 7413) {
				if (!c.getItems().isWearingItem(4081) && c.getItems().isWearingItem(11865)) {
					modifier *= 1.15;
				}
			}
			if (c.getItems().isWearingItem(4081, c.playerAmulet)) {
				if (Misc.linearSearch(Config.UNDEAD_IDS, npc.npcType) != -1) {
					modifier *= 1.15;
				}
			}
		}
		return (int) (accuracy * modifier);
	}

	public static int mageDefence(Player c) {
		double prayerDefence = c.prayerActive[0] ? 1.05 : c.prayerActive[5] ? 1.10 : c.prayerActive[13] ? 1.15 : c.prayerActive[24] ? 1.20 : c.prayerActive[25] ? 1.25 : 1.0;
		double defence = Math.floor((c.playerLevel[1] * prayerDefence) * .3);
		double magicDefence = Math.floor(c.playerLevel[6] * .7);
		defence += magicDefence + c.playerBonus[8];
		return (int) defence;
	}

	public static int magiMaxHit(Player c) {
		double damage = MagicData.MAGIC_SPELLS[c.oldSpellId][6];
		double damageMultiplier = 1;
		switch (c.playerEquipment[c.playerWeapon]) {
		case 6914:
		case 21006:
			damageMultiplier += .04;
			break;
		case 11907:
		case 11791:
		case 12904:
			damageMultiplier += 0.10;
			break;
		}
		if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] != 6914 && c.playerEquipment[c.playerWeapon] != 21006 && c.playerEquipment[c.playerWeapon] != 11791
				&& c.playerEquipment[c.playerWeapon] != 12904) {
			damageMultiplier += .10;
		} else if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] == 11791) {
			damageMultiplier += .15;
		} else if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] == 12904) {
			damageMultiplier += .15;
		} else if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] == 11907) {
			damageMultiplier += .15;
		} else if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] == 12899) {
			damageMultiplier += .15;
		} else if (c.playerEquipment[c.playerAmulet] == 12002 && c.playerEquipment[c.playerWeapon] == 6914 && c.playerEquipment[c.playerWeapon] == 21006) {
			damageMultiplier += .12;
		}
		if (c.npcIndex > 0 && c.getSlayer().getTask().isPresent()) {
			NPC npc = NPCHandler.npcs[c.npcIndex];
			if (npc != null && c.getSlayer().getTask().get().matches(npc.getDefinition().getNpcName()) || npc.npcType == 7413) {
				boolean SLAYER_HELM = IntStream.of(c.IMBUED_SLAYER_HELMETS).anyMatch(i -> c.getItems().isWearingItem(i));
				if (SLAYER_HELM) {
					damageMultiplier += .15;
				}
			}
		}
		switch (MagicData.MAGIC_SPELLS[c.oldSpellId][0]) {
		case 12037:
			if (c.getItems().isWearingAnyItem(21255)) {
				damage += (c.playerLevel[6] / 6) + 5;
			} else {
				damage += c.playerLevel[6] / 10;
			}
			break;
		}

		damage *= damageMultiplier;
		return (int) damage;
	}
}