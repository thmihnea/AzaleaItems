package by.thmihnea.api;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ItemizedPlayerManager {

    private static HashMap<UUID, ItemizedPlayer> itemizedPlayers = new HashMap<>();

    public static void addItemizedPlayer(Player player, ItemizedPlayer itemizedPlayer) {
        itemizedPlayers.put(player.getUniqueId(), itemizedPlayer);
    }

    public static void removeItemizedPlayer(Player player) {
        itemizedPlayers.remove(player.getUniqueId());
    }

    public static ItemizedPlayer getItemizedPlayer(Player player) {
        return itemizedPlayers.get(player.getUniqueId());
    }
}
