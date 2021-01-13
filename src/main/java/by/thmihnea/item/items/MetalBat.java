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

public class MetalBat extends AzaleaItem {
    public MetalBat(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability, boolean placeable, boolean stackable) {
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
    public void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent e) {

    }

    @Override
    public void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent e) {

    }

    @Override
    public void rightClickEntityAction(Player paramPlayer, PlayerInteractEntityEvent e) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        if (itemizedPlayer.hasCooldown(CooldownType.HOMERUN)) {
            itemizedPlayer.getCooldownByType(CooldownType.HOMERUN).sendCooldownMessage();
            return;
        }
        if (!(e.getRightClicked() instanceof Player)) return;
        Player target = (Player) e.getRightClicked();
        FPlayer self = FPlayers.getInstance().getByPlayer(paramPlayer);
        FPlayer fTarget = FPlayers.getInstance().getByPlayer(target);
        if (self.getFaction().equals(fTarget.getFaction())) {
            self.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can only use this ability on enemies!"));
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(paramPlayer);
        if (attributedPlayer.getCurrentMana() < this.getManaCost()) {
            paramPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough mana to use this ability!"));
            return;
        }
        attributedPlayer.setCurrentMana(attributedPlayer.getMaxMana() - this.getManaCost());
        Cooldown cooldown = new Cooldown(paramPlayer, CooldownType.HOMERUN, 30);
        itemizedPlayer.addCooldown(cooldown);
        target.setVelocity(target.getLocation().getDirection().multiply(-1.8).setY(3.25));
    }
}
