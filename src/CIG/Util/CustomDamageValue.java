package CIG.Util;

public class CustomDamageValue {
	private final double damage;
	private final double penetration;
	private final double pvpDamage;
	private final double pveDamage;
	private final double attackAoERadius;
	private final double attackAoEDamage;
	private final double criticalChance;
	private final double criticalDamage;
	private final double hitRate;

	public CustomDamageValue(double damage, double penetration, double pvpDamage, double pveDamage,
			double attackAoERadius, double attackAoEDamage, double criticalChance, double criticalDamage,
			double hitRate) {
		this.damage = damage;
		this.penetration = penetration;
		this.pvpDamage = pvpDamage;
		this.pveDamage = pveDamage;
		this.attackAoERadius = attackAoERadius;
		this.attackAoEDamage = attackAoEDamage;
		this.criticalChance = criticalChance;
		this.criticalDamage = criticalDamage;
		this.hitRate = hitRate;
	}

	public final double getDamage() {
		return this.damage;
	}

	public final double getPenetration() {
		return this.penetration;
	}

	public final double getPvPDamage() {
		return this.pvpDamage;
	}

	public final double getPvEDamage() {
		return this.pveDamage;
	}

	public final double getAttackAoERadius() {
		return this.attackAoERadius;
	}

	public final double getAttackAoEDamage() {
		return this.attackAoEDamage;
	}

	public final double getCriticalChance() {
		return this.criticalChance;
	}

	public final double getCriticalDamage() {
		return this.criticalDamage;
	}

	public final double getHitRate() {
		return this.hitRate;
	}
}
