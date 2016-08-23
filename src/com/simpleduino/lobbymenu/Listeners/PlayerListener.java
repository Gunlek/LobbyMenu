package com.simpleduino.lobbymenu.Listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.economy.API.EconomicAPI;
import com.simpleduino.economy.API.EconomicEntities.EconomicAccount;
import com.simpleduino.lobbymenu.Inventories.*;
import com.simpleduino.lobbymenu.LobbyMenuPlugin;
import com.simpleduino.lobbymenu.SQL.LobbySQL;
import com.simpleduino.lobbymenu.ServersListing;
import com.simpleduino.lobbymenu.particules.Particles;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class PlayerListener implements Listener {

    ServersListing serverChecker = new ServersListing();
    LobbySQL lobbySQL = new LobbySQL();
    private File f = new File("plugins/LobbyMenu/playerSettings.yml");
    private YamlConfiguration playerSettings = YamlConfiguration.loadConfiguration(f);
    private EconomicAPI economicAPI = new EconomicAPI();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if(!lobbySQL.isPlayerParticlesRegistered(e.getPlayer()))
        {
            lobbySQL.registerPlayerParticles(p);
        }
        if(!playerSettings.contains(e.getPlayer().getUniqueId().toString()))
        {
            playerSettings.set(e.getPlayer().getUniqueId().toString()+".enable-chat", "true");
            playerSettings.set(e.getPlayer().getUniqueId().toString()+".enable-particles", "true");
            playerSettings.set(e.getPlayer().getUniqueId().toString()+".player-visibility", "true");
            try {
                playerSettings.save(f);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        p.getInventory().clear();
        ItemStack compassMenu = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compassMenu.getItemMeta();
        compassMeta.setDisplayName(ChatColor.GREEN + "Menu Principal");
        compassMenu.setItemMeta(compassMeta);
        p.getInventory().setItem(0, compassMenu);

        ItemStack PlayerHead = new ItemStack(Material.SKULL_ITEM, 1);
        PlayerHead.setDurability((short)3);
        SkullMeta skullMeta = (SkullMeta)PlayerHead.getItemMeta();
        skullMeta.setOwner(p.getName());
        skullMeta.setDisplayName(ChatColor.GREEN + "Profil");
        PlayerHead.setItemMeta(skullMeta);
        p.getInventory().setItem(1, PlayerHead);

        ItemStack comparatorMenu = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        ItemMeta comparatorMeta = comparatorMenu.getItemMeta();
        comparatorMeta.setDisplayName(ChatColor.GREEN + "Paramètres");
        comparatorMenu.setItemMeta(comparatorMeta);
        p.getInventory().setItem(2, comparatorMenu);

        ItemStack particleMenu = new ItemStack(Material.REDSTONE, 1);
        ItemMeta particleMeta = particleMenu.getItemMeta();
        particleMeta.setDisplayName(ChatColor.YELLOW + "Particules");
        particleMenu.setItemMeta(particleMeta);
        p.getInventory().setItem(3, particleMenu);

        if(!economicAPI.hasAccount(p))
            economicAPI.createAccount(p);
        EconomicAccount economicAccount = economicAPI.getAccount(p);

        Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sc.registerNewObjective("endlessfight", "dummy");
        Score player_number = obj.getScore(ChatColor.BLUE+"Joueurs (Lobby): "+ChatColor.RED + Integer.toString(Bukkit.getOnlinePlayers().size()));
        player_number.setScore(-1);
        Score tokens = obj.getScore(ChatColor.GOLD+"Tokens: "+ChatColor.RED+"0");
        tokens.setScore(-2);
        Score coins = obj.getScore(ChatColor.YELLOW+"Coins: "+ChatColor.RED+"0");
        coins.setScore(-3);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.DARK_GREEN.toString()+ChatColor.BOLD+"Endless"+ChatColor.WHITE+"Fight");
        p.setScoreboard(sc);
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        Particles.clear(p);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) ||e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            ItemStack inHand = e.getPlayer().getItemInHand();
            if(inHand != null && !inHand.getType().equals(Material.AIR)) {
                String inHandItemName = ChatColor.stripColor(inHand.getItemMeta().getDisplayName());
                if(inHandItemName != null && !inHandItemName.equalsIgnoreCase("")) {
                    switch (inHandItemName.toLowerCase()) {
                        case "menu principal":
                            e.getPlayer().openInventory(new MainMenu().getInventory());
                            e.setCancelled(true);
                            break;

                        case "profil":
                            e.getPlayer().openInventory(new ProfileMenu().getInventory());
                            e.setCancelled(true);
                            break;

                        case "paramètres":
                            e.getPlayer().openInventory(new SettingsMenu(e.getPlayer()).getInventory());
                            e.setCancelled(true);
                            break;

                        case "particules":
                            e.getPlayer().openInventory(new ParticleMenu(e.getPlayer()).getInventory());
                            e.setCancelled(true);
                            break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractMainMenu(InventoryClickEvent e) {
        if (e.getInventory().getTitle() != null && ChatColor.stripColor(e.getInventory().getTitle()).equalsIgnoreCase("menu principal")) {
            if (e.getCurrentItem() != null) {
                ItemStack clickedItem = e.getInventory().getItem(e.getSlot());
                if (!clickedItem.getType().equals(Material.STAINED_GLASS_PANE) && !clickedItem.getType().equals(Material.AIR)) {
                    String serverType = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());
                    serverType = serverType.replace("é", "e");
                    serverType = serverType.replace("è", "e");
                    serverType = serverType.replace(" ", "_");
                    serverType = serverType.toLowerCase();
                    e.getWhoClicked().closeInventory();
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();

                    out.writeUTF("ConnectOther");
                    out.writeUTF(e.getWhoClicked().getName());
                    out.writeUTF(serverChecker.getOnlineServers(serverType).get(0));

                    Player player = (Player) e.getWhoClicked();

                    player.sendPluginMessage(LobbyMenuPlugin.getPlugin(LobbyMenuPlugin.class), "BungeeCord", out.toByteArray());
                    e.setCancelled(true);
                }
            }
        }
    }

}
