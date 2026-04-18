package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public class TestPaginatedMenu extends PaginatedMenu<ConfigurationSection> {

    private final PotuzhniAPI plugin;
    private final ConfigurationSection config;

    public TestPaginatedMenu(PotuzhniAPI plugin, ConfigurationSection config) {
        super(plugin);
        this.plugin = plugin;
        this.config = plugin.getConfig().getConfigurationSection("paginated-menu");
    }

    @Override
    public void onSetItems() {
        ConfigurationSection itemsSection = config.getConfigurationSection("items");
        if (itemsSection == null) return;

        Map<ItemStack, Consumer<Player>> entries = new LinkedHashMap<>();

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
            if (itemSection == null) return;

            ItemStack item = plugin.getMenuLoader().parseItem(itemSection);
            Consumer<Player> action = plugin.getMenuLoader().parseAction(itemSection);

            entries.put(item, action != null ? action : player -> {});
        }

        addAll(entries);
    }

    @Override
    public @NotNull Inventory getInventory() {
        String title = config.getString("title", "Paginated menu");
        int size = config.getInt("size", 54);
        Inventory inventory = Bukkit.createInventory(this, size, parse(title));
        items.forEach(inventory::setItem);
        return inventory;
    }
}
