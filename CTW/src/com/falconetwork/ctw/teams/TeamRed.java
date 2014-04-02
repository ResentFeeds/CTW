package com.falconetwork.ctw.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.events.TeamJoinEvent;
import com.falconetwork.ctw.teams.events.TeamLeaveEvent;
import com.falconetwork.ctw.util.TeamType;

public class TeamRed extends Team {

	public TeamRed() {
		super("Red");
	}
	
	public void scorePoint() {
		this.score++;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Team.inTeam(this, p))
				p.sendMessage(CTW.prefix + "§6 Your team scored a point!");
			else
				p.sendMessage(CTW.prefix + "§c The " + toString() + " §cscored a point!");
		}
	}
	
	public void onJoin(TeamJoinEvent e) {
		if(e.getType() == TeamType.RED) {
			if(!players.contains(e.getPlayer())) {
				players.add(e.getPlayer());
				Player pl = e.getPlayer();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				p.setTeam(e.getTeam());
				// TELEPORTATION, AND INVENTORY CODE HERE.
				e.getPlayer().sendMessage(CTW.prefix + "§6You have joined " + toString() + "§e team.");
			}
		}
	}

	public void onLeave(TeamLeaveEvent e) {
		if(e.getType() == TeamType.RED) {
			if(e.getType() == TeamType.RED) {
				if(players.contains(e.getPlayer())) {
					players.remove(e.getPlayer());
					Player pl = e.getPlayer();
					CPlayer p = CTW.players.get(pl.getUniqueId());
					p.setTeam(null);
					// TELEPORTATION, AND INVENTORY CODE HERE.
					e.getPlayer().sendMessage(CTW.prefix + "§6You have left " + toString() + "§e team.");
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "§c" + getName();
	}

	public TeamType getType() {
		return TeamType.RED;
	}

}
