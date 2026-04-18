package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public class ExampleMenu extends Menu {

    private final PotuzhniAPI plugin;
    private final MenuLoader menuLoader;
    private final ConfigurationSection config;

    public ExampleMenu(PotuzhniAPI plugin, ConfigurationSection config) {
        super(plugin);
        this.plugin = plugin;
        this.menuLoader = plugin.getMenuLoader();
        this.config = config;
    }

    @Override
    public void onSetItems() {
        ConfigurationSection itemsSection = config.getConfigurationSection("items");
        if (itemsSection == null) return;

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
            if (itemSection == null) continue;

            int slot = itemSection.getInt("slot");
            menuLoader.setItems(this, slot, itemSection);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        String title = config.getString("title", "Menu");
        int size = config.getInt("size", 9);
        Inventory inventory = Bukkit.createInventory(this, size, parse(title));

        items.forEach(inventory::setItem);
        return inventory;
    }
}
