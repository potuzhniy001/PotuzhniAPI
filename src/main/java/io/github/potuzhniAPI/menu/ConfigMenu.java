package io.github.potuzhniAPI.menu;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ConfigMenu implements Menu {

    private final Inventory inventory;
    private final ConfigurationSection config;
    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();

    public ConfigMenu(ConfigurationSection config) {
        this.config = config;
        String title = config.getString("title", "<red>Menu");
        int size = config.getInt("size", 27);
        this.inventory = Bukkit.createInventory(this, size,
                MiniMessage.miniMessage().deserialize(title));
    }

    @Override
    public void click(Player player, int slot) {
        if (actions.containsKey(slot)) {
            actions.get(slot).accept(player);
        }
    }

    @Override
    public void setItem(int slot, ItemStack item) {

    }

    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        inventory.setItem(slot, item);
        actions.put(slot, action);
    }

    @Override
    public void onSetItems() {
        ConfigurationSection itemsSection = config.getConfigurationSection("items");
        if (itemsSection == null) return;

        for (String key : itemsSection.getKeys(false)) {
            int slot = Integer.parseInt(key);
            ConfigurationSection itemData = itemsSection.getConfigurationSection(key);

            ItemStack item = new ItemStack(Material.valueOf(itemData.getString("material")));
            ItemMeta meta = item.getItemMeta();

            meta.displayName(MiniMessage.miniMessage().deserialize(
                    itemData.getString("display-name", "<red>Display name")));

            item.setItemMeta(meta);

            List<String> action = itemData.getStringList("actions");

            setItem(slot, item, player -> {
                if (actions == null) return;

                for (String actions : action) {

                    String processedCommand = PlaceholderAPI.setPlaceholders(player, actions);

                    if (actions.startsWith("[console] ")) {
                        String command = processedCommand.replace("[console] ", "").trim();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                    }

                    else if (actions.startsWith("[player] ")) {
                        String command = processedCommand.replace("[player] ", "").trim();
                        player.performCommand(command);
                    }

                    else {
                        player.performCommand(processedCommand);
                    }
                }
            });
        }
    }

    @Override
    public boolean usePlaceholders() {
        return false;
    }

    @Override
    public void setPlaceholders() {

    }

    @Override
    public void update() {

    }

    @Override
    public Map<Integer, ItemStack> getItemsMap() {
        return Map.of();
    }

    @Override
    public Map<Integer, Consumer<Player>> getActionsMap() {
        return Map.of();
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
