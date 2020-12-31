package by.thmihnea.rarity;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum Rarity {
    COMMON(ChatColor.WHITE, Color.WHITE),
    UNCOMMON(ChatColor.GREEN, Color.LIME),
    RARE(ChatColor.BLUE, Color.BLUE),
    EPIC(ChatColor.DARK_PURPLE, Color.PURPLE),
    LEGENDARY(ChatColor.GOLD, Color.YELLOW),
    SPECIAL(ChatColor.RED, Color.RED);

    private ChatColor color;
    private Color armorColor;

    Rarity(ChatColor color, Color armorColor) {
        this.color = color;
        this.armorColor = armorColor;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public Color getArmorColor() {
        return this.armorColor;
    }

    public boolean isRarerThan(Rarity rarity) {
        int current = getIndex();
        int param = rarity.getIndex();
        return (current > param);
    }

    public int getIndex() {
        int index = 0;
        for (Rarity rarity : values()) {
            if (equals(rarity))
                return index;
            index++;
        }
        return -1;
    }
}
