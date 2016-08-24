package com.simpleduino.lobbymenu.particules;

import com.simpleduino.lobbymenu.Listeners.MenuListeners.SettingsMenuListener;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.SQL.LobbySQL;
import com.simpleduino.shopAPI.APIObjects.ParticleType;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.inventivetalent.particle.ParticleEffect;

import java.util.ArrayList;

public class Particles {
    private static ArrayList<String> acheté = new ArrayList<>();
    private static ItemStack i = new ItemStack(Material.ARROW);
    private static ItemStack desac = new ItemStack(Material.BARRIER);
    private LobbySQL lobbySQL = new LobbySQL();

    public void initParticles() {
        acheté.add(ChatColor.GREEN + "Acheté");
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.WHITE + "Retour");
        i.setItemMeta(im);
        ItemMeta desacm = desac.getItemMeta();
        desacm.setDisplayName(ChatColor.DARK_RED + "Désactiver");
        desac.setItemMeta(desacm);
        for(ParticleType pType : ParticleType.values())
            LobbyMenuPlugin.particles.put(pType, new ArrayList<Player>());
        this.flames();
        this.rain();
        this.hearts();
        this.enchantment();
        this.slimeball();
        this.lava();
        this.angryVillager();
        this.happyVillager();
        this.smoke();
        this.diamond();
        this.emerald();
        this.neige();
        this.note();
    }

    public Inventory getShopInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.DARK_GREEN+"Particules");
        ItemStack i1 = new ItemStack(Material.FIREBALL);
        ItemStack i2 = new ItemStack(Material.SNOW_BALL);
        ItemStack i3 = new ItemStack(Material.WATER_BUCKET);
        ItemStack i4 = new ItemStack(Material.NOTE_BLOCK);
        ItemStack i5 = new ItemStack(Material.DOUBLE_PLANT);
        ItemStack i6 = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemStack i7 = new ItemStack(Material.SLIME_BALL);
        ItemStack i8 = new ItemStack(Material.MAGMA_CREAM);
        ItemStack i9 = new ItemStack(Material.DEAD_BUSH);
        ItemStack i10 = new ItemStack(Material.EMERALD);
        ItemStack i11 = new ItemStack(Material.FLINT_AND_STEEL);
        ItemStack i12 = new ItemStack(Material.DIAMOND);
        ItemStack i13 = new ItemStack(Material.EMERALD);
        ItemMeta im1 = i1.getItemMeta();
        ArrayList<String> playerParticles = lobbySQL.getPlayerParticles(player);
        im1.setDisplayName(ChatColor.YELLOW + "Flames");
        if (playerParticles.contains("flames")) {
            im1.setLore(acheté);
        } else {
            im1.setLore(LobbyMenuPlugin.nonacheté(200, false, 1, player));
        }
        i1.setItemMeta(im1);
        ItemMeta im2 = i2.getItemMeta();
        im2.setDisplayName(ChatColor.YELLOW + "Neige");
        if (playerParticles.contains("neige")) {
            im2.setLore(acheté);
        } else {
            im2.setLore(LobbyMenuPlugin.nonacheté(500, false, 1, player));
        }
        i2.setItemMeta(im2);
        ItemMeta im3 = i3.getItemMeta();
        im3.setDisplayName(ChatColor.YELLOW + "Pluie");
        if (playerParticles.contains("pluie")) {
            im3.setLore(acheté);
        } else {
            im3.setLore(LobbyMenuPlugin.nonacheté(1000, false, 1, player));
        }
        i3.setItemMeta(im3);
        ItemMeta im4 = i4.getItemMeta();
        im4.setDisplayName(ChatColor.YELLOW + "Note");
        if (playerParticles.contains("note")) {
            im4.setLore(acheté);
        } else {
            im4.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i4.setItemMeta(im4);
        ItemMeta im5 = i5.getItemMeta();
        im5.setDisplayName(ChatColor.YELLOW + "Coeurs");
        if (playerParticles.contains("hearts")) {
            im5.setLore(acheté);
        } else {
            im5.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i5.setDurability((short)4);
        i5.setItemMeta(im5);
        ItemMeta im6 = i6.getItemMeta();
        im6.setDisplayName(ChatColor.YELLOW + "Enchantement");
        if (playerParticles.contains("enchantment")) {
            im6.setLore(acheté);
        } else {
            im6.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i6.setItemMeta(im6);
        ItemMeta im7 = i7.getItemMeta();
        im7.setDisplayName(ChatColor.YELLOW + "Slime");
        if (playerParticles.contains("slimeball")) {
            im7.setLore(acheté);
        } else {
            im7.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i7.setItemMeta(im7);
        ItemMeta im8 = i8.getItemMeta();
        im8.setDisplayName(ChatColor.YELLOW + "Lave");
        if (playerParticles.contains("lava")) {
            im8.setLore(acheté);
        } else {
            im8.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i8.setItemMeta(im8);
        ItemMeta im9 = i9.getItemMeta();
        im9.setDisplayName(ChatColor.YELLOW + "Villageois méchant");
        if (playerParticles.contains("angry_villager")) {
            im9.setLore(acheté);
        } else {
            im9.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i9.setItemMeta(im9);
        ItemMeta im10 = i10.getItemMeta();
        im10.setDisplayName(ChatColor.YELLOW + "Villageois gentil");
        if (playerParticles.contains("happy_villager")) {
            im10.setLore(acheté);
        } else {
            im10.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i10.setItemMeta(im10);
        ItemMeta im11 = i11.getItemMeta();
        im11.setDisplayName(ChatColor.YELLOW + "Fumée");
        if (playerParticles.contains("smoke")) {
            im11.setLore(acheté);
        } else {
            im11.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i11.setItemMeta(im11);
        ItemMeta im12 = i12.getItemMeta();
        im12.setDisplayName(ChatColor.YELLOW + "Diamants");
        if (playerParticles.contains("diamond")) {
            im12.setLore(acheté);
        } else {
            im12.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i12.setItemMeta(im12);
        ItemMeta im13 = i13.getItemMeta();
        im13.setDisplayName(ChatColor.YELLOW + "Emeraudes");
        if (playerParticles.contains("emerald")) {
            im13.setLore(acheté);
        } else {
            im13.setLore(LobbyMenuPlugin.nonacheté(1200, false, 2, player));
        }
        i13.setItemMeta(im13);
        inv.setItem(0, i1);
        inv.setItem(1, i2);
        inv.setItem(2, i3);
        inv.setItem(3, i4);
        inv.setItem(4, i5);
        inv.setItem(5, i6);
        inv.setItem(6, i7);
        inv.setItem(7, i8);
        inv.setItem(8, i9);
        inv.setItem(9, i10);
        inv.setItem(10, i11);
        inv.setItem(11, i12);
        inv.setItem(12, i13);
        inv.setItem(25, desac);
        inv.setItem(26, i);
        return inv;
    }

    private void flames() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;
            double y;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.FLAME).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.FLAME)) {
                        double x = this.count * 3.141592653589793 / 12.0;
                        Location loc3 = new Location(particleplayer.getWorld(), particleplayer.getLocation().getX() + Math.cos(x), particleplayer.getLocation().getY() + this.y, particleplayer.getLocation().getZ() + Math.sin(x));
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (LobbyMenuPlugin.noparticules.contains(online)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                ParticleEffect.FLAME.send(l, loc3, 0.0, 0.0, 0.0, 0.0, 1);
                                ParticleEffect.FLAME.send(l, loc3, 0.0, 0.0, 0.0, 0.0, 1);
                                ParticleEffect.FLAME.send(l, loc3, 0.0, 0.0, 0.0, 0.0, 1);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        double d = this.y = this.y == 0.0 ? 2.0 : this.y - 0.25;
                        if (this.count >= 24.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void rain() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.PLUIE).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.PLUIE)) {
                        Location l1temp = particleplayer.getLocation();
                        Location l1 = new Location(l1temp.getWorld(), l1temp.getX(), l1temp.getY() + 2.0, l1temp.getZ());
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                for (double radius = 1.0; radius > 0.0; radius -= 0.5) {
                                    for (Location l2 : Particles.this.getCircle(l1, radius, 24)) {
                                        ParticleEffect.WATER_DROP.send(l, l2, 0.0, 0.0, 0.0, 0.009999999776482582, 1);
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 24.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void lava() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.LAVA).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.LAVA)) {
                        Location l1temp = particleplayer.getLocation();
                        Location l1 = new Location(l1temp.getWorld(), l1temp.getX(), l1temp.getY() + 2.0, l1temp.getZ());
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                for (double radius = 1.0; radius > 0.0; radius -= 0.5) {
                                    for (Location l2 : Particles.this.getCircle(l1, radius, 24)) {
                                        ParticleEffect.DRIP_LAVA.send(l, l2, 0.0, 0.0, 0.0, 0.0, 1);
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 0, 5);
    }

    private void hearts() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.HEARTS).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.HEARTS)) {
                        Location l1temp = particleplayer.getLocation();
                        Location l1 = new Location(l1temp.getWorld(), l1temp.getX(), l1temp.getY() + 2.0, l1temp.getZ());
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                for (Location l2 : Particles.this.getCircle(l1, 0.5, 6)) {
                                    ParticleEffect.HEART.send(l, l2, 0.0, 0.0, 0.0, 0.0, 1);
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 12.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 10);
    }

    private void angryVillager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.ANGRY_VILLAGER).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.ANGRY_VILLAGER)) {
                        double x = this.count * 3.141592653589793 / 6.0;
                        Location l1 = new Location(particleplayer.getWorld(), particleplayer.getLocation().getX() + Math.cos(x) / 2.0, particleplayer.getLocation().getY() + 2.0, particleplayer.getLocation().getZ() + Math.sin(x) / 2.0);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                ParticleEffect.VILLAGER_ANGRY.send(l, l1, 0.0, 0.0, 0.0, 0.0, 1);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 12.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void happyVillager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.HAPPY_VILLAGER).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.HAPPY_VILLAGER)) {
                        double x = this.count * 3.141592653589793 / 6.0;
                        Location l1 = new Location(particleplayer.getWorld(), particleplayer.getLocation().getX() + Math.cos(x) / 2.0, particleplayer.getLocation().getY() + 2.0, particleplayer.getLocation().getZ() + Math.sin(x) / 2.0);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                ParticleEffect.VILLAGER_HAPPY.send(l, l1, 0.0, 0.0, 0.0, 0.0, 1);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 12.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void smoke() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.SMOKE).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.SMOKE)) {
                        Location l1 = particleplayer.getLocation();
                        l1.setY(l1.getY() + 2.3);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                for (Location l2 : Particles.this.getCircle(l1, 0.1, 7)) {
                                    ParticleEffect.SMOKE_LARGE.send(l, l2, 0.0, 0.0, 0.0, 0.0, 1);
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 0, 1);
    }

    private void diamond() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.DIAMOND).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.DIAMOND)) {
                        Location l1 = particleplayer.getLocation();
                        l1.setY(l1.getY() + 2.3);
                        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
                        ItemMeta im = diamond.getItemMeta();
                        im.setDisplayName("Diamond_particle");
                        diamond.setItemMeta(im);
                        ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 1);
                        diamondBlock.setItemMeta(im);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                for (Location l2 : Particles.this.getCircle(l1, 0.1, 5)) {
                                    final Item item = l2.getWorld().dropItem(l2, diamond);
                                    Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

                                        @Override
                                        public void run() {
                                            item.remove();
                                        }
                                    }, 15);
                                    final Item item2 = l2.getWorld().dropItem(l2, diamondBlock);
                                    Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

                                        @Override
                                        public void run() {
                                            item2.remove();
                                        }
                                    }, 15);
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }, 0, 5);
    }

    private void emerald() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.EMERALD).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.EMERALD)) {
                        Location l1 = particleplayer.getLocation();
                        l1.setY(l1.getY() + 2.3);
                        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
                        ItemMeta im = emerald.getItemMeta();
                        im.setDisplayName("Diamond_particle");
                        emerald.setItemMeta(im);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                for (Location l2 : Particles.this.getCircle(l1, 0.1, 5)) {
                                    final Item item = l2.getWorld().dropItem(l2, emerald);
                                    Bukkit.getScheduler().runTaskLater(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

                                        @Override
                                        public void run() {
                                            item.remove();
                                        }
                                    }, 15);
                                }
                            }
                            continue;
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
            }

        }, 0, 1);
    }

    private void enchantment() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.ENCHANTMENT).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.ENCHANTMENT)) {
                        double x = this.count * 3.141592653589793 / 6.0;
                        Location l1 = new Location(particleplayer.getWorld(), particleplayer.getLocation().getX() + Math.cos(x) / 2.0, particleplayer.getLocation().getY() + 2.0, particleplayer.getLocation().getZ() + Math.sin(x) / 2.0);
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                ParticleEffect.ENCHANTMENT_TABLE.send(l, l1, 0.0, 0.0, 0.0, 0.30000001192092896, 1, 50.0);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 12.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void slimeball() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){
            double count;

            @Override
            public void run() {
                if (LobbyMenuPlugin.particles.get(ParticleType.SLIMEBALL).size() >= 1) {
                    for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.SLIMEBALL)) {
                        Location l1 = particleplayer.getLocation();
                        try {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (!SettingsMenuListener.doPlayerHasParticlesEnabled(particleplayer)) continue;
                                ArrayList<Player> l = new ArrayList<>();
                                l.add(online);
                                for (double radius = 1.5; radius > 0.0; radius -= 0.5) {
                                    for (Location l2 : Particles.this.getCircle(l1, radius, 24)) {
                                        ParticleEffect.SLIME.send(l, l2, 0.0, 0.0, 0.0, 0.30000001192092896, 1, 50.0);
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.count >= 12.0) {
                            this.count = 0.0;
                        }
                        this.count += 1.0;
                    }
                }
            }
        }, 0, 1);
    }

    private void neige()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbyMenuPlugin.getPlugin((Class)LobbyMenuPlugin.class), new Runnable(){

            @Override
            public void run() {
                if (Bukkit.getServer().getOnlinePlayers().size() >= 1) {
                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                        if (LobbyMenuPlugin.noparticules.contains(online)) continue;
                        if (LobbyMenuPlugin.particles.get(ParticleType.NEIGE).size() >= 1) {
                            for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.NEIGE)) {
                                online.playEffect(particleplayer.getLocation(), Effect.CLOUD, 1);
                                online.playEffect(particleplayer.getLocation(), Effect.CLOUD, 1);
                                online.playEffect(particleplayer.getLocation(), Effect.CLOUD, 1);
                                online.playEffect(particleplayer.getLocation(), Effect.CLOUD, 1);
                            }
                        }
                    }
                }
            }
        }, 0, 1);
    }

    private void note()
    {
        if (Bukkit.getServer().getOnlinePlayers().size() >= 1) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (LobbyMenuPlugin.noparticules.contains(online)) continue;
                if (LobbyMenuPlugin.particles.get(ParticleType.NOTE).size() >= 1)
                for (Player particleplayer : LobbyMenuPlugin.particles.get(ParticleType.NOTE)) {
                    Location loc = particleplayer.getLocation();
                    loc.setY(particleplayer.getLocation().getY() + 2.0);
                    online.playEffect(loc, Effect.NOTE, 2);
                }
            }
        }
    }

    private ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = 6.283185307179586 / (double)amount;
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            double angle = (double)i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    public static void clear(Player player) {
        LobbyMenuPlugin.particles.get(ParticleType.FLAME).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.PLUIE).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.NEIGE).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.NOTE).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.HEARTS).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.ENCHANTMENT).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.SLIMEBALL).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.LAVA).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.ANGRY_VILLAGER).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.HAPPY_VILLAGER).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.SMOKE).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.DIAMOND).remove(player);
        LobbyMenuPlugin.particles.get(ParticleType.EMERALD).remove(player);
    }

    /*public static String getParticleQueryHasString(particulestype type, boolean get) {
        if (get) {
            if (type == particulestype.Flame) {
                return "SELECT flame FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Neige) {
                return "SELECT neige FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Pluie) {
                return "SELECT pluie FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Note) {
                return "SELECT note FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Hearts) {
                return "SELECT hearts FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Enchantment) {
                return "SELECT enchantment FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Slimeball) {
                return "SELECT slimeball FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Lava) {
                return "SELECT lava FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Angry_Villager) {
                return "SELECT angry_villager FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Happy_Villager) {
                return "SELECT happy_villager FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Smoke) {
                return "SELECT smoke FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Diamond) {
                return "SELECT diamond FROM particules WHERE uuid = ?";
            }
            if (type == particulestype.Emerald) {
                return "SELECT emerald FROM particules WHERE uuid = ?";
            }
        } else {
            if (type == particulestype.Flame) {
                return "UPDATE particules SET flame =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Neige) {
                return "UPDATE particules SET neige =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Pluie) {
                return "UPDATE particules SET pluie =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Note) {
                return "UPDATE particules SET note =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Hearts) {
                return "UPDATE particules SET hearts =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Enchantment) {
                return "UPDATE particules SET enchantment =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Slimeball) {
                return "UPDATE particules SET slimeball =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Lava) {
                return "UPDATE particules SET lava =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Angry_Villager) {
                return "UPDATE particules SET angry_villager =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Happy_Villager) {
                return "UPDATE particules SET happy_villager =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Smoke) {
                return "UPDATE particules SET smoke =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Diamond) {
                return "UPDATE particules SET diamond =  ? WHERE  uuid =  ?";
            }
            if (type == particulestype.Emerald) {
                return "UPDATE particules SET emerald =  ? WHERE  uuid =  ?";
            }
        }
        return null;
    }*/

    /*public static void setPlayerParticle(Player player, boolean arg1, particulestype type) {
        try {
            String query = Particles.getParticleQueryHasString(type, false);
            PreparedStatement statement = new Mysql().getConnection().prepareStatement(query);
            if (arg1) {
                statement.setInt(1, 1);
            } else {
                statement.setInt(1, 0);
            }
            statement.setString(2, player.getUniqueId().toString());
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int doPlayerHasParticle(Player player, particulestype type) {
        try {
            String query = Particles.getParticleQueryHasString(type, true);
            PreparedStatement statement = new Mysql().getConnection().prepareStatement(query);
            statement.setString(1, player.getUniqueId().toString());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int retur = result.getInt(Particles.getparticulestypeHasString(type));
                statement.close();
                return retur;
            }
            return 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }*/

}

