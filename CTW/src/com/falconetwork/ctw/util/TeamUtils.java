package com.falconetwork.ctw.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.Team;

public class TeamUtils {
	public static List<Player> spectating = new ArrayList<Player>();
	
	public static void joinTeam(Team team, CPlayer p, Player pl) {
		CTW.display.joinTeam(pl, teamToInt(team));
	}
	
	public static void leaveTeam(Team team, CPlayer p, Player pl) {
		CTW.display.leaveTeam(pl, teamToInt(team));
	}
	
	public static void enterSpectate(Team team, CPlayer p, Player pl) {
		if(!spectating.contains(pl)) {
			spectating.add(pl);
			CTW.display.joinTeam(pl, 4);
		}
	}
	
	public static void leaveSpectate(Team team, CPlayer p, Player pl) {
		if(spectating.contains(pl)) {
			spectating.remove(pl);
			CTW.display.leaveTeam(pl, 4);
		}
	}
	
	public static boolean isSpecating(Player pl) {
		return (spectating.contains(pl));
	}
	
	public static int teamToInt(Team t) {
		int team = -1;
		if(t.getName().equals("Red")) team = 0; else
		if(t.getName().equals("Blue")) team = 1; else
		if(t.getName().equals("Green")) team = 2; else
		if(t.getName().equals("Yellow")) team = 3; else
		team = -1;
		return team;
	}
	
}