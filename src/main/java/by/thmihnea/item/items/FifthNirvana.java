package by.thmihnea.item.items;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.Ability;
import by.thmihnea.item.AzaleaItem;
import by.thmihnea.rarity.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.C;

public class FifthNirvana extends AzaleaItem {
    public FifthNirvana(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability, boolean placeable, boolean stackable) {
        super(material, name, type, id, rarity, health, defense, attackDamage, critDamage, critChance, mana, manaCost, ability, placeable, stackable);
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
        this.init(paramPlayer);
    }

    @Override
    public void rightClickBlockAction(Player paramPlayer, PlayerInteractEvent e) {
        this.init(paramPlayer);
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
    public void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent e) {

    }

    @Override
    public void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent e) {

    }

    @Override
    public void rightClickEntityAction(Player paramPlayer, PlayerInteractEntityEvent e) {

    }

    private void init(Player player) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.GREATER_GOOD)) {
            itemizedPlayer.getCooldownByType(CooldownType.GREATER_GOOD).sendCooldownMessage();
            return;
        }
        Cooldown cooldown = new Cooldown(player, CooldownType.GREATER_GOOD, 120);
        itemizedPlayer.addCooldown(cooldown);
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        int health = (int) (attributedPlayer.getMaxHealth() * 0.10);
        int damage = (int) (attributedPlayer.getDamage() * 0.15);
        attributedPlayer.setDamage(attributedPlayer.getDamage() + damage);
        attributedPlayer.setMaxHealth(attributedPlayer.getMaxHealth() - health);
        if (attributedPlayer.getMaxHealth() - player.getHealth() < 0) player.setHealth(attributedPlayer.getMaxHealth());
        player.setMaxHealth(attributedPlayer.getMaxHealth());
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            attributedPlayer.setMaxHealth(attributedPlayer.getMaxHealth() + health);
            player.setMaxHealth(attributedPlayer.getMaxHealth());
            attributedPlayer.setDamage(attributedPlayer.getDamage() - damage);
        }, 400L);
    }
}
