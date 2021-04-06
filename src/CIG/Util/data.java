package CIG.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import CIG.Main.CustomItemGUI;

public class data {
	private final CustomItemGUI plugin;
	private static Map<String, ItemStack> setitem = new HashMap<String, ItemStack>();
	private static Map<String, List<String>> attribute = new HashMap<String, List<String>>();
	private static List<String> allattribute = new ArrayList<String>();
	public static final String KEY_STATS_LORE = color("&1&0&r");

	public data(CustomItemGUI plugin) {
		this.plugin = plugin;
	}

	public static Map<String, ItemStack> getitem() {
		return setitem;
	}

	public void setitem(String name, ItemStack item) {
		this.setitem.put(name, item);
	}

	public static String color(String input) {
		if (input.contains("&")) {
			return ChatColor.translateAlternateColorCodes('&', input);
		} else
			return input;
	}

	public static List<String> Listcolor(List<String> str) {
		List<String> lore = new LinkedList<>();
		for (String s : str) {
			lore.add(ChatColor.translateAlternateColorCodes('&', s));

		}

		return lore;
	}

	public static boolean isPlayer(Entity entity) {

		return (entity != null ? Boolean.valueOf(entity instanceof Player) : null).booleanValue();

	}

	public static boolean CheckAttribute(String s) {
		if (attribute.get("Attribute").contains(s)) {
			return true;
		} else {
			return false;
		}
	}

	public static String checkstatslore(String stats, String value) {
		switch (stats) {
		case "DURABILITY":
			return "@CustomItemGUI," + " Stats=" + stats + ", Value=" + (int) Double.parseDouble(value);
		}
		return "@CustomItemGUI," + " Stats=" + stats + ", Value=" + Double.parseDouble(value);
	}

	public String getTextLores(String stats) {
		String newstats = plugin.getConfig().getString("Configuration.Stats.Identifier_" + stats.toLowerCase());
		return newstats;

	}

	public static int getStatsLores(ItemStack item, String stats) {
		List<String> lores = getLores(item);
		for (int i = 0; i < lores.size(); i++) {
			if (lores.get(i).contains(stats)) {

				return i;
			}
		}
		return -1;

	}

	public static double getLoreValue(ItemStack item, String stats, int line) {
		if (line == -1) {
			return 0.0D;
		}
		ItemMeta meta = item.getItemMeta();
		List<String> list = meta.getLore();
		String lore = (String) list.get(line);
		String[] textListValue = lore.split(KEY_STATS_LORE);
		String valuecolor = MainConfiguration.getStatsIdentifierValuecolor();
		if (textListValue.length > 1) {
			String textValue = textListValue[1];
			if (stats.equals(getText("DAMAGE"))) {
				String[] textlorevalue = textValue.split(color(getSymbol("DAMAGE")));

				if (textlorevalue.length > 1) {
					String loreminvalue = textlorevalue[0].replaceFirst(valuecolor, "");
					String loremaxvalue = textlorevalue[1].replaceFirst(valuecolor, "");
					double minvalue = Double.parseDouble(loreminvalue);
					double maxvalue = Double.parseDouble(loremaxvalue);
					double value = minvalue + Math.random() * (maxvalue - minvalue);

					return Double.parseDouble(String.format("%.1f", value));
				} else {
					double value = Double.parseDouble(textlorevalue[0].replaceFirst(valuecolor, ""));
					return Double.parseDouble(String.format("%.1f", value));
				}
			}
			if (stats.equals(getText("DURABILITY"))) {
				String[] textlorevalue = textValue.split(color(getSymbol("DURABILITY")));
				if (textlorevalue.length > 1) {
					String loreminvalue = textlorevalue[0].replaceFirst(valuecolor, "");
					String loremaxvalue = textlorevalue[1].replaceFirst(valuecolor, "");

					double minvalue = Double.parseDouble(loreminvalue);
					// double maxvalue = Double.parseDouble(loremaxvalue);
					double value = minvalue;

					return (int) Double.parseDouble(String.format("%.1f", value));
				} else {
					double value = Double.parseDouble(textlorevalue[0].replaceFirst(valuecolor, ""));
					return Double.parseDouble(String.format("%.1f", value));
				}

			}
			if (stats.equals(getText("DEFANSE"))) {
				String loreminvalue = textValue.replaceFirst(valuecolor, "");
				double value = Double.parseDouble(loreminvalue);

				return value;
			}
			if (stats.equals(getText("HEALTH"))) {

				String loreminvalue = textValue.replaceFirst(valuecolor, "");
				double value = Double.parseDouble(loreminvalue);

				return value;

			}
			if (stats.equals(getText("PVEDAMAGE"))) {

				String loreminvalue = textValue.replaceFirst(valuecolor, "");
				double value = Double.parseDouble(loreminvalue);

				return value;

			}
			if (stats.equals(getText("PVPDAMAGE"))) {

				String loreminvalue = textValue.replaceFirst(valuecolor, "");
				double value = Double.parseDouble(loreminvalue);

				return value;

			}
			if (stats.equals(getText("CRITDAMAGE"))) {

				String loreminvalue = textValue.replaceFirst(valuecolor, "").replace(getSymbol("CRITDAMAGE"), "");
				double value = Double.parseDouble(loreminvalue);

				return value;

			}
			if (stats.equals(getText("CRITCHANCE"))) {

				String loreminvalue = textValue.replaceFirst(valuecolor, "").replace(getSymbol("CRITCHANCE"), "");
				double value = Double.parseDouble(loreminvalue);
				return value;

			}

		}
		return 0.0D;

	}

