package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;

/**
 * Called when a team scores a point.
 * @author Jatboy
 */
public class TeamScoreEvent extends TeamEvent {

	public TeamScoreEvent(Team team, Player player) {
		super(team, team.getType(), player);
	}
	
}