package com.falconetwork.ctw;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CTW extends JavaPlugin {
	public static String prefix = "§8[§cCTW§8] ";
	public static File dataFolder, playersFolder;
	public static Map<UUID, CPlayer> players = new HashMap<UUID, CPlayer>();
	
	@Override 
	public void onEnable() {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			CPlayer p = new CPlayer(pl);
			players.put(pl.getUniqueId(), p);
		}
	}
	
	@Override 
	public void onLoad() {
		dataFolder = getDataFolder();
		if(!getDataFolder().exists())
			getDataFolder().mkdirs();
		
		playersFolder = new File(getDataFolder(), "players");
		if(!playersFolder.exists())
			playersFolder.mkdirs();
	}
	
	@Override 
	public void onDisable() {
		
	}
	
	public static boolean hasItem(Player p, ItemStack i) {
		boolean contains = false;
		for(ItemStack it : p.getInventory().getContents()) {
			if(it != null) {
				if(it.equals(i)) {
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	
}