package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;

/**
 * Called when a player joins a team.
 * @author Jatboy
 */
public class TeamJoinEvent extends TeamEvent {
	
	public TeamJoinEvent(Team team, Player player) {
		super(team, team.getType(), player);
	}
	
}