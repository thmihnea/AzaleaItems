package by.thmihnea.items;

import by.thmdev.api.AttributePlaceholder;
import by.thmihnea.rarity.Rarity;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;

public abstract class AzaleaArmor {

    private ItemStack itemStack;
    private Material material;
    private String name;
    private Integer objectID;
    private Integer id;
    private Rarity rarity;
    private Integer hp;
    private Integer defense;
    private Integer mana;
    private Integer damage;
    private Integer critDamage;
    private Integer critChance;
    private SetBonus halfSetBonus;
    private SetBonus fullSetBonus;
    private Map<String, Integer> loreStats = new HashMap<>();

    public AzaleaArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
        this.material = material;
        this.name = name;
        this.objectID = objectID;
        this.id = id;
        this.rarity = rarity;
        this.hp = hp;
        this.defense = defense;
        this.mana = mana;
        this.damage = damage;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.halfSetBonus = halfSetBonus;
        this.fullSetBonus = fullSetBonus;
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Health: &a", this.hp);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Defense: &3", this.defense);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Mana: &b", this.mana);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Damage: &c", this.damage);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Crit Damage: &e", this.critDamage);
        this.loreStats.put(this.rarity.getColor() + "• " + "&7Crit Chance: &e", this.critChance);
        this.reloadItemStack();
    }

    public abstract void fullSetBonusNoEvent(Player paramPlayer);

    public abstract void fullSetBonus(Player paramPlayer, Event event);

    public abstract void halfSetBonusNoEvent(Player paramPlayer);

    public abstract void halfSetBonus(Player paramPlayer, Event event);

    public SetBonus getHalfSetBonus() {
        return this.halfSetBonus;
    }

    public SetBonus getFullSetBonus() {
        return this.fullSetBonus;
    }

    public Map<String, Integer> getLoreStats() {
        return this.loreStats;
    }

    public Material getMaterial() {
        return this.material;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getObjectID() {
        return this.objectID;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public Integer getHp() {
        return this.hp;
    }

    public Integer getDefense() {
        return this.defense;
    }

    public Integer getMana() {
        return this.mana;
    }

    public Integer getDamage() {
        return this.damage;
    }

    public Integer getCritDamage() {
        return this.critDamage;
    }

    public Integer getCritChance() {
        return this.critChance;
    }

    public void reloadItemStack() {
        ItemStack item = new ItemStack(this.material);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(this.rarity.getColor() + this.name);
        List<String> loreStats = new ArrayList<>();
        List<String> setBonuses = new ArrayList<>();
        halfSetBonus.getSetLore().forEach(str -> {
            setBonuses.add(ChatColor.translateAlternateColorCodes('&', str));
        });
        fullSetBonus.getSetLore().forEach(str -> {
            setBonuses.add(ChatColor.translateAlternateColorCodes('&', str));
        });
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
        finalLore.addAll(setBonuses);
        finalLore.add("");
        finalLore.add(this.rarity.getColor() + "" + ChatColor.BOLD + this.rarity.name() + " ARMOR PIECE");
        meta.setLore(finalLore);
        meta.setColor(this.rarity.getArmorColor());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        NBTItem nbt = new NBTItem(item);
        nbt.setInteger(AttributePlaceholder.HEALTH_ARMOR.getPlaceholder(), this.hp);
        nbt.setInteger(AttributePlaceholder.DEFENSE_ARMOR.getPlaceholder(), this.defense);
        nbt.setInteger(AttributePlaceholder.MANA_ARMOR.getPlaceholder(), this.mana);
        nbt.setInteger(AttributePlaceholder.DAMAGE_ARMOR.getPlaceholder(), this.damage);
        nbt.setInteger(AttributePlaceholder.CRIT_DAMAGE_ARMOR.getPlaceholder(), this.critDamage);
        nbt.setInteger(AttributePlaceholder.CRIT_CHANCE_ARMOR.getPlaceholder(), this.critChance);
        nbt.setInteger("Unbreakable", 1);
        nbt.setInteger("ID", this.id);
        this.itemStack = nbt.getItem();
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
