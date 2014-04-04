package com.falconetwork.ctw.util;

import java.util.Set;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.CPlayer;

/**
 * Hold chat-related utilities.
 * @author Jatboy
 */
public class ChatUtils {

	/**
	 * Customizes chat message to have prefixes, colors, and custom look.
	 */
	public static void customizeChat(String msg, Player pl, CPlayer p, Set<Player> receivers) {
		String prefix = "";
		String message = msg;
		boolean dev = NameUtils.isDeveloper(pl);
		boolean donator = NameUtils.isDonator(pl);
		boolean owner = NameUtils.isOwner(pl);
		boolean mod = NameUtils.isModerator(pl);
		String nametag = "§8" + pl.getName() + "§r: ";
		if(dev) prefix = "§7[§cDev§7]§r ";
		if(owner) prefix = "§7[§1Owner§7]§r ";
		if(mod) prefix = "§7[§dModerator§7]§r ";
		if(donator && (!dev && !owner && !mod)) prefix = "§7[§6Donator§7]§r ";
		if(p.getTeam() != null)
			prefix += "§7[" + p.getTeamType().colored() + "§7] ";
		for(Player r : receivers) {
			r.sendMessage(prefix + nametag + message);
		}
	}
	
}