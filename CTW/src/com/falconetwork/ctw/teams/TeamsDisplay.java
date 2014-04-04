package com.falconetwork.ctw.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class TeamsDisplay {
	private Objective captures;
	private Scoreboard scoreboard;
	private Score rCaps, bCaps, gCaps, yCaps;
	private org.bukkit.scoreboard.Team rTeam, bTeam, gTeam, yTeam, sTeam;
	
	public TeamsDisplay() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		rTeam = scoreboard.registerNewTeam("Red");
		bTeam = scoreboard.registerNewTeam("Blue");
		gTeam = scoreboard.registerNewTeam("Green");
		yTeam = scoreboard.registerNewTeam("Yellow");
		sTeam = scoreboard.registerNewTeam("Spectators");
		setupTeams();
		setupObjectives();
	}
	
	public void update() {
		for(Team t : Team.teams) {
			if(t.getName().equalsIgnoreCase("Red")) rCaps.setScore(t.getScore());
			if(t.getName().equalsIgnoreCase("Blue")) bCaps.setScore(t.getScore());
			if(t.getName().equalsIgnoreCase("Green")) gCaps.setScore(t.getScore());
			if(t.getName().equalsIgnoreCase("Yellow")) yCaps.setScore(t.getScore());
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			p.setScoreboard(scoreboard);
		}
	}
	
	private void setupTeams() {
		rTeam.setAllowFriendlyFire(false);
		gTeam.setAllowFriendlyFire(false);
		bTeam.setAllowFriendlyFire(false);
		yTeam.setAllowFriendlyFire(false);
		sTeam.setAllowFriendlyFire(false);
		
		sTeam.setCanSeeFriendlyInvisibles(true);
		
		rTeam.setPrefix("§c");
		bTeam.setPrefix("§b");
		gTeam.setPrefix("§a");
		yTeam.setPrefix("§e");
	}
	
	private void setupObjectives() {
		captures = scoreboard.registerNewObjective("captures", "dummy");
		captures.setDisplayName("Captures");
		captures.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		rCaps = captures.getScore(Bukkit.getOfflinePlayer("§cRed: "));
		bCaps = captures.getScore(Bukkit.getOfflinePlayer("§bBlue: "));
		gCaps = captures.getScore(Bukkit.getOfflinePlayer("§aGreen: "));
		yCaps = captures.getScore(Bukkit.getOfflinePlayer("§eYellow: "));
		
		rCaps.setScore(0);
		bCaps.setScore(0);
		gCaps.setScore(0);
		yCaps.setScore(0);
	}
	
	public void joinTeam(Player pl, int team) {
		switch(team) {
			case 0:
				rTeam.addPlayer(pl);
				break;
			case 1:
				bTeam.addPlayer(pl);
				break;
			case 2:
				gTeam.addPlayer(pl);
				break;
			case 3:
				yTeam.addPlayer(pl);
				break;
			case 4:
				sTeam.addPlayer(pl);
				break;
			default:
				break;
		}
		update();
	}
	
	public void leaveTeam(Player pl, int team) {
		switch(team) {
			case 0:
				rTeam.removePlayer(pl);
				break;
			case 1:
				bTeam.removePlayer(pl);
				break;
			case 2:
				gTeam.removePlayer(pl);
				break;
			case 3:
				yTeam.removePlayer(pl);
				break;
			case 4:
				sTeam.removePlayer(pl);
				break;
			default:
				break;
		}
		update();
	}
	
	public Scoreboard get() {
		return scoreboard;
	}
	
}