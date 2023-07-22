package org.av.easyafk;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    AfkChecker afkChecker;
    long afkTime;

    @Override
    public void onEnable() {
        getLogger().info("Plugin activated!");
        DataFileManager dataFileManager = new DataFileManager(this);
        dataFileManager.loadFile();
        afkTime = dataFileManager.getDataConfig().getLong("afk_time");
        afkChecker = new AfkChecker();
        getCommand("afk").setExecutor(new AfkCommand(afkChecker));

        getServer().getPluginManager().registerEvents(new AfkListener(afkChecker), this);
        getServer().getScheduler().runTaskTimer(this, this::checkAfkPlayers, 0L, 20L * 60L);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin deactivated!");
    }

    public void checkAfkPlayers() {
        afkChecker.checkAfkPlayers(this.afkTime);
    }
}
