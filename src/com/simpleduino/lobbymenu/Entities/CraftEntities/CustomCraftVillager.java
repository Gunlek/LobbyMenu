package com.simpleduino.lobbymenu.Entities.CraftEntities;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;

/**
 * Created by Simple-Duino on 26/08/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class CustomCraftVillager extends EntityVillager {

    private CustomCraftVillager(World world) {
        super(world);
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
    }

    @Override
    public void collide(Entity entity)
    {

    }

    @Override
    public void g(double d0, double d1, double d2)
    {

    }

    public static Villager spawn(Location l, String customName, Villager.Profession profession){
        CustomCraftVillager villager = new CustomCraftVillager(((CraftWorld)l.getWorld()).getHandle());
        ArmorStand armorStand = (ArmorStand)l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
        armorStand.setCustomName(customName);
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        villager.setLocation(l.getX(), l.getY(), l.getZ(), l.getPitch(), l.getYaw());
        villager.setProfession(profession.getId());
        ((CraftWorld)l.getWorld()).getHandle().addEntity(villager);
        return (Villager) villager.getBukkitEntity();
    }
}
