package com.falconetwork.ctw;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.ctw.util.NameUtils;
import com.falconetwork.fca.api.IconMenu;

public class VIPShop extends IconMenu {
	private Player pl;
	private CPlayer p;
	
	public VIPShop(CPlayer p) {
		super("VIP Shop", 9, CTW.get(), new IconMenu.OptionClickEventHandler() {
			public void onOptionClick(OptionClickEvent event) {
				Player pl = event.getPlayer();
				int slot = event.getPosition();
				CPlayer p = CTW.players.get(pl.getUniqueId());
				
				double donated = p.getDonated();
				if(slot == 1) if(donated >= 3.50 || NameUtils.isDeveloper(pl) || NameUtils.isOwner(pl)) { 
					p.addPerk("enderpearlArrows"); p.removePerk("eggArrows"); p.removePerk("tntArrows"); p.removePerk("witherArrows"); 
					pl.sendMessage(CTW.prefix + "§eYou now have §7Enderpearl§e arrows!");
				}
				if(slot == 2) if(donated >= 3.50 || NameUtils.isDeveloper(pl) || NameUtils.isOwner(pl)) { 
					p.addPerk("eggArrows"); p.removePerk("enderpearlArrows"); p.removePerk("tntArrows"); p.removePerk("witherArrows"); 
					pl.sendMessage(CTW.prefix + "§eYou now have §7Egg§e arrows!");
				}
				if(slot == 4) if(donated >= 5.50 || NameUtils.isDeveloper(pl) || NameUtils.isOwner(pl)) { 
					p.addPerk("tntArrows"); p.removePerk("enderpearlArrows"); p.removePerk("eggArrows"); p.removePerk("witherArrows"); 
					pl.sendMessage(CTW.prefix + "§eYou now have §7TNT§e arrows!");
				
					pl.sendMessage(CTW.prefix + "§eYou now have enderpearl arrows!");}
				if(slot == 7) if(donated >= 5.50 || NameUtils.isDeveloper(pl) || NameUtils.isOwner(pl)) {
					p.addPerk("witherArrows"); p.removePerk("enderpearlArrows"); p.removePerk("eggArrows"); p.removePerk("tntArrows"); 
					pl.sendMessage(CTW.prefix + "§eYou now have §7Wither§e arrows!");
				}
				if(slot == 8) if(donated >= 0.50 || NameUtils.isDeveloper(pl) || NameUtils.isOwner(pl)) {  
					p.removePerk("enderpearlArrows"); p.removePerk("eggArrows"); p.removePerk("tntArrows"); p.removePerk("witherArrows"); 
					pl.sendMessage(CTW.prefix + "§eYou're back to normal!");
				}
			}
		});
		this.p = p;
		this.pl = p.getPlayer();
		setup();
	}
	
	public void setup() {
		setOption(0, new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "§rVIP", "§6Includes:", "§7VIP Weapons", "§7VIP Armor", "§7VIP Nametag", "§6Requires: §7$2.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(1, new ItemStack(Material.ENDER_PEARL, 1), "§rEnderpearl Arrows", "§7Shoot enderpearls instead of arrows!", "§6Requires: §7$3.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(2, new ItemStack(Material.EGG, 1), "§rEgg Arrows", "§7Shoot eggs instead of arrows!", "§7On impact the target is turned into a chicken!", "§6Requires: §7$3.50 Donated", "§6Current: $" + p.getDonated());
		setOption(3, new ItemStack(Material.POTION, 1), "§rInvincibility", "§7Makes you temporarily invincible!", "§7Knockback is still a factor!", "§6Requires: §7$3.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(4, new ItemStack(Material.TNT, 1), "§rTNT Arrows", "§7Shoot tnt instead of arrows!", "§6Requires: §7$5.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(5, new ItemStack(Material.POTION, 1), "§rJuggernaut Potion", "§7Fight your way through the enemies!", "§6Requires: §7$7.00 Donated", "§6Current: §7$" + p.getDonated());
		setOption(6, new ItemStack(Material.ARROW, 1), "§rKnockback", "§7Reduces knockback you take.", "§7Increases knockback you give.", "§6Requires: §7$7.00 Donated", "§6Current: §7$" + p.getDonated());
		setOption(7, new ItemStack(Material.SKULL_ITEM, 1, (short) 1), "§rWither Arrows", "§7Shoot wither heads instead of arrows!", "§6Requires: §7$7.50 Donated", "§6Current: §7$" + p.getDonated());
		
		setOption(8, new ItemStack(Material.REDSTONE, 1), "§rClear", "§7Reset your player to default state.", "§6Requires: §7$0.50+ Donated", "§6Current: §7$" + p.getDonated());
	}
	
	public CPlayer getCustomPlayer() {
		return p;
	}
	
	public Player getDefaultPlayer() {
		return pl;
	}
	
}