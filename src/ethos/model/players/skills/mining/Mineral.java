package ethos.model.players.skills.mining;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import ethos.util.Misc;

/**
 * An enumeration of ore vein information.
 * 
 * @author Jason MacKeigan
 * @date Feb 18, 2015, 5:14:50 PM
 */
public enum Mineral {
			CLAY(
					new int[] { 7487, 7454 }, // rock objects 
					"none",	// bar name
					1,	// level
					18,	// experience
					0,	// depletion (1 in value+1)
					3, 	// respawn rate (cycles to respawn)
					15, // extraction rate
					30000, 	// pet chance
					true, 	// does it deplete?
					generateExclusive(434)	// mineral return
				), 
			COPPER(
					new int[] { 7453, 7484, 11961, 11960, 11962, 13709 }, // rock objects
					"bronze", // bar name
					1, // level
					18, // experience
					29, // depletion (1 in value+1)
					5, // respawn rate (cycles to respawn)
					15, // extraction rate
					15000, // pet chance
					true, // does it deplete?
					generateExclusive(436)	// mineral return
				), 
			TIN(
					new int[] { 7485, 9714, 9716, 11957, 11958, 11959, 13712 }, // rock objects
					"bronze", // bar name
					1, // level
					18, // experience
					29, // depletion (1 in value+1)
					5, // respawn rate (cycles to respawn)
					15, // extraction rate
					15000, // pet chance
					true, // does it deplete?
					generateExclusive(438)	// mineral return
				), 
			IRON(
					new int[] { 7488, 11954, 11955, 11956, 13710, 13444, 13445, 13446, 7455 }, // rock objects
					"iron", // bar name
					15, // level
					35, // experience
					24, // depletion (1 in value+1)
					8, // respawn rate (cycles to respawn)
					17, // extraction rate
					14800, // pet chance
					true, // does it deplete?
					generateExclusive(440)	// mineral return
				), 
			COAL(
					new int[] { 7489, 9717, 9718, 9719, 2096, 13714, 13706, 7456 }, // rock objects
					"none", // bar name
					30, // level
					50, // experience
					20, // depletion (1 in value+1)
					15, // respawn rate (cycles to respawn)
					29, // extraction rate
					14600, // pet chance
					true, // does it deplete?
					generateExclusive(453)	// mineral return
				), 
			GOLD(
					new int[] { 7491, 7458, 9722, 9720, 13707, 14962, 14963, 14964, 13441, 13442, 13443, 8728, 8975 }, // rock objects
					"gold", // bar name
					40, // level
					65, // experience
					20, // depletion (1 in value+1)
					25, // respawn rate (cycles to respawn)
					32, // extraction rate
					14200, // pet chance
					true, // does it deplete?
					generateExclusive(444)	// mineral return
				), 
			MITHRIL(
					new int[] { 7492, 13718, 7459 }, // rock objects
					"mithril", // bar name
					55, // level
					80, // experience
					15, // depletion (1 in value+1)
					40, // respawn rate (cycles to respawn)
					35, // extraction rate
					13800, // pet chance
					true, // does it deplete?
					generateExclusive(447)	// mineral return
				), 
			ADAMANT(
					new int[] { 7493, 13720, 7460 }, // rock objects
					"adamant", // bar name
					70, // level
					95, // experience
					10, // depletion (1 in value+1)
					50, // respawn rate (cycles to respawn)
					37, // extraction rate
					13200, // pet chance
					true, // does it deplete?
					generateExclusive(449)	// mineral return
				), 
			RUNE(
					new int[] { 7494, 7461, 14175 }, // rock objects
					"rune", // bar name
					85, // level
					125, // experience
					5, // depletion (1 in value+1)
					100, // respawn rate (cycles to respawn)
					39, // extraction rate
					12500, // pet chance
					true, // does it deplete?
					generateExclusive(451)	// mineral return
				), 
			ESSENCE(
					new int[] { 7471, 14912 }, // rock objects
					"none", // bar name
					30, // level
					5, // experience
					-1, // depletion [never depletes]
					-1, // respawn rate [never respawns]
					5, // extraction rate
					15000, // pet chance
					false, // does it deplete?
					generateExclusive(7936)	// mineral return
				),
			AMETHYST(
					new int[] { 30371, 30372 }, // rock objects
					"none", // bar name
					92, // level
					140, // experience
					0, // depletion (1 in value+1)
					100, // respawn rate (cycles to respawn)
					40, // extraction rate
					11500, // pet chance
					false, // does it deplete?
					generateExclusive(21347) // mineral return
				),			
			GEM(
					new int[] { 9030 }, // rock objects
					"none", // bar name
					40, // level
					25, // experience
					30, // depletion (1 in value+1)
					25, // respawn rate (cycles to respawn)
					20, // extraction rate
					15000, // pet chance
					true, // does it deplete?
					new MineralReturn() { // mineral return
						@Override
						public int generate() {
							int[] inclusives = inclusives();
							int percent = RandomUtils.nextInt(0, 100);
							int dstoneChance = RandomUtils.nextInt(0, 750);
							int onyxChance = RandomUtils.nextInt(0, 2500);
							
							if(dstoneChance == 1) 
								return 1631;
							if(onyxChance == 1) 
								return 6571;
							
							return percent < 50 ? Misc.randomSearch(inclusives, 0, 2) : 
								   percent >= 50 && percent < 80 ? inclusives[2] : 
								   percent >= 80 && percent < 98 ? Misc.randomSearch(inclusives, 3, 5) : 
								   inclusives[inclusives.length - 1];
						}
						@Override
						public int[] inclusives() {
							return new int[] { 
								1625, // opal
								1627, // jade
								1623, // sapphire
								1621, // emerald
								1619, // ruby
								1617  // diamond
							};
						}
		
					}
				);

