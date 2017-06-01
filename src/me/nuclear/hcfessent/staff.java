package me.nuclear.hcfessent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class staff implements CommandExecutor{
	
	Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(p.hasPermission("core.mod")) {
			if(main.staff.contains(p)) {
				main.staff.remove(p);
				p.sendMessage(ChatColor.YELLOW + "You are no longer in staff mode.");
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				p.setGameMode(GameMode.SURVIVAL);
				p.getInventory().setContents(main.staffInventory.get(p));
				p.getInventory().setArmorContents(main.staffArmour.get(p));
				Bukkit.getServer().dispatchCommand(p, "vanish");
			} else {
				main.staff.add(p);
				p.sendMessage(ChatColor.YELLOW + "You are now in staff mode.");
				main.staffInventory.put(p, p.getInventory().getContents());
				main.staffArmour.put(p, p.getInventory().getArmorContents());
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				staffInv.getInstance().giveStaffItems(p);
				Bukkit.getServer().dispatchCommand(p, "vanish");
			}
		}
		
		return false;
	}
	
	
	
}
