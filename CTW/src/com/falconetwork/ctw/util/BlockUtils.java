package com.falconetwork.ctw.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.falconetwork.ctw.CPlayer;

public class BlockUtils {

	/**
	 * Makes the player invincible, and makes him blind and slow.
	 */
	public static void pickupWool(CPlayer p, Player pl) {
		pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2), true);
		pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0), true);
		pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 9), true);
	}
	
	/**
	 * Clears all effects from wool.
	 */
	public static void dropWool(CPlayer p, Player pl) {
		pl.removePotionEffect(PotionEffectType.SLOW);
		pl.removePotionEffect(PotionEffectType.BLINDNESS);
		pl.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
	}
	
	/**
	 * Checks if the location specified is in center tower not their spawn tower.
	 */
	public static boolean isObjective(Location l) {
		boolean objective = false;
		// int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
		// TODO: Code for checking if they are the pieces in the central tower not spawn.
		return objective;
	}
	
}