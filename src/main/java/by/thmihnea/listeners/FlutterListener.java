package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlutterListener implements Listener {

    @EventHandler
    public void doubleJump(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        e.setCancelled(true);
        player.setFlying(false);
        if (!Util.getHalfSetIDs(player).contains(7)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.FLUTTER)) return;
        player.setAllowFlight(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));
        Cooldown cooldown = new Cooldown(player, CooldownType.FLUTTER, 5);
        itemizedPlayer.addCooldown(cooldown);
    }
}
