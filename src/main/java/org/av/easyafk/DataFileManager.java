package org.av.easyafk;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DataFileManager {
    private final JavaPlugin plugin;
    private FileConfiguration dataConfig;

    public DataFileManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadFile() {
        File dataFile = new File(plugin.getDataFolder(), "config.yml");

        if (!dataFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

}
