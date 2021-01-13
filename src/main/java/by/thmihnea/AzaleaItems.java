package by.thmihnea;

import by.thmihnea.item.*;
import by.thmihnea.item.armors.*;
import by.thmihnea.item.items.*;
import by.thmihnea.rarity.Rarity;
import com.cryptomorin.xseries.XMaterial;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AzaleaItems extends JavaPlugin {

    public static Map<Integer, AzaleaArmor> armors = new HashMap<>();
    public static Map<Integer, AzaleaItem> items = new HashMap<>();

    private static AzaleaItems instance;
    public static AzaleaItems getInstance() {
        return instance;
    }
    private long timeEnabled;
    public static File config = new File("plugins/AzaleaItems/config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);
    public List<Listener> events = new ArrayList<>();
    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        timeEnabled = System.currentTimeMillis();
        System.out.println("[AzaleaItems] Attempting to enable plugin modules...");
        instance = this;
        Util.setupFiles();
        Util.registerEvents();
        Util.initObjects();
        Util.registerArmors();
        Util.registerItems();
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 35; i <= items.size(); i++)
                player.getInventory().addItem(items.get(i).getItemStack());
        }
        System.out.println("[AzaleaItems] Successfully enabled plugin. Process took: " + (System.currentTimeMillis() - this.timeEnabled));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void addArmor(Integer id, AzaleaArmor azaleaArmor) {
        armors.put(id, azaleaArmor);
    }

    public void addItem(Integer id, AzaleaItem azaleaItem) {
        items.put(id, azaleaItem);
    }

    @Override
    public void onDisable() {
        System.out.println("[AzaleaItems] Disabling plugin modules. Bye!");
    }

    public static Economy getEconomy() {
        return econ;
    }
}
