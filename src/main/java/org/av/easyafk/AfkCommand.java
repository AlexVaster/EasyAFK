package org.av.easyafk;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkCommand implements CommandExecutor {
    private final AfkChecker afkChecker;

    public AfkCommand(AfkChecker afkChecker) {
        this.afkChecker = afkChecker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("afk")) {
            if (sender instanceof Player player) {
                if (!afkChecker.isPlayerAFK(player)) {
                    afkChecker.setPlayerAFK(player, true);
                    afkChecker.sendMeCommand(player, "afk");
                } else {
                    afkChecker.setPlayerAFK(player, false);
                    afkChecker.sendMeCommand(player, "is now active");
                }
            } else {
                sender.sendMessage("This command only for players!");
            }
            return true;
        }
        return false;
    }

}
