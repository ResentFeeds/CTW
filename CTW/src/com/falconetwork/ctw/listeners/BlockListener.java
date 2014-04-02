package com.falconetwork.ctw.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.util.TeamType;

public class BlockListener implements Listener {

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBlockPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		byte data = b.getData();
		Player pl = e.getPlayer();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		if(b.getType() == Material.WOOL) {
			// YELLOW
			if(data == 4 && p.getTeamType() == TeamType.YELLOW && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 5))) {
				
			}
			// GREEN
			if(data == 5 && p.getTeamType() == TeamType.GREEN && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 5))) {
				
			}
			// BLUE
			if(data == 11 && p.getTeamType() == TeamType.BLUE && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 5))) {
				
			}
			// RED
			if(data == 14 && p.getTeamType() == TeamType.RED && CTW.hasItem(pl, new ItemStack(Material.WOOL, (short) 5))) {
				
			}
		}
	}
	
}