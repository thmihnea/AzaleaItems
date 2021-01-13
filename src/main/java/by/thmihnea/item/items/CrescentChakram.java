package by.thmihnea.item.items;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.Ability;
import by.thmihnea.item.AzaleaItem;
import by.thmihnea.rarity.Rarity;
import by.thmihnea.runnables.BleedingTask;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CrescentChakram extends AzaleaItem {
    public CrescentChakram(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability, boolean placeable, boolean stackable) {
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
        if (itemizedPlayer.hasCooldown(CooldownType.CYCLICAL_CUTS)) {
            itemizedPlayer.getCooldownByType(CooldownType.CYCLICAL_CUTS).sendCooldownMessage();
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (attributedPlayer.getCurrentMana() < this.getManaCost()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough mana to use this ability!"));
            return;
        }
        attributedPlayer.setCurrentMana(attributedPlayer.getCurrentMana() - this.getManaCost());
        Cooldown cooldown = new Cooldown(player, CooldownType.CYCLICAL_CUTS, 45);
        itemizedPlayer.addCooldown(cooldown);
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (!(entity instanceof Player)) return;
            Player target = (Player) entity;
            FPlayer targetf = FPlayers.getInstance().getByPlayer(target);
            if (self.getFaction().equals(targetf.getFaction())) return;
            double damage = attributedPlayer.getDamage() * 0.25;
            new BleedingTask(target, (int) damage, 10);
        });
    }
}
