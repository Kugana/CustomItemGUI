package CIG.Util;

import org.bukkit.Bukkit;

import CIG.Main.CustomItemGUI;

public class MainConfiguration {

	private static final CustomItemGUI plugin = (CustomItemGUI) Bukkit.getPluginManager().getPlugin("CustomItemGUI");

	public final String getStatsIdentifierDamage() {

		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_Damage"));

	}

	public final String getStatsIdentifierHealth() {

		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_Health"));

	}

	public final String getStatsIdentifierDefanse() {

		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_Defanse"));

	}

	public String getStatsIdentifierDurability() {
		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_Durability"));
	}

	public String getStatsIdentifierCritChance() {
		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_CritChance"));
	}

	public String getStatsIdentifierCritDamage() {
		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_CritDamage"));
	}

	public String getStatsIdentifierPvEDamage() {
		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_PvEDamage"));
	}

	public String getStatsIdentifierPvPDamage() {
		return data.color(plugin.getConfig().getString("Configuration.Stats.Identifier_PvPDamage"));
	}

	public final static String getStatsIdentifierValuecolor() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Value_color"));

	}

	public final static String getStatsIdentifierValueFormat() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Format_Value"));

	}

	public final static String getStatsIdentifierValueRange() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Lore_Value_Range_Symbol"));
	}

	public final static String getStatsIdentifierValueDurability() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Lore_Value_Durability_Symbol"));

	}

	public final static String getStatsIdentifierValueMultiplier() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Lore_Value_Multiplier_Symbol"));
	}

	public final static String getStatsIdentifierValuePercent() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Lore_Value_Percent_Symbol"));
	}

	public static String getStatsIdentifierSocketFormat() {

		return data.color(plugin.getConfig().getString("Configuration.Basic.Format_Socket"));
	}

}