	/**
	 * An array of object ids that are associated with the mineral obtained from them
	 */
	private final int[] objectIds;
	
	/**
	 * A string of the bar name being created
	 */
	private final String barName;

	/**
	 * The level required to mine this ore
	 */
	private final int level;

	/**
	 * The experience gained from mining this mineral
	 */
	private final double experience;

	/**
	 * The probability that the mineral will deplete
	 */
	private final int depletionProbability;

	/**
	 * The amount of cycles that need to pass before the mineral is extractable
	 */
	private final int respawnRate;

	/**
	 * The default amount of cycles it takes to extract ore from a vein
	 */
	private final int extractionRate;

	/**
	 * The default amount of chance to receive a pet from mining
	 */
	private final int petChance;

	/**
	 * Determines if this mineral depletes
	 */
	private final boolean depletes;

	/**
	 * The mineral that is returned to the player as a result of mining the ore
	 */
	private final MineralReturn mineralReturn;

	/**
	 * Constructs a new mineral
	 * 
	 * @param objectIds the objects that the mineral can be extracted from
	 * @param level the level required to extract minerals from the object(s)
	 * @param experience the experience gain after extraction
	 * @param depletionProbability the probability in terms of a 1: {@link #depletionProbability} ratio.
	 * @param respawnRate the rate at which the mineral respawn's to the world
	 * @param extractionRate the rate at which the mineral is extracted
	 * @param depletes determine if the mineral depletes in the world
	 * @param mineralReturn the mineral that is returned to the player as a result of mining the resource
	 */
	private Mineral(int[] objectIds, String barName, int level, double experience, int depletionProbability, int respawnRate, int extractionRate, int petChance, boolean depletes, MineralReturn mineralReturn) {
		this.objectIds = objectIds;
		this.barName = barName;
		this.level = level;
		this.experience = experience;
		this.depletionProbability = depletionProbability;
		this.respawnRate = respawnRate;
		this.extractionRate = extractionRate;
		this.petChance = petChance;
		this.depletes = depletes;
		this.mineralReturn = mineralReturn;
	}

	/**
	 * The array of objectId values associated with this mineral
	 * 
	 * @return the array of object id's
	 */
	public int[] getObjectIds() {
		return objectIds;
	}
	
	public String getBarName() {
		return barName;
	}

	/**
	 * The level required to extract minerals
	 * 
	 * @return the level required
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * The experience gained from extracting a mineral
	 * 
	 * @return the experience gained
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * The rate, in cycles, the mineral respawns at
	 * 
	 * @return the rate at which the mineral respawns
	 */
	public int getRespawnRate() {
		return respawnRate;
	}

	/**
	 * Every mineral has a difference rate at which they deplete. Some minerals faster then others, and some minerals instantly after being extracted from.
	 * 
	 * @return the probability of depletion. When the probability is 0 the chance of depletion is 1:1.
	 */
	public int getDepletionProbability() {
		return depletionProbability;
	}

	/**
	 * The default amount of cycles it takes for a single ore to be extracted
	 * 
	 * @return the default extraction rate
	 */
	public int getExtractionRate() {
		return extractionRate;
	}

	/**
	 * The default amount of chance to receive a pet while mining
	 * 
	 * @return the default pet rate
	 */
	public int getPetChance() {
		return petChance;
	}

	/**
	 * Determines if the mineral depletes or not
	 * 
	 * @return true if object depletes
	 */
	public boolean isDepletable() {
		return depletes;
	}

	/**
	 * Accesses the {@link MineralReturn} instance's {@link MineralReturn#generate()} function.
	 * 
	 * @return An {@link Integer} value as the result of mining the mineral
	 */
	public MineralReturn getMineralReturn() {
		return mineralReturn;
	}

	/**
	 * The identification value of the object with no mineral remaining after extraction
	 */
	public static final int EMPTY_VEIN = 2835;

	/**
	 * An unmodifiable set of {@link Mineral} objects that will be used as a constant for obtaining information about certain minerals.
	 */
	private static final Set<Mineral> MINERALS = Collections.unmodifiableSet(EnumSet.allOf(Mineral.class));

	/**
	 * Retrieves the {@link Mineral} object with the same objectId as the parameter passed.
	 * 
	 * @param objectId the object id of the mineral
	 * @return the mineral object with the corresponding object id
	 */
	public static Mineral forObjectId(int objectId) {
		return MINERALS.stream().filter(mineral -> Arrays.stream(mineral.objectIds).anyMatch(id -> id == objectId)).findFirst().orElse(null);
	}

	/**
	 * Creates a {@link MineralReturn} object that always generates the same item.
	 * 
	 * @param id the item identification value
	 * @return an object that only returns the id specified
	 */
	private static MineralReturn generateExclusive(int id) {
		return new MineralReturn() {

			@Override
			public int generate() {
				return id;
			}

			@Override
			public int[] inclusives() {
				return new int[] { id };
			}

		};
	}

}
