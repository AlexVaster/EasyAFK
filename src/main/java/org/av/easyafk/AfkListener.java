package org.av.easyafk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class AfkListener implements Listener {

    private final AfkChecker afkChecker;

    public AfkListener(AfkChecker afkChecker) {
        this.afkChecker = afkChecker;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        afkChecker.updateLastActionTime(event.getPlayer());
    }


}
