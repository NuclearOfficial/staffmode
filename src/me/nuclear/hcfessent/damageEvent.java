package me.nuclear.hcfessent;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damageEvent implements Listener{
	
	Main main = Main.getInstance();
	
	public void onDamage (EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player t = (Player) e.getDamager();
			
			if (main.frozen.contains(p)) {
				e.setCancelled(true);
				t.sendMessage(ChatColor.YELLOW + p.getName() + " is frozen!");
			}
		}
		
	}
	
}
