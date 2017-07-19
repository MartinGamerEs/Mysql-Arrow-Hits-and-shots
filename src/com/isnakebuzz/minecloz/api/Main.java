package com.isnakebuzz.minecloz.api;

import com.isnakebuzz.minecloz.datatransfer.*;
import com.isnakebuzz.minecloz.listeners.Events;
import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin{
    
    // publics :) //
    public static MySQL mysql;
    public static Main INSTANCE;
    
    @Override
    public void onEnable() {
        saveConfig();
        saveDefaultConfig();
        Main.mysql = new MySQL();
        this.init();
        MySQL.connect();
        MySQL.update("CREATE TABLE IF NOT EXISTS Data_API (Name VARCHAR(100), Ashots long, Ahits long)");
        this.getServer().getPluginManager().registerEvents(new Events(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!MySQL.isConnected()){
                    MySQL.connect();
                }
            }
        }.runTaskTimer(this, 240 * 20, 240 * 20);
    }
    
    
    public void init() {
        final File file = new File(this.getDataFolder().getPath(), "config.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        MySQL.host = config.getString("MySQL.hostname");
        MySQL.port = config.getString("MySQL.port");
        MySQL.database = config.getString("MySQL.database");
        MySQL.username = config.getString("MySQL.username");
        MySQL.password = config.getString("MySQL.password");
    }
    
}
