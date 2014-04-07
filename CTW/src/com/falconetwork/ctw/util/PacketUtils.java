package com.falconetwork.ctw.util;

import net.minecraft.server.v1_7_R2.EntityLiving;
import net.minecraft.server.v1_7_R2.Packet;
import net.minecraft.server.v1_7_R2.PacketPlayOutSpawnEntityLiving;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PacketUtils {

	public static void sendPacket(Player pl, Packet packet) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!p.equals(pl)) {
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}
	
	public static EntityLiving createEntity(Location l, EntityType type) {
		return ((EntityLiving) ((CraftLivingEntity) l.getWorld().spawnEntity(l, type)).getHandle());
	}
	
	public static EntityLiving createEntity(Location l, EntityType type, String name) {
		LivingEntity e = (LivingEntity) l.getWorld().spawnEntity(l, type);
		e.setCustomName(name);
		e.setCustomNameVisible(true);
		return ((EntityLiving) ((CraftLivingEntity) e).getHandle());
	}
	
	public static PacketPlayOutSpawnEntityLiving createSpawnPacket(EntityLiving e) {
		return (new PacketPlayOutSpawnEntityLiving(e));
	}
	
}