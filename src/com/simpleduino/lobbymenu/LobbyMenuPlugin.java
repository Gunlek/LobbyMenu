package com.simpleduino.lobbymenu;

import com.simpleduino.lobbymenu.Listeners.MenuListeners.*;
import com.simpleduino.lobbymenu.Listeners.ParticleListener;
import com.simpleduino.lobbymenu.Listeners.PlayerListener;
import com.simpleduino.lobbymenu.Messaging.MessageListener;
import com.simpleduino.lobbymenu.Runnable.actionBarRunnable;
import com.simpleduino.lobbymenu.Runnable.updateScoreboardRunnable;
import com.simpleduino.lobbymenu.Runnable.updateScoreboardTitle;
import com.simpleduino.lobbymenu.particules.Particles;
import com.simpleduino.shopAPI.APIObjects.ParticleType;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simple-Duino on 07/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class LobbyMenuPlugin extends JavaPlugin {

    private File cfgFile = new File("plugins/LobbyMenu/config.yml");
    private File f = new File("plugins/LobbyMenu/playerSettings.yml");

    public static HashMap<ParticleType, ArrayList<Player>> particles = new HashMap<>();

    public static ArrayList<Player> noparticules = new ArrayList<>();

    public void onEnable()
    {
        if(!cfgFile.exists())
        {
            cfgFile.getParentFile().mkdirs();
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
            cfg.set("sql.server-list.db-host", "localhost");
            cfg.set("sql.server-list.db-name", "main_management");
            cfg.set("sql.server-list.db-user", "root");
            cfg.set("sql.server-list.db-password", "");

            try {
                cfg.save(cfgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!f.exists())
        {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        new Particles().initParticles();

        new ServersListing();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getServer().getPluginManager().registerEvents(new SettingsMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new ParticleListener(), this);
        this.getServer().getPluginManager().registerEvents(new ParticleMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new ConfirmationMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new CosmeticMenuListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new updateScoreboardRunnable(), 20L, 20L*5);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new updateScoreboardTitle(), 1L, 2L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new actionBarRunnable(), 1L, 2L);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());
    }

    public void onDisable()
    {

    }

    public static ArrayList<String> nonacheté(int prix, boolean coins, int grade, Player player) {
        ArrayList<String> a = new ArrayList<>();
        a.add(ChatColor.DARK_RED + "Non Acheté");
        if (coins) {
            a.add(ChatColor.YELLOW + "Acheter pour : " + prix + " coins");
        } else {
            a.add(ChatColor.YELLOW + "Acheter pour : " + prix + " tokens");
        }
        /*if (new Money().getGrade(player) < grade) {
            a.add("§eRéservé pour le grade : " + new EndlessAPI().getGradeChatStyle(grade));
        }*/
        return a;
    }

}
