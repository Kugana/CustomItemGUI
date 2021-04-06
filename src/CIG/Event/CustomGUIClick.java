package CIG.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import CIG.Main.CustomItemGUI;
import CIG.Util.PageAPI;
import CIG.Util.data;

public class CustomGUIClick extends data implements Listener {

	private final CustomItemGUI main;

	public CustomGUIClick(CustomItemGUI main) {
		super(main);
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();

		if (holder instanceof PageAPI) {
			((PageAPI) holder).onClick(event);
		}
	}

}
