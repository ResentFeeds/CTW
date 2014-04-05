package com.falconetwork.ctw.teams;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.events.TeamJoinEvent;
import com.falconetwork.ctw.teams.events.TeamLeaveEvent;
import com.falconetwork.ctw.teams.events.TeamRespawnEvent;
import com.falconetwork.ctw.util.InventoryUtils;
import com.falconetwork.ctw.util.TeamType;
import com.falconetwork.ctw.util.TeamUtils;

public class TeamBlue extends Team {

	public TeamBlue() {
		super("Blue");
	}
	
	public void scorePoint() {
		this.score++;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Team.inTeam(this, p))
				p.sendMessage(CTW.prefix + "�6 Your team scored a point!");
			else
				p.sendMessage(CTW.prefix + "�c The " + toString() + " �cscored a point!");
		}
		CTW.display.update();
	}
	
	public void onJoin(TeamJoinEvent e) {
		if(e.getType() == TeamType.BLUE) {
			if(!players.contains(e.getPlayer())) {
				players.add(e.getPlayer());
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				p.setTeam(e.getTeam());
				pl.teleport(getSpawn());
				TeamUtils.leaveSpectate(this, p, pl);
				TeamUtils.joinTeam(this, p, pl);
				InventoryUtils.giveInventory(this, p, pl);
				e.getPlayer().sendMessage(CTW.prefix + "�6You have joined " + toString() + "�e team.");
			}
		}
	}

	public void onLeave(TeamLeaveEvent e) {
		if(e.getType() == TeamType.BLUE) {
			if(players.contains(e.getPlayer())) {
				players.remove(e.getPlayer());
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				p.setTeam(null);
				TeamUtils.leaveTeam(this, p, pl);
				TeamUtils.enterSpectate(this, p, pl);
				// TELEPORTATION, AND INVENTORY CODE HERE.
				e.getPlayer().sendMessage(CTW.prefix + "�6You have left " + toString() + "�e team.");
			}
		}
	}
	
	public void onRespawn(TeamRespawnEvent e) {
		if(e.getType() == TeamType.BLUE) {
			if(players.contains(e.getPlayer())) {
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 11));
				pl.setVelocity(new Vector(0, 0, 0));
				pl.teleport(getSpawn());
				InventoryUtils.giveInventory(this, p, pl);
			}
		}
	}
	
	@Override
	public String toString() {
		return "�b" + getName();
	}
	
	public Location getSpawn() {
		return new Location(Bukkit.getWorlds().get(0), -685, 54, 629, -179.82f, 0.70f);
	}
	
	public TeamType getType() {
		return TeamType.BLUE;
	}
	
}