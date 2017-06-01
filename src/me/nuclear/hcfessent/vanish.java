package me.nuclear.hcfessent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vanish implements CommandExecutor{
	
	Main main = Main.getInstance();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(!(p.hasPermission("core.vanish"))) {
			main.noPermission(p);
		} else {
			if(main.vanished.contains(p)) {
				main.vanished.remove(p);
				if(main.staff.contains(p)) {
					staffInv.getInstance().changeDyeColor(8);
				}
				for(String i : main.config.getStringList("vanish-disabled")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', i));
				}
				for(Player e : Bukkit.getOnlinePlayers()) {
					e.showPlayer(p);
				}
			} else {
				main.vanished.add(p);
				if(main.staff.contains(p)) {
					staffInv.getInstance().changeDyeColor(10);
				}
				for(String x : main.config.getStringList("vanish-enabled")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', x));
				}
				for(Player e : Bukkit.getOnlinePlayers()) {
					e.hidePlayer(p);
					if(e.hasPermission("core.seevanished")) {
						e.showPlayer(p);
					}
				}
				p.showPlayer(p);
			}
			
		}
		
		return true;
	}

}
