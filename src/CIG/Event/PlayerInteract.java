package CIG.Event;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import CIG.Main.CustomItemGUI;
import CIG.Util.MainConfiguration;
import CIG.Util.MainMessage;
import CIG.Util.SlotType;
import CIG.Util.data;

public class PlayerInteract extends data implements Listener {

	private final CustomItemGUI plugin;

	public PlayerInteract(CustomItemGUI plugin) {
		super(plugin);
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Action action = e.getAction();

		if ((action.equals(Action.RIGHT_CLICK_AIR)) || (action.equals(Action.RIGHT_CLICK_BLOCK))) {

			Player p = e.getPlayer();
			ItemStack item = p.getItemInHand();
			SlotType slotType = SlotType.getSlotType(item);
			if (slotType.equals(SlotType.ARMOR)) {
				double durability = 0;
				if (getStatsLores(item, getText("DURABILITY")) != -1) {
					
					durability = getLoreValue(item, getText("DURABILITY"),
							getStatsLores(item, getText("DURABILITY")));
					if (durability != 0) {
						
					}

				}
				if (getStatsLores(item, getText("DAMAGE")) != -1) {
					if (durability > 0) {

						double customdefanse = getLoreValue(item, getText("DEFANSE"),
								getStatsLores(item, getText("DEFANSE")));
					} else {
						e.setCancelled(true);
						MainMessage.CUSTOMITEMGUI_ITEMBREAK(p);
					}
				}

			} else {
				return;
			}
		}
	}

}
