package io.github.potuzhniAPI.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static io.github.potuzhniAPI.PotuzhniAPI.getPlugin;
import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public abstract class AbstractSimpleMenu implements Menu {

    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Map<Integer, ItemStack>        items = new HashMap<>();

    private final Inventory inventory;

    public AbstractSimpleMenu(String menuKey) {
        ConfigurationSection config = getPlugin().getConfig().getConfigurationSection("menus." + menuKey);
        if (config == null) { throw new IllegalArgumentException("Menu section " + menuKey + " not found!"); }

        String title = config.getString("title", "Default Title");
        int rows = config.getInt("rows", 3) * 9;

        this.inventory = Bukkit.createInventory(null, rows, parse(title));

        loadItems(config.getConfigurationSection("items"));
    }

    private void loadItems(ConfigurationSection itemsSection) {
        if (itemsSection == null) return;

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection itemsConfig = itemsSection.getConfigurationSection(key);

            for (String itemsSettings : itemsConfig.getKeys(false)) {
                ConfigurationSection settings = itemsConfig.getConfigurationSection(itemsSettings);

                Material material = Material.matchMaterial(settings.getString("material", "STONE").toUpperCase());

                if (material == null) {
                    getPlugin().getComponentLogger().error("Unknown material in key: {}", key);
                    material = Material.STONE;
                }

                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();

                String displayName = settings.getString("display-name");
                meta.displayName(parse(displayName));

                if (settings.isList("lore")) {
                    List<String> lore = settings.getStringList("lore");
                    List<Component> formattedLore = new ArrayList<>();

                    for (String line : lore) {
                        formattedLore.add(parse(line));
                    }

                    meta.lore(formattedLore);

                } else if (settings.isString("lore")) {
                    String lore = settings.getString("lore", "Default Lore");
                    Component formattedLore = parse(lore);
                    meta.lore(Collections.singletonList(formattedLore));
                }

                int slot = settings.getInt("slot");

                if (slot >= 0 && slot < inventory.getSize()) {
                    inventory.setItem(slot, item);
                }
            }
        }
    }

    /**
     * Accept action from {@code Consumer<Player>}
     */
    @Override
    public void click(Player player, int slot) {
        Consumer<Player> action = this.actions.get(slot);
        if (action != null) { action.accept(player); }
    }


    /**
     * Set items into inventory
     * @param slot The slot where you need to place the item
     * @param item The type of item needed
     * @param action Action to be performed
     */
    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        this.actions.put(slot, action);
        this.items.put(slot, item);
        getInventory().setItem(slot, item);
    }


    /**
     * Update the menu
     */
    @Override
    public void update() {
        getInventory().clear();

        for (int i = 0; i < getInventory().getSize(); i++) {
            ItemStack item = getItemsMap().get(i);
            if (item != null) getInventory().setItem(i, item);
        }
    }


    public abstract void onSetItems();


    @Override
    public @NotNull Inventory getInventory() { return inventory; }


    @Override
    public Map<Integer, ItemStack> getItemsMap() { return items; }


    @Override
    public Map<Integer, Consumer<Player>> getActionsMap() { return actions; }
}