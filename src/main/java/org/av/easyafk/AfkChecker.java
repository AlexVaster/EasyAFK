package org.av.easyafk;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AfkChecker {
    private final HashMap<Player, Long> lastActionTimes = new HashMap<>();
    private final HashMap<Player, Boolean> isAFK = new HashMap<>();

    public void updateLastActionTime(Player player) {
        lastActionTimes.put(player, System.currentTimeMillis());
        if (isAFK.getOrDefault(player, false)) {
            isAFK.put(player, false);
            sendMeCommand(player, "is now active");
        }
    }

    public void checkAfkPlayers(long minute) {
        long afkTimeThreshold = 60L * 1000 * minute; // 1 minute * minute from config

        for (Player player : Bukkit.getOnlinePlayers()) {
            long lastActionTime = lastActionTimes.getOrDefault(player, 0L);
            long currentTime = System.currentTimeMillis();

            if (!isAFK.containsKey(player)) {
                if (currentTime - lastActionTime > afkTimeThreshold) {
                    sendMeCommand(player,"afk");
                    isAFK.put(player, true);
                }
            } else {
                if (!isAFK.get(player)) {
                    if (currentTime - lastActionTime > afkTimeThreshold) {
                        sendMeCommand(player,"afk");
                        isAFK.put(player, true);
                    }
                }
            }
        }
    }

    public void sendMeCommand(CommandSender sender, String message) {
        Bukkit.dispatchCommand(sender, "me " + message);
    }

    public boolean isPlayerAFK(Player player) {
        return isAFK.getOrDefault(player, false);
    }

    public void setPlayerAFK(Player player, boolean isAFK) {
        this.isAFK.put(player, isAFK);
    }
}
