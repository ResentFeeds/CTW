package com.falconetwork.ctw.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.teams.events.TeamJoinEvent;
import com.falconetwork.ctw.teams.events.TeamLeaveEvent;
import com.falconetwork.ctw.util.TeamType;

public abstract class Team {
	private String name;
	private int sizeLimit;
	protected List<Player> players;
	
	public static List<Team> teams = new ArrayList<Team>();
	
	static {
		teams.add(new TeamBlue());
		teams.add(new TeamRed());
		teams.add(new TeamGreen());
		teams.add(new TeamYellow());
	}
	
	public Team(String name) {
		this(name, -1);
	}
	
	public Team(String name, int size) {
		this.name = name;
		this.sizeLimit = size;
		if(size == -1) players = new ArrayList<Player>();
		else players = new ArrayList<Player>(size);
	}
	
	public static Team getTeam(String name) {
		Team team = null;
		for(Team t : teams) {
			if(t.getName().equalsIgnoreCase(name)) {
				team = t;
				break;
			}
		}
		return team;
	}
	
	public static void executeEvent(String team, Player p, int evt) {
		Team t = getTeam(team);
		if(t != null)
		switch(evt) {
			case 0:
				t.onJoin(new TeamJoinEvent(t, p));
				break;
			case 1:
				t.onLeave(new TeamLeaveEvent(t, p));
				break;
			default:
				break;
		}
	}
	
	public abstract void onJoin(TeamJoinEvent e);
	public abstract void onLeave(TeamLeaveEvent e);
	
	public abstract TeamType getType();
	
	public String getName() {
		return name;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public int getSizeLimit() {
		return sizeLimit;
	}
	
}