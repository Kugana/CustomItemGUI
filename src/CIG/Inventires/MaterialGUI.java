package CIG.Inventires;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import CIG.Main.CustomItemGUI;
import CIG.Util.PageAPI;
import CIG.Util.PageButton;
import CIG.Util.data;

public class MaterialGUI extends data {

	private static CustomItemGUI main = new CustomItemGUI();

	public MaterialGUI(CustomItemGUI instance) {
		super(instance);
		this.main = new CustomItemGUI();

		// TODO Auto-generated constructor stub
	}

	public static void MaterialAll(Player p) {

		Bukkit.getScheduler().runTask(main, () -> {

			PageAPI pagedPane = new PageAPI(6 - 2, 6, color("&aMaterial"));

			Set<ItemStack> Items = new HashSet<ItemStack>();
			// xMaterial [] material = xMaterial.values();
			Material[] material = Material.values();
			for (int a = 0; a < material.length; a++) {
				// material[a].name()
				// ItemStack is = new ItemStack(material[a]);

				if (material[a].name().contains("WOOL")) {
					Bukkit.broadcastMessage("" + material[a].name());
					for (int i = 0; i < 15; i++) {
						Items.add(new ItemStack(Material.getMaterial(material[a].name()), 1, (short) i));
					}
				}
				// Bukkit.broadcastMessage(material[a].name() + " " + material[a].getId());

				Items.add(new ItemStack(Material.getMaterial(material[a].name())));
			}

			for (ItemStack shopItem : Items) {

				pagedPane.addButton(new PageButton(shopItem, new Consumer<InventoryClickEvent>() {
					@Override
					public void accept(InventoryClickEvent e) {

						HumanEntity whoClicked = e.getWhoClicked();

						if (whoClicked instanceof Player) {

							Player p = (Player) whoClicked;

						}
					}
				}));
			}

			pagedPane.open(Bukkit.getPlayer(p.getName()));

		});

	}
}
