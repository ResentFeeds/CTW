package com.falconetwork.ctw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.entity.Player;

import com.falconetwork.ctw.util.TeamType;
import com.falconetwork.fca.jnbt.CompoundTag;
import com.falconetwork.fca.jnbt.DoubleTag;
import com.falconetwork.fca.jnbt.IntTag;
import com.falconetwork.fca.jnbt.NBTInputStream;
import com.falconetwork.fca.jnbt.NBTOutputStream;
import com.falconetwork.fca.jnbt.Tag;

public class CPlayer {
	private int cash;
	private int wins;
	private int kills;
	private int deaths;
	private double kdr;
	private TeamType team;
	private Player player;
	private File dataFile;
	
	public CPlayer(Player player) {
		this.player = player;
		this.team = TeamType.UNKNOWN;
		this.dataFile = new File(CTW.playersFolder, player.getName() + ".dat");
		
		try {
			if(!dataFile.exists()) {
				dataFile.createNewFile();
				save();
			} else
				load();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void load() {
		try {
			FileInputStream fin = new FileInputStream(dataFile);
			NBTInputStream in = new NBTInputStream(new GZIPInputStream(fin));
			CompoundTag tag = (CompoundTag) in.readTag();
			in.close();
			Map<String, Tag> tags = tag.getValue();
			if(tags.containsKey("KDR")) kdr = ((IntTag) tags.get("KDR")).getValue();
			if(tags.containsKey("Cash")) cash = ((IntTag) tags.get("Cash")).getValue();
			if(tags.containsKey("Wins")) wins = ((IntTag) tags.get("Wins")).getValue();
			if(tags.containsKey("Kills")) kills = ((IntTag) tags.get("Kills")).getValue();
			if(tags.containsKey("Deaths")) deaths = ((IntTag) tags.get("Deaths")).getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
	
	public void save() {
		try {
			Map<String, Tag> tags = new HashMap<String, Tag>();
			tags.put("Cash", new IntTag("Cash", cash));
			tags.put("Wins", new IntTag("Wins", wins));
			tags.put("KDR", new DoubleTag("KDR", kdr));
			tags.put("Kills", new IntTag("Kills", kills));
			tags.put("Deaths", new IntTag("Deaths", deaths));
			CompoundTag tag = new CompoundTag("Player", tags);
			FileOutputStream fos = new FileOutputStream(dataFile);
			NBTOutputStream out = new NBTOutputStream(fos);
			out.writeTag(tag);
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	
	public void addCash(int cash) {
		this.cash += cash;
		if(this.cash < 0)
			this.cash = 0;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public void addWins(int wins) {
		this.wins += wins;
		if(this.wins < 0)
			this.wins = 0;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public void addKills(int kills) {
		this.kills += kills;
		if(this.kills < 0)
			this.kills = 0;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public void addDeaths(int deaths) {
		this.deaths += deaths;
		if(this.deaths < 0)
			this.deaths = 0;
	}

	public double getKdr() {
		return kdr;
	}

	public void setKdr(double kdr) {
		this.kdr = kdr;
	}
	
	public void calcKDR() {
		this.kdr = (double) (kills / deaths);
	}

	public TeamType getTeamType() {
		return team;
	}
	
	public void setTeamType(TeamType team) {
		this.team = team;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
}