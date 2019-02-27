
public enum Skill {
	MATH(30), GEOGRAPHY(10), HISTORY(), ENGLISH(), FRENCH, LITERATURE, SPORTS(10),
	PROGRAMMING(40) {
		@Override
		public int getBonus(int level) {
			return this.getBase() + level * 100;
		}
	}, JAPANESE(40);
	
	private final int base;
	
	public int getBase() {
		return base;
	}

	Skill(int base) {
		this.base = base;
	}
	
	Skill() {
		this(20);
	}
	
	public int getBonus(int level) {
		return this.base * level;
	}
}
