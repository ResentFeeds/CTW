package com.falconetwork.ctw;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falconetwork.fca.api.IconMenu;

public class VIPShop extends IconMenu {
	private Player pl;
	private CPlayer p;
	
	public VIPShop(CPlayer p) {
		super("VIP Shop", 9, CTW.get(), new IconMenu.OptionClickEventHandler() {
			public void onOptionClick(OptionClickEvent event) {}
		});
		this.p = p;
		this.pl = p.getPlayer();
		setup();
	}
	
	public void setup() {
		setOption(0, new ItemStack(Material.SKULL_ITEM, 1, (short) 3), "VIP", "Includes:", "§7VIP Weapons", "§7VIP Armor", "§7VIP Nametag", "§6Requires: §7$2.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(1, new ItemStack(Material.ENDER_PEARL, 1), "Enderpearl Arrows", "§7Shoot enderpearls instead of arrows!", "§6Requires: §7$3.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(2, new ItemStack(Material.POTION, 1), "Invincibility", "§7Makes you temporarily invincible!", "§7Knockback is still a factor!", "§6Requires: §7$3.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(3, new ItemStack(Material.TNT, 1), "TNT Arrows", "§7Shoot tnt instead of arrows!", "§6Requires: §7$5.50 Donated", "§6Current: §7$" + p.getDonated());
		setOption(4, new ItemStack(Material.POTION, 1), "Juggernaut Potion", "§7Fight your way through the enemies!", "§6Requires: §7$7.00 Donated", "§6Current: §7$" + p.getDonated());
		setOption(5, new ItemStack(Material.ARROW, 1), "Knockback", "§7Reduces knockback you take.", "§7Increases knockback you give.", "§6Requires: §7$7.00 Donated", "§6Current: §7$" + p.getDonated());
		setOption(8, new ItemStack(Material.REDSTONE, 1), "Clear", "§7Reset your player to default state.", "§6Requires: §7$0.50+ Donated", "§6Current: §7$" + p.getDonated());
	}
	
	public CPlayer getCustomPlayer() {
		return p;
	}
	
	public Player getDefaultPlayer() {
		return pl;
	}
	
}