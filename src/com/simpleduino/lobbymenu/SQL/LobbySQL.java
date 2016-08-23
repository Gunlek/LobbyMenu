package com.simpleduino.lobbymenu.SQL;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simple-Duino on 22/07/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class LobbySQL {

    private File cfgFile = new File("plugins/LobbyMenu/config.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
    private Connection con;

    public LobbySQL()
    {
        String hostname = cfg.get("sql.server-list.db-host").toString();
        String database = cfg.get("sql.server-list.db-name").toString();
        String username = cfg.get("sql.server-list.db-user").toString();
        String password = cfg.get("sql.server-list.db-password").toString();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.con = DriverManager.getConnection("jdbc:mysql://"+hostname+":3306/"+database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<String>> getServerList(String serverType)
    {
        HashMap<String, ArrayList<String>> serverList = new HashMap<>();
        try {
            Statement statement = this.con.createStatement();
            String request = "SELECT * FROM server_list WHERE type=\""+serverType+"\"";
            ResultSet result = statement.executeQuery(request);
            while(result.next())
            {
                ArrayList<String> serverData = new ArrayList<>();
                serverData.add(result.getString("address"));
                serverData.add(result.getString("port"));
                serverData.add(result.getString("type"));

                serverList.put(result.getString("name"), serverData);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serverList;
    }

    public boolean isPlayerParticlesRegistered(Player p)
    {
        try {
            Statement statement = this.con.createStatement();
            String request = "SELECT * FROM player_particles WHERE player_uuid=\""+p.getUniqueId().toString()+"\"";
            ResultSet result = statement.executeQuery(request);
            if(result.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void registerPlayerParticles(Player p)
    {
        try {
            Statement statement = this.con.createStatement();
            String request = "INSERT INTO player_particles(player_uuid, player_name, particles) VALUES(\""+p.getUniqueId().toString()+"\", \""+p.getName()+"\", \"\")";
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPlayerParticles(Player p)
    {
        ArrayList<String> returnList = new ArrayList<>();
        try {
            Statement statement = this.con.createStatement();
            String request = "SELECT * FROM player_particles WHERE player_uuid=\""+p.getUniqueId().toString()+"\"";
            ResultSet result = statement.executeQuery(request);
            if(result.next())
            {
                String particles_inline = result.getString("particles");
                for(String particle : particles_inline.split(", "))
                {
                    returnList.add(particle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnList;
    }

    public void addPlayerParticle(Player p, String particleName)
    {
        ArrayList<String> ParticleList = this.getPlayerParticles(p);
        String inline_particles = "";
        for(String particle : ParticleList)
        {
            inline_particles += particle+", ";
        }
        inline_particles+=particleName+", ";

        try {
            Statement statement = this.con.createStatement();
            String request = "UPDATE player_particles SET particles=\""+inline_particles+"\" WHERE player_uuid=\""+p.getUniqueId().toString()+"\"";
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
