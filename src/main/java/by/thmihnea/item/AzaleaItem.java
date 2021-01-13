package by.thmihnea.item;

import by.thmdev.api.AttributePlaceholder;
import by.thmihnea.rarity.Rarity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AzaleaItem {

    private ItemStack itemStack;
    private Material material;
    private String name;
    private String type;
    private Rarity rarity;
    private Ability ability;
    private int id;
    private int health;
    private int defense;
    private int attackDamage;
    private int critDamage;
    private int critChance;
    private int mana;
    private int manaCost;
    private boolean placeable;
    private boolean stackable;
    private Map<String, Integer> loreStats = new HashMap<>();

    public AzaleaItem(Material material, String name, String type, int id, Rarity rarity, int health, int defense, int attackDamage, int critDamage, int critChance, int mana, int manaCost, Ability ability, boolean placeable, boolean stackable) {
        this.material = material;
        this.name = name;
        this.type = type;
        this.id = id;
        this.rarity = rarity;
        this.health = health;
        this.defense = defense;
        this.attackDamage = attackDamage;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.mana = mana;
        this.manaCost = manaCost;
        this.ability = ability;
        this.placeable = placeable;
        this.stackable = stackable;
        this.initLoreStats();
        this.reloadItemStack();
    }

    public boolean isPlaceable() {
        return this.placeable;
    }

    public void setPlaceable(boolean placeable) {
        this.placeable = placeable;
    }

    public boolean isStackable() {
        return this.stackable;
    }

    public abstract void onItemStackCreate(ItemStack paramItemStack);

    public abstract void leftClickAirAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void leftClickBlockAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void rightClickAirAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void rightClickBlockAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void shiftLeftClickAirAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void shiftLeftClickBlockAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void shiftRightClickAirAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void shiftRightClickBlockAction(Player paramPlayer, PlayerInteractEvent e);

    public abstract void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent e);

    public abstract void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent e);

    public abstract void rightClickEntityAction(Player paramPlayer, PlayerInteractEntityEvent e);

    private void initLoreStats() {
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Health: &a", this.health);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Defense: &3", this.defense);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Mana: &b", this.mana);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Damage: &c", this.attackDamage);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Crit Damage: &e", this.critDamage);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Crit Chance: &e", this.critChance);
    }

    public void reloadItemStack() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(this.rarity.getColor() + this.name);
        Set<String> loreStats = new HashSet<>();
        List<String> abilities = new ArrayList<>();
        if (this.ability == null) abilities = Collections.emptyList();
        else {
            List<String> finalAbilities = abilities;
            this.ability.getAbilityLore().forEach(str -> {
                finalAbilities.add(ChatColor.translateAlternateColorCodes('&', str));
            });
            abilities = finalAbilities;
        }
        this.loreStats.keySet().forEach(str -> {
            if (!this.loreStats.get(str).equals(0))
                if (str.toUpperCase().contains("CRIT"))
                    loreStats.add(ChatColor.translateAlternateColorCodes('&', str + String.format("%,d", this.loreStats.get(str)) + "%"));
                else
                    loreStats.add(ChatColor.translateAlternateColorCodes('&', str + String.format("%,d", this.loreStats.get(str))));
        });
        List<String> finalLore = new ArrayList<>();
        finalLore.addAll(loreStats);
        finalLore.add("");
        if (this.ability != null) {
            finalLore.addAll(abilities);
            finalLore.add("");
        }
        finalLore.add(this.rarity.getColor() + "" + ChatColor.BOLD + this.rarity.name() + " " + this.type.toUpperCase());
        meta.setLore(finalLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        NBTItem nbt = new NBTItem(itemStack);
        nbt.setInteger(AttributePlaceholder.ITEM_HEALTH.getPlaceholder(), this.health);
        nbt.setInteger(AttributePlaceholder.ITEM_DEFENSE.getPlaceholder(), this.defense);
        nbt.setInteger(AttributePlaceholder.ITEM_MANA.getPlaceholder(), this.mana);
        nbt.setInteger(AttributePlaceholder.ITEM_MANA_COST.getPlaceholder(), this.manaCost);
        nbt.setInteger(AttributePlaceholder.ITEM_DAMAGE.getPlaceholder(), this.attackDamage);
        nbt.setInteger(AttributePlaceholder.ITEM_CRIT_DAMAGE.getPlaceholder(), this.critDamage);
        nbt.setInteger(AttributePlaceholder.ITEM_CRIT_CHANCE.getPlaceholder(), this.critChance);
        nbt.setInteger("Unbreakable", 1);
        nbt.setInteger("ID", this.id);
        if (!isStackable()) {
            int hash = ThreadLocalRandom.current().nextInt(999999999);
            nbt.setInteger("hash", hash);
        }
        this.itemStack = nbt.getItem();
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
