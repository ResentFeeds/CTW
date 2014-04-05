package com.falconetwork.ctw.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;

public class BlockUtils {

	/**
	 * Makes the player invincible, and makes him blind and slow.
	 */
	public static void pickupWool(CPlayer p, Player pl, TeamType type) {
		pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2), true);
		pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0), true);
		pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 9), true);
		String message = CTW.prefix + "§9";
		String message2 = CTW.prefix + "§7";
		if(type.getLocalized().equals("Red")) {
			message += pl.getName() + "§7 has the §c" + type.getLocalized() + "§7 wool!";
			message2 += "You have the §c" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Blue")) {
			message += pl.getName() + "§7 has the §b" + type.getLocalized() + "§7 wool!";
			message2 += "You have the §b" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Green")) {
			message += pl.getName() + "§7 has the §a" + type.getLocalized() + "§7 wool!";
			message2 += "You have the §a" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Yellow")) {
			message += pl.getName() + "§7 has the §e" + type.getLocalized() + "§7 wool!";
			message2 += "You have the §e" + type.getLocalized() + "§7 wool!";
		}
			
		for(Player pp : Bukkit.getOnlinePlayers()) {
			if(pp.equals(pl)) {
				pp.sendMessage(message2);
			} else {
				pp.sendMessage(message);
			}
		}
	}
	
	/**
	 * Clears all effects from wool.
	 */
	public static void dropWool(CPlayer p, Player pl, TeamType type) {
		pl.removePotionEffect(PotionEffectType.SLOW);
		pl.removePotionEffect(PotionEffectType.BLINDNESS);
		pl.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		String message = CTW.prefix + "§9";
		String message2 = CTW.prefix + "§7";
		if(type.getLocalized().equals("Red")) {
			message += pl.getName() + "§7 has dropped the §c" + type.getLocalized() + "§7 wool!";
			message2 += "You have dropped the §c" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Blue")) {
			message += pl.getName() + "§7 has dropped the §b" + type.getLocalized() + "§7 wool!";
			message2 += "You have dropped the §b" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Green")) {
			message += pl.getName() + "§7 has dropped the §a" + type.getLocalized() + "§7 wool!";
			message2 += "You have dropped the §a" + type.getLocalized() + "§7 wool!";
		}
		if(type.getLocalized().equals("Yellow")) {
			message += pl.getName() + "§7 has dropped the §e" + type.getLocalized() + "§7 wool!";
			message2 += "You have dropped the §e" + type.getLocalized() + "§7 wool!";
		}
			
		for(Player pp : Bukkit.getOnlinePlayers()) {
			if(pp.equals(pl)) {
				pp.sendMessage(message2);
			} else {
				pp.sendMessage(message);
			}
		}
	}
	
	public static boolean redObject(Location l) {
		boolean objective = false;
		int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		if((x == -691 && y == 53 && z == 530)) objective = true;
		if((x == -691 && y == 53 && z == 519)) objective = true;
		if((x == -680 && y == 53 && z == 519)) objective = true;
		if((x == -680 && y == 53 && z == 530)) objective = true;
		return objective;
	}
	
	public static boolean blueObject(Location l) {
		boolean objective = false;
		int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		if((x == -691 && y == 53 && z == 623)) objective = true;
		if((x == -691 && y == 53 && z == 634)) objective = true;
		if((x == -680 && y == 53 && z == 634)) objective = true;
		if((x == -680 && y == 53 && z == 623)) objective = true;
		return objective;
	}
	
	public static boolean greenObject(Location l) {
		boolean objective = false;
		int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		if((x == -639 && y == 53 && z == 571)) objective = true;
		if((x == -628 && y == 53 && z == 571)) objective = true;
		if((x == -628 && y == 53 && z == 582)) objective = true;
		if((x == -639 && y == 53 && z == 582)) objective = true;
		return objective;
	}
	
	public static boolean yellowObject(Location l) {
		boolean objective = false;
		int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		if((x == -732 && y == 53 && z == 582)) objective = true;
		if((x == -743 && y == 53 && z == 582)) objective = true;
		if((x == -743 && y == 53 && z == 571)) objective = true;
		if((x == -732 && y == 53 && z == 571)) objective = true;
		return objective;
	}
	
	/**
	 * Checks if the location specified is in center tower not their spawn tower.
	 */
	public static boolean isObjective(Location l) {
		boolean objective = false;
		int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		if((x == -686 && y == 69 && z == 576)) objective = true; // RED
		if((x == -685 && y == 69 && z == 576)) objective = true; // GREEN
		if((x == -685 && y == 69 && z == 577)) objective = true; // BLUE
		if((x == -686 && y == 69 && z == 577)) objective = true; // YELLOW
		return objective;
	}
	
	@SuppressWarnings("deprecation")
	public static void regenObjectives(World w) {
		Block red = new Location(w, -686, 69, 576).getBlock();
		Block blue = new Location(w, -685, 69, 577).getBlock();
		Block green = new Location(w, -685, 69, 576).getBlock();
		Block yellow = new Location(w, -686, 69, 577).getBlock();
		red.setType(Material.WOOL); blue.setType(Material.WOOL); green.setType(Material.WOOL); yellow.setType(Material.WOOL);
		red.setData((byte) 14); blue.setData((byte) 11); green.setData((byte) 5); yellow.setData((byte) 4);
	}
	
}