package com.falconetwork.ctw.util;

import org.bukkit.entity.Player;

/**
 * Will be used for detecting if a player is one of the following:
 * Donator, Developer, Owner, Moderator, ect..
 * @author Jatboy
 */
public class NameUtils {

	public static boolean isDeveloper(Player pl) {
		boolean developer = false;
		String name = pl.getName();
		if(name.equalsIgnoreCase("jatboy")) developer = true;
		return developer;
	}
	
	public static boolean isDonator(Player pl) {
		boolean donator = false;
		return donator;
	}
	
	public static boolean isOwner(Player pl) {
		boolean owner = false;
		return owner;
	}
	
	public static boolean isModerator(Player pl) {
		boolean moderator = false;
		return moderator;
	}
	
}