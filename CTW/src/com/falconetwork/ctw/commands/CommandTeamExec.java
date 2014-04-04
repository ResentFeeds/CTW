package com.falconetwork.ctw.commands;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.teams.Team;
import com.falconetwork.ctw.teams.events.TeamJoinEvent;
import com.falconetwork.ctw.teams.events.TeamLeaveEvent;

public class CommandTeamExec implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command c, String cmd, String[] args) {
		if (sender instanceof Player) {
			Player pl = (Player) sender;
			CPlayer p = CTW.players.get(pl.getUniqueId());
			if (args.length == 2 || args.length == 1) {
				if (args[0].equalsIgnoreCase("join")) {
					if (args.length == 2) {
						if (!p.isInTeam()) {
							Team team = Team.getTeam(args[1]);
							Validate.notNull(team, "Team must not be null! Name: " + args[1]);
							TeamJoinEvent e = new TeamJoinEvent(team, pl);
							Bukkit.getPluginManager().callEvent(e);
							if (!e.isCancelled()) {
								p.setTeam(team);
								team.onJoin(e);
								p.setCarrying(false);
							}
						} else
							pl.sendMessage(CTW.prefix + "§4Error! §7You're already in a team!");
					} else
						pl.sendMessage(CTW.prefix + "§4Error! §7Usage: /team join [blue/red/green/yellow]");
				}
				if (args[0].equalsIgnoreCase("leave")) {
					if (args.length == 1) {
						if (p.isInTeam()) {
							Team team = p.getTeam();
							Validate.notNull(team, "Team must not be null!");
							TeamLeaveEvent e = new TeamLeaveEvent(team, pl);
							Bukkit.getPluginManager().callEvent(e);
							if (!e.isCancelled()) {
								p.setTeam(null);
								team.onLeave(e);
								p.setCarrying(false);
							}
						} else
							pl.sendMessage(CTW.prefix + "§4Error! §7You aren't in a team!");
					} else
						pl.sendMessage(CTW.prefix + "§4Error! §7Usage: /team leave");
				}
			} else
				pl.sendMessage(CTW.prefix + "§4Error! §7Usage: /team join/leave [blue/red/green/yellow]");
		} else
			sender.sendMessage(ChatColor.stripColor(CTW.prefix) + "Error! You have to be a player to use /team");
		return true;
	}

}