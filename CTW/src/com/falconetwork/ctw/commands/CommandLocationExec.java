package com.falconetwork.ctw.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLocationExec implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command c, String cmd, String[] args) {
		if(sender instanceof Player) {
			Player pl = (Player) sender;
			Location l = pl.getLocation();
			float yaw = l.getYaw(), pitch = l.getPitch();
			double x = l.getX(), y = l.getY(), z = l.getY();
			pl.sendMessage("location[x=" + x + ",y=" + y + ",z=" + z +",yaw=" + yaw + ",pitch=" + pitch + "]");
		}
		return true;
	}

}