	public String setLoreValue(ItemStack item, String stats, int line, int newvalue) {
		if (line == -1) {

			return "";
		}
		ItemMeta meta = item.getItemMeta();
		List<String> list = meta.getLore();
		String lore = (String) list.get(line);
		String[] textListValue = lore.split(KEY_STATS_LORE);
		String valuecolor = MainConfiguration.getStatsIdentifierValuecolor();
		if (textListValue.length > 1) {
			String textValue = textListValue[1];
			if (stats.equals(getText("DURABILITY"))) {
				String[] textlorevalue = textValue.split(getSymbol("DURABILITY"));
				if (textlorevalue.length > 1) {
					String loremaxvalue = textlorevalue[1].replace(valuecolor, "");
					int minvalue = newvalue;
					int maxvalue = Integer.parseInt(loremaxvalue);
					String value = KEY_STATS_LORE + valuecolor + minvalue + getSymbol("DURABILITY") + valuecolor
							+ maxvalue;

					return value;
				} else {

					return "";
				}

			}
		}
		Bukkit.broadcastMessage("test3");
		return "";

	}

	public static final List<String> getLores(ItemStack item) {
		if (item != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (itemMeta != null) {
				List<String> lores = itemMeta.getLore();
				if (lores != null) {
					return lores;
				}
			}
		}
		return new ArrayList();
	}

	public boolean checkhasstats(List<String> rl, String stats) {

		for (String s : rl) {
			if (s.contains(stats)) {
				return true;
			}

		}
		return false;

	}

	private List<String> getallattribute() {
		allattribute.add("DAMAGE");
		allattribute.add("DURABILITY");
		allattribute.add("PVPDAMAGE");
		allattribute.add("PVEDAMAGE");
		allattribute.add("CRITCHANCE");
		allattribute.add("CRITDAMAGE");
		allattribute.add("DEFENSE");
		allattribute.add("HEALTH");
		allattribute.add("PVPDEFENSE");
		allattribute.add("PVEDEFENSE");
		allattribute.add("HEALTHREGEN");
		allattribute.add("BLOCKAMOUNT");
		allattribute.add("BLOCKRATE");
		allattribute.add("DODGERATE");

		return allattribute;
	}

