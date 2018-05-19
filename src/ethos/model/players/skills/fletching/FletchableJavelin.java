package ethos.model.players.skills.fletching;

public enum FletchableJavelin {
	BRONZE(19570, 825, 1, 46), 
	IRON(19572, 826, 17, 58), 
	STEEL(19574, 827, 32, 83), 
	MITHRIL(19576, 828, 47, 108), 
	ADAMANT(19578, 829, 62, 143), 
	RUNE(19580, 830, 77, 168),
	AMETHYST(21352, 21318, 84, 179), 
	DRAGON(19582, 19484, 92, 189);

	/**
	 * The id
	 */
	private int id;
	/**
	 * The reward;
	 */
	private int reward;
	/**
	 * The level required.
	 */
	private int levelRequired;
	/**
	 * The experience granted.
	 */
	private double experience;

	private FletchableJavelin(int id, int reward, int levelRequired, double experience) {
		this.id = id;
		this.reward = reward;
		this.levelRequired = levelRequired;
		this.experience = experience;
	}

	public double getExperience() {
		return experience;
	}

	public int getId() {
		return id;
	}

	public int getLevelRequired() {
		return levelRequired;
	}

	public int getReward() {
		return reward;
	}

}
