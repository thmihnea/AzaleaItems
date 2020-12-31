package by.thmihnea.listeners;

import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ItemizedPlayerManager.removeItemizedPlayer(e.getPlayer());
    }
}
