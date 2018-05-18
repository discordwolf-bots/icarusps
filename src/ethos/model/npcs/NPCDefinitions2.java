package ethos.model.npcs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NPCDefinitions2 {
	
	public static final NPCDefinitions2[] DEFINITIONS = new NPCDefinitions2[20000];
	
	public static void add(int index, NPCDefinitions2 def) {
		DEFINITIONS[index] = def;
	}
	
	private static Map<Integer, NPCDefinitions2> definitions = new HashMap<>();
	
	public static void load() throws IOException {
		System.out.println("Loading NPC definitions...");
		List<NPCDefinitions2> list = new Gson().fromJson(FileUtils.readFileToString(new File("./Data/json/npc_definitions.json")), new TypeToken<List<NPCDefinitions2>>() {
		}.getType());
		list.stream().filter(Objects::nonNull).forEach(item -> definitions.put((int) item.id, item));
		System.out.println("Loaded " + definitions.size() + " npc definitions.");
	}
	
	public static NPCDefinitions2 get(int npcID) {
		return definitions.get(npcID);
	}
	
	public static Map<Integer, NPCDefinitions2> getDefinitions() {
		return definitions;
	}
	
	private int id;
	private String name;
	private String description;
	private int size = 1;
	private boolean attackable;
	private int combatLevel;
	private boolean aggressive;
	private boolean poisonous;
	private boolean undead;
	private boolean poisonImmunity;
	private boolean venomImmunity;
	private int slayerLevel;
	private int hitpoints;
	private int attackSpeed;
	private int maxHit;
	
	private int[] skills;
	private int[] bonusses;

	
	public NPCDefinitions2(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}

	public int getCombatLevel() {
		return combatLevel;
	}

	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
	}

	public boolean isAggressive() {
		return aggressive;
	}

	public void setAggressive(boolean aggressive) {
		this.aggressive = aggressive;
	}

	public boolean isPoisonous() {
		return poisonous;
	}

	public void setPoisonous(boolean poisonous) {
		this.poisonous = poisonous;
	}

	public boolean isUndead() {
		return undead;
	}

	public void setUndead(boolean undead) {
		this.undead = undead;
	}

	public boolean isPoisonImmunity() {
		return poisonImmunity;
	}

	public void setPoisonImmunity(boolean poisonImmunity) {
		this.poisonImmunity = poisonImmunity;
	}

	public boolean isVenomImmunity() {
		return venomImmunity;
	}

	public void setVenomImmunity(boolean venomImmunity) {
		this.venomImmunity = venomImmunity;
	}

	public int getSlayerLevel() {
		return slayerLevel;
	}

	public void setSlayerLevel(int slayerLevel) {
		this.slayerLevel = slayerLevel;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getMaxHit() {
		return maxHit;
	}

	public void setMaxHit(int maxHit) {
		this.maxHit = maxHit;
	}

	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public int[] getBonuses() {
		return bonusses;
	}

	public void setBonuses(int[] bonuses) {
		this.bonusses = bonuses;
	}

	public static void setDefinitions(Map<Integer, NPCDefinitions2> definitions) {
		NPCDefinitions2.definitions = definitions;
	}
	
}