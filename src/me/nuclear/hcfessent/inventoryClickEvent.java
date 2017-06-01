package me.nuclear.hcfessent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class inventoryClickEvent implements Listener{
	
	public void InventoryClickEvent(InventoryClickEvent e) {
		Inventory i = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		playerInteract.getInstance().inv.put(p, i.getContents());
		
		if(Main.getInstance().staff.contains(p) && !p.hasPermission("core.staffedit")) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.YELLOW + "Cannot edit inventory whilst in staff mode!");
		}
	}
	
}
