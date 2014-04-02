package com.falconetwork.ctw.teams.events;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.Team;

public class TeamLeaveEvent extends TeamEvent {

	public TeamLeaveEvent(Team team, Player player) {
		super(team, team.getType(), player);
	}
	
}