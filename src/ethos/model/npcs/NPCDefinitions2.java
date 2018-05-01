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
	private int[] bonuses;

	
	public NPCDefinitions2(int id) {
		this.id = id;
	}
	

	
	
	public int getNpcID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return description;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean getAttackable() {
		return attackable;
	}
	
	public int getCombatLevel() {
		return combatLevel;
	}
	
	public boolean getAggressive() {
		return aggressive;
	}
	
	public boolean getPoisonous() {
		return poisonous;
	}
	
	public boolean getUndead() {
		return undead;
	}
	
	public boolean getPoisonImmunity() {
		return poisonImmunity;
	}
	
	public boolean getVenomImmunity() {
		return venomImmunity;
	}
	
	public int getSlayer() {
		return slayerLevel;
	}
	
	public int getHitpoints() {
		return hitpoints;
	}
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public int getMaxHit() {
		return maxHit;
	}
	
	public int[] getSkill() {
		return skills;
	}
	
	public int[] getBonus() {
		return bonuses;
	}
	
}