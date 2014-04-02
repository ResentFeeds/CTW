package com.falconetwork.ctw.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.util.TeamType;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player pl = e.getEntity();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		p.addDeaths(1);
		if(pl.getKiller() != null || pl.getKiller() instanceof Player) {
			CPlayer k = CTW.players.get(pl.getKiller().getUniqueId());
			k.addKills(1);
		}
	}
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player pl = e.getPlayer();
		Block b = e.getClickedBlock();
		byte data = e.getClickedBlock().getData();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		if(b.getType() == Material.WOOL) {
			// YELLOW
			if(data == 4 && p.getTeamType() == TeamType.YELLOW) {
				b.setType(Material.AIR);
				b.setData((byte) 0);
				pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 4));
			}
			// GREEN
			if(data == 5 && p.getTeamType() == TeamType.GREEN) {
				b.setType(Material.AIR);
				b.setData((byte) 0);
				pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 5));
			}
			// BLUE
			if(data == 11 && p.getTeamType() == TeamType.BLUE) {
				b.setType(Material.AIR);
				b.setData((byte) 0);
				pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 11));
			}
			// RED
			if(data == 14 && p.getTeamType() == TeamType.RED) {
				b.setType(Material.AIR);
				b.setData((byte) 0);
				pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 14));
			}
		}
	}
	
}