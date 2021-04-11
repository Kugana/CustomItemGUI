package CIG.Util;

import java.util.List;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SlotType {
	WEAPON(Arrays.asList(new String[] { "Weapon" })), ARMOR(Arrays.asList(new String[] { "Armor" })),
	UNIVERSAL(Arrays.asList(new String[] { "Uni", "Universal", "All" }));

	private final List<String> aliases;

	private SlotType(List<String> aliases) {
		this.aliases = aliases;
	}

	public final List<String> getAliases() {
		return this.aliases;
	}

	public static final SlotType getSlotType(String type) {
		if (type != null) {
			for (SlotType key : values()) {
				for (String aliases : key.getAliases()) {
					if (aliases.equalsIgnoreCase(type)) {
						return key;
					}
				}
			}
		}
		return WEAPON;
	}

	public static final SlotType getSlotType(ItemStack item) {
		return getSlotType(item.getType());
	}

	@SuppressWarnings("unused")
	public static final SlotType getSlotType(Material material) {
		if (material != null) {
			String str;
			switch ((str = material.toString()).hashCode()) {
			case -2001095540:
				if (str.equals("IRON_CHESTPLATE")) {
					return ARMOR;
				}
				break;
			case -1940637536:
				if (str.equals("DIAMOND_CHESTPLATE")) {
					return ARMOR;
				}
				break;
			case -1850010775:
				if (str.equals("SHIELD")) {
					return ARMOR;
				}
				break;
			case -1085864277:
				if (str.equals("LEATHER_CHESTPLATE")) {
					return ARMOR;
				}
				break;
			case -578068715:
				if (str.equals("DIAMOND_LEGGINGS")) {
					return ARMOR;
				}
				break;
			case -278690602:
				if (str.equals("DIAMOND_BOOTS")) {
					return ARMOR;
				}
				break;
			case -228576288:
				if (str.equals("LEATHER_LEGGINGS")) {
					return ARMOR;
				}
				break;
			case -110934678:
				if (str.equals("IRON_BOOTS")) {
					return ARMOR;
				}
				break;
			case 112969176:
				if (str.equals("DIAMOND_HELMET")) {
					return ARMOR;
				}
				break;
			case 190922498:
				if (str.equals("GOLD_BOOTS")) {
					return ARMOR;
				}
				break;
			case 297389748:
				if (str.equals("CHAINMAIL_HELMET")) {

					return ARMOR;
				}
				break;
			case 384825844:
				if (str.equals("GOLD_CHESTPLATE")) {
					return ARMOR;
				}
				break;
			case 556441841:
				if (str.equals("CHAINMAIL_LEGGINGS")) {
					return ARMOR;
				}
				break;
			case 579132395:
				if (str.equals("LEATHER_BOOTS")) {
					return ARMOR;
				}
				break;
			case 935678307:
				if (str.equals("LEATHER_HELMET")) {
					return ARMOR;
				}
				break;
			case 957310313:
				if (str.equals("GOLD_LEGGINGS")) {
					return ARMOR;
				}
				break;
			case 1018435524:
				if (str.equals("IRON_HELMET")) {
					return ARMOR;
				}
				break;
			case 1112731770:
				if (str.equals("CHAINMAIL_BOOTS")) {
					return ARMOR;
				}
				break;
			case 1697280892:
				if (str.equals("CHAINMAIL_CHESTPLATE")) {
					return ARMOR;
				}
				break;
			case 1786073388:
				if (str.equals("GOLD_HELMET")) {
					return ARMOR;
				}
				break;
			case 1991697921:
				if (str.equals("IRON_LEGGINGS")) {
					return ARMOR;
				}
				break;
			case 2048333745:
				if (!str.equals("ELYTRA")) {
					return ARMOR;
				} else {
					return ARMOR;
				}
			}
		}
		return WEAPON;
	}
}
