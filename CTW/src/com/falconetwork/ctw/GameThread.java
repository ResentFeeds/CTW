package com.falconetwork.ctw;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameThread implements Runnable {
	/** ID of the Thread */
	public static int id = 0;
	public static int gameTime = 481;
	public static int graceTime = 91;
	public static boolean inGrace = true;
	
	public void run() {
		String msg = "";
		if(!inGrace) {
			msg = "§6";
			if(gameTime == 0) {
				msg += "Game over!";
				announce(msg);
			} else {
				if(gameTime == 480) { msg += "8 minutes left!"; announce(msg); }
				if(gameTime == 420) { msg += "7 minutes left!"; announce(msg); }
				if(gameTime == 360) { msg += "6 minutes left!"; announce(msg); }
				if(gameTime == 300) { msg += "5 minutes left!"; announce(msg); }
				if(gameTime == 240) { msg += "4 minutes left!"; announce(msg); }
				if(gameTime == 180) { msg += "3 minutes left!"; announce(msg); }
				if(gameTime == 120) { msg += "2 minutes left!"; announce(msg); }
				if(gameTime == 60) { msg += "1 minute left!"; announce(msg); }
				if(gameTime == 30) { msg += "30 seconds left!"; announce(msg); }
				if(gameTime <= 5 && gameTime > 0) { msg += gameTime + " second" + (gameTime == 1 ? "" : "s") + " left!"; announce(msg); }
			}
		} else {
			msg = "§e";
			if(graceTime == 0) {
				msg += "The game has now begun!";
				announce(msg);
				for(int i = 0; i < 18; i++)
					announce("", false);
				inGrace = false;
			} else {
				if(graceTime == 90) { msg += "1 minute 30 seconds until the game starts!"; announce(msg); }
				if(graceTime == 60) { msg += "1 minute until the game starts!"; announce(msg); }
				if(graceTime == 30) { msg += "30 seconds until the game starts!"; announce(msg); }
				if(graceTime <= 5 && graceTime > 0) { msg += graceTime + " second" + (graceTime == 1 ? "" : "s") + " until the game starts!"; announce(msg); }
			}
		}
		if(gameTime > -1 && !inGrace) gameTime--;
		if(graceTime > -1 && inGrace) graceTime--;
	}
	
	public void announce(String message) {
		announce(message, true);
	}
	
	public void announce(String message, boolean prefix) {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			pl.sendMessage((prefix ? CTW.prefix : "") + message);
			pl.playSound(pl.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
		}
	}
	
}