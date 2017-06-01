package me.nuclear.hcfessent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class freeze implements CommandExecutor{
	Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(!(p.hasPermission("core.freeze"))) {
			main.noPermission(p);
		} else {
			Player t = Bukkit.getServer().getPlayer(args[0]);
			if(args.length < 0) {
				p.sendMessage(ChatColor.YELLOW + "Please specify a player.");
			} else {
				if(main.frozen.contains(t)) {
					p.sendMessage(ChatColor.YELLOW + p.getName() + " is now unfrozen!");
					main.frozen.remove(t);
					t.sendMessage(ChatColor.YELLOW + "You are now unfrozen!");
				} else {
					main.frozen.add(t);
					p.sendMessage(ChatColor.YELLOW + p.getName() + " is now frozen!");
					t.sendMessage(ChatColor.YELLOW + "You are now frozen!");
					t.sendMessage(ChatColor.YELLOW + "Please join ts.zokumc.com. You have 3 minutes!");
				}
			}
			
		}
		
		return false;
	}

}
