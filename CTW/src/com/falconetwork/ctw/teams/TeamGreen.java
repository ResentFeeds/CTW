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
import com.falconetwork.ctw.util.TeamType;
import com.falconetwork.ctw.util.TeamUtils;

public class TeamGreen extends Team {

	public TeamGreen() {
		super("Green");
	}
	
	public void scorePoint() {
		this.score++;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Team.inTeam(this, p))
				p.sendMessage(CTW.prefix + "§6 Your team scored a point!");
			else
				p.sendMessage(CTW.prefix + "§c The " + toString() + "§c team scored a point!");
		}
	}
	
	public void onJoin(TeamJoinEvent e) {
		if(e.getType() == TeamType.GREEN) {
			if(!players.contains(e.getPlayer())) {
				players.add(e.getPlayer());
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				p.setTeam(e.getTeam());
				pl.teleport(getSpawn());
				// TELEPORTATION, AND INVENTORY CODE HERE.
				e.getPlayer().sendMessage(CTW.prefix + "§6You have joined " + toString() + "§6 team.");
			}
		}
	}

	public void onLeave(TeamLeaveEvent e) {
		if(e.getType() == TeamType.GREEN) {
			if(players.contains(e.getPlayer())) {
				players.remove(e.getPlayer());
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				p.setTeam(null);
				pl.teleport(getSpawn());
				TeamUtils.enterSpectate(this, p, pl);
				// TODO: INVENTORY CODE
				e.getPlayer().sendMessage(CTW.prefix + "§6You have left " + toString() + "§6 team.");
			}
		}
	}
	
	public void onRespawn(TeamRespawnEvent e) {
		if(e.getType() == TeamType.GREEN) {
			if(players.contains(e.getPlayer())) {
				Player pl = e.getPlayer();
				pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 11));
				pl.setVelocity(new Vector(0, 0, 0));
				pl.teleport(getSpawn());
			}
		}
	}
	
	@Override
	public String toString() {
		return "§a" + getName();
	}
	
	public Location getSpawn() {
		return new Location(Bukkit.getWorlds().get(0), -633, 54, 576, -268.77f, 0.53f);
	}
	
	public TeamType getType() {
		return TeamType.GREEN;
	}
	
}