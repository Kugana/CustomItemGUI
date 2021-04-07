package CIG.Command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import CIG.Main.CustomItemGUI;
import CIG.Util.MainConfiguration;
import CIG.Util.MainMessage;
import CIG.Util.data;

public class CIGcommand extends data implements TabExecutor {

	private final CustomItemGUI plugin;
	private static String key = "@CustomItemGUI";

	public CIGcommand(CustomItemGUI plugin) {
		super(plugin);
		this.plugin = plugin;

	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		return null;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			String valuecolor = MainConfiguration.getStatsIdentifierValuecolor();
			if (args.length == 0) {

			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("help")) {
					if (args[0].equalsIgnoreCase("reload")) {
						Bukkit.getServer().getPluginManager().disablePlugin(plugin);

						plugin.reloadConfig();
						Bukkit.getServer().getPluginManager().enablePlugin(plugin);
						sender.sendMessage(color(plugin.getConfig().getString("Message.reload")));
					}
					if (args[0].equalsIgnoreCase("help")) {

					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("delete")
						|| args[0].equalsIgnoreCase("save") || args[0].equalsIgnoreCase("load")) {
					if (args[0].equalsIgnoreCase("create")) {
						if (args[1] != null) {
							if (p.getItemInHand().getType() != Material.AIR && p.getItemInHand() != null) {
								if (!getitem().containsKey(args[1])) {

									ItemStack item = p.getItemInHand();
									setitem(args[1], item);
								} else {
									MainMessage.CUSTOMITEMGUI_ITEMEQUALS(p);
									return true;
								}
							}

						}

					}
					if (args[0].equalsIgnoreCase("save")) {
						if (args[1] != null) {
							if (getitem().containsKey(args[1])) {
								File file = new File(plugin.getDataFolder() + File.separator + "Item" + File.separator
										+ args[1] + ".yml");
								YamlConfiguration y = YamlConfiguration.loadConfiguration(file);
								y.set(args[1], getitem().get(args[1]));
								try {
									y.save(file);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								MainMessage.CUSTOMITEMGUI_NOEXITITEM(p);
							}

						}

					}

					if (args[0].equalsIgnoreCase("load")) {

						if (args[1] != null && getitem().containsKey(args[1])) {

							ItemStack item = getitem().get(args[1]).clone();
							ItemMeta meta = item.getItemMeta();
							if (meta.getLore() != null) {
								List<String> lore = meta.getLore();

								for (int i = 0; i < lore.size(); i++) {

									if (lore.get(i).contains(key)) {
										String[] test = lore.get(i).split(",");
										if (test.length == 2) {
											String stats = test[1].substring(test[1].lastIndexOf("=") + 1);
											String newlore = getFormat(stats);
											HashMap<String, String> map = new HashMap<String, String>();
											map.put("slot", stats);
											lore.set(i, color(placeholder(map, newlore, "<", ">")));
										}
										if (test.length == 3) {

											String stats = test[1].substring(test[1].lastIndexOf("=") + 1);
											String value = valuecolor + test[2].substring(test[2].lastIndexOf("=") + 1);
											String newlore = getFormat(stats);
											HashMap<String, String> map = new HashMap<String, String>();

											map.put("stats", stats);
											map.put("value", getvalue(stats, value));

											lore.set(i, color(placeholder(map, newlore, "<", ">")));
										}
										if (test.length == 4) {
											String stats = test[1].substring(test[1].lastIndexOf("=") + 1);
											String minvalue = test[2].substring(test[2].lastIndexOf("=") + 1);
											String maxvalue = test[3].substring(test[3].lastIndexOf("=") + 1);
											String range = getSymbol(stats);
											String value = KEY_STATS_LORE + valuecolor + minvalue + range + valuecolor
													+ maxvalue;
											String newlore = getFormat(stats);
											HashMap<String, String> map = new HashMap<String, String>();
											map.put("stats", stats);
											map.put("value", value);

											lore.set(i, color(placeholder(map, newlore, "<", ">")));
										}

									}
								}
								meta.setLore(lore);
								item.setItemMeta(meta);

							}

							if (!(p.getInventory().firstEmpty() == -1)) {

								p.getInventory().addItem(item);

							} else {
								ItemStack[] list = p.getInventory().getContents();

								for (int i = 0; i < list.length; i++) {

									if (list[i].getItemMeta().getDisplayName() != null
											&& list[i].getItemMeta().getDisplayName()
													.equals(getitem().get(args[1]).getItemMeta().getDisplayName())) {
										if (p.getInventory().getItem(i).getAmount() < getitem().get(args[1])
												.getMaxStackSize()) {

											p.getInventory().addItem(item);
											return true;
										}

									}
								}

							}
						} else {
							MainMessage.CUSTOMITEMGUI_NOEXITITEM(p);

						}
					}

					if (args[0].equalsIgnoreCase("delete")) {
						if (args[1] != null && getitem().containsKey(args[1])) {
							File file = new File(plugin.getDataFolder() + File.separator + args[1] + ".yml");
							if (file.exists()) {
								file.delete();
								if (getitem().containsKey(args[1])) {
									getitem().remove(args[1]);
								}
							}
						} else {
							MainMessage.CUSTOMITEMGUI_NOEXITITEM(p);
						}
					}
				}
			}
			if (args.length == 4) {
				if (args[0].equalsIgnoreCase("set")) {
					if (args[1] != null && getitem().containsKey(args[1])) {
						if (args[2].equalsIgnoreCase("type")) {
							if (args[3] != null) {
								if (Material.getMaterial(args[3]).isItem()) {
									if (getitem().containsKey(args[2])) {
										ItemStack item = getitem().get(args[2]);
										item.setType(Material.getMaterial(args[3]));
										setitem(args[1], item);
									}
								}
							}
						}
						if (args[2].equalsIgnoreCase("name")) {
							if (args[3] != null) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();

								meta.setDisplayName(color(args[3]));
								item.setItemMeta(meta);
								setitem(args[1], item);
							}
						}

						if (args[2].equalsIgnoreCase("data")) {
							if (args[3] != null) {
								ItemStack item = getitem().get(args[1]);
								Damageable meta = (Damageable) item.getItemMeta();
								meta.setDamage(Integer.valueOf(args[3]));
								
								item.setItemMeta((ItemMeta) meta);
								setitem(args[1], item);
							}
						}

						if (args[2].equalsIgnoreCase("unbreakable")) {
							if (args[3] != null) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								meta.setUnbreakable(Boolean.parseBoolean(args[3]));
								// meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new
								// AttributeModifier("generic.attackSpeed", 3, Operation.ADD_NUMBER));
								item.setItemMeta(meta);
								setitem(args[1], item);
							}
						}

					} else {
						MainMessage.CUSTOMITEMGUI_NOEXITITEM(p);
					}
				}
				if (args[0].equalsIgnoreCase("add")) {
					if (args[1] != null && getitem().containsKey(args[1])) {
						if (args[2].equalsIgnoreCase("socket")) {
							if (args[3] != null && isNum(args[3])) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta itemMeta = item.getItemMeta();
								String newlore2 = null;
								newlore2 = "@CustomItemGUI," + " SlotSocket=Empty";
								List<String> newlore3 = addLores(item, newlore2, Integer.parseInt(args[3]));

								itemMeta.setLore(newlore3);
								item.setItemMeta(itemMeta);
								setitem(args[1], item);

							}
						}
						if (args[2].equalsIgnoreCase("flags")) {
							if (args[3] != null) {
								if (args[3].equalsIgnoreCase("HIDE_ATTRIBUTES")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
								if (args[3].equalsIgnoreCase("HIDE_DESTROYS")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
								if (args[3].equalsIgnoreCase("HIDE_ENCHANTS")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
								if (args[3].equalsIgnoreCase("HIDE_PLACED_ON")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
								if (args[3].equalsIgnoreCase("HIDE_POTION_EFFECTS")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
								if (args[3].equalsIgnoreCase("HIDE_UNBREAKABLE")) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta meta = item.getItemMeta();
									meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
						}
					}
				}
				if (args[0].equalsIgnoreCase("remove")) {
					if (args[1] != null && getitem().containsKey(args[1])) {
						if (args[2].equalsIgnoreCase("flags")) {
							if (args[3].equalsIgnoreCase("HIDE_ATTRIBUTES")) {

								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();

								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
							if (args[3].equalsIgnoreCase("HIDE_DESTROYS")) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_DESTROYS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
							if (args[3].equalsIgnoreCase("HIDE_ENCHANTS")) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
							if (args[3].equalsIgnoreCase("HIDE_PLACED_ON")) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_PLACED_ON);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
							if (args[3].equalsIgnoreCase("HIDE_POTION_EFFECTS")) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
							if (args[3].equalsIgnoreCase("HIDE_UNBREAKABLE")) {
								ItemStack item = getitem().get(args[1]);
								ItemMeta meta = item.getItemMeta();
								if (meta.hasItemFlag(ItemFlag.valueOf(args[3]))) {
									meta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
									item.setItemMeta(meta);
									setitem(args[1], item);
								}
							}
						}
					}
				}
			}
			if (args.length == 5) {
				if (args[0].equalsIgnoreCase("set")) {
					if (args[1] != null) {
						if (args[2].equalsIgnoreCase("stats")) {
							if (args[3] != null && CheckAttribute(args[3])) {
								if (args[4] != null) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta itemMeta = item.getItemMeta();
									List<String> rl = itemMeta.getLore() == null ? new ArrayList<String>()
											: itemMeta.getLore();
									String newlore2 = null;
									String newlore1 = null;
									if (args[4].contains(":")) {
										if (args[3].equals("DAMAGE")) {
											String[] value = args[4].split(":");
											double a = Double.parseDouble(value[0]);
											double b = Double.parseDouble(value[1]);
											double max = Math.max(a, b);
											double min = Math.min(a, b);
											newlore2 = "@CustomItemGUI," + " Stats=" + args[3] + ", MinValue=" + min
													+ ", MaxValue=" + max;
										} else {
											MainMessage.CUSTOMITEMGUI_NOMAX(p);
											return true;
										}

									} else {

										newlore1 = checkstatslore(args[3], args[4]);

									}

									String newlore = args[4].contains(":") ? newlore2 : newlore1;
									if (itemMeta.getLore() != null) {
										if (checkhasstats(rl, args[3])) {
											for (int i = 0; i < rl.size(); i++) {
												String[] test = itemMeta.getLore().get(i).split(",");
												String stats = test[1].substring(test[1].lastIndexOf("=") + 1);

												if (stats.equals(args[3])) {

													rl.set(i, newlore);
													itemMeta.setLore(rl);

													break;
												}
											}

										} else {
											rl.add(newlore);
											itemMeta.setLore(rl);

										}

									} else {
										rl.add(newlore);
										itemMeta.setLore(rl);

									}

									item.setItemMeta(itemMeta);
									setitem(args[1], item);
								} else {
									MainMessage.CUSTOMITEMGUI_ISNOTNUM(p);
									return true;
								}
							} else {
								MainMessage.CUSTOMITEMGUI_ISNOTATTR(p);
								return true;
							}
						}

						if (args[2].equalsIgnoreCase("setlore")) {
							if (args[3] != null && isNum(args[3])) {
								if (args[4] != null) {
									ItemStack item = getitem().get(args[1]);
									ItemMeta itemMeta = item.getItemMeta();
									List<String> lore = addLores(item, color(args[4]), Double.parseDouble(args[3]));
									itemMeta.setLore(lore);
									item.setItemMeta(itemMeta);
									setitem(args[1], item);
									MainMessage.CUSTOMITEMGUI_ADDLORE(p);
								}

							} else {
								MainMessage.CUSTOMITEMGUI_ISNOTNUM(p);
								return true;
							}
						}
					} else {
						MainMessage.CUSTOMITEMGUI_NOEXITITEM(p);
					}

				}
			}
		}
		return true;
	}
}
