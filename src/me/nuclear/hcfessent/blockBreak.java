package me.nuclear.hcfessent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreak implements Listener {
	
	Main main = Main.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(main.frozen.contains(p)) {
			e.setCancelled(true);
		} else if (main.vanished.contains(p)) {
			e.setCancelled(true);
		}
		
	}
	
}