	public void putallattribute() {
		attribute.put("Attribute", getallattribute());
	}

	public static final List<String> addLores(ItemStack item, String lore, double d) {
		ItemMeta meta = item.getItemMeta();
		List<String> itemlore = meta.getLore() == null ? new ArrayList<String>() : meta.getLore();
		if (itemlore.size() < d) {
			for (int x = itemlore.size(); x < d; x++) {
				if (x + 1 == d) {
					itemlore.add(lore);
				} else {
					itemlore.add("");
				}

			}
		} else {
			itemlore.set((int) (d - 1), lore);
		}

		return itemlore;

	}

	public static final boolean CheckItem(Player p, ItemStack item) {
		if (p.getItemInHand() == item) {
			return true;
		}
		return false;
	}

	public static final String ItemEquals(String name) {
		for (String key : getitem().keySet()) {
			ItemStack item = getitem().get(key);
			if (item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null) {
				if (item.getItemMeta().getDisplayName().equals(name)) {
					return key;
				}
			}

		}
		return null;

	}

	@SuppressWarnings("rawtypes")
	public static final String placeholder(HashMap<String, String> map, String text, String leftKey, String rightKey) {
		if (text == null) {
			return "";
		}
		if ((map != null) && (leftKey != null) && (rightKey != null)) {
			int lengthLeft = leftKey.length();
			int lengthRight = rightKey.length();
			String idLeft = Pattern.quote(leftKey);
			String idRight = Pattern.quote(rightKey);
			String regex = idLeft + "[^(" + idRight + "|" + idLeft + ")]+" + idRight;
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);

			for (Iterator localIterator = map.keySet().iterator(); matcher.find(); localIterator.hasNext()) {
				String component = matcher.group();
				int length = component.length();
				String variable = component.substring(lengthLeft, length - lengthRight);

				for (String key : map.keySet()) {
					if (key.equalsIgnoreCase(variable)) {
						String value = (String) map.get(variable);
						String replacement = value != null ? value : "";

						text = text.replace(component, getText(replacement));
						continue;
					}
				}

			}
		}
		return text;
	}

	public static CustomDefanseValue Equipvalue(ItemStack item) {

		double defense = 0.0D;
		double pvpDefense = 0.0D;
		double pveDefense = 0.0D;
		double health = 0.0D;
		double healthRegen = 0.0D;
		double blockAmount = 0.0D;
		double blockRate = 0.0D;
		double dodgeRate = 0.0D;

		return new CustomDefanseValue(defense, pvpDefense, pveDefense, health, healthRegen, blockAmount, blockRate,
				dodgeRate);
	}

	public static CustomDamageValue weaponDamage(ItemStack item) {
		double damage = 0.0D;
		double penetration = 0.0D;
		double pvpdamage = 0.0D;
		double pvedamage = 0.0D;
		double attackAoERadius = 0.0D;
		double attackAoEDamage = 0.0D;
		double criticalChance = 0.0D;
		double criticalDamage = 0.0D;
		double hitRate = 0.0D;

		if (getStatsLores(item, getText("DAMAGE")) != -1) {

			damage = getLoreValue(item, getText("DAMAGE"), getStatsLores(item, getText("DAMAGE")));

		}
		if (getStatsLores(item, getText("PVPDAMAGE")) != -1) {

			pvpdamage = getLoreValue(item, getText("PVPDAMAGE"), getStatsLores(item, getText("PVPDAMAGE")));

		}
		if (getStatsLores(item, getText("PVEDAMAGE")) != -1) {

			pvedamage = getLoreValue(item, getText("PVEDAMAGE"), getStatsLores(item, getText("PVEDAMAGE")));

		}
		if (getStatsLores(item, getText("ATTACKAOERADIUS")) != -1) {

			attackAoERadius = getLoreValue(item, getText("ATTACKAOERADIUS"),
					getStatsLores(item, getText("ATTACKAOERADIUS")));

		}
		if (getStatsLores(item, getText("ATTACKAOEDAMAGE")) != -1) {

			attackAoEDamage = getLoreValue(item, getText("ATTACKAOEDAMAGE"),
					getStatsLores(item, getText("ATTACKAOEDAMAGE")));

		}
		if (getStatsLores(item, getText("CRITCHANCE")) != -1) {

			criticalChance = getLoreValue(item, getText("CRITCHANCE"), getStatsLores(item, getText("CRITCHANCE")));

		}
		if (getStatsLores(item, getText("CRITDAMAGE")) != -1) {

			criticalDamage = getLoreValue(item, getText("CRITDAMAGE"), getStatsLores(item, getText("CRITDAMAGE")));

		}
		if (getStatsLores(item, getText("HITRATE")) != -1) {

			hitRate = getLoreValue(item, getText("HITRATE"), getStatsLores(item, getText("HITRATE")));

		}

		return new CustomDamageValue(damage, penetration, pvpdamage, pvedamage, attackAoERadius, attackAoEDamage,
				criticalChance, criticalDamage, hitRate);
	}

	public static String getvalue(String stats, String value) {
		MainConfiguration mainconfig = new MainConfiguration();

		switch (stats) {
		case "DURABILITY":
			return KEY_STATS_LORE + value + mainconfig.getStatsIdentifierValueDurability() + value;
		case "CRITCHANCE":
			return KEY_STATS_LORE + value + mainconfig.getStatsIdentifierValuePercent();
		case "CRITDAMAGE":
			return KEY_STATS_LORE + value + mainconfig.getStatsIdentifierValuePercent();
		}
		return KEY_STATS_LORE + value;

	}

	public static String getSymbol(String text) {
		MainConfiguration mainconfig = new MainConfiguration();
		switch (text) {
		case "DAMAGE":
			return mainconfig.getStatsIdentifierValueRange();
		case "DURABILITY":
			return mainconfig.getStatsIdentifierValueDurability();
		case "CRITCHANCE":
			return mainconfig.getStatsIdentifierValuePercent();
		case "CRITDAMAGE":
			return mainconfig.getStatsIdentifierValuePercent();
		}
		return text;
	}

	public static String getFormat(String stats) {
		MainConfiguration mainconfig = new MainConfiguration();
		switch (stats) {
		case "Empty":
			return mainconfig.getStatsIdentifierSocketFormat();
		}
		return mainconfig.getStatsIdentifierValueFormat();
	}

	protected static String getText(String text) {
		MainConfiguration mainconfig = new MainConfiguration();
		switch (text) {
		case "DAMAGE":
			return mainconfig.getStatsIdentifierDamage();
		case "HEALTH":
			return mainconfig.getStatsIdentifierHealth();
		case "DEFANSE":
			return mainconfig.getStatsIdentifierDefanse();
		case "DURABILITY":
			return mainconfig.getStatsIdentifierDurability();
		case "PVPDAMAGE":
			return mainconfig.getStatsIdentifierPvPDamage();
		case "PVEDAMAGE":
			return mainconfig.getStatsIdentifierPvEDamage();
		case "CRITCHANCE":
			return mainconfig.getStatsIdentifierCritChance();
		case "CRITDAMAGE":
			return mainconfig.getStatsIdentifierCritDamage();
		}
		return text;

	}

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	public YamlConfiguration loadConfig(File file) {
		YamlConfiguration config = new YamlConfiguration();
		try {
			config.load(file);

		} catch (FileNotFoundException localFileNotFoundException) {

		} catch (IOException ex) {
			plugin.getLogger().info("The file '" + file.getName() + "' could not be loaded.");

			ex.printStackTrace();
		} catch (InvalidConfigurationException ex) {

			plugin.getLogger().info("The file '" + file.getName()
					+ "' is incorrectly configured. View the error report in the console and fix it!");
			ex.printStackTrace();
		}
		return config;
	}

	public void loadallFile() {

	}

}
