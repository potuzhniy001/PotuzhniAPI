package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static io.github.potuzhniAPI.PotuzhniAPI.getPlugin;
import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public abstract class AbstractSimpleMenu implements Menu, InventoryHolder {

    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Map<Integer, ItemStack>        items = new HashMap<>();

    private final Inventory inventory;

    public AbstractSimpleMenu(String menuKey) {
        ConfigurationSection config = getPlugin().getConfig().getConfigurationSection("menus." + menuKey);
        if (config == null) { throw new IllegalArgumentException("Menu section " + menuKey + " not found!"); }

        String title = config.getString("title", "Default Title");
        int rows = config.getInt("rows", 3) * 9;

        inventory = Bukkit.createInventory(this, rows, parse(title));

        PotuzhniAPI.getUtils().loadItems(this, config.getConfigurationSection("items"));
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


    @Override
    public @NotNull Inventory getInventory() { return inventory; }


    @Override
    public Map<Integer, ItemStack> getItemsMap() { return items; }


    @Override
    public Map<Integer, Consumer<Player>> getActionsMap() { return actions; }


}