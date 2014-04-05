package com.falconetwork.ctw.util;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.teams.Team;

public class InventoryUtils {

	public static void giveInventory(Team team, CPlayer p, Player pl) {
		giveDefaults(pl);
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
		switch(TeamUtils.teamToInt(team)) {
		case 0: // RED
			meta.setColor(Color.RED);
			break;
		case 1: // BLUE
			meta.setColor(Color.BLUE);
			break;
		case 2: // GREEN
			meta.setColor(Color.GREEN);
			break;
		case 3: // YELLOW
			meta.setColor(Color.YELLOW);
			break;
		default:
			break;
		}
		helmet.setItemMeta(meta);
		pl.setGameMode(GameMode.SURVIVAL);
		pl.getInventory().setHelmet(helmet);
	}
	
	public static void giveDefaults(Player p) {
		PlayerInventory i = p.getInventory(); 
		i.clear();
		// HOTBAR
		{
			i.setItem(0, new ItemStack(Material.COOKED_BEEF, 16));
			i.setItem(1, new ItemStack(Material.IRON_SWORD, 1));
			
			ItemStack bow = new ItemStack(Material.BOW, 1);
			bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
			i.setItem(2, bow);
		
			ItemStack shop = new ItemStack(Material.NETHER_STAR);
			ItemMeta shopMeta = getItemMeta(shop);
			shopMeta.setDisplayName("§bVIP Shop");
			shop.setItemMeta(shopMeta);
			i.setItem(8, shop);
		}
		// ARMOR - NO HELMET BECAUSE ITS GIVEN IN giveInventory(Team, CPlayer, Player)
		{
			i.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
			i.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
			i.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
		}
		// INVENTORY
		{
			i.setItem(29, new ItemStack(Material.ARROW, 1));
		}
	}
	
	public static ItemMeta getItemMeta(ItemStack item) {
		return item.getItemMeta();
	}
	
}