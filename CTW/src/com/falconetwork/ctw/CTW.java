package com.falconetwork.ctw;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.falconetwork.ctw.commands.CommandLocationExec;
import com.falconetwork.ctw.commands.CommandTeamExec;
import com.falconetwork.ctw.listeners.BlockListener;
import com.falconetwork.ctw.listeners.PlayerListener;
import com.falconetwork.ctw.teams.TeamsDisplay;
import com.falconetwork.ctw.util.BlockUtils;
import com.falconetwork.fca.jdb.MySQL;

public class CTW extends JavaPlugin {
	private static CTW INSTANCE;
	public static TeamsDisplay display;
	public static MySQL donators, ranks;
	public static String prefix = "§8[§cCTW§8] ";
	public static File dataFolder, playersFolder;
	public static Map<UUID, CPlayer> players = new HashMap<UUID, CPlayer>();
	
	@Override 
	public void onEnable() {
		initSQL();
		display = new TeamsDisplay();
		
		getCommand("team").setExecutor(new CommandTeamExec());
		getCommand("location").setExecutor(new CommandLocationExec());
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			CPlayer p = new CPlayer(pl);
			players.put(pl.getUniqueId(), p);
			pl.setScoreboard(display.get());
			if(pl.getActivePotionEffects().size() >= 1) {
				pl.getInventory().addItem(new ItemStack(Material.MILK_BUCKET));
				pl.sendMessage(prefix + "§7Sorry about the effects, hope this helps!");
			}
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
		
		INSTANCE = this;
	}
	
	@Override 
	public void onDisable() {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			CPlayer p = players.get(pl.getUniqueId());
			p.setCarrying(false);
			pl.setMaxHealth(20.0D);
		}
		closeSQL();
		BlockUtils.regenObjectives(Bukkit.getWorlds().get(0));
	}
	
	private void initSQL() {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream(new File(getDataFolder(), "database.properties"));
			props.load(in);
			{
				String user = props.getProperty("user").replaceAll("'", "");
				String pass = props.getProperty("pass").replaceAll("'", "");
				ranks = new MySQL(this, "thefalconetwork.com", "", "CTW/ranks.db", user, pass);
				donators = new MySQL(this, "thefalconetwork.com", "25565", "CTW/donators.db", user, pass);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void closeSQL() {
		try {
			ranks.closeConnection();
			donators.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	
	public static CTW get() {
		return INSTANCE;
	}
	
}