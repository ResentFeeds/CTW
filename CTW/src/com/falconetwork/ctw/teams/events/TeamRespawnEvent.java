package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;

/**
 * Called when a player respawns to his team spawn.
 * @author Jatboy
 */
public class TeamRespawnEvent extends TeamEvent {

	public TeamRespawnEvent(Team team, Player player) {
		super(team, team.getType(), player);
	}
	
}