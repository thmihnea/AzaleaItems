package by.thmihnea.listeners;

import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.runnables.DoubleJumpTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new ItemizedPlayer(e.getPlayer());
    }
}
