package me.nuclear.hcfessent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class playerInteract implements Listener{
	
	private static playerInteract instance;
	Main main = Main.getInstance();
	
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		ItemStack i = e.getItem();
		
		if(Main.getInstance().staff.contains(p)) {
			if(i != null) {
                if (i.getType() == Material.NETHER_STAR) {
                    if (i.getItemMeta().getDisplayName().contains("Online Staff")) {
                        e.setCancelled(true);
                        showStaffs(p);
                    }
                } else if(i.getType() == Material.INK_SACK) {
                	if(i.getItemMeta().getDisplayName().contains("Vanish")) {
                		e.setCancelled(true);
                		if(Main.getInstance().vanished.contains(p)) {
                			for(String msg : Main.getInstance().config.getStringList("vanish-disabled")) {
            					p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            				}
            				for(Player all : Bukkit.getOnlinePlayers()) {
            					all.showPlayer(p);
            				}
            				staffInv.getInstance().changeDyeColor(8);
                		} else {
                			for(String msg : Main.getInstance().config.getStringList("vanish-enabled")) {
            					p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            				}
            				for(Player all : Bukkit.getOnlinePlayers()) {
            					all.hidePlayer(p);
            					if(all.hasPermission("core.seevanished")) {
            						all.showPlayer(p);
            					}
            				}
            				staffInv.getInstance().changeDyeColor(10);
                		}
                	}
                } else if (i.getType() == Material.WATCH) {
                	if(i.getItemMeta().getDisplayName().contains("Random Teleport")) {
                		ArrayList<Player> players = new ArrayList<Player>();
                		for (Player allP : Bukkit.getOnlinePlayers()) players.add(allP);
                		players.remove(p);
                		Player randomPlayer = players.get(new Random().nextInt(players.size()));
                		p.teleport(randomPlayer.getLocation());
                	}
                }
			} else return;
		}
		instance = this;
	}
	
    public void showStaffs(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Staff Online");
        int slot = 0;
        int lowPlayers = 0;
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            if (all.hasPermission("core.mod")) {
                ItemStack xrayplayer = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                ItemMeta xraymeta = xrayplayer.getItemMeta();
                xraymeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', all.getName()));
                xrayplayer.setItemMeta(xraymeta);
                inv.setItem(slot, xrayplayer);
                lowPlayers = lowPlayers + 1;
                slot++;
            }
            player.openInventory(inv);
        }

    }
    
	HashMap<Player, ItemStack[]> inv = new HashMap<Player, ItemStack[]>();
    public void interactEntity(PlayerInteractEntityEvent e) {
    	Player t = (Player) e.getRightClicked();
    	Player p = e.getPlayer();
    	
    	if(p.getItemInHand().getType() == Material.BOOK) {
    		if(p.getItemInHand().getItemMeta().getDisplayName().contains("Open Inventory")) {
    			inv.put(t, t.getInventory().getContents());
    			Inventory tInv = Bukkit.createInventory(null, 35, t.getName() + "'s Inventory");
    			tInv.setContents(inv.get(t));
    			
    			p.openInventory(tInv);
    		}
    	} else if(p.getItemInHand().getType() == Material.PACKED_ICE && p.getItemInHand().getItemMeta().getDisplayName().contains("Freeze")) {
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
    
    public static playerInteract getInstance() {
    	return instance;
    }
    
}
