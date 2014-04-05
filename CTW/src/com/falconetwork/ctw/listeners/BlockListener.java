package com.falconetwork.ctw.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.events.TeamScoreEvent;
import com.falconetwork.ctw.util.BlockUtils;
import com.falconetwork.ctw.util.TeamType;

public class BlockListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player pl = e.getPlayer();
		if(pl.getGameMode() != GameMode.CREATIVE)
			e.setCancelled(true);
	}
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		byte data = b.getData();
		Player pl = e.getPlayer();
		Location l = b.getLocation();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		if(b.getType() == Material.WOOL) {
			// YELLOW
			if(data == 4 && p.getTeamType() == TeamType.YELLOW && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 4)) && BlockUtils.yellowObject(l)) {
				TeamScoreEvent evt = new TeamScoreEvent(p.getTeam(), pl);
				Bukkit.getPluginManager().callEvent(evt);
				p.getTeam().scorePoint();
			}
			// GREEN
			if(data == 5 && p.getTeamType() == TeamType.GREEN && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 5)) && BlockUtils.greenObject(l)) {
				TeamScoreEvent evt = new TeamScoreEvent(p.getTeam(), pl);
				Bukkit.getPluginManager().callEvent(evt);
				p.getTeam().scorePoint();
			}
			// BLUE
			if(data == 11 && p.getTeamType() == TeamType.BLUE && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 11)) && BlockUtils.blueObject(l)) {
				TeamScoreEvent evt = new TeamScoreEvent(p.getTeam(), pl);
				Bukkit.getPluginManager().callEvent(evt);
				p.getTeam().scorePoint();
			}
			// RED
			if(data == 14 && p.getTeamType() == TeamType.RED && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 14)) && BlockUtils.redObject(l)) {
				TeamScoreEvent evt = new TeamScoreEvent(p.getTeam(), pl);
				Bukkit.getPluginManager().callEvent(evt);
				p.getTeam().scorePoint();
			}
			if(p.isCarrying()) {
				p.setCarrying(false);
			}
		}
	}
	
}