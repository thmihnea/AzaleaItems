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
import by.thmihnea.runnables.MeteorTask;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MeteorClub extends AzaleaItem {
    public MeteorClub(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability, boolean placeable, boolean stackable) {
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
        this.init(paramPlayer, e.getClickedBlock());
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
        this.init(paramPlayer, e.getClickedBlock());
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

    private void init(Player player, Block block) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.METEOR_SHOWER)) {
            itemizedPlayer.getCooldownByType(CooldownType.METEOR_SHOWER).sendCooldownMessage();
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (attributedPlayer.getCurrentMana() < this.getManaCost()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have enough mana to use this ability!"));
            return;
        }
        attributedPlayer.setCurrentMana(attributedPlayer.getCurrentMana() - this.getManaCost());
        Cooldown cooldown = new Cooldown(player, CooldownType.METEOR_SHOWER, 5);
        itemizedPlayer.addCooldown(cooldown);
        Location l1 = block.getLocation().add(0, 50, 0);
        Location l2 = l1.clone().add(7, 0, 7);
        Location l3 = l1.clone().add(7, 0, -7);
        Arrays.asList(l1, l2, l3).forEach(location -> this.spawnMeteor(player, location));
    }

    private void spawnMeteor(Player player, Location location) {
        int random = ThreadLocalRandom.current().nextInt(1, 30);
        double velocity_component = random * 0.01;
        Location l1 = location.clone().add(0, 1, 0);
        Location l2 = location.clone().add(0, -1, 0);
        Location l3 = location.clone().add(1, 0, 0);
        Location l4 = location.clone().add(0, 0, 1);
        Location l5 = location.clone().add(-1, 0, 0);
        Location l6 = location.clone().add(0, 0, -1);
        Arrays.asList(location, l1, l2, l3, l4, l5, l6).forEach(loc -> {
            FallingBlock f = player.getWorld().spawnFallingBlock(loc, XMaterial.COBBLESTONE.parseMaterial(), (byte) 0);
            f.setVelocity(new Vector(velocity_component, f.getVelocity().getY(), -velocity_component));
            f.setDropItem(false);
            new MeteorTask(f, player);
        });
    }

    /*
            Location l1 = location.clone().add(0, 1, 0);
        Location l2 = location.clone().add(0, -1, 0);
        Location l3 = location.clone().add(1, 0, 0);
        Location l4 = location.clone().add(0, 0, 1);
        Location l5 = location.clone().add(-1, 0, 0);
        Location l6 = location.clone().add(0, 0, -1);
        Arrays.asList(location, l1, l2, l3, l4, l5, l6).forEach(loc -> {
            FallingBlock f = player.getWorld().spawnFallingBlock(loc, XMaterial.COBBLESTONE.parseMaterial(), (byte) 0);
            f.setVelocity(new Vector(velocity_component, f.getVelocity().getY(), -velocity_component));
            f.setDropItem(false);
            new MeteorTask(f, player);
        });
     */

    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {
        List<Location> circleBlocks = new ArrayList<Location>();
        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();
        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {
                        Location l = new Location(centerBlock.getWorld(), x, y, z);
                        circleBlocks.add(l);
                    }
                }
            }
        }
        return circleBlocks;
    }
}
