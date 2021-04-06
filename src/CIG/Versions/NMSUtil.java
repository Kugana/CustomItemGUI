package CIG.Versions;

import org.bukkit.Bukkit;

import CIG.Main.CustomItemGUI;
import net.md_5.bungee.api.ChatColor;

public class NMSUtil {

	static NMS nms;

	static CustomItemGUI main;

	public static String version = null;

	public NMSUtil(CustomItemGUI instance) {

		main = instance;

	}

	public static void setupNMS() {

		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			Bukkit.getConsoleSender().sendMessage("[" + main.getInstance().getDescription().getName() + "]"
					+ ChatColor.RED + "Something went wrong wile loading versions!!");
			Bukkit.getScheduler().cancelTasks(main);// cancelAllTasks();
			Bukkit.getPluginManager().disablePlugin(main.getInstance());
		}
		if (version.equals("v1_12_R1")) {
			nms = new Version_v1_12_R1();
		} else if (version.equals("v1_13_R1")) {
			nms = new Version_v1_13_R1();
		} else if (version.equals("v1_13_R2")) {
			nms = new Version_v1_13_R2();
		} else if (version.equals("v1_14_R1")) {
			nms = new Version_v1_14_R1();
		} else if (version.equals("v1_15_R1")) {
			nms = new Version_v1_15_R1();
		} else if (version.equals("v1_16_R1")) {
			nms = new Version_v1_16_R1();
		} else if (version.equals("v1_16_R2")) {
			nms = new Version_v1_16_R2();
		} else if (version.equals("v1_16_R3")) {
			nms = new Version_v1_16_R3();
		} else {
			Bukkit.getConsoleSender().sendMessage("[" + main.getInstance().getDescription().getName() + "]"
					+ ChatColor.RED + "You current versions," + version + ", is no supported!");
			Bukkit.getScheduler().cancelTasks(main);// .cancelAllTasks();
			Bukkit.getPluginManager().disablePlugin(main.getInstance());
		}

	}

	public static NMS getNMS() {
		return nms;
	}

}
