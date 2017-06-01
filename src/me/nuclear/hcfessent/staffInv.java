package me.nuclear.hcfessent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class staffInv implements Listener{
	
	public static staffInv instance;
	
	ItemStack vanish = new ItemStack(Material.INK_SACK, 1);
	
	@SuppressWarnings("deprecation")
	public void giveStaffItems(Player p) {
		ItemStack openInventory = new ItemStack(Material.BOOK, 1);
		ItemStack randomTeleport = new ItemStack(Material.WATCH, 1);
		ItemStack onlineStaff = new ItemStack(Material.NETHER_STAR, 1);
		ItemStack freeze = new ItemStack(Material.PACKED_ICE, 1);
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		
		ItemMeta openInventoryMeta = openInventory.getItemMeta();
		ItemMeta randomTeleportMeta = randomTeleport.getItemMeta();
		ItemMeta onlineStaffMeta = onlineStaff.getItemMeta();
		ItemMeta vanishMeta = vanish.getItemMeta();
		ItemMeta freezeMeta = freeze.getItemMeta();
		ItemMeta compassMeta = compass.getItemMeta();
		
		openInventoryMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Open Inventory");
		randomTeleportMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Random Teleport");
		onlineStaffMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Online Staff");
		vanishMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Vanish");
		freezeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Freeze");
		compassMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Compass");
		
		if(Main.getInstance().vanished.contains(p)) {
			vanish.getData().setData((byte) 10);
		} else {
			vanish.getData().setData((byte) 8);
		}
		
		p.getInventory().setItem(0, freeze);
		p.getInventory().setItem(1, vanish);
		p.getInventory().setItem(5, compass);
		p.getInventory().setItem(6, randomTeleport);
		p.getInventory().setItem(7, onlineStaff);
		p.getInventory().setItem(8, openInventory);
		
		
		instance = this;
		
	}
	
	public static staffInv getInstance() {
		return instance;
	}
	
	public void changeDyeColor(int color) {
		vanish.getData().setData((byte) color);
	}
	
}
