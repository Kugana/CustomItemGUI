package CIG.Main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import CIG.Command.CIGcommand;
import CIG.Event.CustomGUIClick;
import CIG.Event.EntityDamagenbyEntity;
import CIG.Event.PlayerInteract;
import CIG.Util.data;

public class CustomItemGUI extends JavaPlugin {

	static CustomItemGUI plugin;
	data data = new data(this);

	@Override
	public void onEnable() {
		plugin = this;
		getCommand();
		getEvent();
		getAllItem();
		saveDefaultConfig();
		checkAll();
		DefaultFile();
	}

	private void checkAll() {
		data.putallattribute();

	}

	@Override
	public void onDisable() {
		saveAllItem();
	}

	private void getCommand() {
		getCommand("CIG").setExecutor(new CIGcommand(this));
	}

	private void getEvent() {
		getServer().getPluginManager().registerEvents(new CustomGUIClick(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamagenbyEntity(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
	}

	public Plugin getInstance() {
		return plugin;

	}

	public void getAllItem() {

		File file = new File(getDataFolder() + File.separator + "Item");
		if (file.listFiles() != null) {
			for (File name : file.listFiles()) {
				YamlConfiguration y = YamlConfiguration.loadConfiguration(name);
				for (String string : data.loadConfig(name).getKeys(true)) {

					getLogger().info(string);

					
					ItemStack item = y.getItemStack(string);
					data.setitem(string, item);

				}


			}
		} else {

		}

	}

	public void saveAllItem() {
		for (String itemname : data.getitem().keySet()) {
			File file = new File(getDataFolder() + File.separator + "Item" + File.separator + itemname + ".yml");
			YamlConfiguration y = YamlConfiguration.loadConfiguration(file);
			y.set(itemname, data.getitem().get(itemname));
			try {
				y.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void DefaultFile() {
		File ItemFile = new File(getDataFolder() + File.separator + "Item");
		File MessageFile = new File(getDataFolder() + File.separator + "Message");
		File US = new File(getDataFolder() + File.separator + "Message" + File.separator + "en.yml");
		File TW = new File(getDataFolder() + File.separator + "Message" + File.separator + "tw.yml");
		File CN = new File(getDataFolder() + File.separator + "Message" + File.separator + "cn.yml");
		if (!ItemFile.exists()) {
			ItemFile.mkdir();
		}
		if (!MessageFile.exists()) {
			MessageFile.mkdir();
		}
		if (!US.exists()) {
			try {
				US.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration y = YamlConfiguration.loadConfiguration(US);
			y.set("CustomItemGUI.error.notnumber", "&cLine must be number");
			y.set("CustomItemGUI.error.notString", "&cLine must be has String");
			y.set("CustomItemGUI.error.notAttribute", "&cNo find this Attribute");
			y.set("CustomItemGUI.error.notExitItem", "&cNo find this Item");
			y.set("CustomItemGUI.error.nomax", "&cJust Can only be a single number");
			y.set("CustomItemGUI.error.itemEquals", "&cThe item is exist, use other name");
			y.set("CustomItemGUI.item.itembreak", "&cThe item is broken, use need repair");

			try {
				y.save(US);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!TW.exists()) {
			try {
				TW.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!CN.exists()) {
			try {
				CN.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
