package me.nuclear.hcfessent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	public static FileConfiguration config;
	public static File conf;
	Logger log;
	
	private ArrayList<Player>cobbleEnabled = new ArrayList<Player>();
	public ArrayList<Player>vanished = new ArrayList<Player>();
	public ArrayList<Player>frozen = new ArrayList<Player>();
	public ArrayList<Player>staff = new ArrayList<Player>();
	
	public HashMap<Player, ItemStack[]>staffInventory = new HashMap<Player, ItemStack[]>();
	public HashMap<Player, ItemStack[]>staffArmour = new HashMap<Player, ItemStack[]>();
	
	@Override
	public void onEnable() {
		log = this.getLogger();
		log.info("[HCFEssentials] Enabled. Made by Nuclear_Test_Man");
		
		instance = this;
		
		config = getConfig();
		config.options().copyDefaults(true);
		conf = new File(getDataFolder(), "config.yml");
		saveConfig();
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		try{
			initCommands();
			initEvents();
		} catch (Exception e) {
			log.warning("[HCFEssentials] Error: " + e.getMessage());
		}
	}
	
	public void noPermission(Player p) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("no-permision")));
	}
	
	public void initCommands() {
		getCommand("coords").setExecutor(new coords());
		getCommand("teamspeak").setExecutor(new teamspeak());
		getCommand("cobble").setExecutor(new cobble());
		getCommand("vanish").setExecutor(new vanish());
		getCommand("freeze").setExecutor(new freeze());
		getCommand("staff").setExecutor(new staff());
		
	}
	
	public void initEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new moveEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new damageEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new blockPlace(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new blockBreak(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new playerInteract(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new inventoryClickEvent(), this);
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if(getCobble(p) == true) {
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		takeCobble(e.getPlayer());
	}
	
	public void addCobble(Player p) {
		cobbleEnabled.add(p);
	}
	public void takeCobble(Player p) {
		cobbleEnabled.remove(p);
	}
	public boolean getCobble(Player p) {
		if(cobbleEnabled.contains(p)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static Main getInstance(){
		return instance;
	}
	
}
