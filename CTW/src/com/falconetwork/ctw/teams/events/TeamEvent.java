package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;
import com.falconetwork.ctw.util.TeamType;

public class TeamEvent {
	private Team team;
	private Player player;
	private TeamType type;
	
	public TeamEvent() {}
	
	public TeamEvent(Team team, TeamType type, Player player) {
		this.team = team;
		this.type = type;
		this.player = player;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public TeamType getType() {
		return type;
	}
	
	public void setType(TeamType type) {
		this.type = type;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}