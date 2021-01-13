package by.thmihnea.item.items;

import by.thmihnea.item.Ability;
import by.thmihnea.item.AzaleaItem;
import by.thmihnea.rarity.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class Rock extends AzaleaItem {

    public Rock(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability) {
        super(material, name, type, id, rarity, health, defense, attackDamage, critDamage, critChance, mana, manaCost, ability, false, false);
    }

    @Override
    public void onItemStackCreate(ItemStack paramItemStack) {

    }

    @Override
    public void leftClickAirAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void leftClickBlockAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void rightClickAirAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void rightClickBlockAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void shiftLeftClickAirAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void shiftLeftClickBlockAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void shiftRightClickAirAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void shiftRightClickBlockAction(Player paramPlayer, PlayerInteractEvent e) {

    }

    @Override
    public void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent e) {

    }

    @Override
    public void rightClickEntityAction(Player paramPlayer, PlayerInteractEntityEvent e) {

    }

    @Override
    public void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent e) {
        int hash = ThreadLocalRandom.current().nextInt(1000);
        int chance = 1;
        System.out.println(hash);
        if (hash > chance) return;
        if (!(e.getEntity() instanceof LivingEntity)) return;
        ((LivingEntity) e.getEntity()).damage(((LivingEntity) e.getEntity()).getMaxHealth());
        if (e.getEntity() instanceof Player)
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6" + ((Player) e.getDamager()).getName() + " &ehas K.O.'d &6" + ((Player) e.getEntity()).getName() + " &ewith a Rock!"));
    }
}
