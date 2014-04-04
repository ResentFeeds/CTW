package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.Team;
import com.falconetwork.ctw.util.TeamType;

public class TeamEvent extends Event implements Cancellable {
	private Team team;
	private Player player;
	private TeamType type;
	private boolean isCancelled;
	private static final HandlerList handlers = new HandlerList();
	
	public TeamEvent() {}
	
	public TeamEvent(Team team, TeamType type, Player player) {
		this.team = team;
		this.type = type;
		this.player = player;
		this.isCancelled = false;
		if(team == null) isCancelled = true;
		if(type == null) isCancelled = true;
		if(player == null) isCancelled = true;
		CTW.display.update();
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
	
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}