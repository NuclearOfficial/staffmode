package me.nuclear.hcfessent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class moveEvent implements Listener{

	Main main = Main.getInstance();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(main.frozen.contains(p)) {
			p.teleport(p.getLocation());
		}
	}

}
