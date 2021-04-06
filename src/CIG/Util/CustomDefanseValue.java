package CIG.Util;

public class CustomDefanseValue {

	private final double defense;
	private final double pvpDefense;
	private final double pveDefense;
	private final double health;
	private final double healthRegen;
	private final double blockAmount;
	private final double blockRate;
	private final double dodgeRate;

	public CustomDefanseValue(double defense, double pvpDefense, double pveDefense, double health, double healthRegen,
			double blockAmount, double blockRate, double dodgeRate) {
		this.defense = defense;
		this.pvpDefense = pvpDefense;
		this.pveDefense = pveDefense;
		this.health = health;
		this.healthRegen = healthRegen;
		this.blockAmount = blockAmount;
		this.blockRate = blockRate;
		this.dodgeRate = dodgeRate;
	}

	public final double getDefense() {
		return this.defense;
	}

	public final double getPvPDefense() {
		return this.pvpDefense;
	}

	public final double getPvEDefense() {
		return this.pveDefense;
	}

	public final double getHealth() {
		return this.health;
	}

	public final double getHealthRegen() {
		return this.healthRegen;
	}

	public final double getBlockAmount() {
		return this.blockAmount;
	}

	public final double getBlockRate() {
		return this.blockRate;
	}

	public final double getDodgeRate() {
		return this.dodgeRate;
	}
}
