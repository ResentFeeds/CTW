package com.falconetwork.ctw.listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.events.TeamRespawnEvent;
import com.falconetwork.ctw.util.BlockUtils;
import com.falconetwork.ctw.util.ChatUtils;
import com.falconetwork.ctw.util.TeamType;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		final Player pl = e.getPlayer();
		final CPlayer p = CTW.players.get(pl.getUniqueId());
		Bukkit.getScheduler().runTaskLater(CTW.get(), new Runnable() {
			public void run() {
				if(p.getTeam() != null) {
					p.getTeam().onRespawn(new TeamRespawnEvent(p.getTeam(), pl));
				}
			}
		}, 1L);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player pl = e.getPlayer();
		String message = e.getMessage();
		Set<Player> receivers = e.getRecipients();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		e.setCancelled(true);
		ChatUtils.customizeChat(message, pl, p, receivers);
	}
	
	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e) {
		final Player p = e.getPlayer();
		final ItemStack i = e.getItem();
		if (i.getType() == Material.MILK_BUCKET)
			Bukkit.getScheduler().runTaskLater(CTW.get(), new Runnable() {
				public void run() {
					p.getInventory().remove(new ItemStack(Material.BUCKET));
				}
			}, 5L);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location l = e.getTo();
		if (l.getBlockY() >= 1)
			return;
		if (l.getBlockY() <= 0) {
			e.setCancelled(true);
			Player pl = e.getPlayer();
			CPlayer p = CTW.players.get(pl.getUniqueId());
			p.getTeam().onRespawn(new TeamRespawnEvent(p.getTeam(), pl));
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("");
		Player pl = e.getPlayer();
		if (!CTW.players.containsKey(pl.getUniqueId())) {
			CPlayer p = new CPlayer(pl);
			CTW.players.put(pl.getUniqueId(), p);
			pl.setScoreboard(CTW.display.get());
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage("");
		Player pl = e.getPlayer();
		if (CTW.players.containsKey(pl.getUniqueId())) {
			CPlayer p = CTW.players.get(pl);
			pl.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			p.save();
			CTW.players.remove(pl.getUniqueId());
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player pl = e.getEntity();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		p.addDeaths(1);
		if (pl.getKiller() != null || pl.getKiller() instanceof Player) {
			CPlayer k = CTW.players.get(pl.getKiller().getUniqueId());
			k.addKills(1);
			if (p.isCarrying())
				BlockUtils.dropWool(p, pl, p.getTeamType());
		}
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player pl = e.getPlayer();
		Block b = e.getClickedBlock();
		if (b != null) {
			CPlayer p = CTW.players.get(pl.getUniqueId());
			if (b.getType() == Material.WOOL) {
				// YELLOW
				byte data = e.getClickedBlock().getData();
				if (BlockUtils.isObjective(b.getLocation())) {
					e.setCancelled(true);
					boolean pickedUp = false;
					if (data == 4 && p.getTeamType() == TeamType.YELLOW) {
						b.setType(Material.AIR);
						b.getState().update(true);
						pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 4));
						pickedUp = true;
					}
					// GREEN
					if (data == 5 && p.getTeamType() == TeamType.GREEN) {
						b.setType(Material.AIR);
						b.getState().update(true);
						pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 5));
						pickedUp = true;
					}
					// BLUE
					if (data == 11 && p.getTeamType() == TeamType.BLUE) {
						b.setType(Material.AIR);
						b.getState().update(true);
						pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 11));
						pickedUp = true;
					}
					// RED
					if (data == 14 && p.getTeamType() == TeamType.RED) {
						b.setType(Material.AIR);
						b.getState().update(true);
						pl.getInventory().addItem(new ItemStack(Material.WOOL, 1, (short) 14));
						pickedUp = true;
					}
					if (!p.isCarrying() && pickedUp) {
						BlockUtils.pickupWool(p, pl, p.getTeamType());
						p.setCarrying(true);
					}
				}
			}
		}
	}

}