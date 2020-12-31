package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

public class Sturdy implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity()).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(27)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.STURDY)) return;
        if (player.getHealth() - e.getDamage() <= 0) {
            e.setCancelled(true);
            Cooldown cooldown = new Cooldown(player, CooldownType.STURDY, 60);
            itemizedPlayer.addCooldown(cooldown);
            Vector dir = player.getLocation().getDirection();
            Location toTp = player.getLocation().add(dir.multiply(-10));
            player.teleport(toTp);
        }
    }
}
