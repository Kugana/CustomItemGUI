package CIG.Util;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import CIG.Main.CustomItemGUI;

public class MainMessage extends data{

	public MainMessage(CustomItemGUI plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	private static final CustomItemGUI plugin = (CustomItemGUI) Bukkit.getPluginManager().getPlugin("CustomItemGUI");

	static String language = plugin.getConfig().getString("Configuration.Language");

	static File file = new File(
			plugin.getDataFolder() + File.separator + "Message" + File.separator + language + ".yml");
	static YamlConfiguration y = YamlConfiguration.loadConfiguration(file);

	public static void CUSTOMITEMGUI_ISNOTNUM(Player p) {

		String notNum = y.getString("CustomItemGUI.error.notnumber");
		p.sendMessage(color(notNum));

	}

	public static void CUSTOMITEMGUI_NOSTRING(Player p) {

		String notNum = y.getString("CustomItemGUI.error.noString");
		p.sendMessage(color(notNum));

	}

	public static void CUSTOMITEMGUI_ISNOTATTR(Player p) {
		String notAttr = y.getString("CustomItemGUI.error.notAttribute");
		p.sendMessage(color(notAttr));

	}

	public static void CUSTOMITEMGUI_NOEXITITEM(Player p) {
		String notAttr = y.getString("CustomItemGUI.error.notExitItem");
		p.sendMessage(color(notAttr));

	}

	public static void CUSTOMITEMGUI_ADDLORE(Player p) {
		String addlore = y.getString("CustomItemGUI.success.addlore");
		p.sendMessage(color(addlore));

		
	}

	public static void CUSTOMITEMGUI_NOMAX(Player p) {
		String nomax = y.getString("CustomItemGUI.error.nomax");
		p.sendMessage(color(nomax));
		
	}

	public static void CUSTOMITEMGUI_ITEMBREAK(Player p) {
		String itembreak = y.getString("CustomItemGUI.item.itembreak");
		p.sendMessage(color(itembreak));
		
	}

	public static void CUSTOMITEMGUI_ITEMEQUALS(Player p) {
		String itembreak = y.getString("CustomItemGUI.error.itemEquals");
		p.sendMessage(color(itembreak));
		
	}

}
