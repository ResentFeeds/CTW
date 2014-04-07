package com.falconetwork.ctw.listeners;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_7_R2.EntityChicken;
import net.minecraft.server.v1_7_R2.EntityLiving;
import net.minecraft.server.v1_7_R2.PacketPlayOutSpawnEntityLiving;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.falconetwork.ctw.CPlayer;
import com.falconetwork.ctw.CTW;
import com.falconetwork.ctw.util.PacketUtils;

public class PerkListener implements Listener {
	public static Map<Player, EntityChicken> chickens = new HashMap<Player, EntityChicken>();
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBowShoot(EntityShootBowEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player pl = (Player) e.getEntity();
		Arrow arrow = (Arrow) e.getProjectile();
		CPlayer p = CTW.players.get(pl.getUniqueId());
		if(p.hasPerk("enderpearlArrows")) {
			e.setCancelled(true);
			arrow.remove();
			EnderPearl pearl = pl.launchProjectile(EnderPearl.class);
			pearl.setShooter(pl);
			pearl.setVelocity(arrow.getVelocity());
			pl.playSound(pl.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
		} else
		if(p.hasPerk("tntArrows")) {
			e.setCancelled(true);
			TNTPrimed tnt = (TNTPrimed) pl.getWorld().spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT);
			tnt.setVelocity(arrow.getVelocity());
			pl.playSound(pl.getLocation(), Sound.CREEPER_HISS, 1.0F, 1.0F);
		} else
		if(p.hasPerk("eggArrows")) {
			e.setCancelled(true);
			Egg egg = pl.launchProjectile(Egg.class);
			egg.setShooter(pl);
			egg.setVelocity(arrow.getVelocity());
			pl.playSound(pl.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
		} else
		if(p.hasPerk("witherArrows")) {
			e.setCancelled(true);
			WitherSkull wither = pl.launchProjectile(WitherSkull.class);
			wither.setShooter(pl);
			wither.setVelocity(arrow.getVelocity());
			pl.playSound(pl.getLocation(), Sound.WITHER_SHOOT, 1.0F, 1.0F);
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(e.getEntity().getType() == EntityType.CHICKEN && e.getSpawnReason() == SpawnReason.EGG) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile p = (Projectile) e.getEntity();
		if(p.getType() == EntityType.ARROW) {
			p.remove();
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if((e.getDamager() instanceof Egg) && (e.getEntity() instanceof Player)) {
			Player pl = (Player) e.getEntity();
			/* Below removes the player, replacing him with a named chicken. */
			for(Player p : Bukkit.getOnlinePlayers())
				if(!p.equals((Player) e.getEntity()))
					p.hidePlayer(pl);
			
			EntityLiving chick = PacketUtils.createEntity(pl.getLocation(), EntityType.CHICKEN, pl.getName());
			PacketPlayOutSpawnEntityLiving spawn = PacketUtils.createSpawnPacket(chick);
			PacketUtils.sendPacket(pl, spawn);
			if(!chickens.containsKey(pl))
				chickens.put(pl, (EntityChicken) chick);
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		if(e.getEntity().getType() == EntityType.PRIMED_TNT) {
			e.setCancelled(true);
			World w = e.getEntity().getWorld();
			Location l = e.getEntity().getLocation();
			w.createExplosion(l.getX(), l.getY(), l.getZ(), 2.0F, false, false);
		} else
		if(e.getEntity().getType() == EntityType.WITHER_SKULL) {
			e.setCancelled(true);
			World w = e.getEntity().getWorld();
			Location l = e.getEntity().getLocation();
			w.createExplosion(l.getX(), l.getY(), l.getZ(), 1.0F, false, false);
		}
	}
	
}