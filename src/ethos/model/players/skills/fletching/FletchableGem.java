package ethos.model.players.skills.fletching;

public enum FletchableGem {
	OPAL(1609, 45, 12, 11, 16), JADE(1611, 9187, 12, 26, 20), TOPAZ(1613, 9188, 12, 48, 34), SAPHIRE(1607, 9189, 12, 56, 47), EMERALD(1605, 9190, 12, 58, 55), RUBY(1603, 9191, 12, 63,
			7), DIAMOND(1601, 9192, 12, 65, 70), DRAGONSTONE(1615, 9193, 12, 71, 82), ONYX(6573, 9194, 24, 73, 94);

	private final int gem, tips, amount, level, experience;

	private FletchableGem(int gem, int tips, int amount, int level, int experience) {
		this.gem = gem;
		this.tips = tips;
		this.amount = amount;
		this.level = level;
		this.experience = experience;
	}

	public int getGem() {
		return gem;
	}

	public int getTips() {
		return tips;
	}

	public int getAmount() {
		return amount;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

}
