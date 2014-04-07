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
		if(name.equals("jatboy")) developer = true;
		return developer;
	}
	
	public static boolean isDonator(Player pl) {
		boolean donator = false;
		try {
			/*ResultSet set = CTW.donators.querySQL("SELECT Player FROM [Donators]");
			ResultSetMetaData data = set.getMetaData();
			int columnCount = data.getColumnCount();
			
			String[] values = new String[columnCount];
			
			while (set.next()) {
				for (int i = 1; i <= data.getColumnCount(); i++)
					values[i - 1] = set.getString(i);
			}
			
			for(String s : values) {
				if(pl.getName().equals(s)) {
					donator = true;
					break;
				}
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return donator;
	}
	
	public static boolean isOwner(Player pl) {
		boolean owner = false;
		String name = pl.getName();
		if(name.equals("Car12man")) owner = true;
		return owner;
	}
	
	public static boolean isModerator(Player pl) {
		boolean moderator = false;
		return moderator;
	}
	
}