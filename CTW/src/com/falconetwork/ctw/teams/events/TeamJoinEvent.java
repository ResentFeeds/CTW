package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;

public class TeamJoinEvent extends TeamEvent {
	
	public TeamJoinEvent(Team team, Player player) {
		super(team, team.getType(), player);
	}
	
}